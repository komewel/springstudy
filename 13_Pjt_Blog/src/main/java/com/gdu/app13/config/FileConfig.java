package com.gdu.app13.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {

	@Bean  //commonfileresolver 기능을 이용할때 필요한 bean
	public MultipartResolver multipartResolver() {  
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("UTF-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 100); // 전체 첨부 파일의 크기 100MB 
		multipartResolver.setMaxUploadSizePerFile(1024 * 1024 * 10); // 첨부 파일 하나의 크기 10MB 
		return multipartResolver;
		// 반환을 안해주면 업로드할 빈이 없다고 오류 뜸, 걍 들고 다니면 됨, 용량을 수정해줘야함(무턱대고 용량 크게 하지말고) 
	}
	
}
