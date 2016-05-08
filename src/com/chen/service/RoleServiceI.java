package com.chen.service;

import java.util.List;

import com.chen.PageModel.DataGrid;
import com.chen.PageModel.PageHelper;
import com.chen.PageModel.Role;
import com.chen.PageModel.Tree;
import com.chen.model.PRole;

public interface RoleServiceI {
	/**
	 * 得到所有的角色
	 * @return
	 */
	public DataGrid dataGrid(PageHelper pageHelper);
	
	/**
	 *添加角色
	 * @param role
	 * @return
	 */
	public PRole add(Role role);
	
	/**
	 * 通过id删除角色
	 * @param id
	 */
	public void deleteById(Integer id);
	
	/**
	 * 通过id得到一个角色
	 * @param id
	 * @return
	 */
	public Role getById(Integer id);
	
	/**
	 * 修改角色
	 * @param role
	 * @return
	 */
	public PRole edit(Role role);
	
	/**
	 * 给角色授权
	 * @param role
	 */
	public void grant(Role role);
	/**
	 * 角色树
	 */
	public List<Tree> tree();
}
