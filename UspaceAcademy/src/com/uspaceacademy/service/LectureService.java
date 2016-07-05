package com.uspaceacademy.service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uspaceacademy.dao.LectureDao;
import com.uspaceacademy.dao.MemberDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.StudentLectureJoin;
import com.uspaceacademy.vo.Teacher;

@Service
@Transactional	
public class LectureService {
	
	@Autowired
	private LectureDao lectureDao;
	@Autowired
	private MemberDao memberDao;
	
	//모든 강의 목록 조회
	public List getLectureList(){
		return lectureDao.selectLectureList();
	}
	//강의no로 강의 조회
	public Lecture getLectureByNo(int lectureNo) {
		return lectureDao.selectLectureByNo(lectureNo);
	}
	//코드리스트 조회
	public List searchCode(String codeType){
		return lectureDao.selectCode(codeType);
	}
	
	//강의 등록하기 위한 서비스
	@Transactional(rollbackFor=Exception.class)
	public int registerLecture(Lecture lecture) {
		//시퀀스로부터 강의번호를 조회해온다
		int lectureNo = lectureDao.selectLectureSequence();
		lecture.setLectureNo(lectureNo);
		return lectureDao.insertLecture(lecture);
	}
	//강의 수정하기 위한 서비스
	public int modifyLectureByNo(Lecture lecture) {
		return lectureDao.updateLectureByNo(lecture);
	}
	//강의 삭제하기 위한 서비스(출석, 학생_강의_조인테이블, 강의)
	public int removeLectureByNo(int lectureNo) {
		lectureDao.deleteAttendance(lectureNo);				// 참조관계 있는 출석 컬럼부터 삭제
		lectureDao.deleteStudentLectureJoin(lectureNo);		// 참조관계 있는 학생_강의_조인테이블 컬럼 삭제
		return lectureDao.deleteLectureByNo(lectureNo);		// 마지막으로 강의 테이블에서 삭제
	}
	//강의 결제하기 위한 서비스(강의 현재인원 +1)
	@Transactional(rollbackFor=Exception.class)
	public int chargeLecture(String studentId, int lectureNo, String zzim) {
		Lecture lecture = lectureDao.selectLectureByNo(lectureNo);
		//현재인원 +1하고
		lecture.setLectureCurrentStudent(lecture.getLectureCurrentStudent()+1);
		//새로 +1한 것을 업데이트
		lectureDao.updateLectureByNo(lecture);
		return lectureDao.insertStudentLectureJoin(studentId, lectureNo, zzim);
	}
	//로그인한 수강생이 등록한 찜 목록을 보여주기 위한 서비스
	public List getStudentLectureJoinList(String studentId, String zzimOption) {
		return lectureDao.selectStudentLectureJoinList(studentId, zzimOption);
	}
	//찜 목록에서 선택된 찜 리스트를 결제하기 위한 서비스(flag를 변경해줌)
	@Transactional(rollbackFor=Exception.class)
	public int chargeFromZzimList(String studentId, int lectureNo, String zzimOption) throws Exception {
		Lecture lecture = lectureDao.selectLectureByNo(lectureNo);
		
		if(lecture.getLectureCurrentStudent()>=lecture.getLectureTotalStudent()){
			String str = URLEncoder.encode("선택하신 강의 중 수강생이 가득 찬 강의가 있습니다.","UTF-8");
			throw new Exception(str); 
		}
		
		lecture.setLectureCurrentStudent(lecture.getLectureCurrentStudent()+1);
		//업데이트 해줌
		lectureDao.updateLectureByNo(lecture);
		return lectureDao.updateStudentLectureJoinList(studentId, lectureNo, zzimOption);
	}
	//조인테이블에서 하나의 값을 받아오기 위한 테이블
	public StudentLectureJoin getOneStudentLectureJoin(String studentId, int lectureNo, String zzimOption) {
		return lectureDao.selectOneStudentLectureJoin(studentId, lectureNo, zzimOption);
	}
	//강의 목록 페이징 처리
	public Map getLectureList(int page) {
		Map map = new HashMap();
		map.put("lectureList", lectureDao.selectList(page));
		map.put("paging", new PagingBean(lectureDao.selectCountContents(), page));
		return map;
	}
	//강의명으로 강의리스트에서 검색해서 강의명이 중간에 들어가는 강의들을 모두 list로 조회해옴
	public Map getLectureByTitle(String lectureTitle, int page) {
		Map map = new HashMap();
		map.put("lectureList", lectureDao.selectLectureListByLectureTitle(lectureTitle, page));
		map.put("paging", new PagingBean(lectureDao.selectCountContentsByLectureTitle(lectureTitle), page));
		return map;
	}
	//강의과목으로 강의리스트에서 검색
	public Map getLectureByLectureSubject(String lectureSubject, int page) {
		Map map = new HashMap();
		map.put("lectureList", lectureDao.selectLectureListByLectureSubject(lectureSubject, page));
		map.put("paging", new PagingBean(lectureDao.selectCountContentsByLectureSubject(lectureSubject), page));
		return map;
	}
	//강의강사명으로 강의리스트에서 검색
	@Transactional(rollbackFor=Exception.class)
	public Map getLectureByTeacherName(String teacherName, int page) {
		List teacherList = memberDao.selectTeacherListByTeacherName(teacherName);//강사리스트롤 조회해옴
		List lectureList = new ArrayList();//페이징해서 넣을 강사 리스트 생성
		int totalItems = 0;
		for(Object o : teacherList){
			//반복문 돌리면서 teacher객체에서 ID를 뽑아서 그 강사ID로 조회한 강의를 강의목록에 추가해줌 
			Teacher teacher = (Teacher)o;
			List semiLectureList = lectureDao.selectLectureListByTeacherId(teacher.getTeacherId(), page);//리스트 리턴
			
			for(Object obj : semiLectureList){
				lectureList.add((Lecture)obj);
			}
			
			//totalItems+=lectureDao.selectCountContentsByTeacherId(teacher.getTeacherId());
		}
		
		Map map = new HashMap();
		map.put("lectureList", lectureList);
		map.put("paging", new PagingBean(lectureList.size(), page));
		return map;
	}
	//학생 강의 테이블에 pk2개로 강의 한개 삭제(리스트에서)
	@Transactional(rollbackFor=Exception.class)
	public int removeLectureFromApplyListByLectureNo(String studentId, int lectureNo) {
		Lecture lecture = lectureDao.selectLectureByNo(lectureNo);
		lecture.setLectureCurrentStudent(lecture.getLectureCurrentStudent()-1);
		lectureDao.updateLectureByNo(lecture);
		return lectureDao.deleteLectureFromApplyListByLectureNo(studentId, lectureNo);
	}
	
	
	public List selectAllByTeacherId(String teacherId) {		
		return lectureDao.selectAllByTeacherId(teacherId);
	}
	
}
