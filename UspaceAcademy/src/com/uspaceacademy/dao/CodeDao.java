package com.uspaceacademy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.uspaceacademy.vo.Code;

@Repository
public class CodeDao {
	
	private String namespace = "codeMapper.";
	
	private SqlSessionTemplate session;
	
	@Autowired
	public CodeDao(SqlSessionTemplate session) {
		this.session = session;
	}
	
	// 코드 테이블에서 name 값 가져오기
	public List selectCode(String code) {
		return session.selectList(namespace+"selectCodeName", code);
	}
	
	// 출석날짜 저장하기 전에 우선 강의번호로 출결 등록 날짜 조회
	public Code selectLastDay(String codeType) {
		return session.selectOne(namespace+"selectCodeName", codeType);
	}
	
	// 코드 테이블 sequence Number
	public int selectNextNo() {
		return session.selectOne(namespace+"selectNextNo");
	}
	
	// 코드 테이블에 강의별 출석 등록 최종 날짜 저장
	public int insertAttendanceRegisterLastDayDao(Code code) {
		return session.insert(namespace+"insertAttendanceDay", code);
	}
	
	// 코드 테이블에 출결 등록 날짜 수정
	public int updateAttendanceDayDao(Code code) {
		return session.update(namespace+"updateAttendanceDay", code);
	}
}
