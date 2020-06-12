package org.study.oals.controller;

import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.User;
import org.study.oals.model.dto.AuditDto;
import org.study.oals.model.dto.AuditQueryDto;
import org.study.oals.model.vo.AuditVo;
import org.study.oals.service.AuditService;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-06 13:59
 * @Description:
 */
@RestController
@RequestMapping("/audit")
public class AuditController {

    @Resource
    private AuditService auditService;

    /**
     * 查询个人认证审核资料
     *
     * @param login 当前登录者
     * @param id    审核id
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/showById/{id}", method = RequestMethod.POST)
    public JsonResult showById(@CurrentUser User login, @PathVariable Long id) {

        AuditVo auditVo = auditService.showById(id);

        return new JsonResult(true, "操作成功", auditVo);
    }

    /**
     * 申请审核认证
     *
     * @param login     当前登录者
     * @param auditDto  审核对象
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/auditApply", method = RequestMethod.POST)
    public JsonResult auditApply(@CurrentUser User login, AuditDto auditDto) {

        Integer result = auditService.auditApply(login, auditDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 修改审核认证
     *
     * @param login     当前登录者
     * @param auditDto  审核对象
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/auditModify", method = RequestMethod.POST)
    public JsonResult auditModify(@CurrentUser User login, AuditDto auditDto) {

        Integer result = auditService.auditModify(login, auditDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 审核认证
     *
     * @param login     当前登录者
     * @param auditDto  审核认证对象
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/auditUser", method = RequestMethod.POST)
    public JsonResult auditUser(@CurrentUser User login, AuditDto auditDto) {

        Integer result = auditService.auditUser(login, auditDto);
        if (result < 1) {
            return new JsonResult(false, "操作失败", result);
        }

        return new JsonResult(true, "操作成功", result);
    }

    /**
     * 查询认证审核列表 - 分页查询
     *
     * @param auditQueryDto 查询条件
     *
     * @return  the json result
     */
    @Authorization
    @RequestMapping(value = "/queryAuditListWithPage", method = RequestMethod.POST)
    public JsonResult queryAuditListWithPage(AuditQueryDto auditQueryDto) {

        List<AuditVo> auditVoList = auditService.queryAuditListWithPage(auditQueryDto);

        return new JsonResult(true, "操作成功", new PageInfo<>(auditVoList));
    }

}
