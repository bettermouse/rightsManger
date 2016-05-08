package com.chen.PageModel;

import java.util.ArrayList;
import java.util.List;

public class SessionInfo implements java.io.Serializable {

	private static final long serialVersionUID = 8405780740316158699L;
	private String username;
	private String password;
	private Integer id;
	private List<String> resourceList = new ArrayList<String>();
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public List<String> getResourceList() {
		return resourceList;
	}
	public void setResourceList(List<String> resourceList) {
		this.resourceList = resourceList;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	
}
