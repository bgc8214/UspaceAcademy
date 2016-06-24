package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.LectureReview;
// 영주 - 이거 사용안함. 지워도됨.
public class LectureReviewValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Lecture.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		LectureReview LectureReview = (LectureReview) target;
		
		if(LectureReview.getReviewTitle()==null || LectureReview.getReviewTitle().trim().isEmpty()){
			errors.rejectValue("reviewTitle", "", "제목은 필수 입력사항입니다");
		}
		if(LectureReview.getReviewContent()==null || LectureReview.getReviewContent().trim().isEmpty()){
			errors.rejectValue("reviewContent", "", "내용은 필수 입력사항입니다");
		}
	}

}
