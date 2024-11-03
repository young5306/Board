package com.ssafy.mvc.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class FileDownloadView extends AbstractView {

	private ResourceLoader resourceLoader;
	
	@Autowired
	public FileDownloadView(ResourceLoader resourceLoader) {
		this.resourceLoader = resourceLoader;
	}

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 준비
		String fileName = (String) model.get("fileName"); // 파일명 가져오기
		Resource resource = resourceLoader.getResource("classpath:/static/img"); // 파일들 저장하는 경로 가져오기
		File file = new File(resource.getFile(), fileName); // getFile(디렉터리 경로) 안에 있는 fileName으로 지정된 파일을 가리키는 객체 생성 
		
		// 응답 헤더 설정
		fileName = new String(fileName.getBytes("UTF-8"), "ISO-8859-1"); // 파일명 안깨지게 변경 (생략 가능)
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + fileName + "\";"); // 다운로드 명시
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		// 파일 보내기
		// FileInputStream fis = new FileInputStream(file); 서버로 파일 읽어오는 통로 만들기
		// OutputStream os = response.getOutputStream(); 클라이언트에 데이터 전송하는 통로 
		// FileCopyUtils.copy(fis, os); // fis로 들어온 데이터를 os에 실어서 보낸다.
		
		//  try with resources : 자동으로 리소스 관리 하게 해줌
		try (FileInputStream fis = new FileInputStream(file); OutputStream os = response.getOutputStream();) {
			FileCopyUtils.copy(fis, os);
		}
		
	}
	

}
