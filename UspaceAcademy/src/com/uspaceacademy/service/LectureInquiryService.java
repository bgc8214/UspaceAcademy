package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uspaceacademy.dao.LectureInquiryDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Inquiry;
import com.uspaceacademy.vo.LectureInquiry;

@Service
@Transactional
public class LectureInquiryService {
	
	@Autowired
	private LectureInquiryDao dao;
	
	//전체 조회
	public List<LectureInquiry> selectAllInquirys(){		
		return dao.selectAllInquirys();
	}
	
	//등록하기
	public int insertInquiry(LectureInquiry inquiry){
		return dao.insertInquiry(inquiry);
	}	
	
	//상세조회
	public LectureInquiry selectByAdvancedNo(int advancedNo){
		return dao.selectByAdvancedNo(advancedNo);
	}
	
	//조회수
	public int updateHit(LectureInquiry inquiry){
		return dao.updateHit(inquiry);
	}
	
	//수정하기
	public int updateInquiry(LectureInquiry inquiry){
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
	public List selectCodeName(String codeName) {
		return dao.selectCodeName(codeName);
	}
	
	//강의 목록 페이징 처리
	public Map getInquiryList(int page) {
		Map map = new HashMap();
		map.put("inquiryList", dao.selectList(page));
		map.put("paging", new PagingBean(dao.selectCountContents(), page));
		return map;
	}
}
