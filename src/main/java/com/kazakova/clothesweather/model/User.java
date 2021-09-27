package com.kazakova.clothesweather.model;

import lombok.*;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "user_wardrobe")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String login;
    private String hashPassword;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    @Enumerated(value = EnumType.STRING)
    private State state;

//    public static User from(UserForm form) {
//        return User.builder()
//                .firstName(form.getFirstName())
//                .lastName(form.getLastName())
//                .build();
//    }

}
