package org.study.oals.model.domain;

import lombok.Data;
import org.study.oals.base.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Data
@Table(name = "t_audit")
public class Audit extends BaseEntity {

    /**
     * 审核类型 2-学生 3-教师
     */
    @Column(name = "audit_type")
    private Integer auditType;

    /**
     * 身份证
     */
    @Column(name = "id_card")
    private String idCard;

    /**
     * 身份证正面
     */
    @Column(name = "id_card_up")
    private String idCardUp;

    /**
     * 身份证反面
     */
    @Column(name = "id_card_down")
    private String idCardDown;

    /**
     * 学生编号
     */
    @Column(name = "student_no")
    private String studentNo;

    /**
     * 学校名称
     */
    private String school;

    /**
     * 教师资格证编号
     */
    @Column(name = "teacher_no")
    private String teacherNo;

    /**
     * 是否通过 0-待审核 1-通过 2-不通过
     */
    private Integer pass;

    /**
     * 失败描述
     */
    @Column(name = "failed_desc")
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