package com.webshop.app.Controller;

import com.webshop.app.Model.Product;
import com.webshop.app.Service.ProductService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ProductController {
    
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    
    @RequestMapping(value = "/getAllProduct", method = RequestMethod.GET)
    public List<Product> getAllProduct() {
        return productService.getAllProduct();
    }
    
    @RequestMapping(value = "/getProduct/{productId}", method = RequestMethod.GET)
    public Optional<Product> getProductById(@PathVariable Long productId) {
        return productService.getProductById(productId);
    }
    
    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public void addProduct(Product product) {
        productService.addProduct(product);
    }
    
    @RequestMapping(value = "/deleteProduct/{productId}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProductById(productId);
    }
    
    @RequestMapping(value = "/updateProduct/{productId}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable Long productId, Product product) {
        productService.updateProductById(productId, product);
    }
    
}
