package com.uspaceacademy.controller;


import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	
	//수강후기 목록(main->lectureReview_list.jsp)
	@RequestMapping("/lecture_review_list") //main.jsp링크*
	public ModelAndView list(){
		System.out.println("수강후기 리스트ok");
		List lectureListReview = service.selectList();
		return new ModelAndView("lectureReview/lectureReview_list.tiles", "lectureListReview", lectureListReview);
	}
	
	
	
	//수강후기 상세조회(lectureReview_list.jsp -> lectureReview_detail.jsp)
	@RequestMapping("/lecture_review_detail") //lectureReview_detail.jsp에 제목에 링크*
	public ModelAndView detail(String reviewNo){		//string으로하기
		int num = Integer.parseInt(reviewNo);				//parseInt해야함
		System.out.println("수강후기 상세조회ok");
		//System.out.println("controller "+service.selectNo(num));
		LectureReview lectureListReview2 = service.selectNo(num);
		//System.out.println(lectureListReview2);
		return new ModelAndView("lectureReview/lectureReview_detail.tiles", "lectureListReview2", lectureListReview2);
	}
	
	
	
	//수강후기 작성 폼 (lectureReview_list.jsp -> lectureReview_register.jsp)
	@RequestMapping("/lecture_review_register")//lectureReview_detail.jsp에 수강후기등록!버튼에 링크*
	public ModelAndView registerForm(){
		System.out.println("수강후기 작성폼ok");
	
	return new ModelAndView("lectureReview/lectureReview_register.tiles");//등록폼으로 온다*
	}
	
	
	
	//수강후기 작성하고 등록(수강후기작성완료 눌렀을때)
	@RequestMapping("/lecture_review_registerSuccess")//lectureReview_register.jsp 에서  
	public ModelAndView register(String lectureReviewSubject, String lectureReviewTitle ,String title, String content){
		//글번호,글쓴이,강의과목,강의명,제목,글내용,날짜,조회수
		System.out.println(lectureReviewTitle);
		//날짜
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdfDate = sdf.format(date);
		//글번호
		int num = service.selectNextNo();
		
		
		
		LectureReview lectureReview = new LectureReview(num,"이영주",lectureReviewSubject,lectureReviewTitle,title,content,sdfDate,0);
		service.insert(lectureReview);
		
		
		System.out.println("수강후기 작성하고 등록ok");
		
	return new ModelAndView("lectureReview/lectureReview_detail.tiles","lectureListReview2",lectureReview); // 영주 수정할것  :  success페이지 따로만들어야함 새로고침하면 또 등록되니까 (리다이렉트 방식으로 어떻게해서..)
	}
	
	
	
	
	
	
	
	
	
	//수강후기 삭제(수강상세조회에서 삭제 눌렀을때)
	@RequestMapping("/lecture_review_delete")//lectureReview_detail.jsp 에서  
	public ModelAndView delete(){

		
		
		System.out.println("수강후기 삭제 ok");
		
	return new ModelAndView("lectureReview/lectureReview_delete.tiles"); // 영주 수정할것  :  success페이지 따로만들어야함 새로고침하면 또 등록되니까 (리다이렉트 방식으로 어떻게해서..)
	}
	
	
	
	
	
	
	
	
	//수강후기 수정(수강상세조회에서 수정 눌렀을때)
	@RequestMapping("/lecture_review_modify")//lectureReview_detail.jsp 에서  
	public ModelAndView modify(){

		
		
		System.out.println("수강후기 수정 ok");
		
	return new ModelAndView("lectureReview/lectureReview_modify.tiles"); // 영주 수정할것  :  success페이지 따로만들어야함 새로고침하면 또 등록되니까 (리다이렉트 방식으로 어떻게해서..)
	}
	
	
	
	
	
	
}









