package com.prashant.SecureAuth_Basic.Service;

import com.prashant.SecureAuth_Basic.dto.RegisterRequest;

public interface UserService {

    void register(RegisterRequest request);
}
