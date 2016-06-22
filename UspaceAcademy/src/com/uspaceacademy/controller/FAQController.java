package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.FAQService;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.FAQ;
import com.uspaceacademy.vo.Notice;


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
//		System.out.println(codeNames);
		List codeList = service.searchCode("basic_board");
//		System.out.println(codeList);
		for(int i=0; i<codeList.size(); i++) {
			Code c = (Code) codeList.get(i);
			if(codeNames.equals(c.getCodeName())) {
				value = c.getCodeName();
			}
		}
//		System.out.println(value);
		return new ModelAndView("FAQ/FAQ_form.tiles", "codeName", value);
	}
	
	// FAQ 게시물 등록
	@RequestMapping("/FAQWrite.do")
	public ModelAndView noticeAdd(String title, String content, String codeName) {
//		System.out.println("FAQ 게시물 등록 method()");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		Date date = new Date();
		String sDate = sdf.format(date);
		int num = service.selectSeq();
		FAQ faq = new FAQ(num, "관리자", title, content, sDate, 0, codeName);
		service.register(faq);
		List list = service.FAQAll(faq.getBasicType());
		return new ModelAndView("FAQ/FAQ_list.tiles",	"list", list);
		}
	
	
	@RequestMapping("/list.do")
	public ModelAndView noticeAllList(String type) {
//		System.out.println("FAQ 전체리스트");
		List list = service.FAQAll(type);
		return new ModelAndView("FAQ/FAQ_list.tiles", "list", list);
	}
	
	// FAQ 수정
	@RequestMapping("/FAQUpdate.do")
	public ModelAndView noticeModify(String no, String title, String content, String date) {
//		System.out.println("공지사항 수정");
//		System.out.println(no);
		int num = Integer.parseInt(no);
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd kk:mm");
		Date date1 = new Date();
		String uDate = sdf.format(date1);
		FAQ faq = new FAQ(num, title, content, uDate);
		service.modifyFAQ(faq);
		
		FAQ faq1 = service.selectByNo(num);
		
		List list = service.FAQAll(faq1.getBasicType());
	
		return new ModelAndView("FAQ/FAQ_list.tiles", "list", list);
	}
	
	// FAQ 삭제
	@RequestMapping("/FAQDelete.do")
	public ModelAndView noticeRemove(String no, String type) {
//		System.out.println("공지사항 삭제");
		int num = Integer.parseInt(no);
		System.out.println(no+" "+type);
		service.deleteFAQ(num);
		List list = service.FAQAll(type);
		return new ModelAndView("FAQ/FAQ_list.tiles", "list", list);
	}
	
	// 수정 폼 가기전에 no로 vo 가져오기
	@RequestMapping("/FAQUpdateForm.do")
	public ModelAndView noticeUpdateForm(String no) {
		int num = Integer.parseInt(no);
		FAQ faq = service.selectByNo(num);
		return new ModelAndView("FAQ/FAQ_update.tiles", "faq", faq);
	}
		
		
/*	@RequestMapping("/FAQDetail.do")
	public ModelAndView noticeDetail(String no) {
		int num = Integer.parseInt(no);
		FAQ faq = service.selectByNo(num);
		int val = faq.getBasicHit();
		faq.setBasicHit(++val);
		System.out.println(faq);
		service.modifyFAQ(faq);
		return new ModelAndView("FAQ/FAQ_detail.tiles", "faq", faq);
	}*/
	
	@RequestMapping("/findFAQByNo.do") 
	@ResponseBody
	public FAQ getFAQByNo(String no, String hit) {
		int num = Integer.parseInt(no);
		FAQ faq = service.selectByNo(Integer.parseInt(no));
		int count = Integer.parseInt(hit);
		faq.setBasicHit(++count);
		service.modifyFAQ(faq);
//		System.out.println(faq);
		return faq;
	}
}
