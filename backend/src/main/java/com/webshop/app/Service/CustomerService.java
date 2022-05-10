package com.webshop.app.Service;

import com.webshop.app.JPARepository.CustomerJPARepository;
import com.webshop.app.JPARepository.ProductJPARepository;
import com.webshop.app.Model.Cart;
import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Product;
import com.webshop.app.ServiceInterface.CustomerServiceInterface;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService implements CustomerServiceInterface{
    
    private final CustomerJPARepository customerJPA;
    private final ProductJPARepository productJPA;

    @Autowired
    public CustomerService(CustomerJPARepository customerJPA, ProductJPARepository productJPA) {
        this.customerJPA = customerJPA;
        this.productJPA = productJPA;
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
    public void addProductToCart(Long customerId, Long productId) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        Optional<Product> currentProduct = productJPA.findById(productId);
        
        Set<Cart> currentCart = currentCustomer.get().getCart();
        boolean isProductInCart = currentCart.stream().anyMatch(cart -> cart.getProduct().equals(currentProduct.get()));
        
        if (currentCart.isEmpty() || !isProductInCart) {
            newCartCreation(currentProduct.get(), currentCustomer.get()); 
        }
        else {
            currentCustomer.get().getCart().stream().forEach(cart -> {
                if (cart.getProduct().equals(currentProduct.get())) {
                    cart.setProductQuantity(cart.getProductQuantity() + 1);
                    currentCustomer.get().getCart().add(cart); 
                }
            });
        }
        
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
    public Set<Cart> getCustomerCart(Long customerId) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        return currentCustomer.get().getCart();
    }

    
    private void newCartCreation(Product currentProduct, Customer currentCustomer) {
        Cart newCart = new Cart();
        newCart.setProduct(currentProduct);
        newCart.setProductQuantity(1);
        newCart.setCustomerForCart(currentCustomer);
        currentCustomer.getCart().add(newCart);
    }

    
}
