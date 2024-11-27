package sk.app.model.entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;
import sk.app.model.enums.Sex;
import sk.app.model.enums.States;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "customer", nullable = false)
    private String customer;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "sur_name", nullable = false)
    private String surName;

    @Enumerated(EnumType.STRING)
    @Column(name = "nationality")
    private States nationality;


    @Column(name = "date_of_birth")
    private LocalDateTime dateOfBirth;

    @Column(name = "card_number", nullable = false, unique = true)
    private Integer cardNumber;

    @Column(name = "date_of_issue")
    private LocalDateTime dateOfIssue;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    @Column(name = "date_of_expiry")
    private LocalDateTime dateOfExpiry;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "FK_account__customer__id"))
    private List<Account> accounts;

    public Customer(Long id, String customer, String name, String surName, Sex sex, States nationality, LocalDateTime dateOfBirth, Integer cardNumber, LocalDateTime dateOfIssue, LocalDateTime dateOfExpiry, List<Account> accounts) {
        this.id = id;
        this.customer = customer;
        this.name = name;
        this.surName = surName;
        this.sex = sex;
        this.nationality = nationality;
        this.dateOfBirth = dateOfBirth;
        this.cardNumber = cardNumber;
        this.dateOfIssue = dateOfIssue;
        this.dateOfExpiry = dateOfExpiry;
        this.accounts = accounts;
    }

    public Customer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public States getNationality() {
        return nationality;
    }

    public void setNationality(States nationality) {
        this.nationality = nationality;
    }

    public LocalDateTime getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDateTime dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public LocalDateTime getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDateTime dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDateTime getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(LocalDateTime dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer1 = (Customer) o;

        if (!Objects.equals(id, customer1.id)) return false;
        if (!Objects.equals(customer, customer1.customer)) return false;
        if (!Objects.equals(name, customer1.name)) return false;
        if (!Objects.equals(surName, customer1.surName)) return false;
        if (nationality != customer1.nationality) return false;
        if (!Objects.equals(dateOfBirth, customer1.dateOfBirth))
            return false;
        if (!Objects.equals(cardNumber, customer1.cardNumber)) return false;
        if (!Objects.equals(dateOfIssue, customer1.dateOfIssue))
            return false;
        if (!Objects.equals(dateOfExpiry, customer1.dateOfExpiry))
            return false;
        if (sex != customer1.sex) return false;
        return Objects.equals(accounts, customer1.accounts);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (dateOfExpiry != null ? dateOfExpiry.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (accounts != null ? accounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", nationality=" + nationality +
                ", dateOfBirth=" + dateOfBirth +
                ", cardNumber=" + cardNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpiry=" + dateOfExpiry +
                ", sex=" + sex +
                ", accounts=" + accounts +
                '}';
    }
}
