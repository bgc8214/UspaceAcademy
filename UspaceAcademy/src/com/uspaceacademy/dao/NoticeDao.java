package com.uspaceacademy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
	public int insertNotice(Notice notice) {
		return session.insert(namespace+"insert_notice", notice);
	}
	
	// 공지사항 게시물 no sequence
	public int selectNextNo() {
		return session.selectOne(namespace+"selectNextNo");
	}
	
	// 공지사항 게시물 전체조회
	public List noticeList() {
		return session.selectList(namespace+"selectList");
	}
	
	// codeTable에서 name값 가져오기
	public List selectCode(String code) {
		return session.selectList("codeTable.selectCodeName", code);
	}
	
	// 공지사항 게시물 수정
	public int updateNotice(Notice notice) {
		return session.update(namespace+"update_notice", notice);
	}
	
	// 공지사항 게시물 삭제
	public int deleteNotice(int no) {
		return session.delete(namespace+"delete_notice", no);
	}
	
	
}
