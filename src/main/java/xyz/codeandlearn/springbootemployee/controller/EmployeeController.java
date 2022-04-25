package xyz.codeandlearn.springbootemployee.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xyz.codeandlearn.springbootemployee.model.Employee;
import xyz.codeandlearn.springbootemployee.service.EmployeeService;

import java.util.List;

// @RestController combines @Controller and @ResponseBody
@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    // inject the dependency EmployeeService (constructor method)
    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    // build create employee REST API
    // we use ResponseEntity type because
    // can provide complete response details in the return type
    // generate the response for this http request

    // @RequestBody annotation allows us to retrieve the request body
    // and automatically convert it to Java object
    @PostMapping()
    public ResponseEntity<Employee> saveEmployee(@RequestBody  Employee employee) {
        return new ResponseEntity<Employee>(employeeService.saveEmployee(employee), HttpStatus.CREATED);
    }

    // Get all employees REST API
    @GetMapping()
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by id REST API (useing the path variable id, dynamic path variable)
    // http://localhost:8080/api/v1/employees/1
    @GetMapping("{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long emplyeeId) {
        return new ResponseEntity<Employee>(employeeService.getEmployeeById(emplyeeId), HttpStatus.OK);
    }

//      Implementation without ResponseEntity
//    @GetMapping("/{id}")
//    public Employee getEmployeeById(@PathVariable("id") Long id) {
//        return employeeService.getEmployeeById(id);
//    }

    // Update employee REST API
    // http://localhost:8080/api/v1/employees/1
    @PutMapping("{id}")
        public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(employee, id), HttpStatus.OK);
    }


//    @PutMapping("/{id}")
//    public ResponseEntity<Employee> updateEmployee(@PathVariable("id") Long id, @RequestBody Employee employee) {
//        return new ResponseEntity<Employee>(employeeService.updateEmployee(id, employee), HttpStatus.OK);
//    }

    // Delete employee REST API
    // http://localhost:8080/api/v1/employees/1
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long Id){
        // delete employee from DB
        employeeService.deleteEmployee(Id);
        // return success message
        return new ResponseEntity<String>("Employee deleted succesfully", HttpStatus.OK );
    }
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) {
//        employeeService.deleteEmployee(id);
//        return new ResponseEntity<String>("Employee with id " + id + " deleted successfully", HttpStatus.OK);
//    }
}
