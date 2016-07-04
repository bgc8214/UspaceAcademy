package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryCommentService;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Comment;

@Controller
@RequestMapping("/comment")
public class InquiryCommentController {
	
	private String value;
	
	@Autowired
	private InquiryCommentService service;
	
	
	
/*	//댓글 등록 폼
	@RequestMapping("/insertCommentForm")
	public ModelAndView insertCommentForm(String codeName){
		List codeList = service.selectCode("comment_board");
		for(int i=0; i<codeList.size(); i++) {
			Code code = (Code) codeList.get(i);
			if(codeName.equals(code.getCodeName())) {
				value = code.getCodeName();
			}
		}
		return new ModelAndView("comment/comment_insert.tiles", "codeName", value);
	}
	
	//댓글 등록하기
	@RequestMapping("/insertComment")
	public ModelAndView insertComment(String codeName, String commentContent){
		int commentNo = service.increaseCommentNo();
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		
		Comment comment = new Comment(commentNo, commentContent, commentDate, "홍길동", 1);
		service.insertInquiryComment(comment);
		
		return new ModelAndView("inquiry/inquiry_detail.tiles", "registerComment", comment);
	}
	
	@RequestMapping("/exInsertComment")
	@ResponseBody
	public String exInsertComment(String commentContent, String inquiryNo, String commentNo){
		
		
		return commentContent;
	}*/
	
	
}
