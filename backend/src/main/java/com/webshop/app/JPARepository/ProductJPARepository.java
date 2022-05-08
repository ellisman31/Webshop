package com.webshop.app.JPARepository;

import com.webshop.app.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJPARepository extends JpaRepository<Product, Long>{
    
}
