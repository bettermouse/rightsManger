package com.chen.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.PageModel.Json;
import com.chen.PageModel.Resource;
import com.chen.PageModel.SessionInfo;
import com.chen.PageModel.Tree;
import com.chen.service.ResourceTypeServiceI;
import com.chen.service.ResourcesServiceI;
import com.chen.util.ConfigUtil;


/**
 * 资源控制器
 * 
 * @author 
 * 
 */
@Controller
@RequestMapping("/resourceController")
public class ResourceController extends BaseController {
	
	@Autowired
	ResourcesServiceI resourcesServiceI;
	
	@Autowired
	ResourceTypeServiceI resourceTypeServiceI;
	
	@ResponseBody
	@RequestMapping("/myMenuTree")
	public List<Tree> tree(HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourcesServiceI.getResourceMyTree(sessionInfo.getId());
	}
	
	/**
	 * 跳到资源管理页面
	 * @return
	 */
	@RequestMapping("/manager")
	public String manager(){
		
		return "/admin/resource";
	}
	
	/**
	 * 资源树
	 * @param session
	 * @return
	 */
	@RequestMapping("/treeGrid")
	@ResponseBody
	public List<Resource> treeGrid(HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourcesServiceI.treeGrid(sessionInfo);
	
	}
	
	/**
	 * 添加资源页面
	 * @param request
	 * @return
	 */
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("resourceTypeList", resourceTypeServiceI.getResourceTypeList());
		return "/admin/resourceAdd";
	}
	
	/**
	 * 添加资源
	 * 
	 * @return
	 */
	@RequestMapping("/add")
	@ResponseBody
	public Json add(Resource resource) {
		Json j = new Json();
		resourcesServiceI.add(resource);
		j.setSuccess(true);
		j.setMsg("添加成功！");
		return j;
	}
	
	/**
	 * 删除资源
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		resourcesServiceI.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}
	
	/**
	 * 跳转到资源编辑页面
	 * 
	 * @return
	 */
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		request.setAttribute("resourceTypeList",resourceTypeServiceI.getResourceTypeList());
		Resource r = resourcesServiceI.get(id);
		request.setAttribute("resource", r);
		return "/admin/resourceEdit";
	}
	
	/**
	 * 编辑资源
	 * 
	 * @param resource
	 * @return
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Resource resource) {
		Json j = new Json();
		resourcesServiceI.edit(resource);
		j.setSuccess(true);
		j.setMsg("编辑成功！");
		return j;
	}
	
	
	@ResponseBody
	@RequestMapping("allTree")
	public List<Tree> allTree(){
		return resourcesServiceI.getResourceAllTree();
	}

	@ResponseBody
	@RequestMapping("allMenuTree")
	public List<Tree> allMenuTree(){
		return resourcesServiceI.getResourceAllMenuTree();
	}
	
	@ResponseBody
	@RequestMapping("allMyTree")
	public List<Tree> allMyTree(HttpSession session){
		SessionInfo sessionInfo = (SessionInfo) session.getAttribute(ConfigUtil.getSessionInfoName());
		return resourcesServiceI.getResourceAllMyTree(sessionInfo.getId());
	}
	
}
