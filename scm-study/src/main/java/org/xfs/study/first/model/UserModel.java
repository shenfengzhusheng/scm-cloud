package org.xfs.study.first.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@ApiModel("用户模型")
@AllArgsConstructor
@Getter
public class UserModel {

    @ApiModelProperty(value = "用户Id")
    private int id;
    @ApiModelProperty(value = "用户姓名")
    private String name;
    @ApiModelProperty(value = "用户密码")
    private String password;
}
