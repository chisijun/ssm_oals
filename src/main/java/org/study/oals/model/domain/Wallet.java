package org.study.oals.model.domain;

import lombok.Data;
import org.study.oals.base.BaseEntity;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Data
@Table(name = "t_wallet")
public class Wallet extends BaseEntity {

    /**
     * 用户Id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 支付宝账户
     */
    @Column(name = "pay_number")
    private String payNumber;

    /**
     * 余额 单位-元保留两位小数
     */
    private BigDecimal balance;

    /**
     * 状态
     */
    private String status;

    /**
     * 备注
     */
    private String remark;

}