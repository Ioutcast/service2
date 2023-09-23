package vasilkov.service2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Configuration
public class EmailService {

//    @Bean
//    public JavaMailSender getJavaMailSender() {
//        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setProtocol("smtp");
//        javaMailSender.setHost("smtp.yandex.ru");
//        javaMailSender.setPort(587);
//        javaMailSender.setUsername("vasilkov.a.s@yandex.ru");
//        javaMailSender.setPassword("ArigatoDerftion011");
//        return javaMailSender;
//    }

}
