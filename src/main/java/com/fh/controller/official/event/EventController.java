//package com.fh.controller.official.event;
//
//import com.fh.controller.base.BaseController;
//import com.fh.entity.Page;
//import com.fh.entity.system.Menu;
//import com.fh.service.official.event.EventService;
//import com.fh.util.*;
//import org.springframework.beans.propertyeditors.CustomDateEditor;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.WebDataBinder;
//import org.springframework.web.bind.annotation.InitBinder;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.annotation.Resource;
//import javax.servlet.http.HttpSession;
//import java.io.PrintWriter;
//import java.text.DateFormat;
//import java.text.SimpleDateFormat;
//import java.util.*;
//
///**
// * Created by lijc on 15/5/18.
// */
//@Controller
//@RequestMapping(value="/official/event")
//public class EventController extends BaseController {
//
//    @Resource(name="eventService")
//    private EventService eventService;
//
//    /**
//     * 新增
//     */
//    @RequestMapping(value="/save")
//    public ModelAndView save() throws Exception{
//        logBefore(logger, "新增event");
//
//        pd = this.getPageData();
////		pd.put("ACCOUNT_ID", this.get32UUID());	//主键
//        int sn = eventService.count() + 1;
//        pd.put("SN", sn);
//        pd = Spider.getAccount(pd);
//        eventService.save(pd);
//        mv.addObject("msg","success");
//        mv.setViewName("save_result");
//        return mv;
//    }
//
//
//    /**
//     * 删除
//     */
//    @RequestMapping(value="/delete")
//    public void delete(PrintWriter out){
//        logBefore(logger, "新增event");
//
//        try{
//            pd = this.getPageData();
//            eventService.delete(pd);
//            out.write("success");
//            out.close();
//        } catch(Exception e){
//            logger.error(e.toString(), e);
//        }
//
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping(value="/edit")
//    public ModelAndView edit() throws Exception{
//        logBefore(logger, "修改event");
//
//        pd = this.getPageData();
//        pd = Spider.getAccount(pd);
//        eventService.edit(pd);
//        mv.addObject("msg","success");
//        mv.setViewName("save_result");
//        return mv;
//    }
//
//    /**
//     * 列表
//     */
//    @RequestMapping(value="/list")
//    public ModelAndView list(Page page){
//        logBefore(logger, "列表Account");
//
//        try{
//            pd = this.getPageData();
//            page.setPd(pd);
//            List<PageData> varList = eventService.list(page);	//列出event列表
//            this.getHC(); //调用权限
//            mv.setViewName("official/event/event_list");
//            mv.addObject("varList", varList);
//            mv.addObject("pd", pd);
//        } catch(Exception e){
//            logger.error(e.toString(), e);
//        }
//        return mv;
//    }
//
//    /**
//     * 去新增页面
//     */
//    @RequestMapping(value="/goAdd")
//    public ModelAndView goAdd(){
//        logBefore(logger, "去新增event页面");
//
//        pd = this.getPageData();
//        try {
//            mv.setViewName("official/event/event_edit");
//            mv.addObject("msg", "save");
//            mv.addObject("pd", pd);
//        } catch (Exception e) {
//            logger.error(e.toString(), e);
//        }
//        return mv;
//    }
//
//    /**
//     * 去修改页面
//     */
//    @RequestMapping(value="/goEdit")
//    public ModelAndView goEdit(){
//        logBefore(logger, "去修改event页面");
//
//        pd = this.getPageData();
//        try {
//            pd = eventService.findById(pd);	//根据ID读取
//            mv.setViewName("official/event/event_edit");
//            mv.addObject("msg", "edit");
//            mv.addObject("pd", pd);
//        } catch (Exception e) {
//            logger.error(e.toString(), e);
//        }
//        return mv;
//    }
//
//    /**
//     * 批量删除
//     */
//    @RequestMapping(value="/deleteAll")
//    @ResponseBody
//    public Object deleteAll() {
//        logBefore(logger, "批量删除event");
//        Map map = new HashMap();
//        try {
//            pd = this.getPageData();
//            List<PageData> pdList = new ArrayList<PageData>();
//            String DATA_IDS = pd.getString("DATA_IDS");
//            if(null != DATA_IDS && !"".equals(DATA_IDS)){
//                String ArrayDATA_IDS[] = DATA_IDS.split(",");
//                eventService.deleteAll(ArrayDATA_IDS);
//                pd.put("msg", "ok");
//            }else{
//                pd.put("msg", "no");
//            }
//            pdList.add(pd);
//            map.put("list", pdList);
//        } catch (Exception e) {
//            logger.error(e.toString(), e);
//        } finally {
//            logAfter(logger);
//        }
//        return AppUtil.returnObject(pd, map);
//    }
//
//    /*
//     * 导出到excel
//     * @return
//     */
//    @RequestMapping(value="/excel")
//    public ModelAndView exportExcel(){
//        logBefore(logger, "导出event到excel");
//        ModelAndView mv = new ModelAndView();
//        pd = this.getPageData();
//        try{
//            Map<String,Object> dataMap = new HashMap<String,Object>();
//            List<String> titles = new ArrayList<String>();
//            titles.add("openid");	//1
//            titles.add("公众号名称");	//2
//            titles.add("微信号");	//3
//            titles.add("功能介绍");	//4
//            titles.add("二维码url");	//5
//            dataMap.put("titles", titles);
//            List<PageData> varOList = eventService.listAll(pd);
//            List<PageData> varList = new ArrayList<PageData>();
//            for(int i=0;i<varOList.size();i++){
//                PageData vpd = new PageData();
//                vpd.put("var1", varOList.get(i).getString("OPENID"));	//1
//                vpd.put("var2", varOList.get(i).getString("ACCOUNTNAME"));	//2
//                vpd.put("var3", varOList.get(i).getString("ACCOUNT"));	//3
//                vpd.put("var4", varOList.get(i).getString("INFO"));	//4
//                vpd.put("var5", varOList.get(i).getString("QRIMG"));	//5
//                varList.add(vpd);
//            }
//            dataMap.put("varList", varList);
//            ObjectExcelView erv = new ObjectExcelView();
//            mv = new ModelAndView(erv,dataMap);
//        } catch(Exception e){
//            logger.error(e.toString(), e);
//        }
//        return mv;
//    }
//
//    /* ===============================权限================================== */
//    public void getHC(){
//        HttpSession session = this.getRequest().getSession();
//        Map<String, String> map = (Map<String, String>)session.getAttribute(Const.SESSION_QX);
//        mv.addObject(Const.SESSION_QX,map);	//按钮权限
//        List<Menu> menuList = (List)session.getAttribute(Const.SESSION_menuList);
//        mv.addObject(Const.SESSION_menuList, menuList);//菜单权限
//    }
//	/* ===============================权限================================== */
//
//    @InitBinder
//    public void initBinder(WebDataBinder binder){
//        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//        binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
//    }
//}
