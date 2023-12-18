package com.pos.demo.controller;

import com.pos.demo.model.dto.employee.EmployeeCreateDto;
import com.pos.demo.model.dto.employee.EmployeeInfoDto;
import com.pos.demo.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/{employeeId}")
    ResponseEntity<EmployeeInfoDto> getEmployeeById(@PathVariable UUID employeeId) {
        return ResponseEntity.ok(employeeService.getEmployeeByID(employeeId));
    }

    @PostMapping()
    ResponseEntity<EmployeeInfoDto> createEmployee(@RequestBody EmployeeCreateDto newEmployee) {
        return ResponseEntity.ok(employeeService.createEmployee(newEmployee));
    }

    @DeleteMapping("/{employeeId}")
    ResponseEntity<String> deleteEmployee(@PathVariable UUID employeeId) {
        employeeService.deleteUser(employeeId);
        return ResponseEntity.ok("Employee deleted successfully");
    }

    @PutMapping("/{employeeId}")
    ResponseEntity<EmployeeInfoDto> updateEmployee(@PathVariable UUID employeeId, @RequestBody EmployeeCreateDto updatedEmployee) {
        return ResponseEntity.ok(employeeService.updateEmployee(employeeId, updatedEmployee));
    }
}
