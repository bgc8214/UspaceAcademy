package com.uspaceacademy.controller;

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
		return new ModelAndView("/WEB-INF/view/notice/notice_form.jsp", "codeName", value);
	}
	
	@RequestMapping("/noticeWrite.do")
	public ModelAndView noticeAdd(String title, String content, String codeName) {
		System.out.println("게시물 등록 method()");
		Date date = new Date();
		int num = service.selectSeq();
		Notice notice = new Notice(num, "관리자", title, content, date.toString(), 0, codeName);
		service.register(notice);
		System.out.println("공지사항 게시물 등록!");
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
	}
	
	@RequestMapping("/list.do")
	public ModelAndView noticeAllList() {
		System.out.println("공지사항 전체리스트");
		List list = service.noticeAll();
		return new ModelAndView("notice/notice_list.tiles", "list", list);
	}
	
	@RequestMapping("/noticeUpdate.do")
	public ModelAndView noticeModify(int no, String title, String content) {
		System.out.println("공지사항 수정");
		Notice notice = new Notice(no, title, content);
		service.modifyNotice(notice);
		return new ModelAndView("notice/notice_detail.tiles", "notice", notice);
		
	}
	
	@RequestMapping("/noticeDelete.do")
	public ModelAndView noticeRemove(int no) {
		System.out.println("공지사항 삭제");
		service.deleteNotice(no);
		List list = service.noticeAll();
		return new ModelAndView("notice/notice_list.tiles", "list", list);
	}
	
	
}
