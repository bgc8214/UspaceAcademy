package com.uspaceacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.LectureReviewDao;
import com.uspaceacademy.vo.LectureReview;

@Service
public class LectureReviewService{
	
	@Autowired
	private LectureReviewDao dao;
	
//----------------------------------------	
	
	
	//수강후기 게시물등록 *ok
	public int insert(LectureReview lectureReview){
		return dao.insert(lectureReview);
	}
	
	//수강후기 게시물 LectureReview sequence처리==============*ok
	public int selectNextNo(){
		return dao.selectNextNo();
	}
	
	//수강후기 전체 리스트 *ok
	public List selectList(){
		return dao.selectList();
	}
	
	//수강후기 수정 
	public int update(LectureReview lectureReview){
		return dao.update(lectureReview);
	}
	
	//수강후기 삭제 *ok
	public int delete(int reviewNo){
		return dao.deleteByNo(reviewNo);
	}
	
	//수강후기 세부조회 *ok
	public LectureReview selectNo(int reviewNo){
		//System.out.println("service "+dao.selectNo(reviewNo));
		return dao.selectNo(reviewNo);
	}
	
	
	
	
	
	
	
	
	
	
	//수강후기 페이징처리==============
	public List selectListPage(int page){
		return dao.selectListPage(page);
	}
	//수강후기 페이지기준 조회=============
	
	
	
	
	
	
	
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
	
	
	
	
	
	
}
