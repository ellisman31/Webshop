package com.webshop.app.ServiceInterface;

import com.webshop.app.Model.Cart;
import com.webshop.app.Model.Customer;
import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface CustomerServiceInterface {
    
    List<Customer> getAllCustomer();
    
    Optional<Customer> getCustomerById(Long customerId);
            
    //void updateCustomerById(Long customerId, Customer customer);
            
    void deleteCustomerById(Long customerId);
    
    void addCustomer(Customer customer);
    
    void addProductToCart(Long customerId, Long productId);
    
    Set<Cart> getCustomerCart(Long customerId);
    
}
