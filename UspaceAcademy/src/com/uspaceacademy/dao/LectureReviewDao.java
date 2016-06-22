package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.uspaceacademy.vo.LectureReview;
import com.uspaceacademy.vo.LectureReview;

@Repository
public class LectureReviewDao{

	private String namespace="lectureReviewMapper.";//끝에 점 붙혀야됨*
	
	private SqlSessionTemplate session;//
	
	@Autowired
	public LectureReviewDao(SqlSessionTemplate session){
		this.session = session;
	}																	//	
	
	
//-----------------------------------------------------------------------------------------	
	
	//수강후기 삽입 *ok
	public int insert(LectureReview lectureReview){
		System.out.println(lectureReview);
		return session.insert(namespace+"insert", lectureReview);
	}
	
	
	//수강후기 게시물 시퀀스 no sequence===========*ok
	public int selectNextNo(){
		return session.selectOne(namespace+"selectNextNo");
	}
	
	
	//수강후기 수정
	public int update(LectureReview lectureReview){
	return session.update(namespace+"update",lectureReview);
	}
	
	
	//수강후기 (no로) 삭제   *ok
	public int deleteByNo(int reviewNo){
	return session.delete(namespace+"deleteByNo", reviewNo);// mapper에서 deleteByNo 로함 잘보기 *
	}
	
	//수강후기 no로 조회 (수강신청 세부조회할때) *ok
	public LectureReview selectNo(int reviewNo){
		//System.out.println("dao "+session.selectOne(namespace+"selectNo", reviewNo));
		return session.selectOne(namespace+"selectNo", reviewNo); 
	}
	
	//수강후기 제목+내용 으로조회  --리스트로 받아야함 (오류)*=================
	public List<LectureReview> selectTitleContent(String lectureTitle, String reviewContent){
	return session.selectList(namespace+"lectureTitle"+"reviewContent");
	}
	
	//수강후기 제목으로 조회=====================
	public List<LectureReview> selectTitle(String lectureTitle){
	return session.selectList(namespace+"lectureTitle");
	}
	
	//수강후기 내용으로 조회=========================
	public List<LectureReview> selectContent(String reviewContent){
	return session.selectList(namespace+"reviewContent");
	}
	
	//수강후기 전제조회
	public List selectList(){
	return session.selectList(namespace+"select");
	}
	
	
	
	//수강후기 페이징처리===========================
	public List selectListPage(int page){
	HashMap param = new HashMap();
	param.put("itemPerPage", 10);
	param.put("page",page);
	return session.selectList("lectureReviewMapper.selectList",param);
	}
	//수강후기 총no가 몇개인지 세어줌==================페이징처리관련
	public int selectLectureReviewCount(){
		return 0; //??????????????????????????????????????????????????????????????
	}
	
	
	
	
	//수강후기 조회수 ====================
	
	
	
	
	
	
	
}
