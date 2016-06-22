package com.uspaceacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uspaceacademy.dao.MemberDao;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Service
public class MemberService
{
	@Autowired
	private MemberDao dao;
	
	public void insertStudent(Student student)
	{
		
		System.out.println(dao.insertStudent(student)+"명 추가");
	}
	
	public void insertTeacher(Teacher teacher)
	{
		System.out.println(dao.insertTeacher(teacher)+"명 추가");
		
	}
	
	public List searchCode(String code)
	{
		return dao.selectCode(code);
	}
	
	public Student findStudentById(String id)
	{
		return dao.findStudentById(id);
	}
	
	public Teacher findTeacherById(String id)
	{
		return dao.findTeacherById(id);
	}

	public List getAllTeacher() {
		
		return dao.selectAllTeacher();
	}

	public List getTeacherBySubject(String teacherSubject) {
		return dao.selectTeacherBySubject(teacherSubject);
	}

}
