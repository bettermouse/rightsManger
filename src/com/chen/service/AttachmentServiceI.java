package com.chen.service;

import org.springframework.web.multipart.MultipartFile;

import com.chen.model.Attachment;

public interface AttachmentServiceI {
	Attachment add(MultipartFile multipartFile);
}
