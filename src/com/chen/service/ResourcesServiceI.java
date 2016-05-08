package com.chen.service;

import java.util.List;

import com.chen.PageModel.Resource;
import com.chen.PageModel.SessionInfo;
import com.chen.PageModel.Tree;
import com.chen.model.Tresource;

public interface ResourcesServiceI {
	/**
	 * 得到所有的资源树
	 * 
	 * @return
	 */
	public List<Tree> getResourceAllTree();

	/**
	 * 得到所有的 资源
	 * 
	 * @param sessionInfo
	 * @return
	 */
	public List<Resource> treeGrid(SessionInfo sessionInfo);

	/**
	 * 增加一个资源
	 * 
	 * @param resource
	 * @return
	 */
	public Tresource add(Resource resource);

	/**
	 * 删除一个资源
	 * 
	 * @param id
	 */
	public void delete(String id);

	/**
	 * 根据id得到资源
	 * 
	 * @param id
	 * @return
	 */
	public Resource get(String id);

	/**
	 * 编辑资源
	 * 
	 * @param resource
	 */
	public void edit(Resource resource);

	/**
	 * 根据id 得到自己菜单级树
	 * 
	 * @return
	 */
	public List<Tree> getResourceMyTree(Integer id);

	/**
	 * 得到所有的菜单树
	 * @return
	 */
	public List<Tree> getResourceAllMenuTree();
	/**
	 * 通才id 得到所有资源的URL
	 * @param id
	 * @return
	 */
	public List<String> getResourceList(Integer id);
	/**
	 * 通过id 得到我的资源树
	 * @param id
	 * @return
	 */
	public List<Tree> getResourceAllMyTree(Integer id);
}
