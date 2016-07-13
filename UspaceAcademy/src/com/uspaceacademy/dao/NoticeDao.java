package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.Notice;

@Repository
public class NoticeDao
{
	private String namespace = "noticeMapper.";

	private SqlSessionTemplate session;
	
	@Autowired
	public NoticeDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	// 공지사항 게시물 insert
	public int insertNoticeDao(Notice notice) {
		return session.insert(namespace+"insert_notice", notice);
	}
	
	// 공지사항 게시물 시퀀스no sequence
	public int selectNextNo() {
		return session.selectOne(namespace+"selectNextNo");
	}
	
	// 공지사항 게시물 전체조회
	public List noticeListDao(String type) {
		return session.selectList(namespace+"selectList", type);
	}

	// 공지사항 게시물 수정
	public int updateNoticeDao(Notice notice) {
		return session.update(namespace+"update_notice", notice);
	}
	
	// 공지사항 게시물 삭제
	public int deleteNoticeDao(int no) {
		return session.delete(namespace+"delete_notice", no);
	}
	
	public Notice selectNoticeByNoDao(int no) {
		return session.selectOne(namespace+"select_byno", no);
	}
	
	public int selectHit(int no) {
		return session.selectOne(namespace+"select_hit", no);
	}
	
	// 공지 게시판 페이징 처리
	public List selectListPageDao(int page, String type) {
		Map map = new HashMap<>();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("basicType", type);
		return session.selectList(namespace+"selectListByPaing", map);
	}
	
	// 공지 게시판 페이징 처리
	public int selectCountContents(String type) {
		return session.selectOne(namespace+"selectCountContents", type);
	}
	
	// 검색엔진 페이징 처리
	public List noticeSearchDao(String keyword, int page) {
		Map map = new HashMap<>();
		map.put("basicType", "공지사항");
		map.put("keyword", keyword);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("page", page);
		return session.selectList(namespace+"noticeSearch", map);
	}
	
	// 공지사항 게시판에서 제목+내용으로 찾은 공통된 게시글의 총 개수 검색
	public int selectNoticeCountContents(String keyword) {
		Map map = new HashMap<>();
		map.put("keyword", keyword);
		map.put("basicType", "공지사항");
		return session.selectOne(namespace+"selectNoticeCountContents", map);
	}
}
