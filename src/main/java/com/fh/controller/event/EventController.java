package com.fh.controller.event;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.service.event.EventService;
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * 类名称：EventController
 * 创建人：FH 
 * 创建时间：2015-07-16
 */
@Controller
@RequestMapping(value="/event")
public class EventController extends BaseController {
	
	@Resource(name="eventService")
	private EventService eventService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Event");
		
		pd = this.getPageData();

        String title = pd.getString("title");
        String beginAt = pd.getString("begin_at");
        String endAt = pd.getString("end_at");
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
        TemplateUtil.printFile(resourceFilename, root, path + "/" + fileName);

        url = "http://123.56.149.56:8089/oper/html/" + fileName;

        String largeName = (UUID.randomUUID().toString() + ".jpg").replaceAll("-","");
        String smallName = (UUID.randomUUID().toString() + ".jpg").replaceAll("-","");

        String savePath = "/usr/local/img/";
        String getPath = imgLink.replace("http://123.56.149.56:8089/oper","/usr/local/server/tomcat/webapps/oper");

        //先按比例缩放存到内存
        BufferedImage image = Thumbnails.of(new File(getPath)).width(702).asBufferedImage();
        //后从中心剪裁
        Thumbnails.of(image).size(702, 308).sourceRegion(Positions.CENTER, 702, 308).outputQuality(1.0f).toFile(savePath+largeName);

        //先按比例缩放存到内存
        BufferedImage image1 = Thumbnails.of(new File(getPath)).height(130).asBufferedImage();
        //后从中心剪裁
        Thumbnails.of(image1).size(130, 130).sourceRegion(Positions.CENTER, 130, 130).outputQuality(1.0f).toFile(savePath+smallName);

        pd.clear();
        pd.put("title", title);
        pd.put("url",url);
        pd.put("begin_at",beginAt);
        pd.put("end_at",endAt);
        pd.put("img",imgLink);
        pd.put("large_img", "http://123.56.149.56:8080/img/" + largeName);
        pd.put("small_img", "http://123.56.149.56:8080/img/" + smallName);
        pd.put("status", 0);
        pd.put("apply_count", 0);

		eventService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
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
            Integer status = Integer.parseInt(pd.getString("status"));
            PageData param = new PageData();

            if(null != DATA_IDS && !"".equals(DATA_IDS)){
                String ArrayDATA_IDS[] = DATA_IDS.split(",");
                param.put("DATA_IDS",ArrayDATA_IDS);
                param.put("status",status);
                eventService.onlineAll(param);
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
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Event");
		
		try{
			pd = this.getPageData();
			eventService.delete(pd);
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
		logBefore(logger, "修改Event");
		
		pd = this.getPageData();
		eventService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}


    @RequestMapping(value="/updateRemark")
    public ModelAndView updateRemark() throws Exception{
        logBefore(logger, "修改备注");

        pd = this.getPageData();
        eventService.updateRemark(pd);
        mv.addObject("msg","success");
        mv.setViewName("save_result");
        return mv;
    }
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Event");
		
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = eventService.list(page);	//列出Event列表
			this.getHC(); //调用权限
			mv.setViewName("event/event_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}

    @RequestMapping(value = "/apply")
    public ModelAndView findApply(Page page){
        logBefore(logger, "报名列表");

        try{
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData>	varList = eventService.findApply(page);	//列出Event列表
            this.getHC(); //调用权限
            mv.setViewName("event/apply_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }

    @RequestMapping(value="/lasting")
    public ModelAndView lasting(Page page){
        logBefore(logger, "列表Event");

        try{
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData>	varList = eventService.listLasting(page);	//列出Event列表
            this.getHC(); //调用权限
            mv.setViewName("event/lasting_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }

    @RequestMapping(value="/over")
    public ModelAndView over(Page page){
        logBefore(logger, "列表Event");

        try{
            pd = this.getPageData();
            page.setPd(pd);
            List<PageData>	varList = eventService.listOver(page);	//列出Event列表
            this.getHC(); //调用权限
            mv.setViewName("event/over_list");
            mv.addObject("varList", varList);
            mv.addObject("pd", pd);
        } catch(Exception e){
            logger.error(e.toString(), e);
        }
        return mv;
    }


    @RequestMapping(value="/goApplyEdit")
    public ModelAndView goApplyEdit(){
        logBefore(logger, "去修改Apply页面");

        pd = this.getPageData();
        try {
            mv.setViewName("event/apply_edit");
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
		logBefore(logger, "去新增Event页面");
		
		pd = this.getPageData();
		try {
			mv.setViewName("event/event_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}

    @RequestMapping(value = "/goEventAdd")
    public ModelAndView goNewsAdd(){
        logBefore(logger, "去新增活动页面");

        pd = this.getPageData();
        try {
            mv.setViewName("event/event_add");
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
		logBefore(logger, "去修改Event页面");
		
		pd = this.getPageData();
		try {
			pd = eventService.findById(pd);	//根据ID读取
			mv.setViewName("event/event_edit");
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
		logBefore(logger, "批量删除Event");
		Map map = new HashMap();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				eventService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Event到excel");
		ModelAndView mv = new ModelAndView();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("标题");	//1
			titles.add("详情地址");	//2
			titles.add("图片");	//3
			titles.add("大题图");	//4
			titles.add("小题图");	//5
			titles.add("状态");	//6
			titles.add("开始时间");	//7
			titles.add("结束时间");	//8
			titles.add("申请人数");	//9
			dataMap.put("titles", titles);
			List<PageData> varOList = eventService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("title"));	//1
				vpd.put("var2", varOList.get(i).getString("url"));	//2
				vpd.put("var3", varOList.get(i).getString("img"));	//3
				vpd.put("var4", varOList.get(i).getString("large_img"));	//4
				vpd.put("var5", varOList.get(i).getString("small_img"));	//5
				vpd.put("var6", varOList.get(i).getString("status"));	//6
				vpd.put("var7", varOList.get(i).getString("begin_at"));	//7
				vpd.put("var8", varOList.get(i).getString("end_at"));	//8
				vpd.put("var9", varOList.get(i).getString("apply_count"));	//9
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
