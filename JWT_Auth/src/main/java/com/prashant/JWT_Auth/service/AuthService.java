package com.prashant.JWT_Auth.service;

import com.prashant.JWT_Auth.dto.RegisterRequest;

public interface AuthService {

    void register(RegisterRequest request);
}
