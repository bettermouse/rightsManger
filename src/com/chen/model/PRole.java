package com.chen.model;


import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.chen.PageModel.User;
@Entity
@Table(name="role")
public class PRole {
	private Integer id;
	private String name;
	private Integer seq;
	private String  description;
	private Set<Tresource> resources;
	private Set<PUser> user;
	
	@GeneratedValue
	@Id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(name = "role_tresource", joinColumns = { @JoinColumn(name = "role_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "resources_ID", nullable = false, updatable = false) })
	public Set<Tresource> getResources() {
		return resources;
	}
	public void setResources(Set<Tresource> resources) {
		this.resources = resources;
	}
	public Integer getSeq() {
		return seq;
	}
	public void setSeq(Integer seq) {
		this.seq = seq;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="role_puser",joinColumns = { @JoinColumn(name="role_id",nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name="user_id",nullable = false, updatable = false) })
	public Set<PUser> getUser() {
		return user;
	}
	public void setUser(Set<PUser> user) {
		this.user = user;
	}
	
}
