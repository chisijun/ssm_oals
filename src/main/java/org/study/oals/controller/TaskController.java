package org.study.oals.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.Task;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.vo.OrderVo;
import org.study.oals.model.vo.TaskVo;
import org.study.oals.service.TaskService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 14:10
 * @Description:
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    @Resource
    private TaskService taskService;

    /**
     * 教师完成任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/finishTask", method = RequestMethod.POST)
    public JsonResult finishTask(@CurrentUser User login, Long id, String answer) {

        Integer result = taskService.finishTask(login, id, answer);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);

    }

    @Authorization
    @RequestMapping(value = "/confirmTask/{id}", method = RequestMethod.POST)
    public JsonResult confirmTask(@CurrentUser User login, @PathVariable Long id) {

        Integer result = taskService.confirmTask(login, id);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);

    }

    /**
     * 教师接收任务
     *
     * @param login 当前登录者
     * @param id    任务ID
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/receiveTask", method = RequestMethod.POST)
    public JsonResult receiveTask(@CurrentUser User login, Long id) {

        Integer result = taskService.receiveTask(login, id);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 查询任务列表 - 分页
     *
     * @param taskQueryDto  查询条件
     *
     * @return  the json result.
     */
    @Authorization
    @RequestMapping(value = "/queryTaskListWithPage", method = RequestMethod.POST)
    public JsonResult queryTaskListWithPage(TaskQueryDto taskQueryDto) {

        List<TaskVo> taskVoList = taskService.queryTaskListWithPage(taskQueryDto);

        return new JsonResult(true, "操作成功", new PageInfo<>(taskVoList));

    }

    @Authorization
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public JsonResult saveUser(@CurrentUser User login, Task task) {

        Integer result = taskService.save(task, login);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }
}
