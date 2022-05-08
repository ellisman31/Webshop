package com.webshop.app.JPARepository;

import com.webshop.app.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderJPARepository extends JpaRepository<Order, Long>{
    
}
