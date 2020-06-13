package org.study.oals.model.dto;

import lombok.Data;
import org.study.oals.base.BaseQuery;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 16:27
 * @Description:
 */
@Data
public class AuditQueryDto extends BaseQuery {

    /**
     * 是否通过 0-待审核 1-通过 2-不通过
     */
    private Integer pass;

    /**
     * 审核类型 2-学生 3-教师
     */
    private Integer auditType;

    /**
     * 用户Id
     */
    private Long userId;

    private String userName;
}
