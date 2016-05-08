package com.chen.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.PageModel.DataGrid;
import com.chen.PageModel.Json;
import com.chen.PageModel.PageHelper;
import com.chen.PageModel.SessionInfo;
import com.chen.PageModel.User;
import com.chen.service.ResourcesServiceI;
import com.chen.service.UserServiceI;
import com.chen.util.ConfigUtil;

@Controller
@RequestMapping("/userController")
public class UserController extends BaseController{

	@Autowired
	UserServiceI userServiceI;
	
	@Autowired
	ResourcesServiceI resourcesServiceI;
	@RequestMapping("/login")
	@ResponseBody
	/**
	 * 判断用户名密码是否正确   如果正确  session赋值
	 * @param user
	 * @param request
	 * @param session
	 * @return
	 */
	public Json login(User user,String inputValNumber, HttpServletRequest request, HttpSession session) {
		Json json = new Json();
		String valNumber =(String) session.getAttribute("valNumber");
		if(valNumber.equals(inputValNumber)){
			User rUser = userServiceI.login(user);
			if (rUser != null) {
				SessionInfo sessionInfo = new SessionInfo();
				sessionInfo.setUsername(rUser.getUsername());
				sessionInfo.setPassword(rUser.getPassword());
				sessionInfo.setId(rUser.getId());
				sessionInfo.setResourceList(resourcesServiceI.getResourceList(rUser.getId()));
				session.setAttribute(ConfigUtil.getSessionInfoName(), sessionInfo);
				json.setSuccess(true);

			} else {
				json.setSuccess(false);
				json.setMsg("用户名或密码错误");
			}
			
		}else{
			json.setSuccess(false);
			json.setMsg("验证码错误");
		}
	

		return json;
	}

	@RequestMapping("/loginOut")
	@ResponseBody
	public Json loginOut(HttpSession session){
		Json j = new Json();
		if (session != null) {
			session.invalidate();
		}
		j.setSuccess(true);
		j.setMsg("注销成功！");
		return j;
	}

	/**
	 * 跳转到用户管理页面
	 * 
	 * @return
	 */

	@RequestMapping("/manager")
	public String manager() {
		return "/admin/myuser";
	}

	/**
	 * 跳转到 dataGrid页面
	 * 
	 * @param user
	 * @param pageHelper
	 * @return
	 */
	@RequestMapping("dataGrid")
	@ResponseBody
	public DataGrid dataGrid(User user, PageHelper pageHelper) {

		return userServiceI.dataGrid(user, pageHelper);
	}

	@RequestMapping("/addPage")
	public String addPage() {
		return "admin/userAdd";
	}

	@ResponseBody
	@RequestMapping("/add")
	public Json add(User user) {
		Json json = new Json();
		try {
			userServiceI.add(user);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setMsg(e.getMessage());
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Json delete(Integer id) {
		Json json = new Json();
		try {
			userServiceI.delete(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/status")
	public Json status(Integer id) {
		Json json = new Json();
		try {
			userServiceI.status(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/rePwd")
	public Json rePwd(Integer id) {
		Json json = new Json();
		try {
			userServiceI.rePwd(id);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	
	@ResponseBody
	@RequestMapping("/edit")
	public Json edit(User user) {
		Json json = new Json();
		try {
			userServiceI.edit(user);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/editPage")
	public String editPage(Integer id,HttpServletRequest request) {
		request.setAttribute("user", userServiceI.get(id));
		return "admin/userEdit";
	}
	
	@RequestMapping("/grantPage")
	public String grantPage(Integer id,HttpServletRequest request) {
		request.setAttribute("user", userServiceI.get(id));
		return "admin/userGrant";
	}
	
	@ResponseBody
	@RequestMapping("/grant")
	public Json grant(User user) {
		Json json = new Json();
		try {
			userServiceI.grnat(user);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
	}
	
	@RequestMapping("/editCurrentUserPwdPage")
	public String editUserPwdPage(){
		return "jsp/person/pwdEdit";
	}
	
	@ResponseBody
	@RequestMapping("/editCurrentUserPwd")
	public Json editUserPwd(HttpSession session,String newPwd,String pwd){
		Json json = new Json();
		if(session!=null){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			if(userServiceI.editCurrentPwd(sessionInfo.getId(),pwd,newPwd)){
				json.setSuccess(true);
				json.setMsg("修改密码成功");
			}
			else {
				json.setMsg("密码错误");
			}
		}else{
			json.setMsg("登陆超时");
		}
		return json;
	}
	
	
	@RequestMapping("/editCurrentUserMessagePage")
	public String editUserMessagePage(HttpServletRequest request,HttpSession session){
		if(session!=null){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			request.setAttribute("user", userServiceI.get(sessionInfo.getId()));
		}
		return "jsp/person/messageEdit";
	}
	
	@ResponseBody
	@RequestMapping("/editCurrentUserMessage")
	public Json editUserMessage(HttpSession session,User user){
		Json json = new Json();
		if(session!=null){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			userServiceI.editCurrentMessage(sessionInfo.getId(),user);
			json.setSuccess(true);
			json.setMsg("修改成功");
		}else{
			json.setMsg("登陆超时");
		}
		return json;
	}
	
	@RequestMapping("/myRolePage")
	public String myRolePage(HttpServletRequest request,HttpSession session){
		if(session!=null){
			SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
			request.setAttribute("user", userServiceI.get(sessionInfo.getId()));
		}
		return "jsp/person/myRole";
	}
	
	@RequestMapping("/myResourcesPage")
	public String myResources(HttpServletRequest request,HttpSession session){
		return "jsp/person/myResource";
	}
	

	
}
