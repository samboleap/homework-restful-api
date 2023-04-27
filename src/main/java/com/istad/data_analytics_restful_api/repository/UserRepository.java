package com.istad.data_analytics_restful_api.repository;

import com.istad.data_analytics_restful_api.model.Account;
import com.istad.data_analytics_restful_api.model.User;
import com.istad.data_analytics_restful_api.model.UserAccount;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
@Repository
public interface UserRepository {


    @Result(column = "id",property = "userId")
    @Select("select * from users_tb")
    List<User> allUsers();

    @Insert("insert into users_tb(username, gender, address)\n values(#{user.username},#{user.gender},#{user.address})"
    )
    int createNewUser(@Param("user") User user);

    @Result(column = "id" , property = "userId")
    @Select("select * from users_tb where id = #{id}")
    User findUserByID(int id);

    @Delete("delete users_tb where id =#{id}")
    @Result(column = "id", property = "userId")
    int removeUser(int id);

    List<User> findUserByUsername();

    @Update("")
    int updateUser(@PathVariable("users") User user,int id);

    @Results({
            @Result(column = "id", property = "userId"),
            @Result(column = "id", property = "accounts",many = @Many(select = "findAccountsByUserId")),

    })
    @Select("select * from users_tb")
    List<UserAccount> getAllUserAccount();

    @Results({
            @Result(property = "accountName", column = "account_name"),
            @Result(property = "accountNumber", column = "account_no"),
            @Result(property = "transferLimit",column = "transfer_limit"),
            @Result(property = "password",column = "passcode"),
            @Result(property = "accountType",column = "account_type", one = @One(select = "com.istad.data_analytics_restful_api.repository.AccountRepository.getAccountTypeByID"))
    })
    @Select("select * from user_account_tb inner join account_tb" +
            "    a on a.id = user_account_tb.account_id\n" +
            "where user_id = #{id}")
    List<Account> findAccountsByUserId(int id);
}
