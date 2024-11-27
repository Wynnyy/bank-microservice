package sk.zentity.model.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnDefault;
import sk.zentity.model.enums.Currency;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "iban", nullable = false, updatable = false, length = 24, unique = true)
    private String iban;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    private Currency currency;

    @ColumnDefault(value = "0")
    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "customer_id", nullable = false)
    private Long customerId;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", foreignKey = @ForeignKey(name = "FK_account__transfer__id"))
    private List<Transfer> transfer;

    public Account(Long id, String iban, Currency currency, BigDecimal balance, Long customerId, List<Transfer> transfer) {
        this.id = id;
        this.iban = iban;
        this.currency = currency;
        this.balance = balance;
        this.customerId = customerId;
        this.transfer = transfer;
    }

    public Account() {
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
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<Transfer> getTransfer() {
        return transfer;
    }

    public void setTransfer(List<Transfer> transfer) {
        this.transfer = transfer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        if (!Objects.equals(id, account.id)) return false;
        if (!Objects.equals(iban, account.iban)) return false;
        if (currency != account.currency) return false;
        if (!Objects.equals(balance, account.balance)) return false;
        if (!Objects.equals(customerId, account.customerId)) return false;
        return Objects.equals(transfer, account.transfer);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (iban != null ? iban.hashCode() : 0);
        result = 31 * result + (currency != null ? currency.hashCode() : 0);
        result = 31 * result + (balance != null ? balance.hashCode() : 0);
        result = 31 * result + (customerId != null ? customerId.hashCode() : 0);
        result = 31 * result + (transfer != null ? transfer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                ", currency=" + currency +
                ", balance=" + balance +
                ", customerId=" + customerId +
                ", transfer=" + transfer +
                '}';
    }
}
