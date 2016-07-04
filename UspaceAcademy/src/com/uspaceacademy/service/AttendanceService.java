package com.uspaceacademy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.uspaceacademy.dao.AttendanceDao;
import com.uspaceacademy.vo.Attendance;
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
		return dao.selectLectureStudentInfo(lectureNo3);
	}
	
	// 출석등록
	public int attendanceRegisterService(int day, String[] attendanceState, int lectureNo, List<Student> list) {
		
		for(int i=0; i<attendanceState.length; i++) {
			
			Attendance attendance = new Attendance(attendanceState[i], day, list.get(i).getStudentId(), lectureNo);
			dao.attendanceRegisterDao(attendance);
			
		}
		return 1;
	}
	
	// 한 강좌의 출석상태 조회(강사입장)
	public List attendanceSelectService(int lectureNo) {
		return dao.attendanceSelectDao(lectureNo);
	}
	
	// 출결 조회
	public List attendanceStateService(int lectureNo) {
		int max = dao.maxDay(lectureNo);		// DB에 등록된 출결의 최종날짜
		
		
		ArrayList<List> list = new ArrayList();

		
		for(int i=1; i<=max; i++) {
			 list.add(dao.attendanceStateDao(lectureNo, i));
		}
		return list;

		
	}
	
	// 일차별(하루) 출석 수정
	public int attendanceStateModify(int lectureNo, int lectureDay, String attendanceState, List list) {
		for(int i=0; i<list.size(); i++) {
			dao.attendanceUpdateDao(lectureNo, lectureDay, attendanceState.split(",")[i], ((Student) list.get(i)).getStudentId());
		}
		return 1;
	}
	

}
