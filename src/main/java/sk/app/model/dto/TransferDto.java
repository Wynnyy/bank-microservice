package sk.app.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransferDto {

    private Long id;
    private LocalDateTime date;
    private BigDecimal amount;
    private String debtorIban;
    private String creditorIban;
    private String message;
    private Long accountId;

    public TransferDto(Long id, LocalDateTime date, BigDecimal amount, String debtorIban, String creditorIban, String message, Long accountId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.debtorIban = debtorIban;
        this.creditorIban = creditorIban;
        this.message = message;
        this.accountId = accountId;
    }

    public TransferDto() {
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDebtorIban() {
        return debtorIban;
    }

    public void setDebtorIban(String debtorIban) {
        this.debtorIban = debtorIban;
    }

    public String getCreditorIban() {
        return creditorIban;
    }

    public void setCreditorIban(String creditorIban) {
        this.creditorIban = creditorIban;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


}
