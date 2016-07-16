package com.uspaceacademy.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import com.uspaceacademy.service.CodeService;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	private AttendanceService service;
	
	@Autowired
	private CodeService codeService;
	
	// 강사가 강의중인 강좌목록 조회
	@RequestMapping("/attendanceList.do")
	public ModelAndView lectureList(HttpSession session) {
		Teacher teacher = (Teacher)session.getAttribute("login_info");
		Map map = new HashMap<>();
		map.put("list", service.selectTeachLectureService(teacher.getTeacherId()));
		return new ModelAndView("attendance/teacher_lecture_list.tiles", map);
		
	}

	// 강사가 선택한 강좌의 출석부 조회
	@RequestMapping("/attendanceSearch.do")
	public ModelAndView attendanceList(@ModelAttribute Lecture lecture) throws ParseException {
		Map map = new HashMap<>();
		map.put("diffDays", service.realLectureDay(lecture.getLectureStartDate(), lecture.getLectureEndDate(), lecture.getLectureDay()));		// 강의일수
		map.put("studentInfoList", service.selectLectureStudentInfoService(lecture.getLectureNo()));	// 강사가 선택된 강의를 수강 중인 학생 정보
		map.put("lectureNo", lecture.getLectureNo());
		map.put("lecture", lecture);
		map.put("attendanceList", service.attendanceStateService(lecture.getLectureNo()));		// 일자별로 정렬된 학생들의 출결정보 - 출결이 등록된 마지막 날짜까지의....
		map.put("diff", service.dayCount(lecture.getLectureStartDate()));		// 넘어가면 출결 등록 버튼 숨기기(강의시작일 - 현재 날)		
		map.put("dummy", service.comparisonOfDate(lecture.getLectureNo()+""));	// 강의시작일과 오늘 날짜를 비교해서 시작일이 더 크다면 출석 등록 버튼 숨기기
		map.put("dummy1", service.comparisonOfDay(lecture));	// 선택된 요일을 확인하여 버튼 등록 보이게 하기 위한 더미 값
		
		return new ModelAndView("attendance/attendance.tiles", map);	
	}
	
	// 강사가 학생의 출결 등록
	@RequestMapping("/attendanceRegister.do")
	public String attendanceRegister(int day, String [] attendanceState, int lectureNo, @ModelAttribute Lecture lecture, HttpSession session) throws ParseException, UnsupportedEncodingException {
		
		// 강의 날짜, 출결상태, 강의번호, 특정강의를 수강중인 학생 정보를 가진 객체 List
		service.attendanceInsertService(day, attendanceState, lectureNo, service.selectLectureStudentInfoService(lectureNo));	
		
		// 일단 코드 테이블에서 강의번호로 출석 날짜 조회 해 오고 이것이 만약에 null이라면
		if(codeService.selectCode(Integer.toString(lectureNo))==null)  {
			// 코드테이블에 출석등록 날짜
			codeService.insertAttendanceRegisterLastDayService(new Code(Integer.toString(codeService.selectSeq()), new SimpleDateFormat("yyyy/MM/dd").format(new Date()), Integer.toString(lectureNo)));
		}
		else {
			// 코드 테이블에 출결 정보가 존재하므로 최종 출석 등록 날짜 수정
			codeService.updateAttendanceDayService(new Code(Integer.toString(codeService.selectSeq()), new SimpleDateFormat("yyyy/MM/dd").format(new Date()), Integer.toString(lectureNo)));
		}
		return "redirect:/attendance/attendanceRedirect.do?startDate="+lecture.getLectureStartDate()+"&endDate="+lecture.getLectureEndDate()+"&lectureDay="+URLEncoder.encode(lecture.getLectureDay(),"UTF-8")+"&lectureNo="+lecture.getLectureNo();
	}
	
	// 출석등록 redirect 처리!
	@RequestMapping("attendanceRedirect.do")
	public ModelAndView attendanceRegisterRedirect(String startDate, String endDate, String lectureDay , int lectureNo) throws ParseException {
		Map map = new HashMap<>();
		map.put("diffDays", service.realLectureDay(startDate, endDate, lectureDay));			// 강의일수
		map.put("studentInfoList", service.selectLectureStudentInfoService(lectureNo));			// 강사가 선택한 강좌의 학생정보
		map.put("attendanceList", service.attendanceStateService(lectureNo));					// 학생들의 출석 상태
		map.put("lectureNo", lectureNo);														// 강의 번호
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("lectureDay", lectureDay);
		return new ModelAndView("attendance/attendance_detail.tiles", map);
	}
	
	// 일자(하루씩) 출결 수정
	@RequestMapping("/updateAttendance.do")
	public String attendanceModify(int lectureNo, int day, String startDate, String endDate, String lectureDay, @RequestParam String attendanceState) throws UnsupportedEncodingException {
		service.attendanceStateUpdateService(lectureNo, day, attendanceState, service.selectLectureStudentInfoService(lectureNo));	// 수정
		return "redirect:/attendance/attendanceUpdateRedirect.do?startDate="+startDate+"&endDate="+endDate+"&lectureNo="+lectureNo+"&lectureDay="+URLEncoder.encode(lectureDay,"UTF-8");
	}
	
	// 수정 Redirect - 출결 수정 후 조회 & 수정 페이지로 이동
	@RequestMapping("/attendanceUpdateRedirect.do")
	public ModelAndView modifyRedirect(String startDate, String endDate, int lectureNo, String lectureDay) throws ParseException {

		Map map = new HashMap<>();
		map.put("diffDays", service.realLectureDay(startDate, endDate, lectureDay));			// 강의일수
		map.put("studentInfoList", service.selectLectureStudentInfoService(lectureNo));			// 강사가 선택한 강좌의 학생정보
		map.put("attendanceList", service.attendanceStateService(lectureNo));					// 학생들의 출석 상태
		map.put("lectureNo", lectureNo);														// 강의 번호
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		
		return new ModelAndView("attendance/attendance_detail.tiles", map);
	}

	// 학생이 수강중인 강의 정보
	@RequestMapping("/studentLectureInfo.do")
	public ModelAndView studentLectInfo(HttpSession session) {	
		Student student = (Student)session.getAttribute("login_info");
		return new ModelAndView("member/student_lectureInfo.tiles", "studentLectureInfo", service.studentLectureInfoService(student.getStudentId()));
	}
	
	// 자신이 수강 중인 강의의 출결 상태
	@RequestMapping("/attendanceInfo.do")
	public ModelAndView studentAttendance(int lectureNo2, HttpSession session) {
		Student student = (Student)session.getAttribute("login_info");
		return new ModelAndView("attendance/student_attendanceState.tiles",	"attendanceStateList", service.studentAttendanceStateService(lectureNo2, student.getStudentId()));
	}
	
}
	