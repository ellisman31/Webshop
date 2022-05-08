package com.webshop.app.JPARepository;

import com.webshop.app.Model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerJPARepository extends JpaRepository<Customer, Long>{
    
}
