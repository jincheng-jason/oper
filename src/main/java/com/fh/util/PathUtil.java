package com.fh.util;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;

/**
 * 路径工具类
 * 
 * @author
 * 
 */
public class PathUtil {

	/**
	 * 图片访问路径
	 * 
	 * @param pathType
	 *            图片类型 visit-访问；save-保存
	 * @param pathCategory
	 *            图片类别，如：话题图片-topic、话题回复图片-reply、商家图片
	 * @return
	 */
	public static String getPicturePath(String pathType, String pathCategory) {
		String strResult = "";
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		StringBuffer strBuf = new StringBuffer();
		if ("visit".equals(pathType)) {
		} else if ("save".equals(pathType)) {
			String projectPath = PublicUtil.getPorjectPath().replaceAll("\\\\",
					"/");
			projectPath = splitString(projectPath, "bin/");

			strBuf.append(projectPath);
			strBuf.append("webapps/ROOT/");
		}

		strResult = strBuf.toString();

		return strResult;
	}

	private static String splitString(String str, String param) {
		String result = str;

		if (str.contains(param)) {
			int start = str.indexOf(param);
			result = str.substring(0, start);
		}

		return result;
	}

    /**
     * @描述 Maven项目中，获取项目路径
     * @时间 2013-7-26 下午5:13:02
     * @return 项目路径。如：D:\kuaipan\springmvc\
     */
    public static String getMavenWebProjectPath() {
        Resource resource = new ClassPathResource("./");
        String filePath = "";
        try {
            filePath = resource.getFile().getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        filePath = filePath.substring(0, filePath.indexOf("target"));
        return filePath;
    }
	
	/*
	 * 获取classpath1
	 */
	public static String getClasspath(){
//		return "/" + (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		String classPath = PathUtil.class.getClassLoader().getResource("/").getPath();
		String filePath  = "";
		if("/".equals(File.separator)){   
			filePath  = classPath.substring(0,classPath.indexOf("/WEB-INF/classes"));
			filePath = filePath.replace("\\", "/");
		}
		filePath = filePath.replaceAll("file:/", "");
		filePath = filePath.replaceAll("%20", " ");
		return filePath+"/";
	}
	
	/*
	 * 获取classpath2
	 */
	public static String getClassResources(){
//		return (String.valueOf(Thread.currentThread().getContextClassLoader().getResource(""))).replaceAll("file:/", "").replaceAll("%20", " ").trim();	
		return PathUtil.class.getClassLoader().getResource("/").getPath().replaceAll("file:/", "").replaceAll("%20", " ")+"/";
	}
	
	public static String PathAddress() {
		String strResult = "";

		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();

		StringBuffer strBuf = new StringBuffer();

		strBuf.append(request.getScheme() + "://");
		strBuf.append(request.getServerName() + ":");
		strBuf.append(request.getServerPort() + "");

		strBuf.append(request.getContextPath() + "/");

		strResult = strBuf.toString();// +"ss/";//加入项目的名称

		return strResult;
	}
	
	
}
