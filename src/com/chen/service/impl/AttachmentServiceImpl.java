package com.chen.service.impl;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chen.dao.BaseDaoI;
import com.chen.model.Attachment;
import com.chen.service.AttachmentServiceI;

@Service
public class AttachmentServiceImpl implements AttachmentServiceI {

	@Autowired
	BaseDaoI<Attachment> attachmentDaoI;
	
	@Override
	public Attachment add(MultipartFile multipartFile) {
		Attachment attachment = new Attachment();
		String fileName = multipartFile.getOriginalFilename(); 
		String[] split = fileName.split("\\.");
		String newFileName = UUID.randomUUID().toString()+"."+split[split.length-1];
		String path ="d:"+File.separator+"files"+File.separator+newFileName;
		/*File targetFile = new File("/upload", fileName);  
	        if(!targetFile.exists()){  
	            targetFile.mkdirs();  
	        } */
	    try {
	    	multipartFile.transferTo(new File(path));
		//	IOUtils.copy(multipartFile.getInputStream(),new FileOutputStream("d:"+File.separator+fileName));
		} catch (FileNotFoundException e) {
		    System.out.println("文件上传异常");
			e.printStackTrace();
		} catch (IOException e) {
			 System.out.println("文件上传异常");
			e.printStackTrace();
		}
	    attachment.setName(newFileName);
	    attachment.setPath(path);
	    attachment.setType(split[1]);
	 //   attachment.setPath(targetFile.getAbsolutePath());
	    attachmentDaoI.save(attachment);
		return attachment;
	}
	
}
