package org.study.oals.model.dto;

import lombok.Data;

import javax.persistence.Column;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 16:18
 * @Description:
 */
@Data
public class AuditDto {

    /**
     * 审核ID
     */
    private Long id;

    /**
     * 是否通过 1-通过 2-不通过
     */
    private Integer pass;

    /**
     * 失败描述
     */
    private String failedDesc;

    /**
     * 审核类型 2-学生 3-教师
     */
    private Integer auditType;

    private String idCard;

    private String userName;

    private String mobileNo;

    private String email;

    private String payNumber;

    /**
     * 身份证正面
     */
    private String idCardUp;

    /**
     * 身份证反面
     */
    private String idCardDown;

    /**
     * 学生编号
     */
    private String studentNo;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 教师资格证编号
     */
    private String teacherNo;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
