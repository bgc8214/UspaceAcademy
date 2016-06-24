package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uspaceacademy.dao.LectureDao;
import com.uspaceacademy.util.PagingBean;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.StudentLectureJoin;

@Service
@Transactional	
public class LectureService {
	
	@Autowired
	private LectureDao lectureDao;
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
	//강의 삭제하기 위한 서비스
	public int removeLectureByNo(int lectureNo) {
		return lectureDao.deleteLectureByNo(lectureNo);
	}
	//강의 결제하기 위한 서비스
	public int chargeLecture(String studentId, int lectureNo, String zzim) {
		return lectureDao.insertStudentLectureJoin(studentId, lectureNo, zzim);
	}
	//로그인한 수강생이 등록한 찜 목록을 보여주기 위한 서비스
	public List getStudentLectureJoinList(String studentId, String zzimOption) {
		return lectureDao.selectStudentLectureJoinList(studentId, zzimOption);
	}
	//찜 목록에서 선택된 찜 리스트를 결제하기 위한 서비스(flag를 변경해줌)
	public int chargeFromZzimList(String studentId, int lectureNo, String zzimOption) {
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
	
}
