package com.webshop.app.Service;

import com.webshop.app.JPARepository.ProductJPARepository;
import com.webshop.app.Model.Product;
import com.webshop.app.ServiceInterface.ProductServiceInterface;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements ProductServiceInterface{

    private final ProductJPARepository productJPA;
    
    @Autowired
    public ProductService(ProductJPARepository productJPA) {
        this.productJPA = productJPA;
    }
    
    
    @Override
    public List<Product> getAllProduct() {
        return productJPA.findAll();
    }

    @Override
    public Optional<Product> getProductById(Long productId) {
        return productJPA.findById(productId);
    }

    @Override
    public void deleteProductById(Long productId) {
        productJPA.deleteById(productId);
    }

    @Override
    public void updateProduct(Product product) {
        productJPA.save(product);
    }

    @Override
    public void addProduct(Product product) {
        productJPA.save(product);
    }

        
}
