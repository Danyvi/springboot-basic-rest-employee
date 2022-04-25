package xyz.codeandlearn.springbootemployee.service.impl;

import org.springframework.stereotype.Service;

import xyz.codeandlearn.springbootemployee.exception.ResourceNotFoundException;
import xyz.codeandlearn.springbootemployee.model.Employee;
import xyz.codeandlearn.springbootemployee.repository.EmployeeRepository;
import xyz.codeandlearn.springbootemployee.service.EmployeeService;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    // Constructor based Dependency injection
    // since we use EmployeeRepository as a dependency, we can inject it into the constructor
    private EmployeeRepository employeeRepository;

    // constructor based dependency injection
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee); // save employee instance to the database
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll(); // return all employees from the database
    }

    @Override
    public Employee getEmployeeById(Long id) {
//        Optional<Employee>  employee = employeeRepository.findById(id); // get employee by id
//        // check if the optional object contains the Emplyee Object or not
//        if (employee.isPresent()) {
//            return employee.get(); // return the employee object
//        } else {
//            throw new ResourceNotFoundException("Employee", "Id",  id); // throw exception if employee not found
//        }

        // otherwise with Lambda expression
        return employeeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee", "Id", id));
    }

    @Override
    public Employee updateEmployee(Employee employee, Long id) {
        // check if that Employer id exists or not in the DB
        Employee existingEmployee = employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id)
        );

        // we update this existingEmployee with employee received from the request
        existingEmployee.setFirstName(employee.getFirstName());
        existingEmployee.setLastName(employee.getLastName());
        existingEmployee.setEmail(employee.getEmail());

        // save existingEmployee to the database
        employeeRepository.save(existingEmployee);

        return existingEmployee;

    }

    @Override
    public void deleteEmployee(Long id) {

        // check if that Employer id exists or not in the DB
        employeeRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Employee", "Id", id)
        );

        employeeRepository.deleteById(id);
    }

}


