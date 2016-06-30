package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.Secret;

public class secretValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		
		
		return clazz.isAssignableFrom(Secret.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Secret secretValidate = (Secret)target;		
		
		if(secretValidate.getSecret()==null || secretValidate.getSecret().trim().isEmpty())
		{
			errors.rejectValue("secret", "","비밀글 여부를 선택하세요.");
		}
	}
	
}
