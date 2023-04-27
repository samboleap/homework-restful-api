package com.istad.data_analytics_restful_api.service;

import com.istad.data_analytics_restful_api.model.User;
import com.istad.data_analytics_restful_api.model.UserAccount;

import java.util.List;

public interface UserService {
    List<User> allUsers();
    List<User> findUserByUsername();
    int createNewUser(User user);
    int updateUser(User user, int id);
    User findUserByID(int id);
    int removeUser(int id);
    List<UserAccount> getAllUserAccount();
}
