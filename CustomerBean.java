package com.example.lab11.beans;

import com.example.lab11.dao.CustomerDao;
import com.example.lab11.entities.Customer;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Named
@SessionScoped
@Data
public class CustomerBean implements Serializable {
    private String searchName;
    private int cardNumber;
    private int cardNumberEnd;
    private List<Integer> list = new ArrayList<>();
    private HashMap<LocalDate,Customer> map = new HashMap<>();
    @EJB
    private CustomerDao customerDao;
    public List<Customer> getCustomers(){
        return customerDao.findAll();
    }
    public List<Customer> searchByName(){
        return  customerDao.searchByName(searchName);
    }
    public List<Customer> findByCardNumberBetween(){
        return  customerDao.findByCardNumberBetween(cardNumber,cardNumberEnd);
    }
    public List<Customer> sortByCardBalance(){
        return customerDao.sortByCardBalance();
    }
    public List<Customer> debtors(){
        return customerDao.debtors();
    }
    public List<Customer> sortByCardBalanceAndCardNumber(){
        return customerDao.sortByCardBalanceAndCardNumber();
    }
    public List<Integer> getOnlyYear(){
        list = customerDao.getOnlyYear();
        return list;
    }
    public HashMap<Integer, Customer>  findMaxCardBalanceByYear(){
        return customerDao.findMaxCardBalanceByYear();
    }
}
