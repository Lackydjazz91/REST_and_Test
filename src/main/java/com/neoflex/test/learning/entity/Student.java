package com.neoflex.test.learning.entity;


import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "employees")
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String surname;
    @Column(name = "department")
    private String department;
    @Column(name = "salary")
    private Integer salary;

}
