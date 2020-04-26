package org.study.oals.dao;

import org.study.car.model.domain.User;
import org.study.car.model.dto.UserQueryDto;
import org.study.car.model.vo.UserVo;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    /**
     * @Description: TODO
     * @param @param loginName
     * @param @return
     * @return User
     * @throws
     * @author chisj chisj@foxmail.com
     * @date 2019年5月25日
     */
    User selectByLoginName(String loginName);

    /**
     * @Description: TODO
     * @param @param userQueryDto
     * @param @return
     * @return List<User>
     * @throws
     * @author chisj chisj@foxmail.com
     * @date 2019年6月10日
     */
    List<UserVo> queryUserListWithPage(UserQueryDto userQueryDto);
}