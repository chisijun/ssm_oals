package org.study.oals.model.dto;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 17:27
 * @Description:
 */
@Data
public class OrderDto {

    private BigDecimal amount;

    private String payNumber;

    private String username;

    private String mobile;
}
