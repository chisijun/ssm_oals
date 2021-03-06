package org.study.oals.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.OrderQueryDto;
import org.study.oals.model.vo.AuditVo;
import org.study.oals.model.vo.OrderVo;
import org.study.oals.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:06
 * @Description:
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    public OrderService orderService;

    @Authorization
    @RequestMapping(value = "/queryListWithPage", method = RequestMethod.POST)
    public JsonResult queryOrderListWithPage(@CurrentUser User login, OrderQueryDto orderQueryDto) {

        if (orderQueryDto.getUserId() != null &&
                !orderQueryDto.getUserId().equals(-1)) {
            orderQueryDto.setWalletId(login.getId());
        }
        List<OrderVo> orderVoList = orderService.queryOrderListWithPage(orderQueryDto);

        return new JsonResult(true, "操作成功", new PageInfo<>(orderVoList));
    }

}
