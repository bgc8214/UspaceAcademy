package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.LectureInquiry;

@Repository
public class LectureInquiryDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public LectureInquiryDao(){}
	
	public LectureInquiryDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	//전체 조회
	public List<LectureInquiry> selectAllInquirys(){
		return session.selectList("lectureInquiryMapper.selectAllInquirys");
	}
	
	//등록하기
	public int insertInquiry(LectureInquiry inquiry){		
		return session.insert("lectureInquiryMapper.insertInquiry", inquiry);
	}
	
	//상세 조회
	public LectureInquiry selectByAdvancedNo(int advancedNo){
		return session.selectOne("lectureInquiryMapper.selectByAdvancedNo", advancedNo);
	}
	
	//조회수
	public int updateHit(LectureInquiry inquiry) {
		return session.update("lectureInquiryMapper.updateHit", inquiry);
	}
	
	//수정하기
	public int updateInquiry(LectureInquiry inquiry){
		return session.update("lectureInquiryMapper.updateInquiry", inquiry);
	}
	
	//삭제하기
	public int deleteByAdvancedNo(int advancedNo){
		return session.delete("lectureInquiryMapper.deleteByAdvancedNo", advancedNo);
	}	
		
	//글 번호 sequence
	public int increaseAdvancedNo(){
		return session.selectOne("lectureInquiryMapper.increaseAdvancedNo");
	}
	
	//코드 조회
	public List selectCodeName(String codeName) {
		return session.selectList("codeTable.selectCodeName", codeName);
	}
	
	//페이징 처리
	public List selectList(int page) {	
		Map map = new HashMap();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("lectureInquiryMapper.selectListByPaging", map);
	}
	
	//페이징 처리
	public int selectCountContents() {
		return session.selectOne("lectureInquiryMapper.selectCountContents");
	}
}
