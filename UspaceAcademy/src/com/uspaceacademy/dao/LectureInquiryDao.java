package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.LectureInquiry;

@Repository
public class LectureInquiryDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public LectureInquiryDao(){}
	
	public LectureInquiryDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	//등록하기
	public int insertLectureInquiry(LectureInquiry lectureInquiry){		
		return session.insert("lectureInquiryMapper.insertLectureInquiry", lectureInquiry);
	}
	
	//글 번호 sequence
	public int increaseAdvancedNo(){
		
		return session.selectOne("lectureInquiryMapper.increaseAdvancedNo");
	}
	
	//등록 상세 조회
	public LectureInquiry selectByAdvancedNo(String advancedType, int advancedNo, int lectureNo2){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectOne("lectureInquiryMapper.selectByAdvancedNo", map);
	}
	
	//목록 상세 조회
	public LectureInquiry selectByAdvancedNoWithComment(String advancedType, int advancedNo, int lectureNo2){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectOne("lectureInquiryMapper.selectByAdvancedNoWithComment", map);
	}
	
	//조회수
	public int updateAdvancedHit(LectureInquiry lectureInquiry) {
		
		return session.update("lectureInquiryMapper.updateAdvancedHit", lectureInquiry);
	}
	
	
	
	//글번호로 댓글 상세 조회
	public List commentList(String commentType, int advancedNo2){
		Map map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("advancedNo2", advancedNo2);
		
		return session.selectList("lectureInquiryMapper.commentList", map);
	}
	
	//댓글no로 검색
	public Comment selectByCommentNo(String commentType, int commentNo, int advancedNo2){
		Map map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("commentNo", commentNo);
		map.put("advancedNo2", advancedNo2);
		
		return session.selectOne("lectureInquiryMapper.selectByCommentNo", map);
	}
	
	//댓글 등록
	public int insertComment(Comment comment){		
		
		return session.insert("lectureInquiryMapper.insertComment", comment);
	}
	
	//댓글 번호 sequence
	public int increaseCommentNo(){
		return session.selectOne("inquiryMapper.increaseCommentNo");
	}
	
	//댓글 수정
	public int updateComment(Comment comment){
		
		return session.update("lectureInquiryMapper.updateComment", comment);
	}
	
	//댓글 삭제
	public int deleteComment(String commentType, int commentNo, int advancedNo2){	
		
		Map map = new HashMap<>();
		map.put("commentType", commentType);
		map.put("commentNo", commentNo);
		map.put("advancedNo2", advancedNo2);
		
		return session.insert("lectureInquiryMapper.deleteComment", map);
	}
	
	
	
	//수정하기
	public int updateLectureInquiry(LectureInquiry lectureInquiry){
		
		return session.update("lectureInquiryMapper.updateLectureInquiry", lectureInquiry);
	}
	
	//삭제하기
	public int deleteLectureInquiry(String advancedType, int advancedNo, int lectureNo2){
		Map map = new HashMap<>();
		map.put("advancedType", advancedType);
		map.put("advancedNo", advancedNo);
		map.put("lectureNo2", lectureNo2);
		
		return session.delete("lectureInquiryMapper.deleteLectureInquiry", map);
	}	
	
	//페이징 처리
	public List selectAllByPaging(int page, String advancedType, int lectureNo2){	
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectList("lectureInquiryMapper.selectAllByPaging", map);
	}
	
	//페이징 처리 헬퍼
	public int selectAllCountContents(String advancedType, int lectureNo2){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectOne("lectureInquiryMapper.selectAllCountContents", map);
	}
	
	//페이징 처리(제목으로 검색)
	public List selectTitleByPaging(int page, String advancedType, String advancedTitle, int lectureNo2){	
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("advancedTitle", advancedTitle);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectList("lectureInquiryMapper.selectTitleByPaging", map);
	}
	
	//페이징 처리 헬퍼(제목으로 검색)
	public int selectTitleCountContents(String advancedType, String advancedTitle, int lectureNo2){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedTitle);
		
		return session.selectOne("lectureInquiryMapper.selectTitleCountContents", map);
	}
	
	//페이징 처리(내용으로 검색)
	public List selectContentByPaging(int page, String advancedType, String advancedContent, int lectureNo2){
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedContent);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectList("lectureInquiryMapper.selectContentByPaging", map);
	}
	
	//페이징 처리 헬퍼(내용으로 검색)
	public int selectContentCountContents(String advancedType, String advancedContent, int lectureNo2){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedContent);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectOne("lectureInquiryMapper.selectContentCountContents", map);
	}
	
	//페이징 처리(글쓴이로 검색)
	public List selectIdByPaging(int page, String advancedType, String advancedId, int lectureNo2){
		
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedId);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectList("lectureInquiryMapper.selectIdByPaging", map);
	}
	
	//페이징 처리 헬퍼(글쓴이로 검색)
	public int selectIdCountContents(String advancedType, String advancedId, int lectureNo2){
		
		Map map = new HashMap();
		
		map.put("advancedType", advancedType);
		map.put("advancedContent", advancedId);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectOne("lectureInquiryMapper.selectIdCountContents", map);
	}
	
	
	
	//전체 조회
	public List<LectureInquiry> selectAllInquirys(){
		return session.selectList("lectureInquiryMapper.selectAllInquirys");
	}
	
	//코드 조회
	public List selectCodeName(String codeName){
		
		return session.selectList("codeTable.selectCodeName", codeName);
	}

	public List selectAllByPagingRownum(int page, String advancedType, int lectureNo2) {
		Map map = new HashMap();
		
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("advancedType", advancedType);
		map.put("lectureNo2", lectureNo2);
		
		return session.selectList("lectureInquiryMapper.selectRownum", map);
	}
}
