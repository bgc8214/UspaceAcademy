package com.uspaceacademy.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Repository
public class MemberDao
{
	private SqlSessionTemplate session;
	@Autowired
	public MemberDao(SqlSessionTemplate session)
	{
		this.session=session;
	}
	
	
	public int insertStudent(Student student){
		return session.insert("memberMapper.insertStudent", student);
	}
	
	public int insertTeacher(Teacher teacher)
	{
		return session.insert("memberMapper.insertTeacher", teacher);
	}
	

}