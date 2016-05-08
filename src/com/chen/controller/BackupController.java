package com.chen.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chen.PageModel.BackupFile;
import com.chen.PageModel.DataGrid;
import com.chen.PageModel.Json;
import com.chen.util.BackupFileUtil;
import com.chen.util.PagerUtils;
import com.iflytek.cloud.a.d;


@Controller
@RequestMapping("/backupController")
public class BackupController {
	@RequestMapping("/backupPage")
	public String backupPage() {
		return "admin/backup";
	}
	
	@RequestMapping("/backupAddPage")
	public String backupAddPage() {
		return "admin/backupAdd";
	}
	
	@ResponseBody
	@RequestMapping("/add")
	public Json add(String name,HttpSession session) {
		Json json = new Json();
		try {
			String path = session.getServletContext().getRealPath("");
			System.out.println(path);
			BackupFileUtil bfu = BackupFileUtil.getInstance(path);
			bfu.backup(name);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("错误");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/dataGrid")
	public DataGrid dataGrid(HttpSession session){
		String path = session.getServletContext().getRealPath("");
		BackupFileUtil bfu = BackupFileUtil.getInstance(path);
		List<BackupFile> backupFiles =bfu.listBackups();
		DataGrid dataGrid = new DataGrid();
		dataGrid.setRows(backupFiles);
	
		return dataGrid;
	}
	
	@ResponseBody
	@RequestMapping("/delete")
	public Json delete(String name,HttpSession session) {
		Json json = new Json();
		try {
			String path = session.getServletContext().getRealPath("");
			System.out.println(path);
			BackupFileUtil bfu = BackupFileUtil.getInstance(path);
			bfu.delete(name);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("错误");
		}
		return json;
	}
	
	@ResponseBody
	@RequestMapping("/resume")
	public Json resume(String name,HttpSession session) {
		Json json = new Json();
		try {
			String path = session.getServletContext().getRealPath("");
			System.out.println(path);
			BackupFileUtil bfu = BackupFileUtil.getInstance(path);
			bfu.resume(name);
			json.setSuccess(true);
		} catch (Exception e) {
			e.printStackTrace();
			json.setMsg("错误");
		}
		return json;
	}
}
