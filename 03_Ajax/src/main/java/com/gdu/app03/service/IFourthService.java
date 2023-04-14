package com.gdu.app03.service;

import org.springframework.http.ResponseEntity;

public interface IFourthService {

	public ResponseEntity<byte[]> display(String path, String filename); // 이미지를 반환한다는건 바이트 배열을 반환한다는것
		
		
	}
