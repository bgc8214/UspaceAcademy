package com.uspaceacademy.vo;

import java.io.Serializable;
import java.util.List;

public class Student implements Serializable
{
	private String studentId;
	private String studentPassword;
	private String studentName;
	private String studentEmail;
	private String studentPhoneNo;
	private String studentAddress;
	
	public String getStudentId()
	{
		return studentId;
	}
	public void setStudentId(String studentId)
	{
		this.studentId = studentId;
	}
	public String getStudentPassword()
	{
		return studentPassword;
	}
	public void setStudentPassword(String studentPassword)
	{
		this.studentPassword = studentPassword;
	}
	public String getStudentName()
	{
		return studentName;
	}
	public void setStudentName(String studentName)
	{
		this.studentName = studentName;
	}
	public String getStudentEmail()
	{
		return studentEmail;
	}
	public void setStudentEmail(String studentEmail)
	{
		this.studentEmail = studentEmail;
	}
	public String getStudentPhoneNo()
	{
		return studentPhoneNo;
	}
	public void setStudentPhoneNo(String studentPhoneNo)
	{
		this.studentPhoneNo = studentPhoneNo;
	}
	public String getStudentAddress()
	{
		return studentAddress;
	}
	public void setStudentAddress(String studentAddress)
	{
		this.studentAddress = studentAddress;
	}
	
	public Student(String studentId, String studentPassword, String studentName, String studentEmail,
			String studentPhoneNo, String studentAddress)
	{
		super();
		this.studentId = studentId;
		this.studentPassword = studentPassword;
		this.studentName = studentName;
		this.studentEmail = studentEmail;
		this.studentPhoneNo = studentPhoneNo;
		this.studentAddress = studentAddress;
	}
	
	public Student()
	{
		
	}
	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentPassword=" + studentPassword + ", studentName="
				+ studentName + ", studentEmail=" + studentEmail + ", studentPhoneNo=" + studentPhoneNo
				+ ", studentAddress=" + studentAddress + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((studentAddress == null) ? 0 : studentAddress.hashCode());
		result = prime * result + ((studentEmail == null) ? 0 : studentEmail.hashCode());
		result = prime * result + ((studentId == null) ? 0 : studentId.hashCode());
		result = prime * result + ((studentName == null) ? 0 : studentName.hashCode());
		result = prime * result + ((studentPassword == null) ? 0 : studentPassword.hashCode());
		result = prime * result + ((studentPhoneNo == null) ? 0 : studentPhoneNo.hashCode());
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
		Student other = (Student) obj;
		if (studentAddress == null) {
			if (other.studentAddress != null)
				return false;
		} else if (!studentAddress.equals(other.studentAddress))
			return false;
		if (studentEmail == null) {
			if (other.studentEmail != null)
				return false;
		} else if (!studentEmail.equals(other.studentEmail))
			return false;
		if (studentId == null) {
			if (other.studentId != null)
				return false;
		} else if (!studentId.equals(other.studentId))
			return false;
		if (studentName == null) {
			if (other.studentName != null)
				return false;
		} else if (!studentName.equals(other.studentName))
			return false;
		if (studentPassword == null) {
			if (other.studentPassword != null)
				return false;
		} else if (!studentPassword.equals(other.studentPassword))
			return false;
		if (studentPhoneNo == null) {
			if (other.studentPhoneNo != null)
				return false;
		} else if (!studentPhoneNo.equals(other.studentPhoneNo))
			return false;
		return true;
	}
	
	
	
}
