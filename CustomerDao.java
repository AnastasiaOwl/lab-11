package com.example.lab11.dao;

import com.example.lab11.entities.Customer;
import jakarta.ejb.Stateless;
import jakarta.inject.Named;
import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
@Named
@Data
@Stateless
public class CustomerDao implements Serializable {
    @PersistenceContext
    private EntityManager em;
    public List<Customer> findAll(){
        return em.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }
    public List<Customer> searchByName(String searchName){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.searchByName", Customer.class);
        query.setParameter("searchName", "%" + searchName + "%"); // add wildcard characters to allow partial matching
        List<Customer> results = query.getResultList();
        return results;
    }
    public List<Customer> findByCardNumberBetween(int cardNumber, int cardNumberEnd){
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByCardNumberBetween", Customer.class);
        query.setParameter("cardNumber", cardNumber);
        query.setParameter("cardNumberEnd", cardNumberEnd);
        List<Customer> results = query.getResultList();
        return results;
    }
    public List<Customer> sortByCardBalance(){
        return em.createNamedQuery("Customer.findByCardBalanceLessThanOrderByCardBalanceDesc",Customer.class).getResultList();
    }
    public List<Customer> debtors(){
        return em.createNamedQuery("Customer.countByCardBalanceLessThan",Customer.class).getResultList();
    }
    public List<Customer> sortByCardBalanceAndCardNumber(){
        return em.createNamedQuery("Customer.findByOrderByCardBalanceAscCardNumberAsc",Customer.class).getResultList();
    }
    public List<Integer> getOnlyYear(){
        return em.createNamedQuery("Customer.findYearOfBirthDate").getResultList();
    }
    public  HashMap<Integer, Customer> findMaxCardBalanceByYear() {
        TypedQuery<Customer> query = em.createNamedQuery("Customer.findByBirthYearWithMaxCardBalance", Customer.class);
        List<Customer> customers = query.getResultList();
        HashMap<Integer, Customer> customerMap = new HashMap<>();
        for (Customer customer : customers) {
            customerMap.put(customer.getBirthDate().getYear(), customer);
        }
        return customerMap;
    }

}
