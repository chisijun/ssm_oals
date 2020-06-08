package org.study.oals.dao;

import org.study.oals.model.domain.Task;
import org.study.oals.model.dto.TaskQueryDto;
import org.study.oals.model.vo.TaskVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TaskMapper extends Mapper<Task> {

    /**
     * 查询任务列表 - 分页
     *
     * @param taskQueryDto 查询条件
     *
     * @return the list.
     */
    List<TaskVo> queryTaskListWithPage(TaskQueryDto taskQueryDto);
}