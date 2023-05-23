package com.gdu.app13.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BlogDTO {
	private int blogNo;
	private String title;
	private String content;
	private String hit;
	private String createdAt;
	private String modifiedAt;
	private MemberDTO memberDTO; // private  in 
}
