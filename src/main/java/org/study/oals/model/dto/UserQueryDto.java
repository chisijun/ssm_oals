/**  
 * @Title: UserQueryDto.java
 * @Package org.study.heat.dto
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2019年6月10日
 */
package org.study.oals.model.dto;

import lombok.Data;
import org.study.oals.base.BaseQuery;

/**
 * ClassName: UserQueryDto 
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2019年6月10日
 */
@Data
public class UserQueryDto extends BaseQuery {

	/**
	 * 用户姓名
	 */
	private String userName;
	
	/**
	 * 用户类型
	 */
	private String type;

	/**
	 * 角色ID
	 */
	private Long roleId;
	
}
