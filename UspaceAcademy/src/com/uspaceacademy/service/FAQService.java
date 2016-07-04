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
	public int register(FAQ faq) {
		return dao.insertFAQ(faq);
	}
	
	// 공지사항 게시물 basicNo sequence 처리
	public int selectSeq() {
		return dao.selectNextNo();
	}
	
	// 공지사항 게시물 전체리스트
	public List FAQAll(String type) {
		return dao.FAQList(type);
	}
	
	// 코드 값 search
	public List searchCode(String code) {
		return dao.selectCode(code);
	}
	
	// 공지사항 수정
	public int modifyFAQ(FAQ faq) {
		return dao.updateFAQ(faq);
	}
	
	// 공지사항 삭제
	public int deleteFAQ(int no) {
		return dao.deleteFAQ(no);
	}
	
	public FAQ selectByNo(int no) {
		return dao.selectByNo(no);
	}
	
	public int selectHit(int no) {
		return dao.selectHit(no);
	}
	
	// FAQ 페이징 처리
	public Map FAQPagingList(int page, String type) {
		Map map = new HashMap();
		map.put("FAQList", dao.selectListPage(page, type));
		map.put("paging", new PagingBean(dao.selectCountContents(type), page));
		return map;
	}
	
	// FAQ 제목으로 찾은 목록 페이징 처리
	public Map FAQTitleSearch(String title, String type, int page) {
		Map map = new HashMap<>();
		map.put("FAQTitleList", dao.selectFAQTitle(title, type, page));
		map.put("paging", new PagingBean(dao.selectFAQCountContents(title, type), page));
		return map;
	}
	
/*	// FAQ 제목으로 찾은 목록 페이징 처리
	public Map FAQTitleSearch(String title, String type, int page) {
		Map map = new HashMap<>();
		map.put("FAQTitleList", dao.selectFAQTitle(title, type, page));
		map.put("paging", new PagingBean(dao.selectFAQCountContents(type), page));
		return map;
	}*/
}
