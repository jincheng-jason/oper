package com.fh.controller.official.account;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.system.Menu;
import com.fh.service.official.account.AccountService;
import com.fh.util.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/** 
 * 类名称：AccountController
 * 创建人：lijc 
 * 创建时间：2015-03-10
 */
@Controller
@RequestMapping(value="/account")
public class AccountController extends BaseController {
	
	@Resource(name="accountService")
	private AccountService accountService;
	
//	/**
//	 * 完善所有公众号信息
//	 */
//	@RequestMapping(value="/complete")
//	public ModelAndView complete() throws Exception{
//		logBefore(logger, "完善Account");
//		pd = this.getPageData();
//		List<PageData> varOList = accountService.listAll(pd);
//		for(int i=0;i<varOList.size();i++){
//			
//			if(!varOList.get(i).containsKey("OPENID")){
//				String accountName = varOList.get(i).getString("ACCOUNTNAME");
//				String accountid = varOList.get(i).getString("ACCOUNT_ID");
//				PageData vpd = Spider.getAccount(accountName);
//				vpd.put("ACCOUNT_ID", accountid);
////				varList.add(vpd);
//				accountService.edit(vpd);
//			}
//			
//		}
////		accountService.batchUpdate(varList);
//		mv.addObject("msg","success");
//		mv.setViewName("save_result");
//		return mv;
//	}
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save() throws Exception{
		logBefore(logger, "新增Account");
		
		pd = this.getPageData();
//		pd.put("ACCOUNT_ID", this.get32UUID());	//主键
		int sn = accountService.count() + 1;
		pd.put("SN", sn);
		pd = Spider.getAccount(pd);
		accountService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}

    /**
     * 新增
     */
    @RequestMapping(value="/commend")
    public void commend() throws Exception{

        pd = this.getPageData();
        String isCommended = pd.getString("isCommended");
        if ("true".equals(isCommended)){
            pd.put("flag", 1);
        }else{
            pd.put("flag", 0);
        }
        accountService.commend(pd);
//        mv.addObject("msg","success");
//        mv.setViewName("save_result");
//        return mv;
    }
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Account");
		
		try{
			pd = this.getPageData();
			accountService.delete(pd);
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
		logBefore(logger, "修改Account");
		
		pd = this.getPageData();
		pd = Spider.getAccount(pd);
		accountService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Account");
		
		try{
			pd = this.getPageData();
			page.setPd(pd);
			List<PageData>	varList = accountService.list(page);	//列出Account列表
			this.getHC(); //调用权限
			mv.setViewName("official/account/account_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		logBefore(logger, "去新增Account页面");
		
		pd = this.getPageData();
		try {
			mv.setViewName("official/account/account_edit");
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
		logBefore(logger, "去修改Account页面");
		
		pd = this.getPageData();
		try {
			pd = accountService.findById(pd);	//根据ID读取
			mv.setViewName("official/account/account_edit");
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
		logBefore(logger, "批量删除Account");
		Map map = new HashMap();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				accountService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Account到excel");
		ModelAndView mv = new ModelAndView();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("openid");	//1
			titles.add("公众号名称");	//2
			titles.add("微信号");	//3
			titles.add("功能介绍");	//4
			titles.add("二维码url");	//5
			dataMap.put("titles", titles);
			List<PageData> varOList = accountService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("OPENID"));	//1
				vpd.put("var2", varOList.get(i).getString("ACCOUNTNAME"));	//2
				vpd.put("var3", varOList.get(i).getString("ACCOUNT"));	//3
				vpd.put("var4", varOList.get(i).getString("INFO"));	//4
				vpd.put("var5", varOList.get(i).getString("QRIMG"));	//5
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
