package org.study.oals.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.OrderDto;
import org.study.oals.service.WalletService;

import javax.annotation.Resource;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:13
 * @Description:
 */
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Resource
    private WalletService walletService;

    /**
     * 教师提现
     *
     * @param login     当前登陆者
     * @param orderDto  提现对象
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/withdrawal", method = RequestMethod.POST)
    public JsonResult withdrawal(@CurrentUser User login, OrderDto orderDto) {

        Integer result = walletService.withdrawal(login, orderDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 学生充值
     *
     * @param login     当前登陆者
     * @param orderDto  充值对象
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/recharge", method = RequestMethod.POST)
    public JsonResult recharge(@CurrentUser User login, OrderDto orderDto) {

        Integer result = walletService.recharge(login, orderDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

}
