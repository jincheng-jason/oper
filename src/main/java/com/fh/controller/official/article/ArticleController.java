package com.fh.controller.official.article;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.service.official.account.AccountService;
import com.fh.service.official.article.ArticleService;
import com.fh.util.*;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.PrintWriter;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 类名称：ArticleController
 * 创建人：FH 
 * 创建时间：2015-03-27
 */
@Controller
@RequestMapping(value="/article")
public class ArticleController extends BaseController {
	
	@Resource(name="articleService")
	private ArticleService articleService;
	
	@Resource(name="accountService")
	private AccountService accountService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Article");
		
		pd = this.getPageData();
        pd.put("PUB_DATE",new Date());
        pd.put("STATUS",0);
        pd.put("CATEGORY_ID",0);

        String imgUrl = pd.getString("IMG_LINK");
//        String ioableUrl = imgUrl.split("\\?")[0] + ".jpeg";
        String largeName = (UUID.randomUUID().toString() + ".jpg").replaceAll("-","");
        String smallName = (UUID.randomUUID().toString() + ".jpg").replaceAll("-","");

        String savePath = "/usr/local/img/";
//        Thumbnails.of(new URL(ioableUrl)).forceSize(702, 308).outputQuality(1.0).toFile(savePath+largeName);
//        Thumbnails.of(new URL(ioableUrl)).forceSize(130, 130).outputQuality(1.0).toFile(savePath + smallName);
        //先按比例缩放存到内存
        BufferedImage imageBuff = Thumbnails.of(new URL(imgUrl)).width(702).asBufferedImage();
        //后从中心剪裁
        Thumbnails.of(imageBuff).size(702, 308).sourceRegion(Positions.CENTER, 702, 308).outputQuality(1.0f).toFile(savePath+largeName);

        //先按比例缩放存到内存
        BufferedImage image1 = Thumbnails.of(new URL(imgUrl)).height(130).asBufferedImage();
        //后从中心剪裁
        Thumbnails.of(image1).size(130, 130).sourceRegion(Positions.CENTER, 130, 130).outputQuality(1.0f).toFile(savePath+smallName);

        pd.put("LARGE_IMG", "http://123.56.149.56:8080/img/" + largeName);
        pd.put("SMALL_IMG", "http://123.56.149.56:8080/img/" + smallName);

        String accountName = pd.getString("ACCOUNT_NAME");
        PageData accountPd = accountService.findByName(accountName);
        if (accountPd != null){
            Long id = (Long)accountPd.get("ID");
            String image = accountPd.getString("ORIGIN_IMG");
            pd.put("ACCOUNT_ID",id);
            pd.put("HEAD_IMAGE",image);
        }else{

        }

		articleService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

    @RequestMapping(value="/chief")
    public void chief() throws Exception{

        pd = this.getPageData();
        String isChief = pd.getString("isChief");
        if ("true".equals(isChief)){
            pd.put("flag", 1);
        }else{
            pd.put("flag", 0);
        }
        articleService.chief(pd);
    }

    @RequestMapping(value = "/newsAdd")
    public ModelAndView newsAdd() throws Exception{
        logBefore(logger,"新增News");

        pd = this.getPageData();

        String title = pd.getString("title");
        String content = pd.getString("editorValue");
        String imgLink = "";
        if (content.contains("img src=")){
            Pattern p = Pattern.compile("img src=\"(.*?)\"");
            Matcher m = p.matcher(content);
            if(m.find())
                imgLink = m.group(1);
        }

        //使用模板生成html,返回访问地址
        String url = "";
        Map<String,Object> root = new HashMap<>();
        root.put("title",title);
        root.put("content",content);
        String fileName = get32UUID() +".html";
        String path = this.getRequest().getSession().getServletContext().getRealPath("/html");
        org.springframework.core.io.Resource resource = new ClassPathResource("ftl/html_news.ftl");
        String resourceFilename = resource.getFile().getAbsolutePath();
        System.out.println(resourceFilename);
        TemplateUtil.printFile(resourceFilename, root, path + "/" +  fileName);

        url = "http://123.56.149.56:8089/oper/html/" + fileName;
        //拼装pagedata，存入
        pd.clear();
        pd.put("TITLE", title);
        pd.put("URL",url);
        pd.put("IMG_LINK",imgLink);
        pd.put("PUB_DATE",new Date());
        pd.put("ACCOUNT_ID",0);
        pd.put("CATEGORY_ID",0);
        pd.put("STATUS",0);
        pd.put("HEAD_IMAGE","http://123.56.149.56:8080/img/8mm.png");
        pd.put("ACCOUNT_NAME","8mm");

        String largeName = (UUID.randomUUID().toString() + ".jpg").replaceAll("-","");
        String smallName = (UUID.randomUUID().toString() + ".jpg").replaceAll("-","");

        String savePath = "/usr/local/img/";
        String getPath = imgLink.replace("http://123.56.149.56:8089/oper","/usr/local/server/tomcat/webapps/oper");

//        Thumbnails.of(new File(getPath)).forceSize(702, 308).outputQuality(1.0).toFile(savePath+largeName);
//        Thumbnails.of(new File(getPath)).forceSize(130, 130).outputQuality(1.0).toFile(savePath+smallName);

        //先按比例缩放存到内存
        BufferedImage image = Thumbnails.of(new File(getPath)).width(702).asBufferedImage();
        //后从中心剪裁
        Thumbnails.of(image).size(702, 308).sourceRegion(Positions.CENTER, 702, 308).outputQuality(1.0f).toFile(savePath+largeName);

        //先按比例缩放存到内存
        BufferedImage image1 = Thumbnails.of(new File(getPath)).height(130).asBufferedImage();
        //后从中心剪裁
        Thumbnails.of(image1).size(130, 130).sourceRegion(Positions.CENTER, 130, 130).outputQuality(1.0f).toFile(savePath+smallName);

        pd.put("LARGE_IMG", "http://123.56.149.56:8080/img/" + largeName);
        pd.put("SMALL_IMG", "http://123.56.149.56:8080/img/" + smallName);

        articleService.save(pd);

        mv.addObject("msg","success");
        mv.setViewName("save_result");

        return mv;
    }
	
	/**
	 * 手动同步文章数据
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/update")
	public ModelAndView Update() throws Exception{
		logBefore(logger, "手工同步Article");
		Map map = new HashMap();
		try{
			pd = this.getPageData();
			List<PageData>	varList = accountService.listAll(pd);	//列出Account列表
			for (PageData pageData : varList) {
				String openid = pageData.getString("OPENID");
				List<PageData> contents = Spider.parseContent(openid);
				articleService.batchSave(contents);
			}
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Article");
		
		try{
			pd = this.getPageData();
			articleService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Article");
		
		pd = this.getPageData();
		articleService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

    /**
     * 未上线文章列表
     */
    @RequestMapping(value = "/offline")
    public ModelAndView offline(Page page){
        logBefore(logger,"未上线article");

        try{
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData>	varList = articleService.offline(page);	//列出Article列表
            this.getHC(); //调用权限
            mv.setViewName("official/article/offline_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;

    }
	
	/**
	 * 热门推荐列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Article");
		
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = articleService.list(page);	//列出Article列表
			this.getHC(); //调用权限
			mv.setViewName("official/article/article_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}

    /**
     * 行业动态列表
     */
    @RequestMapping(value="/newsList")
    public ModelAndView newsList(Page page){
        logBefore(logger, "列表Article");

        try{
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData>	varList = articleService.newsList(page);	//列出Article列表
            this.getHC(); //调用权限
            mv.setViewName("official/article/news_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }



    /**
     * 去手动导入页面
     */
    @RequestMapping(value="/goManualAdd")
    public ModelAndView goManualAdd(){
        logBefore(logger, "去手动导入页面");

        pd = this.getPageData();
        try {
            mv.setViewName("official/article/offline_edit");
            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

    /**
     * 去新增文章页面
     */
    @RequestMapping(value="/goOfflineAdd")
    public ModelAndView goOfflineAdd(){
        logBefore(logger, "去新增文章页面");

        pd = this.getPageData();
        try {
            mv.setViewName("official/article/offline_edit");
            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }

	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Article页面");
		
		pd = this.getPageData();
		try {
			mv.setViewName("official/article/article_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}

    @RequestMapping(value = "/goNewsAdd")
    public ModelAndView goNewsAdd(){
        logBefore(logger, "去新增News页面");

        pd = this.getPageData();
        try {
            mv.setViewName("official/article/news_edit");
            mv.addObject("msg", "save");
            mv.addObject("pd", pd);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return mv;
    }


	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Article页面");
		
		pd = this.getPageData();
		try {
			pd = articleService.findById(pd);	//根据ID读取
			mv.setViewName("official/article/article_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Article");
		Map map = new HashMap();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				articleService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}

    //批量上线下线
    @RequestMapping(value="/onlineAll")
    @ResponseBody
    public Object onlineAll(){
        logBefore(logger, "批量上线下线Article");
        Map map = new HashMap();
        try {
            pd = this.getPageData();
            List<PageData> pdList = new ArrayList<PageData>();
            String DATA_IDS = pd.getString("DATA_IDS");
            Integer categoryId = Integer.parseInt(pd.getString("category"));
            Integer status = 0;
            if (categoryId != 0)
                status = 1;
            PageData param = new PageData();

            if(null != DATA_IDS && !"".equals(DATA_IDS)){
                String ArrayDATA_IDS[] = DATA_IDS.split(",");
                param.put("DATA_IDS",ArrayDATA_IDS);
                param.put("CATEGORY_ID",categoryId);
                param.put("STATUS",status);
                articleService.onlineAll(param);
                pd.put("msg", "ok");
            }else{
                pd.put("msg", "no");
            }
            pdList.add(pd);
            map.put("list", pdList);
        } catch (Exception e) {
            logger.error(e.toString(), e);
        } finally {
            logAfter(logger);
        }
        return AppUtil.returnObject(pd, map);

    }
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Article到excel");
		ModelAndView mv = new ModelAndView();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("标题");	//1
			titles.add("文章地址");	//2
			titles.add("图片");	//3
			titles.add("标题图片");	//4
			titles.add("来源");	//5
			titles.add("概要");	//6
			titles.add("发布日期");	//7
			titles.add("最后修改");	//8
			dataMap.put("titles", titles);
			List<PageData> varOList = articleService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TITLE"));	//1
				vpd.put("var2", varOList.get(i).getString("URL"));	//2
				vpd.put("var3", varOList.get(i).getString("IMGLINK"));	//3
				vpd.put("var4", varOList.get(i).getString("HEADIMAGE"));	//4
				vpd.put("var5", varOList.get(i).getString("SOURCENAME"));	//5
				vpd.put("var6", varOList.get(i).getString("CONTENT168"));	//6
				vpd.put("var7", varOList.get(i).getString("DATE"));	//7
				vpd.put("var8", varOList.get(i).getString("LASTMODIFIED"));	//8
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	public void getHC(){
		HttpSession session = this.getRequest().getSession();
		Map<String, String> map = (Map<String, String>)session.getAttribute(Const.SESSION_QX);
		mv.addObject(Const.SESSION_QX,map);	//按钮权限
		List<Menu> menuList = (List)session.getAttribute(Const.SESSION_menuList);
		mv.addObject(Const.SESSION_menuList, menuList);//菜单权限
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
