package org.study.oals.model.dto;

import lombok.Data;
import org.study.oals.base.BaseQuery;

import javax.persistence.Column;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 17:07
 * @Description:
 */
@Data
public class OrderQueryDto extends BaseQuery {

    /**
     * 订单类型 0-充值 1-提现 2-支付任务费用 3-获取任务奖励
     */
    private Integer orderType;

    private Long userId;

    private Long walletId;
}
