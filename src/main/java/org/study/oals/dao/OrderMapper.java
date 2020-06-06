package org.study.oals.dao;

import org.study.oals.model.domain.Order;
import org.study.oals.model.dto.OrderQueryDto;
import org.study.oals.model.vo.OrderVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface OrderMapper extends Mapper<Order> {

    /**
     * 查询订单列表 - 分页
     *
     * @param orderQueryDto 查询条件
     *
     * @return the list
     */
    List<OrderVo> queryOrderListWithPage(OrderQueryDto orderQueryDto);
}