package org.study.oals.service.impl;

import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.dao.TaskMapper;
import org.study.oals.model.domain.Task;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.vo.TaskVo;
import org.study.oals.service.TaskService;
import org.study.oals.utils.PublicUtil;

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

    /**
     * 教师完成任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return the int.
     */
    @Override
    public Integer finishTask(User login, Long id) {

        Task task = taskMapper.selectByPrimaryKey(id);
        if (PublicUtil.isEmpty(task)) {
            throw new RuntimeException("任务不存在");
        }

        // TODO 判断任务是否已接受

        task.setUpdateInfo(login);


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

        return null;
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

        return null;
    }
}
