package com.example.ecommerce.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="address")
@Getter
@Setter
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private  Long id;

    @Column(name = "county")
    private String county;

    @Column(name = "town")
    private String town;

    @Column(name = "street")
    private String street;

    @Column(name = "postal_code")
    private String postalCode;

    @OneToOne
    @PrimaryKeyJoinColumn
    private Order order;
}
