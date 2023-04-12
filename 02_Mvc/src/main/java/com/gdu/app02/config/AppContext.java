package com.gdu.app02.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gdu.app02.domain.Bbs;

@Configuration
public class AppContext {

	// 1번재 Bean은 root-context.xml에 있습니다.
	
	@Bean
	public Bbs bbs2() {
		Bbs bbs = new Bbs();
		bbs.setBbsNo(2);
		bbs.setTitle("살려주세요");
		bbs.setCreatedAt("2023-04-13");
		return bbs;
	}
	// Bbs bbs1과 Bbs bbs2가 Spring Container에 저장되어 있는 상태이다.
}
