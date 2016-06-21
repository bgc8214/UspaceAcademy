package com.uspaceacademy.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.LectureService;
import com.uspaceacademy.vo.Lecture;

@Controller
@RequestMapping("/lecture")
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	
	
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
		session.setAttribute("memberType", "administrator");
		String memberType = (String) session.getAttribute("memberType");
		ArrayList list = new ArrayList();
		list.add(lecture);
		list.add(memberType);
		return list;
	}
	
	
}
