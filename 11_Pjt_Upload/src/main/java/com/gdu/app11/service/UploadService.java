package com.gdu.app11.service;

import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface UploadService {
	public int addUpload(MultipartHttpServletRequest multipartRequest); // 파일첨부할때 쓰는 특별한 기능
	
}
