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

@Repository
public class InquiryDao
{
	@Autowired
	private SqlSessionTemplate session;
	
	public InquiryDao(){}
	
	public InquiryDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	//전체 조회
	public List<Inquiry> selectAllInquirys(){
		return session.selectList("inquiryMapper.selectAllInquirys");
	}
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){		
		return session.insert("inquiryMapper.insertInquiry", inquiry);
	}
	
	//상세 조회
	public Inquiry selectByAdvancedNo(String advancedType, int advancedNo){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		
		return session.selectOne("inquiryMapper.selectByAdvancedNo", map);
	}
	
	//조회수
	public int updateHit(Inquiry inquiry) {
		return session.update("inquiryMapper.updateHit", inquiry);
	}
	
	//수정하기
	public int updateInquiry(Inquiry inquiry){
		return session.update("inquiryMapper.updateInquiry", inquiry);
	}
	
	//삭제하기
	public int deleteByAdvancedNo(int advancedNo){
		return session.delete("inquiryMapper.deleteByAdvancedNo", advancedNo);
	}	
		
	//글 번호 sequence
	public int increaseAdvancedNo(){
		return session.selectOne("inquiryMapper.increaseAdvancedNo");
	}
	
	//코드 조회
	public List selectByCodeName(String codeName) {
		return session.selectList("codeTable.selectByCodeName", codeName);
	}
	
	//페이징 처리
	public List selectList(int page, String advancedType) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		return session.selectList("inquiryMapper.selectListByPaging", map);
	}
	
	//페이징 처리
	public int selectCountContents(String advancedType) {
		return session.selectOne("inquiryMapper.selectCountContents", advancedType);
	}
	
	//제목으로 검색 페이징 조회
	public List selectByTitle(String advancedTitle, String advancedType, int page) {
		Map map = new HashMap();
		map.put("advancedTitle", advancedTitle);
		map.put("advancedType", advancedType);	
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("page", page);				
		return session.selectList("inquiryMapper.selectByTitle", map);
	}
	
	//제목으로 검색 페이징 조회 헬퍼
	public int selectByTitleCountContents(String advancedTitle, String advancedType) {
		System.out.println(advancedTitle);
		Map map = new HashMap();
		map.put("advancedTitle", advancedTitle);
		map.put("advancedType", advancedType);
		return session.selectOne("inquiryMapper.selectByTitleCountContents", map);
	}
	
	//제목 검색 테스트
	public List searchByTitle(String title){
		return session.selectList("inquiryMapper.searchByTitle", title);
	}
	
	
	//글번호로 댓글 조회
	public Inquiry selectJoinByAdvancedNo(String advancedType, int advancedNo){
		HashMap map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		
		return session.selectOne("inquiryMapper.selectJoinByAdvancedNo", map);
	}
	
	//댓글 번호 sequence
	public int increaseCommentNo(){
		return session.selectOne("inquiryMapper.increaseCommentNo");
	}
	

	//code 조회
	public List selectCode(String codeType){
		return session.selectList("codeTable.selectCodeName", codeType);
	}
	
	//댓글 등록
	public int insertComment(Comment comment){		
		
		return session.insert("inquiryMapper.insertComment", comment);
	}
	
	//상세 조회
	public Comment selectByCommentNo(String commentType, int commentNo){
		HashMap map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("commentNo", commentNo);
		
		return session.selectOne("inquiryMapper.selectByCommentNo", map);
	}
	
	//댓글 수정
	public int updateComment(Comment comment){
		return session.update("inquiryMapper.updateComment", comment);
	}
	
	//댓글 삭제
	public int deleteComment(String commentType, int commentNo){
		HashMap map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("commentNo", commentNo);
		
		return session.delete("inquiryMapper.deleteComment", map);
	}
	
}
