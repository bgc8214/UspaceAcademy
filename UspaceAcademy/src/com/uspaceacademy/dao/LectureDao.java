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
}
