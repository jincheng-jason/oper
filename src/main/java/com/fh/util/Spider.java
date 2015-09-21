package com.fh.util;



import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.jsoup.Jsoup;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





public class Spider {

	/**
	 * 根据公众号名称获取公众号信息
	 * @param
	 * @return
	 */
	public static PageData getAccount(PageData pd){
//		PageData pd = new PageData();
		String openid = "";
		String name = "";
		String account = "";
		String info = "";
		String QRCodeImg = "";
		String accountName = pd.getString("ACCOUNT_NAME");
//		try {
//			name = URLEncoder.encode(accountName, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		String strurl = "http://weixin.sogou.com/weixin?type=1&query=" + name;
//		String buf = getResponse(strurl);
//		System.out.println(buf);
		String buf = parseAccount(accountName);
		//匹配openid
		Pattern p = Pattern.compile("openid=(.*?)\"");
		Matcher m = p.matcher(buf);
		if(m.find())
			openid = m.group(1);
		
		//匹配微信号
		
		Pattern p1 = Pattern.compile("<span>微信号：(.*?)</span>");
		Matcher m1 = p1.matcher(buf);
		
		if(m1.find())
			account = m1.group(1);
		System.out.println(account);
		
		//匹配功能介绍
		
		Pattern p2 = Pattern.compile("功能介绍：</span><spanclass=\"sp-txt\">(.*?)</span>");
		Matcher m2 = p2.matcher(buf);
		if(m2.find())
			info = m2.group(1);
		info = info.replaceAll("<em><!--red_beg-->", "").replaceAll("<!--red_end--></em>", "");
		System.out.println(info);
		
		//匹配二维码
		
		Pattern p3 = Pattern.compile("alt=\"\"src=\"(.*?)\"><spanclass=\"_phoneimg\">");
		Matcher m3 = p3.matcher(buf);
		if(m3.find())
			QRCodeImg = m3.group(1);
//		System.out.println(QRCodeImg);
		
		pd.put("OPEN_ID", openid);	//1
//		pd.put("ACCOUNTNAME", accountName);	//2
		pd.put("ACCOUNT", account);	//3
		pd.put("INFO", info);	//4
		pd.put("QRIMG", QRCodeImg);	//5
		
		return pd;
	}
	
	/**
	 * 筛选与公众号名称匹配的段落
	 * @param accountName
	 * @return
	 */
	public static String parseAccount(String accountName){
		String result = "";
		org.jsoup.nodes.Document doc;
		try {
			String name = URLEncoder.encode(accountName, "UTF-8");
			String strurl = "http://weixin.sogou.com/weixin?type=1&query=" + name;
			doc = Jsoup.connect(strurl).get();
			Elements divs = doc.getElementsByAttributeValue("class", "wx-rb bg-blue wx-rb_v1 _item");
			for (org.jsoup.nodes.Element element : divs) {
//				org.jsoup.nodes.Element txtElement = element.getElementsByAttribute("").first();
				org.jsoup.nodes.Element nameElement = element.select("h3").first();
//				System.out.println(nameElement.html().replace("\n", ""));
				String realName = "<em> <!--red_beg-->" + accountName + " <!--red_end--></em>";
				if (realName.equals(nameElement.html().replace("\n", ""))) {
//					String href = element.attr("href");
//					System.out.println(href);
					result = replaceBlank(element.toString());
					break;
				}else {
					continue;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 根据公众号名称获得openid
	 * @param accountName
	 * @return
	 */
//	public String getOpenid(String accountName){
//		String openid = "";
//		String name = "";
//		try {
//			name = URLEncoder.encode(accountName, "UTF-8");
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		String strurl = "http://weixin.sogou.com/weixin?type=1&query=" + name;
//		String buf = getResponse(strurl);
////		System.out.println(buf);
//		//匹配openid
//		Pattern p = Pattern.compile("openid=(.*?)\"");
//		Matcher m = p.matcher(buf);
//		if(m.find())
//			openid = m.group(1);
//		
//		//匹配微信号
//		String account = "";
//		Pattern p1 = Pattern.compile("<span>微信号：(.*?)</span>");
//		Matcher m1 = p1.matcher(buf);
//		
//		if(m1.find())
//			account = m1.group(1);
//		System.out.println(account);
//		
//		//匹配功能介绍
//		String info = "";
//		Pattern p2 = Pattern.compile("功能介绍：</span><span class=\"sp-txt\">(.*?)</span>");
//		Matcher m2 = p2.matcher(buf);
//		if(m2.find())
//			info = m2.group(1);
//		info = info.replaceAll("<em><!--red_beg-->", "").replaceAll("<!--red_end--></em>", "");
//		System.out.println(info);
//		
//		//匹配二维码
//		String QRCodeImg = "";
//		Pattern p3 = Pattern.compile("alt=\"\" src=\"(.*?)\"><span class=\"_phoneimg\">");
//		Matcher m3 = p3.matcher(buf);
//		if(m3.find())
//			QRCodeImg = m3.group(1);
//		System.out.println(QRCodeImg);
//		
////		System.out.println(openid);
//		return openid;
//	}
	
	/**
	 * 根据openid解析公众号文章
	 * @param openid
	 * @return
	 */
	public static List<PageData> parseContent(String openid){
		String result = getContent(openid);
		List<PageData> contents = getArticle(result);
		return contents;
	}
	
	/**
	 * 根据openid获得公众号明细
	 * @param openid
	 * @return
	 */
	public static String getContent(String openid){

        String url = "http://weixin.sogou.com/gzh?openid=" + openid;

		String strurl = "http://weixin.sogou.com/gzhjs?cb=sogou.weixin.gzhcb&openid=" + openid;
		String result = getResponse(url);
		Pattern p = Pattern.compile("items\":\\[\"(.*?)\"]");
		Matcher m = p.matcher(result);
		if(m.find())
			result = m.group(1);
		result = result.replaceAll("\\\\", "");
		
		return result;
	}
	
	/**
	 * 解析xml
	 * @param content
	 * @return
	 */
	public static List<PageData> getArticle(String content){
		List<PageData> pds = new ArrayList<PageData>();
		try {
			String[] docs =content.split("\",\"");
			for (int i = 0; i < docs.length; i++) {
				PageData pd = new PageData();
				Document doc = DocumentHelper.parseText(docs[i]);
				pd.put("TITLE", doc.selectSingleNode("//display/title1").getText());
				pd.put("URL", doc.selectSingleNode("//display/url").getText());
				pd.put("IMG_LINK", doc.selectSingleNode("//display/imglink").getText());
				pd.put("HEAD_IMAGE", doc.selectSingleNode("//display/headimage").getText());
				pd.put("SOURCE_NAME", doc.selectSingleNode("//display/sourcename").getText());
				pd.put("CONTENT168", doc.selectSingleNode("//display/content168").getText());
				pd.put("DATE", doc.selectSingleNode("//display/date").getText());
				pd.put("LAST_MODIFIED", doc.selectSingleNode("//display/lastModified").getText());
				pd.put("ARTICLE_ID", UuidUtil.get32UUID());	//主键
				pds.add(pd);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pds;
	}
	
	
	public static String getResponse(String strurl){
		String result = "";
		try {
			URL url = new URL(strurl);
			HttpURLConnection httpConn = (HttpURLConnection) url.openConnection(); 
			InputStreamReader input = new InputStreamReader(httpConn.getInputStream(),"utf-8");
			BufferedReader bufReader = new BufferedReader(input);  
			String line = "";  
			StringBuilder contentBuf = new StringBuilder(); 
			while ((line = bufReader.readLine()) != null) {  
		        contentBuf.append(line);  
		    } 
			result = contentBuf.toString(); 
		}catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public static String replaceBlank(String str) {
        String dest = "";
        if (str!=null) {
            Pattern p = Pattern.compile("\\s*|\t|\r|\n");
            Matcher m = p.matcher(str);
            dest = m.replaceAll("");
        }
        return dest;
    }
	
	public static void main(String[] args) {
//		String xmlStr = getContent("oIWsFt1Q7m-4_FL1naINQQ5K2ww4");
//		System.out.println(xmlStr);
//		List<PageData> result = getArticle(xmlStr);
//		for(PageData pd : result){
//			System.out.println(pd);
//		}
//        System.out.println(result);
//		parseAccount("艺术影视");
//		PageData pd = new PageData();
//		pd.put("ACCOUNTNAME", "艺术影视");
//		pd = getAccount(pd);
//		System.out.println(pd);
        parseContent("oIWsFt_2oo3YnlKNJbFytAO_tD4I");
	}
	
}
