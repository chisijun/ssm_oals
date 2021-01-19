package org.study.oals.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.config.Constants;
import org.study.oals.dao.TaskMapper;
import org.study.oals.model.domain.Order;
import org.study.oals.model.domain.Task;
import org.study.oals.model.domain.User;
import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.vo.TaskVo;
import org.study.oals.service.OrderService;
import org.study.oals.service.TaskService;
import org.study.oals.service.WalletService;
import org.study.oals.utils.PublicUtil;
import org.study.oals.utils.TimeUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 17:47
 * @Description:
 */
@Service("taskService")
public class TaskServiceImpl extends BaseService<Task> implements TaskService {

    @Resource
    private TaskMapper taskMapper;
    @Resource
    private WalletService walletService;
    @Resource
    private OrderService orderService;

    /**
     * 教师完成任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return the int.
     */
    @Override
    public Integer finishTask(User login, Long id, String answer) {

        Task task = taskMapper.selectByPrimaryKey(id);
        if (PublicUtil.isEmpty(task)) {
            throw new RuntimeException("任务不存在");
        }

        // TODO 判断任务是否已接受
        task.setAnswer(answer);
        task.setUpdateInfo(login);
        task.setAnswerId(login.getId());
        task.setAnswerName(login.getUserName());
        task.setState(Constants.TASK_FISH);

        return taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 教师接收任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return the int.
     */
    @Override
    public Integer receiveTask(User login, Long id) {

        Task task = taskMapper.selectByPrimaryKey(id);
        if (PublicUtil.isEmpty(task)) {
            throw new RuntimeException("任务不存在");
        }

        // TODO 判断任务是否待接受

        task.setUpdateInfo(login);


        return taskMapper.updateByPrimaryKeySelective(task);
    }

    /**
     * 查询任务列表 - 分页
     *
     * @param taskQueryDto 查询条件
     *
     * @return the list.
     */
    @Override
    public List<TaskVo> queryTaskListWithPage(TaskQueryDto taskQueryDto) {

        PageHelper.startPage(taskQueryDto.getPageNum(), taskQueryDto.getPageSize());

        return taskMapper.queryTaskListWithPage(taskQueryDto);
    }

    @Override
    public Integer save(Task task, User login) {

        task.setUpdateInfo(login);
        task.setState(Constants.TASK_APPLY);

        // 从学生账户扣钱
        Wallet wallet = walletService.selectByKey(login.getId());

        // 检查钱包余额
        if (wallet.getBalance().compareTo(task.getPrice()) < 0) {
            throw new RuntimeException("余额不足, 请充值");
        }

        wallet.setBalance(wallet.getBalance().subtract(task.getPrice()));
        wallet.setUpdateInfo(login);

        walletService.update(wallet);

        Order order = new Order();
        order.setOrderNo(TimeUtils.getPaymentNo());
        order.setUpdateInfo(login);
        order.setAmount(task.getPrice());
        order.setRemark("问题支出");
        order.setOrderType(Constants.ORDER_XF);
        order.setWalletId(login.getId());

        orderService.save(order);

        return taskMapper.insertSelective(task);
    }

    @Override
    public Integer confirmTask(User login, Long id) {

        Task task = taskMapper.selectByPrimaryKey(id);
        if (PublicUtil.isEmpty(task)) {
            throw new RuntimeException("任务不存在");
        }
        task.setState(Constants.TASK_CHECK);
        task.setUpdateInfo(login);

        Wallet wallet = walletService.selectByKey(task.getAnswerId());

        // 给老师转钱
        wallet.setBalance(wallet.getBalance().add(task.getPrice()));
        wallet.setUpdateInfo(login);

        walletService.update(wallet);

        Order order = new Order();
        order.setOrderNo(TimeUtils.getPaymentNo());
        order.setUpdateInfo(login);
        order.setAmount(task.getPrice());
        order.setRemark("任务奖励");
        order.setOrderType(Constants.ORDER_SR);
        order.setWalletId(task.getAnswerId());

        orderService.save(order);

        return taskMapper.updateByPrimaryKeySelective(task);
    }
}
