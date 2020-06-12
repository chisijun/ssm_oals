package org.study.oals.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.OrderDto;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.dto.WalletQueryDto;
import org.study.oals.model.vo.AuditVo;
import org.study.oals.model.vo.TaskVo;
import org.study.oals.model.vo.WalletVo;
import org.study.oals.service.WalletService;

import javax.annotation.Resource;
import java.util.List;

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

    /**
     * 查询个人钱包
     *
     * @param login 当前登录者
     * @param id    钱包id
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/showById/{id}", method = RequestMethod.POST)
    public JsonResult showById(@CurrentUser User login, @PathVariable Long id) {

        WalletVo walletVo = walletService.showById(id);

        return new JsonResult(true, "操作成功", walletVo);
    }

    /**
     * 查询钱包列表 - 分页
     *
     * @param walletQueryDto  查询条件
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/queryListWithPage", method = RequestMethod.POST)
    public JsonResult queryListWithPage(WalletQueryDto walletQueryDto) {

        List<WalletVo> walletVoList = walletService.queryListWithPage(walletQueryDto);

        return new JsonResult(true, "操作成功", new PageInfo<>(walletVoList));

    }

}
