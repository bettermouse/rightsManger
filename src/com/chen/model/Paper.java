package com.chen.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="paper")
public class Paper {
	private Integer id;
	//论文第一作者
	private PUser firstAuthor;
    private Set<PUser> attendeeAuthors = new HashSet<>();
    private String title;
    //期刊名称
    private String journalName;
    //年卷期页
    private String publishDate;
    private String journalType;
    private Attachment paperAttachment;
    private Attachment search;
    
    
	public Paper() {
	
	}


	@Id
	@GeneratedValue
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne
	@JoinColumn(name = "user_id",nullable = true)
	public PUser getFirstAuthor() {
		return firstAuthor;
	}


	public void setFirstAuthor(PUser firstAuthor) {
		this.firstAuthor = firstAuthor;
	}





	@ManyToMany
	@JoinTable(name="author_paper",joinColumns = { @JoinColumn(name="paper_id",nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name="author_id",nullable = false, updatable = false) })
	public Set<PUser> getAttendeeAuthors() {
		return attendeeAuthors;
	}


	public void setAttendeeAuthors(Set<PUser> attendeeAuthors) {
		this.attendeeAuthors = attendeeAuthors;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getJournalName() {
		return journalName;
	}


	public void setJournalName(String journalName) {
		this.journalName = journalName;
	}


	public String getPublishDate() {
		return publishDate;
	}


	public void setPublishDate(String publishDate) {
		this.publishDate = publishDate;
	}


	public String getJournalType() {
		return journalType;
	}


	public void setJournalType(String journalType) {
		this.journalType = journalType;
	}


	@OneToOne
	@JoinColumn(name="paperAttachment_id")
	public Attachment getPaperAttachment() {
		return paperAttachment;
	}


	public void setPaperAttachment(Attachment paperAttachment) {
		this.paperAttachment = paperAttachment;
	}




	@OneToOne
	@JoinColumn(name="searchAttachment_id")
	public Attachment getSearch() {
		return search;
	}


	public void setSearch(Attachment search) {
		this.search = search;
	}

	

}
