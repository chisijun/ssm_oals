package org.study.oals.model.domain;

import lombok.Data;
import org.study.oals.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "t_order")
public class Order extends BaseEntity {

    /**
     * 钱包Id
     */
    @Column(name = "wallet_id")
    private Long walletId;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 订单号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 订单类型 0-充值 1-提现 2-支付任务费用 3-获取任务奖励
     */
    @Column(name = "order_type")
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