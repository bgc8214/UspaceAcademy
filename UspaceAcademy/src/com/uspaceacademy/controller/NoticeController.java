package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public String noticeAdd(@ModelAttribute Notice notice, BindingResult errors) {

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
		return "redirect:/notice/noticeRedirect.do?no="+notice1.getBasicNo();

	}
	
	@RequestMapping("/noticeRedirect.do")
	public String noticeRedirectRegister(int no) {// 새로고침 시 더 등록 안되도록 redirect 처리
		return "redirect:/notice/noticeDetail.do?no="+no;
	}
	
	@RequestMapping("/noticeRegisterForm.do")
	public ModelAndView moveNotice(String type) {
		return new ModelAndView("notice/notice_form.tiles", "codeName", type);
	}
	
	@RequestMapping("/list.do")
	public ModelAndView noticeAllList(String type) {
		List list = service.noticeAll(type);
		return new ModelAndView("notice/notice_list.tiles", "list", list);
	}
	
	// 공지사항 수정
	@RequestMapping("/noticeUpdate.do")
	public String noticeModify(@ModelAttribute Notice notice, BindingResult errors) {

		// 검증 - NoticeValidator	
		NoticeValidator validator = new NoticeValidator();
		validator.validate(notice, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		
		System.out.printf("공지사항 수정 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);

		if(errors.hasErrors()) {
			return "notice/notice_update.tiles";
		}

		notice.setBasicDate(new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()));
		service.modifyNotice(notice);

		return "redirect:/notice/noticeUpdateRedirect.do?no="+notice.getBasicNo();
				
	}
	
	@RequestMapping("/noticeUpdateRedirect.do")
	public String noticeRedirectUpdate(int no) {	// 새로고침 시 더 등록 안되도록 redirect 처리
		System.out.println("수정 리다이렉트");
		return "/notice/noticeDetail.do?no="+no;
	}
	
	// 공지사항 삭제
	@RequestMapping("/noticeDelete.do")
	public ModelAndView noticeRemove(int no, String type) {
		service.deleteNotice(no);
		List list = service.noticeAll(type);
		return new ModelAndView("notice/notice_list.tiles", "list", list);
	}
	
	// 수정 폼 가기전에 no로  Notice 객체 가져오기
	@RequestMapping("/noticeUpdateForm.do")
	public ModelAndView noticeUpdateForm(int no) {
		System.out.println("수정폼으로 : "+no);
		Notice notice = service.selectByNo(no);
		return new ModelAndView("notice/notice_update.tiles", "notice", notice);
	}
	
	// 공지사항 상세페이지 
	@RequestMapping("/noticeDetail.do")
	public ModelAndView noticeDetail(int no) {	
		Notice notice = service.selectByNo(no);
		int val = notice.getBasicHit();
		notice.setBasicHit(++val);			// 조회수 증가
		service.modifyNotice(notice);		// 증가된 조회수가 반영된 객체로 DB 수정
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
	}
	
}

/*		String num = no;
String realNo = num.split(",")[0];
문자열 자르기
*/
