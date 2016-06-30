package com.uspaceacademy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.NoticeService;
import com.uspaceacademy.validaotor.NoticeValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Notice;

@Controller
@RequestMapping("/notice")
public class NoticeController
{
	private String value;
	
	@Autowired
	private NoticeService service;
	
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
		return new ModelAndView("notice/notice_form.tiles", "codeName", value);
	}
	
	// 공시사항 게시물 등록
	@RequestMapping("/noticeWrite.do")
	public String noticeAdd(@RequestParam(defaultValue="1") int page, @ModelAttribute Notice notice, BindingResult errors) throws ParseException {
		
		// 검증 - NoticeValidator	
		NoticeValidator validator = new NoticeValidator();
		validator.validate(notice, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("공지사항 등록 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "/notice/codeList.do?codeNames="+notice.getBasicType();
		}
		Notice notice1 = new Notice(service.selectSeq(), "administrator", notice.getBasicTitle(), notice.getBasicContent(), new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()), 0, notice.getBasicType());
		service.register(notice1);
		return "redirect:/notice/noticeRedirect.do?no="+notice1.getBasicNo()+"&page="+page;
	}
	
	@RequestMapping("/noticeRegisterForm.do")
	public ModelAndView moveNotice(String type) {
		return new ModelAndView("notice/notice_form.tiles", "codeName", type);
	}
	
	// 페이징 처리된 공지사항 리스트 조회
	@RequestMapping("/list.do")
	public ModelAndView noticeAllList(@RequestParam(defaultValue="1") int page, String type) {
		Map map = service.getNoticeList(page, type);
		map.put("page", page);
		return new ModelAndView("notice/notice_list.tiles", map);
	}
		
	// 공지사항 수정
	@RequestMapping("/noticeUpdate.do")
	public String noticeModify(@RequestParam(defaultValue="1") int page, @ModelAttribute("updateForm") Notice notice, BindingResult errors) {

		// 검증 - NoticeValidator	
		NoticeValidator validator = new NoticeValidator();
		validator.validate(notice, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("공지사항 수정 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);

		if(errors.hasErrors()) {
			return "/notice/noticeUpdateForm.do?page="+page+"&no="+notice.getBasicNo();
		}

		notice.setBasicDate(new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()));
		service.modifyNotice(notice);

		return "redirect:/notice/noticeUpdateRedirect.do?no="+notice.getBasicNo()+"&page="+page;
				
	}
	
	@RequestMapping("/noticeUpdateRedirect.do")
	public String noticeRedirectUpdate(int no, @RequestParam(defaultValue="1") int page) {	// 새로고침 시 더 등록 안되도록 redirect 처리
		System.out.println("수정 리다이렉트");
		return "/notice/noticeDetail.do";
	}
	
	// 공지사항 삭제
	@RequestMapping("/noticeDelete.do")
	public ModelAndView noticeRemove(@RequestParam(defaultValue="1") int page, int no, String type) {
		service.deleteNotice(no);
		Map map = service.getNoticeList(page, type);
		return new ModelAndView("notice/notice_list.tiles", map);
	}
	
	// 수정 폼 가기전에 no로  Notice 객체 가져오기
	@RequestMapping("/noticeUpdateForm.do")
	public ModelAndView noticeUpdateForm(int no, @RequestParam(defaultValue="1") int page) {
		System.out.println(page);
		Notice notice = service.selectByNo(no);
		Map map = new HashMap();
		map.put("notice", notice);
		map.put("page", page);
		return new ModelAndView("notice/notice_update.tiles", map);
	}
	
	// 공지사항 상세페이지 
	@RequestMapping("/noticeDetail.do")
	public ModelAndView noticeDetail(int no, @RequestParam(defaultValue="1") int page) {	
		Notice notice = service.selectByNo(no);
		int val = notice.getBasicHit();
		notice.setBasicHit(++val);			// 조회수 증가
		service.modifyNotice(notice);		// 증가된 조회수가 반영된 객체로 DB 수정
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
	}
	
	@RequestMapping("/noticeRedirect.do")
	public String noticeRedirectRegister(int no, @RequestParam(defaultValue="1") int page) {// 새로고침 시 더 등록 안되도록 redirect 처리
		return "redirect:/notice/noticeDetail.do?no="+no+"&page="+page;
	}
	
	// 공지사항 제목 내용으로 검색 List
	@RequestMapping("/noticeSearch.do")
	public ModelAndView noticeSearch(@RequestParam(defaultValue="1") int page, String keyword) {
		Map map = service.getSearchList(keyword, page);
		map.put("page", page);
		map.put("keyword", keyword);
		return new ModelAndView("notice/notice_search.tiles", map);
	}
	
}

/*		String num = no;
String realNo = num.split(",")[0];
문자열 자르기
*/
