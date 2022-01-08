package com.example.ecommerce.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "town")
@Data
public class Town {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "county_id")
    private County county;
}
