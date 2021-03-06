package org.study.oals.service;

import org.study.oals.base.IService;
import org.study.oals.model.domain.Audit;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.AuditDto;
import org.study.oals.model.dto.AuditQueryDto;
import org.study.oals.model.vo.AuditVo;

import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 13:58
 * @Description:
 */
public interface AuditService extends IService<Audit> {

    /**
     * 认证审核
     *
     * @param login     认证审核者
     * @param auditDto  认证审核信息
     *
     * @return  the int.
     */
    Integer auditUser(User login, AuditDto auditDto);

    /**
     * 查询审核列表 - 分页
     *
     * @param auditQueryDto 查询条件
     *
     * @return  the list.
     */
    List<AuditVo> queryAuditListWithPage(AuditQueryDto auditQueryDto);

    /**
     * 申请审核认证
     *
     * @param login     当前登录者
     * @param auditDto  审核对象
     *
     * @return  the int.
     */
    Integer auditApply(User login, AuditDto auditDto);

    /**
     * 修改审核认证
     *
     * @param login     当前登录者
     * @param auditDto  审核对象
     *
     * @return  the int.
     */
    Integer auditModify(User login, AuditDto auditDto);

    /**
     * 查询个人认证审核资料
     *
     * @param id    审核id
     *
     * @return  the json result
     */
    AuditVo showById(Long id);
}
