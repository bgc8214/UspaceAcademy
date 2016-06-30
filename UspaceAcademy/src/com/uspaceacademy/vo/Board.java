package com.uspaceacademy.vo;

import java.util.List;

public class Board {

		private String title;
		private String content;
		private List upfile; //upload된 파일들을 저장할 property.  List<MultipartFile>
		/* 
		각기다른 요청파라미터 이름으로 전송된 경우 MultipartFile 타입 변수로 각각 선언
		private MultipartFile upfile1;
		private MultipartFile upfile2;
		private MultipartFile upfile3;
		*/
		
		
		public Board(){}


		public Board(String title, String content, List upfile) {
			super();
			this.title = title;
			this.content = content;
			this.upfile = upfile;
		}


		@Override
		public String toString() {
			return "Board [title=" + title + ", content=" + content + ", upfile=" + upfile + "]";
		}


		public String getTitle() {
			return title;
		}


		public void setTitle(String title) {
			this.title = title;
		}


		public String getContent() {
			return content;
		}


		public void setContent(String content) {
			this.content = content;
		}


		public List getUpfile() {
			return upfile;
		}


		public void setUpfile(List upfile) {
			this.upfile = upfile;
		}


		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((content == null) ? 0 : content.hashCode());
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
			Board other = (Board) obj;
			if (content == null) {
				if (other.content != null)
					return false;
			} else if (!content.equals(other.content))
				return false;
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
		
		
		
}
