package org.study.oals.service;

import org.study.oals.base.IService;
import org.study.oals.model.domain.User;
import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.OrderDto;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:11
 * @Description:
 */
public interface WalletService extends IService<Wallet> {

    /**
     * 教师提现
     *
     * @param login     当前登陆者
     * @param orderDto  提现对象
     *
     * @return  the int.
     */
    Integer withdrawal(User login, OrderDto orderDto);

    /**
     * 学生充值
     *
     * @param login     当前登陆者
     * @param orderDto  充值对象
     *
     * @return  the int.
     */
    Integer recharge(User login, OrderDto orderDto);
}
