package com.uspaceacademy.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.AttendanceService;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService service;
	
	// 강사가 강의중인 강좌목록 조회
	@RequestMapping("/attendanceList.do")
	public ModelAndView lectureList(HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("login_info");
		List list = service.selectTeachLectureService(teacher.getTeacherId());
		Map map = new HashMap<>();
		map.put("list", list);
		System.out.println("강의 목록 조회 " +list);
		return new ModelAndView("attendance/lecture_list.tiles", map);
		
	}

	// 강사가 선택한 강좌의 출석부 조회
	@RequestMapping("/attendanceSearch.do")
	public ModelAndView attendanceList(@ModelAttribute Lecture lecture) throws ParseException {
		
		// 조회 및 상세페이지로 이동하여 이미 등록된 츨결정보를 보여주고 출석등록이 가능하도록!!!
		List studentInfoList = service.selectLectureStudentInfoService(lecture.getLectureNo());	// 강사가 선택한 강좌의 학생 정보
		List attendanceList = service.attendanceStateService(lecture.getLectureNo());			// 출석 상태 조회		
		
		long diffDays = diffDaysMethod(lecture.getLectureStartDate(), lecture.getLectureEndDate());
		
		Map map = new HashMap<>();
		map.put("diffDays", diffDays);		// 강의일수
		map.put("studentInfoList", studentInfoList);	// 강사가 선택된 강의를 수강 중인 학생 정보
		map.put("lectureNo", lecture.getLectureNo());
		map.put("lecture", lecture);
		map.put("attendanceList", attendanceList);		// 일자별로 정렬된 학생들의 출결정보 - 출결이 등록된 마지막 날짜까지의....
	
		return new ModelAndView("attendance/attendance.tiles", map);	
	}
	
	// 강사가 학생의 출결 등록
	@RequestMapping("/attendanceRegister.do")
	public String attendanceRegister(int day, String [] attendanceState, int lectureNo, @ModelAttribute Lecture lecture) throws ParseException {
		List<Student> list = service.selectLectureStudentInfoService(lectureNo);	// 특정강의를 수강중인 학생 정보를 가진 객체 List
		service.attendanceRegisterService(day, attendanceState, lectureNo, list);	// 강의 날짜, 출결상태, 강의번호, 특정강의를 수강중인 학생 정보를 가진 객체 List
		return "redirect:/attendance/attendanceRedirect.do?startDate="+lecture.getLectureStartDate()+"&endDate="+lecture.getLectureEndDate()+"&lectureNo="+lecture.getLectureNo();
	}
	
	// 출석등록 redirect 처리!
	@RequestMapping("attendanceRedirect.do")
	public ModelAndView attendanceRegisterRedirect(String startDate, String endDate, int lectureNo) throws ParseException {
		long diffDays = diffDaysMethod(startDate, endDate);
		
		List studentInfoList = service.selectLectureStudentInfoService(lectureNo);	// 강사가 선택한 강좌의 학생 정보
		List attendanceList = service.attendanceStateService(lectureNo);			// 출석 상태 조회
		
		Map map = new HashMap<>();
		map.put("diffDays", diffDays);			// 강의일수
		map.put("studentInfoList", studentInfoList);	// 강사가 선택한 강좌의 학생정보
		map.put("attendanceList", attendanceList);		// 학생들의 출석 상태
		map.put("lectureNo", lectureNo);			// 강의 번호
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return new ModelAndView("attendance/attendance_detail.tiles", map);
	}
	
	// 일자(하루씩) 출결 수정
	@RequestMapping("/updateAttendance.do")
	public String modifyAttendance(int lectureNo, int day, String startDate, String endDate, @RequestParam String attendanceState) {
		List<Student> list = service.selectLectureStudentInfoService(lectureNo);	// 강의를 수강중인 학생 정보(학생id로 오름차순 정렬된)
		service.attendanceStateModify(lectureNo, day, attendanceState, list);	// 수정
		
		return "redirect:/attendance/attendanceUpdateRedirect.do?startDate="+startDate+"&endDate="+endDate+"&lectureNo="+lectureNo;
	}
	
	// 수정 Redirect - 출결 수정 후 조회 & 수정 페이지로 이동
	@RequestMapping("/attendanceUpdateRedirect.do")
	public ModelAndView modifyRedirect(String startDate, String endDate, int lectureNo) throws ParseException {
		long diffDays = diffDaysMethod(startDate, endDate);
		
		List studentInfoList = service.selectLectureStudentInfoService(lectureNo);	// 강사가 선택한 강좌의 학생 정보
		List attendanceList = service.attendanceStateService(lectureNo);			// 출석 상태 조회
		
		Map map = new HashMap<>();
		map.put("diffDays", diffDays);			// 강의일수
		map.put("studentInfoList", studentInfoList);	// 강사가 선택한 강좌의 학생정보
		map.put("attendanceList", attendanceList);		// 학생들의 출석 상태
		map.put("lectureNo", lectureNo);			// 강의 번호
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		return new ModelAndView("attendance/attendance_detail.tiles", map);
	}
	
	// 강의일수 구해주는 메서드
	public long diffDaysMethod(String startDate, String endDate) throws ParseException {
		String day1 = startDate.split("/")[0]+startDate.split("/")[1]+startDate.split("/")[2];
		String day2 = endDate.split("/")[0]+endDate.split("/")[1]+endDate.split("/")[2];
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault());
		Date date = dateFormat.parse(startDate);
		Date date1 = dateFormat.parse(endDate);
		long diff = date1.getTime() - date.getTime();		
		long diffDays = (diff/(24*60*60*1000))+1;		// 일차수
		return diffDays;
	}
	
	// 학생이 수강중인 강의 정보
	@RequestMapping("/studentLectureInfo.do")
	public ModelAndView studentLectInfo(HttpSession session) {
		
		Student student = (Student)session.getAttribute("login_info");
		List studentLectureInfo =  service.studentLectureInfoService(student.getStudentId());
		return new ModelAndView("member/student_lectureInfo.tiles", "studentLectureInfo", studentLectureInfo);
	}
	
	// 자신이 수강 중인 강의의 출결 상태
	@RequestMapping("/attendanceInfo.do")
	public ModelAndView studentAttendance(int lectureNo2, HttpSession session) {
		Student student = (Student)session.getAttribute("login_info");
		List attendanceStateList = service.studentAttendanceStateService(lectureNo2, student.getStudentId());
		return new ModelAndView("member/student_attendanceState.tiles",	"attendanceStateList", attendanceStateList);
	}	
}
	