/**  
 * @Title: OSSManageUtil.java
 * @Package org.csun.nc.utils
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2017年10月17日
 */
package org.study.oals.utils;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;


/**
 * ClassName: OSSManageUtil 
 * @Description: 阿里云存储文件处理类
 * @author puyijun
 * @date 2017.12.22
 */
public class OSSManageUtil {

	/**
	 * 上传OSS服务器文件
	 * 
	 * @Title: uploadFile
	 * @Description:
	 * @param @param ossConfigure
	 * @param @param file
	 * @param @param remotePath
	 * @param @return
	 * @param @throws Exception 设定文件
	 * @return String 返回类型
	 * @throws
	 */
	public static String uploadFile(OSSConfigure ossConfigure, File file,
			String fileFileName, String fileContentType, String remotePath)
			throws Exception {
		InputStream fileContent = null;
		fileContent = new FileInputStream(file);
		
		OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),
				ossConfigure.getAccessKeyId(),
				ossConfigure.getAccessKeySecret());
		String remoteFilePath = remotePath.substring(0, remotePath.length())
				.replaceAll("\\\\", "/") + "/";
		// 创建上传Object的Metadata
		ObjectMetadata objectMetadata = new ObjectMetadata();
		objectMetadata.setContentLength(fileContent.available());
		objectMetadata.setCacheControl("no-cache");
		objectMetadata.setHeader("Pragma", "no-cache");
		objectMetadata.setContentType(fileContentType);
		// System.out.println("contentType = " +
		// contentType(file.getName().substring(file.getName().lastIndexOf("."))));
		objectMetadata.setContentDisposition("inline;filename=" + fileFileName);
		System.out.println("remoteFilePath = " + remoteFilePath);
		// 上传文件
		ossClient.putObject(ossConfigure.getBucketName(), remoteFilePath
				+ fileFileName, fileContent, objectMetadata);
		System.out.println(ossConfigure.getAccessUrl() + "/" + remoteFilePath
				+ fileFileName);
		return ossConfigure.getAccessUrl() + "/" + remoteFilePath
				+ fileFileName;
	}

	/**
	 * 根据key删除OSS服务器上的文件
	 * 
	 * @Title: deleteFile
	 * @Description:
	 * @param @param ossConfigure
	 * @param @param filePath 设定文件
	 * @return void 返回类型
	 * @throws
	 */
	public static void deleteFile(OSSConfigure ossConfigure, String filePath) {
		OSSClient ossClient = new OSSClient(ossConfigure.getEndpoint(),
				ossConfigure.getAccessKeyId(),
				ossConfigure.getAccessKeySecret());
		System.out.println("oss uri = " + ossConfigure.getAccessUrl());
		System.out.println("filePath = " + filePath);
		if (filePath.startsWith(ossConfigure.getAccessUrl() + "/")) {
			String key = filePath.substring(ossConfigure.getAccessUrl()
					.length() + 1, filePath.length());
			System.out.println("key = " + key);
			ossClient.deleteObject(ossConfigure.getBucketName(), key);
		}

	}

	/**
	 * Description: 判断OSS服务文件上传时文件的contentType
	 * 
	 * @Version1.0
	 * @param FilenameExtension
	 *            文件后缀
	 * @return String
	 */
	public static String contentType(String FilenameExtension) {
		if (FilenameExtension.equals("BMP") || FilenameExtension.equals("bmp")) {
			return "image/bmp";
		}
		if (FilenameExtension.equals("GIF") || FilenameExtension.equals("gif")) {
			return "image/gif";
		}
		if (FilenameExtension.equals("JPEG")
				|| FilenameExtension.equals("jpeg")
				|| FilenameExtension.equals("JPG")
				|| FilenameExtension.equals("jpg")
				|| FilenameExtension.equals("PNG")
				|| FilenameExtension.equals("png")) {
			return "image/jpeg";
		}
		if (FilenameExtension.equals("HTML")
				|| FilenameExtension.equals("html")) {
			return "text/html";
		}
		if (FilenameExtension.equals("TXT") || FilenameExtension.equals("txt")) {
			return "text/plain";
		}
		if (FilenameExtension.equals("VSD") || FilenameExtension.equals("vsd")) {
			return "application/vnd.visio";
		}
		if (FilenameExtension.equals("PPTX")
				|| FilenameExtension.equals("pptx")
				|| FilenameExtension.equals("PPT")
				|| FilenameExtension.equals("ppt")) {
			return "application/vnd.ms-powerpoint";
		}
		if (FilenameExtension.equals("DOCX")
				|| FilenameExtension.equals("docx")
				|| FilenameExtension.equals("DOC")
				|| FilenameExtension.equals("doc")) {
			return "application/msword";
		}
		if (FilenameExtension.equals("XML") || FilenameExtension.equals("xml")) {
			return "text/xml";
		}
		if (FilenameExtension.equals("APK") || FilenameExtension.equals("apk")) {
			return "application/vnd.android.package-archive,application/octet-stream";
		}
		if (FilenameExtension.equals("PDF") || FilenameExtension.equals("pdf")) {
			return "application/pdf";
		}
		return "text/html";
	}
}
