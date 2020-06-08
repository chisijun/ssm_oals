package org.study.oals.model.dto;

import lombok.Data;
import org.study.oals.base.BaseQuery;

import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 17:30
 * @Description:
 */
@Data
public class TaskQueryDto extends BaseQuery {

    /**
     * 状态
     */
    private Integer state;

    /**
     * 状态列表
     */
    private List<Integer> stateList;

}
