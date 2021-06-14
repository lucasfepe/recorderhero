package com.appsdeveloperblog.tutorials.legacyusersservice;

import javax.transaction.Transactional;

import com.appsdeveloperblog.tutorials.legacyusersservice.data.Role;
import com.appsdeveloperblog.tutorials.legacyusersservice.data.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.appsdeveloperblog.tutorials.legacyusersservice.data.UserEntity;
import com.appsdeveloperblog.tutorials.legacyusersservice.data.UsersRepository;

import java.util.ArrayList;
import java.util.List;

@Component
public class InitialSetup {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @EventListener
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent event) {


        UserEntity user = new UserEntity(
                1L,
                "ma",
                "qswe3mg84mfjtu",
                "test2",
                "Kargopolov",
                "ma",
                bCryptPasswordEncoder.encode("ma"),
                "",
                false);



      usersRepository.save(user);

    }
}
