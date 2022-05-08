package com.webshop.app.ServiceInterface;

import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Product;
import java.util.List;
import java.util.Optional;

public interface CustomerServiceInterface {
    
    List<Customer> getAllCustomer();
    
    Optional<Customer> getCustomerById(Long customerId);
            
    void updateCustomerById(Long customerId, Customer customer);
            
    void deleteCustomerById(Long customerId);
    
    void addCustomer(Customer customer);
    
    void addProductToCart(Long customerId, Product product);
    
    List<Product> getCustomerCart(Long customerId);
    
}
