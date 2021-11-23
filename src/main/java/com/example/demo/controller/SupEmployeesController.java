package com.example.demo.controller;


import com.example.demo.common.ResponseUtil;
import com.example.demo.controller.param.EmployeeParam;
import com.example.demo.model.SupEmployees;

import com.example.demo.service.ISupEmployeesService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RestController;
import com.example.demo.codegenerator.BaseController;

import java.io.IOException;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author demo
 * @since 2021-09-07
 */
@RestController
@RequestMapping("/demo/sup-employees")
public class SupEmployeesController extends BaseController {
    @Autowired
    private ISupEmployeesService supEmployeesService;

    @ApiOperation(value = "新增企业用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/insert")
    public void saveUser(@RequestBody SupEmployees supEmployees) throws IOException {
        supEmployeesService.insertEntUser(supEmployees);
        ResponseUtil.showMessage("200", "新增成功", "");
    }

    @ApiOperation(value = "修改企业用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "新增成功")
    })
    @PostMapping("/update")
    public void updateUser(@RequestBody SupEmployees supEmployees) throws IOException {
        supEmployeesService.updateEntUser(supEmployees);
        ResponseUtil.showMessage("200", "修改成功", "");
    }
    @ApiOperation(value = "删除企业用户")
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功")
    })
    @PostMapping("/delete")
    public void delete(@RequestBody String idNumber) throws IOException {
        supEmployeesService.deleteEntUser(idNumber);
        //用户角色关联表数据库外键删除
        ResponseUtil.showMessage("200", "删除成功", "");
    }
    @ApiOperation(value = "根据许可证号获取食品经营许可证")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = SupEmployees.class,responseContainer = "List")
    })
    @GetMapping("/getByIdnumbers")
    public List<SupEmployees> getByIdnumbers(@RequestParam("idNumber") List<String> idNumber) throws IOException {
        List<SupEmployees> list = supEmployeesService.getByIdnumbers(idNumber);
        return list;
    }
    @ApiOperation(value = "根据许可证号获取食品经营许可证")
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = SupEmployees.class)
    })
    @GetMapping("/getByIdnumber")
    public SupEmployees getByIdnumber(@RequestParam("idNumber") String idNumber) throws IOException {
        SupEmployees supEmployees = supEmployeesService.getByIdnumber(idNumber);
         return supEmployees;
    }
    @ApiOperation(value = "搜索企业从业人员")
    @ApiImplicitParams(value = {@ApiImplicitParam(name = "Range", value = "分页", dataType = "String", dataTypeClass = String.class, defaultValue = "0:9", example = "0:9", required = true, paramType = "header")})
    @ApiResponses({
            @ApiResponse(code = 200, message = "查询成功", response = SupEmployees.class, responseContainer = "List")
    })
    @PostMapping("/getPage")
    public  List<SupEmployees> getPage(@RequestBody EmployeeParam eMployeeParam) throws IOException {
        List<SupEmployees> list= supEmployeesService.getPage(eMployeeParam);
        return list;
    }
}