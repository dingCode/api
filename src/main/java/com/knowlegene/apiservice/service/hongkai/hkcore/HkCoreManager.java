package com.knowlegene.apiservice.service.hongkai.hkcore;


import com.knowlegene.apiservice.entity.Page;
import com.knowlegene.apiservice.util.PageData;

import java.util.List;

/**
 * 说明： 测试数据关系模块接口
 * 创建人：hongkai
 * 创建时间：2016-12-01
 * 邮箱地址：18611949252@163.com
 * @version
 */
public interface HkCoreManager{

	/**新增
	 * @param pd
	 * @throws Exception
	 */
	public void save(PageData pd)throws Exception;
	
	/**删除
	 * @param pd
	 * @throws Exception
	 */
	public void delete(PageData pd)throws Exception;
	
	/**修改
	 * @param pd
	 * @throws Exception
	 */
	public void edit(PageData pd)throws Exception;
	
	/**列表
	 * @param page
	 * @throws Exception
	 */
	public List<PageData> list(Page page)throws Exception;
	
	/**列表(全部)
	 * @param pd
	 * @throws Exception
	 */
	public List<PageData> listAll(PageData pd)throws Exception;
	
	/**通过id获取数据
	 * @param pd
	 * @throws Exception
	 */
	public PageData findById(PageData pd)throws Exception;
	
	/**批量删除
	 * @param ArrayDATA_IDS
	 * @throws Exception
	 */
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception;

	/**测试用
	 * @throws Exception
	 */
	
}

