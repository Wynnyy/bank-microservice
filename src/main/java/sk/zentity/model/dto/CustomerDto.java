package sk.zentity.model.dto;

import sk.zentity.model.enums.Sex;
import sk.zentity.model.enums.States;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


public class CustomerDto {

    private Long id;
    private String customer;
    private String name;
    private String surName;
    private Sex sex;
    private States nationality;
    private LocalDateTime dateOfBirth;
    private Integer cardNumber;
    private LocalDateTime dateOfIssue;
    private LocalDateTime dateOfExpiry;
    private List<AccountDto> accounts;

    public CustomerDto(Long id, String customer, String name, String surName, Sex sex, States nationality, LocalDateTime dateOfBirth, Integer cardNumber, LocalDateTime dateOfIssue, LocalDateTime dateOfExpiry, List<AccountDto> accounts) {
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

    public CustomerDto() {
    }

    public List<AccountDto> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<AccountDto> accounts) {
        this.accounts = accounts;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CustomerDto that = (CustomerDto) o;

        if (!Objects.equals(id, that.id)) return false;
        if (!Objects.equals(customer, that.customer)) return false;
        if (!Objects.equals(name, that.name)) return false;
        if (!Objects.equals(surName, that.surName)) return false;
        if (sex != that.sex) return false;
        if (nationality != that.nationality) return false;
        if (!Objects.equals(dateOfBirth, that.dateOfBirth)) return false;
        if (!Objects.equals(cardNumber, that.cardNumber)) return false;
        if (!Objects.equals(dateOfIssue, that.dateOfIssue)) return false;
        if (!Objects.equals(dateOfExpiry, that.dateOfExpiry)) return false;
        return Objects.equals(accounts, that.accounts);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surName != null ? surName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + (nationality != null ? nationality.hashCode() : 0);
        result = 31 * result + (dateOfBirth != null ? dateOfBirth.hashCode() : 0);
        result = 31 * result + (cardNumber != null ? cardNumber.hashCode() : 0);
        result = 31 * result + (dateOfIssue != null ? dateOfIssue.hashCode() : 0);
        result = 31 * result + (dateOfExpiry != null ? dateOfExpiry.hashCode() : 0);
        result = 31 * result + (accounts != null ? accounts.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CustomerDto{" +
                "id=" + id +
                ", customer='" + customer + '\'' +
                ", name='" + name + '\'' +
                ", surName='" + surName + '\'' +
                ", sex=" + sex +
                ", nationality=" + nationality +
                ", dateOfBirth=" + dateOfBirth +
                ", cardNumber=" + cardNumber +
                ", dateOfIssue=" + dateOfIssue +
                ", dateOfExpiry=" + dateOfExpiry +
                ", accounts=" + accounts +
                '}';
    }
}
