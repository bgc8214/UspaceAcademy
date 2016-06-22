package com.uspaceacademy.controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.LectureReviewService;
import com.uspaceacademy.vo.LectureReview;

@Controller
@RequestMapping("/lectureReview")
public class LectureReviewController{
	
	@Autowired
	private LectureReviewService service;
	
	
	//수강후기 전체 리스트
	@RequestMapping("/lecture_review_insert") //main.jsp링크
	public ModelAndView insert(){
		System.out.println("수강후기 리스트");
		List lectureListReview = service.selectList();
		return new ModelAndView("lectureReview/lectureReview_list.tiles", "lectureListReview", lectureListReview);
	}
	
	
	
	//수강후기 상세조회
	@RequestMapping("/lecture_review_detail") //main.jsp링크
	public ModelAndView insert2(String reviewNo){		//string으로하기
		int num = Integer.parseInt(reviewNo);				//parseInt해야함
		//System.out.println("수강후기 상세조회");
		//System.out.println("controller "+service.selectNo(num));
		LectureReview lectureListReview2 = service.selectNo(num);
		//System.out.println(lectureListReview2);
		return new ModelAndView("lectureReview/lectureReview_detail.tiles", "lectureListReview2", lectureListReview2);
	}
	
	
	
	//수강후기 등록
	@RequestMapping("/lecture_review_register")
	public ModelAndView register(){
	
	
		
	return new ModelAndView("lectureReview/lectureReview_register.tiles");
	}
	
	
}









