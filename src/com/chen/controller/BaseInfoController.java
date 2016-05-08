package com.chen.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.chen.PageModel.BaseInfo;
import com.chen.PageModel.Json;
import com.chen.util.BaseInfoUtil;



@Controller
@RequestMapping("/baseInfoController")
public class BaseInfoController {
	
	@RequestMapping("/show")
	public String showBaseInfoPage(HttpServletRequest request){
		request.setAttribute("baseInfo", BaseInfoUtil.getInstacne().read());
		return "/admin/baseInfo";
	}
	
	@RequestMapping("/update")
	public Json updateBaseInfo(BaseInfo baseInfo){
		Json json = new Json();
		try {
			BaseInfoUtil.getInstacne().write(baseInfo);
			json.setSuccess(true);
		} catch (Exception e) {
			json.setSuccess(false);
			e.printStackTrace();
		}
		return json;
		
	}
}
