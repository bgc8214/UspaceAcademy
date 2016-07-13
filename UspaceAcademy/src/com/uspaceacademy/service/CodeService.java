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
}
