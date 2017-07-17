package com.lardi_trans.service.impl;


import com.fasterxml.jackson.databind.ObjectMapper;

import com.lardi_trans.domain.User;
import com.lardi_trans.dto.UserDTO;
import com.lardi_trans.exception.UserServiceException;
import com.lardi_trans.exception.VerifyUserException;
import com.lardi_trans.repository.UserRepository;
import com.lardi_trans.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;


import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;


/**
 * Created by nata on 19.03.2017.
 */
@Service
@PropertySource("classpath:reg.properties")
public class UserServiceImpl implements UserService {
    private ResourceBundleMessageSource bean;

    @Resource
   Environment environment;

   @Autowired
   private UserRepository userRepository;



    @Override
    public void registrationUser(UserDTO userDTO) {

        User user = new User();
       user.setName(userDTO.getName());
       user.setSurname(userDTO.getSurname());
       user.setPatronymic(userDTO.getPatronymic());
        user.setLogin(userDTO.getLogin());
        user.setPassword(userDTO.getPassword());


        try{

     userRepository.save(user);

        }catch(Exception e){
            throw new UserServiceException(environment.getProperty("reg.exist"));
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(environment.getProperty("db.urlJson")), user);

        } catch (IOException e) {
            throw new UserServiceException(environment.getProperty("db.save_failed"));
        }

    }

    @Override
    public User verify(String login, String password) {


        User user = userRepository.getUserByLogin(login);

        if (!( user!=null && user.getPassword().equals(password)))

        {
            throw new VerifyUserException(environment.getProperty("reg.incorrect"));

        }

        return user;

    }




}
