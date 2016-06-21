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

	public Lecture selectLectureByNo(int lectureNo) {
		return session.selectOne("lecture.selectLectureByNo", lectureNo);
	}
}
