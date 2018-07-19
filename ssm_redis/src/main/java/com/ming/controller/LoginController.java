package com.ming.controller;

import com.ming.entity.UserEntity;
import com.ming.service.Userservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    @Autowired
    Userservice userservice;
    @RequestMapping("/login/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/login/volidate")
    public String volidate(UserEntity userEntity, HttpServletRequest request){
        String result="";
        try {
            int i=userservice.login(userEntity);
            result = i>0?"forward:/emplist":"forward:/login/login";
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
