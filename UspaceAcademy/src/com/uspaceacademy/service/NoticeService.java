package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.NoticeDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Notice;

@Service
public class NoticeService
{
	@Autowired
	private NoticeDao dao;
	
	
	// 공지사항 게시물 등록
	public int register(Notice notice) {
		return dao.insertNotice(notice);
	}
	
	// 공지사항 게시물 basicNo sequence 처리
	public int selectSeq() {
		return dao.selectNextNo();
	}
	
	// 공지사항 게시물 전체리스트
	public List noticeAll(String type) {
//		System.out.println("service : "+type);
		return dao.noticeList(type);
	}
	
	// 코드 값 search
	public List searchCode(String code) {
		return dao.selectCode(code);
	}
	
	// 공지사항 수정
	public int modifyNotice(Notice notice) {
		return dao.updateNotice(notice);
	}
	
	// 공지사항 삭제
	public int deleteNotice(int no) {
		return dao.deleteNotice(no);
	}
	
	public Notice selectByNo(int no) {
		return dao.selectByNo(no);
	}
	
	public int selectHit(int no) {
		return dao.selectHit(no);
	}
	
	// 공지목록 페이징 처리
	public Map getNoticeList(int page, String type) {
		Map map = new HashMap();
		map.put("noticeList", dao.selectListPage(page, type));
		map.put("paging", new PagingBean(dao.selectCountContents(type), page));
		return map;
	}
	
}
