package com.webshop.app.Service;

import com.webshop.app.JPARepository.CustomerJPARepository;
import com.webshop.app.JPARepository.OrderJPARepository;
import com.webshop.app.JPARepository.ProductJPARepository;
import com.webshop.app.Model.Cart;
import com.webshop.app.Model.Customer;
import com.webshop.app.Model.Order;
import com.webshop.app.Model.Product;
import com.webshop.app.ServiceInterface.OrderServiceInterface;
import com.webshop.app.Status.OrderProcess;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    //NEEDS TO MODIFY(CHECK IF THERE IS ANY ORDER AT THE MOMENT)
    @Override
    public void addCustomerOrderById(Long customerId) {
        Optional<Customer> currentCustomer = customerJPA.findById(customerId);
        
        Order currentOrder = new Order();
        currentOrder.setCurrentCustomer(currentCustomer.get()); // Current customer
        currentOrder.setOrderProcessStatus(OrderProcess.INPROGRESS); // Order process status
        
        orderJPA.save(currentOrder);
    }
    
    //@Override
    //public void updateOrderById(Long orderId, Order order) {
    //    Optional<Order> currentOrder = orderJPA.findById(orderId);
        
    //    currentOrder.get().setCurrentCustomer(order.getCurrentCustomer()); //Order customer
    //    currentOrder.get().setOrderProcessStatus(order.getOrderProcessStatus()); //Order process
        
    //    if (order.getOrderProcessStatus().equals(OrderProcess.PICKUPATTHESTORE) || 
    //            order.getOrderProcessStatus().equals(OrderProcess.SHIPPING)) {
    //        saveCustomerOrderToOrderHistory(order.getCurrentCustomer(),order);
            
    //    } else if (order.getOrderProcessStatus().equals(OrderProcess.FINISHED)) {
    //        saveProductPurchaser(order.getCurrentCustomer());
    //    }
        
    //    orderJPA.save(currentOrder.get());
    //}
    
    //FOR UPDATE(TODO: EMPTY THE ORDER AFTER SAVING):
    
    //private void saveCustomerOrderToOrderHistory(Customer customer,Order order) {
        //customer.getOrderHistory().add(order);
    //}
    
    //private void saveProductPurchaser(Customer customer) {
       //Set<Cart> customerCart = customer.getCart();
        
       //customerCart.stream().forEach((product -> {
            //Product currentProduct = productJPA.findById(product.getProduct().getId()).get();
            //currentProduct.setProductQuantity(currentProduct.getProductQuantity() - product.getProductQuantity());
            //currentProduct.getPurchaser().add(customer);
        //}));
    //}

}
