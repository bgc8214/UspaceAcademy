package com.uspaceacademy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.uspaceacademy.service.LectureService;
import com.uspaceacademy.service.MemberService;
import com.uspaceacademy.vo.Lecture;

@Controller
@RequestMapping("/lecture")
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private MemberService memberService;
	
	//강의 목록 전체조회
	@RequestMapping("/lectureList.do")
	public ModelAndView getLectureList(){
		List lectureList = lectureService.getLectureList();
		
		return new ModelAndView("lecture/lecture_list.tiles", "lectureList", lectureList);
	}
	
	//수강번호로 강의 조회
	@RequestMapping("/findLectureByNo.do")
	@ResponseBody
	public List getLectureByNo(int lectureNo, HttpSession session){
		Lecture lecture = lectureService.getLectureByNo(lectureNo);
		String memberType = (String) session.getAttribute("memberType");
		ArrayList list = new ArrayList();
		list.add(lecture);
		list.add(memberType);
		return list;
	}
	//관리자가 강의를 등록하기 폼을 조회하기 위한 컨트롤러
	@RequestMapping("/registerForm.do")
	public ModelAndView registerLecture(String codeType){
		List codeList = lectureService.searchCode(codeType);
		List teacherList = memberService.getTeacherBySubject("국어");//default값인 국어선생님들을 옵션으로 뿌려줌
		Map map = new HashMap();
		map.put("codeList", codeList);
		map.put("teacherList", teacherList);
		return new ModelAndView("lecture/register_form.tiles", map);
	}
	
	//과목명으로 선생들을 조회해옴
	@RequestMapping("/getTeacherBySubject.do")
	@ResponseBody
	public List getTeacherBySubject(String teacherSubject){
		System.out.println("대영"+teacherSubject);
		List teacherList = memberService.getTeacherBySubject(teacherSubject);
		//System.out.println(teacherList);
		//return new ModelAndView("lecture/register_form.tiles","teacherList", teacherList);
		return teacherList;
	}
	
	//강의등록폼에서 리다이렉트로 모델을 부르는 핸들러를 redirect방식으로 요청하는 중간 연결자
	@RequestMapping("/lectureRegisterRedirect")
	public ModelAndView	registerRedirect(){
		List lectureList = lectureService.getLectureList();
		return new ModelAndView("lecture/lecture_list.tiles", "lectureList", lectureList);
		
	}
	
	//강의등록폼에서 입력한 정보를 토대로 강의를 등록해줌
	@RequestMapping("/registerLecture.do")
	public String registerLecture(String lectureTitle, String lectureDescription, int lectureStartTime, 	
		int lectureEndTime, String lectureDay, int lecturePrice, int lectureTotalStudent, String lectureStartDate, 
		String lectureEndDate, String lectureSubject, String teacherId2){
		
		int lectureNo = -1;//어차피 sequence를 사용할 것이므로 -1 세팅
		int lectureCurrentStudent = 0; // 초기 학생은 0명
		
		Lecture lecture = new Lecture(lectureNo, lectureTitle, lectureDescription, lectureStartTime, 
				lectureEndTime, lectureDay, lectureStartDate, lectureEndDate, lecturePrice, 
				lectureTotalStudent, lectureCurrentStudent, lectureSubject, teacherId2);
		int flag = lectureService.registerLecture(lecture);
		System.out.println(flag+"개의 강의가 등록되었습니다 - 대영-");
		return "redirect:/lecture/lectureRegisterRedirect.do";
	}
	
	//수정 폼을 불러오기 위한 핸들러 
	@RequestMapping("/getModifyForm.do")
	public ModelAndView getLectureModifyForm(int lectureNo, String codeType){
		Lecture lecture = lectureService.getLectureByNo(lectureNo);
		List codeList = lectureService.searchCode(codeType);
		List teacherList = memberService.getTeacherBySubject(lecture.getLectureSubject());
		Map map = new HashMap();
		map.put("lecture", lecture);
		map.put("codeList", codeList);
		map.put("teacherList", teacherList);
		return new ModelAndView("lecture/modify_form.tiles",map);
	}
	
	//강의를 수정하기 위한 컨트롤러
	@RequestMapping("modifyLectureByNo")
	public ModelAndView modifyLectureByNo(int lectureNo, String lectureTitle, String lectureDescription, int lectureStartTime, 	
		int lectureEndTime, String lectureDay, int lecturePrice, int lectureTotalStudent, String lectureStartDate, 
		String lectureEndDate, String lectureSubject, String teacherId2){
		
		int lectureCurrentStudent = 0;
		//업데이트할 새로운 강의 정보 등록
		Lecture lecture = new Lecture(lectureNo, lectureTitle, lectureDescription, lectureStartTime, lectureEndTime, lectureDay, lectureStartDate, lectureEndDate, lecturePrice, lectureTotalStudent, lectureCurrentStudent, lectureSubject, teacherId2);
		
		int flag = lectureService.modifyLectureByNo(lecture);
		System.out.println(flag+"개의 강의가 수정되었습니다 - 대영쓰 - ");
		//새로운 강의 리스트 조회
		List lectureList = lectureService.getLectureList();
		return new ModelAndView("lecture/lecture_list.tiles", "lectureList", lectureList);
	}
	
	//강의를 삭제하기 위한 컨트롤러
	@RequestMapping("removeLectureByNo.do")
	public ModelAndView removeLectureByNo(int lectureNo){
		//삭제하는 서비스 부름
		int flag = lectureService.removeLectureByNo(lectureNo);
		//새로운 강의 리스트 조회
		List lectureList = lectureService.getLectureList();
		return new ModelAndView("lecture/lecture_list.tiles", "lectureList", lectureList);
	}
	
}
