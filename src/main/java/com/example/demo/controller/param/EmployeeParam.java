package com.example.demo.controller.param;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class EmployeeParam {
    @ApiModelProperty(value = "从业人员类型")
    @TableField("staff_type")
    private Integer staffType;
    @TableField("username")
    private String username;
    @TableField("phone")
    private String phone;
    @TableField("end_time")
    private LocalDate endTime;
}
