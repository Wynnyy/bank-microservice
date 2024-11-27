package sk.zentity.account;

import sk.zentity.model.dto.AccountDto;
import sk.zentity.model.entities.Account;

import java.math.BigDecimal;

public interface AccountService {

    AccountDto create(Account newAccount);
    AccountDto modify(Account account);
    void remove(Long id);
    BigDecimal viewSummary(Long id);
}
