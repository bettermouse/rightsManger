package com.chen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.PageModel.DataGrid;
import com.chen.PageModel.Json;
import com.chen.PageModel.PageHelper;
import com.chen.PageModel.Role;
import com.chen.PageModel.Tree;
import com.chen.service.RoleServiceI;

/**
 * 角色控制器
 * @author chen
 *
 */
@Controller
@RequestMapping("/roleController")
public class RoleController {

	@Autowired
	RoleServiceI roleServiceI;
	/**
	 * 跳转到角色管理页面
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(){
		
		return "/admin/role";
	}
	/**
	 * 得到所有的角色
	 * @param pageHelper
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dataGrid")
	public DataGrid dataGrid(PageHelper pageHelper){
		
		return roleServiceI.dataGrid(pageHelper);
	}
	
	/**
	 * 得到所有的角色
	 * @param pageHelper
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/tree")
	public List<Tree> tree(){
		
		return roleServiceI.tree();
	}
	
	/**
	 * 跳转到增加一个角色页面
	 */

	@RequestMapping("/addPage")
	public String addPage(){
		return "/admin/roleAdd";
	}
	
	/**
	 * 添加角色
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Role role){
		Json json = new Json();
		try {
			roleServiceI.add(role);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}
	
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Integer id){
		Json json = new Json();
		try {
			roleServiceI.deleteById(id);
			json.setMsg("删除成功!");
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg("删除失败!");
			e.printStackTrace();
		}
		return json;
	}
	
 
	/**
	 * 跳转到编辑页面
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request,Integer id){
		request.setAttribute("role", roleServiceI.getById(id));
		return "admin/roleEdit";
	}
	
	/**
	 * 修改角色页面
	 * @param role
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Role role){
		Json json = new Json();
		try {
			roleServiceI.edit(role);
			json.setMsg("修改成功");
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * 跳转到授权页面
	 * @param request
	 * @param id
	 * @return
	 */
	@RequestMapping("/grantPage")
	public String grantPage(HttpServletRequest request,Integer id){
		request.setAttribute("role", roleServiceI.getById(id));
		return "admin/roleGrant";
	}
	
	
	/**
	 * 角色授权
	 * @param role
	 * @return
	 */
	@RequestMapping("/grant")
	@ResponseBody
	public Json grant(Role role){
		Json json = new Json();
		try {
			roleServiceI.grant(role);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return json;
		
	}
}
