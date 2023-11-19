package com.grubneac.CafeDemoCRM.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "person", schema = "Orders")
@Data
public class Person {
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
}
