package com.webshop.app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
@JsonIgnoreProperties(value= {"id"})
@Table(name="customer", schema="public")
public class Customer {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "first_name")
    private String firstName;
    
    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "email_address")
    private String emailAddress;
    
    @Column(name = "password")
    private String password;
    
    @Column(name = "address")
    private String address;
    
    @ManyToMany
    @JoinTable(
            name = "purchase_product",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    @JsonBackReference(value="purchaseProduct")
    private List<Product> purchaseProduct;
    
    @JsonManagedReference(value = "orderHistory")
    @OneToMany(mappedBy = "customer")
    private Set<Order> orderHistory;
    
    @Column(name = "cart")
    private List<Product> cart;
    
}
