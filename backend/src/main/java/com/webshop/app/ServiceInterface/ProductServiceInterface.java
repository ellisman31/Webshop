package com.webshop.app.ServiceInterface;

import com.webshop.app.Model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductServiceInterface {
    
    List<Product> getAllProduct();
    
    Optional<Product> getProductById(Long productId);
    
    void deleteProductById(Long productId);
        
    void updateProductById(Long productId, Product product);
    
    void addProduct(Product product);
    
}
