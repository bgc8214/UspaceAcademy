package com.uspaceacademy.validaotor;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.uspaceacademy.vo.Lecture;

public class LectureValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return clazz.isAssignableFrom(Lecture.class);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Lecture lecture = (Lecture) target;
		/*if(lecture.getLectureTitle()==null || lecture.getLectureTitle().trim().isEmpty()){
			errors.rejectValue("lectureTitle", "", "강의제목을 입력하십시오.");
		}
		if(lecture.getLectureDescription()==null || lecture.getLectureDescription().trim().isEmpty()){
			errors.rejectValue("lectureTitle", "", "강의설명을 입력하십시오.");
		}*/
	}

}
