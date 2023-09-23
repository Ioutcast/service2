package vasilkov.service2;

import jakarta.inject.Named;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.Objects;

@Named("checkMsg")
@RequiredArgsConstructor
@Slf4j
public class CheckMsg implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        try{
            String message = delegateExecution.getVariable("message").toString();
        } catch (Exception e){
            log.info("CheckMsg");
        }
//       String message = delegateExecution.getVariable("message").toString();
//       if (Objects.equals(message, ""))
//                        throw new Exception();

    }
}
