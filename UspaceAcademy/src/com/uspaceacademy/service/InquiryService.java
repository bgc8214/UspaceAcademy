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
	
	public int insertInquiry(){
//		Inquiry inquiry = ;
		int insertInquiry = dao.insertInquiry(new Inquiry(2, 1, "제목2", "내용2", "2016-05-23", 1, "id-13", 1));
		return insertInquiry;
	}
	
	public int deleteInquiryByAdvancedNo(int advancedNo){
		int deleteInquiry = dao.deleteInquiryByAdvancedNo(advancedNo);
		return deleteInquiry;
	}
	
	public List<Inquiry> selectAllInquiry(){
	
		List<Inquiry> list = dao.selectAllInquirys();
		
		for(Inquiry i : list){
			System.out.println(i);
		}
		
		return list;
	}
}
