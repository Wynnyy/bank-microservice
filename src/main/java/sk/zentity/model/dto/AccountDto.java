package sk.zentity.model.dto;

import sk.zentity.model.enums.Currency;

import java.math.BigDecimal;
import java.util.List;

public class AccountDto {

    private Long id;
    private String iban;
    private Currency currency;
    private BigDecimal Balance;
    private Long customerId;
    private List<TransferDto> transfers;

    public AccountDto(Long id, String iban, Currency currency, BigDecimal balance, Long customerId, List<TransferDto> transfers) {
        this.id = id;
        this.iban = iban;
        this.currency = currency;
        Balance = balance;
        this.customerId = customerId;
        this.transfers = transfers;
    }

    public AccountDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public BigDecimal getBalance() {
        return Balance;
    }

    public void setBalance(BigDecimal balance) {
        Balance = balance;
    }

    public List<TransferDto> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<TransferDto> transfers) {
        this.transfers = transfers;
    }
}
