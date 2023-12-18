package com.pos.demo.service;


import com.pos.demo.mapper.EmployeeMapper;
import com.pos.demo.mapper.UserMapper;
import com.pos.demo.model.dto.employee.EmployeeCreateDto;
import com.pos.demo.model.dto.employee.EmployeeDto;
import com.pos.demo.model.dto.employee.EmployeeInfoDto;
import com.pos.demo.model.dto.user.CreateUserDto;
import com.pos.demo.model.dto.user.UserDto;
import com.pos.demo.model.dto.user.UserInfoDto;
import com.pos.demo.model.entity.EmployeeEntity;
import com.pos.demo.model.entity.UserEntity;
import com.pos.demo.repository.EmployeeRepository;
import com.pos.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
@Log4j2
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserService userService;

    @Transactional
    public EmployeeInfoDto getEmployeeByID(UUID employeeId) {
        log.info("Searching for employee with ID {}", employeeId);
        Optional<EmployeeEntity> employeeEntity = employeeRepository.findById(employeeId);

        if (employeeEntity.isEmpty()) {
            log.warn("User was not found");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee was not found");
        }

        EmployeeInfoDto employeeInfoDto = employeeMapper.entityToDto(employeeEntity.get());

        return employeeInfoDto;
    }

    @Transactional
    public EmployeeInfoDto createEmployee(EmployeeCreateDto newEmployee)
    {
        log.info("Creating new employee");

        EmployeeDto employee = employeeMapper.createToDto(newEmployee);
        UserEntity newUserEntity = userMapper.dtoToEntity(userMapper.createToDto(newEmployee.getUser()));

        //warn if existing user is employee, create user if it doesn't exist
        Optional<UserEntity> existingUser = userRepository.findByEmail(newUserEntity.getEmail());
        UserDto user;
        if(existingUser.isPresent()) {
            if(employeeRepository.findByUserId(existingUser.get().getUserId()).isPresent()) {
                log.warn("Provided user is already an employee");
                throw new ResponseStatusException(HttpStatus.CONFLICT,
                        "Provided user is already an employee");
            }
            else {
                user = userMapper.entityToDto(existingUser.get());
            }
        }
        else {
            userService.createUser(newEmployee.getUser());
            user = userMapper.entityToDto(userRepository.findByEmail(newUserEntity.getEmail()).get());
        }

        //create new employee
        employee.setUser(user);
        EmployeeEntity employeeEntity = employeeMapper.dtoToEntity(employee);
        UUID randomEmployeeId = UUID.randomUUID();
        employeeEntity.setEmployeeId(randomEmployeeId);
        if(employeeRepository.createEmployee(employeeEntity) == 0) {
            log.warn("Error occurred while adding new employee to the database");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while adding new employee to the database");
        }

        //fetch newly created employee
        Optional<EmployeeEntity> foundEmployee = employeeRepository.findById(randomEmployeeId);
        if(!foundEmployee.isPresent()) {
            log.warn("Error occured while trying to find the newly created employee");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occured while trying to find the newly created employee");
        }

        return employeeMapper.entityToDto(foundEmployee.get());
    }

    @Transactional
    public void deleteUser(UUID employeeId)
    {
        log.info("Deleting employee with ID {}", employeeId);

        //Check if user exists
        if(employeeRepository.findById(employeeId).isPresent()) {
            if(employeeRepository.deleteEmployee(employeeId) == 0) {
                log.info("Error occurred while trying to delete employee from the database");
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                        "Error occurred while trying to delete employee from the database");
            }
            else {
                return;
            }
        }
        else {
            log.info("Employee not found.");
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found.");
        }

    }

    @Transactional
    public EmployeeInfoDto updateEmployee(UUID employeeId, EmployeeCreateDto updatedEmployee)
    {
        log.info("Updating Employee with ID {}", employeeId);

        //Create employeeDto and add the id
        EmployeeDto employeeDto = employeeMapper.createToDto(updatedEmployee);
        employeeDto.setEmployeeId(employeeId);

        //Update user info
        UserInfoDto updatedUser = userService.updateUser(userRepository.findByEmail(updatedEmployee.getUser().getEmail()).get().getUserId(),
                updatedEmployee.getUser());
        UserDto user = userMapper.createToDto(updatedEmployee.getUser());
        user.setUserId(updatedUser.getUserId());
        employeeDto.setUser(user);

        //Update employee info
        EmployeeEntity employeeEntity = employeeMapper.dtoToEntity(employeeDto);
        if(employeeRepository.updateEmployee(employeeEntity) == 0) {
            log.warn("Employee update unsuccessful");
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error occurred while updating employee in the database.");
        }

        //Get newly updated employee
        Optional<EmployeeEntity> foundEmployeeEntity = employeeRepository.findById(employeeId);
        EmployeeInfoDto foundEmployeeDto = employeeMapper.entityToDto(foundEmployeeEntity.get());

        return foundEmployeeDto;
    }
}
