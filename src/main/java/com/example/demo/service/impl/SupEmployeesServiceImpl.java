package com.example.demo.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.controller.param.EmployeeParam;
import com.example.demo.common.exception.BusinessException;
import com.example.demo.common.exception.EmBusinessError;
import com.example.demo.model.SupEmployees;
import com.example.demo.dao.SupEmployeesMapper;
import com.example.demo.common.doPage.DoPage;
import com.example.demo.service.ISupEmployeesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author demo
 * @since 2021-09-07
 */
@Service
public class SupEmployeesServiceImpl extends ServiceImpl<SupEmployeesMapper, SupEmployees> implements ISupEmployeesService {

    @Autowired
    private SupEmployeesMapper supEmployeesMapper;

    @Override
    public void insertEntUser(SupEmployees supEmployees) {
        if(supEmployees.getIdNumber()==null||supEmployees.getIdNumber().equals("")){
            throw new BusinessException(EmBusinessError.USER_NO, "身份证号不可为空");
        }
        QueryWrapper<SupEmployees> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_number",supEmployees.getIdNumber());
        if(supEmployeesMapper.selectCount(queryWrapper)>0){
            throw new BusinessException(EmBusinessError.USER_NO, "用户已注册");
        }
        supEmployeesMapper.insert(supEmployees);

    }

    @Override
    public void updateEntUser(SupEmployees supEmployees) {
        if(supEmployees.getIdNumber()==null||supEmployees.getIdNumber().equals("")){
            throw new BusinessException(EmBusinessError.USER_NO, "身份证号不可为空");
        }
        QueryWrapper<SupEmployees> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_number",supEmployees.getIdNumber())
                    .ne("id_number",supEmployees.getId());
        if(supEmployeesMapper.selectCount(queryWrapper)>0){
            throw new BusinessException(EmBusinessError.USER_NO, "用户已注册");
        }
        supEmployeesMapper.updateById(supEmployees);

    }

    @Override
    public void deleteEntUser(String idNumber) {
        if(idNumber == null ||idNumber.equals("")){
            throw new BusinessException(EmBusinessError.USER_NO, "参数异常");
        }
            Map<String,Object> map = new HashMap<>();
            map.put("id_number",idNumber);
        supEmployeesMapper.deleteByMap(map);
    }

    @Override
    public  List<SupEmployees> getByIdnumbers(List<String> idNumber) {
        if(idNumber == null ||idNumber.size()==0){
            throw new BusinessException(EmBusinessError.USER_NO, "参数异常");
        }
        QueryWrapper<SupEmployees> queryWrapper = new QueryWrapper<>();
        queryWrapper.in("id_number",idNumber);
        List<SupEmployees> supEmployees = supEmployeesMapper.selectList(queryWrapper);
        return supEmployees;
    }
    @Override
    public  SupEmployees getByIdnumber(String idNumber) {
        if(idNumber == null ||idNumber==""){
            throw new BusinessException(EmBusinessError.USER_NO, "参数异常");
        }
        QueryWrapper<SupEmployees> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id_number",idNumber);
        SupEmployees supEmployees = supEmployeesMapper.selectOne(queryWrapper);
        return supEmployees;
    }

    @Override
    @DoPage
    public List<SupEmployees> getPage(EmployeeParam employeeParam) {
        QueryWrapper<SupEmployees> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(employeeParam.getUsername()),"username",employeeParam.getUsername())
                    .like(StringUtils.isNotEmpty(employeeParam.getPhone()),"phone",employeeParam.getPhone())
                    .ge(employeeParam.getEndTime()!=null,"end_time",employeeParam.getEndTime())
                    .eq("staff_type",employeeParam.getStaffType());
        List<SupEmployees> list = supEmployeesMapper.selectList(queryWrapper);
        return list;
    }

}
