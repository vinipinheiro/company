package com.example.company.repository;

import com.example.company.entity.Departament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDepartamentRepository extends JpaRepository<Departament, Long> {
}
