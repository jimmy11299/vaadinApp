package com.example.service;

import com.example.entity.EmployeeEntity;
import com.example.repository.EmployeeRepository;
import com.example.vo.EmployeeVO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    // 查詢全部：將 Entity List 轉成 VO List
    public List<EmployeeVO> findAll() {
        return employeeRepository.findAll().stream()
                .map(entity -> new EmployeeVO(
                        entity.getId(), 
                        entity.getName(), 
                        entity.getDepartment()
                ))
                .collect(Collectors.toList());
    }

    // 儲存：將傳進來的 VO 轉成 Entity 後存入資料庫
    public void save(EmployeeVO vo) {
        EmployeeEntity entity = new EmployeeEntity(vo.getName(), vo.getDepartment());
        employeeRepository.save(entity);
    }
}