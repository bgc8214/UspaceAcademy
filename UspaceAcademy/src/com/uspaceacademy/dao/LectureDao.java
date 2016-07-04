package com.uspaceacademy.dao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.StudentLectureJoin;

@Repository
public class LectureDao {
	
	@Autowired
	private SqlSessionTemplate session;
	
	//강의목록 전체조회
	public List selectLectureList(){
		return session.selectList("lecture.selectLectureList");
	}
	//no로 강의 조회
	public Lecture selectLectureByNo(int lectureNo) {
		return session.selectOne("lecture.selectLectureByNo", lectureNo);
	}
	//code리스트 조회
	public List selectCode(String codeType){
		return session.selectList("codeTable.selectCodeName",codeType);
	}
	
	//lecture시퀀스 조회
	public int selectLectureSequence() {
		return session.selectOne("lecture.selectLectureSequence");
	}
	
	//lecture 등록
	public int insertLecture(Lecture lecture) {
		return session.insert("lecture.insertLecture", lecture);
	}
	
	//lecture 수정
	public int updateLectureByNo(Lecture lecture) {
		return session.update("lecture.updateLectureByNo", lecture);
	}
	
	// 강의 삭제하기 위해 참조관계 있는 출석테이블 컬럼부터 삭제
	public int deleteAttendance(int lectureNo) {
		return session.delete("attendanceMapper.deleteAttendance", lectureNo);
	}
	
	// 강의 삭제하기 위해 참조관계 있는 학생_강의_조인 테이블 컬럼부터 삭제
	public int deleteStudentLectureJoin(int lectureNo) {
		return session.delete("lecture.deleteJointable", lectureNo);
	}
	
	//lecture 삭제
	public int deleteLectureByNo(int lectureNo) {
		return session.delete("lecture.deleteLectureByNo", lectureNo);
	}
	//학생-강의 조인테이블에 insert(수강신청이나 찜 등록할 때 사용)
	public int insertStudentLectureJoin(String studentId, int lectureNo, String zzim) {
		Map map = new HashMap();
		map.put("studentId", studentId);
		map.put("lectureNo", lectureNo);
		map.put("zzim", zzim);
		return session.insert("lecture.insertStudentLectureJoin", map);
	}
	//아이디와 zzimpOption으로 조인 테이블의 리스트를 조회해옴
	public List selectStudentLectureJoinList(String studentId, String zzimOption) {
		Map map = new HashMap();
		map.put("studentId", studentId);
		map.put("zzimOption", zzimOption);
		return session.selectList("lecture.selectStudentLectureJoinList", map);
	}
	//조인 테이블의 찜 목록을 실제로 결제해줌
	public int updateStudentLectureJoinList(String studentId, int lectureNo, String zzimOption) {
		Map map = new HashMap();
		map.put("studentId", studentId);
		map.put("zzimOption", zzimOption);
		map.put("lectureNo", lectureNo);
		return session.update("lecture.updateStudentLectureJoinList", map);
	}
	//하나의 row만을 조회해옴 studentlecturejoin에서 (찜목록 - 결제목록 중복 체크하기위해쓰임)
	public StudentLectureJoin selectOneStudentLectureJoin(String studentId, int lectureNo, String zzimOption) {
		Map map = new HashMap();
		map.put("studentId", studentId);
		map.put("zzimOption", zzimOption);
		map.put("lectureNo", lectureNo);
		return session.selectOne("lecture.selectOneStudentLectureJoin", map);
	}
	//페이징 처리
	public List selectList(int page) {
		Map map = new HashMap();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		SimpleDateFormat smp = new SimpleDateFormat("yyyy/MM/dd");
		String currentDate  = smp.format(new Date());
		map.put("currentDate", currentDate);
		return session.selectList("lecture.selectListByPaging", map);
	}
	
	//페이징 처리
	public int selectCountContents() {
		return session.selectOne("lecture.selectCountContents");
	}
	//강의명에 매개변수로 넘어온 값이 포함되어있으면 모두 조회
	public List selectLectureListByLectureTitle(String lectureTitle, int page) {
		Map map = new HashMap();
		map.put("lectureTitle", lectureTitle);
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("lecture.selectLectureListByLectureTitle", map);
	}
	//강의명으로 검색한 것 페이징 처리 도와줌
	public int selectCountContentsByLectureTitle(String lectureTitle) {
		return session.selectOne("lecture.selectCountContentsByLectureTitle",lectureTitle);
	}
	
	//강의과목으로 검색한 것 페이징 처리
	public Object selectLectureListByLectureSubject(String lectureSubject, int page) {
		Map map = new HashMap();
		map.put("lectureSubject", lectureSubject);
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("lecture.selectLectureListByLectureSubject", map);
	}
	//강의과목으로 검색한 것 페이징 처리 도와줌
	public int selectCountContentsByLectureSubject(String lectureSubject) {
		return session.selectOne("lecture.selectCountContentsBylectureSubject",lectureSubject);
	}
	//선생ID로 lectureList를 조회함
	public List selectLectureListByTeacherId(String teacherId2, int page) {
		Map map = new HashMap();
		map.put("teacherId2", teacherId2);
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("lecture.selectLectureListByTeacherId", map);
	}
	//선생ID로 검색한 것 페이징 처리 도와줌
	public int selectCountContentsByTeacherId(String teacherId2) {
		return session.selectOne("lecture.selectCountContentsByTeacherId", teacherId2);
	}
	//학생 강의 조인 테이블에서 강의 한개 삭제
	public int deleteLectureFromApplyListByLectureNo(String studentId, int lectureNo) {
		Map map = new HashMap();
		map.put("studentId", studentId);
		map.put("lectureNo", lectureNo);
		return session.delete("lecture.deleteLectureFromApplyListByLectureNo", map);
	}
}

