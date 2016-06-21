package com.uspaceacademy.vo;

import java.io.Serializable;

public class Lecture implements Serializable{
	
	private int lectureNo;
	private String lectureTitle;
	private String lectureDescription;
	private int lectureStartTime;
	private int lectureEndTime;
	private String lectureStartDate;
	private String lectureEndDate;
	private int lecturePrice;
	private int lectureTotalStudent;
	private int lectureCurrentStudent;
	private String lectureSubject;
	private String teacherId2;//외래키(teacher table)
	
	
	//생성자 3개(null 허용인 컬럼은 각각 생성자를 다 만들어 준다)
	public Lecture(){
		
	}
	
	public Lecture(int lectureNo, String lectureTitle, String lectureDescription, int lectureStartTime,
			int lectureEndTime, String lectureStartDate, String lectureEndDate, int lecturePrice,
			int lectureTotalStudent, int lectureCurrentStudent) {
		this.lectureNo = lectureNo;
		this.lectureTitle = lectureTitle;
		this.lectureDescription = lectureDescription;
		this.lectureStartTime = lectureStartTime;
		this.lectureEndTime = lectureEndTime;
		this.lectureStartDate = lectureStartDate;
		this.lectureEndDate = lectureEndDate;
		this.lecturePrice = lecturePrice;
		this.lectureTotalStudent = lectureTotalStudent;
		this.lectureCurrentStudent = lectureCurrentStudent;
	}

	
	

	public Lecture(int lectureNo, String lectureTitle, String lectureDescription, int lectureStartTime,
			int lectureEndTime, String lectureStartDate, String lectureEndDate, int lecturePrice,
			int lectureTotalStudent, int lectureCurrentStudent, String teacherId2) {
		this.lectureNo = lectureNo;
		this.lectureTitle = lectureTitle;
		this.lectureDescription = lectureDescription;
		this.lectureStartTime = lectureStartTime;
		this.lectureEndTime = lectureEndTime;
		this.lectureStartDate = lectureStartDate;
		this.lectureEndDate = lectureEndDate;
		this.lecturePrice = lecturePrice;
		this.lectureTotalStudent = lectureTotalStudent;
		this.lectureCurrentStudent = lectureCurrentStudent;
		this.teacherId2 = teacherId2;
	}

	public Lecture(int lectureNo, String lectureTitle, String lectureDescription, int lectureStartTime,
			int lectureEndTime, String lectureStartDate, String lectureEndDate, int lecturePrice,
			int lectureTotalStudent, int lectureCurrentStudent, String lectureSubject, String teacherId2) {
		this.lectureNo = lectureNo;
		this.lectureTitle = lectureTitle;
		this.lectureDescription = lectureDescription;
		this.lectureStartTime = lectureStartTime;
		this.lectureEndTime = lectureEndTime;
		this.lectureStartDate = lectureStartDate;
		this.lectureEndDate = lectureEndDate;
		this.lecturePrice = lecturePrice;
		this.lectureTotalStudent = lectureTotalStudent;
		this.lectureCurrentStudent = lectureCurrentStudent;
		this.lectureSubject = lectureSubject;
		this.teacherId2 = teacherId2;
	}

	public int getLectureNo() {
		return lectureNo;
	}

	public void setLectureNo(int lectureNo) {
		this.lectureNo = lectureNo;
	}

	public String getLectureTitle() {
		return lectureTitle;
	}

	public void setLectureTitle(String lectureTitle) {
		this.lectureTitle = lectureTitle;
	}

	public String getLectureDescription() {
		return lectureDescription;
	}

	public void setLectureDescription(String lectureDescription) {
		this.lectureDescription = lectureDescription;
	}

	public int getLectureStartTime() {
		return lectureStartTime;
	}

	public void setLectureStartTime(int lectureStartTime) {
		this.lectureStartTime = lectureStartTime;
	}

	public int getLectureEndTime() {
		return lectureEndTime;
	}

	public void setLectureEndTime(int lectureEndTime) {
		this.lectureEndTime = lectureEndTime;
	}

	public String getLectureStartDate() {
		return lectureStartDate;
	}

	public void setLectureStartDate(String lectureStartDate) {
		this.lectureStartDate = lectureStartDate;
	}

	public String getLectureEndDate() {
		return lectureEndDate;
	}

	public void setLectureEndDate(String lectureEndDate) {
		this.lectureEndDate = lectureEndDate;
	}

	public int getLecturePrice() {
		return lecturePrice;
	}

	public void setLecturePrice(int lecturePrice) {
		this.lecturePrice = lecturePrice;
	}

	public int getLectureTotalStudent() {
		return lectureTotalStudent;
	}

	public void setLectureTotalStudent(int lectureTotalStudent) {
		this.lectureTotalStudent = lectureTotalStudent;
	}

	public int getLectureCurrentStudent() {
		return lectureCurrentStudent;
	}

	public void setLectureCurrentStudent(int lectureCurrentStudent) {
		this.lectureCurrentStudent = lectureCurrentStudent;
	}

	public String getLectureSubject() {
		return lectureSubject;
	}

	public void setLectureSubject(String lectureSubject) {
		this.lectureSubject = lectureSubject;
	}

	public String getTeacherId2() {
		return teacherId2;
	}

	public void setTeacherId2(String teacherId2) {
		this.teacherId2 = teacherId2;
	}

	@Override
	public String toString() {
		return "Lecture [lectureNo=" + lectureNo + ", lectureTitle=" + lectureTitle + ", lectureDescription="
				+ lectureDescription + ", lectureStartTime=" + lectureStartTime + ", lectureEndTime=" + lectureEndTime
				+ ", lectureStartDate=" + lectureStartDate + ", lectureEndDate=" + lectureEndDate + ", lecturePrice="
				+ lecturePrice + ", lectureTotalStudent=" + lectureTotalStudent + ", lectureCurrentStudent="
				+ lectureCurrentStudent + ", lectureSubject=" + lectureSubject + ", teacherId2=" + teacherId2 + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lectureCurrentStudent;
		result = prime * result + ((lectureDescription == null) ? 0 : lectureDescription.hashCode());
		result = prime * result + ((lectureEndDate == null) ? 0 : lectureEndDate.hashCode());
		result = prime * result + lectureEndTime;
		result = prime * result + lectureNo;
		result = prime * result + lecturePrice;
		result = prime * result + ((lectureStartDate == null) ? 0 : lectureStartDate.hashCode());
		result = prime * result + lectureStartTime;
		result = prime * result + ((lectureSubject == null) ? 0 : lectureSubject.hashCode());
		result = prime * result + ((lectureTitle == null) ? 0 : lectureTitle.hashCode());
		result = prime * result + lectureTotalStudent;
		result = prime * result + ((teacherId2 == null) ? 0 : teacherId2.hashCode());
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
		Lecture other = (Lecture) obj;
		if (lectureCurrentStudent != other.lectureCurrentStudent)
			return false;
		if (lectureDescription == null) {
			if (other.lectureDescription != null)
				return false;
		} else if (!lectureDescription.equals(other.lectureDescription))
			return false;
		if (lectureEndDate == null) {
			if (other.lectureEndDate != null)
				return false;
		} else if (!lectureEndDate.equals(other.lectureEndDate))
			return false;
		if (lectureEndTime != other.lectureEndTime)
			return false;
		if (lectureNo != other.lectureNo)
			return false;
		if (lecturePrice != other.lecturePrice)
			return false;
		if (lectureStartDate == null) {
			if (other.lectureStartDate != null)
				return false;
		} else if (!lectureStartDate.equals(other.lectureStartDate))
			return false;
		if (lectureStartTime != other.lectureStartTime)
			return false;
		if (lectureSubject == null) {
			if (other.lectureSubject != null)
				return false;
		} else if (!lectureSubject.equals(other.lectureSubject))
			return false;
		if (lectureTitle == null) {
			if (other.lectureTitle != null)
				return false;
		} else if (!lectureTitle.equals(other.lectureTitle))
			return false;
		if (lectureTotalStudent != other.lectureTotalStudent)
			return false;
		if (teacherId2 == null) {
			if (other.teacherId2 != null)
				return false;
		} else if (!teacherId2.equals(other.teacherId2))
			return false;
		return true;
	}
	
	
	
}
