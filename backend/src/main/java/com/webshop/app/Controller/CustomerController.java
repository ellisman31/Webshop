package com.webshop.app.Controller;

import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Product;
import com.webshop.app.Service.CustomerService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CustomerController {
    
    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }
    
    @RequestMapping(value = "/getAllCustomer", method = RequestMethod.GET)
    public List<Customer> getAllCustomer(){
        return customerService.getAllCustomer();
    }
    
    @RequestMapping(value = "/getCustomer/{customerId}", method = RequestMethod.GET)
    public Optional<Customer> getCustomerById(@PathVariable Long customerId) {
        return customerService.getCustomerById(customerId);
    }
    
    @RequestMapping(value = "/addCustomer", method = RequestMethod.POST)
    public void addCustomer(@RequestBody Customer customer) {
        customerService.addCustomer(customer);
    }
    
    @RequestMapping(value = "/deleteCustomer/{customerId}", method = RequestMethod.DELETE)
    public void deleteCustomer(@PathVariable Long customerId) {
        customerService.deleteCustomerById(customerId);
    }
    
    @RequestMapping(value = "/updateCustomer/{customerId}", method = RequestMethod.PUT)
    public void updateCustomer(@PathVariable Long customerId, @RequestBody Customer customer) {
        customerService.updateCustomerById(customerId, customer);
    }
    
    @RequestMapping(value = "/addProductToCart/{customerId}/{productId}", method = RequestMethod.PUT)
    public void addProductToCart(@PathVariable("customerId") Long customerId, @PathVariable("productId") Long productId) {
        customerService.addProductToCart(customerId, productId);
    }
    
    @RequestMapping(value = "/getCustomerCart/{customerId}", method = RequestMethod.GET)
    public List<Product> getCustomerCart(@PathVariable Long customerId) {
        return customerService.getCustomerCart(customerId);
    }
    
}
