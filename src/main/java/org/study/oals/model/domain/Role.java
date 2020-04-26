package org.study.oals.model.domain;

import lombok.Data;
import org.study.car.base.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;

@Data
@Table(name = "t_role")
public class Role extends BaseEntity {

    /**
     * 角色编码
     */
    @Column(name = "role_code")
    private String roleCode;

    /**
     * 角色名称
     */
    @Column(name = "role_name")
    private String roleName;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}