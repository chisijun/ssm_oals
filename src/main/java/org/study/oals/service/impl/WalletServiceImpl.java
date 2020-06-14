package org.study.oals.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.config.Constants;
import org.study.oals.dao.WalletMapper;
import org.study.oals.model.domain.Order;
import org.study.oals.model.domain.User;
import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.OrderDto;
import org.study.oals.model.dto.WalletQueryDto;
import org.study.oals.model.vo.WalletVo;
import org.study.oals.service.OrderService;
import org.study.oals.service.WalletService;
import org.study.oals.utils.TimeUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:12
 * @Description:
 */
@Service("walletService")
public class WalletServiceImpl extends BaseService<Wallet> implements WalletService {

    @Resource
    private WalletMapper walletMapper;
    @Resource
    private OrderService orderService;

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

        Wallet wallet = walletMapper.selectByPrimaryKey(login.getId());
        if (wallet.getBalance().compareTo(orderDto.getAmount()) < 0) {
            throw new RuntimeException("余额不足");
        }

        wallet.setBalance(wallet.getBalance().subtract(orderDto.getAmount()));

        Order order = new Order();
        order.setOrderNo(TimeUtils.getPaymentNo());
        order.setUpdateInfo(login);
        order.setAmount(orderDto.getAmount());
        order.setRemark(orderDto.getRemark());
        order.setOrderType(Constants.ORDER_OUT);
        order.setWalletId(login.getId());

        orderService.save(order);

        return walletMapper.updateByPrimaryKeySelective(wallet);
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

//        Wallet wallet = walletMapper.selectByPrimaryKey(login.getId());
//        if (wallet.getBalance().compareTo(orderDto.getAmount()) < 0) {
//            throw new RuntimeException("余额不足");
//        }

        Wallet wallet = walletMapper.selectByPrimaryKey(login.getId());
        wallet.setId(login.getId());
        wallet.setBalance(wallet.getBalance().add(orderDto.getAmount()));
        wallet.setUpdateInfo(login);

        Order order = new Order();
        order.setOrderNo(TimeUtils.getPaymentNo());
        order.setUpdateInfo(login);
        order.setAmount(orderDto.getAmount());
        order.setRemark(orderDto.getRemark());
        order.setOrderType(Constants.ORDER_IN);
        order.setWalletId(login.getId());

        orderService.save(order);

        return walletMapper.updateByPrimaryKeySelective(wallet);
    }

    /**
     * 查询个人钱包
     *
     * @param id 钱包id
     *
     * @return the json result
     */
    @Override
    public WalletVo showById(Long id) {

        return walletMapper.showById(id);
    }

    /**
     * 查询钱包列表 - 分页
     *
     * @param walletQueryDto 查询条件
     *
     * @return the json result.
     */
    @Override
    public List<WalletVo> queryListWithPage(WalletQueryDto walletQueryDto) {

        PageHelper.startPage(walletQueryDto.getPageNum(), walletQueryDto.getPageSize());

        return walletMapper.queryListWithPage(walletQueryDto);
    }

}
