package com.example.demo.service;

import com.example.demo.controller.param.EmployeeParam;
import com.example.demo.model.SupEmployees;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author demo
 * @since 2021-09-07
 */
public interface ISupEmployeesService extends IService<SupEmployees> {

    void insertEntUser(SupEmployees supEmployees);

    void updateEntUser(SupEmployees supEmployees);

    void deleteEntUser(String idNumber);

    SupEmployees getByIdnumber(String idNumber);
    List<SupEmployees>  getByIdnumbers(List<String> idNumber);
    List<SupEmployees> getPage(EmployeeParam eMployeeParam);
}
