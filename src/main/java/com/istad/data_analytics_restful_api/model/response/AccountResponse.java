package com.istad.data_analytics_restful_api.model.response;

import com.istad.data_analytics_restful_api.model.AccountType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {
    private int id;
    private String account_no;
    private String account_name;
    private String profile;
    private String phoneNumber;
    private int transfer_limit;
    private AccountType accountType;
}
