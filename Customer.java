package com.example.lab11.entities;


import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Data
@Named
@SessionScoped
@Table(name = "customer")
@NamedQueries({
        @NamedQuery(name = "Customer.findAll", query = "select c from Customer c"),
        @NamedQuery(name = "Customer.searchByName", query = "SELECT c FROM Customer c WHERE c.name LIKE :searchName"),
        @NamedQuery(name = "Customer.findByCardNumberBetween", query = "SELECT c FROM Customer c WHERE c.cardNumber BETWEEN :cardNumber AND :cardNumberEnd"),
        @NamedQuery(name = "Customer.findByCardBalanceLessThanOrderByCardBalanceDesc", query = "select c from Customer c where c.cardBalance <0 order by c.cardBalance DESC"),
        @NamedQuery(name = "Customer.countByCardBalanceLessThan", query = "select count(c) from Customer c where c.cardBalance <0"),
        @NamedQuery(name = "Customer.findByOrderByCardBalanceAscCardNumberAsc", query = "select c from Customer c order by c.cardBalance, c.cardNumber"),
        @NamedQuery(name = "Customer.findYearOfBirthDate", query = "SELECT distinct FUNCTION('YEAR', c.birthDate) FROM Customer c"),
        @NamedQuery(name = "Customer.findByBirthYearWithMaxCardBalance", query = "SELECT c FROM Customer c WHERE c.cardBalance IN (SELECT MAX(c2.cardBalance) FROM Customer c2 GROUP BY FUNCTION('YEAR', c2.birthDate))")
})


public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Size(max = 30)
    @Column(name = "surname", length = 30)
    private String surname;

    @Size(max = 35)
    @Column(name = "name", length = 35)
    private String name;

    @Size(max = 30)
    @Column(name = "patronymic", length = 30)
    private String patronymic;

    @Size(max = 35)
    @Column(name = "address", length = 35)
    private String address;
    @Size(max = 11)
    @Column(name = "cardNumber")
    private Integer cardNumber;

    @Column(name = "cardBalance")
    private Double cardBalance;

    @Column(name = "birthDate")
    private LocalDate birthDate;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Integer cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getCardBalance() {
        return cardBalance;
    }

    public void setCardBalance(Double cardBalance) {
        this.cardBalance = cardBalance;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}