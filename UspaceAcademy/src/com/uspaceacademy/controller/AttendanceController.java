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

import org.apache.catalina.webresources.TomcatURLStreamHandlerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.AttendanceService;
import com.uspaceacademy.vo.Code;
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
	
		// 출석부에서 강의 하는 요일 뽑아내기
		String value = lecture.getLectureDay();
		ArrayList<String> list = new ArrayList<>();
		
		for(int i=0; i<value.length(); i++) {
			list.add(value.substring(i, (i+1)));
		}
		
		// 강의가 시작되었을 때만 등록버튼 띄우기	
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault());
		
		Date currentDate = new Date();	// 현재 날짜
		String current = new SimpleDateFormat("yyyy/MM/dd").format(currentDate);		// 현재 날짜 변환
		
		Date vDate = dateFormat.parse(current);			// 현재 날짜
		Date sDate = dateFormat.parse(lecture.getLectureStartDate());		// 강의 시작일

		long diff = sDate.getTime() - vDate.getTime();	// 강의시작일 - 현재날짜
	//	
		String dummy = "0";
	
		// 코드 테이블에서 강의번호로 최종 출석이 등록된 날짜를 조회
		Code code = service.selectCode(lecture.getLectureNo()+"");	
		if(code!=null) {
			
			String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());		// 현재 날짜 확인
			
			String registerDay = code.getCodeName();
			
			if(today.equals(registerDay)) {
				System.out.println("오늘 날짜와 전 날짜가 같아요");
				dummy = "1";
			}
		}
		else {
			System.out.println("출석 목록 이 없습니다.");
		}
		
		String day = new SimpleDateFormat("E").format(currentDate);	// 오늘 요일
		String dummy1 = "0";
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(day)) {
				dummy1 = "1";
			}
		}	

		// 조회 및 상세페이지로 이동하여 이미 등록된 츨결정보를 보여주고 출석등록이 가능하도록!!!
		List studentInfoList = service.selectLectureStudentInfoService(lecture.getLectureNo());	// 강사가 선택한 강좌의 학생 정보
		List attendanceList = service.attendanceStateService(lecture.getLectureNo());			// 출석 상태 조회		
		
		int diffDays = realLectureDay(lecture.getLectureStartDate(), lecture.getLectureEndDate(), lecture.getLectureDay());	// 강의 일수 구하기
//		System.out.println("강의일수 - "+diffDays);
		
		Map map = new HashMap<>();
		map.put("diffDays", diffDays);		// 강의일수
		map.put("studentInfoList", studentInfoList);	// 강사가 선택된 강의를 수강 중인 학생 정보
		map.put("lectureNo", lecture.getLectureNo());
		map.put("lecture", lecture);
		map.put("attendanceList", attendanceList);		// 일자별로 정렬된 학생들의 출결정보 - 출결이 등록된 마지막 날짜까지의....
		map.put("diff", diff);		// 넘어가면 출결 등록 버튼 숨기기(강의시작일 - 현재 날)		
		map.put("dummy", dummy);	// 강의시작일과 오늘 날짜를 비교해서 시작일이 더 크다면 출석 등록 버튼 숨기기
		map.put("dummy1", dummy1);	// 선택된 요일을 확인하여 버튼 등록 보이게 하기 위한 더미 값
		
		return new ModelAndView("attendance/attendance.tiles", map);	
	}
	
	// 강사가 학생의 출결 등록
	
	@RequestMapping("/attendanceRegister.do")
	public String attendanceRegister(int day, String [] attendanceState, int lectureNo, @ModelAttribute Lecture lecture, HttpSession session) throws ParseException, UnsupportedEncodingException {
		List<Student> list = service.selectLectureStudentInfoService(lectureNo);	// 특정강의를 수강중인 학생 정보를 가진 객체 List
		service.attendanceRegisterService(day, attendanceState, lectureNo, list);	// 강의 날짜, 출결상태, 강의번호, 특정강의를 수강중인 학생 정보를 가진 객체 List
		System.out.println(lecture.getLectureDay());
		// 코드 테이블에서 sequence 번호
		String num = Integer.toString(lectureNo);	// 강의번호 문자로 변환
		String seq = Integer.toString(service.selectSeq());	// 시퀀스 문자로 변환
		// 일단 코드 테이블에서 강의번호로 출석 날짜 조회 해 오고 이것이 만약에 null이라면
		if(service.selectCode(num)==null)  {
			System.out.println("코드테이블에 출석등록 날짜");
			System.out.println(service.attendanceDayAdd(new Code(seq, new SimpleDateFormat("yyyy/MM/dd").format(new Date()), num)));
		}
		else {
			System.out.println("코드 테이블에 출결 정보가 존재하므로 최종 출석 등록 날짜 수정");
			System.out.println(service.updateAttendanceDayService(new Code(seq, new SimpleDateFormat("yyyy/MM/dd").format(new Date()), num)));
		}
			System.out.println("출결등록 Redirect 전..."+lecture.getLectureDay());	
		return "redirect:/attendance/attendanceRedirect.do?startDate="+lecture.getLectureStartDate()+"&endDate="+lecture.getLectureEndDate()+"&lectureDay="+URLEncoder.encode(lecture.getLectureDay(),"UTF-8")+"&lectureNo="+lecture.getLectureNo();
	}
	
	// 출석등록 redirect 처리!
	@RequestMapping("attendanceRedirect.do")
	public ModelAndView attendanceRegisterRedirect(String startDate, String endDate, String lectureDay , int lectureNo) throws ParseException {

		int diffDays = realLectureDay(startDate, endDate, lectureDay);

		List studentInfoList = service.selectLectureStudentInfoService(lectureNo);	// 강사가 선택한 강좌의 학생 정보
		List attendanceList = service.attendanceStateService(lectureNo);			// 출석 상태 조회
		
		Map map = new HashMap<>();
		map.put("diffDays", diffDays);			// 강의일수
		map.put("studentInfoList", studentInfoList);	// 강사가 선택한 강좌의 학생정보
		map.put("attendanceList", attendanceList);		// 학생들의 출석 상태
		map.put("lectureNo", lectureNo);			// 강의 번호
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("lectureDay", lectureDay);
		return new ModelAndView("attendance/attendance_detail.tiles", map);
	}
	
	// 일자(하루씩) 출결 수정
	@RequestMapping("/updateAttendance.do")
	public String modifyAttendance(int lectureNo, int day, String startDate, String endDate, String lectureDay, @RequestParam String attendanceState) throws UnsupportedEncodingException {
		List<Student> list = service.selectLectureStudentInfoService(lectureNo);	// 강의를 수강중인 학생 정보(학생id로 오름차순 정렬된)
		service.attendanceStateModify(lectureNo, day, attendanceState, list);	// 수정
//		System.out.println(lectureDay);
//		System.out.println("리다이렉트 전"+ lectureDay);
		return "redirect:/attendance/attendanceUpdateRedirect.do?startDate="+startDate+"&endDate="+endDate+"&lectureNo="+lectureNo+"&lectureDay="+URLEncoder.encode(lectureDay,"UTF-8");
	}
	
	// 수정 Redirect - 출결 수정 후 조회 & 수정 페이지로 이동
	@RequestMapping("/attendanceUpdateRedirect.do")
	public ModelAndView modifyRedirect(String startDate, String endDate, int lectureNo, String lectureDay) throws ParseException {
		int diffDays = realLectureDay(startDate, endDate, lectureDay);
		System.out.println("리다이렉트"+lectureDay);
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
	
	// 실제 강의 일수를 계산하는 Method
	public int realLectureDay(String startDate, String endDate, String lectureDay) throws ParseException {
		
		// 출석부에서 강의 하는 요일 뽑아내기
		ArrayList<String> list = new ArrayList<>();
		
		for(int i=0; i<lectureDay.length(); i++) {
			list.add(lectureDay.substring(i, (i+1)));
		}

		// String 강의 시작일 & 강의 종료일 -  Date 형태로 변형
		DateFormat dateFormat1 = new SimpleDateFormat("yyyy/MM/dd");
		Date startDate1 = dateFormat1.parse(startDate); // 강의시작일
		Date endDate1 = dateFormat1.parse(endDate);	// 강의종료일

		// Date형을 Calendar 형으로 변환
		Calendar sCal = Calendar.getInstance();		// 강의 시작일
		sCal.setTime(startDate1);

		Calendar eCal = Calendar.getInstance();		// 강의 종료일
		eCal.setTime(endDate1);

		// 총일수에서 실제 강의 일수 구하기
		int intervalDay = (eCal.get(Calendar.DAY_OF_YEAR) - sCal.get(Calendar.DAY_OF_YEAR))+1;	// 총일수 
		System.out.println("총일차수 - "+ intervalDay);
		System.out.println("강의 요일  "+list);
		System.out.println("요일 숫자 표기"+sCal.get(Calendar.DAY_OF_WEEK));
		System.out.println("요일 수 "+list.size());

		String confirmDay="";
		int count=0;	// 요일 count
		for(int i=1; i<=intervalDay; i++) {		// 달력에서 선택된 강의시작일과 종료일로 일차수를 구해서 Loop
			switch(sCal.get(Calendar.DAY_OF_WEEK)) {	// 강의시작일부터 종료일까지의 요일을 check
			case 1:
				confirmDay = "일";
				break;
			case 2:
				confirmDay = "월";
				break;
			case 3:
				confirmDay = "화";
				break;
			case 4:
				confirmDay = "수";
				break;
			case 5:
				confirmDay = "목";
				break;
			case 6:
				confirmDay = "금";
				break;
			case 7:
				confirmDay = "토";
				break;
			}
			for(int j=0; j<list.size(); j++) {	// 강의 요일이 담겨져 있는 list를 돌면서 요일을 비교하여 같으면 count 값 증가
				if(list.get(j).equals(confirmDay)) {
					count++;
				}
			}
			sCal.add(Calendar.DATE, 1);	// 하루 증가
		}
		System.out.println("실 강의 일수 "+ count);
		return count;
	}
}
	