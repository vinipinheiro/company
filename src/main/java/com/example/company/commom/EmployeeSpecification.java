package com.example.company.commom;

import com.example.company.entity.Employee;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class EmployeeSpecification implements Specification<Employee> {

    private String randomColumnName;
    private String valueToSearchFor;

    public EmployeeSpecification(String randomColumnName, String valueToSearchFor) {
        this.randomColumnName = randomColumnName;
        this.valueToSearchFor = valueToSearchFor;
    }

    @Override
    public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        return builder.and(builder.equal(root.<String>get(this.randomColumnName), this.valueToSearchFor));
    }
}
