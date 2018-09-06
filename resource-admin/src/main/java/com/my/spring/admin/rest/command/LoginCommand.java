package com.my.spring.admin.rest.command;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

@Data
public class LoginCommand {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
