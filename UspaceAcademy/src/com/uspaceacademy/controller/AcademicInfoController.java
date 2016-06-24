package com.uspaceacademy.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class AcademicInfoController
{
	@RequestMapping("/academyIntroduceUpload.do")
	public String academyIntroduceUpload(@RequestParam MultipartFile upImage,
			HttpServletRequest request) throws IllegalStateException, IOException
	{
		if(upImage!=null && !upImage.isEmpty())
		{
			String fileName = "academyIntroduce.jpg"; //업로드된 파일명
			long fileSize = upImage.getSize(); // 업로드된 파일 사이즈
			System.out.println(fileName+" "+fileSize);
			
			String dir = request.getServletContext().getRealPath("/image");
			File dest = new File(dir,fileName);
			upImage.transferTo(dest); 
			
		}
		return "main.tiles";
	}
	@RequestMapping("/roadIntroduceUpload.do")
	public String roadIntroduceUpload(@RequestParam MultipartFile upImage,
			HttpServletRequest request) throws IllegalStateException, IOException
	{
		if(upImage!=null && !upImage.isEmpty())
		{
			String fileName = "roadIntroduce.jpg"; //업로드된 파일명
			long fileSize = upImage.getSize(); // 업로드된 파일 사이즈
			System.out.println(fileName+" "+fileSize);
			
			String dir = request.getServletContext().getRealPath("/image");
			File dest = new File(dir,fileName);
			upImage.transferTo(dest); 
			
		}
		return "main.tiles";
	}
	@RequestMapping("/teacherIntroduceUpload.do")
	public String teacherIntroduceUpload(@RequestParam MultipartFile upImage,
			HttpServletRequest request) throws IllegalStateException, IOException
	{
		if(upImage!=null && !upImage.isEmpty())
		{
			String fileName = "teacherIntroduce.jpg"; //업로드된 파일명
			long fileSize = upImage.getSize(); // 업로드된 파일 사이즈
			System.out.println(fileName+" "+fileSize);
			
			String dir = request.getServletContext().getRealPath("/image");
			File dest = new File(dir,fileName);
			upImage.transferTo(dest); 
			
		}
		return "main.tiles";
	}
}
