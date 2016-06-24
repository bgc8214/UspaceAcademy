package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.FAQ;

public class FAQValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(FAQ.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		FAQ faq = (FAQ)target;
		
		if(faq.getBasicTitle()==null || faq.getBasicTitle().trim().isEmpty() || faq.getBasicTitle().length()>15) {
			System.out.println(faq.getBasicTitle().length());
			errors.rejectValue("basicTitle", "", "제목은 필수 입력사항(15글자로 제한)");
		}
		if(faq.getBasicContent()==null || faq.getBasicContent().trim().isEmpty()) {
			errors.rejectValue("basicContent", "", "내용은 필수 입력사항입니다");
		}
		
	}
	
}
