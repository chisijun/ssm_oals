package org.study.oals.model.vo;

import lombok.Data;
import org.study.oals.base.BaseEntity;
import org.study.oals.base.BaseVo;

import javax.persistence.Column;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 16:38
 * @Description:
 */
@Data
public class AuditVo extends BaseVo {

    /**
     * 审核类型 2-学生 3-教师
     */
    private Integer auditType;

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
     * 是否通过 0-待审核 1-通过 2-不通过
     */
    private Integer pass;

    /**
     * 失败描述
     */
    private String failedDesc;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
