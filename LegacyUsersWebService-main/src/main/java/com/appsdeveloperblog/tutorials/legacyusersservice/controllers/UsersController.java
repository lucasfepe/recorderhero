package com.appsdeveloperblog.tutorials.legacyusersservice.controllers;


import com.appsdeveloperblog.tutorials.legacyusersservice.data.RoleRepository;
import com.appsdeveloperblog.tutorials.legacyusersservice.data.UserEntity;
import com.appsdeveloperblog.tutorials.legacyusersservice.data.UsersRepository;
import com.appsdeveloperblog.tutorials.legacyusersservice.util.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.appsdeveloperblog.tutorials.legacyusersservice.service.UsersService;
import com.appsdeveloperblog.tutorials.legacyusersservice.response.UserRest;
import com.appsdeveloperblog.tutorials.legacyusersservice.response.VerifyPasswordResponse;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UsersService usersService;

    @GetMapping("/{userName}")
    public UserRest getUser(@PathVariable("userName") String userName) {
        System.out.println("@@@@@@@@@@@userName restapiendpoint");
        UserRest userDetails = usersService.getUserDetails(userName);
        return userDetails;

    }
    @GetMapping
    public List<UserRest> getUsers() {
        System.out.println("@@@@@@@@@@@getUsers");
        List<UserRest> users = usersService.getUsers();
        return usersService.getUsers();

    }

    @PostMapping("/{userName}/verify-password")
    public VerifyPasswordResponse verifyUserPassword(@PathVariable("userName") String userName,
            @RequestBody String password) {

        VerifyPasswordResponse returnValue = new VerifyPasswordResponse(false);

        UserRest user = usersService.getUserDetails(userName, password);

        if (user != null) {
            returnValue.setResult(true);
        }

        return returnValue;
    }

    @PostMapping()
    public void addUser(@RequestBody UserEntity user) {


        user.setUserId(RandomStringGenerator.getAlphaNumericString(14));
        user.setEncryptedPassword(bCryptPasswordEncoder.encode(user.getEncryptedPassword()));



        usersRepository.save(user);


    }

}
