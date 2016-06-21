package com.uspaceacademy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryService;
import com.uspaceacademy.vo.Inquiry;

@Controller
@RequestMapping("/inquiry")
public class InquiryController
{
	@Autowired
	private InquiryService service;
	
	@RequestMapping("/insertInquiry")
	public ModelAndView insertInquiry(Inquiry inquiry){
		int insertInquiry = service.insertInquiry(inquiry);
		return new ModelAndView("/Inquiry/insert_inquiry.tiles", "insertInquiry", insertInquiry);
	}
	
	@RequestMapping("/deleteInquiryByAdvancedNo")
	public ModelAndView deleteInquiryByAdvancedNo(int advancedNo){
		int deleteInquiry = service.deleteInquiryByAdvancedNo(advancedNo);
		return new ModelAndView("/inquiry/inquiry_detail.tiles", "deleteList", deleteInquiry);
	}
	
	@RequestMapping("/inquiryList")
	public ModelAndView inquiryList(){
		System.out.println("전체조회");
		List list = service.selectAllInquirys();
		
		return new ModelAndView("Inquiry/Inquiry_list.tiles", "list", list);
	}
}
