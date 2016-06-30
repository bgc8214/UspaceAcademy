package com.uspaceacademy.vo;

import java.io.Serializable;

public class Assignment implements Serializable{
	//
	private int assignmentNo;					//1.글번호-
	private int assignmentSecret;				//4.비밀여부 
	private String assignmentTitle;				//3.글제목-
	private String assignmentContent;		//    글내용 - 					리스트세부
	private String assignmentDate;			//7.글 등록일-
	private int assignmentHit;					//9.글 조회수-
	private int assignmentPassword;			//    글 비밀번호				리스트세부
	private int replyFamily;						//    답글 묶음
	private int replyStep;							//    답글 순서
	private int replyLevel;						//    답글 단계
	private String assignmentWriter;			//6.글쓴이-
	private String assignmentDeadline;		//8.글 마감일-
	private int lectureNo; //F키 , 강의번호	2.강의명	5.강사명
	
	
	
/*	private int assignmentNo;					//1.글번호	   
	private String assignmentTitle;				//3.글제목
	private int assignmentSecret;				//4.비밀여부 				
	private String assignmentWriter;			//6.글쓴이				
	private String assignmentDate;			//7.글 등록일
	private String assignmentDeadline;		//8.글 마감일
	private int assignmentHit;					//9.글 조회수
	
	private int assignmentPassword;			//    글 비밀번호				리스트세부
	private String assignmentContent;		//    글내용  					리스트세부
	private int reply;								//    답글 그룹
	private int replyStep;							//    답글 레벨
	private int replyLevel;						//    답글 스텝(순서)
					
			
	
	private int lectureNo; //F키 , 강의번호	2.강의명	5.강사명
*/	
	//
	public Assignment(){}

	
	
	
	//생성자 중요!-----------------------------------------------------------------
	
	//다있는 생성자 (13개)
public Assignment(int assignmentNo, int assignmentSecret, String assignmentTitle, String assignmentContent,
		String assignmentDate, int assignmentHit, int assignmentPassword, int replyFamily, int replyStep,
		int replyLevel, String assignmentWriter, String assignmentDeadline, int lectureNo) {
	super();
	this.assignmentNo = assignmentNo;
	this.assignmentSecret = assignmentSecret;
	this.assignmentTitle = assignmentTitle;
	this.assignmentContent = assignmentContent;
	this.assignmentDate = assignmentDate;
	this.assignmentHit = assignmentHit;
	this.assignmentPassword = assignmentPassword;
	this.replyFamily = replyFamily;
	this.replyStep = replyStep;
	this.replyLevel = replyLevel;
	this.assignmentWriter = assignmentWriter;
	this.assignmentDeadline = assignmentDeadline;
	this.lectureNo = lectureNo;
}





//----------------------------------------------------------------------------------------------




//7개
public Assignment(int assignmentNo, String assignmentTitle, String assignmentContent, String assignmentDate,
		int assignmentHit, String assignmentWriter, String assignmentDeadline) {
	super();
	this.assignmentNo = assignmentNo;
	this.assignmentTitle = assignmentTitle;
	this.assignmentContent = assignmentContent;
	this.assignmentDate = assignmentDate;
	this.assignmentHit = assignmentHit;
	this.assignmentWriter = assignmentWriter;
	this.assignmentDeadline = assignmentDeadline;
}

//8개  
public Assignment(int assignmentNo, String assignmentTitle, String assignmentContent, String assignmentDate,
		int assignmentHit, String assignmentWriter, String assignmentDeadline, int lectureNo) {
	super();
	this.assignmentNo = assignmentNo;
	this.assignmentTitle = assignmentTitle;
	this.assignmentContent = assignmentContent;
	this.assignmentDate = assignmentDate;
	this.assignmentHit = assignmentHit;
	this.assignmentWriter = assignmentWriter;
	this.assignmentDeadline = assignmentDeadline;
	this.lectureNo = lectureNo;
}

























//----------------------------------------------------------


@Override
public String toString() {
	return "Assignment [assignmentNo=" + assignmentNo + ", assignmentSecret=" + assignmentSecret + ", assignmentTitle="
			+ assignmentTitle + ", assignmentContent=" + assignmentContent + ", assignmentDate=" + assignmentDate
			+ ", assignmentHit=" + assignmentHit + ", assignmentPassword=" + assignmentPassword + ", replyFamily="
			+ replyFamily + ", replyStep=" + replyStep + ", replyLevel=" + replyLevel + ", assignmentWriter="
			+ assignmentWriter + ", assignmentDeadline=" + assignmentDeadline + ", lectureNo=" + lectureNo + "]";
}













@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((assignmentContent == null) ? 0 : assignmentContent.hashCode());
	result = prime * result + ((assignmentDate == null) ? 0 : assignmentDate.hashCode());
	result = prime * result + ((assignmentDeadline == null) ? 0 : assignmentDeadline.hashCode());
	result = prime * result + assignmentHit;
	result = prime * result + assignmentNo;
	result = prime * result + assignmentPassword;
	result = prime * result + assignmentSecret;
	result = prime * result + ((assignmentTitle == null) ? 0 : assignmentTitle.hashCode());
	result = prime * result + ((assignmentWriter == null) ? 0 : assignmentWriter.hashCode());
	result = prime * result + lectureNo;
	result = prime * result + replyFamily;
	result = prime * result + replyLevel;
	result = prime * result + replyStep;
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
	Assignment other = (Assignment) obj;
	if (assignmentContent == null) {
		if (other.assignmentContent != null)
			return false;
	} else if (!assignmentContent.equals(other.assignmentContent))
		return false;
	if (assignmentDate == null) {
		if (other.assignmentDate != null)
			return false;
	} else if (!assignmentDate.equals(other.assignmentDate))
		return false;
	if (assignmentDeadline == null) {
		if (other.assignmentDeadline != null)
			return false;
	} else if (!assignmentDeadline.equals(other.assignmentDeadline))
		return false;
	if (assignmentHit != other.assignmentHit)
		return false;
	if (assignmentNo != other.assignmentNo)
		return false;
	if (assignmentPassword != other.assignmentPassword)
		return false;
	if (assignmentSecret != other.assignmentSecret)
		return false;
	if (assignmentTitle == null) {
		if (other.assignmentTitle != null)
			return false;
	} else if (!assignmentTitle.equals(other.assignmentTitle))
		return false;
	if (assignmentWriter == null) {
		if (other.assignmentWriter != null)
			return false;
	} else if (!assignmentWriter.equals(other.assignmentWriter))
		return false;
	if (lectureNo != other.lectureNo)
		return false;
	if (replyFamily != other.replyFamily)
		return false;
	if (replyLevel != other.replyLevel)
		return false;
	if (replyStep != other.replyStep)
		return false;
	return true;
}




public int getAssignmentNo() {
	return assignmentNo;
}




public void setAssignmentNo(int assignmentNo) {
	this.assignmentNo = assignmentNo;
}




public int getAssignmentSecret() {
	return assignmentSecret;
}




public void setAssignmentSecret(int assignmentSecret) {
	this.assignmentSecret = assignmentSecret;
}




public String getAssignmentTitle() {
	return assignmentTitle;
}




public void setAssignmentTitle(String assignmentTitle) {
	this.assignmentTitle = assignmentTitle;
}




public String getAssignmentContent() {
	return assignmentContent;
}




public void setAssignmentContent(String assignmentContent) {
	this.assignmentContent = assignmentContent;
}




public String getAssignmentDate() {
	return assignmentDate;
}




public void setAssignmentDate(String assignmentDate) {
	this.assignmentDate = assignmentDate;
}




public int getAssignmentHit() {
	return assignmentHit;
}




public void setAssignmentHit(int assignmentHit) {
	this.assignmentHit = assignmentHit;
}




public int getAssignmentPassword() {
	return assignmentPassword;
}




public void setAssignmentPassword(int assignmentPassword) {
	this.assignmentPassword = assignmentPassword;
}




public int getReplyFamily() {
	return replyFamily;
}




public void setReplyFamily(int replyFamily) {
	this.replyFamily = replyFamily;
}




public int getReplyStep() {
	return replyStep;
}




public void setReplyStep(int replyStep) {
	this.replyStep = replyStep;
}




public int getReplyLevel() {
	return replyLevel;
}




public void setReplyLevel(int replyLevel) {
	this.replyLevel = replyLevel;
}




public String getAssignmentWriter() {
	return assignmentWriter;
}




public void setAssignmentWriter(String assignmentWriter) {
	this.assignmentWriter = assignmentWriter;
}




public String getAssignmentDeadline() {
	return assignmentDeadline;
}




public void setAssignmentDeadline(String assignmentDeadline) {
	this.assignmentDeadline = assignmentDeadline;
}




public int getLectureNo() {
	return lectureNo;
}




public void setLectureNo(int lectureNo) {
	this.lectureNo = lectureNo;
}
}	














