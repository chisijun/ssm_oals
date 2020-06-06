package org.study.oals.model.domain;

import lombok.Data;
import org.study.oals.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "t_task")
public class Task extends BaseEntity {

    /**
     * 问题标题
     */
    @Column(name = "question_name")
    private String questionName;

    /**
     * 问题描述
     */
    @Column(name = "question_desc")
    private String questionDesc;

    /**
     * 问题附件
     */
    @Column(name = "question_attachment")
    private String questionAttachment;

    /**
     * 价格 单位-元保留两位小数
     */
    private BigDecimal price;

    /**
     * 答案
     */
    private String answer;

    /**
     * 答案附件
     */
    @Column(name = "answer_attachment")
    private String answerAttachment;

    /**
     * 回答者id
     */
    @Column(name = "answer_id")
    private Long answerId;

    /**
     * 回答者姓名
     */
    @Column(name = "answer_name")
    private String answerName;

    /**
     * 状态
     */
    private Integer state;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 标签
     */
    private String tags;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}