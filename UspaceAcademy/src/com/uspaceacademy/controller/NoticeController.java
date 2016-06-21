package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.NoticeService;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Notice;

@Controller
@RequestMapping("/notice")
public class NoticeController
{
	private String value;
	
	@Autowired
	private NoticeService service;
	
	// 코드테일 name과 일치할 때만 구분 값 넣기
	@RequestMapping("/codeList.do")
	public ModelAndView codeSearch(String codeNames) {
		System.out.println("codeTable 조회");
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
	public ModelAndView noticeAdd(String title, String content, String codeName) {
		System.out.println("게시물 등록 method()");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd kk:mm");
		Date date = new Date();
		String sDate = sdf.format(date);
		int num = service.selectSeq();
		Notice notice = new Notice(num, "관리자", title, content, sDate, 0, codeName);
		service.register(notice);
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
	}
	
	@RequestMapping("/list.do")
	public ModelAndView noticeAllList() {
//		System.out.println("공지사항 전체리스트");
		List list = service.noticeAll();
		return new ModelAndView("notice/notice_list.tiles", "list", list);
	}
	
	// 공지사항 수정
	@RequestMapping("/noticeUpdate.do")
	public ModelAndView noticeModify(String no, String title, String content, String date) {
//		System.out.println("공지사항 수정");
//		System.out.println(no);
		int num = Integer.parseInt(no);
		SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd kk:mm");
		Date date1 = new Date();
		String uDate = sdf.format(date1);
		Notice notice = new Notice(num, title, content, uDate);
		service.modifyNotice(notice);
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
	}
	
	// 공지사항 삭제
	@RequestMapping("/noticeDelete.do")
	public ModelAndView noticeRemove(String no) {
//		System.out.println("공지사항 삭제");
		int num = Integer.parseInt(no);
		service.deleteNotice(num);
		List list = service.noticeAll();
		return new ModelAndView("notice/notice_list.tiles", "list", list);
	}
	
	// 수정 폼 가기전에 no로 vo 가져오기
	@RequestMapping("/noticeUpdateForm.do")
	public ModelAndView noticeUpdateForm(String no) {
		int num = Integer.parseInt(no);
		Notice notice = service.selectByNo(num);
		return new ModelAndView("notice/notice_update.tiles", "notice", notice);
	}
	
	
	@RequestMapping("/noticeDetail.do")
	public ModelAndView noticeDetail(String no) {
		int num = Integer.parseInt(no);
		Notice notice = service.selectByNo(num);
		int val = notice.getBasicHit();
		notice.setBasicHit(++val);
		System.out.println(notice);
		service.modifyNotice(notice);
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
	}
}
