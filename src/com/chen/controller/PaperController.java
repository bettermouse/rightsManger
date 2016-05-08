package com.chen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.chen.PageModel.Json;
import com.chen.PageModel.PaperModel;
import com.chen.service.PaperServiceI;

@Controller
@RequestMapping("/paper")
public class PaperController {

	@Autowired
	PaperServiceI paperServiceI;
	
	@RequestMapping("/paperPage")
	public String paperManger(){
		return "/myStudy/paperManager";
	}
	
	@RequestMapping("/addPaperPage")
	public String addPaperPage(){
		return "/myStudy/addPaperPage";
	}
	

	@RequestMapping("/add")
	public Json addPaper(@RequestParam MultipartFile paperAttachmentFile,@RequestParam MultipartFile serachFile,PaperModel paperModel){
		System.out.println(paperAttachmentFile.toString());
		System.out.println(serachFile.toString());
		Boolean flag = paperServiceI.add(paperAttachmentFile, serachFile, paperModel);
		Json json = new Json();
		json.setSuccess(false);
		//json.setMsg(msg);
		return json;
	}

}
