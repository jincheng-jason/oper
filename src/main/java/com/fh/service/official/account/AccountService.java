package com.fh.service.official.account;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("accountService")
public class AccountService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;


    public void commend(PageData pd)throws Exception{
        dao.update("AccountMapper.updateCommend", pd);
    }

	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("AccountMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("AccountMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("AccountMapper.edit", pd);
	}
	
	/**
	 * 批量修改
	 */
	public void batchUpdate(List<PageData> pds)throws Exception{
		dao.batchUpdate("AccountMapper.batchUpdate", pds);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("AccountMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AccountMapper.listAll", pd);
	}
	
	
	/*
	 * 获得序号
	 */
	public int count()throws Exception{
		return (int)dao.findForObject("AccountMapper.count");
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AccountMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("AccountMapper.deleteAll", ArrayDATA_IDS);
	}

    //按公众号名称查找
    public PageData findByName(String accountName)throws Exception{
        return (PageData)dao.findForObject("AccountMapper.findByName",accountName);
    }
	
}

