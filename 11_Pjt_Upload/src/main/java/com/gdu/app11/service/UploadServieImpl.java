package com.gdu.app11.service;

import java.io.File;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app11.mapper.UploadMapper;
import com.gdu.app11.util.MyFileUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor  // field의 @Autowired 처리를 위함이다.
public class UploadServieImpl implements UploadService {

	// field
	private UploadMapper uploadMapper;
	private MyFileUtil myFileUtil;
	
	@Override
	public int addUpload(MultipartHttpServletRequest multipartRequest) {
		
		/* Upload 테이블에 UploadDTO 넣기 */
		
		/* Attach 테이블에 AttachDTO 넣기 */
		
		// 첨부된 파일 목록
		List<MultipartFile> files = multipartRequest.getFiles("files"); // <input type="file" name="files"> 
		
		// 첨부된 파일이 있는지(제목만쓰고 파일은 첨부 안했을수도 있으므로)
		if(files != null && files.isEmpty() == false) {
			
		// 첨부된 파일 목록 순회
		for(MultipartFile multipartFile : files) {
			
			// 예외처리 
			try {
				// 첨부 파일의 저장 경로
				String path = myFileUtil.getPath();
				
				// 첨부 파일의 저장 경로가 없으면 만들기
				File dir = new File(path);
				if(dir.exists() == false) {
					dir.mkdirs();
				}
				
				// 첨부 파일의 원래 이름 
				String originName = multipartFile.getOriginalFilename();
				originName = originName.substring(originName.lastIndexOf("\\") + 1);
				// 이름이 특이하게 경로까지 포함해서 오는 경우가 있다(IE) 그런경우를 방지해준다. 마지막 역슬래시 뒤에 있는 파일명만 사용한다.
				
				// 첨부 파일의 저장이름
				String filesystemName = myFileUtil.getFilesystemName(originName);
				
				// 첨부파일의 File 객체(HDD에 저장할 첨부 파일)
				File file = new File(dir, filesystemName);
				
				// 첨부파일을 HDD에 저장
				multipartFile.transferTo(file); // 첨부파일의 객체만 만들고 보내기만 알아서 처리된다, 여기서 실제 서버에 저장된다.
				
				/* DB에 첨부 파일 정보 저장하기, uploadNo를 알아야 한다  */
				
			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
		return 0;
}
	
	

}
