package com.ming.service.impl;

import com.ming.dao.UserEntityMapper;
import com.ming.entity.UserEntity;
import com.ming.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Userserviceimpl implements Userservice {

    @Autowired
    UserEntityMapper userEntityMapper;


    public int login(UserEntity userEntity) {
        return userEntityMapper.login(userEntity);
    }
}
