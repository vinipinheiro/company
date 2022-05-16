package com.example.company.repository;

import com.example.company.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IEmployeeRepository extends PagingAndSortingRepository<Employee, Long>, JpaSpecificationExecutor<Employee> {

    @Query(value = "SELECT COUNT(e.email) FROM Employee e" +
            " WHERE e.email LIKE :email%")
    public int countEmailRegistered(String email);

    @Query(value = "SELECT e FROM Employee e" +
            " WHERE e.documentType = :documentType " +
            " AND e.documentNumber = :documentNumber")
    public Optional<Employee> findDocument(String documentType, String documentNumber);
}
