package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.FAQService;
import com.uspaceacademy.validaotor.FAQValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.FAQ;


@Controller
@RequestMapping("/FAQ")
public class FAQController
{
	private String value;
	
	@Autowired
	private FAQService service;
	
	// 코드테이블 name과 일치할 때만 구분 값 넣기
	@RequestMapping("/codeList.do")
	public ModelAndView codeSearch(String codeNames) {
		List codeList = service.searchCode("basic_board");
		for(int i=0; i<codeList.size(); i++) {
			Code c = (Code) codeList.get(i);
			if(codeNames.equals(c.getCodeName())) {
				value = c.getCodeName();
			}
		}
		return new ModelAndView("FAQ/FAQ_form.tiles", "codeName", value);
	}
	
	// FAQ 게시물 등록
	@RequestMapping("/FAQWrite.do")
	public String FAQAdd(@RequestParam(defaultValue="1") int page, String type,@ModelAttribute("FAQForm") FAQ faq, BindingResult errors) {
		FAQValidator validator = new FAQValidator();
		validator.validate(faq, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("FAQ 등록 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "/FAQ/codeList.do?codeNames="+faq.getBasicType();
		}
		FAQ faq1 = new FAQ(service.selectSeq(), "administrator", faq.getBasicTitle(), faq.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), 0, faq.getBasicType());
		service.register(faq1);
		List list = service.FAQAll(faq.getBasicType());
		return "redirect:/FAQ/list.do?type="+faq.getBasicType();
	}
	
	@RequestMapping("/FAQRedirect.do")
	public String FAQRedirectRegister(int no) {	// 새로고침 시 더 등록 안되도록 redirect 처리
		return "redirect:/FAQ/FAQDetatil.do?no="+no;
	}
	
	
	// FAQ Paiging 리스트
	@RequestMapping("/list.do")
	public ModelAndView FAQAllList(@RequestParam(defaultValue="1") int page, String type) {
		Map map = service.FAQPagingList(page, type);
		map.put("page", page);
		return new ModelAndView("FAQ/FAQ_list.tiles", map);
	}
	
	// FAQ 수정
	@RequestMapping("/FAQUpdate.do")
	public ModelAndView FAQModify(@RequestParam(defaultValue="1") int page, @ModelAttribute("FAQForm") FAQ faq, BindingResult errors) {
	// 검증
		FAQValidator validator = new FAQValidator();
		validator.validate(faq, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("FAQ 수정 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return new ModelAndView("/FAQ/FAQUpdateForm.do?no="+faq.getBasicNo()+"&hit="+faq.getBasicHit()+"&page="+page);
		}

		FAQ faq1 = new FAQ(faq.getBasicNo(), faq.getBasicTitle(), faq.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), faq.getBasicHit());
		service.modifyFAQ(faq1);
//		System.out.println(faq1.getBasicType());
		
		Map map = service.FAQPagingList(page, "FAQ");
		return new ModelAndView("FAQ/FAQ_list.tiles", map);
	}
	
	@RequestMapping("/FAQUpdateRedirect.do")
	public String FAQRedirectUpdate(String type) {	// 새로고침 시 더 등록 안되도록 redirect 처리
		System.out.println("수정 리다이렉트 "+type);
		return "redirect:/FAQ/list.do?type="+type;
	}
	
	// FAQ 삭제
	@RequestMapping("/FAQDelete.do")
	public ModelAndView FAQRemove(@RequestParam(defaultValue="1") int page, int no, String type) {
		service.deleteFAQ(no);
		Map map = service.FAQPagingList(page, type);
		return new ModelAndView("FAQ/FAQ_list.tiles", map);
	}
	
	@RequestMapping("/findFAQByNo.do") 
	@ResponseBody
	public FAQ getFAQByNo(int no, int hit) {
		FAQ faq = service.selectByNo(no);
		faq.setBasicHit(++hit);
		service.modifyFAQ(faq);
		return faq;
	}
	
	// 수정 폼 가기전에 no로 vo 가져오기
	@RequestMapping("/FAQUpdateForm.do")
	public ModelAndView FAQUpdateForm(int no, int hit, @RequestParam(defaultValue="1") int page) {
		FAQ faq = service.selectByNo(no);
		faq.setBasicHit(hit);
		HashMap map = new HashMap();
		map.put("faq", faq);
		map.put("page", page);
		return new ModelAndView("FAQ/FAQ_update.tiles", map);
	}
		
	// FAQ 상세페이지로 이동	
	@RequestMapping("/FAQDetail.do")
	public ModelAndView FAQDetail(int no) {
		FAQ faq = service.selectByNo(no);
		int val = faq.getBasicHit();
		faq.setBasicHit(++val);
		service.modifyFAQ(faq);
		return new ModelAndView("FAQ/FAQ_detail.tiles", "faq", faq);
	}
	
	// FAQ Title로 검색엔진
	@RequestMapping("/FAQTitleSearch.do")
	public ModelAndView FAQTitleSearch(@RequestParam(defaultValue="1") int page, String title) {
//		System.out.println(title);
		Map map = service.FAQTitleSearch(title, "FAQ", page); 
		map.put("page", page);
		map.put("title", title);
		return new ModelAndView("FAQ/FAQTitle_search.tiles", map);
		
	}

}
