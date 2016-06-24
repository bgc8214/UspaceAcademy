package com.uspaceacademy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.vo.Comment;

@Repository
public class InquiryCommentDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	public InquiryCommentDao(){}
	
	public InquiryCommentDao(SqlSessionTemplate session){
		this.session = session;
	}
	
	//code 조회
	public List selectCode(String codeType){
		return session.selectList("codeTable.selectCodeName", codeType);
	}
	
	//댓글 등록
	public int insertComment(Comment comment){
		return session.insert("commentMapper.insertComment", comment);
	}
	
	//상세 조회
	public Comment selectByCommentNo(int commentNo){
		return session.selectOne("commentMapper.selectByCommentNo", commentNo);
	}
	
	//댓글 수정
	public int updateComment(Comment comment){
		return session.update("commentMapper.updateComment", comment);
	}
	
	//댓글 삭제
	public int deleteByCommentNo(int commentNo){
		return session.delete("commentMapper.deleteByCommentNo", commentNo);
	}
	
	//글 번호 sequence
	public int increaseCommentNo(){
		return session.selectOne("commentMapper.increaseCommentNo");
	}
}
