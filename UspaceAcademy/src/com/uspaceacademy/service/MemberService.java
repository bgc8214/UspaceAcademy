package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.synth.SynthSeparatorUI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import com.uspaceacademy.dao.MemberDao;
import com.uspaceacademy.util.PagingBean;
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

	public List getTeacherBySubject(String teacherSubject) {//
		return dao.selectTeacherBySubject(teacherSubject);
	}
	
	// 강사 개인정보 수정
	public int modifyTeacher(Teacher teacher) {
		System.out.println("Service : "+teacher);
		return dao.updateTeacher(teacher);
	}
	
/*	// 강사 탈퇴
	public int removeTeacher(String teacherId) {
		return dao.deleteTeacher(teacherId);
	}*/
	
	// 학생 개인정보 수정
	public int modifyStudent(Student student) {
		return dao.updateStudent(student);
	}
	
	// 학생 탈퇴
	public int deleteStudent(String studentId) {
		dao.deleteAttendance(studentId);
		dao.deleteJoinTable(studentId);
		return dao.deleteStudent(studentId);
	}
	
	/* 순서
	 * 1. lecture 테이블에서 강사가 강의하고 있는 강의 번호 조회
	 * 2. student_lecture_join 테이블에서 강의번호로 삭제
	 * 3. 과제게시판 강의번호로 삭제
	 * 4. lecture 테이블 - 강사 아이디로 삭제
	 * 5. teacher 테이블에서 삭제
	 * -> 탈퇴
	 */
	public int removeTeacher(String teacherId) {
		
		List list = dao.selectLectureNo(teacherId);		// 강사 id로 강의 테이블에서 강의 정보 가져오기
		System.out.println(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(dao.deleteAttendanceForTeacher((int)list.get(i)));	// 강의번호로 출석부 테이블의 강사의 강좌 출석부 정보 삭제
			System.out.println(dao.deleteByLectureNo((int)list.get(i)));	// 학생_강의 테이블 강의번호로 삭제
			System.out.println(dao.deleteTeacherInfoDao((int)list.get(i))); // 과제게시판 강의번호로 삭제
//			dao.deleteByLectureNo((int)list.get(i));
		}
		System.out.println("강의 테이블에서 강사번호로 강의 삭제 Service");
		dao.deleteByTeacherId(teacherId);		// 강의 테이블에서 강사번호로 강의 들 삭제
		System.out.println("강사 테이블에서 삭제 전");
		return dao.deleteTeacher(teacherId);	// 강사 테이블에서 강사번호로 강사 정보 삭제
	}
	
	// 모든 학생 조회(페이징)
	public Map searchAllStudent(int page) {
		Map map = new HashMap<>();
		map.put("studentAllList", dao.studentAllDao(page));
		map.put("paging", new PagingBean(dao.selectCountents(), page));
		return map;
	}
	
	// 모든 강사 조회(페이징)
	public Map searchAllTeacher(int page) {
		Map map = new HashMap<>();
		map.put("teacherAllList", dao.teacherAllDao(page));
		map.put("paging", new PagingBean(dao.selectCountents1(), page));
		return map;
	}
	// 학생이름으로 조회
	public Map searchBystudentNameService(String name, int page) {
		Map map = new HashMap<>();
		map.put("nameList", dao.searchStudentByNameDao(name, page));
		map.put("paging", new PagingBean(dao.selectStudentCountContents(name), page));
		return map;
	}
	
	// 강사이름으로 조회
	public Map searchByteacherNameService(String name, int page) {
		Map map = new HashMap<>();
		map.put("nameList", dao.searchTeacherByNameDao(name, page));
		map.put("paging", new PagingBean(dao.selectTeacherCountContents(name), page));
		return map;
	}
}
