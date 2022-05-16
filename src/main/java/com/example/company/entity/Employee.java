package com.example.company.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
@Data
public class Employee {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="employee_id", nullable = false, unique = true, updatable = false)
    private Long employeeId;
    @Column(name="first_name", nullable = false, length = 20)
    private String firstName;
    @Column(name="middle_name", length = 50)
    private String middleName;
    @Column(name="first_last_name", nullable = false, length = 20)
    private String firstLastName;
    @Column(name="second_last_name", nullable = false, length = 20)
    private String secondLastName;
    @Column(name="email", nullable = false, length = 300, unique = true)
    private String email;
    @Column(name="job_country", nullable = false, length = 50)
    private String jobCountry;
    @Column(name="document_type", nullable = false, length = 50)
    private String documentType;
    @Column(name="document_number", nullable = false, length = 20)
    private String documentNumber;
    @Column(name="entryDate", nullable = false)
    private Date entryDate;
    @Column(name="status", nullable = false)
    private String status;
    @Column(name="insert_date", nullable = false)
    private Date insertDate;
    @ManyToOne
    @JoinColumn(name="departament_id")
    Departament departament;

    @PrePersist
    private void prePersist(){
        this.status = "Active";
        this.insertDate = new Date();
    }

}
