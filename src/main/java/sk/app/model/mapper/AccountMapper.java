package sk.app.model.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sk.app.model.dto.AccountDto;
import sk.app.model.entities.Account;

import java.util.ArrayList;
import java.util.List;

@Component
public class AccountMapper implements ConvertService<AccountDto, Account> {

    @Autowired
    private TransferMapper transferMapper;

    @Override
    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setIban(account.getIban());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setCustomerId(account.getCustomerId());
        if(account.getTransfer() != null) accountDto.setTransfers(transferMapper.toDtoList(account.getTransfer()));
        else accountDto.setTransfers(new ArrayList<>());

        return accountDto;
    }

    @Override
    public List<AccountDto> toDtoList(List<Account> accountList) {
        return accountList.stream()
                .map(this::toDto)
                .toList();
    }
}
