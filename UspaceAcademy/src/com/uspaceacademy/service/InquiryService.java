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
	
	public int insertInquiry(Inquiry inquiry){
		int insertInquiry = dao.insertInquiry(inquiry);
		return insertInquiry;
	}
	
	public int deleteInquiryByAdvancedNo(int advancedNo){
		int deleteInquiry = dao.deleteInquiryByAdvancedNo(advancedNo);
		return deleteInquiry;
	}
	
	public List<Inquiry> selectAllInquirys(){
	
		List<Inquiry> list = dao.selectAllInquirys();
		
		return list;
	}
}
