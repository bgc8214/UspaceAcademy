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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryService;
import com.uspaceacademy.validaotor.InquiryValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Comment;
import com.uspaceacademy.vo.Inquiry;
import com.uspaceacademy.vo.Student;

@Controller
@RequestMapping("/inquiry")
public class InquiryController
{
	private String code;
	private String value;
	
	@Autowired
	private InquiryService service;
	
	//1:1문의 전체 목록
	@RequestMapping("/inquiryList")
	public ModelAndView inquiryList(@RequestParam(defaultValue="1") int page){

		Map map = service.getInquiryList(page);
		
		map.put("page", page);
		
		return new ModelAndView("inquiry/inquiry_list.tiles", map);			
	}
	
	//1:1문의 전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회하기. 
	@RequestMapping("/selectJoinByAdvancedNo")
	public ModelAndView selectJoinByAdvancedNo(int advancedNo, String advancedSecret, HttpSession session){
		String secret = advancedSecret.split(",")[0];
		String advancedType = "1:1문의";
		List codeList = service.selectByCodeName(advancedType);		
		Object member = session.getAttribute("memberType");	
		
		System.out.println("아예 넘어오지도 않네");
		
		
		Inquiry selectInquiry1 = service.selectJoinByAdvancedNo(advancedType, advancedNo);
		int advancedHit1 = selectInquiry1.getAdvancedHit();
		System.out.println("hit1: " + advancedHit1);

//		List<Comment> commentList = selectInquiry1.getCommentList();
		
//		if(commentList)
		
//		System.out.println("memberType = " + member);
//		System.out.println("advancedNo = " + advancedNo);
//		System.out.println("advancedSecret = " + advancedSecret);
		
		//비밀글이 아닌 경우(모든 사용자 조회 가능)
		if(secret.equals("0")||secret.equals(null)){
			System.out.println("000");
			Inquiry selectInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
			int advancedHit = selectInquiry.getAdvancedHit();
			System.out.println("hit: " + advancedHit);
			selectInquiry.setAdvancedHit(++advancedHit);
			service.updateHit(selectInquiry);			
//			List<Comment> commentList = selectInquiry.getCommentList();
			
			HashMap map = new HashMap<>();
			map.put("inquiryDetail", selectInquiry);
//			map.put("commentList", commentList);
			
			return new ModelAndView("inquiry/inquiry_detail.tiles", map);

		}
		
		//비밀글인 경우
		else if(secret.equals("1")){
			System.out.println("111");
			
			member = session.getAttribute("memberType");			
			
			if(member.equals("student")){
				Student student = (Student)session.getAttribute("login_info");
				Inquiry findInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
				String id = findInquiry.getAdvancedId();

				//비밀글에서 글쓴이가 맞는 경우
				if(student.getStudentId().equals(id)){
					Inquiry selectInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
					int advancedHit = selectInquiry.getAdvancedHit();
					selectInquiry.setAdvancedHit(++advancedHit);
					service.updateHit(selectInquiry);				
					
//					List<Comment> commentList = selectInquiry.getCommentList();
					HashMap map = new HashMap<>();
					map.put("inquiryDetail", selectInquiry);
//					map.put("commentList", commentList);
					
					return new ModelAndView("inquiry/inquiry_detail.tiles", map);			
				}			
			
				//비밀글에서 글쓴이가 아닌 경우
				else{
					return new ModelAndView("/inquiry/inquiryList.do");				
				}
			}
			
			//관리자인 경우
			else if(member.equals("administrator")){
				Inquiry selectInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
				int advancedHit = selectInquiry.getAdvancedHit();
				selectInquiry.setAdvancedHit(++advancedHit);
				service.updateHit(selectInquiry);
				
//				List<Comment> commentList = selectInquiry.getCommentList();
				HashMap map = new HashMap<>();
				map.put("inquiryDetail", selectInquiry);
//				map.put("commentList", commentList);
				
				return new ModelAndView("inquiry/inquiry_detail.tiles", map);				
			}
			
/*			//비회원인 경우
			if(member.equals(null)){
				return new ModelAndView("/inquiry/inquiryList.do");
			}*/
			
			else
				return new ModelAndView("/inquiry/inquiryList.do");
		}		
		else
			return new ModelAndView("main.tiles");
	}
	
	
	
	@RequestMapping("/selectByAdvancedNo")
	public ModelAndView selectByAdvancedNo(int advancedNo, String advancedSecret, HttpSession session){
		advancedSecret = advancedSecret.split(",")[0];
		String advancedType = "1:1문의";
	
		Object member = session.getAttribute("memberType");			
		
//		System.out.println("advancedNo = " + advancedNo);
//		System.out.println("advancedSecret = " + advancedSecret);
		
		//비밀글이 아닌 경우(모든 사용자 조회 가능)
		if(advancedSecret.equals("0")||advancedSecret.equals(null)){

			Inquiry selectInquiry = service.selectByAdvancedNo(advancedNo);
			int advancedHit = selectInquiry.getAdvancedHit();
			selectInquiry.setAdvancedHit(++advancedHit);
			service.updateHit(selectInquiry);			
			
			return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", selectInquiry);
		}
		
		//비밀글인 경우
		else if(advancedSecret.equals("1")){
			System.out.println("111");
			
			member = session.getAttribute("memberType");			
			
			if(member.equals("student")){
				Student student = (Student)session.getAttribute("login_info");
				Inquiry findInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
				String id = findInquiry.getAdvancedId();

				//비밀글에서 글쓴이가 맞는 경우
				if(student.getStudentId().equals(id)){
					Inquiry selectInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
					int advancedHit = selectInquiry.getAdvancedHit();
					selectInquiry.setAdvancedHit(++advancedHit);
					service.updateHit(selectInquiry);		
					
					return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", selectInquiry);			
				}			
			
				//비밀글에서 글쓴이가 아닌 경우
				else{
					return new ModelAndView("/inquiry/inquiryList.do");				
				}
			}
			
			//관리자인 경우
			else if(member.equals("administrator")){
				Inquiry selectInquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);
				int advancedHit = selectInquiry.getAdvancedHit();
				selectInquiry.setAdvancedHit(++advancedHit);
				service.updateHit(selectInquiry);
				
				return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", selectInquiry);				
			}
			
/*			//비회원인 경우
			if(member.equals(null)){
				return new ModelAndView("/inquiry/inquiryList.do");
			}*/
			
			else
				return new ModelAndView("/inquiry/inquiryList.do");
		}		
		else
			return new ModelAndView("main.tiles");
	}
	

	
	//1:1문의 등록폼
	@RequestMapping("/codeList.do")
	public ModelAndView selectCodeName() {
		String advancedType = "1:1문의";
		
		return new ModelAndView("inquiry/insert_inquiry.tiles", "advancedType", advancedType);
	}
	
	//1:1문의 등록하기	
//	String advancedTitle, String advancedContent, String codeName,
	@RequestMapping("/insertInquiry")
	public String insertInquiry(Inquiry inquiry, String secret, HttpSession session, BindingResult errors){	
		System.out.println("secret이 뭘까? "+secret);
		
		//비밀글인 경우
		if(secret.equals("true")){
			Object member = session.getAttribute("memberType");
			
			//학생인 경우
			if(member.equals("student")){
				int advancedNo = service.increaseAdvancedNo();
				String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		
				Student student = (Student)session.getAttribute("login_info");
				String advancedId = student.getStudentId();
				
				inquiry = new Inquiry(advancedNo, "1", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 0, advancedId, "1:1문의");
				System.out.println(inquiry);
				
				InquiryValidator validator = new InquiryValidator();
				validator.validate(inquiry, errors);
				
				//에러가 났을 경우
				if (errors.hasErrors())
				{
					return "/inquiry/codeList.do";
				}
				
				service.insertInquiry(inquiry);		
				
	//	//		return "/inquiry/selectByAdvancedNo.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret();
				return "redirect:/inquiry/inquiryRedirect.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret()
				+"&advancedType="+inquiry.getAdvancedType();
			}
			
			//관리자인 경우
			if(member.equals("administrator")){
				int advancedNo = service.increaseAdvancedNo();
				String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
				
				inquiry = new Inquiry(advancedNo, "1", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 0, "administrator", "1:1문의");
				System.out.println(inquiry);
				
				InquiryValidator validator = new InquiryValidator();
				validator.validate(inquiry, errors);
				
				//에러가 났을 경우
				if (errors.hasErrors())
				{
					return "/inquiry/codeList.do";
				}
				
				service.insertInquiry(inquiry);		
				
	//	//		return "/inquiry/selectByAdvancedNo.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret();
				return "redirect:/inquiry/inquiryRedirect.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret()
				+"&advancedType="+inquiry.getAdvancedType();
			}
		}
		
		//비밀글이 아닌 경우
		else{
			Object member = session.getAttribute("memberType");
			
			//학생인 경우
			if(member.equals("student")){						
				int advancedNo = service.increaseAdvancedNo();
				String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
				Student student = (Student)session.getAttribute("login_info");
				String advancedId = student.getStudentId();
				
				inquiry = new Inquiry(advancedNo, "0", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 0, advancedId, inquiry.getAdvancedType());
				System.out.println(inquiry);
				
				InquiryValidator validator = new InquiryValidator();
				validator.validate(inquiry, errors);
				
				//에러가 났을 경우
				if (errors.hasErrors())
				{
					return "/inquiry/codeList.do";
				}
				
				service.insertInquiry(inquiry);
				
				return "redirect:/inquiry/inquiryRedirect.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret()
				+"&advancedType="+inquiry.getAdvancedType();
			}
			
			//관리자인 경우
			if(member.equals("administrator")){						
				int advancedNo = service.increaseAdvancedNo();
				String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
				
				inquiry = new Inquiry(advancedNo, "0", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 0, "administrator", inquiry.getAdvancedType());
				System.out.println(inquiry);
				
				InquiryValidator validator = new InquiryValidator();
				validator.validate(inquiry, errors);
				
				//에러가 났을 경우
				if (errors.hasErrors())
				{
					return "/inquiry/codeList.do";
				}
				
				service.insertInquiry(inquiry);
				
				return "redirect:/inquiry/inquiryRedirect.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret()
				+"&advancedType="+inquiry.getAdvancedType();
			}			
		}
		return "main.tiles";
	}	
	
	//등록 redirect 처리
	@RequestMapping("/inquiryRedirect")
	public ModelAndView inquiryRedirect(int advancedNo, @RequestParam(defaultValue="1") int page, String advancedSecret) // 새로고침 시 더 등록 안되도록 redirect 처리
	{
		Map map = service.getInquiryList(page);
//		map.put("advancedSecret", advancedSecret);
//		map.put("advancedId", advancedId);
//		map.put(advancedType, advancedType);
		return new ModelAndView("/inquiry/selectJoinByAdvancedNo.do?advancedNo="+advancedNo+"&advancedSecret="+advancedSecret);
	}	

	//1:1문의 수정폼
	@RequestMapping("/updateByAdvancedNo")
	public ModelAndView updateByAdvancedNo(int advancedNo){

		Inquiry inquiryDetail = service.selectByAdvancedNo(advancedNo);
		return new ModelAndView("inquiry/inquiry_modify.tiles", "inquiryDetail", inquiryDetail);
	}
	
	//1:1문의 수정하기
	@RequestMapping("/updateInquiry")
	public ModelAndView updateInquiry(int advancedNo, String advancedSecret, String advancedTitle, String advancedContent){
		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		Inquiry inquiryDetail = new Inquiry(advancedNo, advancedSecret, advancedTitle, advancedContent, advancedDate);
		service.updateInquiry(inquiryDetail);
		return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", inquiryDetail);
	}
	
	//1:1문의 삭제하기
	@RequestMapping("/deleteByAdvancedNo")
	public String deleteByAdvancedNo(int advancedNo, String advancedType){
		System.out.println("삭제: " + advancedType);
		String codeName = "1:1문의";
		List codeList = service.selectByCodeName(codeName);
		Code code = (Code)codeList.get(0);
		String getCodeName = code.getCodeName();
	
		service.deleteByAdvancedNo(advancedNo);
		return "inquiry/inquiry_list.tiles";

	}
	
	//1:1문의 제목으로 검색하기
	@RequestMapping("/selectByTitle")
	public ModelAndView selectByTitle(@RequestParam(defaultValue="1") int page, String advancedTitle){
//		System.out.println(advancedType);
		System.out.println(advancedTitle);
		Map map = service.selectByTitle(advancedTitle, "1:1문의", page);
		map.put("page", page);
		map.put("advancedTitle", advancedTitle);
		
		return new ModelAndView("inquiry/inquiry_title_search.tiles", map);
	}
	
	@RequestMapping("/searchByTitle")
	public ModelAndView searchByTitle(String title){
		System.out.println(title);
		Map map = service.searchByTitle(title);
		map.put("title", title);
		
		return new ModelAndView("inquiry/inquiry_title_search.tiles", map);
	}
	
	//1:1문의  댓글 등록
	@RequestMapping("/insertComment")
	public ModelAndView insertComment(String commentContent, int advancedNo, HttpSession session){
		int commentNo = service.increaseCommentNo();
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		String commentType = "1:1문의댓글";
		String advancedType = "1:1문의";
		Object member = session.getAttribute("memberType");
		
		if(member.equals("student")){
			Student advancedId = (Student)session.getAttribute("login_info");
			String commentWriter = advancedId.getStudentId();
			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, commentWriter, commentType, advancedNo);
			service.insertComment(insertComment);
			Inquiry inquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);		
			
			HashMap map = new HashMap<>();
			map.put("inquiryDetail", inquiry);
//			map.put("commentList", inquiry.getCommentList());		
			
			return new ModelAndView("inquiry/inquiry_detail.tiles", map);
		}
		else if(member.equals("administrator")){			
			Comment insertComment = new Comment(commentNo, commentContent, commentDate, "administrator", commentType, advancedNo);
			service.insertComment(insertComment);
			Inquiry inquiry = service.selectJoinByAdvancedNo(advancedType, advancedNo);		
			
			HashMap map = new HashMap<>();
			map.put("inquiryDetail", inquiry);
//			map.put("commentList", inquiry.getCommentList());		
			
			return new ModelAndView("inquiry/inquiry_detail.tiles", map);
		}
		else
			return new ModelAndView("main.tiles");
	}
	
	//1:1문의  댓글 수정폼
	@RequestMapping("/updateCommentForm")
	public ModelAndView updateCommentForm(int commentNo){
		String commentType = "1:1문의";
//		Comment comment = service.selectByCommentNo(commentType, commentNo);
//		return new ModelAndView("inquiry/inquiry_modify.tiles", "commentList", comment);
		return new ModelAndView("inquiry/inquiry_modify.tiles");
	}
	
	//1:1문의  댓글
	@RequestMapping("/updateComment")
	public ModelAndView updateComment(int commentNo, String commentContent, int advancedNo, HttpSession session){
		String advancedType = "1:1문의";
		String commentType = "1:1문의댓글";
		String commentDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		
		Comment comment = new Comment(commentNo, commentContent, commentDate);		
		service.updateComment(comment);
		
		Inquiry inquiryDetail = service.selectJoinByAdvancedNo(advancedType, advancedNo);
//		List commentList = inquiryDetail.getCommentList();	
		
		HashMap map = new HashMap<>();
		map.put("inquiryDetail", inquiryDetail);
//		map.put("commentList", commentList);
		
		return new ModelAndView("inquiry/inquiry_detail", map);
	}
	
	//1:1문의  댓글 삭제
	@RequestMapping("/deleteComment")
	public ModelAndView deleteComment(int commentNo, int advancedNo){
		String advancedType = "1:1문의";
		String commentType = "1:1문의댓글";
		Inquiry inquiryDetail = service.selectJoinByAdvancedNo(advancedType, advancedNo);
//		List commentList = inquiryDetail.getCommentList();		
				
//		service.deleteComment(commentType, commentNo);
		
		HashMap map = new HashMap<>();
		map.put("inquiryDetail", inquiryDetail);
//		map.put("commentList", commentList);

		
		return new ModelAndView("/inquiry/inquiry_detail.tiles", map);
	}

}
