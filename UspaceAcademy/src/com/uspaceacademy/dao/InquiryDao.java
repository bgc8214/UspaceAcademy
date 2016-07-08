package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.Inquiry;
import com.uspaceacademy.vo.LectureInquiry;

@Repository
public class InquiryDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public InquiryDao(){}
	
	public InquiryDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){		
		return session.insert("inquiryMapper.insertInquiry", inquiry);
	}
	
	//글 번호 sequence
	public int increaseAdvancedNo(){
		
		return session.selectOne("inquiryMapper.increaseAdvancedNo");
	}
	
	//등록 상세 조회
	public Inquiry selectByAdvancedNo(String advancedType, int advancedNo){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		
		return session.selectOne("inquiryMapper.selectByAdvancedNo", map);
	}
	
	//목록 상세 조회
	public Inquiry selectByAdvancedNoWithComment(String advancedType, int advancedNo){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		
		return session.selectOne("inquiryMapper.selectByAdvancedNoWithComment", map);
	}
	
	//조회수
	public int updateAdvancedHit(Inquiry inquiry) {
		
		return session.update("inquiryMapper.updateAdvancedHit", inquiry);
	}
	
	
	
	//글번호로 댓글 상세 조회
	public List commentList(String commentType, int advancedNo2){
		Map map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("advancedNo2", advancedNo2);
		
		return session.selectList("inquiryMapper.commentList", map);
	}
	
	//댓글no로 검색
	public Comment selectByCommentNo(String commentType, int commentNo, int advancedNo2){
		Map map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("commentNo", commentNo);
		map.put("advancedNo2", advancedNo2);
		
		return session.selectOne("inquiryMapper.selectByCommentNo", map);
	}
	
	//댓글 등록
	public int insertComment(Comment comment){		
		
		return session.insert("inquiryMapper.insertComment", comment);
	}
	
	//댓글 번호 sequence
	public int increaseCommentNo(){
		return session.selectOne("inquiryMapper.increaseCommentNo");
	}
	
	//댓글 수정
	public int updateComment(Comment comment){
		
		return session.update("inquiryMapper.updateComment", comment);
	}
	
	//댓글 삭제
	public int deleteComment(String commentType, int commentNo, int advancedNo2){	
		
		Map map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("commentNo", commentNo);
		map.put("advancedNo2", advancedNo2);
		
		return session.insert("inquiryMapper.deleteComment", map);
	}
	
	
	
	//수정하기
	public int updateInquiry(Inquiry inquiry){
		
		return session.update("inquiryMapper.updateInquiry", inquiry);
	}
	
	//삭제하기
	public int deleteInquiry(String advancedType, int advancedNo){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		
		return session.delete("inquiryMapper.deleteInquiry", map);
	}	
	
	//페이징 처리
	public List selectAllByPaging(int page, String advancedType){	
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		
		return session.selectList("inquiryMapper.selectAllByPaging", map);
	}
	
	//페이징 처리 헬퍼
	public int selectAllCountContents(String advancedType){
		
		return session.selectOne("inquiryMapper.selectAllCountContents", advancedType);
	}
	
	//페이징 처리(제목으로 검색)
	public List selectTitleByPaging(int page, String advancedType, String advancedTitle){	
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("advancedTitle", advancedTitle);
		
		return session.selectList("inquiryMapper.selectTitleByPaging", map);
	}
	
	//페이징 처리 헬퍼(제목으로 검색)
	public int selectTitleCountContents(String advancedType, String advancedTitle){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedTitle);
		
		return session.selectOne("inquiryMapper.selectTitleCountContents", map);
	}
	
	//페이징 처리(내용으로 검색)
	public List selectContentByPaging(int page, String advancedType, String advancedContent){
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedContent);
		
		return session.selectList("inquiryMapper.selectContentByPaging", map);
	}
	
	//페이징 처리 헬퍼(내용으로 검색)
	public int selectContentCountContents(String advancedType, String advancedContent){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedContent);
		
		return session.selectOne("inquiryMapper.selectContentCountContents", map);
	}
	
	//페이징 처리(글쓴이로 검색)
	public List selectIdByPaging(int page, String advancedType, String advancedId){
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedId);
		
		return session.selectList("inquiryMapper.selectIdByPaging", map);
	}
	
	//페이징 처리 헬퍼(글쓴이로 검색)
	public int selectIdCountContents(String advancedType, String advancedId){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedId);
		
		return session.selectOne("inquiryMapper.selectIdCountContents", map);
	}

	//전체 조회
	public List<LectureInquiry> selectAllInquirys(){
		return session.selectList("inquiryMapper.selectAllInquirys");
	}
}
