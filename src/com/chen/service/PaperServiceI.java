package com.chen.service;

import org.springframework.web.multipart.MultipartFile;

import com.chen.PageModel.PaperModel;

public interface PaperServiceI {

	Boolean add(MultipartFile paperAttachmentFile,MultipartFile serachFile,PaperModel paperModel);
}
