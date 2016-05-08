package com.chen.PageModel;

import java.util.HashSet;
import java.util.Set;

import com.chen.model.Attachment;
import com.chen.model.PUser;

public class PaperModel {
	private PUser firstAuthor;
    private Set<PUser> attendeeAuthors = new HashSet<>();
    private String title;
    private String journalName;
    //年卷期页
    private String publishDate;
    private String journalType;
    private Attachment paperAttachment;
    private Attachment search;
	public PUser getFirstAuthor() {
		return firstAuthor;
	}
	public void setFirstAuthor(PUser firstAuthor) {
		this.firstAuthor = firstAuthor;
	}
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
	public Attachment getPaperAttachment() {
		return paperAttachment;
	}
	public void setPaperAttachment(Attachment paperAttachment) {
		this.paperAttachment = paperAttachment;
	}
	public Attachment getSearch() {
		return search;
	}
	public void setSearch(Attachment search) {
		this.search = search;
	}
    
}
