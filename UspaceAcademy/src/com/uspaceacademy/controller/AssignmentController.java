package com.uspaceacademy.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;

import com.uspaceacademy.service.AssignmentService;
import com.uspaceacademy.vo.Assignment;
import com.uspaceacademy.vo.Board;
import com.uspaceacademy.vo.LectureReview;
import com.uspaceacademy.vo.Student;

@Controller
@RequestMapping("/assignment")
public class AssignmentController {

	@Autowired
	private AssignmentService service;
	

	//mybatis.config.xml에 mapper꼭 등록하기
	
	
	
	
	//아래 강사******************************************************************************************************************
	
	//오류났던거 적기 : 답글게시판으로 변경하는 과정에서 - mapper에 replyGetList -  where절에 ,콤마안찍어줬고, resultMap안적어줬음 그리고 아래 map.put("assignment")로했는데 jsp에서 이름다르게 뿌려줘서 그랬음.
	//과제게시판 - 목록보기(마이페이지에서 - 내강좌 눌렀을때) //답글기능생긴후 변경 - ok
		@RequestMapping("/assignment_list.do")
		public ModelAndView list(@RequestParam(defaultValue="1") int page){  //@RequestParam(defaultValue="1") 디폴트값일때 1을 넣어라
			
			System.out.println("controller-------------------"+service.replyGetList());
			Map map = new HashMap();
			map.put("page", service.selectPagingCount(page));
			map.put("assignment", service.replyGetList());
			
			System.out.println("과제게시판ok");
			return new ModelAndView("assignment/assignment_list.tiles", map) ;
		}
/*		//과제게시판 - 목록보기(마이페이지에서 - 내강좌 눌렀을때) //답글기능 생기기전
		@RequestMapping("/assignment_list.do")
		public ModelAndView list(@RequestParam(defaultValue="1") int page){  //@RequestParam(defaultValue="1") 디폴트값일때 1을 넣어라
			
			Map map = service.selectPagingCount(page);
			map.put("page", page);

			System.out.println("과제게시판ok");
			return new ModelAndView("assignment/assignment_list.tiles", map) ;
		}*/
	




	//과제게시판 -  상세조회(assignment_list.jsp -> assignment_detail.jsp)
	@RequestMapping("/assignment_detail")
	public ModelAndView detail(String assignmentNo){
		int num = Integer.parseInt(assignmentNo);
		Assignment assignment = service.selectNo(num);//no값으로 게시물 찾아옴
		service.selectHit(assignment); //조회수 증가시키기
		
		System.out.println("과제게시판 상세조회ok");
		return new ModelAndView("assignment/assignment_detail.tiles","assignment", assignment);
	}










		//과제글 작성 폼 (assignment_list.jsp ->assignment_register.jsp)
		@RequestMapping("/assignment_register")
		public ModelAndView registerForm(){
			
			
			
			System.out.println("과제게시판 작성폼ok");	
			return new ModelAndView("assignment/assignment_register.tiles");//등록폼으로 온다*
		}
		

		

		
		
		
		
		
		
		//과제등록할거 작성하고 등록(과제작성완료 눌렀을때)
		@RequestMapping("/assignment_registerSuccess")//assignment_register.jsp 에서  
		public ModelAndView register(Assignment assignment, String teacherName){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sdfDate = sdf.format(date);
			
	
			
			//글번호
			int num = service.selectNextNo();
			
			//Assignment assignment 하면안되고 assignment= 하기 똑같은거1개 x																																									//안되서 맨끝에 lectureNo 우선 개설강좌에있는 no넣어놓고함*
			assignment= new Assignment(num,assignment.getAssignmentTitle(),assignment.getAssignmentContent(),sdfDate,assignment.getAssignmentHit(),teacherName,assignment.getAssignmentDeadline(),3);
			service.insert(assignment);
			
			System.out.println("과제글 작성하고 등록ok");
		return new ModelAndView("assignment/assignment_detail.tiles","assignment",assignment);
		}
		


		
		
		
		
		
		
		
		
		//과제출제글 삭제(과제글 상세조회에서 삭제 눌렀을때)
		@RequestMapping("/assignment_delete")//assignment_detail.jsp 에서  
		public ModelAndView delete(int assignmentNo, String type){
			//int list =  service.delete(assignmentNo);
			service.delete(assignmentNo);
			List list = service.selectList(type);
				
			System.out.println("과제글 삭제 ok");
		return new ModelAndView("assignment/assignment_list.tiles","assignment",list);
		}
		
		
		
		
		
		
		
		
		

		//과제글 수정폼(수강상세조회에서 수정하기 눌렀을때 - modify폼으로감)
		@RequestMapping("/assignment_modifyForm")  
		public ModelAndView modifyForm(){

			
			
			
			System.out.println("과제글 수정 폼 ok");
			return new ModelAndView("assignment/assignment_modify.tiles"); 
		}
		


		
		
		
		
		
		
		//과제글 수정완료하기 (수정폼에서 과제글 수정완료 눌렀을때)
		@RequestMapping("/assignment_modify") //assignment_modify.jsp에서
		public ModelAndView modify(@ModelAttribute("lec") @Valid LectureReview lectureReview, BindingResult errors) { 
			

			System.out.println("과제글 수정 ok");
			return new ModelAndView("assignment/assignment_detail.tiles"); //과제글 수정완료 버튼누르면 내가 수정한내용 detail에서 보여짐
		}




	//위에강사******************************************************************************************************************

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	//아래 학생 답글-----------------------------------------------------------------------------------------------------------------
	
		//답글 작성 폼 (assignment_detail.jsp ->assignment_replyRegister.jsp)글상세페이지에서 답변하기버튼클릭 -> 답변폼으로 온다
		@RequestMapping("/assignment_replyRegister")
		public ModelAndView replyRegister(){
			
			
			
			System.out.println("답글 작성폼 ok");	
			return new ModelAndView("assignment/assignment_replyRegister.tiles");
		}

		
		
		
		
		
		
		//답변폼에서   ->  답글등록할거 작성하고 답글등록(답글작성완료 눌렀을때) assignment_replyRegister.jsp 에서 assignment_list.jsp로감
		@RequestMapping("/assignment_replyRegisterSuccess")// 
		public ModelAndView reply(Assignment assignment, HttpServletRequest request){
			
			HttpSession session = request.getSession();//
			Student s = (Student)session.getAttribute("login_info");//
			String student = s.getStudentName();//
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sdfDate = sdf.format(date);
			
	
			
			//글번호
			int num = service.selectNextNo();
			
			//Assignment assignment 하면안되고 assignment= 하기 똑같은거1개 x																																									//안되서 맨끝에 lectureNo 우선 개설강좌에있는 no넣어놓고함*
			assignment= new Assignment(num,assignment.getAssignmentTitle(),assignment.getAssignmentContent(),sdfDate,assignment.getAssignmentHit(),student,assignment.getAssignmentDeadline(),3);
			service.insert(assignment);
			
			
			
			System.out.println("답글  작성하고 등록ok");
		return new ModelAndView("assignment/assignment_list.tiles","assignment",assignment); //답글작성완료하면 상세페이지로 돌아가기*
		}
		
		/*//과제등록할거 작성하고 등록(과제작성완료 눌렀을때)
		@RequestMapping("/assignment_registerSuccess")//assignment_register.jsp 에서  
		public ModelAndView register(Assignment assignment, String teacherName){
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sdfDate = sdf.format(date);
			
	
			
			//글번호
			int num = service.selectNextNo();
			
			//Assignment assignment 하면안되고 assignment= 하기 똑같은거1개 x																																									//안되서 맨끝에 lectureNo 우선 개설강좌에있는 no넣어놓고함*
			assignment= new Assignment(num,assignment.getAssignmentTitle(),assignment.getAssignmentContent(),sdfDate,assignment.getAssignmentHit(),teacherName,assignment.getAssignmentDeadline(),3);
			service.insert(assignment);
			
			System.out.println("과제글 작성하고 등록ok");
		return new ModelAndView("assignment/assignment_detail.tiles","assignment",assignment);
		}
		*/
      //위에 학생 답글-----------------------------------------------------------------------------------------------------------------
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		/*//>>>>>>>>>>>>>>>파일 업로드 (학생이 답글달기에서 파일 업로드 할때)
		@RequestMapping("/imageUpload")
		public String imageUpload(@RequestParam MultipartFile upImage, @RequestParam String comment, HttpServletRequest request, ModelMap map) throws IllegalStateException, IOException{

			System.out.println(comment);
		//null -> upImage란 요청파라미터가 없는 경우, upImage.isEmpty():true -> 업로드된 파일이 없는 경우.
		if(upImage != null && !upImage.isEmpty()){
		String fileName = upImage.getOriginalFilename();//업로드된 파일명
		long fileSize = upImage.getSize(); //업로드된 파일 사이즈
		System.out.println(fileName+"-"+fileSize);
		
		
		//임시저장소 저장된 업로드 파일을 최종 저장소로 이동.
		//최종 저장소 디렉토리 조회		//    /application 루트 경로 -> 파일경로로 알려준다.
		File dest = new File(request.getServletContext().getRealPath("/upImage"),fileName); //request.getServletContext().getRealPath("/upImage")                
		
		
		//이동
		upImage.transferTo(dest); //이동하는것, 한번밖에안됨*
		
		//Request scope 속성으로 파일정보와 comment를 View로 전달
		map.addAttribute("imageName",fileName);
		map.addAttribute("comment",comment);
		map.addAttribute("imageSize",fileSize);
		}
			//return "assignment/assignment_file.tiles";
		return "assignment/assignment_detail.tiles";
		}	
		
		//>>>>>>>>>>>>>>>
		@RequestMapping("/writeBoard")
		public String writeBoard(@ModelAttribute Board board,  ModelMap map, HttpServletRequest request) throws IOException{
			System.out.println(board);
			
			ArrayList fileNames = new ArrayList(); //~~
			
			List upfile = board.getUpfile();
			if( upfile != null ){ //null인 경우는 upfile이름으로 넘어온 요청파라미터가 없는 경우.
				
				String saveDir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
				//String saveDir = "C:\\java\\temp"; //파일저장디렉토리
				
				for(Object f : upfile){
					MultipartFile file = (MultipartFile)f; //업로드된 파일 정보 하나씩 조회 -> 이동
					
					if( ! file.isEmpty()){//업로드된 파일이 있으면 -> 이동
						String fileName = file.getOriginalFilename(); //업로드된 파일명 조회.
						File dest = new File(saveDir, fileName);
						file.transferTo(dest); //이동처리 
						fileNames.add(fileName); //~~
					}
				}
			}
			map.addAttribute("fileNames",fileNames);             //~~
			map.addAttribute("title", board.getTitle());             //~~
			map.addAttribute("content", board.getContent());   //~~
			
			//return "assignment/assignment_file.tiles";
			return "assignment/assignment_detail.tiles";
		}	
		
		
		
		
		//>>>>>>>>>>>>>>>다운로드 처리 handler메소드
		@RequestMapping("/download")
		public ModelAndView download(@RequestParam String fileName){
		 
		 * 	 View-Name : downloadView
		 * 	 Model(View에 전달할 값) : "downFile" - 파일명 downFile-abc.txt
		 
		return new ModelAndView("downloadView", "downFile", fileName);
		}
		
		
		
		//>>>>>>>>>>>>>>>>>>>>>>>
		public class DownloadView extends AbstractView{

			//응답 Content Type을 리턴
			public String getContentType(){
				return "application/octet-stream";//타입을 명확히 지정하지 않겠다. -> 웹브라우저가 다운받게 하겠다.
			}
			
			
			 	응답처리 구현 메소드
			 * 		매개변수 : 1.Map model - handler에서 전달한 Model 값(ModelAndView의 Model)
			 
			public void renderMergedOutputModel(Map model, HttpServletRequest request, HttpServletResponse response) throws Exception{
				
				String fileName = (String)model.get("downFile");
				String downFileName = new String(fileName.getBytes("euc-kr"), "8859_1"); //파일명 한글 처리
				
				//응답헤더 설정
				//content type 설정
				response.setContentType(getContentType());
				//다운로드될 파일의 파일명 지정
				response.setHeader("content-disposition", "attachment;filename="+downFileName);
			
				//Stream을 이용해 파일 Cilent(Web browser)로 전동
				String dir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
				//String dir = "C:\\java\\temp"; //파일저장디렉토리
				
				FileInputStream fi = new FileInputStream(new File(dir, fileName));
				OutputStream os = response.getOutputStream();
				FileCopyUtils.copy(fi, os); // fi stream에서 읽을 것을 os strean으로 카피(출력)
				
			}
		}
		*/
		
		//-----------------------
		
		
		
		
		
		
		
		
}














