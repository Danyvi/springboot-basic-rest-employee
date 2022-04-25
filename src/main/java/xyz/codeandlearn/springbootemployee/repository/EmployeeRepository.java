package xyz.codeandlearn.springbootemployee.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import xyz.codeandlearn.springbootemployee.model.Employee;
// CRUD Methods for JPA Entity

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
