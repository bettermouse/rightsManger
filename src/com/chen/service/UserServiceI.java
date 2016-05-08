package com.chen.service;

import com.chen.PageModel.DataGrid;
import com.chen.PageModel.PageHelper;
import com.chen.PageModel.User;
import com.chen.model.PUser;

public interface UserServiceI {
	/**
	 * 根据用户名 密码  查询登陆的用户
	 * @param user
	 * @return
	 */
	public User login(User user);
	/**
	 * 得到用户的Datagrid
	 * @param user
	 * @param pageHelper
	 * @return
	 */
	public DataGrid dataGrid(User user,PageHelper pageHelper);
	/**
	 * 增加用户
	 * @param user
	 * @return
	 * @throws Exception 
	 */
	public PUser add(User user) throws Exception;
	/**
	 * 根据用户名得到用户
	 * @param username
	 * @return
	 */
	public PUser getUser(String username);
	/**
	 * 通过id删除用户
	 * @param id
	 */
	public void delete(Integer id);
	/**
	 * 通过Id  激活/禁用用户
	 * @param id
	 */
	public void status(Integer id);
	/**
	 * 通过id重置密码
	 * @param id
	 */
	public void rePwd(Integer id);
	/**
	 * 通过Id 得到用户
	 * @param id
	 * @return
	 */
	public User get(Integer id);
	/**
	 * 修改用户
	 * @param user
	 */
	public void edit(User user);
	/**
	 * 用户授权
	 * @param user
	 */
	public void grnat(User user);
	/**
	 * 修改用户密码
	 * @param id
	 * @param pwd
	 * @param newPwd
	 */
	public boolean editCurrentPwd(Integer id, String pwd, String newPwd);
	/**
	 * 修改用户的个人资料
	 * @param id
	 * @param user
	 * @return
	 */
	public boolean editCurrentMessage(Integer id, User user);
}
