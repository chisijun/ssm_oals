package org.study.oals.service.impl;

import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.model.domain.User;
import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.OrderDto;
import org.study.oals.service.WalletService;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:12
 * @Description:
 */
@Service("walletService")
public class WalletServiceImpl extends BaseService<Wallet> implements WalletService {

    /**
     * 教师提现
     *
     * @param login    当前登陆者
     * @param orderDto 提现对象
     *
     * @return the int.
     */
    @Override
    public Integer withdrawal(User login, OrderDto orderDto) {
        return null;
    }

    /**
     * 学生充值
     *
     * @param login    当前登陆者
     * @param orderDto 充值对象
     *
     * @return the int.
     */
    @Override
    public Integer recharge(User login, OrderDto orderDto) {
        return null;
    }
}
