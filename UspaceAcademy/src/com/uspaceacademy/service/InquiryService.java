package com.uspaceacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.InquiryDao;
import com.uspaceacademy.vo.Inquiry;

@Service
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
	public Inquiry selectByAdvancedNo(int advancedNo){
		return dao.selectByAdvancedNo(advancedNo);
	}
	
	//조회수
	public int selectHit(int advancedNo){
		return dao.selectHit(advancedNo);
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
	public List selectCodeName(String codeName) {
		return dao.selectCodeName(codeName);
	}
}