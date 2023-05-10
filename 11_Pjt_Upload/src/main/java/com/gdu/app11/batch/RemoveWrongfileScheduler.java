package com.gdu.app11.batch;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.gdu.app11.domain.AttachDTO;
import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@EnableScheduling
@Component
public class RemoveWrongfileScheduler {

	// field
	private MyFileUtil myFileUtil;
	private UploadMapper uploadMapper;

	@Scheduled(cron="0 0 2 1/1 * ?")  // www.cronmaker.com에서 생성한 매일 새벽 3시 정보에서 마지막 필드 *를 지워줌
	public void execute() {			  // 메소드명은 아무 의미 없다
	
		// 어제 업로드 된 첨부 파일들의 정보 (DB에서 가져오기)
		List<AttachDTO> attachList = uploadMapper.getAttachListInYesterday();
		
		// List<AttachDTO> -> List<Path>로 변환하기 (path : 경로 + 파일명)
		List<Path> pathList = new ArrayList<Path>();
		if(attachList != null && attachList.isEmpty() == false) { // 어제 첨부된게 있으면 
			for(AttachDTO attachDTO : attachList) {
				pathList.add(new File(attachDTO.getPath(), attachDTO.getFilesystemName()).toPath()); // path 만들기 : File객체.toPath()
				if(attachDTO.getHasThumbnail() == 1) {
					pathList.add(new File(attachDTO.getPath(), "s_" + attachDTO.getFilesystemName()).toPath());
				}
			}
		}
		
		// 어제 업로드 된 경로 
		String yesterdayPath = myFileUtil.getyesterdayPath();
		
		// 어제 업로드 된 파일 목록(HDD에서 확인) 중에서 DB에는 정보가 없는 파일 목록
		File dir = new File(yesterdayPath);
		File[] wrongFiles = dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) { // true를 반환하면 File[] wrongFiles에 포함된다. 매개변수 File dir , String name은 HDD에 저장된 파일을 의미한다.
				// DB에 있는 목록 : pathList				- path 이미 패스로 바뀌어져있고
				// HDD에 있는 파일 : File dir, String name  - File.toPath() 처리해서 바꾸어 주어야 비교가능
				return pathList.contains(new File(dir, name).toPath()) == false; // 포함되어 있지 않다가 되어야 하므로 false
			}
		}); // 가져올때 선별해서 가지고 올수 있다 (필터), 인터페이스 메소드를 그대로 구현한다면(본문을 채워준다면) new로 선언할 수 있다.
		
		// File[] wrongFiles 모두 삭제
		if(wrongFiles != null && wrongFiles.length != 0) {
			for(File wrongFile : wrongFiles) {
				wrongFile.delete();
			}
		}
	}
}
