package com.webshop.app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@JsonIgnoreProperties(value= {"id"})
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
    @JsonManagedReference(value = "purchaseProduct")
    private Set<Customer> purchaser;
    
}
