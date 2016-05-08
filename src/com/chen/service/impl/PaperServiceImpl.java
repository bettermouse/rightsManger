package com.chen.service.impl;

import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chen.PageModel.PaperModel;
import com.chen.dao.BaseDaoI;
import com.chen.model.Attachment;
import com.chen.model.Paper;
import com.chen.service.AttachmentServiceI;
import com.chen.service.PaperServiceI;
@Service
public class PaperServiceImpl implements PaperServiceI{

	@Autowired
	AttachmentServiceI attachmentServiceI;
	
	@Autowired
	BaseDaoI<Paper> paperDaoI;
	
	
	@Override
	public Boolean add(MultipartFile paperAttachmentFile,MultipartFile serachFile,PaperModel paperModel) {
		Paper paper = new Paper();
		//保存附件
		Attachment paperAttachment = attachmentServiceI.add(paperAttachmentFile);
		//保存附件
		Attachment serachAttachment = attachmentServiceI.add(serachFile);
		paper.setPaperAttachment(paperAttachment);
		paper.setSearch(serachAttachment);
		BeanUtils.copyProperties(paperModel,paper);
		paperDaoI.save(paper);
		return true;
	}

}
