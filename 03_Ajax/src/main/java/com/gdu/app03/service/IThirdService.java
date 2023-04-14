package com.gdu.app03.service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.gdu.app03.domain.Contact;

public interface IThirdService {
	public ResponseEntity<Contact> execute1(Contact contact);
	public ResponseEntity<Map<String, String>> execute2(Map<String, String> map);
	// jackson이 json파일을 받아서 bean이나 map으로 바꿔주는 과정
}
