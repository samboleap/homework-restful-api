package com.istad.data_analytics_restful_api.service.serviceimpl;

import com.istad.data_analytics_restful_api.model.User;
import com.istad.data_analytics_restful_api.model.UserAccount;
import com.istad.data_analytics_restful_api.repository.UserRepository;
import com.istad.data_analytics_restful_api.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public List<User> allUsers() {
        return userRepository.allUsers();
    }

    @Override
    public List<User> findUserByUsername() {
        return userRepository.findUserByUsername();
    }

    @Override
    public int createNewUser(User user) {
        return userRepository.createNewUser(user);
    }

    @Override
    public int updateUser(User user, int id) {
        return userRepository.updateUser(user, id);
    }

    @Override
    public User findUserByID(int id) {
        return userRepository.findUserByID(id);
    }

    @Override
    public int removeUser(int id) {
        return userRepository.removeUser(id);
    }

    @Override
    public List<UserAccount> getAllUserAccount() {
        return userRepository.getAllUserAccount();
    }
}
