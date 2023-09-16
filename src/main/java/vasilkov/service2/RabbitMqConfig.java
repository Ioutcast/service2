package vasilkov.service2;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@EnableAutoConfiguration
public class RabbitMqConfig {
    final static String queueName = "queue1";
    private final EmailService emailService;
    private final JavaMailSenderImpl emailSsender;

    public RabbitMqConfig(EmailService emailService, JavaMailSenderImpl emailSsender) {
        this.emailService = emailService;
        this.emailSsender = emailSsender;
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
    @Bean
    public Queue myQueue() {
        return new Queue(queueName);
    }

    @Bean
    DirectExchange exchange() {
        return new DirectExchange("testExchange", true, false);
    }
    @Bean
    Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("testRoutingKey");
    }

    @Bean
    public ConnectionFactory connectionFactory()
    {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(
                "localhost");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        return connectionFactory;
    }

    @Bean
    SimpleMessageListenerContainer jmsContainerFactory(
            ConnectionFactory connectionFactory,
            MessageListenerAdapter listenerAdapter)
    {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setQueueNames(queueName);
        container.setMessageListener(listenerAdapter);
        return container;
    }
    @Bean
    JmsReceiver receiver()
    {
        return new JmsReceiver();
    }
    @Bean
    MessageListenerAdapter listenerAdapter(JmsReceiver jmsReceiver)
    {
        return new MessageListenerAdapter(jmsReceiver, "receiveMessage");
    }
}
