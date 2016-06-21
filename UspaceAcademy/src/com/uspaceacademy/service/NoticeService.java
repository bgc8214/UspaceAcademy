package com.uspaceacademy.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.NoticeDao;
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
	public List noticeAll() {
		return dao.noticeList();
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
	
}
