package com.appsdeveloperblog.tutorials.legacyusersservice.service;

import org.apache.catalina.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.tutorials.legacyusersservice.data.UserEntity;
import com.appsdeveloperblog.tutorials.legacyusersservice.data.UsersRepository;
import com.appsdeveloperblog.tutorials.legacyusersservice.response.UserRest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private UsersRepository usersRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UsersServiceImpl(UsersRepository usersRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.usersRepository = usersRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
    
    @Override
    public UserRest getUserDetails(String userName) {
        UserRest returnValue = new UserRest();

        UserEntity userEntity = usersRepository.findByEmail(userName);
        if (userEntity == null) {
            userEntity = usersRepository.findByUserName(userName);
            if(userEntity == null){
                return null;
            } else{
                BeanUtils.copyProperties(userEntity, returnValue);
            }
        }else {

            BeanUtils.copyProperties(userEntity, returnValue);
        }
        return returnValue;
    }

    @Override
    public List<UserRest> getUsers() {
        List<UserRest> returnValue = new ArrayList<>();
//        UserRest returnUser = new UserRest();

        List<UserEntity> userEntity = usersRepository.findAll();
        for (UserEntity userEntityd: userEntity
             ) {

            returnValue.add(new UserRest(userEntityd.getFirstName(), userEntityd.getLastName(), userEntityd.getEmail(),
                    userEntityd.getEmail(), userEntityd.getUserId()));
        }

        return returnValue;
    }

    @Override
    public UserRest getUserDetails(String userName, String password) {
        UserRest returnValue = new UserRest();

        UserEntity userEntity = usersRepository.findByEmail(userName);

        if (userEntity == null) {
            return null;
        }else {

            if (bCryptPasswordEncoder.matches(password,
                    userEntity.getEncryptedPassword())) {
                System.out.println("password matches!!!");


                BeanUtils.copyProperties(userEntity, returnValue);

            }
        }

        return returnValue;
    }

}
