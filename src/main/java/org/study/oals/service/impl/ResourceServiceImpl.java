package org.study.oals.service.impl;

import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.dao.ResourceMapper;
import org.study.oals.model.domain.Resource;
import org.study.oals.service.ResourceService;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-08 11:04
 * @Description:
 */
@Service("resourceService")
public class ResourceServiceImpl extends BaseService<Resource> implements ResourceService {

    @javax.annotation.Resource
    private ResourceMapper resourceMapper;

    /**
     * 资源添加接口
     *
     * @param resource 资源对象
     *
     * @return the int.
     */
    @Override
    public Integer resourceAdd(Resource resource) {

        return resourceMapper.insertSelective(resource);
    }
}
