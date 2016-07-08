/*package com.uspaceacademy.validaotor;

import java.util.Set;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import javax.validation.metadata.BeanDescriptor;

import org.springframework.validation.Errors;

import com.uspaceacademy.vo.Assignment;
import com.uspaceacademy.vo.Inquiry;

public class AssignmentValidator implements Validator{

	public boolean supports(Class<?> clazz)
	{
		return clazz.isAssignableFrom(Inquiry.class);
	}


public void validate(Object target, Errors errors) {
	Assignment assignment = (Assignment) target;
	
	if(assignment.getAssignmentTitle()==null || assignment.getAssignmentTitle().trim().isEmpty()){
		errors.rejectValue("assignmentTitle", "", "제목을 입력하십시오.");
	}
	if(assignment.getAssignmentContent()==null || assignment.getAssignmentContent().trim().isEmpty()){
		errors.rejectValue("assignmentContent", "", "내용을 입력하십시오.");
	}
	if(assignment.getAssignmentDeadline()==null || assignment.getAssignmentDeadline().trim().isEmpty()){
		errors.rejectValue("assignmentDeadline", "", "마감일을 입력하십시오.");
	}
	if(assignment.getAssignmentDeadline()==null ||  assignment.getAssignmentDeadline()!=){
		errors.rejectValue("assignmentDeadline", "", "숫자만 입력하십시오.");

	}
}
*/
