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
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.LectureInquiryService;
import com.uspaceacademy.validaotor.LectureInquiryValidator;
import com.uspaceacademy.vo.Comment;
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
		
		Object member = session.getAttribute("memberType");
		
		if(member.equals("student")){
			
			Student advancedId = (Student)session.getAttribute("login_info");
			String commentWriter = advancedId.getStudentId();
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo2);
			int c = service.insertComment(insertComment);
			
			List commentList = service.commentList(advancedNo2);
			
			LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo2, lectureNo2);
			
			HashMap map = new HashMap<>();
			map.put("lectureInquiryDetail", lectureInquiry);
			map.put("commentList", commentList);		
			
			return new ModelAndView("/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ lectureInquiry.getAdvancedSecret() + "&lectureNo2=" + lectureNo2, map);
		}
		
		else if(member.equals("teacher")){	
			
			Teacher advancedId = (Teacher)session.getAttribute("login_info");
			String commentWriter = advancedId.getTeacherId();		
		
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo2);
			service.insertComment(insertComment);
			List commentList = service.commentList(advancedNo2);
			
			LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo2, lectureNo2);
			
			HashMap map = new HashMap<>();
			map.put("lectureInquiryDetail", lectureInquiry);
			map.put("commentList", commentList);		
			
//			return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
			return new ModelAndView("/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ lectureInquiry.getAdvancedSecret() + "&lectureNo2=" + lectureNo2, map);
		}
		
		else if(member.equals("administrator")){	
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, "administrator", commentType, advancedNo2);
			service.insertComment(insertComment);			
			List commentList = service.commentList(advancedNo2);
			
			LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo2, lectureNo2);
			
			System.out.println("렉쳐: " + lectureInquiry);
			System.out.println("인서트코멘트: " + insertComment);
			
			HashMap map = new HashMap<>();
			map.put("lectureInquiryDetail", lectureInquiry);
			map.put("commentList", commentList);
			
			return new ModelAndView("/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ lectureInquiry.getAdvancedSecret() + "&lectureNo2=" + lectureNo2, map);
		}
		
		else
			return new ModelAndView("main.tiles");
	}
	
	//댓글 수정폼
	@RequestMapping("/updateCommentForm")
	public ModelAndView updateCommentForm(int commentNo, int advancedNo2, int lectureNo2){
		
		LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo2, lectureNo2);
		Comment comment = service.selectByCommentNo(commentNo, advancedNo2);
		List commentList = service.commentList(advancedNo2);
		
		HashMap map = new HashMap<>();
		map.put("lectureInquiryDetail", lectureInquiry);
		map.put("comment", comment);
		map.put("commentList", commentList);

		return new ModelAndView("lectureInquiry/comment_modify.tiles", map);
	}	
	
	//댓글 수정하기
	@RequestMapping("/updateComment")
	public ModelAndView updateComment(int commentNo, int advancedNo2, int lectureNo2, String commentContent){
		String commentType = "강의질문댓글";
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		
		LectureInquiry lecturInquiry = service.selectByAdvancedNoWithComment(advancedNo2, lectureNo2);

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
		
		LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo2, lectureNo2);
		List commentList = service.commentList(advancedNo2);
		
		service.deleteComment(commentNo, advancedNo2);		
		
		HashMap map = new HashMap<>();
		map.put("lectureInquiryDetail", lectureInquiry);
		map.put("commentList", commentList);

		return new ModelAndView("/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + advancedNo2 + "&advancedSecret="
			+ lectureInquiry.getAdvancedSecret() + "&lectureNo2=" + lectureNo2, map);
	}	
	
	
	//게시판 전체 목록
	@RequestMapping("/lectureInquiryList")
	public ModelAndView lectureInquiryList(@RequestParam(defaultValue="1") int page, int lectureNo2, String lectureTitle){	
		Map map = service.selectAllByPaging(page, lectureNo2);
		map.put("page", page);
		map.put("lectureNo2", lectureNo2);
		map.put("lectureTitle", lectureTitle);
		
		return new ModelAndView("lectureInquiry/lectureInquiry_list.tiles", map);
	}
	
	//전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회. 
	@RequestMapping("/selectByAdvancedNoWithComment")
	public ModelAndView selectByAdvancedNoWithComment(int advancedNo, String advancedSecret, int lectureNo2, HttpSession session){
		String secret = advancedSecret.split(",")[0];

		Object member = session.getAttribute("memberType");	
		
		System.out.println("비밀: " + advancedSecret);
		
		//비밀글일 경우
		if(secret.equals("true")){
			member = session.getAttribute("memberType");
			
			//학생일 경우
			if(member.equals("student")){
				Student student = (Student)session.getAttribute("login_info");
				LectureInquiry findLectureInquiry = service.selectByAdvancedNoWithComment(advancedNo, lectureNo2);
				String id = findLectureInquiry.getAdvancedId();
				
				//비밀글에서 글쓴이가 맞는 경우
				if(student.getStudentId().equals(id)){
					
					LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo, lectureNo2);
					
					int advancedHit = lectureInquiry.getAdvancedHit();
					lectureInquiry.setAdvancedHit(++advancedHit);				
					service.updateAdvancedHit(lectureInquiry);
					
					List commentList = service.commentList(advancedNo);
					
					Map map = new HashMap();
					map.put("lectureInquiryDetail", lectureInquiry);
					map.put("commentList", commentList);
					
					return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", map);
				}
				
				//비밀글에서 글쓴이가 아닌 경우
				else
					return new ModelAndView("/lectureInquiry/lectureInquiryList.do?lectureNo2=" + lectureNo2);	
			}
			
			//강사, 관리자일 경우
			else if(member.equals("teacher")||member.equals("administrator")){
				
				LectureInquiry LectureInquiry = service.selectByAdvancedNoWithComment(advancedNo, lectureNo2);
				
				int advancedHit = LectureInquiry.getAdvancedHit();
				LectureInquiry.setAdvancedHit(++advancedHit);				
				service.updateAdvancedHit(LectureInquiry);
				
				List commentList = service.commentList(advancedNo);
				
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
		else if(secret.equals("false")){
			
			member = session.getAttribute("memberType");			
			
			//학생, 강사, 관리자일 경우
			if(member.equals("student")||member.equals("teacher")||member.equals("administrator")){
				
				LectureInquiry lectureInquiry = service.selectByAdvancedNoWithComment(advancedNo, lectureNo2);
				
				int advancedHit = lectureInquiry.getAdvancedHit();
				lectureInquiry.setAdvancedHit(++advancedHit);				
				service.updateAdvancedHit(lectureInquiry);
				
				List commentList = service.commentList(advancedNo);
				
				System.out.println("코멘트리스트3: " + commentList);
				
				Map map = new HashMap();
				map.put("lectureInquiryDetail", lectureInquiry);
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
	public String registerLectureInquiry(@RequestParam(defaultValue="1") int page, int lectureNo2, LectureInquiry lectureInquiry, String advancedSecret, HttpSession session, BindingResult errors){
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
			validator.validate(lectureInquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors()){
				return "/lectureInquiry/registerLectureInquiryForm.do?lectureNo2=" + lectureNo2;
			}
			
			service.insertLectureInquiry(lectureInquiry);
			
//			service.selectAllByPaging(page, lectureNo2);
			
//			return "redirect:/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + lectureInquiry.getAdvancedNo()
//			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
			return "redirect:/lectureInquiry/registerLectureInquiryRedirect.do?advancedNo=" + lectureInquiry.getAdvancedNo()
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
			validator.validate(lectureInquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/lectureInquiry/registerLectureInquiryForm.do?lectureNo2=" + lectureNo2;
			}
			
			service.insertLectureInquiry(lectureInquiry);
			
//			service.selectAllByPaging(page, lectureNo2);
			
//			return "redirect:/lectureInquiry/selectByAdvancedNo.do?advancedNo=" + lectureInquiry.getAdvancedNo()
//			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
//			return "redirect:/lectureInquiry/selectByAdvancedNoWithComment.do?advancedNo=" + lectureInquiry.getAdvancedNo() + "&advancedSecret="
//			+ lectureInquiry.getAdvancedSecret() + "&lectureNo2=" + lectureInquiry.getLectureNo2();
			return "redirect:/lectureInquiry/registerLectureInquiryRedirect.do?advancedNo=" + lectureInquiry.getAdvancedNo()
			+ "&lectureNo2=" + lectureInquiry.getLectureNo2();
		}
		else 
			return "main.tiles";		
	}	
	
	//등록 redirect 처리
	@RequestMapping("/registerLectureInquiryRedirect")
	public ModelAndView registerLectureInquiryRedirect(int advancedNo, int lectureNo2, @RequestParam(defaultValue="1") int page){
		LectureInquiry lectureInquiryDetail = service.selectByAdvancedNoWithComment(advancedNo, lectureNo2);	
		
		System.out.println("re No: " + advancedNo + "lecture: " + lectureNo2);

		return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", "lectureInquiryDetail", lectureInquiryDetail);
	}
	
	//등록, 수정했을 때 상세페이지 보기
	@RequestMapping("/selectByAdvancedNo")
	public ModelAndView selectByAdvancedNo(int advancedNo, int lectureNo2){
		
		LectureInquiry lectureInquiry = service.selectByAdvancedNo(advancedNo, lectureNo2);
		String advancedSecret = lectureInquiry.getAdvancedSecret().split(",")[0];
		
		lectureInquiry.setAdvancedSecret(advancedSecret);
		
		System.out.println("상세: " + lectureInquiry.getAdvancedSecret());
		
		return new ModelAndView("lectureInquiry/lectureInquiry_insert_detail.tiles", "lectureInquiryDetail", lectureInquiry);
	}

	//수정폼
	@RequestMapping("/updateLectureInquiryForm")
	public ModelAndView updateLectureInquiryForm(@RequestParam(defaultValue="1") int page, int advancedNo, int lectureNo2){
		
		LectureInquiry lectureInquiryDetail = service.selectByAdvancedNo(advancedNo, lectureNo2);
		
		return new ModelAndView("lectureInquiry/lectureInquiry_modify.tiles", "lectureInquiryDetail", lectureInquiryDetail);
	}
	
	//수정하기
	@RequestMapping("/updateLectureInquiry")
	public String updateLectureInquiry(@RequestParam(defaultValue="1") int page, @ModelAttribute("updateLectureInquiry") @Valid LectureInquiry updateLectureInquiry, BindingResult errors){
		String secret = updateLectureInquiry.getAdvancedSecret();

		//비밀글인 경우
		if(secret.equals("true")){
		
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			String advancedType = "강의질문";
			
			updateLectureInquiry = new LectureInquiry(updateLectureInquiry.getAdvancedNo(), "true", updateLectureInquiry.getAdvancedTitle(), updateLectureInquiry.getAdvancedContent(), advancedDate, advancedType, updateLectureInquiry.getLectureNo2());
			
			LectureInquiryValidator validator = new LectureInquiryValidator();
			validator.validate(updateLectureInquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/lectureInquiry/updateLectureInquiryForm.do?advancedNo=" + updateLectureInquiry.getAdvancedNo() + "&lectureNo2=" + updateLectureInquiry.getLectureNo2();
			}
			
			service.updateLectureInquiry(updateLectureInquiry);				
			
			return "redirect:/lectureInquiry/updateLectureInquiryRedirect.do?advancedNo=" + updateLectureInquiry.getAdvancedNo()
					+ "&lectureNo2=" + updateLectureInquiry.getLectureNo2();
		}
		
		//공개글인 경우
		if(secret.equals("false")){
		
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			String advancedType = "강의질문";
			
			updateLectureInquiry = new LectureInquiry(updateLectureInquiry.getAdvancedNo(), "false", updateLectureInquiry.getAdvancedTitle(), updateLectureInquiry.getAdvancedContent(), advancedDate, advancedType, updateLectureInquiry.getLectureNo2());
			
			LectureInquiryValidator validator = new LectureInquiryValidator();
			validator.validate(updateLectureInquiry, errors);
			
			//에러가 났을 경우
			if (errors.hasErrors())
			{
				return "/lectureInquiry/updateLectureInquiryForm.do?advancedNo=" + updateLectureInquiry.getAdvancedNo() + "&lectureNo2=" + updateLectureInquiry.getLectureNo2();
			}
			
			service.updateLectureInquiry(updateLectureInquiry);				
			
			return "redirect:/lectureInquiry/updateLectureInquiryRedirect.do?advancedNo=" + updateLectureInquiry.getAdvancedNo()
					+ "&lectureNo2=" + updateLectureInquiry.getLectureNo2();
		}
		else 
			return "main.tiles";
	}
	
	//수정 redirect 처리
	@RequestMapping("/updateLectureInquiryRedirect")
	public ModelAndView updateLectureInquiryRedirect(int advancedNo, int lectureNo2, @RequestParam(defaultValue="1") int page){
		LectureInquiry lectureInquiryDetail = service.selectByAdvancedNoWithComment(advancedNo, lectureNo2);	
		
		System.out.println("re No: " + advancedNo + "lecture: " + lectureNo2);

		return new ModelAndView("lectureInquiry/lectureInquiry_detail.tiles", "lectureInquiryDetail", lectureInquiryDetail);
	}
	
	//삭제하기
	@RequestMapping("/deleteLectureInquiry")
	public String deleteLectureInquiry(int advancedNo, int lectureNo2){
		
		service.deleteLectureInquiry(advancedNo, lectureNo2);

		return "/lectureInquiry/lectureInquiryList.do?lectureNo2=" + lectureNo2;
	}
	
	
	//키워드로 강의리스트 가져오기
	@RequestMapping("/searchByKeyword.do")
	public ModelAndView searchByKeyword(@RequestParam(defaultValue="1") int page, String searchType, String keyword, int lectureNo2){
		Map map = new HashMap();
		if(searchType.equals("advancedTitle")){
		   map = service.selectTitleByPaging(page, keyword, lectureNo2);
		   map.put("searchType", searchType);
		   map.put("keyword", keyword);
		   map.put("lectureNo2", lectureNo2);
		   map.put("page", page);

		}else if(searchType.equals("advancedContent")){
			map = service.selectContentByPaging(page, keyword, lectureNo2);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
			map.put("lectureNo2", lectureNo2);
			map.put("page", page);
		}
		else{
			map = service.selectAllByPaging(page, lectureNo2);
			map.put("page", page); 
			map.put("searchType", searchType);
			map.put("keyword", keyword);
			map.put("lectureNo2", lectureNo2);
			
			return new ModelAndView("lectureInquiry/lectureInquiry_list.tiles");
		}
		return new ModelAndView("lectureInquiry/lectureInquiry_search.tiles", map);
	}
}
