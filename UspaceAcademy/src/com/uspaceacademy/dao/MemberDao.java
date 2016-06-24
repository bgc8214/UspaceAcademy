package com.uspaceacademy.dao;

import java.util.List;

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
	
	
	public int insertStudent(Student student){ //학생 등록
		return session.insert("memberMapper.insertStudent", student);
	}
	
	public int insertTeacher(Teacher teacher) //강사 등록
	{
		return session.insert("memberMapper.insertTeacher", teacher);
	}


	public List selectAllTeacher() {
		return session.selectList("memberMapper.selectAllTeacher");
	}


	public List selectTeacherBySubject(String teacherSubject) {
		return session.selectList("memberMapper.selectTeacherBySubject", teacherSubject);
	}
	
	//영주 1
	public List selectCode(String code) { //코드 목록 조회
		return session.selectList("codeTable.selectCodeName", code);
	}
	
	public Student findStudentById(String id) //아이디로 학생 조회
	{
		return session.selectOne("memberMapper.selectStudentByID", id);
	}
	public Teacher findTeacherById(String id) //아이디로 강사 조회

	{
		return session.selectOne("memberMapper.selectTeacherByID", id);
	}
	public Student findStudentId(Student student) //학생 아이디 찾기
	{
		return session.selectOne("memberMapper.findStudentId", student);
	}
	public Teacher findTeacherId(Teacher teacher) //강사 아이디 찾기
	{
		return session.selectOne("memberMapper.findTeacherId", teacher);
	}
	public Student findStudentPassword(Student student) //학생 비밀번호 찾기
	{
		return session.selectOne("memberMapper.findStudentPassword",student);
	}
	public Teacher findTeacherPassword(Teacher teacher) //강사 비밀번호 찾기
	{
		return session.selectOne("memberMapper.findTeacherPassword",teacher);
	}
	

}
