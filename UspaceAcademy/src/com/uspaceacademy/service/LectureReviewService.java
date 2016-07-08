package com.uspaceacademy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.LectureReviewDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.LectureReview;

@Service
public class LectureReviewService{
	
	@Autowired
	private LectureReviewDao dao;
	
	//--------------------------------------------------------------------------------------------------------------------	
	
	
	//수강후기 게시물등록 ㅇ
	public int insert(LectureReview lectureReview){
		return dao.insert(lectureReview);
	}
	
	//수강후기 게시물 LectureReview sequence처리 ㅇ
	public int selectNextNo(){
		return dao.selectNextNo();
	}
	
	//수강후기 전체 리스트ㅇ
	public List selectList(String type){
		return dao.selectList(type);
	}
	
	//수강후기 수정 ㅇ
	public int update(LectureReview lectureReview){
		return dao.update(lectureReview);
	}
	
	//수강후기 삭제ㅇ
	public int delete(int reviewNo){
		return dao.deleteByNo(reviewNo);
	}
	
	//수강후기 세부조회ㅇ
	public LectureReview selectNo(int reviewNo){
		return dao.selectNo(reviewNo);
	}
	
		
	
	
	//코드 영주1
	public List selectCodeName(String code){
		return dao.selectCodeName(code);
	}
	
	
	//7.조회수 처리
	public List selectHit(LectureReview lectureReview){
		//lectureReview.setReviewHit(lectureReview.getReviewHit()+1);//
		return dao.selectHit(lectureReview);
	}
	
	
	
	// 페이징처리   8.paging + 9.count
	public Map selectPagingCount(int page){
		Map map = new HashMap();
		map.put("lectureListReview", dao.selectPaging(page));													//여기이름도 잘보기!			
		map.put("paging", new PagingBean(dao.selectCount(), page));//PagingBean 임폴트.				//오류났던거 적기 : lectureReview_list.jsp 에 이름 같아야함!!!
		return map;
	}
	
	
	
	//--------------------------------------------------------------------------------------------------------------------	
	
	
	//10.11.   강의과목으로 검색 lectureSubject
	//12.13.   리뷰제목으로 검색 reviewTitle
	
	
	
	
	//10.11.   강의과목으로 검색 (수강후기 리스트에서)lectureSubject
	public Map<String,Object> searchLectureSubject(String reviewSubject, int page){
		Map <String,Object>map = new HashMap<String,Object>();
		map.put("lectureListReview",dao.selectPagingLectureSubject(reviewSubject, page));//●오류났던거적기 : consol창에 출력은 됐었음, lectureReview_list.jsp에 requestScope ->아래보기                               
		map.put("paging", new PagingBean(dao.selectCountLectureSubject(reviewSubject),page)); //●이부분lectureListReview로 해야하는데 내가 lectureReview했었음
		System.out.println("영주 서비스 강의 과목으로 검색reviewSubject : "+reviewSubject);
		System.out.println("영주 서비스 강의 과목으로 검색page : "+page);
		return map;
	}
	
	
	
	
	
	//12.13.   리뷰제목으로 (수강후기 리스트에서)검색 reviewTitle
	public Map<String,Object> searchReviewTitle(String reviewTitle, int page){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("lectureListReview", dao.selectPagingReviewTitle(reviewTitle, page));
		map.put("paging", new PagingBean(dao.selectCountReviewTitle(reviewTitle),page));
		System.out.println("영주 서비스 리뷰제목으로 검색reviewTitle : "+reviewTitle);
		System.out.println("영주 서비스 리뷰제목으로 검색page : "+page);
		return map;
	}
	
	
	
	
	
	

	
	
	
	
	
	
//--------------------------------------------------------------------------------------------------------------------		

/*
	
	//수강후기 검색 제목+내용==============
	public List selectTitleContent(String lectureTitle, String reviewContent){ //매개변수 값 넣기*
		return dao.selectTitleContent(lectureTitle, reviewContent);
	}
	//수강후기 검색 제목==============
	public List selectTitle(String lectureTitle){
		return dao.selectTitle(lectureTitle);
	}
	//수강후기 검색 내용==============
	public List selectContent(String reviewContent){
		return dao.selectContent(reviewContent);
	}
	
	*/
	
	
	
	
}
