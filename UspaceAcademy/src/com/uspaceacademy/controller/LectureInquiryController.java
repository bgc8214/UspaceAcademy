package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.LectureInquiryService;
import com.uspaceacademy.validaotor.LectureInquiryValidator;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.Inquiry;
import com.uspaceacademy.vo.LectureInquiry;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/lectureInquiry")
public class LectureInquiryController {
	private String value;
	
	@Autowired
	private LectureInquiryService service;
	
	//댓글 작성
	@RequestMapping("/insertComment")
	public ModelAndView insertComment(String commentContent, int advancedNo2, int lectureNo2, HttpSession session){
		
		int commentNo = service.increaseCommentNo();
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		String commentType = "강의질문댓글";
		String advancedType = "강의질문";
		
		System.out.println("내용: " + commentContent + "글번호: " + advancedNo2 + "강의번: " + lectureNo2);
		
		Object member = session.getAttribute("memberType");
		
		if(member.equals("student")){
			
			Student advancedId = (Student)session.getAttribute("login_info");
			String commentWriter = advancedId.getStudentId();
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo2);
			int c = service.insertComment(insertComment);
			
			System.out.println("코멘트수: " + c);
			
			List commentList = service.commentList(advancedType, advancedNo2);
			
			System.out.println("코멘트리스트: " + commentList);
			
			LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo2, lectureNo2);
			
			System.out.println("렉쳐: " + lectureInquiry);
			System.out.println("인서트코멘트: " + insertComment);
			
			HashMap map = new HashMap<>();
			map.put("lectureInquiryDetail", lectureInquiry);
			map.put("commentList", commentList);		
			
			return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
		}
		
		else if(member.equals("teacher")){	
			
			Teacher advancedId = (Teacher)session.getAttribute("login_info");
			String commentWriter = advancedId.getTeacherId();		
		
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo2);
			service.insertComment(insertComment);
			List commentList = service.commentList(advancedType, advancedNo2);
			
			System.out.println("코멘트리스트값: " + commentList);
			
			LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo2, lectureNo2);
			
			System.out.println("렉쳐: " + lectureInquiry);
			System.out.println("인서트코멘트: " + insertComment);
			
			HashMap map = new HashMap<>();
			map.put("lectureInquiryDetail", lectureInquiry);
			map.put("commentList", commentList);		
			
			return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
		}
		
		else if(member.equals("administrator")){	
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, "administrator", commentType, advancedNo2);
			service.insertComment(insertComment);			
			List commentList = service.commentList(advancedType, advancedNo2);
			
			LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo2, lectureNo2);
			
			System.out.println("렉쳐: " + lectureInquiry);
			System.out.println("인서트코멘트: " + insertComment);
			
			HashMap map = new HashMap<>();
			map.put("lectureInquiryDetail", lectureInquiry);
			map.put("commentList", commentList);
			
			return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
		}
		
		else
			return new ModelAndView("main.tiles");
	}
	
	//댓글 수정폼
	@RequestMapping("/updateCommentForm")
	public ModelAndView updateCommentForm(int commentNo, int advancedNo2, int lectureNo2){
		String commentType = "강의질문댓글";
		String advancedType = "강의질문";
		
		LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo2, lectureNo2);
		Comment comment = service.selectByCommentNo(commentType, commentNo, advancedNo2);
//		List commentList = service.commentList(advancedType, advancedNo2);
		
		HashMap map = new HashMap<>();
		map.put("lectureInquiryDetail", lectureInquiry);
		map.put("comment", comment);
//		map.put("commentList", commentList);

		return new ModelAndView("lectureInquiry/comment_modify.tiles", map);
	}	
	
	//댓글 수정하기
	@RequestMapping("/updateComment")
	public ModelAndView updateComment(int commentNo, int advancedNo2, int lectureNo2, String commentContent){
		String commentType = "강의질문댓글";
		String advancedType = "강의질문";
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		
		LectureInquiry lecturInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo2, lectureNo2);

		Comment comment = new Comment(commentNo, commentContent, commentDate, commentType, advancedNo2);
		System.out.println(comment);
		
		int updateComment = service.updateComment(comment);	
		System.out.println(updateComment);

		return new ModelAndView("/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret=" + 
		lecturInquiry.getAdvancedSecret() + "&lectureNo2" + lectureNo2);
	}	
	
	//댓글 삭제하기
	@RequestMapping("/deleteComment")
	public ModelAndView deleteComment(int commentNo, int advancedNo2, int lectureNo2){
		String commentType = "강의질문댓글";
		String advancedType = "강의질문";
		
		LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo2, lectureNo2);
		List commentList = service.commentList(advancedType, advancedNo2);
		
		service.deleteComment(commentType, commentNo, advancedNo2);		
		
		HashMap map = new HashMap<>();
		map.put("lectureInquiryDetail", lectureInquiry);
		map.put("commentList", commentList);

		return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
	}	
	
	
	//게시판 전체 목록
	@RequestMapping("/lectureInquiryList")
	public ModelAndView lectureInquiryList(@RequestParam(defaultValue="1") int page, int lectureNo2){	
		Map map = service.selectAllByPaging(page, lectureNo2);
		map.put("page", page);
		map.put("lectureNo2", lectureNo2);
		
		System.out.println("강의번호: " + lectureNo2);
		
		return new ModelAndView("lectureInquiry/lectureInquiry_list.tiles", map);
	}
	
	//전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회. 
	@RequestMapping("/selectByAdvancedNoWithComment")
	public ModelAndView selectByAdvancedNoWithComment(int advancedNo, String advancedSecret, int lectureNo2, HttpSession session){
		String advancedType = "강의질문";
		Object member = session.getAttribute("memberType");	
		
		System.out.println("비밀: " + advancedSecret);
		
		//비밀글일 경우
		if(advancedSecret.equals("true")){
			member = session.getAttribute("memberType");
			
			//학생일 경우
			if(member.equals("student")){
				Student student = (Student)session.getAttribute("login_info");
				LectureInquiry findLectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo, lectureNo2);
				String id = findLectureInquiry.getAdvancedId();
				
				//비밀글에서 글쓴이가 맞는 경우
				if(student.getStudentId().equals(id)){
				
					String commentType = "강의질문댓글";
					
					LectureInquiry LectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo, lectureNo2);
					
					int advancedHit = LectureInquiry.getAdvancedHit();
					LectureInquiry.setAdvancedHit(++advancedHit);				
					service.updateAdvancedHit(LectureInquiry);
					
					List commentList = service.commentList(commentType, advancedNo);
					
					System.out.println("코멘트리스트1: " + commentList);
					
					Map map = new HashMap();
					map.put("lectureInquiryDetail", LectureInquiry);
					map.put("commentList", commentList);
					
					return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
				}
				
				//비밀글에서 글쓴이가 아닌 경우
				else
					return new ModelAndView("/lectureInquiry/lectureInquiryList.do?lectureNo2=" + lectureNo2);	
			}
			
			//강사, 관리자일 경우
			else if(member.equals("teacher")||member.equals("administrator")){
				
				String commentType = "강의질문댓글";
				
				LectureInquiry LectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo, lectureNo2);
				
				int advancedHit = LectureInquiry.getAdvancedHit();
				LectureInquiry.setAdvancedHit(++advancedHit);				
				service.updateAdvancedHit(LectureInquiry);
				
				List commentList = service.commentList(commentType, advancedNo);
				
				System.out.println("advancedNo: " + advancedNo);
				System.out.println("코멘트리스트2: " + commentList);
				
				Map map = new HashMap();
				map.put("lectureInquiryDetail", LectureInquiry);
				map.put("commentList", commentList);
				
				return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);				
			}			
			
			else
				return new ModelAndView("main.tiles");
		}
		
		//비밀글이 아닐 경우
		else if(advancedSecret.equals("false")){
			
			member = session.getAttribute("memberType");			
			
			//학생, 강사, 관리자일 경우
			if(member.equals("student")||member.equals("teacher")||member.equals("administrator")){
				String commentType = "강의질문댓글";
				
				LectureInquiry LectureInquiry = service.selectByAdvancedNoWithComment(advancedType, advancedNo, lectureNo2);
				
				int advancedHit = LectureInquiry.getAdvancedHit();
				LectureInquiry.setAdvancedHit(++advancedHit);				
				service.updateAdvancedHit(LectureInquiry);
				
				List commentList = service.commentList(commentType, advancedNo);
				
				System.out.println("코멘트리스트3: " + commentList);
				
				Map map = new HashMap();
				map.put("lectureInquiryDetail", LectureInquiry);
				map.put("commentList", commentList);
				
				return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
			}
			
			else
				return new ModelAndView("main.tiles");
		}		
		
		else
			return new ModelAndView("main.tiles");
	}
	
	//글 등록폼
	@RequestMapping("/registerLectureInquiryForm.do")
	public ModelAndView registerLectureInquiryForm(int lectureNo2) {
		return new ModelAndView("lectureInquiry/insert_lectureInquiry.tiles", "lectureNo2", lectureNo2);
	}
	
	//글 등록하기
//	String advancedTitle, String advancedContent, String codeName,
	@RequestMapping("/registerLectureInquiry")
	public String registerLectureInquiry(int lectureNo2, LectureInquiry lectureInquiry, String advancedSecret, HttpSession session, BindingResult errors){
		System.out.println("시크릿: " + advancedSecret);
		
		//비밀글인 경우
		if(advancedSecret.equals("true")){
			
			int advancedNo = service.increaseAdvancedNo();
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			Student student = (Student)session.getAttribute("login_info");
			String advancedId = student.getStudentId();
			String advancedType = "강의질문";
			
			lectureInquiry = new LectureInquiry(advancedNo, "true", lectureInquiry.getAdvancedTitle(), lectureInquiry.getAdvancedContent(),
					advancedDate, 0, advancedId, advancedType, lectureNo2);
	
			LectureInquiryValidator validator = new LectureInquiryValidator();
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/lectureInquiry/registerLectureInquiryForm.do?lectureNo2=" + lectureNo2;
			}
			
			service.insertLectureInquiry(lectureInquiry);
			
			return "redirect:/lectureInquiry/selectByAdvancedNo.do?advancedNo=" + lectureInquiry.getAdvancedNo()
			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
		}
		
		//공개글인 경우
		else if(advancedSecret.equals("false")){
			
			int advancedNo = service.increaseAdvancedNo();
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			Student student = (Student)session.getAttribute("login_info");
			String advancedId = student.getStudentId();
			String advancedType = "강의질문";
			
			lectureInquiry = new LectureInquiry(advancedNo, "false", lectureInquiry.getAdvancedTitle(), lectureInquiry.getAdvancedContent(),
					advancedDate, 0, advancedId, advancedType, lectureNo2);
	
			LectureInquiryValidator validator = new LectureInquiryValidator();
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/lectureInquiry/registerLectureInquiryForm.do?lectureNo2=" + lectureNo2;
			}
			
			service.insertLectureInquiry(lectureInquiry);
			
			return "redirect:/lectureInquiry/selectByAdvancedNo.do?advancedNo=" + lectureInquiry.getAdvancedNo()
			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
		}
		else 
			return "main.tiles";		
	}	
	
	//등록 redirect 처리
//	@RequestMapping("/RegisterLectureInquiryRedirect")
//	public ModelAndView lectureInquiryRedirect(int advancedNo, int lectureNo2, @RequestParam(defaultValue="1") int page){
//		service.selectAllByPaging(page, lectureNo2);		
//		
//		System.out.println("re No: " + advancedNo + "lecture: " + lectureNo2);
//
//		return new ModelAndView("/lecutreInquiry/selectByAdvancedNo.do?advancedNo=" + advancedNo + "&lectureNo2=" + lectureNo2);
//	}
	
	//등록했을 때 상세페이지 보기
	@RequestMapping("/selectByAdvancedNo")
	public ModelAndView selectByAdvancedNo(int advancedNo, int lectureNo2){
		String advancedType = "강의질문";
		
		System.out.println("글번호: " + advancedNo + "강의번호: " + lectureNo2);
		
		LectureInquiry lectureInquiry = service.selectByAdvancedNo(advancedType, advancedNo, lectureNo2);
		
		System.out.println(lectureInquiry);
		
//		int advancedHit = lectureInquiry.getAdvancedHit();
//		lectureInquiry.setAdvancedHit(++advancedHit);				
//		service.updateAdvancedHit(lectureInquiry);
		
		return new ModelAndView("lectureInquiry/lectureInquiry_insert_detail.tiles", "lectureInquiryDetail", lectureInquiry);
	}

	//수정폼
	@RequestMapping("/updateLectureInquiryForm")
	public ModelAndView updateLectureInquiryForm(int advancedNo, int lectureNo2){
		String advancedType = "강의질문";
		
		LectureInquiry lectureInquiryDetail = service.selectByAdvancedNo(advancedType, advancedNo, lectureNo2);
		String secret = lectureInquiryDetail.getAdvancedSecret();
		System.out.println("비밀? " + secret);
		
		return new ModelAndView("lectureInquiry/lectureInquiry_modify.tiles", "lectureInquiryDetail", lectureInquiryDetail);
	}
	
	//수정하기
	@RequestMapping("/updateLectureInquiry")
	public ModelAndView updateLectureInquiry(@ModelAttribute("lectureInquiryForm") LectureInquiry updateLectureInquiry){
		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		String advancedType = "강의질문";
		
		LectureInquiry lectureInquiry = new LectureInquiry(updateLectureInquiry.getAdvancedNo(), updateLectureInquiry.getAdvancedSecret(), updateLectureInquiry.getAdvancedTitle(), updateLectureInquiry.getAdvancedContent(), advancedDate, advancedType, updateLectureInquiry.getLectureNo2());
		
		service.updateLectureInquiry(lectureInquiry);
		
		LectureInquiry lectureInquiryDetail = service.selectByAdvancedNoWithComment(advancedType, updateLectureInquiry.getAdvancedNo(), updateLectureInquiry.getLectureNo2());
//		comment 추가해서 map으로 보내라.
		
		
		return new ModelAndView("redirect:/lectureInquiry/selectByAdvancedNo.do?advancedNo=" + lectureInquiryDetail.getAdvancedNo()
				+ "&lectureNo2=" + lectureInquiryDetail.getLectureNo2(), "lectureInquiryDetail", lectureInquiryDetail);
	}
	
	//삭제하기
	@RequestMapping("/deleteLectureInquiry")
	public String deleteLectureInquiry(int advancedNo, int lectureNo2){
		String advancedType = "강의질문";
		
		System.out.println("타입: " + advancedType + "글번호: " + advancedNo + "강의번호: " + lectureNo2);
		
		service.deleteLectureInquiry(advancedType, advancedNo, lectureNo2);

		return "/lectureInquiry/lectureInquiryList.do?lectureNo2=" + lectureNo2;
	}
	
}
