package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uspaceacademy.dao.InquiryDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.Inquiry;

@Service
@Transactional
public class InquiryService {
	
	@Autowired
	private InquiryDao dao;
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){
		
		return dao.insertInquiry(inquiry);
	}	
	
	//글 번호 sequence
	public int increaseAdvancedNo(){
		
		return dao.increaseAdvancedNo();
	}
	
	//등록, 수정 상세조회
	public Inquiry selectByAdvancedNo(int advancedNo){
		String advancedType = "1:1문의";
		
		return dao.selectByAdvancedNo(advancedType, advancedNo);
	}
	
	//목록 상세조회
	public Inquiry selectByAdvancedNoWithComment(int advancedNo){
		String advancedType = "1:1문의";
		
		return dao.selectByAdvancedNoWithComment(advancedType, advancedNo);
	}
	
	//조회수
	public int updateAdvancedHit(Inquiry inquiry){
		
		return dao.updateAdvancedHit(inquiry);
	}
	
	
	
	//글번호로 댓글 상세조회
	public List commentList(int advancedNo2){
		String commentType = "1:1문의댓글";
		
		return dao.commentList(commentType, advancedNo2);
	}
	
	//댓글번호로 댓글 상세조회
	public Comment selectByCommentNo(int commentNo, int advancedNo2){
		String commentType = "1:1문의댓글";
		
		return dao.selectByCommentNo(commentType, commentNo, advancedNo2);
	}
	
	//댓글 등록하기
	public int insertComment(Comment comment){
		
		return dao.insertComment(comment);
	}
	
	//댓글 번호 sequence
	public int increaseCommentNo(){
		
		return dao.increaseCommentNo();
	}
	
	//댓글 수정하기
	public int updateComment(Comment comment){		
		
		return dao.updateComment(comment);
	}
	
	//댓글 삭제하기
	public int deleteComment(int commentNo, int advancedNo2){
		String commentType = "1:1문의댓글";
		
		return dao.deleteComment(commentType, commentNo, advancedNo2);
	}
	
	
	//글 수정하기
	public int updateInquiry(Inquiry inquiry){
		
		return dao.updateInquiry(inquiry);
	}
	
	//글 삭제하기
	public int deleteInquiry(int advancedNo){
		String advancedType = "1:1문의";
		
		return dao.deleteInquiry(advancedType, advancedNo);
	}

	//강의 목록 페이징 처리
	public Map selectAllByPaging(int page) {
		String advancedType = "1:1문의";
		
		Map map = new HashMap();
		map.put("inquiryList", dao.selectAllByPaging(page, advancedType));
		map.put("paging", new PagingBean(dao.selectAllCountContents(advancedType), page));
		
		return map;
	}
	
	//제목으로 검색 페이징 처리
	public Map selectTitleByPaging(int page, String advancedTitle) {
		String advancedType = "1:1문의";
		
		Map map = new HashMap();
		map.put("inquiryList", dao.selectTitleByPaging(page, advancedType, advancedTitle));
		map.put("paging", new PagingBean(dao.selectTitleCountContents(advancedType, advancedTitle), page));
		
		return map;
	}
	
	//내용으로 검색 페이징 처리
	public Map selectContentByPaging(int page, String advancedContent) {
		String advancedType = "1:1문의";
		
		Map map = new HashMap();
		map.put("inquiryList", dao.selectContentByPaging(page, advancedType, advancedContent));
		map.put("paging", new PagingBean(dao.selectContentCountContents(advancedType, advancedContent), page));
		
		return map;
	}
	
	//글쓴이로 검색 페이징 처리
	public Map selectIdByPaging(int page, String advancedId) {
		String advancedType = "1:1문의";
		
		Map map = new HashMap();
		map.put("inquiryList", dao.selectContentByPaging(page, advancedType, advancedId));
		map.put("paging", new PagingBean(dao.selectContentCountContents(advancedType, advancedId), page));
		
		return map;
	}
	
}
