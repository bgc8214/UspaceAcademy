package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.Notice;

/*
 * 	Validator - 요청파라미터 검증하는 컴포넌트
 *  1. implements Validator
 *  2. method overriding
 *   - support() : 검증 의뢰받은 객체가 검증가능한 타입의 객체인지 체크하는 메소드.
 *   - validate() : 검증 로직을 구현하는 클래스
 *   
 *  3. Controller handler에서 validate() 를 호출한다.	
 */
public class NoticeValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		//Class객체A.isAssignableFrom(Class객체B) -> A하고 B하고 같은 클래스냐? 같으면 true
		return clazz.isAssignableFrom(Notice.class);
	}
	
	//매개변수 1 : 검증대상 객체, 2 : 검증실패시 그 오류 정보를 저장할 Errors/BindingResult
	public void validate(Object target, Errors errors) {
		Notice notice = (Notice)target;

		if(notice.getBasicTitle()==null || notice.getBasicTitle().trim().isEmpty()) {
			errors.rejectValue("basicTitle", "", "제목은 필수 입력사항입니다");
		}
		if(notice.getBasicContent()==null || notice.getBasicContent().trim().isEmpty()) {
			errors.rejectValue("basicContent", "", "내용은 필수 입력사항입니다");
		}
	}
}
