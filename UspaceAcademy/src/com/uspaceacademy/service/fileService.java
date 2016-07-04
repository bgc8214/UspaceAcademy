package com.uspaceacademy.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uspaceacademy.dao.fileDao;

@Service
public class fileService {

	
		@Autowired
		private fileDao dao;
		
		
		
		//--------------------------------------------------------------------------------------------------------------------
		
		public int fileUpload(String title, List upfile){
			System.out.println("파일 서비스 타이틀-----------------"+title);
			System.out.println("파일 서비스 업파일-----------------"+upfile);
			return dao.fileUpload(title, upfile);
		}
		
		
		
		
	}