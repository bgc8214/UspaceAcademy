package com.uspaceacademy.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class AssignmentLectureDao {

	
	private String namespace="assignmentLectureMapper.";
	
	
	private SqlSessionTemplate session;
	
	@Autowired
	public AssignmentLectureDao(SqlSessionTemplate session){
		this.session = session;
	}

	//----------------------------------------------------------

	
	
	
	
	}
			
	
			