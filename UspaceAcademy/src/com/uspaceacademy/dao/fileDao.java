package com.uspaceacademy.dao;

import java.util.HashMap;
import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class fileDao {

	
	private String namespace="fileMapper.";
	
	
	private SqlSessionTemplate session;
	
	@Autowired
	public fileDao(SqlSessionTemplate session){
		this.session = session;
	}

	//----------------------------------------------------------

	public int fileUpload(String title, List upfile){
		HashMap<String,Object>map = new HashMap<>();
		map.put("title", title);
		map.put("upfile",upfile);
		System.out.println("파일 서비스 타이틀-----------------"+title);
		System.out.println("파일 서비스 업파일-----------------"+upfile);
		return session.insert(namespace+"uploadFile", map);
	}
	
	
	
	}
			
	
			