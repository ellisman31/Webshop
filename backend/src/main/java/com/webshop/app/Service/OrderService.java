package com.webshop.app.Service;

import com.webshop.app.JPARepository.OrderJPARepository;
import com.webshop.app.Model.Order;
import com.webshop.app.ServiceInterface.OrderServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface{

    private final OrderJPARepository orderJPA;
    
    @Autowired
    public OrderService(OrderJPARepository orderJPA) {
        this.orderJPA = orderJPA;
    }
    
    @Override
    public List<Order> getAllOrder() {
        return orderJPA.findAll();
    }

    @Override
    public Optional<Order> getOrderById(Long orderId) {
        return orderJPA.findById(orderId);
    }

    @Override
    public void updateOrder(Order order) {
        orderJPA.save(order);
    }

    @Override
    public void deleteOrderById(Long orderId) {
        orderJPA.deleteById(orderId);
    }

    @Override
    public void addOrder(Order order) {
        orderJPA.save(order);
    }
    
}
