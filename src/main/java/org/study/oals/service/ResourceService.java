package org.study.oals.service;

import org.study.oals.base.IService;
import org.study.oals.model.domain.Resource;

/**
 * @Auther: chisj chisj@foxmal.com
 * @Date: 2020-06-08 11:03
 * @Description:
 */
public interface ResourceService extends IService<Resource> {

    /**
     * 资源添加接口
     *
     * @param resource  资源对象
     *
     * @return  the int.
     */
    Integer resourceAdd(Resource resource);
}
