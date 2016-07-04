package com.uspaceacademy.view;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;
//DownloadView
//영주 assignment에~

/*
 * 		View 클래스 정의
 * 		- 1.implements View , extends AbstractView
 */


public class DownloadView extends AbstractView{
	
	
	//응답 Content Type을 리턴
	public String getContentType(){
		return "application/octet-stream";//타입을 명확히 지정하지 않겠다. -> 웹브라우저가 다운받게 하겠다.
	}
	
	
	/* 	응답처리 구현 메소드
	 * 		매개변수 : 1.Map model - handler에서 전달한 Model 값(ModelAndView의 Model)
	 */
	public void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		String fileName = (String)model.get("downFile");
		String downFileName = new String(fileName.getBytes("euc-kr"), "8859_1"); //파일명 한글 처리
		
		//응답헤더 설정
		//content type 설정
		response.setContentType(getContentType());
		//다운로드될 파일의 파일명 지정
		response.setHeader("content-disposition", "attachment;filename="+downFileName);
	
		//Stream을 이용해 파일 Cilent(Web browser)로 전동
		String dir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
		//String dir = "C:\\java\\temp"; //파일저장디렉토리
		
		FileInputStream fi = new FileInputStream(new File(dir, fileName));
		OutputStream os = response.getOutputStream();
		FileCopyUtils.copy(fi, os); // fi stream에서 읽을 것을 os strean으로 카피(출력)
		
	}
}































