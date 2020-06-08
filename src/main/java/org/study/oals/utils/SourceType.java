/**  
 * @Title: SourceType.java
 * @Package org.csun.nc.pojo
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2017年10月17日
 */
package org.study.oals.utils;

/**
 * ClassName: SourceType 
 * @Description: 文件资源类型
 * @author puyijun
 * @date 2017.12.22
 */
public class SourceType {

	private String sourceTypeId;		// 资源类型Id
	private String sourceTypeName;		// 资源类型名称
	private String sourceTypePath;		// 资源类型路径
	
	public SourceType() {
		super();
	}
	
	public SourceType(String sourceTypeId, String sourceTypeName,
                      String sourceTypePath) {
		this.sourceTypeId = sourceTypeId;
		this.sourceTypeName = sourceTypeName;
		this.sourceTypePath = sourceTypePath;
	}
	
	/**
	 * @return sourceTypePath
	 */
	public String getSourceTypePath() {
		return sourceTypePath;
	}
	/**
	 * @param sourceTypePath sourceTypePath
	 */
	public void setSourceTypePath(String sourceTypePath) {
		this.sourceTypePath = sourceTypePath;
	}
	/**
	 * @return sourceTypeId
	 */
	public String getSourceTypeId() {
		return sourceTypeId;
	}
	/**
	 * @param sourceTypeId sourceTypeId
	 */
	public void setSourceTypeId(String sourceTypeId) {
		this.sourceTypeId = sourceTypeId;
	}
	/**
	 * @return sourceTypeName
	 */
	public String getSourceTypeName() {
		return sourceTypeName;
	}
	/**
	 * @param sourceTypeName sourceTypeName
	 */
	public void setSourceTypeName(String sourceTypeName) {
		this.sourceTypeName = sourceTypeName;
	}
}
