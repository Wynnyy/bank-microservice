package sk.app.account;

import sk.app.model.dto.AccountDto;
import sk.app.model.entities.Account;

import java.math.BigDecimal;

public interface AccountService {

    AccountDto create(Account newAccount);
    AccountDto modify(Account account);
    void remove(Long id);
    BigDecimal viewSummary(Long id);
}
