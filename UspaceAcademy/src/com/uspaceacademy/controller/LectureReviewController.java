package com.uspaceacademy.controller;
//영주 - 수강후기
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.CodeService;
import com.uspaceacademy.service.LectureReviewService;
import com.uspaceacademy.service.LectureService;
import com.uspaceacademy.vo.LectureReview;
import com.uspaceacademy.vo.Student;


@Controller
@RequestMapping("/lectureReview")
public class LectureReviewController{
	
	@Autowired
	private LectureReviewService service; 
	@Autowired
	private LectureService lectureService;
	@Autowired
	private CodeService codeService;
	
	//mybatis.config.xml에 mapper 등록하기

	
	
	
	
	
	//수강후기 목록(main->lectureReview_list.jsp)  - 페이징처리후
	@RequestMapping("/lecture_review_list.do") //main.jsp링크
	public ModelAndView list(@RequestParam(defaultValue="1") int page){   //@RequestParam(defaultValue="1") jsp에서 param.page 해줌.

		Map map = service.selectPagingCount(page);
		map.put("page", page);
		
		System.out.println("수강후기 리스트ok");
		return new ModelAndView("lectureReview/lectureReview_list.tiles",  map);   																									//오류났던거적기 : map으로 넘겨줄땐 앞에 "  "  <- string으로 넘겨주는거 안적어도됨,  map이 page라는 이름으로 넘겨줌
	}	


	
	
	
	
	
	
	
	
	//수강후기 상세조회(lectureReview_list.jsp -> lectureReview_detail.jsp)
	@RequestMapping("/lecture_review_detail") //lectureReview_detail.jsp에 제목에 링크
	public ModelAndView detail(String reviewNo){			//string으로하기
		int num = Integer.parseInt(reviewNo);				//parseInt해야함
		
		LectureReview lectureListReview = service.selectNo(num);//no값으로 게시물 찾아옴
		service.selectHit(lectureListReview);//조회수 증가시키기 - 수강후기 상세조회 여기서 처리.
		
		System.out.println("수강후기 상세조회ok");
		return new ModelAndView("lectureReview/lectureReview_detail.tiles", "lectureListReview", lectureListReview);
	}


	
	
	
	
	
	
	
	
	//수강후기 작성 폼 (lectureReview_list.jsp -> lectureReview_register.jsp)
	@RequestMapping("/lecture_review_register")//lectureReview_detail.jsp에 수강후기등록!버튼에 링크
	public ModelAndView registerForm(String codeType){
		
		HashMap map = new HashMap<>();
		List codeList = codeService.searchCodeNameByType(codeType);
		List lectureTitle = lectureService.selectLectureTitleByLectureSubject("국어"); //lecture 테이블에서 가져옴
		
		map.put("lectureTitle", lectureTitle); // lecture테이블에서 가져온 강의명 뿌려줌 lectureTitle라는 이름으로
		map.put("codeType", codeList); 
		System.out.println("수강후기 작성폼ok");	
		return new ModelAndView("lectureReview/lectureReview_register.tiles",map);//등록폼으로 온다*
	}
   //폼에서 과목명에 따른 강좌명 조회 //개설강좌에서 -> 과목명(lectureSubject)으로 강의명(lectureTitle)가져오기!
	@RequestMapping("/selectLectureTitleByLectureSubject.do")
	@ResponseBody
	public List getLectureList(String lectureSubject){
		List lectureTitle = lectureService.selectLectureTitleByLectureSubject(lectureSubject);
		return lectureTitle;
	}
	
	

	
	
	
	
	
	
	
	
	
	//Validator!  사용할때 ex ->   (@ModelAttribute Notice notice, BindingResult errors){ -> vo 바로 옆에 BindingResult errors 써줘야함, 중간에 String abc이런거 있으면 안됨.
	//수강후기 작성하고 등록(수강후기작성완료 눌렀을때)
	@RequestMapping("/lecture_review_registerSuccess")//lectureReview_register.jsp 에서  
	public ModelAndView register(@ModelAttribute  @Valid   LectureReview lectureReview,  BindingResult errors, HttpServletRequest request){//오류적기 : 생성자 4개 받는거만들고, (jsp에서 받아오는 데이터에대한 데이터 만들기)
		//글번호,글쓴이,강의과목,강의명,제목,글내용,날짜,조회수		
		HttpSession session = request.getSession();//
		Student s = (Student)session.getAttribute("login_info");//
		String studentName = s.getStudentName();//
		String studentId = s.getStudentId();
		
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		if(errors.hasErrors()){																			//
			return new ModelAndView("lectureReview/lectureReview_register.tiles");   //오류났던거 적기 : 여기 3줄 해줘야함 , 오류나면 다시 그페이지 보여주면서 valitator보여줘야하기때문.
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String sdfDate = sdf.format(date);
	
		//글번호
		int num = service.selectNextNo();
		
		LectureReview lectureReview1 = new LectureReview(num,studentId,studentName,lectureReview.getLectureSubject(),lectureReview.getLectureTitle(),lectureReview.getReviewTitle(),lectureReview.getReviewContent(),sdfDate,0);
		
		service.insert(lectureReview1);
		
		System.out.println("수강후기 작성하고 등록ok");
	return new ModelAndView("lectureReview/lectureReview_detail.tiles","lectureListReview",lectureReview1); // 영주 수정할것  :  success페이지 따로만들어야함 새로고침하면 또 등록되니까 (리다이렉트 방식으로 어떻게해서..)
	}
	



	
	
	
	
	
	
	
	
	//수강후기 삭제(수강상세조회에서 삭제 눌렀을때)
	@RequestMapping("/lecture_review_delete")//lectureReview_detail.jsp 에서  
	public ModelAndView delete(int reviewNo, String type){
		//int num = Integer.parseInt(reviewNo);
		service.delete(reviewNo);
		List list = service.selectList(type);
		
		System.out.println("수강후기 삭제 ok");
	return new ModelAndView("lectureReview/lectureReview_list.tiles","lectureListReview",list); 
	}
	
	
	
	
	
	
	
	
	
	
	

	@RequestMapping("/lecture_review_modifyForm")//lectureReview_detail.jsp 에서  
	public ModelAndView modifyForm(int reviewNo, String codeType){

		LectureReview lectureReview = service.selectNo(reviewNo); //글번호     수정폼가기전에 reviewNo로 vo가져온다
		List codeList = codeService.searchCodeNameByType(codeType);//코드타입
		List lectureTitle = lectureService.selectLectureTitleByLectureSubject("국어");//lecture 테이블에서 가져옴.
				
		HashMap map = new HashMap<>();
		map.put("lectureTitle", lectureTitle); //lecture테이블에서 가져온 강의명(lectureTitle)뿌려줄것.
		map.put("lectureListReview", lectureReview);
		map.put("codeType", codeList);
		
		
		System.out.println("수강후기 수정 폼 ok");
		return new ModelAndView("lectureReview/lectureReview_modify.tiles", map); 
	}
	


	
	
	
	
	
	
	
	
	//수강후기수정완료하기 (수정폼에서 수강후기 수정완료 눌렀을때)
	@RequestMapping("/lecture_review_modify") //lectureReview_modify.jsp에서
	public ModelAndView modify(@ModelAttribute("lec") @Valid LectureReview lectureReview, BindingResult errors) {  //오류:여기에서6개 넘겨줌, 매매 mapper에서도 6개 쿼리적어줘야함!!!*
		
		/*SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String uDate = sdf.format(date1);*/	
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		if(errors.hasErrors()){																			//
			return new ModelAndView("/lectureReview//lecture_review_modifyForm.do?codeType=teacherSubject&reviewNo="+lectureReview.getReviewNo());   //여기 3줄 해주기
		}
																																												
		LectureReview lectureReview1 = new LectureReview(lectureReview.getReviewNo(),lectureReview.getReviewWriterId(),lectureReview.getReviewWriter(),lectureReview.getLectureSubject(),lectureReview.getLectureTitle(),lectureReview.getReviewTitle(),lectureReview.getReviewContent(),lectureReview.getReviewDate(),lectureReview.getReviewHit());
		service.update(lectureReview1);																																								//오류났던거 적기 : 생성자 vo랑 여기 순서 다르면 안됨 , 순서 바뀌면 바뀌어서 들어감!
																																																			//lectureReview.setReviewDate(reviewDate);//reviewDate setter로 넣어줌
		
		System.out.println("수강후기 수정 ok");
		return new ModelAndView("lectureReview/lectureReview_detail.tiles","lectureListReview", lectureReview1); //수강후기 수정완료 버튼누르면 내가 수정한내용 detail에서 보여짐
	}

	
	
	
	
	
	
	
	
	
	
	
	
	//검색기능
	@RequestMapping("lecture_review_search")
	public ModelAndView search(@RequestParam(defaultValue="1") int page,  @RequestParam(defaultValue="")String searchType, @RequestParam(defaultValue="")String keyword){
		Map<String,Object> map = new HashMap<String,Object>();
		if(searchType.equals("reviewSubject")){//과목일경우
			map = service.searchLectureSubject(keyword, page);
			map.put("searchType", searchType);
			map.put("keyword",keyword);
		}else if(searchType.equals("reviewTitle")){//제목일경우
			map = service.searchReviewTitle(keyword, page);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
		}else if(searchType.equals("lectureTitle")){ //강의명일 경우
			map = service.searchLectureTitle(keyword, page);
			map.put("searchType",searchType);
			map.put("keyword",keyword);
		}else{
			map = service.selectPagingCount(page);
			List codeList = codeService.searchCodeNameByType("teacherSubject");
			map.put("page", page);
			map.put("codeList",codeList );
			map.put("searchType",searchType );
			map.put("keyword",keyword );
		}
		System.out.println(map);
		return new ModelAndView("lectureReview/lectureReview_search.tiles",map);
	}
}










