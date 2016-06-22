package com.uspaceacademy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.LectureReviewDao;
import com.uspaceacademy.vo.LectureReview;

@Service
public class LectureReviewService{
	
	@Autowired
	private LectureReviewDao dao;
	
//----------------------------------------	
	
	
	//수강후기 게시물등록
	public int insert(LectureReview lectureReview){
		return dao.insert(lectureReview);
	}
	
	//수강후기 게시물 LectureReview sequence처리
	
	
	//수강후기 전체 리스트
	
	
	//수강후기 수정
	
	
	//수강후기 삭제
	
	
	
	//수강후기 검색 제목+내용
	//수강후기 검색 제목
	//수강후기 검색 내용
	//수강후기 페이징처리
	
	
	
	
	
}
