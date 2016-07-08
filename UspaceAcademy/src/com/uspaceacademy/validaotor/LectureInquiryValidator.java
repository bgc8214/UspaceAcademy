package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.LectureInquiry;

public class LectureInquiryValidator implements Validator{
	@Override
	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(LectureInquiry.class);
	}

	@Override
	public void validate(Object target, Errors errors)
	{
		LectureInquiry lectureInquiryValidate = (LectureInquiry)target;		
		
		if(lectureInquiryValidate.getAdvancedTitle()==null || lectureInquiryValidate.getAdvancedTitle().trim().isEmpty())
		{
			errors.rejectValue("advancedTitle", "","제목를 입력하세요");
		}
		if(lectureInquiryValidate.getAdvancedContent()==null || lectureInquiryValidate.getAdvancedContent().trim().isEmpty())
		{
			errors.rejectValue("advancedContent", "","내용을 입력하세요");
		}		
		if(lectureInquiryValidate.getAdvancedSecret()==null || lectureInquiryValidate.getAdvancedSecret().trim().isEmpty())
		{
			errors.rejectValue("advancedSecret", "","비밀글 여부를 선택하세요.");
		}	
	}
}
