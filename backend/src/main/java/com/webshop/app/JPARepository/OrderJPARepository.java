package com.webshop.app.JPARepository;

import com.webshop.app.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderJPARepository extends JpaRepository<Order, Long>{
    
}
