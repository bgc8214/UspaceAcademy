package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryService;
import com.uspaceacademy.validaotor.InquiryValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Inquiry;

@Controller
@RequestMapping("/inquiry")
public class InquiryController
{
	private String value;
	
	@Autowired
	private InquiryService service;
	
	//1:1문의 전체 목록
	@RequestMapping("/inquiryList")
	public ModelAndView inquiryList(){	
		return new ModelAndView("inquiry/inquiry_list.tiles", "list", service.selectAllInquirys());
	}
	
	//1:1문의 전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회하기. 
	@RequestMapping("/selectByAdvancedNo")
	public ModelAndView selectByAdvancedNo(int advancedNo){
		Inquiry selectInquiry = service.selectByAdvancedNo(advancedNo);
		int advancedHit = selectInquiry.getAdvancedHit();
		selectInquiry.setAdvancedHit(++advancedHit);
		service.updateInquiry(selectInquiry);
		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", selectInquiry);
	}
	
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
//	@RequestMapping("/insertInquiry")
//	public ModelAndView insertInquiry(String advancedTitle, String advancedContent, String codeName){
//		System.out.println(advancedTitle);
//		int advancedNo = service.increaseAdvancedNo();
//		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
//		Inquiry inquiry = new Inquiry(advancedNo, 1, advancedTitle, advancedContent, advancedDate, 1, "admin");
//		System.out.println(inquiry);
//		int insertInquiry = service.insertInquiry(inquiry);
//		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", inquiry);
//	}
	
//	String advancedTitle, String advancedContent, String codeName,
	@RequestMapping("/insertInquiry")
	public String insertInquiry(String codeName, @ModelAttribute Inquiry inquiry, BindingResult error){
		int advancedNo = service.increaseAdvancedNo();
		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		inquiry = new Inquiry(advancedNo, 1, inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 1, "admin");
		
		InquiryValidator validator = new InquiryValidator();
		validator.validate(inquiry, error);
		
		if (error.hasErrors())
		{
			return "/inquiry/codeList.do";
		}
		
		service.insertInquiry(inquiry);
		return "redirect:inquiry/inquiry_detail.tiles";
	}	
	
	//등록 redirect 처리
//	@RequestMapping("/inquiryRedirect")
//	public String inquiryRedirect() // 새로고침 시 더 등록 안되도록 redirect 처리
//	{
//		return "inquiry/inquiry_detail.tiles";
//	}

	//1:1문의 수정폼
	@RequestMapping("/updateByAdvancedNo")
	public ModelAndView updateByAdvancedNo(int advancedNo){
		Inquiry selectUpdateInquiry = service.selectByAdvancedNo(advancedNo);
		return new ModelAndView("inquiry/inquiry_modify.tiles", "selectUpdateInquiry", selectUpdateInquiry);
	}
	
	//1:1문의 수정하기
	@RequestMapping("/updateInquiry")
	public ModelAndView updateInquiry(int advancedNo, String advancedTitle, String advancedContent){
		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		Inquiry updateInquiry = new Inquiry(advancedNo, advancedTitle, advancedContent, advancedDate);
		service.updateInquiry(updateInquiry);
		return new ModelAndView("inquiry/inquiry_detail.tiles", "updateInquiry", updateInquiry);
	}
	
	//1:1문의 삭제하기
	@RequestMapping("/deleteByAdvancedNo")
	public String deleteByAdvancedNo(int advancedNo){
		service.deleteByAdvancedNo(advancedNo);
//		return "inquiry/inquiry_list.tiles";
		return "/inquiry/inquiryList.do";
	}

}
