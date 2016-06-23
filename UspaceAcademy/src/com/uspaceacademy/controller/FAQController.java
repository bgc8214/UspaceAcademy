package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
//		System.out.println("codeTable 조회");
		System.out.println("코드네임" + codeNames);
		List codeList = service.searchCode("basic_board");
//		System.out.println(codeList);
		for(int i=0; i<codeList.size(); i++) {
			Code c = (Code) codeList.get(i);
			if(codeNames.equals(c.getCodeName())) {
				value = c.getCodeName();
			}
		}
		System.out.println("코드 테이블에서 조회: "+value);
		return new ModelAndView("FAQ/FAQ_form.tiles", "codeName", value);
	}
	
	// FAQ 게시물 등록
	@RequestMapping("/FAQWrite.do")
	public String FAQAdd(@ModelAttribute("FAQForm") FAQ faq, BindingResult errors) {
//		System.out.println("FAQ 게시물 등록 method()");
//		System.out.println("입력 폼에서 넘어온 객체 : "+faq);
		
		FAQValidator validator = new FAQValidator();
		validator.validate(faq, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("FAQ 등록 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "/FAQ/codeList.do?codeNames="+faq.getBasicType();
		}
//		System.out.println(new SimpleDateFormat("yyyyy-MM-dd kk:mm").format(new Date()));
		System.out.println(faq);
		FAQ faq1 = new FAQ(service.selectSeq(), "administrator", faq.getBasicTitle(), faq.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), 0, faq.getBasicType());
		service.register(faq1);
		List list = service.FAQAll(faq.getBasicType());
		return "redirect:/FAQ/list.do?type="+faq.getBasicType();
	}
	
	@RequestMapping("/FAQRedirect.do")
	public String FAQRedirectRegister(int no) {	// 새로고침 시 더 등록 안되도록 redirect 처리
		return "redirect:/FAQ/FAQDetatil.do?no="+no;
	}
	
	@RequestMapping("/list.do")
	public ModelAndView FAQAllList(String type) {
//		System.out.println("FAQ 전체리스트");
		System.out.println("list.do : "+type);
		List list = service.FAQAll(type);
		return new ModelAndView("FAQ/FAQ_list.tiles", "list", list);
	}
	
	// FAQ 수정
	@RequestMapping("/FAQUpdate.do")
	public String FAQModify(@ModelAttribute("FAQForm") FAQ faq, BindingResult errors) {
//		System.out.println("공지사항 수정");
//		System.out.println(no);
		System.out.println(faq);
		FAQValidator validator = new FAQValidator();
		validator.validate(faq, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
//		System.out.println(faq.getBasicNo());
			
		System.out.printf("FAQ 수정 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "FAQ/FAQ_update.tiles";
		}
		System.out.println("에러 안남");
		
		System.out.println(faq);
		FAQ faq1 = new FAQ(faq.getBasicNo(), faq.getBasicTitle(), faq.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), faq.getBasicHit());
		service.modifyFAQ(faq1);
		
		FAQ faq2 = service.selectByNo(faq.getBasicNo());
		
		List list = service.FAQAll(faq2.getBasicType());
	
		return "redirect:/FAQ/FAQUpdateRedirect.do?type="+faq2.getBasicType();
	}
	
	@RequestMapping("/FAQUpdateRedirect.do")
	public String FAQRedirectUpdate(String type) {	// 새로고침 시 더 등록 안되도록 redirect 처리
		System.out.println("수정 리다이렉트 "+type);
		return "redirect:/FAQ/list.do?type="+type;
	}
	
	// FAQ 삭제
	@RequestMapping("/FAQDelete.do")
	public ModelAndView FAQRemove(int no, String type) {
		service.deleteFAQ(no);
		List list = service.FAQAll(type);
		return new ModelAndView("FAQ/FAQ_list.tiles", "list", list);
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
	public ModelAndView FAQUpdateForm(int no, int hit) {
//		System.out.println(no+" "+hit);
		FAQ faq = service.selectByNo(no);
		faq.setBasicHit(hit);
		return new ModelAndView("FAQ/FAQ_update.tiles", "faq", faq);
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
	
	
}
