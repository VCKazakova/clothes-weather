package com.kazakova.clothesweather.service;

import com.kazakova.clothesweather.form.UserForm;
import com.kazakova.clothesweather.model.Role;
import com.kazakova.clothesweather.model.State;
import com.kazakova.clothesweather.model.User;
import com.kazakova.clothesweather.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SignUpServiceImpl implements SignUpService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void signUp(UserForm userForm) {
        String hashPassword = passwordEncoder.encode(userForm.getPassword());

        User user = User.builder()
                .name(userForm.getName())
                .hashPassword(hashPassword)
                .login(userForm.getLogin())
                .role(Role.USER)
                .state(State.ACTIVE)
                .build();

        userRepository.save(user);
    }

}
