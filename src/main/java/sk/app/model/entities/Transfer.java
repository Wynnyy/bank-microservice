package sk.app.model.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transfer")
public class Transfer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "debtor_iban", nullable = false, length = 24)
    private String debtorIban;

    @Column(name = "creditor_iban", nullable = false, length = 24)
    private String creditorIban;

    @Column(name = "message")
    private String message;

    @Column(name = "account_id")
    private Long accountId;

    public Transfer(Long id, LocalDateTime date, BigDecimal amount, String debtorIban, String creditorIban, String message, Long accountId) {
        this.id = id;
        this.date = date;
        this.amount = amount;
        this.debtorIban = debtorIban;
        this.creditorIban = creditorIban;
        this.message = message;
        this.accountId = accountId;
    }

    public Transfer() {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Transfer transfer = (Transfer) o;

        if (!Objects.equals(id, transfer.id)) return false;
        if (!Objects.equals(date, transfer.date)) return false;
        if (!Objects.equals(amount, transfer.amount)) return false;
        if (!Objects.equals(debtorIban, transfer.debtorIban)) return false;
        if (!Objects.equals(creditorIban, transfer.creditorIban))
            return false;
        if (!Objects.equals(message, transfer.message)) return false;
        return Objects.equals(accountId, transfer.accountId);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (debtorIban != null ? debtorIban.hashCode() : 0);
        result = 31 * result + (creditorIban != null ? creditorIban.hashCode() : 0);
        result = 31 * result + (message != null ? message.hashCode() : 0);
        result = 31 * result + (accountId != null ? accountId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Transfer{" +
                "id=" + id +
                ", date=" + date +
                ", amount=" + amount +
                ", debtorIban='" + debtorIban + '\'' +
                ", creditorIban='" + creditorIban + '\'' +
                ", message='" + message + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
