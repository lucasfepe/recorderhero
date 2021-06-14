package com.appsdeveloperblog.tutorials.legacyusersservice.service;

import com.appsdeveloperblog.tutorials.legacyusersservice.data.UserEntity;
import com.appsdeveloperblog.tutorials.legacyusersservice.response.UserRest;

import java.util.List;
import java.util.Optional;

public interface UsersService {
  UserRest getUserDetails(String userName, String password);
   UserRest getUserDetails(String userName);
   List<UserRest> getUsers();
}
