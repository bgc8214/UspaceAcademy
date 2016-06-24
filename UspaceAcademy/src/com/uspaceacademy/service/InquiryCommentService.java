package com.uspaceacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.InquiryCommentDao;
import com.uspaceacademy.vo.Comment;

@Service
public class InquiryCommentService {
	
	@Autowired
	private InquiryCommentDao dao;
	
	//코드 
	public List selectCode(String codeType){
		return dao.selectCode(codeType);
	}
	
	//등록하기
	public int insertInquiryComment(Comment comment){
		return dao.insertComment(comment);
	}
	
	//상세조회
	public Comment selectByCommentNo(int commentNo){
		return dao.selectByCommentNo(commentNo);
	}
	
	//수정하기
	public int updateComment(Comment comment){
		return dao.updateComment(comment);
	}
	
	//삭제하기
	public int deleteByCommentNo(int commentNo){
		return dao.deleteByCommentNo(commentNo);
	}
	
	//댓글 번호 sequence
	public int increaseCommentNo(){
		return dao.increaseCommentNo();
	}
}
