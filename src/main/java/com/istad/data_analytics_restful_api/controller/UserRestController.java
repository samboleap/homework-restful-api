package com.istad.data_analytics_restful_api.controller;

import com.istad.data_analytics_restful_api.model.User;
import com.istad.data_analytics_restful_api.model.UserAccount;
import com.istad.data_analytics_restful_api.service.UserService;
import com.istad.data_analytics_restful_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserRestController {
    private final UserService userService;
    @Autowired
    UserRestController(UserService userService){
        this.userService = userService;
    }

    private boolean isUserValid(int id) {
        User user = userService.findUserByID(id);
        return user != null;
    }
    private Response<User> userNotFound(int id){
        return Response.<User>notFound().setMessage("Id cannot defined : "+id).setSuccess(false).setStatus(Response.Status.NOT_FOUND);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUser(){
        return userService.allUsers();
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable int id){
        return userService.findUserByID(id);
    }

    @PostMapping("/newUser")
    public String createUser(@RequestBody User user){
        System.out.println("user is :" +user);

        try {
            int affectRow =  userService.createNewUser(user);
            System.out.println("Affect row: " +affectRow);
            if (affectRow>0) return "create user successfully";
            else return "Can not create a new User";
        }catch (Exception e){
            return e.getMessage();
        }
    }

    @GetMapping("/userAccounts")
    public Response<List<UserAccount>> getAllUserAccount(){
        try {
            List<UserAccount> userAccounts = userService.getAllUserAccount();
            System.out.println(userAccounts);
            return Response.<List<UserAccount>>ok().setPayload(userAccounts).setMessage("Successfully Retrieved all User Account information");
        }catch (Exception e){
            return Response.<List<UserAccount>>ok().setMessage("Exception occurs! Failed to retrieved all user accounts!").setSuccess(false);
        }
    }

    @PutMapping("/update/{id}")
    public Response<User> updateUser(@PathVariable int id,@RequestBody User user){
        try {
            if (isUserValid(id)){
                user.setUserId(id);
                userService.updateUser(user,id);
                return Response.<User>updateSuccess().setPayload(user).setMessage("Successfully updated by Id " +id);
            }else {
                return userNotFound(id);
            }
        }catch (Exception e){
            return Response.<User>exception().setSuccess(false).setMessage("Cannot defined Id to Update!!" +id);
        }
    }

    @DeleteMapping("/delete/{id}")
    public Response<User> deleteUser(@PathVariable int id){
        try {
            if(isUserValid(id)){
                userService.removeUser(id);
                return Response.<User>deleteSuccess().setMessage("Successfully deleted by Id "+id);
            }
            else {
                return userNotFound(id);
            }
        } catch (Exception exception){
            return Response.<User>exception().setMessage("Cannot  defined Id to delete!!! "+ id).setSuccess(false);
        }
    }
}
