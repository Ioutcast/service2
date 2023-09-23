package vasilkov.service2;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
@Slf4j
public class JmsReceiver {

    @Autowired
    private RuntimeService runtimeService;

    @JmsListener(destination = "${jms.queue}", containerFactory = "jmsContainerFactory")
    public void receiveMessage(String message) {
        try {
//           MailModel mail = mapper.readValue(message, MailModel.class);
           System.out.println(message);
//           sendSimpleMessage(mail.getTo(),mail.getSubject(),mail.getText());
           Map<String, Object> variables = new HashMap<String, Object>();
           variables.put("message", message);
           runtimeService.startProcessInstanceByKey("Process_1", variables);
        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }
}