package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.CodeService;
import com.uspaceacademy.service.FAQService;
import com.uspaceacademy.validaotor.FAQValidator;
import com.uspaceacademy.vo.FAQ;


@Controller
@RequestMapping("/FAQ")
public class FAQController
{
	
	private String value;
	
	@Autowired
	private FAQService service;
	
	@Autowired
	private CodeService codeService;
	
	// 코드테이블 name과 일치할 때만 구분 값 넣기
	@RequestMapping("/codeList.do")
	public ModelAndView codeSearch(String codeName) {
		return new ModelAndView("FAQ/FAQ_form.tiles", "codeName", codeService.searchCode(codeName));
	}
	
	// FAQ 게시물 등록
	@RequestMapping("/FAQRegister.do")
	public String FAQRegister(@RequestParam(defaultValue="1") int page, String type,@ModelAttribute("FAQForm") FAQ faq, BindingResult errors) {
		FAQValidator validator = new FAQValidator();
		validator.validate(faq, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("FAQ 등록 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "/FAQ/codeList.do?codeName="+faq.getBasicType();
		}
		FAQ faq1 = new FAQ(service.selectSeq(), "administrator", faq.getBasicTitle(), faq.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), 0, "FAQ");
		service.insertFAQService(faq1);
		return "redirect:/FAQ/FAQList.do?type=FAQ";
	}
	
	// 새로고침 시 더 등록 안되도록 redirect 처리
	@RequestMapping("/FAQRedirect.do")
	public String FAQRedirectRegister(int no) {			
		return "redirect:/FAQ/FAQDetatil.do?no="+no;
	}
	
	// FAQ Paging 리스트
	@RequestMapping("/FAQList.do")
	public ModelAndView FAQPagingList(@RequestParam(defaultValue="1") int page, String type, HttpSession session) {
		Map map = service.selectFAQPagingListService(page, type);
		map.put("page", page);
		return new ModelAndView("FAQ/FAQ_list.tiles", map);
	}
	
	// FAQ 수정
	@RequestMapping("/FAQModify.do")
	public String FAQModify(@RequestParam(defaultValue="1") int page, @ModelAttribute("FAQForm") FAQ faq, BindingResult errors) {
	
		FAQValidator validator = new FAQValidator();
		validator.validate(faq, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("FAQ 수정 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "redirect:/FAQ/FAQModifyForm.do?no="+faq.getBasicNo()+"&hit="+faq.getBasicHit()+"&page="+page;
		}

		FAQ faq1 = new FAQ(faq.getBasicNo(), faq.getBasicTitle(), faq.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), faq.getBasicHit());
		service.updateFAQService(faq1);
		
		Map map = service.selectFAQPagingListService(page, "FAQ");
		return "redirect:/FAQ/FAQModifyRedirect.do?type="+"FAQ";
	}
	
	@RequestMapping("/FAQModifyRedirect.do")
	public String FAQModifyRedirect(String type) {			// 새로고침 시 더 등록 안되도록 redirect 처리
		return "redirect:/FAQ/FAQList.do?type="+type;
	}
	
	// FAQ 삭제
	@RequestMapping("/FAQRemove.do")
	public ModelAndView FAQRemove(@RequestParam(defaultValue="1") int page, int no, String type) {
		service.deleteFAQService(no);
		Map map = service.selectFAQPagingListService(page, type);
		return new ModelAndView("FAQ/FAQ_list.tiles", map);
	}
	
	// 조회수 올리기
	@RequestMapping("/findFAQByNo.do") 
	@ResponseBody
	public FAQ getFAQByNo(int no, int hit) {
		FAQ faq = service.selectByNo(no);
		faq.setBasicHit(++hit);
		service.updateFAQService(faq);
		return faq;
	}
	
	// 수정 폼 가기전에 no로 FAQ객체 가져오기
	@RequestMapping("/FAQModifyForm.do")
	public ModelAndView FAQModifyForm(int no, int hit, @RequestParam(defaultValue="1") int page) {
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
		service.updateFAQService(faq);
		return new ModelAndView("FAQ/FAQ_detail.tiles", "faq", faq);
	}
	
	// FAQ Title로 검색엔진
	@RequestMapping("/FAQTitleSearch.do")
	public ModelAndView selectFAQByTitleList(@RequestParam(defaultValue="1") int page, String title) {
		Map map = service.selectFAQByTitleService(title, "FAQ", page); 
		map.put("page", page);
		map.put("title", title);
		return new ModelAndView("FAQ/FAQTitle_search.tiles", map);
		
	}

}
