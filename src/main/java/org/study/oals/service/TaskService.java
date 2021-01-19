package org.study.oals.service;

import org.study.oals.base.IService;
import org.study.oals.model.domain.Task;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.vo.TaskVo;

import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:07
 * @Description:
 */
public interface TaskService extends IService<Task> {

    /**
     * 教师完成任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return  the int.
     */
    Integer finishTask(User login, Long id, String answer);

    /**
     * 教师接收任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return  the int.
     */
    Integer receiveTask(User login, Long id);

    /**
     * 查询任务列表 - 分页
     *
     * @param taskQueryDto  查询条件
     *
     * @return  the list.
     */
    List<TaskVo> queryTaskListWithPage(TaskQueryDto taskQueryDto);

    Integer save(Task task, User login);

    Integer confirmTask(User login, Long id);
}
