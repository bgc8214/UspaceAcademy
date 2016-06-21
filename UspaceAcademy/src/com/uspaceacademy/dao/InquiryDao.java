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
	
	public int insertInquiry(Inquiry inquiry){		
		return session.insert("inquiryMapper.insertInquiry", inquiry);
		
	}
	
	public int updateInquiry(Inquiry inquiry){
		return session.update("inquiryMapper.updateInquiry", inquiry);
	}
	
	public int deleteInquiryByAdvancedNo(int advancedNo){
		return session.delete("inquiryMapper.deleteInquiryByAdvancedNo", advancedNo);
	}	
	
	public List<Inquiry> selectAllInquirys(){
		return session.selectList("inquiryMapper.selectAllInquirys");
	}
	
	public List<Inquiry> selectInquiryByAdvancedId(String advancedId){
		return session.selectList("inquiryMapper.selectInquiryByAdvancedId", advancedId);
	}
}
