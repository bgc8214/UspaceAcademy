package com.uspaceacademy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LectureReviewController{

	@RequestMapping("/lecture_review_insert") //main.jsp링크
	public String insert()
	{
		//검증하는 것
		//서비스를 불러서 실제 업무처리
		
		return "lectureReview/lectureReview_list.tiles";
	}
}
