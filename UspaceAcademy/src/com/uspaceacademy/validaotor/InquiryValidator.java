package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.Inquiry;

public class InquiryValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(Inquiry.class);
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		Inquiry inquiryValidate = (Inquiry)target;		
		
		if(inquiryValidate.getAdvancedTitle()==null || inquiryValidate.getAdvancedTitle().trim().isEmpty())
		{
			errors.rejectValue("advancedTitle", "","제목를 입력하세요");
		}
		if(inquiryValidate.getAdvancedContent()==null || inquiryValidate.getAdvancedContent().trim().isEmpty())
		{
			errors.rejectValue("advancedContent", "","내용을 입력하세요");
		}	
		if(inquiryValidate.getAdvancedSecret()==null || inquiryValidate.getAdvancedSecret().trim().isEmpty())
		{
			errors.rejectValue("advancedSecret", "","비밀글 여부를 선택하세요.");
		}	
	}
	
	
}
