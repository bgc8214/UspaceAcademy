package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryService;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Inquiry;

@Controller
@RequestMapping("/inquiry")
public class InquiryController
{
	private String value;
	
	@Autowired
	private InquiryService service;
	
	//1:1문의 등록하기
	@RequestMapping("/codeList.do")
	public ModelAndView selectCodeName(String codeName) {
		List codeList = service.selectCodeName("advanced_board");
		for(int i=0; i<codeList.size(); i++) {
			Code code = (Code) codeList.get(i);
			if(codeName.equals(code.getCodeName())) {
				value = code.getCodeName();
			}
		}
		return new ModelAndView("inquiry/insert_inquiry.tiles", "codeName", value);
	}
	
	//1:1문의 글을 등록했을 때 상세페이지 조회하기
	@RequestMapping("/insertInquiry")
	public ModelAndView insertInquiry(String advancedTitle, String advancedContent, String codeName){
		int advancedNo = service.increaseAdvancedNo();
		String date = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		Inquiry inquiry = new Inquiry(advancedNo, 1, advancedTitle, advancedContent, date, 1, "admin", -1);
		System.out.println(inquiry);
		int insertInquiry = service.insertInquiry(inquiry);
		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", inquiry);
	}
	
	//1:1문의 삭제하기
	@RequestMapping("/deleteInquiryByAdvancedNo")
	public ModelAndView deleteInquiryByAdvancedNo(String advancedNo){
		service.deleteInquiryByAdvancedNo(Integer.parseInt(advancedNo));
		List deleteInquiry = service.selectAllInquirys();
		return new ModelAndView("inquiry/inquiry_list.tiles", "deleteInquiry", deleteInquiry);
	}
	
	//1:1문의 수정하기
	@RequestMapping("/updateInquiryByAdvancedNo")
	public ModelAndView updateInquiryByAdvancedNo(String advancedNo, String advancedTitle, String advancedContent){
		System.out.println(1);
		System.out.println(advancedNo);
		Inquiry updateInquiry = new Inquiry(Integer.parseInt(advancedNo), advancedTitle, advancedContent);
		service.updateInquiryByAdvancedNo(Integer.parseInt(advancedNo));
		return new ModelAndView("inquiry/inquiry_modify.tiles", "updateInquiry", updateInquiry);
	}
	
	//1:1문의 전체 목록
	@RequestMapping("/inquiryList")
	public ModelAndView inquiryList(){
		List list = service.selectAllInquirys();
		
		return new ModelAndView("inquiry/inquiry_list.tiles", "list", list);
	}
	
	//1:1문의 전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회하기. 
	@RequestMapping("/selectInquiryByAdvancedNo")
	public ModelAndView selectInquiryByAdvancedNo(String advancedNo){
//		List allInquirys = service.selectAllInquirys();
		Inquiry selectInquiry = service.selectInquiryByAdvancedNo(Integer.parseInt(advancedNo));
		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", selectInquiry);
	}

}
