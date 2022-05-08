package com.webshop.app.Service;

import com.webshop.app.JPARepository.CustomerJPARepository;
import com.webshop.app.Model.Customer;
import com.webshop.app.ServiceInterface.CustomerServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface{
    
    private final CustomerJPARepository customerJPA;

    @Autowired
    public CustomerService(CustomerJPARepository customerJPA) {
        this.customerJPA = customerJPA;
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customerJPA.findAll();
    }

    @Override
    public Optional<Customer> getCustomerById(Long customerId) {
        return customerJPA.findById(customerId);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerJPA.save(customer);
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerJPA.deleteById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerJPA.save(customer);
    }
    
}
