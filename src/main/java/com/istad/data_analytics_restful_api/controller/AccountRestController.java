package com.istad.data_analytics_restful_api.controller;

import com.istad.data_analytics_restful_api.mapper.AutoAccountMapper;
import com.istad.data_analytics_restful_api.model.Account;
import com.istad.data_analytics_restful_api.model.UserAccount;
import com.istad.data_analytics_restful_api.model.response.AccountResponse;
import com.istad.data_analytics_restful_api.service.AccountService;
import com.istad.data_analytics_restful_api.utils.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountRestController {
    private final AccountService accountService;
    private final AutoAccountMapper autoAccountMapper;
    @Autowired
    AccountRestController(AccountService accountService, AutoAccountMapper autoAccountMapper){this.accountService = accountService;
        this.autoAccountMapper = autoAccountMapper;
    }

    @GetMapping("/allAccounts")
    //return the AccountResponse
    public Response<List<AccountResponse>> getAllAccount(){
        try {
            // modelMapper , mapstruct
//            List<AccountResponse> accountResponses= autoAccountMapper.mapToAccountResponse(allAccount);
//            HashMap<String , Object> response = new HashMap<>();
//            response.put("payload", accountResponses);
//            response.put("message", "Successfully retrieve all account info");
//            response.put("status", HttpStatus.OK);
//
//            return ResponseEntity.ok().body(response);


            List<Account> allAccount = accountService.allAccount();
            List<AccountResponse> accountResponses= autoAccountMapper.mapToAccountResponse(allAccount);
            return Response.<List<AccountResponse>>ok().setPayload(accountResponses).setMessage("Successfully retrieved all account information");
        }catch (Exception e){
            //            return ResponseEntity.ok().body("Failed to retrieved the accounts");
            System.out.println("Something wrong : " +e.getMessage());
            return Response.<List<AccountResponse>>exception().setMessage("Exception occurs !Failed to retrieved account information");

        }
    }

}