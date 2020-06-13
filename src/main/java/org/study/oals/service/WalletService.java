package org.study.oals.service;

import org.study.oals.base.IService;
import org.study.oals.model.domain.User;
import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.OrderDto;
import org.study.oals.model.dto.WalletQueryDto;
import org.study.oals.model.vo.WalletVo;

import java.util.List;

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

    /**
     * 查询个人钱包
     *
     * @param id    钱包id
     *
     * @return  the json result
     */
    WalletVo showById(Long id);

    /**
     * 查询钱包列表 - 分页
     *
     * @param walletQueryDto  查询条件
     *
     * @return  the json result.
     */
    List<WalletVo> queryListWithPage(WalletQueryDto walletQueryDto);
}
