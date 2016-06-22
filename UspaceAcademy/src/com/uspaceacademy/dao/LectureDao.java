package com.uspaceacademy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.vo.Lecture;

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
	//lecture 삭제
	public int deleteLectureByNo(int lectureNo) {
		return session.delete("lecture.deleteLectureByNo", lectureNo);
	}
}
