package com.webshop.app.Controller;

import com.webshop.app.Model.Order;
import com.webshop.app.Service.OrderService;
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
public class OrderController {
    
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }
    
    @RequestMapping(value = "/getAllOrder", method = RequestMethod.GET)
    public List<Order> getAllOrder() {
        return orderService.getAllOrder();
    }
    
    @RequestMapping(value = "/getOrder/{orderId}", method = RequestMethod.GET)
    public Optional<Order> getOrderById(@PathVariable Long orderId) {
        return orderService.getOrderById(orderId);
    }
    
    @RequestMapping(value = "/deleteOrder/{orderId}", method = RequestMethod.DELETE)
    public void deleteOrder(@PathVariable Long orderId) {
        orderService.deleteOrderById(orderId);
    }
    
    @RequestMapping(value = "/addCustomerOrder/{customerId}", method = RequestMethod.POST)
    public void addCustomerOrder(@PathVariable Long customerId) {
        orderService.addCustomerOrderById(customerId);
    }
    
    //@RequestMapping(value = "/updateOrder/{orderId}", method = RequestMethod.PUT)
    //public void updateOrder(@PathVariable Long orderId, @RequestBody Order order) {
    //    orderService.updateOrderById(orderId, order);
    //}
    
    
}
