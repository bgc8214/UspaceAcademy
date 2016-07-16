package com.uspaceacademy.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.CodeDao;
import com.uspaceacademy.vo.Code;

@Service
public class CodeService {
	
	private String value;
	
	@Autowired
	private CodeDao dao;
	
	
	// 코드 Name 조회
	public String searchCode(String codeName) {
		List codeList = dao.selectCode(codeName);
		
		for(int i=0; i<codeList.size(); i++) {
			Code c = (Code) codeList.get(i);
			if(codeName.equals(c.getCodeName())) {
				value = c.getCodeName();
			}
		}
		return value;
	}
	
	// 코드 테이블에서 강의 번호에 맞는 최종 출결 등록 날짜 조회!!!(출석 날자 보다 우선)
	public Code selectCode(String codeType) {
		return dao.selectLastDay(codeType);
	}
	
	// 코드 테이블 SequenceNo
	public int selectSeq() {
		return dao.selectNextNo();
	}
	
	// 출석 등록 날짜 추가
	public int insertAttendanceRegisterLastDayService(Code code) {
		return dao.insertAttendanceRegisterLastDayDao(code);
	}
	
	// 출석 등록 날짜 수정
	public int updateAttendanceDayService(Code code) {
		return dao.updateAttendanceDayDao(code);
	}
	
	// 코드 Names 조회
	public List searchCodeNameByType(String codeType) {
		List codeList = dao.selectCode(codeType);
		return codeList;

	}
}
