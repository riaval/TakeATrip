package com.takeatrip.service.implementation;

import com.takeatrip.domain.User;
import com.takeatrip.repository.UserRepository;
import com.takeatrip.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

}
