package ru.antongrutsin.ecommerce.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SystemUser {
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
}
