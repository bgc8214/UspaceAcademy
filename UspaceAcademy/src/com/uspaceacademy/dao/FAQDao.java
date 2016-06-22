package com.uspaceacademy.dao;

import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
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
	public int insertFAQ(FAQ faq) {
		return session.insert(namespace+"insert_faq", faq);
	}
	
	// 공지사항 게시물 no sequence
	public int selectNextNo() {
		return session.selectOne(namespace+"selectNextNo");
	}
	
	// 공지사항 게시물 전체조회
	public List FAQList(String type) {
		return session.selectList(namespace+"selectList", type);
	}
	
	// codeTable에서 name값 가져오기
	public List selectCode(String code) {
//		System.out.println(session.selectList("codeTable.selectCodeName", code));
		return session.selectList("codeTable.selectCodeName", code);
	}
	
	// 공지사항 게시물 수정
	public int updateFAQ(FAQ faq) {
		return session.update(namespace+"update_faq", faq);
	}
	
	// 공지사항 게시물 삭제
	public int deleteFAQ(int no) {
		return session.delete(namespace+"delete_faq", no);
	}
	
	public FAQ selectByNo(int no) {
		return session.selectOne(namespace+"select_byno", no);
	}
	
	public int selectHit(int no) {
		return session.selectOne(namespace+"select_hit", no);
	}
}
