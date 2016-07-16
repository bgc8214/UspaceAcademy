package com.uspaceacademy.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uspaceacademy.dao.AttendanceDao;
import com.uspaceacademy.dao.CodeDao;
import com.uspaceacademy.vo.Attendance;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.Student;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceDao dao;
	
	@Autowired
	private CodeDao codeDao;
	
	// 강사의 강의 목록 조회
	public List selectTeachLectureService(String teacherId) {
		return dao.selectTeachLectureDao(teacherId);
	}
	
	// 강사가 선택한 강의의 학생 정보 조회를 위한..
	public List selectLectureStudentInfoService(int lectureNo3) {
		return dao.selectLectureStudentInfoDao(lectureNo3);
	}
	
	// 출석등록
	public int attendanceInsertService(int day, String[] attendanceState, int lectureNo, List<Student> list) {
		
		for(int i=0; i<attendanceState.length; i++) {
			
			Attendance attendance = new Attendance(attendanceState[i], day, list.get(i).getStudentId(), lectureNo);
			dao.attendanceInsertDao(attendance);
			
		}
		return 1;
	}
	
	// 한 강좌의 출석상태 조회(강사입장)
	public List attendanceSelectService(int lectureNo) {
		return dao.attendanceSelectDao(lectureNo);
	}
	
	// 출결 조회
	public List attendanceStateService(int lectureNo) {
		int max = dao.lastAttendanceRegisterDayDao(lectureNo);		// DB에 등록된 출결의 최종날짜 -> 추가된 인원에 대한 날짜 구별시에도 사용
		
		List studentList = dao.selectLectureStudentInfoDao(lectureNo);		// 강의 수강 중인 학생 정보 정렬된  ASC
		
		ArrayList<List> list = new ArrayList();

		for(int i=1; i<=max; i++) 
			 list.add(dao.attendanceStateDao(lectureNo, i));

		return list;
	}
	
	// 일차별(하루) 출석 수정
	public int attendanceStateUpdateService(int lectureNo, int lectureDay, String attendanceState, List list) {
		for(int i=0; i<list.size(); i++) 
			dao.attendanceUpdateDao(lectureNo, lectureDay, attendanceState.split(",")[i], ((Student) list.get(i)).getStudentId());
		
		return 1;
	}
	
	// 학생이 수강중인 강의 정보
	public List  studentLectureInfoService(String studentId3) {
		return dao.studentLectureDao(studentId3);
	}
	
	// 학생이 수강중인 강의중 선택한 강의의 출결 상태 조회
	public List studentAttendanceStateService(int lectureNo2, String studentId2) {
		return dao.studentAttendanceStateDao(lectureNo2, studentId2);
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
		return count;
	}
	
	// 강의가 시작되었을 경우에만 출결 등록버튼 띄우기
	public long dayCount(String startDate) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd", java.util.Locale.getDefault());
		
		Date currentDate = new Date();	// 현재 날짜
		String current = new SimpleDateFormat("yyyy/MM/dd").format(currentDate);		// 현재 날짜 변환
		
		Date vDate = dateFormat.parse(current);
		Date sDate = dateFormat.parse(startDate);
		
		return sDate.getTime() - vDate.getTime();		//강의 시작일 - 현재날짜
	}
	
	// 강의시작일과 오늘 날짜 비교해서 시작일이 더 크다면 출석 등록 버튼 숨기기
	public String comparisonOfDate(String value) {
		String dummy = "0";
		
		Code code = codeDao.selectLastDay(value);
		if(code!=null) {
			String today = new SimpleDateFormat("yyyy/MM/dd").format(new Date());		// 현재 날짜 확인
			String registerDay = code.getCodeName();
			
			if(today.equals(registerDay)) 
				dummy = "1";	// 오늘 날짜와 전 날짜가 같은 경우	
		}
		return dummy;
	}
	
	// 강의 요일과 오늘 요일 비교하여 출석등록 버튼 숨기기
	public String comparisonOfDay(Lecture lecture) {
		
		// 출석부에서 강의 하는 요일 뽑아내기
		String value = lecture.getLectureDay();
		ArrayList<String> list = new ArrayList<>();
		
		for(int i=0; i<value.length(); i++) {
			list.add(value.substring(i, (i+1)));
		}
		
		String day = new SimpleDateFormat("E").format(new Date()); // 오늘 요일
		
		String dummy1 = "0";
		for(int i=0; i<list.size(); i++) {
			if(list.get(i).equals(day)) {
				dummy1 = "1";
			}
		}
		return dummy1;
	}
}
