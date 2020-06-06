package org.study.oals.model.vo;

import lombok.Data;
import org.study.oals.base.BaseVo;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 17:15
 * @Description:
 */
@Data
public class OrderVo extends BaseVo {

    /**
     * 钱包Id
     */
    private Long walletId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 订单类型 0-充值 1-提现 2-支付任务费用 3-获取任务奖励
     */
    private Integer orderType;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}
