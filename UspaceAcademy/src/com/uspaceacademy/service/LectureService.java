package com.uspaceacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.uspaceacademy.dao.LectureDao;
import com.uspaceacademy.vo.Lecture;

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
	
}
