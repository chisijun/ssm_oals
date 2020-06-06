package org.study.oals.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.common.JsonResult;
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
    @RequestMapping(value = "/queryAuditListWithPage", method = RequestMethod.POST)
    private JsonResult queryOrderListWithPage(OrderQueryDto orderQueryDto) {

        List<OrderVo> orderVoList = orderService.queryOrderListWithPage(orderQueryDto);

        return new JsonResult(true, "操作成功", new PageInfo<>(orderVoList));
    }

}
