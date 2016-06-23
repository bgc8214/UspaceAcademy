package com.uspaceacademy.vo;

import java.io.Serializable;
public class StudentLectureJoin implements Serializable{
	private String studentId3;
	private int lectureNo3;
	private String zzimOption;
	public StudentLectureJoin() {
		super();
	}
	public StudentLectureJoin(String studentId3, int lectureNo3, String zzimOption) {
		super();
		this.studentId3 = studentId3;
		this.lectureNo3 = lectureNo3;
		this.zzimOption = zzimOption;
	}
	public String getStudentId3() {
		return studentId3;
	}
	public void setStudentId3(String studentId3) {
		this.studentId3 = studentId3;
	}
	public int getLectureNo3() {
		return lectureNo3;
	}
	public void setLectureNo3(int lectureNo3) {
		this.lectureNo3 = lectureNo3;
	}
	public String getZzimOption() {
		return zzimOption;
	}
	public void setZzimOption(String zzimOption) {
		this.zzimOption = zzimOption;
	}
	@Override
	public String toString() {
		return "StudentLectureJoin [studentId3=" + studentId3 + ", lectureNo3=" + lectureNo3 + ", zzimOption="
				+ zzimOption + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lectureNo3;
		result = prime * result + ((studentId3 == null) ? 0 : studentId3.hashCode());
		result = prime * result + ((zzimOption == null) ? 0 : zzimOption.hashCode());
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
		StudentLectureJoin other = (StudentLectureJoin) obj;
		if (lectureNo3 != other.lectureNo3)
			return false;
		if (studentId3 == null) {
			if (other.studentId3 != null)
				return false;
		} else if (!studentId3.equals(other.studentId3))
			return false;
		if (zzimOption == null) {
			if (other.zzimOption != null)
				return false;
		} else if (!zzimOption.equals(other.zzimOption))
			return false;
		return true;
	}
	
	
}
