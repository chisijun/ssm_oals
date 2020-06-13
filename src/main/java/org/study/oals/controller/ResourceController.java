/**  
 * @Title: SourceController.java
 * @Package org.csun.nc.controller
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2017年10月16日
 */
package org.study.oals.controller;

import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.study.oals.annotation.Authorization;
import org.study.oals.annotation.CurrentUser;
import org.study.oals.common.JsonResult;
import org.study.oals.model.domain.Resource;
import org.study.oals.model.domain.User;
import org.study.oals.service.ResourceService;
import org.study.oals.utils.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * ClassName: SourceController 
 * @Description: 资源管理拦截器
 * @author chisj chisj@foxmail.com
 * @date 2017年10月16日
 */
//@CrossOrigin
@RestController
@RequestMapping("/resource")
public class ResourceController {

	@javax.annotation.Resource
	private ResourceService resourceService;
	
	/**
	 * 资源上传
	 * @throws Throwable 
	 */
	@Authorization
	@RequestMapping(value="/resourceAdd",method= RequestMethod.POST,produces="text/html;charset=utf-8")
	public void springUpload(@CurrentUser User login, HttpServletResponse response,
							 HttpServletRequest request) throws Throwable {
		/* 获取文件描述参数 */
		System.out.println("login info = " + login.toString());
		String fileDesc = request.getParameter("fileDesc");
		
		List<Resource> resources = new ArrayList<Resource>();
		
		/* 将当前上下文初始化给  CommonsMutipartResolver （多部分解析器）*/
		CommonsMultipartResolver multipartResolver=new CommonsMultipartResolver(
				request.getSession().getServletContext());
		
		/* 检查form中是否有enctype="multipart/form-data" */
		if(multipartResolver.isMultipart(request)) {
			/* 将request变成多部分request */
			MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
			
			/* 获取multiRequest 中所有的文件名*/
			Iterator iter=multiRequest.getFileNames();
			String timedate  = UtilDate.getOrderNum();	// 时间戳,避免文件覆盖
			
			while(iter.hasNext()) {
				
				//一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
                if(file != null) {

                	/* 文件属性获取 */
					SourceType sourceType = MimeTypeUtil.getSourceType(file.getContentType());

					/* MultipartFile 转 File */
					File f=File.createTempFile("tmp", null);
				    file.transferTo(f);
				    f.deleteOnExit();
				    /* OSS文件上传 */
					OSSManageUtil.uploadFile(OSSConfigure.getOssConfig(), f,
							file.getOriginalFilename(), file.getContentType(), sourceType
								.getSourceTypePath() + "/" + timedate);
                	/* 保存上传的文件属性 */
					Resource r = new Resource();
					r.setResourceName(file.getOriginalFilename());
					r.setResourcePath(OSSConfigure.getOssConfig().getAccessUrl() + "/"
							+ sourceType.getSourceTypePath() + "/" + timedate + "/" + file.getOriginalFilename());
					if (StringUtils.isNotBlank(fileDesc)) {
						r.setRemark(fileDesc);
					}
					r.setResourceTypeId(Integer.parseInt(sourceType.getSourceTypeId()));
					r.setUpdateInfo(login);
					// 添加资源记录
					resourceService.resourceAdd(r);
					resources.add(r);
                }
            }
        }
        
        /* 返回上传结果 */
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/x-json;charset=UTF-8");
        /* 解决文件上传跨域问题 */
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
        // 默认只上传了一个文件
        JSONObject jsObject =JSONObject.fromObject(new JsonResult(true, "文件上传成功", resources));
        
        response.getWriter().write(jsObject.toString());
    }
	
	/**
	 * 资源信息修改
	 */
	@Authorization
	@RequestMapping(value="/resourceUpdate",method= RequestMethod.POST)
	public JsonResult fileUpdate(@CurrentUser User login, Resource resource) throws Throwable{
		
//		Resource r = resourceService.resourceShowById(resource.getResourceid());
		Resource r = resourceService.selectByKey(resource.getId());
		if (r == null) {
			return new JsonResult(false, "修改资源信息参数错误", null);
		}
		
		int result = resourceService.update(resource);
		
		return new JsonResult(true,"修改资源信息成功", result);
	}
	
	/**
	 * 资源删除
	 */
	@Authorization
	@RequestMapping(value="/resourceDel", method= RequestMethod.GET)
	public JsonResult fileDelete(@CurrentUser User login, Resource resource) throws Throwable {

//		Resource r = resourceService.resourceShowById(resource.getResourceid());
		Resource r = resourceService.selectByKey(resource.getId());

		/* 删除资源 */
		OSSManageUtil.deleteFile(OSSConfigure.getOssConfig(), r.getResourcePath());
		
		int result = resourceService.deleteByKey(resource.getId());
		
		return new JsonResult(true,"删除资源信息成功",null);
	}
	
	/**
	 * 资源信息查询
	 */
	@Authorization
	@RequestMapping(method= RequestMethod.GET)
	public JsonResult fileShow(@CurrentUser User login, Resource resource) throws Throwable{

		
//		Resource r = resourceService.resourceShowById(resource.getResourceid());
		Resource r = resourceService.selectByKey(resource.getId());
		if (r == null) {
			return new JsonResult(false, "修改资源信息参数错误", null);
		}
		
		return new JsonResult(true,"查询资源信息成功",r);
	}
	
	/**
	 * 资源下载
	 */
	@RequestMapping(value="/resourceDown",method= RequestMethod.GET)
	public String fileDown(Resource resource, HttpServletRequest request, HttpServletResponse response) throws Throwable{

		
//		Resource r = resourceService.resourceShowById(resource.getResourceid());
		Resource r = resourceService.selectByKey(resource.getId());
		if (r == null) {
			return new JsonResult(false, "下载资源信息参数错误", null).toString();
		}
		//获取项目工程路径
        ServletContext sc = request.getSession().getServletContext();
        String dir = sc.getRealPath("/file");
        File file = new File(dir + "/" + MimeTypeUtil.getFilePath(r));
        if (!file.exists()) {
        	URL httpurl = new URL(r.getResourcePath());
        	FileUtils.copyURLToFile(httpurl, file);
        }
        String downloadFielName = new String(r.getResourceName().getBytes("UTF-8"),"iso-8859-1");
		System.out.println("file is exists.");
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition",
				"attachment;fileName=" + downloadFielName);// 设置文件名
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 根据url下载文件
	 */
	@RequestMapping(value="/downUrl",method= RequestMethod.GET)
	public String fileDownUrl(String url,HttpServletRequest request, 
			HttpServletResponse response) throws Throwable{
		if (StringUtils.isBlank(url)) {
			System.out.println("1 url = " + url);
			return new JsonResult(false, "下载资源信息参数错误", "url不能为空").toString();
		}
		
		//获取项目工程路径
        ServletContext sc = request.getSession().getServletContext();
        String dir = sc.getRealPath("/file");
        String filename = MimeTypeUtil.getUrlFilePath(url);
        File file = new File(dir + "/" + filename);
        if (!file.exists()) {
        	URL httpurl = new URL(url);
        	FileUtils.copyURLToFile(httpurl, file);
        }
		
        String downloadFielName = new String(filename.getBytes("UTF-8"),"iso-8859-1");
		response.setContentType("application/force-download");// 设置强制下载不打开
		response.addHeader("Content-Disposition",
				"attachment;fileName=" + downloadFielName);// 设置文件名
		byte[] buffer = new byte[1024];
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		try {
			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			OutputStream os = response.getOutputStream();
			int i = bis.read(buffer);
			while (i != -1) {
				os.write(buffer, 0, i);
				i = bis.read(buffer);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 * 文件下载示例
	 */
	@RequestMapping(value={"download"},method={RequestMethod.GET})
	public void download(HttpServletResponse response) throws IOException{
		File file =  new File("F:/日创文档模板.doc");
		if(file.exists()){
			System.out.println("file is exists.");
			response.setContentType("application/force-download");
			response.addHeader("Content-Disposition", "attachment; filename=download.doc");
			FileInputStream fileInputStream = new FileInputStream(file);
			byte[] by  = new byte[fileInputStream.available()];
			fileInputStream.read(by);
			OutputStream outputStream = response.getOutputStream();
			outputStream.write(by);
			fileInputStream.close();
			outputStream.close();
		}
	}
	
}
