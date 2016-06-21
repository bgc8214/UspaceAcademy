package com.uspaceacademy.vo;

public class Teacher
{
	private String teacher_id;
	private String teacher_password;
	private String teacher_name;
	private String teacher_email;
	private String teacher_phone_no;
	private String teacher_address;
	private String teacher_subject;
	private int teacher_salary;
	
	public Teacher()
	{
		
	}

	public String getTeacher_id()
	{
		return teacher_id;
	}

	public void setTeacher_id(String teacher_id)
	{
		this.teacher_id = teacher_id;
	}

	public String getTeacher_password()
	{
		return teacher_password;
	}

	public void setTeacher_password(String teacher_password)
	{
		this.teacher_password = teacher_password;
	}

	public String getTeacher_name()
	{
		return teacher_name;
	}

	public void setTeacher_name(String teacher_name)
	{
		this.teacher_name = teacher_name;
	}

	public String getTeacher_email()
	{
		return teacher_email;
	}

	public void setTeacher_email(String teacher_email)
	{
		this.teacher_email = teacher_email;
	}

	public String getTeacher_phone_no()
	{
		return teacher_phone_no;
	}

	public void setTeacher_phone_no(String teacher_phone_no)
	{
		this.teacher_phone_no = teacher_phone_no;
	}

	public String getTeacher_address()
	{
		return teacher_address;
	}

	public void setTeacher_address(String teacher_address)
	{
		this.teacher_address = teacher_address;
	}

	public String getTeacher_subject()
	{
		return teacher_subject;
	}

	public void setTeacher_subject(String teacher_subject)
	{
		this.teacher_subject = teacher_subject;
	}

	public int getTeacher_salary()
	{
		return teacher_salary;
	}

	public void setTeacher_salary(int teacher_salary)
	{
		this.teacher_salary = teacher_salary;
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((teacher_address == null) ? 0 : teacher_address.hashCode());
		result = prime * result + ((teacher_email == null) ? 0 : teacher_email.hashCode());
		result = prime * result + ((teacher_id == null) ? 0 : teacher_id.hashCode());
		result = prime * result + ((teacher_name == null) ? 0 : teacher_name.hashCode());
		result = prime * result + ((teacher_password == null) ? 0 : teacher_password.hashCode());
		result = prime * result + ((teacher_phone_no == null) ? 0 : teacher_phone_no.hashCode());
		result = prime * result + teacher_salary;
		result = prime * result + ((teacher_subject == null) ? 0 : teacher_subject.hashCode());
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
		Teacher other = (Teacher) obj;
		if (teacher_address == null)
		{
			if (other.teacher_address != null)
				return false;
		} else if (!teacher_address.equals(other.teacher_address))
			return false;
		if (teacher_email == null)
		{
			if (other.teacher_email != null)
				return false;
		} else if (!teacher_email.equals(other.teacher_email))
			return false;
		if (teacher_id == null)
		{
			if (other.teacher_id != null)
				return false;
		} else if (!teacher_id.equals(other.teacher_id))
			return false;
		if (teacher_name == null)
		{
			if (other.teacher_name != null)
				return false;
		} else if (!teacher_name.equals(other.teacher_name))
			return false;
		if (teacher_password == null)
		{
			if (other.teacher_password != null)
				return false;
		} else if (!teacher_password.equals(other.teacher_password))
			return false;
		if (teacher_phone_no == null)
		{
			if (other.teacher_phone_no != null)
				return false;
		} else if (!teacher_phone_no.equals(other.teacher_phone_no))
			return false;
		if (teacher_salary != other.teacher_salary)
			return false;
		if (teacher_subject == null)
		{
			if (other.teacher_subject != null)
				return false;
		} else if (!teacher_subject.equals(other.teacher_subject))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "Teacher [teacher_id=" + teacher_id + ", teacher_password=" + teacher_password + ", teacher_name="
				+ teacher_name + ", teacher_email=" + teacher_email + ", teacher_phone_no=" + teacher_phone_no
				+ ", teacher_address=" + teacher_address + ", teacher_subject=" + teacher_subject + ", teacher_salary="
				+ teacher_salary + "]";
	}
	
	
	
}
