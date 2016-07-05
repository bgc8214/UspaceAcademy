package com.uspaceacademy.vo;
//버려 영주.
import java.util.List;

public class FileBoard {

		private String title; //private String fname;
		//private String content;
		private List upfile; //upload된 파일들을 저장할 property.  List<MultipartFile> //private MultipartFile uploadFile;
		 
//		각기다른 요청파라미터 이름으로 전송된 경우 MultipartFile 타입 변수로 각각 선언
//		private MultipartFile upfile1;
//		private MultipartFile upfile2;
//		private MultipartFile upfile3;
		
		
		
		public FileBoard(){}


		@Override
		public String toString() {
			return "FileBoard [title=" + title + ", upfile=" + upfile + "]";
		}


		public FileBoard(String title, List upfile) {
			super();
			this.title = title;
			this.upfile = upfile;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			result = prime * result + ((upfile == null) ? 0 : upfile.hashCode());
			return result;
		}


		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			FileBoard other = (FileBoard) obj;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			if (upfile == null) {
				if (other.upfile != null)
					return false;
			} else if (!upfile.equals(other.upfile))
				return false;
			return true;
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public List getUpfile() {
			return upfile;
		}


		public void setUpfile(List upfile) {
			this.upfile = upfile;
		}

		
		}