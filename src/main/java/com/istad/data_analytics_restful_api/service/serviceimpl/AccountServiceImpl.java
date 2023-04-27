package com.istad.data_analytics_restful_api.service.serviceimpl;

import com.istad.data_analytics_restful_api.model.Account;
import com.istad.data_analytics_restful_api.repository.AccountRepository;
import com.istad.data_analytics_restful_api.service.AccountService;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    @Override
    public List<Account> allAccount() {
        return accountRepository.allAccount();
    }

    @Override
    public int createAccount(Account account) {
        return accountRepository.createAccount(account);
    }

    @Override
    public int updateAccount(Account account, int id) {
        return 0;
    }



    @Override
    public Account findAccountByID(int id) {
        return accountRepository.findAccountByID(id);
    }
}
