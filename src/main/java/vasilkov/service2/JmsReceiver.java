package vasilkov.service2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class JmsReceiver {

    private ObjectMapper mapper;


    @PostConstruct
    public void initialize() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        String email = "vasilkov.a.s@yandex.ru";
        message.setFrom(email);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        emailSender.send(message);
    }

    @JmsListener(destination = "${jms.queue}", containerFactory = "jmsContainerFactory")
    public void receiveMessage(String message) {
        try {
           MailModel mail = mapper.readValue(message, MailModel.class);
           System.out.println(mail);
           sendSimpleMessage(mail.getTo(),mail.getSubject(),mail.getText());
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}