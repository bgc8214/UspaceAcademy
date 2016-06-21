package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.vo.LectureReview;
@Repository
public class LectureReviewDao{

	private String namespace="lectureReviewMapper";//
	private SqlSessionTemplate session;//
	
	@Autowired
	public LectureReviewDao(SqlSessionTemplate session){
		this.session = session;
	}																	//	
	
	
//-----------------------------------------------------------------------------------------	
	
	//수강후기 삽입
	public int insert(LectureReview lectureReview){
		return session.insert(namespace+"insert", lectureReview);
	}
	
	
	//수강후기 수정
	public int update(LectureReview lectureReview){
	return session.update(namespace+"update",lectureReview);
	}
	
	
	//수강후기 (no로) 삭제
	public int deleteByNo(String no){
	return session.delete(namespace+"delete", no);
	}
	
	//수강후기 no로 조회
	public LectureReview selectNo(String reviewNo){
	return session.selectOne(namespace+"selectNo");
	}
	
	//수강후기 제목+내용 으로조회  --리스트로 받아야함 (오류)*
	public List<LectureReview> selectTitleContent(String lectureTitle, String reviewContent){
	return session.selectList(namespace+"lectureTitle"+"reviewContent");
	}
	
	//수강후기 제목으로 조회
	public List<LectureReview> selectTitle(String lectureTitle){
	return session.selectList(namespace+"lectureTitle");
	}
	
	//수강후기 내용으로 조회
	public List<LectureReview> selectContent(String reviewContent){
	return session.selectList(namespace+"reviewContent");
	}
	
	//수강후기 전제조회
	public List selectList(){
	return session.selectList(namespace+"select");
	}
	//수강후기 페이징처리
	public List selectList(int page){
	HashMap param = new HashMap();
	param.put("itemPerPage", 10);
	param.put("page",page);
	return session.selectList("lectureReviewMapper.selectList",param);
	}
}
