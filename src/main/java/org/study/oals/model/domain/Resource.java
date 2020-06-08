package org.study.oals.model.domain;

import lombok.Data;
import org.study.oals.base.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Data
@Table(name = "t_resource")
public class Resource extends BaseEntity {

    /**
     * 资源名称
     */
    @Column(name = "resource_name")
    private String resourceName;

    /**
     * 资源路径
     */
    @Column(name = "resource_path")
    private String resourcePath;

    /**
     * 资源类型Id
     */
    @Column(name = "resource_type_id")
    private Integer resourceTypeId;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}