package vasilkov.service2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data @RequiredArgsConstructor @AllArgsConstructor
public class MailModel implements Serializable {

    private String to;
    private String subject;
    private String text;

}