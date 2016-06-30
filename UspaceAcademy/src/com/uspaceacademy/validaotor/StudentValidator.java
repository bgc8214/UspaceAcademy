package com.uspaceacademy.validaotor;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.Student;


/*
 * 	Validator - 요청파라미터 검증하는 컴포넌트
 *  1. implements Validator
 *  2. method overriding
 *   - support() : 검증 의뢰받은 객체가 검증가능한 타입의 객체인지 체크하는 메소드.
 *   - validate() : 검증 로직을 구현하는 클래스
 *   
 *  3. Controller handler에서 validate() 를 호출한다.	
 */
public class StudentValidator implements Validator
{

	@Override
	public boolean supports(Class<?> clazz)
	{
		//Class객체A.isAssignableFrom(Class객체B) -> A하고 B하고 같은 클래스냐? 같으면 true
		return clazz.isAssignableFrom(Student.class);
	}

	@Override
	//매개변수 1 : 검증대상 객체, 2 : 검증실패시 그 오류 정보를 저장할 Errors/BindingResult
	public void validate(Object target, Errors errors)
	{
		//1. id/pwd/name-> 필수입력사항
		//2. id/pwd - 3글자 이상
		//3. 관심사항은 3개이상 선택
		Student student = (Student)target;
		/*if(member.getId()==null || member.getId().trim().isEmpty())
		{
			errors.rejectValue("id", "","ID를 꼭 넣으세요");
		}*/
		
		
		// 이메일 형식 검증
		boolean err = false;
		String regex = "^[_a-z0-9-]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(student.getStudentEmail());
		if(m.matches()) 
			err = true;
		
		if(student.getStudentPassword()==null || student.getStudentPassword().trim().isEmpty())
		{
			errors.rejectValue("studentPassword", "","비밀번호를 입력하세요");
		}
		if(student.getStudentName()==null || student.getStudentName().trim().isEmpty())
		{
			errors.rejectValue("studentName", "","이름을 입력하세요");
		}
		if(student.getStudentId()==null || student.getStudentId().trim().isEmpty())
		{
			errors.rejectValue("studentId", "","아이디를 입력하세요");
		}
		if(student.getStudentId()!=null&&student.getStudentId().length()<5)
		{
			errors.rejectValue("studentId", "","아이디는 다섯 글자 이상 입력하세요");
		}
		if(student.getStudentAddress()==null || student.getStudentAddress().trim().isEmpty())
		{
			errors.rejectValue("studentAddress", "","주소를 입력하세요");
		}
		if(student.getStudentEmail()==null || student.getStudentEmail().trim().isEmpty() || err==false)
		{
			errors.rejectValue("studentEmail", "","이메일을 입력하세요");
		}
		if(student.getStudentPhoneNo()==null || student.getStudentPhoneNo().trim().isEmpty())
		{
			errors.rejectValue("studentPhoneNo", "","휴대폰 번호를 입력하세요");
		}
		
	}

}
