package com.uspaceacademy.vo;

import java.io.Serializable;

public class Student implements Serializable
{
	private String student_id;
	private String student_password;
	private String student_name;
	private String student_email;
	private String student_phone_no;
	private String student_address;
	
	public Student()
	{
		
	}

	public String getStudent_id()
	{
		return student_id;
	}

	public void setStudent_id(String student_id)
	{
		this.student_id = student_id;
	}

	public String getStudent_password()
	{
		return student_password;
	}

	public void setStudent_password(String student_password)
	{
		this.student_password = student_password;
	}

	public String getStudent_name()
	{
		return student_name;
	}

	public void setStudent_name(String student_name)
	{
		this.student_name = student_name;
	}

	public String getStudent_email()
	{
		return student_email;
	}

	public void setStudent_email(String student_email)
	{
		this.student_email = student_email;
	}

	public String getStudent_phone_no()
	{
		return student_phone_no;
	}

	public void setStudent_phone_no(String student_phone_no)
	{
		this.student_phone_no = student_phone_no;
	}

	public String getStudent_address()
	{
		return student_address;
	}

	public void setStudent_address(String student_address)
	{
		this.student_address = student_address;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((student_address == null) ? 0 : student_address.hashCode());
		result = prime * result + ((student_email == null) ? 0 : student_email.hashCode());
		result = prime * result + ((student_id == null) ? 0 : student_id.hashCode());
		result = prime * result + ((student_name == null) ? 0 : student_name.hashCode());
		result = prime * result + ((student_password == null) ? 0 : student_password.hashCode());
		result = prime * result + ((student_phone_no == null) ? 0 : student_phone_no.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (student_address == null)
		{
			if (other.student_address != null)
				return false;
		} else if (!student_address.equals(other.student_address))
			return false;
		if (student_email == null)
		{
			if (other.student_email != null)
				return false;
		} else if (!student_email.equals(other.student_email))
			return false;
		if (student_id == null)
		{
			if (other.student_id != null)
				return false;
		} else if (!student_id.equals(other.student_id))
			return false;
		if (student_name == null)
		{
			if (other.student_name != null)
				return false;
		} else if (!student_name.equals(other.student_name))
			return false;
		if (student_password == null)
		{
			if (other.student_password != null)
				return false;
		} else if (!student_password.equals(other.student_password))
			return false;
		if (student_phone_no == null)
		{
			if (other.student_phone_no != null)
				return false;
		} else if (!student_phone_no.equals(other.student_phone_no))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Student [student_id=" + student_id + ", student_password=" + student_password + ", student_name="
				+ student_name + ", student_email=" + student_email + ", student_phone_no=" + student_phone_no
				+ ", student_address=" + student_address + "]";
	}

	public Student(String student_id, String student_password, String student_name, String student_email,
			String student_phone_no, String student_address)
	{
		super();
		this.student_id = student_id;
		this.student_password = student_password;
		this.student_name = student_name;
		this.student_email = student_email;
		this.student_phone_no = student_phone_no;
		this.student_address = student_address;
	}
	
	
}
