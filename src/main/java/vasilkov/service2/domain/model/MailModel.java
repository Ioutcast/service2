package vasilkov.service2.domain.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

@Data @RequiredArgsConstructor @AllArgsConstructor
public class MailModel implements Serializable {
    @NotBlank
    private String to;
    @NotBlank
    private String subject;
    @NotNull
    private String text;

}