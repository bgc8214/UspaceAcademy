package com.uspaceacademy.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.AttendanceService;
import com.uspaceacademy.service.LectureService;
import com.uspaceacademy.service.MemberService;
import com.uspaceacademy.validaotor.StudentValidator;
import com.uspaceacademy.validaotor.TeacherValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/member")
public class MemberController
{
	@Autowired
	private MemberService service;
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private AttendanceService attendanceService;
	

	/*
	 * @RequestMapping("/register_form.do") public String register_form() {
	 * System.out.println("연습");
	 * 
	 * return "member/student_register_form.tiles"; }
	 */

	@RequestMapping("/studentRegister.do")
	public String studentRegister(@ModelAttribute Student student, String addr1, String addr2, BindingResult errors)
	{
		student.setStudentAddress(addr1+addr2);

		// 검증 - StudentValidator
		StudentValidator validator = new StudentValidator();
		validator.validate(student, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		System.out.printf("학생 가입 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		if (errors.hasErrors())
		{
			// 에러 응답 페이지로 이동
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
	public String teacherRegister(@ModelAttribute Teacher teacher, String addr1, String addr2, BindingResult errors)
	{
		teacher.setTeacherAddress(addr1+addr2);
		
		TeacherValidator validator = new TeacherValidator();
		validator.validate(teacher, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		System.out.printf("강사 가입 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		if (errors.hasErrors())
		{
			// 에러 응답 페이지로 이동
			return "/member/teacherRegisterForm.do?codeType=teacherSubject";
		}
		service.insertTeacher(teacher);

		return "redirect:/member/teacherRedirect.do";
	}

	@RequestMapping("/teacherRedirect.do")
	public String teacherRedirectRegister() // 새로고침 시 더 등록 안되도록 redirect 처리
	{

		System.out.println("리다이렉트");
		return "main.tiles";
	}

	
	
	//영주1
	@RequestMapping("/teacherRegisterForm.do") // 강사가입폼으로 이동 , 코드 타입을 받아온다
	public ModelAndView moveTeacher(String codeType)
	{
		HashMap map = new HashMap<>(); // 코드테이블을 넘기기 위한 맵
		System.out.println("멤버컨트롤러 코드타입"+codeType);
		List codeList = service.searchCode(codeType); // 코드타입 정보를 리스트로 받는다.
		System.out.println("멤버컨트롤러 코드리스트"+codeList);

		map.put("codeType", codeList);
		return new ModelAndView("member/teacherRegisterForm.tiles", map);
	}

	
	
	
	@RequestMapping("/login.do")
	public String memberLogin(String id, String password, HttpServletRequest request)
	{
		HttpSession session = request.getSession();

		Student student = service.findStudentById(id);
		Teacher teacher = service.findTeacherById(id);
		//System.out.println(student);
		//System.out.println(teacher);
		if (id.equals("admin")) // 관리자 로그인
		{
			if (password.equals("1234"))
			{
				System.out.println("관리자 로그인 성공");
				session.setAttribute("login_info", "administrator");
				session.setAttribute("memberType", "administrator");
				return "main.tiles";
			} else
			{
				System.out.println("관리자 로그인 실패");
				request.setAttribute("passwordError", "패스워드를 틀렸습니다.");
				return "/loginForm.do";
			}
		}

		if (student == null && teacher == null)
		{
			System.out.println("아이디 없음");
			request.setAttribute("idError", "아이디를 틀렸습니다.");
			return "/loginForm.do";
		} else if (student == null && teacher != null)
		{
			if (teacher.getTeacherPassword().equals(password))
			{
				System.out.println("강사로 로그인 성공");
				session.setAttribute("login_info", teacher);
				session.setAttribute("memberType", "teacher");
			} else
			{
				System.out.println("강사 비밀번호 틀림");
				request.setAttribute("passwordError", "패스워드를 틀렸습니다.");
				return "/loginForm.do";
			}
		} else if (student != null && teacher == null)
		{
			if (student.getStudentPassword().equals(password))
			{
				System.out.println("학생으로 로그인 성공");
				session.setAttribute("login_info", student);
				System.out.println(student);
				session.setAttribute("memberType", "student");
			} else
			{
				System.out.println("학생 비밀번호 틀림");
				request.setAttribute("passwordError", "패스워드를 틀렸습니다.");
				return "/loginForm.do";
			}
		} else // 로그인시 입력 아이디가 학생, 강사 둘 다 있을때
		{
			if (teacher.getTeacherPassword().equals(password))
			{
				System.out.println("둘 다 될 때 강사로 로그인 성공");
			} else
			{
				System.out.println("둘 다 될 때 강사 비밀번호 틀림");
				request.setAttribute("passwordError", "패스워드를 틀렸습니다.");
				return "/loginForm.do";
			}

			if (student.getStudentPassword().equals(password))
			{
				System.out.println("둘 다 될 때 학생으로로 로그인 성공");
			} else
			{
				System.out.println("둘 다 될 때 학생 비밀번호 틀림");
				request.setAttribute("passwordError", "패스워드를 틀렸습니다.");
				return "/loginForm.do";
			}
		}

		return "main.tiles";
	}

	@RequestMapping("/studentIdCheck.do")
	public String studentIdCheck(String id,  HttpServletRequest request) // 아이디
																					// 중복체크
	{
		if (id.equals(null) || id.trim().isEmpty())
		{
			request.setAttribute("idError", "아이디를 입력하세요");

			return "/WEB-INF/view/member/studentDuplicationCheck.jsp";
		}

/*		if (id.length() < 5)
		{
			request.setAttribute("idError", "아이디는 5글자보다 길어야합니다");

			return "/WEB-INF/view/member/studentDuplicationCheck.jsp";
		}*/
		
		// ID 정규식 체크
		boolean err = false;
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(id);
		if(m.matches())
			err = true;
		if(err == false) {
			System.out.println("형식에 맞지 않는 아이디가 입력되었습니다.");
			request.setAttribute("idError", "영문대소문자로 시작하고 특수문자 _포함이 가능하며 6~12글자로 입력하세요");
			return "/WEB-INF/view/member/teacherDuplicationCheck.jsp";
		}
		

		if (service.findStudentById(id) != null || service.findTeacherById(id) != null || id.equals("admin"))
		{
			System.out.println("아이디가 중복됩니다.");
			request.setAttribute("idCheck", false);
		} else
		{
			request.setAttribute("idCheck", true);
			request.setAttribute("id", id);
		}

		return "/WEB-INF/view/member/studentDuplicationCheck.jsp";
	}
	
	@RequestMapping("/teacherIdCheck.do")
	public String teacherIdCheck(String id, HttpServletRequest request) // 아이디
																					// 중복체크
	{
		if (id.equals(null) || id.trim().isEmpty())
		{
			request.setAttribute("idError", "아이디를 입력하세요");

			return "/WEB-INF/view/member/teacherDuplicationCheck.jsp";
		}

/*		if (id.length() < 5)
		{
			request.setAttribute("idError", "아이디는 5글자보다 길어야합니다");

			return "/WEB-INF/view/member/teacherDuplicationCheck.jsp";
		}*/
		
		// ID 정규식 체크
		boolean err = false;
		String regex = "^[a-zA-Z]{1}[a-zA-Z0-9_]{5,11}$";

		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(id);
		if(m.matches())
			err = true;
		if(err == false) {
			System.out.println("형식에 맞지 않는 아이디가 입력되었습니다.");
			request.setAttribute("idError", "영문대소문자로 시작하고 특수문자 _포함이 가능하며 6~12글자로 입력하세요");
			return "/WEB-INF/view/member/teacherDuplicationCheck.jsp";
		}
		

		if (service.findStudentById(id) != null || service.findTeacherById(id) != null || id.equals("admin"))
		{
			System.out.println("아이디가 중복됩니다.");
			request.setAttribute("idCheck", false);
		} else
		{
			request.setAttribute("idCheck", true);
			request.setAttribute("id", id);
		}

		return "/WEB-INF/view/member/teacherDuplicationCheck.jsp";
	}

	@RequestMapping("/logout")
	public String memberLogout(HttpServletRequest request)
	{
		HttpSession session = request.getSession();
		session.invalidate();
		return "main.tiles";
	}
	
	@RequestMapping("/findId")
	@ResponseBody 
	public String findId(String name, String phoneNo) //이름과 핸드폰 번호로 아이디 조회
	{
		Student student = new Student();
		student.setStudentName(name);
		student.setStudentPhoneNo(phoneNo);
		Student findStudent = service.findStudentId(student);  //학생으로 조회
		
		Teacher teacher = new Teacher();
		teacher.setTeacherName(name);
		teacher.setTeacherPhoneNo(phoneNo);
		Teacher findTeacher = service.findTeacherId(teacher);
		
		
		if(findStudent==null && findTeacher==null) //찾는 아이디가 없는경우
		{
			return "없습니다";
		}else if (findStudent!=null && findTeacher==null) { //학생 아이디 발견
			return findStudent.getStudentId();
		}else if(findStudent==null && findTeacher!=null) // 강사 아이디 발견
		{
			return findTeacher.getTeacherId(); 
		}else //둘 다 있는 경우 (있어서는 안된다) 처리는 해줌
		{
			return findStudent.getStudentId()+","+findTeacher.getTeacherId();
		}
		
	}
	
	
	@RequestMapping("/sendMail")
	public String sendMail(Student student,Teacher teacher) throws MessagingException //이메일을 보내주는 매소드
	{
		System.out.println("이메일");
		 String host = "smtp.gmail.com";
	        int port=465;
	         
	        // 메일 내용
	        String recipient = ""; // 받을 사람
	        if(student==null){  //받아온 정보로 이메일 알려준다.
	        	recipient = teacher.getTeacherEmail(); 
		    }else
		    {
		      	recipient = student.getStudentEmail();
		    }
	        String subject = "패스워드 찾은 결과입니다."; //메일 제목 지정
	        String body;
	        if(student==null){  //받아온 정보로 비밀번호 알려준다.
	         body = "비밀번호 : "+teacher.getTeacherPassword();   //메일의 내용을 설정한다.
	        }else
	        {
	        	 body = "비밀번호 : "+student.getStudentPassword();
	        }
	        Properties props = System.getProperties();
	         
	         
	        props.put("mail.smtp.host", host);
	        props.put("mail.smtp.port", port);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "true");
	        props.put("mail.smtp.ssl.trust", host);
	          
	        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication("wce3308", "qorrbcjf123"); //자신의 아이디 비밀번호
	            }
	        });
	        session.setDebug(true); //for debug
	          
	        Message mimeMessage = new MimeMessage(session);
	        if(student==null){
	        mimeMessage.setFrom(new InternetAddress(teacher.getTeacherEmail())); //받아온 정보로 메일 지정
	        }
	        else
	        {
	        	mimeMessage.setFrom(new InternetAddress(student.getStudentEmail()));
	        }
	        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
	        mimeMessage.setSubject(subject);
	        mimeMessage.setText(body);
	        Transport.send(mimeMessage);

		return "main.tiles";
	}
	
	@RequestMapping("/findPassword")
	@ResponseBody 
	public String findPassword(String id, String email) throws MessagingException //이름과 핸드폰 번호로 아이디 조회
	{
		System.out.println("비밀번호 조회");
		System.out.println("아이디"+id);
		System.out.println(email);
		Student student = new Student();
		student.setStudentId(id);
		student.setStudentEmail(email);
		Student findStudent = service.findStudentPassword(student);  //학생으로 조회
		
		Teacher teacher = new Teacher(); //강사로 조회
		teacher.setTeacherId(id);
		teacher.setTeacherEmail(email);
		Teacher findTeacher = service.findTeacherPassword(teacher);
		
		
		if(findStudent==null && findTeacher==null) //찾는 비밀번호가 없는경우
		{
			System.out.println("비밀번호 없음");
			return "찾으시는 비밀번호가 없습니다.";
		}else if (findStudent!=null && findTeacher==null) { //학생 비밀번호 발견
			System.out.println("학생 비밀번호");
			sendMail(findStudent, null);
			return "메일을 통해 비밀번호를 보내드렸습니다.";
		}else if(findStudent==null && findTeacher!=null) // 강사 비밀번호 발견
		{
			System.out.println("강사 비밀번호");
			sendMail(null, findTeacher);
			return "메일을 통해 비밀번호를 보내드렸습니다."; 
		}else //둘 다 있는 경우 (있어서는 안된다) 처리는 해줌
		{
			return "main.tiles";
		}
	}
	// 강사의 회원정보 수정을 위한 폼으로 이동
	@RequestMapping("/updateTeacherForm.do")
	public ModelAndView updateTeacherForm() {
		return new ModelAndView("member/teacher_updateForm.tiles");
	}
	
	// 강사 회원정보 수정
	@RequestMapping("/updateTeacher.do")
	public String updateAfterDetail(@ModelAttribute("updateForm") Teacher teacher, String baseAddress, String addr1, String addr2, HttpSession session, BindingResult errors) {
		
		if(addr1!=null && addr2!=null)
			teacher.setTeacherAddress(addr1+addr2);
		else
			teacher.setTeacherAddress(baseAddress);
		
		
		TeacherValidator validator = new TeacherValidator();
		validator.validate(teacher, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		System.out.printf("강사 가입 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		if (errors.hasErrors())
		{
			// 에러 응답 페이지로 이동
			return "/member/updateTeacherForm.do";
		}
			service.modifyTeacher(teacher);	// 수정 ok

			session.setAttribute("login_info", teacher);	// 세션에 수정된 강사정보 재 설정
		
			return "/teacherInfo.do";
	}
	
	// 학생의 회원정보 수정을 위한 폼으로 이동
	@RequestMapping("/updateStudentForm.do")
	public ModelAndView updateStudentForm() {
		return new ModelAndView("member/student_updateForm.tiles");
	}
	
	// 학생의 회원정보 수정
	@RequestMapping("/updateStudent.do")
	public String updateStudent(@ModelAttribute("updateForm") Student student, String baseAddress, String addr1, String addr2, HttpSession session, BindingResult errors) {
		
		if(addr1!=null && addr2!=null)
			student.setStudentAddress(addr1+addr2);
		else
			student.setStudentAddress(baseAddress);
		
		StudentValidator validator = new StudentValidator();
		validator.validate(student, errors);
		boolean error = errors.hasErrors();
		int errorCount = errors.getErrorCount();
		System.out.printf("학생 수정 처리중 에러 발생 여부 : %s, 발생 에러 개수 : %d%n", error, errorCount);
		
		if(errors.hasErrors()) {
			return "/student/updateStudentForm.do";
		}
		
		service.modifyStudent(student);
		session.setAttribute("login_info", student);
		System.out.println(session.getAttribute("login_info"));
		return "/studentInfo.do";
	}
	
	// 학생 탈퇴
	@RequestMapping("/deleteStudent.do")
	public String deleteStudent(HttpSession session) {
		Student student = (Student)session.getAttribute("login_info");
		service.deleteStudent(student.getStudentId());
		session.invalidate();
				return "main.tiles";
	}

	// 강사 탈퇴
	@RequestMapping("/deleteTeacher.do")
	public String deleteTeacher(HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("login_info");
		service.removeTeacher(teacher.getTeacherId());
		session.invalidate();
		return "main.tiles";
	}
	
	//관리자가 학생 강제 탈퇴
	@RequestMapping("/deleteStudentByAdmin.do")
	public String deleteStudentByAdmin(String studentId, HttpSession session) {

		service.deleteStudent(studentId);
		return "/member/studentAll.do";
	}
	
	//관리자가 강사 강제 탈퇴
	@RequestMapping("/deleteTeacherByAdmin.do")
	public String deleteTeacherByAdmin(String teacherId,HttpSession session) {
		
		service.removeTeacher(teacherId);
		return "/member/teacherAll.do";
	}
	
	// 모든 학생 조회
	@RequestMapping("/studentAll.do")
	public ModelAndView studentAll(@RequestParam(defaultValue="1") int page) {
		Map map = service.searchAllStudent(page);
		map.put("page", page);
		return new ModelAndView("member/studentAllInfo.tiles", map);
	}
	
	// 학생이름으로 검색
	@RequestMapping("/searchBystudentName.do")
	public ModelAndView searchBystudentName(@RequestParam(defaultValue="1") int page, String name) {
		Map map = service.searchBystudentNameService(name, page);
		map.put("page", page);
		map.put("name", name);
		return new ModelAndView("member/search_studentName.tiles", map);
	}
	
	// 모든 강사 조회
	@RequestMapping("/teacherAll.do")
	public ModelAndView teacherAll(@RequestParam(defaultValue="1") int page) {
		Map map = service.searchAllTeacher(page);
		map.put("page", page);
		return new ModelAndView("member/teacherAllInfo.tiles", map);
	}
	
	// 강사이름으로 검색
	@RequestMapping("/searchByteacherName.do")
	public ModelAndView searchByteacherName(@RequestParam(defaultValue="1") int page, String name) {
		Map map = service.searchByteacherNameService(name, page);
		map.put("page", page);
		map.put("name", name);
		return new ModelAndView("member/search_teacherName.tiles", map);
	}
	
	//강사가 마이페이지에서 내 강좌 보여주기 
	@RequestMapping("/selectAllByTeacherId.do")
	public ModelAndView selectAllByTeacherId(HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("login_info");
		teacher.getTeacherId();
		
		String teacherId = teacher.getTeacherId();
		
		List selectAllByTeacherId = lectureService.selectAllByTeacherId(teacherId);

		return new ModelAndView("member/teacher_lectureInfo.tiles", "lectureList", selectAllByTeacherId);
	}
	
	//관리자가 강사관리에서 강사의 과목 정보 보기
	@RequestMapping("/selectAllByTeacherId2.do")
	public ModelAndView selectAllByTeacherId2(String teacherId) {
		
		List selectAllByTeacherId = lectureService.selectAllByTeacherId(teacherId);

		return new ModelAndView("member/teacher_lectureInfo.tiles", "lectureList", selectAllByTeacherId);
	}
}
