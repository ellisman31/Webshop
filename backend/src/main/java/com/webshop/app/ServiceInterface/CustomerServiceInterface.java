package com.webshop.app.ServiceInterface;

import com.webshop.app.Model.Customer;
import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    
    List<Customer> getAllCustomer();
    
    Optional<Customer> getCustomerById(Long customerId);
        
    void updateCustomer(Customer customer);
            
    void deleteCustomerById(Long customerId);
    
    void addCustomer(Customer customer);
    
}
