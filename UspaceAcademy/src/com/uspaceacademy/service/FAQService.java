package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uspaceacademy.dao.FAQDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.FAQ;

@Service
public class FAQService
{
	@Autowired
	private FAQDao dao;
	
	// 공지사항 게시물 등록
	public int insertFAQService(FAQ faq) {
		return dao.insertFAQDao(faq);
	}
	
	// 공지사항 게시물 basicNo sequence 처리
	public int selectSeq() {
		return dao.selectNextNo();
	}
	
	// 공지사항 게시물 전체리스트
	public List FAQListService(String type) {
		return dao.FAQListDao(type);
	}
	
	// 공지사항 수정
	public int updateFAQService(FAQ faq) {
		return dao.updateFAQDao(faq);
	}
	
	// 공지사항 삭제
	public int deleteFAQService(int no) {
		return dao.deleteFAQDao(no);
	}
	
	public FAQ selectByNo(int no) {
		return dao.selectByNo(no);
	}
	
	public int selectHit(int no) {
		return dao.selectHit(no);
	}
	
	// FAQ 페이징 처리
	public Map selectFAQPagingListService(int page, String type) {
		Map map = new HashMap();
		map.put("FAQList", dao.selectFAQListPagingDao(page, type));
		map.put("paging", new PagingBean(dao.selectCountContents(type), page));
		return map;
	}
	
	// FAQ 제목으로 찾은 목록 페이징 처리
	public Map selectFAQByTitleService(String title, String type, int page) {
		Map map = new HashMap<>();
		map.put("FAQTitleList", dao.searchFAQByTitleDao(title, type, page));
		map.put("paging", new PagingBean(dao.selectFAQCountContents(title, type), page));
		return map;
	}
}
