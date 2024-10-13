package com.sportyshoes.SportyShoesApp.service.impl;

import com.sportyshoes.SportyShoesApp.dto.response.SignUpResponse;
import com.sportyshoes.SportyShoesApp.entity.User;
import com.sportyshoes.SportyShoesApp.exception.UserNotFoundException;
import com.sportyshoes.SportyShoesApp.repository.UserRepository;
import com.sportyshoes.SportyShoesApp.service.LoginService;
import com.sportyshoes.SportyShoesApp.type.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Override
    public User signIn(User u) {
        User user = userRepository.findByEmailAndPassword(u.getEmail(), u.getPassword())
                .orElseThrow(() -> new UserNotFoundException("User not found."));
        user.setLastLogin(LocalDateTime.now());
        userRepository.save(user);
        return user;
    }

    @Override
    public SignUpResponse signUp(User u) {
        SignUpResponse signUpResponse = new SignUpResponse();
        Optional<User> user = userRepository.findById(u.getEmail());
        if (user.isPresent()) {
            signUpResponse.setSuccess(false);
            signUpResponse.setMessage("User with this email is already is present. Please try again with a different email");
        } else {
            try {
                u.setUserType(UserType.CUSTOMER.value());
                userRepository.save(u);
                signUpResponse.setSuccess(true);
                signUpResponse.setMessage("Successfully signed up! Please log in with your credentials.");
            } catch (Exception e) {
                signUpResponse.setSuccess(false);
                signUpResponse.setMessage(e.getMessage());
            }
        }
        return signUpResponse;
    }

    @Override
    public User findLastLoginCustomer() {
        return userRepository.findTopByUserTypeOrderByLastLoginDesc(UserType.CUSTOMER.value()).get();
    }
}
