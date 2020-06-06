package org.study.oals.service.impl;

import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.dao.AuditMapper;
import org.study.oals.model.domain.Audit;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.AuditDto;
import org.study.oals.model.dto.AuditQueryDto;
import org.study.oals.model.vo.AuditVo;
import org.study.oals.service.AuditService;
import org.study.oals.utils.PublicUtil;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 13:58
 * @Description:
 */
@Service("auditService")
public class AuditServiceImpl extends BaseService<Audit> implements AuditService {

    @Resource
    private AuditMapper auditMapper;

    /**
     * 认证审核
     *
     * @param login    认证审核者
     * @param auditDto 认证审核信息
     *
     * @return the int.
     */
    @Override
    public Integer auditUser(User login, AuditDto auditDto) {

        Audit audit = auditMapper.selectByPrimaryKey(auditDto.getId());

        if (PublicUtil.isEmpty(audit)) {
            throw new RuntimeException("审核信息不存在");
        }

        audit.setPass(auditDto.getPass());

        if (auditDto.getPass().equals(2)) {
            audit.setFailedDesc(auditDto.getFailedDesc());
        }

        audit.setUpdateInfo(login);

        return auditMapper.updateByPrimaryKeySelective(audit);
    }

    /**
     * 查询审核列表 - 分页
     *
     * @param auditQueryDto 查询条件
     *
     * @return the list.
     */
    @Override
    public List<AuditVo> queryAuditListWithPage(AuditQueryDto auditQueryDto) {

        PageHelper.startPage(auditQueryDto.getPageNum(), auditQueryDto.getPageSize());

        return auditMapper.queryAuditListWithPage(auditQueryDto);
    }
}
