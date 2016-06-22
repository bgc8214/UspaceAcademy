package com.uspaceacademy.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.MemberService;
import com.uspaceacademy.validaotor.StudentValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;


@Controller
@RequestMapping("/member")
public class MemberController
{
	@Autowired
	private MemberService service;

	/*
	 * @RequestMapping("/register_form.do") public String register_form() {
	 * System.out.println("연습");
	 * 
	 * return "member/student_register_form.tiles"; }
	 */

	@RequestMapping("/studentRegister.do")
	public String studentRegister(@ModelAttribute Student student, BindingResult errors)
	{
		// 검증 - StudentValidator
		StudentValidator validator = new StudentValidator();
		validator.validate(student, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		System.out.printf("요청파라미터 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n",error,errorCount);
		if(errors.hasErrors())
		{
			//에러 응답 페이지로 이동
			return "/member/studentRegisterForm.do";
		}
		
		
		service.insertStudent(student);

		return "redirect:/member/studentRedirect.do";
	}

	@RequestMapping("/studentRedirect.do")
	public String studentRedirectRegister() // 새로고침 시 더 등록 안되도록 redirect 처리
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
	public String teacherRedirectRegister() // 새로고침 시 더 등록 안되도록 redirect 처리
	{

		System.out.println("리다이렉트");
		return "main.tiles";
	}

	@RequestMapping("/teacherRegisterForm.do") // 강사가입폼으로 이동 , 코드 타입을 받아온다
	public ModelAndView moveTeacher(String codeType)
	{
		HashMap map = new HashMap<>(); // 코드테이블을 넘기기 위한 맵
		System.out.println(codeType);
		List codeList = service.searchCode(codeType); // 코드타입 정보를 리스트로 받는다.
		System.out.println(codeList);

		map.put("codeType", codeList);
		return new ModelAndView("member/teacherRegisterForm.tiles", map);
	}

	@RequestMapping("/login.do")
	public String memberLogin(String id, String password, HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		Student student = service.findStudentById(id);
		Teacher teacher = service.findTeacherById(id);
		System.out.println(student);
		System.out.println(teacher);
		if (student == null && teacher == null)
		{
			System.out.println("아이디 없음");
		} else if (student == null && teacher != null)
		{
			if (teacher.getTeacherPassword().equals(password))
			{
				System.out.println("강사로 로그인 성공");
				session.setAttribute("login_info", teacher);
				session.setAttribute("memberType","administrator");
			} else
			{
				System.out.println("강사 비밀번호 틀림");
			}
		} else if (student != null && teacher == null)
		{
			if (student.getStudentPassword().equals(password))
			{
				System.out.println("학생으로로 로그인 성공");
				session.setAttribute("login_info", student);
				session.setAttribute("memberType", "student");
			} else
			{
				System.out.println("학생 비밀번호 틀림");
			}
		} else // 로그인시 입력 아이디가 학생, 강사 둘 다 있을때
		{
			if (teacher.getTeacherPassword().equals(password))
			{
				System.out.println("둘 다 될 때 강사로 로그인 성공");
			} else
			{
				System.out.println("둘 다 될 때 강사 비밀번호 틀림");
			}

			if (student.getStudentPassword().equals(password))
			{
				System.out.println("둘 다 될 때 학생으로로 로그인 성공");
			} else
			{
				System.out.println("둘 다 될 때 학생 비밀번호 틀림");
			}
		}

		return "main.tiles";
	}

}
