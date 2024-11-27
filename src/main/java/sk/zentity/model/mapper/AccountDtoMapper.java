package sk.zentity.model.mapper;

import sk.zentity.model.dto.AccountDto;
import sk.zentity.model.entities.Account;

import java.util.List;

public interface AccountDtoMapper {

    AccountDto toDto(Account account);
    List<AccountDto> toDtoListAccounts(List<Account> accountList);

}
