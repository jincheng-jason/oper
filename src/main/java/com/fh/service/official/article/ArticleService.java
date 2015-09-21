package com.fh.service.official.article;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("articleService")
public class ArticleService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ArticleMapper.save", pd);
	}
	
	/*
	 * 批量新增
	 */
	public void batchSave(List<PageData> list)throws Exception{
		dao.save("ArticleMapper.batchSave", list);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ArticleMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ArticleMapper.edit", pd);
	}

    public List<PageData> offline(Page page) throws Exception{
        return (List<PageData>)dao.findForList("ArticleMapper.offlinelistPage", page);
    }

	/*
	*   热门推荐列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ArticleMapper.datalistPage", page);
	}

    /*
     *  行业动态列表
     */
    public List<PageData> newsList(Page page)throws Exception{
        return (List<PageData>)dao.findForList("ArticleMapper.newslistPage", page);
    }
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ArticleMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ArticleMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ArticleMapper.deleteAll", ArrayDATA_IDS);
	}


    public void onlineAll(PageData pd) throws Exception {
        dao.update("ArticleMapper.onlineAll",pd);
    }

    public void chief(PageData pd)  throws Exception {
        dao.update("ArticleMapper.updateChief",pd);
    }
}

