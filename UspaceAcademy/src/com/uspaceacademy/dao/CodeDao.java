package com.uspaceacademy.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
