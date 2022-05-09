package com.webshop.app.Service;

import com.webshop.app.JPARepository.CustomerJPARepository;
import com.webshop.app.JPARepository.OrderJPARepository;
import com.webshop.app.JPARepository.ProductJPARepository;
import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Order;
import com.webshop.app.Model.Product;
import com.webshop.app.ServiceInterface.OrderServiceInterface;
import com.webshop.app.Status.OrderProcess;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements OrderServiceInterface{

    private final OrderJPARepository orderJPA;
    private final CustomerJPARepository customerJPA;
    private final ProductJPARepository productJPA;
    
    @Autowired
    public OrderService(OrderJPARepository orderJPA, CustomerJPARepository customerJPA, 
            ProductJPARepository productJPA) {
        this.orderJPA = orderJPA;
        this.customerJPA = customerJPA;
        this.productJPA = productJPA;
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
    public void deleteOrderById(Long orderId) {
        orderJPA.deleteById(orderId);
    }


    @Override
    public void updateOrderById(Long orderId, Order order) {
        Optional<Order> currentOrder = orderJPA.findById(orderId);
        
        currentOrder.get().setCustomer(order.getCustomer()); //Order customer
        currentOrder.get().setOrderProcessStatus(order.getOrderProcessStatus()); //Order process
        
        if (order.getOrderProcessStatus().equals(OrderProcess.PICKUPATTHESTORE) || 
                order.getOrderProcessStatus().equals(OrderProcess.SHIPPING)) {
            saveCustomerOrderToOrderHistory(order.getCustomer(),order);
            
        } else if (order.getOrderProcessStatus().equals(OrderProcess.FINISHED)) {
            saveProductPurchaser(order.getCustomer());
        }
        
        orderJPA.save(currentOrder.get());
    }

    @Override
    public void addCustomerOrderById(Long customerId) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        
        Order currentOrder = new Order();
        currentOrder.setCustomer(currentCustomer.get()); // Current customer
        currentOrder.setOrderProcessStatus(OrderProcess.INPROGRESS); // Order process status
        
        orderJPA.save(currentOrder);
    }
    
    private void saveCustomerOrderToOrderHistory(Customer customer,Order order) {
        customer.getOrderHistory().add(order);
    }
    
    private void saveProductPurchaser(Customer customer) {
        customer.getCart().stream().forEach((item -> {
            Product currentProduct = productJPA.findById(item.getId()).get();
            currentProduct.setProductQuantity(currentProduct.getProductQuantity() - 1);
            currentProduct.getPurchaser().add(customer);
        }));
    }

}
