package com.webshop.app.Service;

import com.webshop.app.JPARepository.CustomerJPARepository;
import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Product;
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
    public void deleteCustomerById(Long customerId) {
        customerJPA.deleteById(customerId);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerJPA.save(customer);
    }

    @Override
    public void addProductToCart(Long customerId, Product product) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        currentCustomer.get().getCart().add(product);
        
        customerJPA.save(currentCustomer.get());
    }

    @Override
    public void updateCustomerById(Long customerId, Customer customer) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        
        currentCustomer.get().setAddress(customer.getAddress()); //Address
        currentCustomer.get().setCart(customer.getCart()); // Cart
        currentCustomer.get().setEmailAddress(customer.getEmailAddress()); //Email address
        currentCustomer.get().setFirstName(customer.getFirstName()); // First name
        currentCustomer.get().setLastName(customer.getLastName()); // Last name
        currentCustomer.get().setOrderHistory(customer.getOrderHistory()); // Order history
        currentCustomer.get().setPassword(customer.getPassword()); // Password
        currentCustomer.get().setPurchaseProduct(customer.getPurchaseProduct()); // Purchased product(but this is invisible)
        
        customerJPA.save(currentCustomer.get());
    }

    @Override
    public List<Product> getCustomerCart(Long customerId) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        return currentCustomer.get().getCart();
    }
    
}
