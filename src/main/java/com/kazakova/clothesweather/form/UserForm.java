package com.kazakova.clothesweather.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class UserForm {
    private String name;
    private String login;
    private String password;
}
