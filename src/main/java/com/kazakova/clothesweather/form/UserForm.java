package com.kazakova.clothesweather.form;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
// todo убрать лишние аннотации
@Getter
@Setter
public class UserForm {
    private String name;
    private String login;
    private String password;
}
