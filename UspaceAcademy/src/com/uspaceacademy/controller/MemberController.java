package com.uspaceacademy.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.MemberService;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/member")
public class MemberController
{
	@Autowired
	private MemberService service;

	/*@RequestMapping("/register_form.do")
	public String register_form()
	{
		System.out.println("연습");

		return "member/student_register_form.tiles";
	}*/

	@RequestMapping("/studentRegister.do")
	public String studentRegister(@ModelAttribute Student student)
	{
	
			System.out.println(student);
			service.insertStudent(student);
		
		return "redirect:/member/studentRedirect.do";
	}
	
	@RequestMapping("/studentRedirect.do")
	public String studentRedirectRegister() //새로고침 시 더 등록 안되도록 redirect 처리	
	{
		
		System.out.println("리다이렉트");
		return "main.tiles";
	}
	
	@RequestMapping("/teacherRegister.do")
	public String teacherRegister(@ModelAttribute Teacher teacher)
	{
	
			System.out.println(teacher);
			service.insertTeacher(teacher);
		
		return "redirect:/member/teacherRedirect.do";
	}
	
	@RequestMapping("/teacherRedirect.do")
	public String teacherRedirectRegister() //새로고침 시 더 등록 안되도록 redirect 처리
	{
		
		System.out.println("리다이렉트");
		return "main.tiles";
	}
	
	@RequestMapping("/teacherRegisterForm.do") //강사가입폼으로 이동 , 코드 타입을 받아온다
	public ModelAndView moveTeacher()
	{
		
		return new ModelAndView("member/teacherRegisterForm.tiles", "codeType","subject");
	}
	
}
