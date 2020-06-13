package org.study.oals.dao;

import org.study.oals.model.domain.Audit;
import org.study.oals.model.dto.AuditQueryDto;
import org.study.oals.model.vo.AuditVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface AuditMapper extends Mapper<Audit> {

    /**
     * 查询审核列表 - 分页
     *
     * @param auditQueryDto 查询条件
     *
     * @return the list.
     */
    List<AuditVo> queryAuditListWithPage(AuditQueryDto auditQueryDto);

    /**
     * 查询个人认证审核资料
     *
     * @param id 审核id
     *
     * @return the json result
     */
    AuditVo showById(Long id);
}