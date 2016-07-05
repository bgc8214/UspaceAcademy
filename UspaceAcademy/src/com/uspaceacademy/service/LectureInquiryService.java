package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uspaceacademy.dao.LectureInquiryDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.LectureInquiry;

@Service
@Transactional
public class LectureInquiryService {
	
	@Autowired
	private LectureInquiryDao dao;
	
	//등록하기
	public int insertLectureInquiry(LectureInquiry lectureInquiry){
		
		return dao.insertLectureInquiry(lectureInquiry);
	}	
	
	//글 번호 sequence
	public int increaseAdvancedNo(){
		
		return dao.increaseAdvancedNo();
	}
	
	//등록 상세조회
	public LectureInquiry selectByAdvancedNo(String advancedType, int advancedNo, int lectureNo2){
		
		return dao.selectByAdvancedNo(advancedType, advancedNo, lectureNo2);
	}
	
	//목록 상세조회
	public LectureInquiry selectByAdvancedNoWithComment(String advancedType, int advancedNo, int lectureNo2){
		
		return dao.selectByAdvancedNoWithComment(advancedType, advancedNo, lectureNo2);
	}
	
	//조회수
	public int updateAdvancedHit(LectureInquiry inquiry){
		
		return dao.updateAdvancedHit(inquiry);
	}
	
	
	
	//글번호로 댓글 상세조회
	public List commentList(String advancedType, int advancedNo2){
		
		return dao.commentList(advancedType, advancedNo2);
	}
	
	//댓글번호로 댓글 상세조회
	public Comment selectByCommentNo(String commentType, int commentNo, int advancedNo2){
		
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
	public int deleteComment(String commentType, int commentNo, int advancedNo2){		
		
		return dao.deleteComment(commentType, commentNo, advancedNo2);
	}
	
	
	//글 수정하기
	public int updateLectureInquiry(LectureInquiry lectureInquiry){
		
		return dao.updateLectureInquiry(lectureInquiry);
	}
	
	//글 삭제하기
	public int deleteLectureInquiry(String advancedType, int advancedNo, int lectureNo2){
		
		return dao.deleteLectureInquiry(advancedType, advancedNo, lectureNo2);
	}

	//강의 목록 페이징 처리
	public Map selectAllByPaging(int page, int lectureNo2) {
		String advancedType = "강의질문";
		
		Map map = new HashMap();
		map.put("lectureInquiryList", dao.selectAllByPaging(page, advancedType, lectureNo2));
		map.put("paging", new PagingBean(dao.selectAllCountContents(advancedType, lectureNo2), page));
//		map.put("rowList", dao.selectAllByPagingRownum(page, advancedType, lectureNo2));
		
		return map;
	}
	
	//제목으로 검색 페이징 처리
	public Map selectTitleByPaging(int page, String advancedTitle, int lectureNo2) {
		String advancedType = "강의질문";
		
		Map map = new HashMap();
		map.put("lectureInquiryList", dao.selectTitleByPaging(page, advancedType, advancedTitle, lectureNo2));
		map.put("paging", new PagingBean(dao.selectTitleCountContents(advancedType, advancedTitle, lectureNo2), page));
		
		return map;
	}
	
	//내용으로 검색 페이징 처리
	public Map selectContentByPaging(int page, String advancedContent, int lectureNo2) {
		String advancedType = "강의질문";
		
		Map map = new HashMap();
		map.put("lectureInquiryList", dao.selectContentByPaging(page, advancedType, advancedContent, lectureNo2));
		map.put("paging", new PagingBean(dao.selectContentCountContents(advancedType, advancedContent, lectureNo2), page));
		
		return map;
	}
	
	//글쓴이로 검색 페이징 처리
	public Map selectIdByPaging(int page, String advancedId, int lectureNo2) {
		String advancedType = "강의질문";
		
		Map map = new HashMap();
		map.put("lectureInquiryList", dao.selectContentByPaging(page, advancedType, advancedId, lectureNo2));
		map.put("paging", new PagingBean(dao.selectContentCountContents(advancedType, advancedId, lectureNo2), page));
		
		return map;
	}
	
	
	
	//전체 조회
	public List<LectureInquiry> selectAllInquirys(){		
		return dao.selectAllInquirys();
	}	
	
	//코드
	public List selectCodeName(String codeName) {
		return dao.selectCodeName(codeName);
	}
	
}
