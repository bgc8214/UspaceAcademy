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
	
	public List getLectureList(){
		return lectureDao.selectLectureList();
	}

	public Lecture getLectureByNo(int lectureNo) {
		return lectureDao.selectLectureByNo(lectureNo);
	}
	
	//@Transactional(rollbackFor=Exception.class)
	
}
