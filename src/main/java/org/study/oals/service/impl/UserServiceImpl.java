/**  
 * @Title: UserServiceImpl.java
 * @Package org.study.heat.service.impl
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2019年5月16日
 */
package org.study.oals.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.study.oals.base.BaseService;
import org.study.oals.base.Constant;
import org.study.oals.config.Constants;
import org.study.oals.dao.RoleUserMapper;
import org.study.oals.dao.UserMapper;
import org.study.oals.model.domain.Audit;
import org.study.oals.model.domain.RoleUser;
import org.study.oals.model.domain.User;
import org.study.oals.model.domain.Wallet;
import org.study.oals.model.dto.CheckLoginNameDto;
import org.study.oals.model.dto.ModifyPwdDto;
import org.study.oals.model.dto.UserQueryDto;
import org.study.oals.model.enums.UserTypeEnum;
import org.study.oals.model.vo.UserVo;
import org.study.oals.service.AuditService;
import org.study.oals.service.UserService;
import org.study.oals.service.WalletService;
import org.study.oals.utils.MD5;
import org.study.oals.utils.PublicUtil;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassName: UserServiceImpl 
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2019年5月16日
 */
@Service("userService")
public class UserServiceImpl extends BaseService<User> implements UserService {

	@Resource
	private UserMapper userMapper;
	@Resource
	private RoleUserMapper roleUserMapper;
	@Resource
	private AuditService auditService;
	@Resource
	private WalletService walletService;


	/* (non-Javadoc)
	 * @see org.study.heat.service.UserService#selectUserById(java.lang.Integer)
	 */
	@Override
	public User selectUserById(Long userId) {
		// TODO Auto-generated method stub

//		return userMapper.selectByPrimaryKey(userId);

        // 查询用户角色信息
        return userMapper.selectByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see org.study.heat.service.UserService#login(java.lang.String, java.lang.String)
	 */
	@Override
	public User login(String loginName, String password) throws Exception {
		// TODO Auto-generated method stub
		if (loginName == null || password == null) {
			throw new RuntimeException("参数传递异常");
		}

		if (StringUtils.isBlank(loginName)) {
			throw new RuntimeException("请输入用户名！");
		}

		if (StringUtils.isBlank(password)) {
			throw new RuntimeException("请输入密码！");
		}

		// 密码加密
		password = MD5.getMd5().getMD5ofStr(password);
		User user = userMapper.selectByLoginName(loginName);

		if (user== null) {
			throw new RuntimeException("用户名不存在.");
		}

		if (!password.equals(user.getLoginPwd())) {
			throw new RuntimeException("密码错误.");
		}

		return user;
	}

	/* (non-Javadoc)
	 * @see org.study.heat.service.UserService#saveUser(org.study.heat.pojo.User)
	 */
	@Override
	public Integer saveUser(User user, User login) {
		// TODO Auto-generated method stub

		user.setUpdateInfo(login);

		if (user.isNew()) {

			User userQuery = userMapper.selectByPrimaryKey(user.getId());
			if (PublicUtil.isEmpty(userQuery)) {
				throw new RuntimeException("用户不存在.");
			}

			// 校验手机号是否重复


			// 校验登录名是否重复

			// 校验是否有更新角色

			String password = MD5.getMd5().getMD5ofStr("123456");
			user.setType(UserTypeEnum.USER.getType());
			user.setLoginPwd(password);

			return userMapper.insertSelective(user);
		} else {

			userMapper.updateByPrimaryKeySelective(user);

			RoleUser roleUser = new RoleUser();
			roleUser.setRoleId(user.getRoleId());

			return roleUserMapper.insertSelective(roleUser);
		}
	}

	/* (non-Javadoc)
	 * @see org.study.heat.service.UserService#queryUserListWithPage(org.study.heat.dto.UserQueryDto)
	 */
	@Override
	public PageInfo queryUserListWithPage(UserQueryDto userQueryDto) {
		// TODO Auto-generated method stub
		userQueryDto.setType(UserTypeEnum.USER.getType());

		PageHelper.startPage(userQueryDto.getPageNum(), userQueryDto.getPageSize());
		List<UserVo> userList = userMapper.queryUserListWithPage(userQueryDto);

		return new PageInfo<>(userList);
	}

	/* (non-Javadoc)
	 * @see org.study.heat.service.UserService#deleteUserById(java.lang.Long)
	 */
	@Override
	public Integer deleteUserById(Long id) {
		// TODO Auto-generated method stub

		return userMapper.deleteByPrimaryKey(id);
	}

	/* (non-Javadoc)
	 * @see org.study.heat.service.UserService#modifyPwd(org.study.heat.dto.ModifyPwdDto)
	 */
	@Override
	public Integer modifyPwd(ModifyPwdDto modifyPwdDto, User login) {
		// TODO Auto-generated method stub
		User user = userMapper.selectByPrimaryKey(modifyPwdDto.getId());
		if (PublicUtil.isEmpty(user)) {
			throw new RuntimeException("用户不存在");
		}

		if (!modifyPwdDto.getPassword().equals(modifyPwdDto.getConfirmPwd())) {
			throw new RuntimeException("两次密码不一致");
		}

		String password = MD5.getMd5().getMD5ofStr(modifyPwdDto.getPassword());

		user = new User();
		user.setUpdateInfo(login);
		user.setId(modifyPwdDto.getId());
		user.setLoginPwd(password);

		return userMapper.updateByPrimaryKeySelective(user);
	}

	/**
	 * 校验登录名是否位置
	 *
	 * @param checkLoginNameDto the check login name dto
	 *
	 * @return the boolean
	 * true登录名唯一 false-登录名不唯一
	 */
	@Override
	public boolean checkLoginName(CheckLoginNameDto checkLoginNameDto) {

		Long id = checkLoginNameDto.getUserId();
		String loginName = checkLoginNameDto.getLoginName();

		Example example = new Example(User.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("loginName", loginName);
		if (id != null) {
			criteria.andNotEqualTo("id", id);
		}

		int result = selectCountByExample(example);

		return result < 1;
	}

	/**
	 * 用户注册
	 *
	 * @param user the user
	 * @return the int
	 */
	@Override
	public Integer register(User user) {

		User record = new User();
		record.setLoginName(user.getLoginName());
		record.setType("user");
		record.setLoginPwd(MD5.getMd5().getMD5ofStr(user.getLoginPwd()));

		int result = save(record);
		if (result > 0) {
			RoleUser roleUser = new RoleUser();
			roleUser.setRoleId(user.getRoleId());
			roleUser.setUserId(record.getId());

			roleUserMapper.insertSelective(roleUser);

			Audit audit = new Audit();
            audit.setId(record.getId());
			audit.setAuditType(user.getRoleId().intValue());
			audit.setPass(Constants.PASS_APPLY);

			auditService.save(audit);

			Wallet wallet = new Wallet();
			wallet.setUserId(record.getId());
			wallet.setId(record.getId());
			wallet.setUpdateInfo(record);

			walletService.save(wallet);
		}

		return result;
	}

}
