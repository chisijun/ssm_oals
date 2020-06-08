/**  
 * @Title: OSSConfigure.java
 * @Package org.csun.nc.utils
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2017年10月17日
 */
package org.study.oals.utils;

import java.io.IOException;
import java.util.Properties;


/**
 * ClassName: OSSConfigure 
 * @Description: 阿里云存储配置类
 * @author puyijun
 * @date 2017.12.22
 */
public class OSSConfigure {
	
	private static final OSSConfigure ossConfig = 
			new OSSConfigure("aliyun-oss.properties");
	
	/**
	 * 云存储服务器域
	 */
	private String endpoint;

	/**
	 * 安全连接key
	 */
	private String accessKeyId;

	/**
	 * 安全连接秘钥
	 */
	private String accessKeySecret;

	/**
	 * 服务根目录
	 */
	private String bucketName;

	/**
	 * 访问地址
	 */
	private String accessUrl;

	/**
	 * 内部域名
	 */
	private String innerendpoint;

	public OSSConfigure() {
		
	}

	public static OSSConfigure getOssConfig() {
		return ossConfig;
	}
	
	/**
	 * 通过配置文件.properties文件获取，这几项内容。
	 * 
	 * @deprecated
	 * @param storageConfName
	 * @throws IOException
	 */
	public OSSConfigure(String storageConfName) {

		try {
			Properties prop = new Properties();
			prop.load(this.getClass().getClassLoader()
					.getResourceAsStream(storageConfName));

			endpoint = prop.getProperty("endpoint").trim();
			accessKeyId = prop.getProperty("accessKeyId").trim();
			accessKeySecret = prop.getProperty("accessKeySecret").trim();
			bucketName = prop.getProperty("bucketName").trim();
			accessUrl = prop.getProperty("accessUrl").trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	public OSSConfigure(String endpoint, String accessKeyId,
                        String accessKeySecret, String bucketName, String accessUrl) {

		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
		this.accessUrl = accessUrl;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

	public String getInnerendpoint() {
		return innerendpoint;
	}

	public void setInnerendpoint(String innerendpoint) {
		this.innerendpoint = innerendpoint;
	}
}
