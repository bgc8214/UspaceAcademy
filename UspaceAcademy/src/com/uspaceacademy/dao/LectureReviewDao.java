package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.LectureReview;

@Repository
public class LectureReviewDao{

	private String namespace="lectureReviewMapper.";//끝에 점 붙혀야됨*
	private String codemapper="codeTable.";  //코드mapper
	
	private SqlSessionTemplate session;//
	
	@Autowired
	public LectureReviewDao(SqlSessionTemplate session){
		this.session = session;
	}																	//	
	
	
//-----------------------------------------------------------------------------------------	
	
	
	
	//1.수강후기 삽입(게시물등록)ㅇ
	public int insert(LectureReview lectureReview){
		System.out.println(lectureReview);
		return session.insert(namespace+"insert", lectureReview);
	}
	
	
	
	//2.수강후기 게시물 시퀀스 no sequence ㅇ
	public int selectNextNo(){
		return session.selectOne(namespace+"selectNextNo");
	}
	
	
	
	//3.수강후기 전제조회 ㅇ
	public List selectList(String type){
	return session.selectList(namespace+"select",type);
	}
	
	
	
	
	//4.수강후기 수정 ㅇ
	public int update(LectureReview lectureReview){
	return session.update(namespace+"update",lectureReview);
	}
	
	
	
	//5.수강후기 (no로) 삭제  ㅇ
	public int deleteByNo(int reviewNo){
	return session.delete(namespace+"deleteByNo", reviewNo);// mapper에서 deleteByNo 로함 잘보기 *
	}
	
	
	
	//6.수강후기 no로 조회 (수강신청 세부조회할때) ㅇ
	public LectureReview selectNo(int reviewNo){
		//System.out.println("dao "+session.selectOne(namespace+"selectNo", reviewNo));
		return session.selectOne(namespace+"selectNo", reviewNo); 
	}
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------
	
	
	
	
	
	//코드 영주1
	public List selectCodeName(String code){
		System.out.println("렉쳐리뷰 다오" + code);
		System.out.println();
		return session.selectList(codemapper+"selectCodeName",code);
	}
	
	
	
	
	
	
	//7.조회수 처리
	public List selectHit(LectureReview lectureReview){
		lectureReview.setReviewHit(lectureReview.getReviewHit()+1); //dao - 여기서 1증가 시키기
		return session.selectList(namespace+"updateHit",lectureReview);
	}
	
	
	
	
	//8.페이징 selectPaging
	public List selectPaging(int page){
		Map map = new HashMap();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList(namespace+"selectPaging", map);
	}
	
	//9.페이징 selectCount
	public int selectCount(){
		return session.selectOne(namespace+"selectCount");
	}
	
	
	
	//--------------------------------------------------------------------
	//10.강의과목으로 검색 lectureSubject / 강의리스트페이징
	//11.강의과목으로 검색 lectureSubject / 강의리스트페이징헬퍼
	//12.내용으로 검색 reviewContent / 강의리스트페이징
	//13.내용으로 검색 reviewContent / 강의리스트페이징헬퍼
	
	
	
	
	
	
	//10.강의과목으로 검색 lectureSubject / 강의리스트페이징selectPagingLectureSubject
	public Object selectPagingLectureSubject(String lectureSubject, int page){
		Map map = new HashMap();
		map.put("lectureSubject", lectureSubject);
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		
		return session.selectList(namespace+"selectPagingLectureSubject"+map);
	}
	
	
	
	//11.강의과목으로 검색 lectureSubject / 강의리스트페이징헬퍼selectCountLectureSubject
	public int selectCountLectureSubject(String lectureSubject){
		return session.selectOne(namespace+"selectCountLectureSubject"+lectureSubject);
	}
	
	
	
	//12.제목으로 검색 reviewTitle / 강의리스트페이징selectPagingReviewContent //제목에 매개변수로 넘어온 값이 포함되어있으면 모두조회
	public List selectPagingReviewTitle(String reviewTitle, int page){
		Map map = new HashMap();
		map.put("reviewTitle", reviewTitle);
		map.put("page", page);
		map.put("itemPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList(namespace+"selectPagingReviewTitle", map);
	}

	
	
	
	//13.제목으로 검색 reviewTitle / 강의리스트페이징헬퍼selectCountReviewContent //
	public int selectCountReviewTitle(String reviewTitle){
		return session.selectOne(namespace+"selectCountReviewTitle",reviewTitle);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//-------------------------------------------------------------------------------------------------------------
	
	
	
	
/*	//수강후기 제목+내용 으로조회  --리스트로 받아야함 (오류)*=================
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
	*/
}







