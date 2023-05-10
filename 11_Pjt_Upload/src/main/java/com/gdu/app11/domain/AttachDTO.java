package com.gdu.app11.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttachDTO {
	private int attachNo;
	private String path;
	private String originName; // 원래이름은 한글이 포함되있을수도 있는 인코딩 오류가 있을수도 있는데 여러가지 중복을 포함해서 여러 오류가 있을수도 있으므로 원래이름도 저장은 하지만 실제 저장은 filesystemName로 한다
	private String filesystemName;
	private int downloadCount;
	private int hasThumbnail;
	private int uploadNo;
}
