package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.FAQ;

@Repository
public class FAQDao
{
	private String namespace = "faqMapper.";
	
	private SqlSessionTemplate session;
	
	@Autowired
	public FAQDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	// 공지사항 게시물 insert
	public int insertFAQDao(FAQ faq) {
		return session.insert(namespace+"insert_faq", faq);
	}
	
	// 공지사항 게시물 No sequence
	public int selectNextNo() {
		return session.selectOne(namespace+"selectNextNo");
	}
	
	// 공지사항 게시물 전체조회
	public List FAQListDao(String type) {
		return session.selectList(namespace+"selectList", type);
	}
	
	// 공지사항 게시물 수정
	public int updateFAQDao(FAQ faq) {
		return session.update(namespace+"update_faq", faq);
	}
	
	// 공지사항 게시물 삭제
	public int deleteFAQDao(int no) {
		return session.delete(namespace+"delete_faq", no);
	}
	
	// 공지 번호로 공지사항 찾기
	public FAQ selectByNo(int no) {
		return session.selectOne(namespace+"select_byno", no);
	}
	
	// 조회수 올리기
	public int selectHit(int no) {
		return session.selectOne(namespace+"select_hit", no);
	}
	
	// 페이징 처리
	public List selectFAQListPagingDao(int page, String type) {
		Map map = new HashMap<>();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("basicType", type);
		return session.selectList(namespace+"selectListByPaging", map);
	}

	// 페이징 처리
	public int selectCountContents(String type) {
		return session.selectOne(namespace+"selectCountContents", type);
	}
	
	// FAQ 제목으로 공통된 게시글 찾기
	public List searchFAQByTitleDao(String title, String type, int page) {
		Map map = new HashMap<>();
		map.put("basicTitle", title);
		map.put("basicType", type);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("page", page);
		return session.selectList(namespace+"selectFAQTitle", map);
	}
	
	// FAQ 제목으로 검색엔진 페이징
	public int selectFAQCountContents(String title, String type) {
		Map map = new HashMap<>();
		map.put("basicTitle", title);
		map.put("basicType", type);
		return session.selectOne(namespace+"selectFAQCountContents", map);

	}
}
