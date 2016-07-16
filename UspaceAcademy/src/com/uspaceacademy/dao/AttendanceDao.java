package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.vo.Attendance;
import com.uspaceacademy.vo.Code;

@Repository
public class AttendanceDao {

	private String namespace = "attendanceMapper.";
	
	private SqlSessionTemplate session;
	
	@Autowired
	public AttendanceDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	// 강사의 강의 목록 가져오기
	public List selectTeachLectureDao(String teacherId) {
		return session.selectList(namespace+"selectTeachLecture", teacherId);
	}
	
	// 강사가 선택한 강좌를 수강하는 학생의 정보를 가져오기 위해서....
	public List selectLectureStudentInfoDao(int lectureNo3) {
		return session.selectList(namespace+"selectLectureStudentInfo", lectureNo3);
	}
	
	// 강사가 특정 강좌의 일차(별) 출석 등록
	public int attendanceInsertDao(Attendance attendance) {
		return session.insert(namespace+"attendanceRegister", attendance);
	}
	
	// 한 강좌의 출결 상태 조회(이 방법으로는 jsp에서 해결이 안됨.!)
	public List attendanceSelectDao(int lectureNo) {
		List list =  session.selectList(namespace+"attendanceSelect", lectureNo);
		return list;
	}
	
	// 한 강좌의 학생들의 출결정보가 등록된 최종 날짜
	public int lastAttendanceRegisterDayDao(int lectureNo) {
		return session.selectOne(namespace+"lastAttendanceRegisterDay", lectureNo);
	}
	
	// 일차(1) 출석 상태 조회
	public List attendanceStateDao(int lectureNo, int lectureDay) {
		Map map =  new HashMap<>();
		map.put("lectureNo", lectureNo);
		map.put("lectureDay", lectureDay);
		return session.selectList(namespace+"lectureAttendanceState", map);
	}
	
	// 일차(하루씩) 출결상태 수정 - 수강중인 학생들...
	public int attendanceUpdateDao(int lectureNo, int lectureDay, String attendanceState, String studentId2){
		Map map = new HashMap<>();
		map.put("lectureNo", lectureNo);
		map.put("lectureDay", lectureDay);
		map.put("attendanceState", attendanceState);
		map.put("studentId2", studentId2);
		return session.update(namespace+"attendanceUpdate", map);
	}
	
	// 학생이 수강중인 강의 조회
	public List studentLectureDao(String studentId3) {
		return session.selectList(namespace+"studentLectureInfo", studentId3);
	}
	
	// 학생이 수강중인 강의중 선택한 강의의 출결 상태 조회
	public List studentAttendanceStateDao(int lectureNo2, String studentId2) {
		Map map = new HashMap<>();
		map.put("lectureNo2", lectureNo2);
		map.put("studentId2", studentId2);
		return session.selectList(namespace+"studentAttendance", map);
	}
}
