package vasilkov.service2;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import vasilkov.service2.config.MailModel;

import javax.annotation.PostConstruct;

@Named("sendMsg")
@RequiredArgsConstructor
@Slf4j
public class SendMsg implements JavaDelegate {
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
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try{
            String message = delegateExecution.getVariable("message").toString();
            MailModel mail = mapper.readValue(message, MailModel.class);
            sendSimpleMessage(mail.getTo(),mail.getSubject(),mail.getText());
        } catch (Exception e){
            log.info("SendMsg");
        }
    }
}
