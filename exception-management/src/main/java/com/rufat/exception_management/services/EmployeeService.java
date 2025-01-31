package com.rufat.exception_management.services;

import com.rufat.exception_management.dto.DtoDepartment;
import com.rufat.exception_management.dto.DtoEmployee;
import com.rufat.exception_management.entities.Department;
import com.rufat.exception_management.entities.Employee;
import com.rufat.exception_management.exceptions.BaseException;
import com.rufat.exception_management.exceptions.ErrorMessage;
import com.rufat.exception_management.exceptions.MessageType;
import com.rufat.exception_management.repostories.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public DtoEmployee findEmployeeById(Long id){
        DtoEmployee dtoEmployee = new DtoEmployee();
        DtoDepartment dtoDepartment = new DtoDepartment();

        Optional<Employee> optional = employeeRepository.findById(id);
        if(optional.isEmpty()){
            throw new BaseException(new ErrorMessage(MessageType.NO_RECORD_EXIST, id.toString()));
        }
        Employee employee = optional.get();
        Department department = employee.getDepartment();
        BeanUtils.copyProperties(employee, dtoEmployee);
        BeanUtils.copyProperties(department, dtoDepartment);
        dtoEmployee.setDtoDepartment(dtoDepartment);

        return dtoEmployee;
    }
}
