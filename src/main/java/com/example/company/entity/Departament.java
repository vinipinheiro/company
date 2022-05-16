package com.example.company.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "DEPARTAMENTS")
@Data
public class Departament {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="departament_id", nullable = false, unique = true, updatable = false)
    private Long departamentId;
    @Column(name="name", nullable = false)
    private String name;
}
