package org.study.oals.model.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020/6/14 21:03
 * @Description: TODO
 */
@Data
public class OnlinePayParam {

    private String body; // 备注
    private BigDecimal totalAmount; // 金额
    private String token; // 用户认证令牌
    private Integer payType = 2;
}
