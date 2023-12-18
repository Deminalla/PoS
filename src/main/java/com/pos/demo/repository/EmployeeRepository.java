package com.pos.demo.repository;

import com.pos.demo.model.entity.EmployeeEntity;
import com.pos.demo.model.entity.UserEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Mapper
@Repository
public interface EmployeeRepository {
    @Select("SELECT * FROM employee WHERE employee_id = #{employeeId}")
    Optional<EmployeeEntity> findById(@Param("employeeId") UUID employeeId);

    @Insert("INSERT INTO employee (user_id, employee_id, employee_since, job_title)" +
            "VALUES (#{employeeEntity.userId}, #{employeeEntity.employeeId}, #{employeeEntity.employeeSince}, #{employeeEntity.jobTitle})")
    int createEmployee(@Param("employeeEntity") EmployeeEntity employeeEntity);

    @Select("SELECT * FROM employee WHERE user_id = #{userId}")
    Optional<EmployeeEntity> findByUserId(@Param("userId") UUID userId);

    @Delete("DELETE FROM employee WHERE employee_id = #{employeeId}")
    int deleteEmployee(@Param("employeeId") UUID employeeId);

    @Update("UPDATE employee SET employee_since = #{employeeEntity.employeeSince}, job_title = #{employeeEntity.jobTitle} " +
            "WHERE employee_id = #{employeeEntity.employeeId}")
    int updateEmployee(@Param("employeeEntity") EmployeeEntity employeeEntity);
}
