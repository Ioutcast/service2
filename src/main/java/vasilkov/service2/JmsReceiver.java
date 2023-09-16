package vasilkov.service2;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.PostConstruct;
import javax.jms.JMSException;
import javax.jms.TextMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;


@Component
public class JmsReceiver {

    private ObjectMapper mapper;

    @PostConstruct
    public void initialize() {
        mapper = new ObjectMapper();
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    }

    @JmsListener(destination = "${jms.queue}", containerFactory = "jmsContainerFactory")
    public void receiveMessage(String message) {
        try {
            System.out.println(message);
//            vasilkov.service2.MailModel mail = mapper.readValue(message.getText(), vasilkov.service2.MailModel.class);
//            System.out.println(mail);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}