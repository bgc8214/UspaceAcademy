package com.uspaceacademy.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uspaceacademy.dao.FAQDao;
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
	public List noticeAll(String type) {
		return dao.noticeList(type);
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

}
