package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.util.Constants;
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
	
	public List selectSalaryList(){
		return session.selectList("memberMapper.selectSalaryList");
	}
	
	public Teacher selectSalaryByTeacherId(String teacherId) {
		return session.selectOne("memberMapper.selectSalaryByTeacherId", teacherId);
	}
	
	public int insertSalary(Teacher teacher)
	{
		return session.insert("memberMapper.insertSalary", teacher);
	}
	
	public int updateSalary(Teacher teacher) {
		return session.update("memberMapper.updateSalary", teacher);
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


	public List selectTeacherBySubject(String teacherSubject) {//
		return session.selectList("memberMapper.selectTeacherBySubject", teacherSubject);
	}
	
	//영주 1
	public List selectCode(String code) { //코드 목록 조회
		return session.selectList("codeMapper.selectCodeName", code);
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
	
	//강사이름으로 강사 목록 조회해옴( where 조건에 like이용)
	public List selectTeacherListByTeacherName(String teacherName){
		return session.selectList("memberMapper.selectTeacherListByTeacherName", teacherName);
	}
	
	// 강사가 자신의 정보 수정 - 아이디와 월급 제외!
	public int updateTeacher(Teacher teacher) {
		return session.update("memberMapper.update_teacher", teacher);
	}
/*	
	// 강사 탈퇴
	public int deleteTeacher(String teacherId) {
		System.out.println("DAO - "+teacherId);
		return session.delete("memberMapper.delete_teacher", teacherId);
	}
	*/
	// 학생 정보 수정
	public int updateStudent(Student student) {
		return session.update("memberMapper.update_student", student);
	}
	
	// 학생 탈퇴를 위해 출석테이블 부터 삭제...
	public int deleteAttendance(String studentId) {
		return session.delete("attendanceMapper.deleteAttendanceForStudent", studentId);
	}
	
	// 학생 탈퇴를 위해 조인 테이블 삭제...
	public int deleteJoinTable(String studentId) {
		return session.delete("lecture.deleteForStudent", studentId);
	}
	// 학생 탈퇴
	public int deleteStudent(String studentId) {
		return session.delete("memberMapper.deleteStudent", studentId);
	}
	// 강사 탈퇴를 위해서 강사가 강의 중인 강좌 조회
	public List selectLectureNo(String teacherId) {
		System.out.println("강사 id로 강의 번호 조회 :  "+session.selectList("lecture.selectByTeacherId", teacherId));
		return session.selectList("lecture.selectByTeacherId", teacherId);
	}
	// 강사가 강의 중인 강좌 정보를 삭제(조인테이블)
	public int deleteByLectureNo(int lectureNo) {
		System.out.println("조인 테이블에서 강사의 강의 정보 삭제");
		return session.delete("lecture.deleteByLectureNo", lectureNo);
	}
	// 강사번호로 강의중인 강좌 삭제
	public int deleteByTeacherId(String teacherId) {
		System.out.println("강의 테이블에서 강사번로 강의들 삭제");
		return session.delete("lecture.deleteByTeacherId", teacherId);
	}
	// 강사 테이블에서 강사 삭제(최종 탈퇴!)
	public int deleteTeacher(String teacherId) {
		System.out.println("강사 테이블에서 강사정보 삭제!");
		return session.delete("memberMapper.deleteTeacher", teacherId);
	}
	
	// 모든 학생 조회(관리자)
	public List studentAllDao(int page) {
		Map map = new HashMap<>();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("memberMapper.studentAll", map);
	}
	
	// 모든 학생 정보 페이징 처리
	public int selectCountents() {
		return session.selectOne("memberMapper.selectStudent");
	}
	
	// 모든 강사 조회(관리자)
	public List teacherAllDao(int page) {
		Map map = new HashMap<>();
		map.put("page", page);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		return session.selectList("memberMapper.teacherAll", map);
	}
	
	// 모든 강사 정보 페이징 처리
	public int selectCountents1() {
		return session.selectOne("memberMapper.selectTeacher");
	}
	
	// 이름으로 학생 찾기
	public List searchStudentByNameDao(String name, int page) {
		Map map = new HashMap<>();
		map.put("name", name);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("page", page);
		return session.selectList("memberMapper.searchStudentByName", map);
	}
	
	// 이름으로 학생 찾기 전체 수
	public int selectStudentCountContents(String name) {
		return session.selectOne("memberMapper.selectStudentByNameCountContents", name);
	}
	
	// 이름으로 강사 찾기
	public List searchTeacherByNameDao(String name, int page) {
		Map map = new HashMap<>();
		map.put("name", name);
		map.put("itemsPerPage", Constants.ITEMS_PER_PAGE);
		map.put("page", page);
		return session.selectList("memberMapper.searchTeacherByName", map);
	}
	
	// 이름으로 강사 찾기 전체 수
	public int selectTeacherCountContents(String name) {
		return session.selectOne("memberMapper.selectTeacherByNameCountContents", name);
	}
	
	// 강사 탈퇴를 위해 강사의 강좌번호 출석부 우선 삭제
	public int deleteAttendanceForTeacher(int lectureNo2) {	
		System.out.println("강의번호로 출석부 삭제 DAO");
		return session.delete("attendanceMapper.deleteByAttendanceForTeacher", lectureNo2);
	}
	
	// 강사 탈퇴를 위해 강사의 강좌번호로 과제 게시판 삭제
	public int deleteTeacherInfoDao(int lectureNo) {
		System.out.println("강의번호로 과제게시판 삭제");
		return session.delete("assignmentMapper.deleteTeacherInfo", lectureNo);
	}
	
}
