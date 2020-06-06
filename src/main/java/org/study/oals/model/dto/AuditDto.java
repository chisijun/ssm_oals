package org.study.oals.model.dto;

import lombok.Data;

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

}
