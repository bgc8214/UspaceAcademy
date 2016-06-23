package com.uspaceacademy.controller;
//영주 - 수강후기
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	public ModelAndView list(String type){
		System.out.println("수강후기 리스트ok");
		List lectureListReview = service.selectList(type);
		return new ModelAndView("lectureReview/lectureReview_list.tiles", "lectureListReview", lectureListReview);
	}
	
	
	
	
	
	
	//수강후기 상세조회(lectureReview_list.jsp -> lectureReview_detail.jsp)
	@RequestMapping("/lecture_review_detail") //lectureReview_detail.jsp에 제목에 링크*
	public ModelAndView detail(String reviewNo){		//string으로하기
		int num = Integer.parseInt(reviewNo);				//parseInt해야함
		System.out.println("수강후기 상세조회ok");
		
		LectureReview lectureListReview = service.selectNo(num);//
		
		//service.update(lectureListReview); //
		
		return new ModelAndView("lectureReview/lectureReview_detail.tiles", "lectureListReview", lectureListReview);
	}
	
	
	
	
	
	
	//수강후기 작성 폼 (lectureReview_list.jsp -> lectureReview_register.jsp)
	@RequestMapping("/lecture_review_register")//lectureReview_detail.jsp에 수강후기등록!버튼에 링크*
	public ModelAndView registerForm(String codeType){
		
		HashMap map = new HashMap<>();
		System.out.println("test"+codeType);
		List codeList = service.selectCodeName(codeType);
		
		
		map.put("codeType", codeList);
		System.out.println("렉쳐리뷰컨트롤러 코드리스트"+codeList);
		
		System.out.println("수강후기 작성폼ok");	
		return new ModelAndView("lectureReview/lectureReview_register.tiles",map);//등록폼으로 온다*
	}
	
	
	
	
	
	
	//수강후기 작성하고 등록(수강후기작성완료 눌렀을때)
	@RequestMapping("/lecture_review_registerSuccess")//lectureReview_register.jsp 에서  
	public ModelAndView register(String lectureSubject, String lectureTitle ,String title, String content){
		//글번호,글쓴이,강의과목,강의명,제목,글내용,날짜,조회수
		//System.out.println(lectureTitle);
		//날짜
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdfDate = sdf.format(date);
		//글번호
		int num = service.selectNextNo();
		
		LectureReview lectureReview = new LectureReview(num,"이영주",lectureSubject,lectureTitle,title,content,sdfDate,0);
		service.insert(lectureReview);
		
		System.out.println("수강후기 작성하고 등록ok");
	return new ModelAndView("lectureReview/lectureReview_detail.tiles","lectureListReview",lectureReview); // 영주 수정할것  :  success페이지 따로만들어야함 새로고침하면 또 등록되니까 (리다이렉트 방식으로 어떻게해서..)
	}
	
	
	
	
	
	
	
	
	
	//수강후기 삭제(수강상세조회에서 삭제 눌렀을때)
	@RequestMapping("/lecture_review_delete")//lectureReview_detail.jsp 에서  
	public ModelAndView delete(int reviewNo, String type){
		//int num = Integer.parseInt(reviewNo);
		service.delete(reviewNo);
		List list = service.selectList(type);
		System.out.println("수강후기 삭제 ok");
	return new ModelAndView("lectureReview/lectureReview_list.tiles","lectureListReview",list); // 영주 수정할것  :  삭제되긴하는데 새로고침해야보여짐, 삭제후 상세페이지보여주기
	}
	
	
	
	
	
	
	
	
	
	//수강후기 수정폼(수강상세조회에서 수정하기 눌렀을때 - modify폼으로감)
	@RequestMapping("/lecture_review_modifyForm")//lectureReview_detail.jsp 에서  
	public ModelAndView modifyForm(int reviewNo){
		//int num = Integer.parseInt(reviewNo);//버려
		LectureReview lectureReview = service.selectNo(reviewNo); //수정폼가기전에 reviewNo로 vo가져온다..
		
		System.out.println("수강후기 수정 폼 ok");
		return new ModelAndView("lectureReview/lectureReview_modify.tiles","lectureListReview",lectureReview); // 영주 수정할것  : 내용그대로뿌려주는거안됨
	}
	
	
	
	
	
	
	
	
	
	
	//수강후기수정완료하기 (수정폼에서 수강후기 수정완료 눌렀을때)
	@RequestMapping("/lecture_review_modify") //lectureReview_modify.jsp에서
	public ModelAndView modify(String reviewNo, String reviewWriter,String lectureSubject, String lectureTitle ,String title, String content, String reviewDate){//오류:여기에서6개 넘겨줌, 매매 mapper에서도 6개 쿼리적어줘야함!!!*
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
		Date date1 = new Date();
		String uDate = sdf.format(date1);*/
	
		LectureReview lectureReview = new LectureReview(Integer.parseInt(reviewNo),reviewWriter,lectureTitle,lectureSubject,title,content);//여기 date없음
		service.update(lectureReview);
		lectureReview.setReviewDate(reviewDate);//reviewDate setter로 넣어줌
		
		System.out.println("수강후기 수정 ok");
		return new ModelAndView("lectureReview/lectureReview_detail.tiles","lectureListReview", lectureReview); //수강후기 수정완료 버튼누르면 내가 수정한내용 detail에서 보여짐
	}
}









