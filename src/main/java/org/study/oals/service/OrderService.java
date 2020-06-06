package org.study.oals.service;

import org.study.oals.base.IService;
import org.study.oals.model.domain.Order;
import org.study.oals.model.dto.OrderQueryDto;
import org.study.oals.model.vo.OrderVo;

import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 13:59
 * @Description:
 */
public interface OrderService extends IService<Order> {

    /**
     * 查询订单列表 - 分页
     *
     * @param orderQueryDto 查询条件
     *
     * @return  the list
     */
    List<OrderVo> queryOrderListWithPage(OrderQueryDto orderQueryDto);
}
