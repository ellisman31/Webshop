package com.webshop.app.ServiceInterface;

import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface {
    
    List<Order> getAllOrder();
    
    Optional<Order> getOrderById(Long orderId);
        
    void updateOrderById(Long orderId, Order order);
        
    void deleteOrderById(Long orderId);
    
    void addCustomerOrderById(Long customerId);
                
}
