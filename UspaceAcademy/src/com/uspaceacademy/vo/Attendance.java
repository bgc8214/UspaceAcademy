package com.uspaceacademy.vo;

import java.io.Serializable;

public class Attendance implements Serializable {
	private String attendanceState;
	private int lectureDay;
	private String studentId2;
	private int lectureNo;
	
	public Attendance() { }
	
	
	public Attendance(String attendanceState, int lectureDay, String studentId2, int lectureNo) {
		super();
		this.attendanceState = attendanceState;
		this.lectureDay = lectureDay;
		this.studentId2 = studentId2;
		this.lectureNo = lectureNo;
	}

	public String getAttendanceState() {
		return attendanceState;
	}

	public void setAttendanceState(String attendanceState) {
		this.attendanceState = attendanceState;
	}

	public int getlectureDay() {
		return lectureDay;
	}

	public void setlectureDay(int lectureDay) {
		this.lectureDay = lectureDay;
	}

	public String getStudentId2() {
		return studentId2;
	}

	public void setStudentId2(String studentId2) {
		this.studentId2 = studentId2;
	}

	public int getLectureNo() {
		return lectureNo;
	}

	public void setLectureNo(int lectureNo) {
		this.lectureNo = lectureNo;
	}

	@Override
	public String toString() {
		return "Attendance [attendanceState=" + attendanceState + ", lectureDay=" + lectureDay + ", studentId2="
				+ studentId2 + ", lectureNo=" + lectureNo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attendanceState == null) ? 0 : attendanceState.hashCode());
		result = prime * result + lectureNo;
		result = prime * result + lectureDay;
		result = prime * result + ((studentId2 == null) ? 0 : studentId2.hashCode());
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
		Attendance other = (Attendance) obj;
		if (attendanceState == null) {
			if (other.attendanceState != null)
				return false;
		} else if (!attendanceState.equals(other.attendanceState))
			return false;
		if (lectureNo != other.lectureNo)
			return false;
		if (lectureDay != other.lectureDay)
			return false;
		if (studentId2 == null) {
			if (other.studentId2 != null)
				return false;
		} else if (!studentId2.equals(other.studentId2))
			return false;
		return true;
	}
	
}
