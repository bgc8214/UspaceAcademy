package com.uspaceacademy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uspaceacademy.dao.AttendanceDao;
import com.uspaceacademy.vo.Attendance;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Student;

@Service
public class AttendanceService {
	
	@Autowired
	private AttendanceDao dao;
	
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
	
	// 코드 테이블에서 강의 번호에 맞는 최종 출결 등록 날짜 조회!!!!(출석 날짜 보다 우선)
	public Code selectCode(String codeType) {
		return dao.selectLastDay(codeType);
	}
	
	// 코드 테이블 시퀀스 no
	public int selectSeq() {
		return dao.selectNextNo();
	}
	
	// 출석 등록 날짜 추가
	public int attendanceDayAdd(Code code) {
		System.out.println("Service(출석등록날짜추가) "+code);
		return dao.insertAttendanceRegisterLastDayDao(code);
	}
	
	// 출석 등록 날짜 수정
	public int updateAttendanceDayService(Code code) {
		return dao.updateAttendanceDayDao(code);
	}

}
