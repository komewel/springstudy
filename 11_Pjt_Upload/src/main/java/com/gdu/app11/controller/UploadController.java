package com.gdu.app11.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gdu.app11.service.UploadService;

@RequestMapping("/upload")
@Controller
public class UploadController {

	// field
	@Autowired
	private UploadService uploadService;
	
	@GetMapping("/list.do")
	public String list(HttpServletRequest request, Model model) {
		uploadService.getUploadList(request, model);
		return "upload/list";
	}
	
	@GetMapping("/write.do")
	public String write() {
		return "upload/write";
	}
	
	@PostMapping("/add.do")
	public String add(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int uploadResult = uploadService.addUpload(multipartRequest);
		redirectAttributes.addFlashAttribute("uploadResult", uploadResult);
		return "redirect:/upload/list.do";
	}
	
	@GetMapping("/detail.do")
	public String detail(@RequestParam(value="uploadNo", required=false, defaultValue="0") int uploadNo
			               , Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/detail";
	}
	
	@GetMapping("/display.do")
	public ResponseEntity<byte[]> display(@RequestParam("attachNo") int attachNo) {
		return uploadService.display(attachNo);
	}
	
	@GetMapping("/download.do")
	public ResponseEntity<Resource> download(@RequestParam("attachNo") int attachNo, @RequestHeader("User-Agent") String userAgent) {
		return uploadService.download(attachNo, userAgent);
	}
	// f12를 눌러 확인해보면 request-header 항목에 user-agent 값을 보면 어떤 브라우저로 접속했는지 알수있다. IE와 EDGE로 접속할때 if 처리해줄것이 있다. 
	// 당장에 도움은 안될지 몰라도 인수가 적을수록 좋은 메소드이다.
	
	@GetMapping("/downloadAll.do")
	public ResponseEntity<Resource> downloadAll(@RequestParam("uploadNo") int uploadNo) {
		return uploadService.downloadAll(uploadNo);
	}
	
	@PostMapping("/removeUpload.do")
	public String removeUpload(@RequestParam("uploadNo") int uploadNo, RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("removeResult", uploadService.removeUpload(uploadNo));
		return "redirect:/upload/list.do";
	}
	
	@PostMapping("/editUpload.do")
	public String editUpload(@RequestParam("uploadNo") int uploadNo, Model model) {
		uploadService.getUploadByNo(uploadNo, model);
		return "upload/edit";
	}
	
	@PostMapping("/modify.do")
	public String modify(MultipartHttpServletRequest multipartRequest, RedirectAttributes redirectAttributes) {
		int modifyResult = uploadService.modifyUpload(multipartRequest);
		redirectAttributes.addFlashAttribute("modifyResult", modifyResult);
		return "redirect:/upload/detail.do?uploadNo=" + multipartRequest.getParameter("uploadNo");
	}
	
	@GetMapping("/removeAttach.do")
	public String removeAttach(@RequestParam("uploadNo") int uploadNo, @RequestParam("attachNo") int attachNo) {
		uploadService.removeAttach(attachNo);
		return "redirect:/upload/detail.do?uploadNo=" + uploadNo;
	}
	
}
	
	
	
	
	
	
	
