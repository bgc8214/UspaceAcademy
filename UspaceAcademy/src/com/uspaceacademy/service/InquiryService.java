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
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){
		int insertInquiry = dao.insertInquiry(inquiry);
		return insertInquiry;
	}
	
	
	public int inquiryAdvancedNo() {
		return dao.inquiryAdvancedNo();
	}
	
	
	//삭제하기
	public int deleteInquiryByAdvancedNo(int advancedNo){
		int deleteInquiry = dao.deleteInquiryByAdvancedNo(advancedNo);
		return deleteInquiry;
	}
	
	//수정하기
	public int updateInquiryByAdvancedNo(int advancedNo){
		int updateInquiry = dao.updateInquiryByAdvancedNo(advancedNo);
		return updateInquiry;
	}
	
	//전체 조회
	public List<Inquiry> selectAllInquirys(){
	
		List<Inquiry> list = dao.selectAllInquirys();
		
		return list;
	}
	
	//상세조회
	public Inquiry selectInquiryByAdvancedNo(int advancedNo){
		return dao.selectInquiryByAdvancedNo(advancedNo);
	}
	
	//글번호 sequence
	public int increaseAdvancedNo(){
		return dao.increaseAdvancedNo();
	}
	
	//코드
	public List selectCodeName(String codeName) {
		return dao.selectCodeName(codeName);
	}
}