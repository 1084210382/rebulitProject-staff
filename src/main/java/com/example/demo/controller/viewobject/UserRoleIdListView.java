package com.example.demo.controller.viewobject;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author whn
 * 接收用户角色list
 */
@Data
@ApiModel(value = "/demo/sys-acl-module/getRoleTreeByRoleId，获取某一角色的权限树使用的view")
public class UserRoleIdListView {

    @ApiModelProperty(value = "待请求的角色id", dataType = "Integer", example = "0")
    Integer roleId;

    @ApiModelProperty(value = "接收当前用户的角色列表,该list用于接收用户角色列表，根据该列表检索权限", dataType = "List<Integer>", example = "[1,2,3]")
    List<Integer> userRoleIdList;
}
