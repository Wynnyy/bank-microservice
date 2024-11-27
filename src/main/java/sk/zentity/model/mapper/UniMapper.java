package sk.zentity.model.mapper;

import org.springframework.stereotype.Component;
import sk.zentity.model.dto.AccountDto;
import sk.zentity.model.dto.CustomerDto;
import sk.zentity.model.dto.TransferDto;
import sk.zentity.model.entities.Account;
import sk.zentity.model.entities.Customer;
import sk.zentity.model.entities.Transfer;

import java.util.ArrayList;
import java.util.List;

/**
 * UniMapper represent universal DTO mapper for {@link Account}, {@link Customer}, {@link Transfer}  *
 */

@Component
public class UniMapper implements AccountDtoMapper, CustomerDtoMapper, TransferDtoMapper {

    @Override
    public AccountDto toDto(Account account) {
        AccountDto accountDto = new AccountDto();

        accountDto.setId(account.getId());
        accountDto.setBalance(account.getBalance());
        accountDto.setIban(account.getIban());
        accountDto.setCurrency(account.getCurrency());
        accountDto.setCustomerId(account.getCustomerId());
        if(account.getTransfer() != null) accountDto.setTransfers(toDtoListTransfers(account.getTransfer()));
        else accountDto.setTransfers(new ArrayList<>());

        return accountDto;
    }

    @Override
    public List<AccountDto> toDtoListAccounts(List<Account> accountList) {
        return accountList.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public CustomerDto toDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();

        customerDto.setId(customer.getId());
        customerDto.setCustomer(customer.getCustomer());
        customerDto.setCardNumber(customer.getCardNumber());
        customerDto.setName(customer.getName());
        customerDto.setSurName(customer.getSurName());
        customerDto.setNationality(customer.getNationality());
        customerDto.setSex(customer.getSex());
        customerDto.setDateOfBirth(customer.getDateOfBirth());
        customerDto.setDateOfExpiry(customer.getDateOfExpiry());
        customerDto.setDateOfIssue(customer.getDateOfIssue());
        if(customer.getAccounts() != null) customerDto.setAccounts(toDtoListAccounts(customer.getAccounts()));
        else customerDto.setAccounts(new ArrayList<>());

        return customerDto;
    }

    @Override
    public List<CustomerDto> toDtoListCustomers(List<Customer> customerList) {
        return customerList.stream()
                .map(this::toDto)
                .toList();
    }

    @Override
    public TransferDto toDto(Transfer transfer) {
        TransferDto transferDto = new TransferDto();

        transferDto.setId(transfer.getId());
        transferDto.setAmount(transfer.getAmount());
        transferDto.setDate(transfer.getDate());
        transferDto.setCreditorIban(transfer.getCreditorIban());
        transferDto.setDebtorIban(transfer.getDebtorIban());
        transferDto.setMessage(transfer.getMessage());
        transferDto.setAccountId(transfer.getAccountId());

        return transferDto;
    }

    @Override
    public List<TransferDto> toDtoListTransfers(List<Transfer> transferList) {
        return transferList.stream()
                .map(this::toDto)
                .toList();
    }
}
