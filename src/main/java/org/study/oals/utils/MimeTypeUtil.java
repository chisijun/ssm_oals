/**  
 * @Title: MimeTypeUtil.java
 * @Package org.csun.nc.utils
 * @Description: TODO
 * @author chisj chisj@foxmail.com
 * @date 2017年10月17日
 */
package org.study.oals.utils;


import org.study.oals.model.domain.Resource;

import java.util.HashMap;


/**
 * ClassName: MimeTypeUtil 
 * @Description: 通过文件类型获取文件类型和路径
 * @author chisj chisj@foxmail.com
 * @date 2017年10月17日
 */
public class MimeTypeUtil {

	/**
	 * 文件属性列表
	 */
	public static HashMap<String, SourceType> mimeType = 
			new HashMap<String, SourceType>();
	/**
	 * 图片
	 */
	public static final SourceType pic = new SourceType("1", "图片", "dryer/pic");
	
	/**
	 * 音频
	 */
	public static final SourceType audio = new SourceType("2", "音频", "dryer/audio");
	
	/**
	 * 视频
	 */
	public static final SourceType video = new SourceType("3", "视频", "dryer/video");
	
	/**
	 * 文档
	 */
	public static final SourceType doc = new SourceType("4", "文档", "dryer/doc");
	
	/**
	 * 可执行程序
	 */
	public static final SourceType exe = new SourceType("5", "可执行程序", "dryer/exe");
	
	static {
		
		mimeType.put("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet", doc); 		//xlsx
		mimeType.put("application/vnd.openxmlformats-officedocument.spreadsheetml.template", doc);	 	//xltx
		mimeType.put("application/vnd.openxmlformats-officedocument.presentationml.template", doc); 	//potx
		mimeType.put("application/vnd.openxmlformats-officedocument.presentationml.slideshow", doc);	//ppsx
		mimeType.put("application/vnd.openxmlformats-officedocument.presentationml.presentation", doc);	//pptx
		mimeType.put("application/vnd.openxmlformats-officedocument.presentationml.slide", doc);		//sldx
		mimeType.put("application/vnd.openxmlformats-officedocument.wordprocessingml.document", doc);	//docx
		mimeType.put("application/vnd.openxmlformats-officedocument.wordprocessingml.template", doc);	//dotx
		mimeType.put("application/vnd.ms-excel.addin.macroEnabled.12", doc);							//xlam
		mimeType.put("application/vnd.ms-excel.sheet.binary.macroEnabled.12", doc);						//xlsb
		mimeType.put("application/vnd.android.package-archive", exe);									//apk
		mimeType.put("application/mac-binhex40", exe); 													//hqx
		mimeType.put("application/mac-compactpro", exe);												//cpt
		mimeType.put("application/msword", doc);														//doc
		mimeType.put("application/ogg", audio);															//ogg
		mimeType.put("application/pdf", doc);															//pdf
		mimeType.put("text/rtf", doc);																	//rtf
		mimeType.put("application/vnd.mif", doc);														//mif
		mimeType.put("application/vnd.ms-excel", doc);													//xls
		mimeType.put("application/vnd.ms-powerpoint", doc); 											//ppt
		mimeType.put("application/vnd.oasis.opendocument.chart", doc); 									//odc
		mimeType.put("application/vnd.oasis.opendocument.database", doc); 								//odb
		mimeType.put("application/vnd.oasis.opendocument.formula", doc);								//odf
		mimeType.put("application/vnd.oasis.opendocument.graphics", doc); 								//odg
		mimeType.put("application/vnd.oasis.opendocument.graphics-template", doc);						//otg
		mimeType.put("application/vnd.oasis.opendocument.image", doc); 									//odi
		mimeType.put("application/vnd.oasis.opendocument.presentation", doc); 							//odp
		mimeType.put("application/vnd.oasis.opendocument.presentation-template", doc); 					//otp    
		mimeType.put("application/vnd.oasis.opendocument.spreadsheet", doc);							//ods    
		mimeType.put("application/vnd.oasis.opendocument.spreadsheet-template", doc);					//ots    
		mimeType.put("application/vnd.oasis.opendocument.text", doc); 									//odt    
		mimeType.put("application/vnd.oasis.opendocument.text-master", doc);							//odm    
		mimeType.put("application/vnd.oasis.opendocument.text-template", doc);							//ott    
		mimeType.put("application/vnd.oasis.opendocument.text-web", doc);								//oth    
		mimeType.put("application/vnd.sun.xml.writer", doc);											//sxw    
		mimeType.put("application/vnd.sun.xml.writer.template", doc);									//stw    
		mimeType.put("application/vnd.sun.xml.calc", doc);												//sxc    
		mimeType.put("application/vnd.sun.xml.calc.template", doc);										//stc    
		mimeType.put("application/vnd.sun.xml.draw", doc);												//sxd    
		mimeType.put("application/vnd.sun.xml.draw.template", doc);										//std    
		mimeType.put("application/vnd.sun.xml.impress", doc);											//sxi    
		mimeType.put("application/vnd.sun.xml.impress.template", doc);									//sti    
		mimeType.put("application/vnd.sun.xml.writer.global", doc);										//sxg    
		mimeType.put("application/vnd.sun.xml.math", doc);												//sxm    
		mimeType.put("application/vnd.symbian.install", doc);											//sis    
		mimeType.put("application/vnd.wap.wbxml", doc);													//wbxml    
		mimeType.put("application/vnd.wap.wmlc", doc);													//wmlc    
		mimeType.put("application/vnd.wap.wmlscriptc", doc);											//wmlsc    
		mimeType.put("application/x-bcpio", doc);														//bcpio    
		mimeType.put("application/x-bittorrent", doc);													//torrent    
		mimeType.put("application/x-bzip2", doc);														//bz2    
		mimeType.put("application/x-cdlink", doc);														//vcd    
		mimeType.put("application/x-chess-pgn", doc);													//pgn    
		mimeType.put("application/x-cpio", doc);														//cpio    
		mimeType.put("application/x-csh", doc);															//csh    
		mimeType.put("application/x-dvi", doc);															//dvi    
		mimeType.put("application/x-futuresplash", doc);												//spl    
		mimeType.put("application/x-gtar", doc);														//gtar    
		mimeType.put("application/x-hdf", doc);															//hdf    
		mimeType.put("application/x-java-archive", doc);												//jar    
		mimeType.put("application/x-java-jnlp-file", doc);												//jnlp    
		mimeType.put("application/x-javascript", doc);													//js    
		mimeType.put("application/x-kspread", doc);														//ksp    
		mimeType.put("application/x-kchart", doc);														//chrt    
		mimeType.put("application/x-killustrator", doc);												//kil    
		mimeType.put("application/x-latex", doc);														//latex    
		mimeType.put("application/x-rpm", doc);															//rpm    
		mimeType.put("application/x-sh", doc);															//sh    
		mimeType.put("application/x-shar", doc);														//shar    
		mimeType.put("application/x-shockwave-flash", doc);												//swf    
		mimeType.put("application/x-stuffit", doc);														//sit    
		mimeType.put("application/x-sv4cpio", doc);														//sv4cpio    
		mimeType.put("application/x-sv4crc", doc);														//sv4crc    
		mimeType.put("application/x-tar", doc);															//tar    
		mimeType.put("application/x-tcl", doc);															//tcl    
		mimeType.put("application/x-tex", doc);															//tex    
		mimeType.put("application/x-troff-man", doc);													//man    
		mimeType.put("application/x-troff-me", doc);													//me    
		mimeType.put("application/x-troff-ms", doc);													//ms    
		mimeType.put("application/x-ustar", doc);														//ustar    
		mimeType.put("application/x-wais-source", doc);													//src    
		mimeType.put("application/zip", doc);															//zip    
		mimeType.put("audio/x-mpegurl", audio);															//m3u    
		mimeType.put("audio/x-pn-realaudio", audio);														//ra    
		mimeType.put("audio/x-wav", audio);																//wav    
		mimeType.put("audio/x-ms-wma", audio);															//wma    
		mimeType.put("audio/x-ms-wax", audio);															//wax    
		mimeType.put("chemical/x-pdb", doc);															//pdb    
		mimeType.put("chemical/x-xyz", doc);															//xyz    
		mimeType.put("image/bmp", pic);																	//bmp    
		mimeType.put("image/gif", pic);																	//gif    
		mimeType.put("image/ief", pic);																	//ief    
		mimeType.put("image/pngv", pic);																//png    
		mimeType.put("image/vnd.wap.wbmp", pic);														//wbmp    
		mimeType.put("image/x-cmu-raster", pic);														//ras    
		mimeType.put("image/x-portable-anymap", pic);													//pnm    
		mimeType.put("image/x-portable-bitmap", pic);													//pbm    
		mimeType.put("image/x-portable-graymap", pic);													//pgm    
		mimeType.put("image/x-portable-pixmap", pic);													//ppm    
		mimeType.put("image/x-rgb", pic);																//rgb 
		mimeType.put("image/png", pic);																//rgb 
		mimeType.put("image/x-xbitmap", pic);															//xbm    
		mimeType.put("image/x-xpixmap", pic);															//xpm    
		mimeType.put("image/x-xwindowdump", pic);
		mimeType.put("image/*", pic);	//xwd
		mimeType.put("text/css", doc);																	//css    
		mimeType.put("text/richtext", doc);																//rtx    
		mimeType.put("text/tab-separated-values", doc);													//tsv    
		mimeType.put("text/vnd.sun.j2me.app-descriptor", doc);											//jad    
		mimeType.put("text/vnd.wap.wml", doc);															//wml    
		mimeType.put("text/vnd.wap.wmlscript", doc);													//wmls    
		mimeType.put("text/x-setext", doc);																//etx    
		mimeType.put("video/vnd.mpegurl", video);															//mxu    
		mimeType.put("video/x-flv", video);																//flv    
		mimeType.put("video/avi", video); 															// video/avi
		mimeType.put("video/x-ms-wm", video);																//wm    
		mimeType.put("video/x-ms-wmv", video);															//wmv    
		mimeType.put("video/x-ms-wmx", video);															//wmx    
		mimeType.put("video/x-ms-wvx", video);															//wvx    
		mimeType.put("video/x-msvideo", video);															//avi    
		mimeType.put("video/x-sgi-movie", video);															//movie    	
		mimeType.put("x-conference/x-cooltalk", doc);													//ice    
		mimeType.put("video/3gpp", video);																//3gp    
		mimeType.put("application/postscript", doc);													//ai    
		mimeType.put("audio/x-aiff", audio);																//aif    
		mimeType.put("audio/x-aiff", audio);																//aifc    
		mimeType.put("audio/x-aiff", audio);																//aiff    
		mimeType.put("text/plain", doc);																//asc    
		mimeType.put("application/atom+xml", doc);														//atom    
		mimeType.put("audio/basic", audio);																//au    
		mimeType.put("application/octet-stream", doc);													//bin    
		mimeType.put("application/x-netcdf", doc);														//cdf    
		mimeType.put("image/cgm", pic);																	//cgm    
		mimeType.put("application/octet-stream", doc);													//class    
		mimeType.put("application/x-director", doc);													//dcr    
		mimeType.put("video/x-dv", video);																//dif    
		mimeType.put("application/x-director", doc);													//dir    
		mimeType.put("image/vnd.djvu", pic);															//djv    
		mimeType.put("image/vnd.djvu", pic);															//djvu    
		mimeType.put("application/octet-stream", doc);													//dll    
		mimeType.put("application/octet-stream", doc);													//dmg    
		mimeType.put("application/octet-stream", doc);													//dms    
		mimeType.put("application/xml-dtd", doc);														//dtd    
		mimeType.put("video/x-dv", video);																//dv    
		mimeType.put("application/x-director", doc);													//dxr    
		mimeType.put("application/postscript", doc);													//eps    
		mimeType.put("application/octet-stream", doc);													//exe    
		mimeType.put("application/andrew-inset", doc);													//ez    
		mimeType.put("application/srgs", doc);															//gram    
		mimeType.put("application/srgs+xml", doc);														//grxml    
		mimeType.put("application/x-gzip", doc);														//gz    
		mimeType.put("application/x-gzip", doc);														//tgz   
		mimeType.put("text/html", doc);																	//htm    
		mimeType.put("text/html", doc);																	//html    
		mimeType.put("image/x-icon", pic);																//ico    
		mimeType.put("text/calendar", doc);																//ics    
		mimeType.put("text/calendar", doc);																//ifb    
		mimeType.put("model/iges", doc);																//iges    
		mimeType.put("model/iges", doc);																//igs    
		mimeType.put("image/jp2", pic);																	//jp2    
		mimeType.put("image/jpeg", pic);																//jpe    
		mimeType.put("image/jpeg", pic);																//jpeg    
		mimeType.put("image/jpeg", pic);																//jpg    
		mimeType.put("audio/midi", audio);																//kar    
		mimeType.put("application/octet-stream", doc);													//lha    
		mimeType.put("application/octet-stream", doc);													//lzh    
		mimeType.put("audio/mp4a-latm", audio);															//m4a    
		mimeType.put("audio/mp4a-latm", audio);															//m4p    
		mimeType.put("video/vnd.mpegurl", video);															//m4u    
		mimeType.put("video/x-m4v", video);																//m4v    
		mimeType.put("image/x-macpaint", pic);															//mac    
		mimeType.put("application/mathml+xml", doc);													//mathml    
		mimeType.put("model/mesh", doc);																//mesh    
		mimeType.put("audio/midi", audio);																//mid    
		mimeType.put("audio/midi", audio);																//midi    
		mimeType.put("video/quicktime", video);															//mov    
		mimeType.put("audio/mpeg", audio);																//mp2    
		mimeType.put("audio/mpeg", audio);																//mp3    
		mimeType.put("audio/mp3", audio);
		mimeType.put("video/mp4", video);																	//mp4    
		mimeType.put("video/mpeg", video);																//mpe    
		mimeType.put("video/mpeg", video);																//mpeg    
		mimeType.put("video/mpeg", video);																//mpg    
		mimeType.put("audio/mpeg", audio);																//mpga    
		mimeType.put("model/mesh", doc);																//msh    
		mimeType.put("application/x-netcdf", doc);														//nc    
		mimeType.put("application/oda", doc);															//oda    
		mimeType.put("video/ogv", video);																	//ogv    
		mimeType.put("image/pict", pic);																//pct    
		mimeType.put("image/pict", pic);																//pic    
		mimeType.put("image/pict", pic);																//pict    
		mimeType.put("image/x-macpaint", pic);															//pnt    
		mimeType.put("image/x-macpaint", pic);															//pntg    
		mimeType.put("application/postscript", doc);													//ps    
		mimeType.put("video/quicktime", video);															//qt    
		mimeType.put("image/x-quicktime", pic);															//qti    
		mimeType.put("image/x-quicktime", pic);															//qtif    
		mimeType.put("audio/x-pn-realaudio", audio);														//ram    
		mimeType.put("application/rdf+xml", doc);														//rdf    
		mimeType.put("application/vnd.rn-realmedia", doc);												//rm    
		mimeType.put("application/x-troff", doc);														//roff    
		mimeType.put("text/sgml", doc);																	//sgm    
		mimeType.put("text/sgml", doc);																	//sgml    
		mimeType.put("model/mesh", doc);																//silo    
		mimeType.put("application/x-koan", doc);														//skd    
		mimeType.put("application/x-koan", doc);														//skm    
		mimeType.put("application/x-koan", doc);														//skp    
		mimeType.put("application/x-koan", doc);														//skt    
		mimeType.put("application/smil", doc);															//smi    
		mimeType.put("application/smil", doc);															//smil    
		mimeType.put("audio/basic", audio);																//snd    
		mimeType.put("application/octet-stream", doc);													//so    
		mimeType.put("image/svg+xml", pic);																//svg    
		mimeType.put("application/x-troff", doc);														//t    
		mimeType.put("application/x-texinfo", doc);														//texi    
		mimeType.put("application/x-texinfo", doc);														//texinfo    
		mimeType.put("image/tiff", pic);																//tif    
		mimeType.put("image/tiff", pic);																//tiff    
		mimeType.put("application/x-troff", doc);														//tr    
		mimeType.put("text/plain", doc);																//txt    
		mimeType.put("model/vrml", doc);																//vrml    
		mimeType.put("application/voicexml+xml", doc);													//vxml    
		mimeType.put("video/webm", video);																//webm    
		mimeType.put("model/vrml", doc);																//wrl    
		mimeType.put("application/xhtml+xml", doc);														//xht    
		mimeType.put("application/xhtml+xml", doc);														//xhtml    
		mimeType.put("application/xml", doc);															//xml    
		mimeType.put("application/xml", doc);															//xsl    
		mimeType.put("application/xslt+xml", doc);														//xslt    
		mimeType.put("application/vnd.mozilla.xul+xml", doc);											//xul    
		mimeType.put("image/webp", pic);																//webp 
		mimeType.put("audio/vnd.dlna.adts", audio);
		
	}

	/**
	 * 获取资源类型
	 */
	public static SourceType getSourceType(String type) {
		return mimeType.get(type);
	}
	
	/**
	 * 获取url文件名称
	 */
	public static String getUrlFilePath(String url) {
		
		int index = url.lastIndexOf('/');
		
		return url.substring(index + 1);
		
	}
	
	/**
	 * 获取资源文件路径
	 */
	public static String getFilePath(Resource resource) {
		
		String type = "";
		//System.out.println(resource.toString());
		switch (resource.getResourceTypeId()) {
		case 1:
			type = "dryer/pic";
			break;
		case 2:
			type = "dryer/audio";
			break;
		case 3:
			type = "dryer/video";
			break;
		case 4:
			type = "dryer/doc";
			break;
		case 5:
			type = "dryer/exe";
			break;
		default:
			break;
		}
		//System.out.println("type = " + type);
		int index = resource.getResourcePath().indexOf(type);
		//System.out.println("index = " + index);
		return resource.getResourcePath().substring(index);
		
	}
	
}
