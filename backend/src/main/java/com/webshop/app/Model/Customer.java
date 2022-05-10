package com.webshop.app.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@JsonIgnoreProperties(value= {"id", "purchaseProduct"})
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
    private List<Product> purchaseProduct;
    
    @OneToMany(mappedBy = "currentCustomer", fetch=FetchType.LAZY)
    @JsonBackReference(value = "customerForOrder")
    private Set<Order> orderHistory;
    
    @OneToMany(mappedBy = "customerForCart", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "customerForCart")
    private Set<Cart> cart;
    
}
