package com.istad.data_analytics_restful_api.mapper;

import com.istad.data_analytics_restful_api.model.Account;
import com.istad.data_analytics_restful_api.model.response.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AutoAccountMapper {

    // account-> accountResponse
    List<AccountResponse> mapToAccountResponse(List<Account> accounts);

    // accountResponse -> account
    List<Account> mapToAccount(List<AccountResponse> responses);
}
