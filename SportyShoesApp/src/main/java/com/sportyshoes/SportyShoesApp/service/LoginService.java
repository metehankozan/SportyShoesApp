package com.sportyshoes.SportyShoesApp.service;

import com.sportyshoes.SportyShoesApp.dto.response.SignUpResponse;
import com.sportyshoes.SportyShoesApp.entity.User;

public interface LoginService {
    User signIn(User u);
    SignUpResponse signUp(User u);
    User findLastLoginCustomer();
}
