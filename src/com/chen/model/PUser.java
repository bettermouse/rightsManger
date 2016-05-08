package com.chen.model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name="puser")
@DynamicUpdate(true)
@DynamicInsert(true)
public class PUser {
	private Integer id;
	private String username;
	private String password;
	private Date createDate;//创建时间
	private String phone;
	private Integer status;
	private Set<PRole> roles;
	
	
	private String name;
	private Boolean sex;
	private String borthDate;
	private String position;
	private String education;
	private String degree;
	//第一者
	private Set<Paper> firstPapers;
	private Set<Paper> attendeePapers;

	
	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
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
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="role_puser",joinColumns = { @JoinColumn(name="user_id",nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name="role_id",nullable = false, updatable = false) })
	public Set<PRole> getRoles() {
		return roles;
	}
	public void setRoles(Set<PRole> roles) {
		this.roles = roles;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Boolean getSex() {
		return sex;
	}
	public void setSex(Boolean sex) {
		this.sex = sex;
	}
	public String getBorthDate() {
		return borthDate;
	}
	public void setBorthDate(String borthDate) {
		this.borthDate = borthDate;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	@OneToMany(mappedBy = "firstAuthor")
	public Set<Paper> getFirstPapers() {
		return firstPapers;
	}
	public void setFirstPapers(Set<Paper> firstPapers) {
		this.firstPapers = firstPapers;
	}
	
	@ManyToMany
	@JoinTable(name="attendee_author_paper",joinColumns = { @JoinColumn(name="attendee_author_id",nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name="paper_id",nullable = false, updatable = false) })
	public Set<Paper> getAttendeePapers() {
		return attendeePapers;
	}
	public void setAttendeePapers(Set<Paper> attendeePapers) {
		this.attendeePapers = attendeePapers;
	}

	
	
	
}
