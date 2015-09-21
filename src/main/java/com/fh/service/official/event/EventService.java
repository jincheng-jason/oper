//package com.fh.service.official.event;
//
//import com.fh.dao.DaoSupport;
//import com.fh.entity.Page;
//import com.fh.util.PageData;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.util.List;
//
///**
// * Created by lijc on 15/5/18.
// */
//@Service("officialEventService")
//public class EventService {
//
//
//    @Resource(name = "daoSupport")
//    private DaoSupport dao;
//
//
//    /*
//    * 新增
//    */
//    public void save(PageData pd)throws Exception{
//        dao.save("EventMapper.save", pd);
//    }
//
//    /*
//    * 删除
//    */
//    public void delete(PageData pd)throws Exception{
//        dao.delete("EventMapper.delete", pd);
//    }
//
//    /*
//    * 修改
//    */
//    public void edit(PageData pd)throws Exception{
//        dao.update("EventMapper.edit", pd);
//    }
//
//    /**
//     * 批量修改
//     */
//    public void batchUpdate(List<PageData> pds)throws Exception{
//        dao.batchUpdate("EventMapper.batchUpdate", pds);
//    }
//
//    /*
//    *列表
//    */
//    public List<PageData> list(Page page)throws Exception{
//        return (List<PageData>)dao.findForList("EventMapper.datalistPage", page);
//    }
//
//    /*
//    *列表(全部)
//    */
//    public List<PageData> listAll(PageData pd)throws Exception{
//        return (List<PageData>)dao.findForList("EventMapper.listAll", pd);
//    }
//
//
//    /*
//     * 获得序号
//     */
//    public int count()throws Exception{
//        return (int)dao.findForObject("EventMapper.count");
//    }
//
//    /*
//    * 通过id获取数据
//    */
//    public PageData findById(PageData pd)throws Exception{
//        return (PageData)dao.findForObject("EventMapper.findById", pd);
//    }
//
//    /*
//    * 批量删除
//    */
//    public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
//        dao.delete("EventMapper.deleteAll", ArrayDATA_IDS);
//    }
//
//}
