package com.uspaceacademy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	
	public int inquiryAdvancedNo(){		
		return session.selectOne("inquiryMapper.inquiryAdvancedNo");	
	}
	
	//등록하기
	public int insertInquiry(Inquiry inquiry){		
		return session.insert("inquiryMapper.insertInquiry", inquiry);
		
	}
	
	//삭제하기
	public int deleteInquiryByAdvancedNo(int advancedNo){
		return session.delete("inquiryMapper.deleteInquiryByAdvancedNo", advancedNo);
	}	
	
	//수정하기
	public int updateInquiryByAdvancedNo(int advancedNo){
		return session.update("inquiryMapper.updateInquiryByAdvancedNo", advancedNo);
	}
	
	//전체 조회
	public List<Inquiry> selectAllInquirys(){
		return session.selectList("inquiryMapper.selectAllInquirys");
	}
	
	//상세 조회
	public Inquiry selectInquiryByAdvancedNo(int advancedNo){
		return session.selectOne("inquiryMapper.selectInquiryByAdvancedNo", advancedNo);
	}
	
	//코드 조회
	public List selectCodeName(String codeName) {
		return session.selectList("codeTable.selectCodeName", codeName);
	}
	
	//글번호sequence
	public int increaseAdvancedNo(){
		return session.selectOne("inquiryMapper.increaseAdvancedNo");
	}
}
