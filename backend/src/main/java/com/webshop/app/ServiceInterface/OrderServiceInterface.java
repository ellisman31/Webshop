package com.webshop.app.ServiceInterface;

import com.webshop.app.Model.Order;
import java.util.List;
import java.util.Optional;

public interface OrderServiceInterface {
    
    List<Order> getAllOrder();
    
    Optional<Order> getOrderById(Long orderId);
    
    void updateOrder(Order order);
        
    void deleteOrderById(Long orderId);
    
    void addOrder(Order order);
    
}
