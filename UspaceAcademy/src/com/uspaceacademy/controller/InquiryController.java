package com.uspaceacademy.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.InquiryService;
import com.uspaceacademy.validaotor.InquiryValidator;
import com.uspaceacademy.vo.Code;
import com.uspaceacademy.vo.Inquiry;
import com.uspaceacademy.vo.Student;

@Controller
@RequestMapping("/inquiry")
public class InquiryController
{
	private String code;
	
	@Autowired
	private InquiryService service;
	
	//1:1문의 전체 목록
	@RequestMapping("/inquiryList")
	public ModelAndView inquiryList(@RequestParam(defaultValue="1") int page, String advancedType){		
		System.out.println("타입: " + advancedType);
		String codeName = "1:1문의";
		List codeList = service.selectByCodeName(codeName);

		Code code = (Code) codeList.get(0);		
//		System.out.println("코드는 무엇인가?" + codeList.get(0));


		System.out.println("inquiryList 넘어오나");
		Map map = service.getInquiryList(page, advancedType);
		map.put("page", page);
		return new ModelAndView("inquiry/inquiry_list.tiles", map);			
	}
	
	//1:1문의 전체목록에서 조회할 글을 눌렀을 때 상세페이지 조회하기. 
	@RequestMapping("/selectByAdvancedNo")
	public ModelAndView selectByAdvancedNo(int advancedNo, String advancedSecret, String advancedType, HttpServletRequest request){
		String secret = advancedSecret.split(",")[0];
		String codeName = "1:1문의";
		List codeList = service.selectByCodeName(codeName);
//		Code code = (Code)codeList.get(0);
//		String getCodeName = code.getCodeName();
//		HashMap map = new HashMap<>();
//		String inquiryId = map.get(advancedId);
		
//		System.out.println("secret: " + secret);
//		System.out.println("id: " + id);
//		System.out.println("advanedType: " + advancedType);
//		
//			
//		System.out.println("조회No: " + advancedNo);
//		System.out.println("secret 조회: " + secret);
		
		
		//비밀글이 아닌 경우(모든 사용자 조회 가능)
		if(secret.equals("0")){
			System.out.println("000");
			Inquiry selectInquiry = service.selectByAdvancedNo(advancedNo);
			int advancedHit = selectInquiry.getAdvancedHit();
			selectInquiry.setAdvancedHit(++advancedHit);
			service.updateHit(selectInquiry);
			
			return new ModelAndView("inquiry/inquiry_detail.tiles", "inquiryDetail", selectInquiry);
		}
		
		//비밀글인 경우
		else if(secret.equals("1")){
			System.out.println("111");
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("login_info");
			Inquiry findInquiry = service.selectByAdvancedNo(advancedNo);
			String id = findInquiry.getAdvancedId();
			Object member = session.getAttribute("login_info");
			
			System.out.println("id를 찾아라" + id);
			
			//비밀글에서 글쓴이가 맞는 경우
			if(student.getStudentId().equals(id)){
				Inquiry selectInquiry = service.selectByAdvancedNo(advancedNo);
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
		
		else
			return new ModelAndView("main.tiles");
	}

	
	//1:1문의 등록폼
	@RequestMapping("/codeList.do")
	public ModelAndView selectCodeName() {
		String codeName = "1:1문의";
		List codeList = service.selectByCodeName(codeName);
		Code code = (Code)codeList.get(0);
		String getCodeName = code.getCodeName();
		
		return new ModelAndView("inquiry/insert_inquiry.tiles", "advancedType", getCodeName);
	}
	
	//1:1문의 등록하기	
//	String advancedTitle, String advancedContent, String codeName,
	@RequestMapping("/insertInquiry")
	public String insertInquiry(Inquiry inquiry, String secret, HttpServletRequest request, BindingResult errors){	
		System.out.println("secret이 뭘까? "+secret);
		
		//비밀글인 경우
		if(secret.equals("true")){
			int advancedNo = service.increaseAdvancedNo();
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("login_info");
			String advancedId = student.getStudentId();
		
			inquiry = new Inquiry(advancedNo, "1", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 0, advancedId, "1:1문의");
			System.out.println(inquiry);
			
			InquiryValidator validator = new InquiryValidator();
			validator.validate(inquiry, errors);
			
			if (errors.hasErrors())
			{
				return "/inquiry/codeList.do?advancedType=1:1문의";
			}
			
			service.insertInquiry(inquiry);		
			
			System.out.println("id등록?비밀글 " + advancedId);
			
//	//		return "/inquiry/selectByAdvancedNo.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret();
			return "redirect:/inquiry/inquiryRedirect.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret()
			+"&advancedType="+inquiry.getAdvancedType();
		}
		
		//비밀글이 아닌 경우
		else{
			int advancedNo = service.increaseAdvancedNo();
			String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
			HttpSession session = request.getSession();
			Student student = (Student)session.getAttribute("login_info");
			String advancedId = student.getStudentId();
		
			inquiry = new Inquiry(advancedNo, "0", inquiry.getAdvancedTitle(), inquiry.getAdvancedContent(), advancedDate, 0, advancedId, inquiry.getAdvancedType());
			System.out.println(inquiry);
			
			InquiryValidator validator = new InquiryValidator();
			validator.validate(inquiry, errors);
			
			if (errors.hasErrors())
			{
				return "/inquiry/codeList.do?advancedType=1:1문의";
			}
			
			service.insertInquiry(inquiry);
			
//			System.out.println("id등록? " + advancedId);
			
			return "redirect:/inquiry/inquiryRedirect.do?advancedNo="+inquiry.getAdvancedNo()+"&advancedSecret="+inquiry.getAdvancedSecret()
			+"&advancedType="+inquiry.getAdvancedType();
		}
	}	
	
	//등록 redirect 처리
	@RequestMapping("/inquiryRedirect")
	public ModelAndView inquiryRedirect(int advancedNo, @RequestParam(defaultValue="1") int page, String advancedSecret, String advancedId, String advancedType) // 새로고침 시 더 등록 안되도록 redirect 처리
	{
		System.out.println("redirect: " + advancedId);
		Map map = service.getInquiryList(page, advancedType);
//		map.put("advancedSecret", advancedSecret);
//		map.put("advancedId", advancedId);
//		map.put(advancedType, advancedType);
		return new ModelAndView("/inquiry/selectByAdvancedNo.do?advancedNo="+advancedNo+"&advancedSecret="+advancedSecret
			+"&advancedType="+advancedType, map);
	}	

	//1:1문의 수정폼
	@RequestMapping("/updateByAdvancedNo")
	public ModelAndView updateByAdvancedNo(int advancedNo){
		Inquiry inquiryDetail = service.selectByAdvancedNo(advancedNo);
		return new ModelAndView("inquiry/inquiry_modify.tiles", "inquiryDetail", inquiryDetail);
	}
	
	//1:1문의 수정하기
	@RequestMapping("/updateInquiry")
	public ModelAndView updateInquiry(int advancedNo, String advancedSecret, String advancedTitle, String advancedContent, int advancedHit, String advancedId){
		String advancedDate = new SimpleDateFormat("yyyy/MM/dd hh:mm").format(new Date());
		Inquiry inquiryDetail = new Inquiry(advancedNo, advancedSecret, advancedTitle, advancedContent, advancedDate, advancedHit, advancedId);
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
	public ModelAndView selectByTitle(@RequestParam(defaultValue="1") int page, String title){	
		System.out.println(title);
		Map map = service.selectByTitle(title, page);
		map.put("page", page);
		map.put("title", title);
		return new ModelAndView("inquiry/inquiry_title_search.tiles", map);
	}
	
	@RequestMapping("/searchByTitle")
	public ModelAndView searchByTitle(String title){
		System.out.println(title);
		Map map = service.searchByTitle(title);
		map.put("title", title);
		
		return new ModelAndView("inquiry/inquiry_title_search.tiles", map);
	}
	
	
	@RequestMapping("/insertComment")
	public ModelAndView selectAllComment(String commentContent, String InquiryNo){
		
		
		return new ModelAndView("inquiry/inquiryComment_list");
	}

}
