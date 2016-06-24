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
	
	//영주1
	public List searchCode(String code)
	{
		return dao.selectCode(code);
	}
	
	
	public Student findStudentById(String id) //로그인 때 필요한 아이디 찾기
	{
		return dao.findStudentById(id);
	}
	
	public Student findStudentId(Student student) //학생 아이디 찾기
	{
		return dao.findStudentId(student);
	}
	
	public Student findStudentPassword(Student student) //학생 비밀번호 찾기
	{
		return dao.findStudentPassword(student);
	}
	
	public Teacher findTeacherById(String id) //로그인 때 필요한 아이디 찾기
	{
		return dao.findTeacherById(id);
	}
	
	public Teacher findTeacherId(Teacher teacher) //강사 아이디 찾기
	{
		return dao.findTeacherId(teacher);
	}
	
	public Teacher findTeacherPassword(Teacher teacher) //강사 비밀번호 찾기
	{
		return dao.findTeacherPassword(teacher);
	}
	

	public List getAllTeacher() {
		
		return dao.selectAllTeacher();
	}

	public List getTeacherBySubject(String teacherSubject) {
		return dao.selectTeacherBySubject(teacherSubject);
	}

}
