package com.gdu.app13.service;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.gdu.app13.domain.BlogDTO;
import com.gdu.app13.domain.MemberDTO;
import com.gdu.app13.domain.SummernoteImageDTO;
import com.gdu.app13.mapper.BlogMapper;
import com.gdu.app13.util.MyFileUtil;
import com.gdu.app13.util.PageUtil;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class BlogServiceImpl implements BlogService {

	private BlogMapper blogMapper;
	private MyFileUtil myFileUtil;
	private PageUtil pageUtil;
	
	@Override
	public void loadBlogList(HttpServletRequest request, Model model) {
		
		Optional<String> opt1 = Optional.ofNullable(request.getParameter("page"));
		int page = Integer.parseInt(opt1.orElse("1"));
		
		int blogCount = blogMapper.getBlogCount();
		
		int recordPerPage = 10; 
		
		pageUtil.setPageutil(page, blogCount, recordPerPage);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("begin", pageUtil.getBegin());
		map.put("end", pageUtil.getEnd());

		List<BlogDTO> blogList = blogMapper.getBlogList(map);
		
		model.addAttribute("blogList", blogList);
		model.addAttribute("pagination", pageUtil.getPagination(request.getContextPath() + "/blog/list.do")); // 목록보기의 경로값을 준다, < 1 2 3 4 5 > 이거 각각의 경로를 준다는 말이다.
		model.addAttribute("beginNo", blogCount - (page - 1) * recordPerPage);
		
	}
	
	@Transactional(readOnly = true)
	@Override
	public void addBlog(HttpServletRequest request, HttpServletResponse response) {
		// 요청
	    String title = request.getParameter("title");
	    String content = request.getParameter("content");
	    int memberNo = Integer.parseInt(request.getParameter("memberNo"));
	    
	     /*
	       
	// addBlog() 서비스에 들어가야하는 기능
	// blog 추가할 때 insert에 <selectKey> 사용해서 BLOG_SEQ 값을 BlogDTO에 넣어 놓아야 한다.
	
	public static void main(String[] args) {

		String content = "<img src=\"/app13/imageLoad/aaaaa.jpg\"><br><img src=\"/app13/imageLoad/bbbbb.png\">"; // 직접 만드는거 보단 메모장에서 미리 작업하고 복붙하는게 편하다(이스케이프 기호처리 때문에)
		
		// content에서 꺼내서(jsoup), String 받아서 분석하고
		Document document = Jsoup.parse(content);
		
		Elements elements = document.getElementsByTag("img"); // 원하는 태그만 쏙 빼가서 저장할수 있다, Elements = Element의 배열
		for(Element element : elements) {
			String src = element.attr("src"); // 우리가 읽을수 있는 String 값으로 변환되었다
			// summernote 말고도 모든 편집기는 태그로 만들어진다.
			String filesystemName = src.substring(src.lastIndexOf("/") + 1); // 파일명의 해당 인덱스부터 끝까지 가져와라 = 실제 파일명 
			// blogMapper.addSummernoteImage(filesystemName);
	      */
		
		// DB로 보낼 BlogDTO 만들기, DTO안에 DTO가 있는 특별한 경우
	    MemberDTO memberDTO = new MemberDTO();
	    memberDTO.setMemberNo(memberNo);
	    BlogDTO blogDTO = new BlogDTO();
	    blogDTO.setTitle(title);
	    blogDTO.setContent(content);
	    blogDTO.setMemberDTO(memberDTO);
		
		// DB로 BlogDTO 보내기(삽입)
	    int addResult = blogMapper.addBlog(blogDTO);

	    /*** BLOG_T, 먼저 작성해야 blogNo을 사용할 수 있다. ***/
	    Document document = Jsoup.parse(content);
	    Elements elements = document.getElementsByTag("img");
	    if(elements != null) {
	        for(Element element : elements) {
	          String src = element.attr("src");
	          String filesystemName = src.substring(src.lastIndexOf("/") + 1);
	          SummernoteImageDTO summernoteImageDTO = new SummernoteImageDTO();
	          summernoteImageDTO.setFilesystemName(filesystemName);
	          summernoteImageDTO.setBlogNo(blogDTO.getBlogNo());
	          blogMapper.addSummernoteImage(summernoteImageDTO);
	        }
	      }
	    
	    /*** SUMMERNOTE_IMAGE_T ***/
		
		// 응답
	    try {
	        
	        response.setContentType("text/html; charset=UTF-8");
	        PrintWriter out = response.getWriter();
	        out.println("<script>");
	        if(addResult == 1) {
	          out.println("alert('블로그가 작성되었습니다.');");
	          out.println("location.href='" + request.getContextPath() + "/blog/list.do';");
	        } else {
	          out.println("alert('블로그 작성이 실패했습니다.');");
	          out.println("history.back();");
	        }
	        out.println("</script>");
	        out.flush();
	        out.close();
	        
	      } catch(Exception e) {
	        e.printStackTrace();
	      }
		}

	    
	
	@Override
	public Map<String, Object> imageUpload(MultipartHttpServletRequest multipartRequest) {
		
		/*
		 	var formData = new FormData();
		 	formData.append('file', 첨부된파일); 여기서 file이름이 아래 getFile("file")에서 쓰인다.
		 	
		 	$.ajax({
		 	 data: formData
		 	 })
		 */
		
		// formData에 저장된 file 꺼내기
		// 스프링에서는 첨부된파일 타입은 multipartFile이다.
	    MultipartFile multipartFile = multipartRequest.getFile("file");
		
		// HDD 저장할 경로
	    String summernoteImagePath = myFileUtil.getSummernoteImagePath();
		
		// HDD 저장할 경로 없으면 만들기
	    File dir = new File(summernoteImagePath);
	    if(dir.exists() == false) {
	      dir.mkdirs();
	    }
		
		// HDD 저장할 파일의 이름(UUID.확장자)
	    String filesystemName = myFileUtil.getFilesystemName(multipartFile.getOriginalFilename()); // 원래 파일이름(getOriginalFilename)의 확장자 앞에 UUID값을 붙여서 저장을 할것이다. 
		
		// HDD 저장
	    try {
	        File file = new File(dir, filesystemName);
	        multipartFile.transferTo(file);
	      } catch(Exception e) {
	        e.printStackTrace();
	      }
		
		// HDD에 저장된 파일의 확인을 위한 mapping 값을 반환(servlet-context.xml 참고)
	    Map<String, Object> map = new HashMap<String, Object>();
	    map.put("src", multipartRequest.getContextPath() + "/imageLoad/" + filesystemName);
	    
	    return map;
	    
	  }
	
	@Override
	public int increaseHit(int blogNo) {
		return blogMapper.increaseHit(blogNo);
	}

	@Override
	public void loadBlog(int blogNo, Model model) {
		model.addAttribute("blog", blogMapper.getBlogByNo(blogNo));
	}
}
