package com.istad.data_analytics_restful_api.controller;

import com.istad.data_analytics_restful_api.model.User;
import com.istad.data_analytics_restful_api.model.UserAccount;
import com.istad.data_analytics_restful_api.model.response.AccountResponse;
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

@DeleteMapping("/removeUser/{id}")
    public Response<List<AccountResponse>> removeUser(@PathVariable("id") int id){
        try {
            int deleteUser = userService.removeUser(id);
            if (deleteUser>0){
                return Response.<List<AccountResponse>>deleteSuccess().setMessage("Successfully Delete account by Id !!!");
            }else {
                return Response.<List<AccountResponse>>notFound().setMessage("Undefined the Id to delete!!!!");
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
          return Response.<List<AccountResponse>>exception().setMessage("Failed to remove the user !!!!");
        }
}
@PutMapping("/updateUser/{id}")
    public Response<List<User>> updateUser(@RequestBody User user, @PathVariable("id")int id){
        try {
int updateUser = userService.updateUser(user, id);
if (updateUser>0){
    return Response.<List<User>>ok().setMessage("Successfully Update account by Id!!!");
}else {
    return Response.<List<User>>notFound().setMessage("Undefined the id to update!!!");
}
        }catch (Exception e){
            System.out.println(e.getMessage());
            return Response.<List<User>>notFound().setMessage("Failed to Update the user !!!!");
        }
}
}
