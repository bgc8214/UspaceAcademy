package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.LectureInquiryService;
import com.uspaceacademy.validaotor.InquiryValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.LectureInquiry;

@Controller
@RequestMapping("/lectureInquiry")
public class LectureInquiryController {
	private String value;
	
	@Autowired
	private LectureInquiryService service;
	
	//1:1문의 전체 목록
	@RequestMapping("/lectureInquiryList")
	public ModelAndView lectureInquiryList(@RequestParam(defaultValue="1") int page){	
//		return new ModelAndView("lectureInquiry/lectureInquiry_list.tiles", "lectureInquiryList", service.selectAllInquirys());
		Map map = service.getInquiryList(page);
		map.put("page", page);
		return new ModelAndView("lectureInquiry/lectureInquiry_list.tiles", map);
	}
	
	//1:1문의 전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회하기. 
	@RequestMapping("/selectLIByAdvancedNo")
	public ModelAndView selectLIByAdvancedNo(int advancedNo){
		LectureInquiry selectInquiry = service.selectByAdvancedNo(advancedNo);
		int advancedHit = selectInquiry.getAdvancedHit();
		selectInquiry.setAdvancedHit(++advancedHit);
		service.updateHit(selectInquiry);
		return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", "lectureInquiryDetail", selectInquiry);
	}
	
	//1:1문의 등록하기
	@RequestMapping("/selectLICodeName.do")
	public ModelAndView selectLICodeName(String codeName) {
		List codeList = service.selectCodeName("advanced_board");
		for(int i=0; i<codeList.size(); i++) {
			Code code = (Code) codeList.get(i);
			if(codeName.equals(code.getCodeName())) {
				value = code.getCodeName();
			}
		}
		return new ModelAndView("lectureInquiry/insert_lectureInquiry.tiles", "codeName", value);
	}
	
	//1:1문의 글을 등록했을 때 상세페이지 조회하기	
//	String advancedTitle, String advancedContent, String codeName,
	@RequestMapping("/insertLectureInquiry")
	public String insertInquiry(String codeName, @ModelAttribute LectureInquiry lectureInquiry, BindingResult errors){
		int advancedNo = service.increaseAdvancedNo();
		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
//		lectureInquiry = new LectureInquiry(advancedNo, 1, lectureInquiry.getAdvancedTitle(), lectureInquiry.getAdvancedContent(), advancedDate, 0, "admin");
		
		InquiryValidator validator = new InquiryValidator();
		validator.validate(lectureInquiry, errors);
		
		if (errors.hasErrors())
		{
			return "/lectureInquiry/selectLICodeName.do";
		}
		
		service.insertInquiry(lectureInquiry);
		return "redirect:/lectureInquiry/lectureInquiryRedirect.do?advancedNo="+lectureInquiry.getAdvancedNo();
	}	
	
	//등록 redirect 처리
//	@RequestMapping("/lectureInquiryRedirect")
//	public ModelAndView lectureInquiryRedirect(int advancedNo, @RequestParam(defaultValue="1") int page) // 새로고침 시 더 등록 안되도록 redirect 처리
//	{
//		Map map = service.getInquiryList(page);
//		return new ModelAndView("/lecutreInquiry/selectLIByAdvancedNo.do?advancedNo="+advancedNo, map);
//	}
	
	@RequestMapping("/lectureInquiryRedirect")
	public String lectureInquiryRedirect(int advancedNo) // 새로고침 시 더 등록 안되도록 redirect 처리
	{
		return "/lecutreInquiry/selectLIByAdvancedNo.do?advancedNo="+advancedNo;
	}

	//1:1문의 수정폼
	@RequestMapping("/updateLIByAdvancedNo")
	public ModelAndView updateLIByAdvancedNo(int advancedNo){
		LectureInquiry inquiryDetail = service.selectByAdvancedNo(advancedNo);
		return new ModelAndView("lecutreInquiry/lectureInquiry_modify.tiles", "lectureInquiryDetail", inquiryDetail);
	}
	
	//1:1문의 수정하기
//	@RequestMapping("/updateLectureInquiry")
//	public ModelAndView updateLectureInquiry(LectureInquiry inquiry){
//		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
//		LectureInquiry inquiryDetail = new LectureInquiry(inquiry.getAdvancedNo(), inquiry.getAdvancedSecret(), inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, inquiry.getAdvancedHit(), inquiry.getAdvancedId());
//		service.updateInquiry(inquiryDetail);
//		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", inquiryDetail);
//	}
	
	//1:1문의 삭제하기
	@RequestMapping("/deleteLIByAdvancedNo")
	public String deleteLIByAdvancedNo(int advancedNo){
		service.deleteByAdvancedNo(advancedNo);
//		return "inquiry/inquiry_list.tiles";
		return "/lectureInquiry/lectureInquiryList.do";
	}
	
	
}
