package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.request.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryService;
import com.uspaceacademy.validaotor.InquiryValidator;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.Inquiry;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {
	
	@Autowired
	private InquiryService service;
	
	//댓글 작성
	@RequestMapping("/insertComment")
	public ModelAndView insertComment(String commentContent, int advancedNo2, HttpSession session){
		
		int commentNo = service.increaseCommentNo();
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		String commentType = "1:1문의댓글";
		
		Object member = session.getAttribute("memberType");
		
		if(member.equals("student")){
			
			Student advancedId = (Student)session.getAttribute("login_info");
			String commentWriter = advancedId.getStudentId();
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo2);
			int c = service.insertComment(insertComment);
			
			List commentList = service.commentList(advancedNo2);
			
			Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo2);
			
			HashMap map = new HashMap<>();
			map.put("inquiryDetail", inquiry);
			map.put("commentList", commentList);		
			
			return new ModelAndView("/inquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ inquiry.getAdvancedSecret(), map);
		}
		
		else if(member.equals("teacher")){	
			
			Teacher advancedId = (Teacher)session.getAttribute("login_info");
			String commentWriter = advancedId.getTeacherId();		
		
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo2);
			service.insertComment(insertComment);
			List commentList = service.commentList(advancedNo2);
			
			Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo2);
			
			HashMap map = new HashMap<>();
			map.put("inquiryDetail", inquiry);
			map.put("commentList", commentList);		
			
			return new ModelAndView("/inquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ inquiry.getAdvancedSecret(), map);
		}
		
		else if(member.equals("administrator")){	
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, "administrator", commentType, advancedNo2);
			service.insertComment(insertComment);			
			List commentList = service.commentList(advancedNo2);
			
			Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo2);
			
			System.out.println("렉쳐: " + inquiry);
			System.out.println("인서트코멘트: " + insertComment);
			
			HashMap map = new HashMap<>();
			map.put("inquiryDetail", inquiry);
			map.put("commentList", commentList);
			
			return new ModelAndView("/inquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ inquiry.getAdvancedSecret(), map);
		}
		
		else
			return new ModelAndView("main.tiles");
	}
	
	//댓글 수정폼
	@RequestMapping("/updateCommentForm")
	public ModelAndView updateCommentForm(int commentNo, int advancedNo2){
		
		Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo2);
		Comment comment = service.selectByCommentNo(commentNo, advancedNo2);
		List commentList = service.commentList(advancedNo2);
		
		HashMap map = new HashMap<>();
		map.put("inquiryDetail", inquiry);
		map.put("comment", comment);
		map.put("commentList", commentList);

		return new ModelAndView("inquiry/comment_detail.tiles", map);
	}	
	
	//댓글 수정하기
	@RequestMapping("/updateComment")
	public ModelAndView updateComment(int commentNo, int advancedNo2, String commentContent){
		String commentType = "1:1문의댓글";
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		
		Inquiry lecturInquiry = service.selectByAdvancedNoWithComment(advancedNo2);

		Comment comment = new Comment(commentNo, commentContent, commentDate, commentType, advancedNo2);
		System.out.println(comment);
		
		int updateComment = service.updateComment(comment);	
		System.out.println(updateComment);

		return new ModelAndView("/inquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret=" + 
		lecturInquiry.getAdvancedSecret());
	}	
	
	//댓글 삭제하기
	@RequestMapping("/deleteComment")
	public ModelAndView deleteComment(int commentNo, int advancedNo2){
		
		Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo2);
		List commentList = service.commentList(advancedNo2);
		
		service.deleteComment(commentNo, advancedNo2);		
		
		HashMap map = new HashMap<>();
		map.put("inquiryDetail", inquiry);
		map.put("commentList", commentList);

		return new ModelAndView("/inquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ inquiry.getAdvancedSecret(), map);
	}	
	
	
	//게시판 전체 목록
	@RequestMapping("/inquiryList")
	public ModelAndView inquiryList(@RequestParam(defaultValue="1") int page){	
		Map map = service.selectAllByPaging(page);
		map.put("page", page);
		
		return new ModelAndView("inquiry/inquiry_list.tiles", map);
	}
	
	//전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회. 
	@RequestMapping("/selectByAdvancedNoWithComment")
	public ModelAndView selectByAdvancedNoWithComment(int advancedNo, String advancedSecret, HttpSession session){
		String secret = advancedSecret.split(",")[0];

		Object member = session.getAttribute("memberType");	
		
		System.out.println("타입: " + member);
		
		System.out.println("비밀: " + advancedSecret);
		Inquiry inquiry111 = service.selectByAdvancedNoWithComment(advancedNo);
		System.out.println("inquiry111: " + inquiry111);
		
		//비밀글일 경우
		if(secret.equals("true")){
			
			System.out.println("비밀!!");
			
			//비회원일 경우
			if(member.equals(null)){
				return new ModelAndView("/inquiry/inquiryList.do");
			}
			
			//학생일 경우
			else if(member.equals("student")){
				Student student = (Student)session.getAttribute("login_info");
				Inquiry findInquiry = service.selectByAdvancedNoWithComment(advancedNo);
				String id = findInquiry.getAdvancedId();
				
				//비밀글에서 글쓴이가 맞는 경우
				if(student.getStudentId().equals(id)){
					
					Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo);
					
					int advancedHit = inquiry.getAdvancedHit();
					inquiry.setAdvancedHit(++advancedHit);				
					service.updateAdvancedHit(inquiry);
					
					List commentList = service.commentList(advancedNo);
					
					Map map = new HashMap();
					map.put("inquiryDetail", inquiry);
					map.put("commentList", commentList);
					
					return new ModelAndView("inquiry/inquiry_detail.tiles", map);
				}
				
				//비밀글에서 글쓴이가 아닌 경우
				else
					return new ModelAndView("/inquiry/inquiryList.do");	
			}
			
			//강사, 관리자일 경우
			else if(member.equals("teacher")||member.equals("administrator")){
				
				Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo);
				
				int advancedHit = inquiry.getAdvancedHit();
				inquiry.setAdvancedHit(++advancedHit);				
				service.updateAdvancedHit(inquiry);
				
				List commentList = service.commentList(advancedNo);
				
				System.out.println("advancedNo: " + advancedNo);
				System.out.println("코멘트리스트2: " + commentList);
				
				Map map = new HashMap();
				map.put("inquiryDetail", inquiry);
				map.put("commentList", commentList);
				
				return new ModelAndView("inquiry/inquiry_detail.tiles", map);				
			}			
			
			else
				return new ModelAndView("/inquiry/inquiryList.do");	
		}
		
		//비밀글이 아닐 경우
		if(secret.equals("false")){
			
			System.out.println("찍히나");
			
			member = session.getAttribute("memberType");	
			
			//학생, 강사, 관리자일 경우
			if(member.equals("student")||member.equals("teacher")||member.equals("administrator")){
				
				System.out.println("여기선 찍히나");
				
				Inquiry inquiry = service.selectByAdvancedNoWithComment(advancedNo);
				
				int advancedHit = inquiry.getAdvancedHit();
				inquiry.setAdvancedHit(++advancedHit);				
				service.updateAdvancedHit(inquiry);
				
				List commentList = service.commentList(advancedNo);
				
				System.out.println("코멘트리스트3: " + commentList);
				
				Map map = new HashMap();
				map.put("inquiryDetail", inquiry);
				map.put("commentList", commentList);
				
				return new ModelAndView("inquiry/inquiry_detail.tiles", map);
			}
			
			else
				return new ModelAndView("/inquiry/inquiryList.do");	
		}		
		
		else
			return new ModelAndView("/inquiry/inquiryList.do");	
	}
	
	//글 등록폼
	@RequestMapping("/registerInquiryForm.do")
	public ModelAndView registerInquiryForm() {
		return new ModelAndView("inquiry/insert_inquiry.tiles");
	}
	
	//글 등록하기
//	String advancedTitle, String advancedContent, String codeName,
	@RequestMapping("/registerInquiry")
	public String registerInquiry(@RequestParam(defaultValue="1") int page, Inquiry inquiry, String advancedSecret, HttpSession session, BindingResult errors){
		System.out.println("시크릿: " + advancedSecret);
		
		//비밀글인 경우
		if(advancedSecret.equals("true")){
			
			int advancedNo = service.increaseAdvancedNo();
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			Student student = (Student)session.getAttribute("login_info");
			String advancedId = student.getStudentId();
			String advancedType = "1:1문의";
			
			inquiry = new Inquiry(advancedNo, "true", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(),
					advancedDate, 0, advancedId, advancedType);
	
			InquiryValidator validator = new InquiryValidator();
			validator.validate(inquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors()){
				return "/inquiry/registerInquiryForm.do";
			}
			
			service.insertInquiry(inquiry);
			
//			service.selectAllByPaging(page, lectureNo2);
			
//			return "redirect:/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + lectureInquiry.getAdvancedNo()
//			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
			return "redirect:/inquiry/registerInquiryRedirect.do?advancedNo=" + inquiry.getAdvancedNo();
		}
		
		//공개글인 경우
		else if(advancedSecret.equals("false")){
			
			int advancedNo = service.increaseAdvancedNo();
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			Student student = (Student)session.getAttribute("login_info");
			String advancedId = student.getStudentId();
			String advancedType = "1:1문의";
			
			inquiry = new Inquiry(advancedNo, "false", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(),
					advancedDate, 0, advancedId, advancedType);
	
			InquiryValidator validator = new InquiryValidator();
			validator.validate(inquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/inquiry/registerInquiryForm.do";
			}
			
			service.insertInquiry(inquiry);
			
//			service.selectAllByPaging(page, lectureNo2);
			
//			return "redirect:/lectureInquiry/selectByAdvancedNo.do?advancedNo=" + lectureInquiry.getAdvancedNo()
//			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
//			return "redirect:/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + lectureInquiry.getAdvancedNo() + "&advancedSecret="
//			+ lectureInquiry.getAdvancedSecret() + "&lectureNo2=" + lectureInquiry.getLectureNo2();
			return "redirect:/inquiry/registerInquiryRedirect.do?advancedNo=" + inquiry.getAdvancedNo();
		}
		else 
			return "main.tiles";		
	}	
	
	//등록 redirect 처리
	@RequestMapping("/registerInquiryRedirect")
	public ModelAndView registerInquiryRedirect(int advancedNo, @RequestParam(defaultValue="1") int page){
		Inquiry inquiryDetail = service.selectByAdvancedNoWithComment(advancedNo);	
		
		System.out.println("re No: " + advancedNo);

		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", inquiryDetail);
	}
	
	//등록, 수정했을 때 상세페이지 보기
	@RequestMapping("/selectByAdvancedNo")
	public ModelAndView selectByAdvancedNo(int advancedNo){
		
		Inquiry inquiry = service.selectByAdvancedNo(advancedNo);
		String advancedSecret = inquiry.getAdvancedSecret().split(",")[0];
		
		inquiry.setAdvancedSecret(advancedSecret);
		
		System.out.println("상세: " + inquiry.getAdvancedSecret());
		
		return new ModelAndView("inquiry/inquiry_insert_detail.tiles", "inquiryDetail", inquiry);
	}

	//수정폼
	@RequestMapping("/updateInquiryForm")
	public ModelAndView updateInquiryForm(@RequestParam(defaultValue="1") int page, int advancedNo){
		
		Inquiry inquiryDetail = service.selectByAdvancedNo(advancedNo);
		
		return new ModelAndView("inquiry/inquiry_modify.tiles", "inquiryDetail", inquiryDetail);
	}
	
	//수정하기
	@RequestMapping("/updateInquiry")
	public String updateInquiry(@RequestParam(defaultValue="1") int page, @ModelAttribute("updateInquiry") @Valid Inquiry updateInquiry, BindingResult errors){
		String secret = updateInquiry.getAdvancedSecret();

		//비밀글인 경우
		if(secret.equals("true")){
		
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			String advancedType = "1:1문의";
			
			updateInquiry = new Inquiry(updateInquiry.getAdvancedNo(), "true", updateInquiry.getAdvancedTitle(), updateInquiry.getAdvancedContent(), advancedDate, advancedType);
			
			InquiryValidator validator = new InquiryValidator();
			validator.validate(updateInquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/inquiry/updateInquiryForm.do?advancedNo=" + updateInquiry.getAdvancedNo();
			}
			
			service.updateInquiry(updateInquiry);				
			
			return "redirect:/inquiry/updateInquiryRedirect.do?advancedNo=" + updateInquiry.getAdvancedNo();
		}
		
		//공개글인 경우
		if(secret.equals("false")){
		
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			String advancedType = "1:1문의";
			
			updateInquiry = new Inquiry(updateInquiry.getAdvancedNo(), "false", updateInquiry.getAdvancedTitle(), updateInquiry.getAdvancedContent(), advancedDate, advancedType);
			
			InquiryValidator validator = new InquiryValidator();
			validator.validate(updateInquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/inquiry/updateInquiryForm.do?advancedNo=" + updateInquiry.getAdvancedNo();
			}
			
			service.updateInquiry(updateInquiry);				
			
			return "redirect:/inquiry/updateInquiryRedirect.do?advancedNo=" + updateInquiry.getAdvancedNo();
		}
		else 
			return "main.tiles";
	}
	
	//수정 redirect 처리
	@RequestMapping("/updateInquiryRedirect")
	public ModelAndView updateInquiryRedirect(int advancedNo, @RequestParam(defaultValue="1") int page){
		Inquiry inquiryDetail = service.selectByAdvancedNoWithComment(advancedNo);	
		
		System.out.println("re No: " + advancedNo);

		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", inquiryDetail);
	}
	
	//삭제하기
	@RequestMapping("/deleteInquiry")
	public String deleteInquiry(int advancedNo){
		
		service.deleteInquiry(advancedNo);

		return "/inquiry/inquiryList.do";
	}
	
	
	//키워드로 강의리스트 가져오기
	@RequestMapping("/searchByKeyword.do")
	public ModelAndView searchByKeyword(@RequestParam(defaultValue="1") int page, String searchType, String keyword){
		Map map = new HashMap();
		if(searchType.equals("advancedTitle")){
		   map = service.selectTitleByPaging(page, keyword);
		   map.put("searchType", searchType);
		   map.put("keyword", keyword);

		}else if(searchType.equals("advancedContent")){
			map = service.selectContentByPaging(page, keyword);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
		}
		else{
//			map = service.getLectureList(page);
//			List codeList = service.searchCode("teacherSubject");
//			map.put("page", page);
//			map.put("codeList", codeList); 
//			map.put("searchType", searchType);
//			map.put("keyword", keyword);
			return new ModelAndView("inquiry/inquiry_list.tiles");
		}
		return new ModelAndView("inquiry/inquiry_title_search.tiles", map);
	}
}
