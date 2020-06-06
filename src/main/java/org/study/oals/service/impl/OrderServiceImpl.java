package org.study.oals.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.dao.OrderMapper;
import org.study.oals.model.domain.Order;
import org.study.oals.model.dto.OrderQueryDto;
import org.study.oals.model.vo.OrderVo;
import org.study.oals.service.OrderService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:00
 * @Description:
 */
@Service("orderService")
public class OrderServiceImpl extends BaseService<Order> implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    /**
     * 查询订单列表 - 分页
     *
     * @param orderQueryDto 查询条件
     *
     * @return the list
     */
    @Override
    public List<OrderVo> queryOrderListWithPage(OrderQueryDto orderQueryDto) {

        PageHelper.startPage(orderQueryDto.getPageNum(), orderQueryDto.getPageSize());

        return orderMapper.queryOrderListWithPage(orderQueryDto);
    }
}
