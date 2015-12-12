package com.takeatrip.service;

import com.takeatrip.domain.User;

public interface UserService {

    User getUserByEmail(String email);

}
