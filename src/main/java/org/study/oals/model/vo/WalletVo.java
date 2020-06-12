package org.study.oals.model.vo;

import lombok.Data;
import org.study.oals.base.BaseVo;

import javax.persistence.Column;
import java.math.BigDecimal;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-12 13:43
 * @Description:
 */
@Data
public class WalletVo extends BaseVo {

    private String userName;

    private String mobileNo;
    /**
     * 身份证
     */
    private String idCard;

    private Integer auditType;

    /**
     * 支付宝账户
     */
    private String payNumber;

    /**
     * 余额 单位-元保留两位小数
     */
    private BigDecimal balance;

    /**
     * 状态
     */
    private String status;

}
