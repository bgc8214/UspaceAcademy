package com.uspaceacademy.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.CodeDao;
import com.uspaceacademy.dao.NoticeDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Notice;

@Service
public class NoticeService
{
	@Autowired
	private NoticeDao dao;
	
	// 공지사항 게시물 등록
	public int insertNoticeService(Notice notice) {		
		return dao.insertNoticeDao(notice);
	}
	
	// 공지사항 게시물 basicNo sequence 처리
	public int selectSeq() {
		return dao.selectNextNo();
	}
	
	// 공지사항 게시물 전체리스트
	public List noticeListService(String type) {
		return dao.noticeListDao(type);
	}
	
	// 공지사항 수정
	public int updateNoticeService(Notice notice) {
		notice.setBasicDate(new SimpleDateFormat("yyyy-MM-dd kk:mm").format(new Date()));
		return dao.updateNoticeDao(notice);
	}
	
	// 공지사항 삭제
	public int deleteNoticeService(int no) {
		return dao.deleteNoticeDao(no);
	}
	// 검색 시 글 번호로 조회
	public Notice selectNoticeByNoService(int no) {
		return dao.selectNoticeByNoDao(no);
	}
	
	public int selectHit(int no) {
		return dao.selectHit(no);
	}
	
	// 공지목록 페이징 처리
	public Map selectNoticeListPageService(int page, String type) {
		Map map = new HashMap();
		map.put("noticeList", dao.selectListPageDao(page, type));
		map.put("paging", new PagingBean(dao.selectCountContents(type), page));
		return map;
	}
	
	// 공지사항 제목+내용으로 찾은 목록 페이징 처리
	public Map noticeSearchService(String keyword, int page) {
		Map map = new HashMap<>();
		map.put("noticeSearch", dao.noticeSearchDao(keyword, page));
		map.put("paging", new PagingBean(dao.selectNoticeCountContents(keyword), page));
		return map;
	}
	
}
