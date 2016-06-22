package com.uspaceacademy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
		System.out.println("대영"+codeType);//teachersubject가 요청파라미터로 조회
		List codeList = lectureService.searchCode(codeType);
		//List teacherList = memberService.getAllTeacher();
		Map map = new HashMap();
		map.put("codeList", codeList);
		//map.put("teacherList", teacherList);
		return new ModelAndView("lecture/register_form.tiles", map);
	}
	
	@RequestMapping("/getTeacherBySubject.do")
	@ResponseBody
	public List getTeacherBySubject(String teacherSubject){
		System.out.println("대영"+teacherSubject);
		List teacherList = memberService.getTeacherBySubject(teacherSubject);
		//System.out.println(teacherList);
		//return new ModelAndView("lecture/register_form.tiles","teacherList", teacherList);
		return teacherList;
	}
	
}
