package com.webshop.app.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(value= {"id", "customerCart"})
@Table(name="product", schema="public")
public class Product {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "name")
    private String productName;
    
    @Column(name = "quantity")
    private int productQuantity;
    
    @Column(name = "isAvailable")
    private boolean isAvailable;
    
    @Column(name = "isInStock")
    private boolean isInStock;
    
    @ManyToMany(mappedBy = "purchaseProduct")
    private List<Customer> purchaser;
    
    @OneToMany(mappedBy = "product", fetch=FetchType.LAZY)
    private Set<Cart> customerCart;
    
}
