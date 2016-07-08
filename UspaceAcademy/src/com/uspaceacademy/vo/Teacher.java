package com.uspaceacademy.vo;

public class Teacher
{
	private String teacherId;
	private String teacherPassword;
	private String teacherName;
	private String teacherEmail;
	private String teacherPhoneNo;
	private String teacherAddress;
	private String teacherSubject;
	private int teacherSalary;
	
	public Teacher()
	{
		
	}

	public Teacher(String teacherId, String teacherPassword, String teacherName, String teacherEmail,
			String teacherPhoneNo, String teacherAddress, String teacherSubject, int teacherSalary)
	{
		super();
		this.teacherId = teacherId;
		this.teacherPassword = teacherPassword;
		this.teacherName = teacherName;
		this.teacherEmail = teacherEmail;
		this.teacherPhoneNo = teacherPhoneNo;
		this.teacherAddress = teacherAddress;
		this.teacherSubject = teacherSubject;
		this.teacherSalary = teacherSalary;
	}

	public String getTeacherId()
	{
		return teacherId;
	}

	public void setTeacherId(String teacherId)
	{
		this.teacherId = teacherId;
	}

	public String getTeacherPassword()
	{
		return teacherPassword;
	}

	public void setTeacherPassword(String teacherPassword)
	{
		this.teacherPassword = teacherPassword;
	}

	public String getTeacherName()
	{
		return teacherName;
	}

	public void setTeacherName(String teacherName)
	{
		this.teacherName = teacherName;
	}

	public String getTeacherEmail()
	{
		return teacherEmail;
	}

	public void setTeacherEmail(String teacherEmail)
	{
		this.teacherEmail = teacherEmail;
	}

	public String getTeacherPhoneNo()
	{
		return teacherPhoneNo;
	}

	public void setTeacherPhoneNo(String teacherPhoneNo)
	{
		this.teacherPhoneNo = teacherPhoneNo;
	}

	public String getTeacherAddress()
	{
		return teacherAddress;
	}

	public void setTeacherAddress(String teacherAddress)
	{
		this.teacherAddress = teacherAddress;
	}

	public String getTeacherSubject()
	{
		return teacherSubject;
	}

	public void setTeacherSubject(String teacherSubject)
	{
		this.teacherSubject = teacherSubject;
	}

	public int getTeacherSalary()
	{
		return teacherSalary;
	}

	public void setTeacherSalary(int teacherSalary)
	{
		this.teacherSalary = teacherSalary;
	}

	@Override
	public String toString() {
		return "Teacher [teacherId=" + teacherId + ", teacherPassword=" + teacherPassword + ", teacherName="
				+ teacherName + ", teacherEmail=" + teacherEmail + ", teacherPhoneNo=" + teacherPhoneNo
				+ ", teacherAddress=" + teacherAddress + ", teacherSubject=" + teacherSubject + ", teacherSalary="
				+ teacherSalary + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teacherAddress == null) ? 0 : teacherAddress.hashCode());
		result = prime * result + ((teacherEmail == null) ? 0 : teacherEmail.hashCode());
		result = prime * result + ((teacherId == null) ? 0 : teacherId.hashCode());
		result = prime * result + ((teacherName == null) ? 0 : teacherName.hashCode());
		result = prime * result + ((teacherPassword == null) ? 0 : teacherPassword.hashCode());
		result = prime * result + ((teacherPhoneNo == null) ? 0 : teacherPhoneNo.hashCode());
		result = prime * result + teacherSalary;
		result = prime * result + ((teacherSubject == null) ? 0 : teacherSubject.hashCode());
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
		Teacher other = (Teacher) obj;
		if (teacherAddress == null) {
			if (other.teacherAddress != null)
				return false;
		} else if (!teacherAddress.equals(other.teacherAddress))
			return false;
		if (teacherEmail == null) {
			if (other.teacherEmail != null)
				return false;
		} else if (!teacherEmail.equals(other.teacherEmail))
			return false;
		if (teacherId == null) {
			if (other.teacherId != null)
				return false;
		} else if (!teacherId.equals(other.teacherId))
			return false;
		if (teacherName == null) {
			if (other.teacherName != null)
				return false;
		} else if (!teacherName.equals(other.teacherName))
			return false;
		if (teacherPassword == null) {
			if (other.teacherPassword != null)
				return false;
		} else if (!teacherPassword.equals(other.teacherPassword))
			return false;
		if (teacherPhoneNo == null) {
			if (other.teacherPhoneNo != null)
				return false;
		} else if (!teacherPhoneNo.equals(other.teacherPhoneNo))
			return false;
		if (teacherSalary != other.teacherSalary)
			return false;
		if (teacherSubject == null) {
			if (other.teacherSubject != null)
				return false;
		} else if (!teacherSubject.equals(other.teacherSubject))
			return false;
		return true;
	}

}