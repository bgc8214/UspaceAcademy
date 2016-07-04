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
public class InquiryService
{
	@Autowired
	private InquiryDao dao;
	
	//전체 조회
	public List<Inquiry> selectAllInquirys(){		
		return dao.selectAllInquirys();
	}
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){
		return dao.insertInquiry(inquiry);
	}	
	
	//상세조회
	public Inquiry selectByAdvancedNo(String advancedType, int advancedNo){
		
		return dao.selectByAdvancedNo(advancedType, advancedNo);
	}
	
	//조회수
	public int updateHit(Inquiry inquiry){
		return dao.updateHit(inquiry);
	}
	
	//수정하기
	public int updateInquiry(Inquiry inquiry){
		return dao.updateInquiry(inquiry);
	}
	
	//삭제하기
	public int deleteByAdvancedNo(int advancedNo){
		return dao.deleteByAdvancedNo(advancedNo);
	}
	
	//글 번호 sequence
	public int increaseAdvancedNo(){
		return dao.increaseAdvancedNo();
	}
	
	//코드
	public List selectByCodeName(String codeName) {
		return dao.selectByCodeName(codeName);
	}
	
	//강의 목록 페이징 처리
	public Map getInquiryList(int page, String advancedType) {
		Map map = new HashMap();
		map.put("inquiryList", dao.selectList(page, advancedType));
		map.put("paging", new PagingBean(dao.selectCountContents(advancedType), page));
		return map;
	}
	
	//제목으로 검색 페이징 처리
	public Map selectByTitle(String advancedTitle, String advancedType, int page) {
		Map map = new HashMap();
		map.put("selectByTitle", dao.selectByTitle(advancedTitle, advancedType, page));
		map.put("paging", new PagingBean(page, dao.selectByTitleCountContents(advancedTitle, advancedType)));
		return map;
	}
	
	public Map searchByTitle(String title){
		Map map = new HashMap();
		map.put("inquiryList", dao.searchByTitle(title));
		map.put("title", title);
		
		return map;
	}
	
	//글번호로 댓글 조회
	public Inquiry selectJoinByAdvancedNo(String advancedType, int advancedNo){		
		return dao.selectJoinByAdvancedNo(advancedType, advancedNo);
	}
	
	//댓글 번호 sequence
	public int increaseCommentNo(){
		return dao.increaseCommentNo();
	}
	

	//코드 
	public List selectCode(String codeType){
		return dao.selectCode(codeType);
	}
	
	//등록하기
	public int insertComment(Comment comment){
		return dao.insertComment(comment);
	}
	
	//상세조회
	public Comment selectByCommentNo(String commentType, int commentNo){
		return dao.selectByCommentNo(commentType, commentNo);
	}
	
	//수정하기
	public int updateComment(Comment comment){
		return dao.updateComment(comment);
	}
	
	//삭제하기
	public int deleteComment(String commentType, int commentNo){
		return dao.deleteComment(commentType, commentNo);
	}
}