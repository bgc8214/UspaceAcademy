
package com.uspaceacademy.controller;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.uspaceacademy.service.AssignmentService;
import com.uspaceacademy.service.LectureService;
import com.uspaceacademy.vo.Assignment;
import com.uspaceacademy.vo.FileBoard;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.Teacher;
//mybatis.config.xml에 mapper꼭 등록하기

@Controller
@RequestMapping("/assignment")
public class AssignmentController {


	@Autowired
	private AssignmentService service;
	@Autowired
	private LectureService lectureService;
	
	
	
	//-----------------------------------------------------------------------------------------------------------------------------------------------
	
	//●오류났던거 적기 : 답글게시판으로 변경하는 과정에서 - mapper에 replyGetList -  where절에 ,콤마안찍어줬고, resultMap안적어줬음 그리고 아래 map.put("assignment")로했는데 jsp에서 이름다르게 뿌려줘서 그랬음.
	//과제게시판 - 목록보기(마이페이지에서 - 내강좌 눌렀을때)            ok
		@RequestMapping("/assignment_list.do")
		public ModelAndView list(@RequestParam(defaultValue="1") int page, int lectureNo){  //@RequestParam(defaultValue="1") 디폴트값일때 1을 넣어라
			
			System.out.println("assignment_list강의번호: " + lectureNo);/////////////////////////
			
			Map map = service.selectPagingCount(page,lectureNo);
			map.put("page", page);//
			map.put("lectureNo", lectureNo);
			
			System.out.println("과제게시판ok");
			return new ModelAndView("assignment/assignment_list.tiles", map) ;       
		}
		

		
		


		

		// 과제게시판 - 상세조회(assignment_list.jsp -> assignment_detail.jsp)    
		@RequestMapping("/assignment_detail")
		public ModelAndView detail(String assignmentNo, int lectureNo){ //●오류났던거적기 : 여기서 lectureNo 안넘겨줬었음.
			int num = Integer.parseInt(assignmentNo);
			Assignment assignment = service.selectNo(num,lectureNo);// no값으로 게시물 찾아옴
			
			System.out.println("assignment_detail 강의번호: " + lectureNo);/////////////////////////
			System.out.println("assignment_detail 글번호: " + assignmentNo);/////////////////////////
			System.out.println("assignment_detail num: " + num);/////////////////////////
			service.selectHit(assignment); // 조회수 증가시키기
			System.out.println("service.selectHit(assignment) : " + service.selectHit(assignment));/////////////////////////
			assignment.setLectureNo(lectureNo);
			
			System.out.println("assignment_detail assignment: " + assignment);/////////////////////////
			
			return new ModelAndView("assignment/assignment_detail.tiles", "assignment", assignment);
		}
		//●오류났던거 적기 :  생성자?(detail(){가로안)에 이거 넣어 줘서 HttpSession session, HttpRequest request


		





		
		
		
		
		

		//과제글 작성 폼 (assignment_list.jsp ->assignment_register.jsp)                     //ok
		@RequestMapping("/assignment_register")
		public ModelAndView registerForm(int lectureNo){
			
			System.out.println("과제게시판 작성폼ok");	
			return new ModelAndView("assignment/assignment_register.tiles", "lectureNo",lectureNo);//등록폼으로 온다*
		}
		

		

		
		
		
		
		
		
		
		
		//과제등록할거 작성하고 등록(과제작성완료 눌렀을때)      
		@RequestMapping("/assignment_registerSuccess")//assignment_register.jsp 에서  										//파일                                              ┌파일보드 vo               ModelMap             HttpServlerRequest
		public ModelAndView register(@ModelAttribute("lec") @Valid Assignment assignment, BindingResult errors,    @ModelAttribute FileBoard fileBoard,ModelMap modelMap, HttpServletRequest request) throws IOException{
			
			String fileName = null; //파일
			
			//Date
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sdfDate = sdf.format(date);
			
			//validator
			boolean error = errors.hasErrors();
			int errorCount = errors.getErrorCount();
			if(errors.hasErrors()){
				return new ModelAndView("assignment/assignment_register.tiles");
			}
	
			//파일 																			-실제파일은 uploadFile파일에 저장하고  파일이름을 db에 저장해서 불러옴, 테이블에assignment_file추가 vo추가 mapper추가 등등해줌.
			System.out.println("assignment_registerSuccess,board"+fileBoard);
			
			ArrayList fileNames = new ArrayList();
			
			List upfile = fileBoard.getUpfile();//fileBoard(vo)에 upfile이라는 <List>
			if( upfile != null ){ //null인 경우는 upfile이름으로 넘어온 요청파라미터가 없는 경우.
				
				String saveDir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
				//String saveDir = "C:\\java\\temp"; //파일저장디렉토리
				
				for(Object f : upfile){
					MultipartFile file = (MultipartFile)f; //업로드된 파일 정보 하나씩 조회 -> 이동
					
					if( ! file.isEmpty()){//업로드된 파일이 있으면 -> 이동
						fileName = file.getOriginalFilename(); //업로드된 파일명 조회.
						File dest = new File(saveDir, fileName);
						file.transferTo(dest); //이동처리 
						fileNames.add(fileName); //~~
					}
				}
			}
			modelMap.addAttribute("fileNames",fileNames);             	  //~~
			modelMap.addAttribute("title", fileBoard.getTitle());             //~~
			
			System.out.println("과제글 작성하고 등록ok");
			
			//글번호
			int num = service.selectNextNo();
																																																																																				
			assignment= new Assignment
					(num,assignment.getAssignmentWriterId(),assignment.getAssignmentTitle(),
							assignment.getAssignmentContent(),sdfDate,assignment.getAssignmentHit(),
							assignment.getAssignmentWriter(),assignment.getAssignmentDeadline(),fileName,assignment.getLectureNo()); //fileName->파일이름넘겨줌
			
			assignment.setReplyFamily(num); 
			
			System.out.println(fileName+"파일이름"); //
			
			service.insert(assignment);

		return new ModelAndView("assignment/assignment_detail.tiles","assignment",assignment);
		}
		//Assignment assignment 하면안되고 assignment= 하기 똑같은거1개 이상 x		
		/*	assignment.setReplyStep(num);//??
			assignment.setReplyLevel(num);//??
			└●오류났던거 적기:  강사가 새로 과제글 등록한글에 학생이 답글쓴거 안달림(db에서 확인해보면 replyFamily값이 0으로 박혀서그럼, 새로운글 등록할때(여기)도 replyFamily,replyStep,replyLevel 넘겨줘야됨  -> 해결: assignment.setReplyFamily(num); 해주고 replyStep,replyLevel도 넘겨줘야함*/

		
		
		
		

		
		

		
		
		
		
		//과제출제글 삭제(과제글 상세조회에서 삭제 눌렀을때) 
			@RequestMapping("/assignment_delete")//assignment_detail.jsp 에서  
			public String delete(@RequestParam(defaultValue="1") String type,int assignmentNo,int lectureNo){
					
				service.delete(assignmentNo,lectureNo);     
					
				System.out.println("과제글 삭제 ok");
				
			
			return "/assignment/assignment_list.do?letureNo="+lectureNo;
			}
		
		
		
		
		
		
		
		
		
		
		
		

		//선생님 : 과제글 수정폼(과제글상세조회에서 수정하기 눌렀을때 - modify폼으로감)
		@RequestMapping("/assignment_modifyForm")  
		public ModelAndView modifyForm(int assignmentNo, int lectureNo){  
			Assignment assignment = service.selectNo(assignmentNo,lectureNo);

			Map map = new HashMap();
			map.put("assignment", assignment);//
			
			System.out.println("과제글 수정 폼 ok");
			return new ModelAndView("assignment/assignment_modify.tiles",map); 
		}
		//학생 : 과제글 수정폼(답글 상세조회에서 수정하기 눌렀을때 - modify폼으로감)
				@RequestMapping("/assignment_modifyFormStudent")  
				public ModelAndView modifyFormStudent(int assignmentNo,int lectureNo){  
					Assignment assignment = service.selectNo(assignmentNo,lectureNo);/////////////////////
					
					Map map = new HashMap();
					map.put("assignment", assignment);//
					
					System.out.println("과제글 수정 폼 ok");
					return new ModelAndView("assignment/assignment_modify_student.tiles",map); 
				}


		
		
		
		
		
		
		
		
		
		
		 //강사 : 수정
		@RequestMapping("/assignment_modify") //assignment_modify.jsp에서 (강사가)수정완료 눌렀을때																  //파일
		public ModelAndView modify(@ModelAttribute("lec") @Valid Assignment assignment,BindingResult errors, HttpSession session, @ModelAttribute com.uspaceacademy.vo.FileBoard fileBoard,ModelMap modelMap, HttpServletRequest request) throws IOException{
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			
			String fileName = null; //파일
			
			//validator
			boolean error = errors.hasErrors();
			int errorCount = errors.getErrorCount();
			if(errors.hasErrors()){
			return new ModelAndView("assignment/assignment_modify.tiles");
			}
			
			//파일 																			-실제파일은 uploadFile파일에 저장하고  파일이름을 db에 저장해서 불러옴, 테이블에assignment_file추가 vo추가 mapper추가 등등해줌.
			System.out.println("assignment_modify,board"+fileBoard);
			
			ArrayList fileNames = new ArrayList(); //~~
			
			List upfile = fileBoard.getUpfile();//fileBoard(vo)에 upfile이라는 <List>
			if( upfile != null ){ //null인 경우는 upfile이름으로 넘어온 요청파라미터가 없는 경우.
				
				String saveDir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
				//String saveDir = "C:\\java\\temp"; //파일저장디렉토리
				
				for(Object f : upfile){
					MultipartFile file = (MultipartFile)f; //업로드된 파일 정보 하나씩 조회 -> 이동
					
					if( ! file.isEmpty()){//업로드된 파일이 있으면 -> 이동
						fileName = file.getOriginalFilename(); //업로드된 파일명 조회.
						File dest = new File(saveDir, fileName);
						file.transferTo(dest); //이동처리 
						fileNames.add(fileName); //~~
					}
				}
			}
			modelMap.addAttribute("fileNames",fileNames);             	  //~~
			modelMap.addAttribute("title", fileBoard.getTitle());             //~~

			Teacher t = (Teacher)session.getAttribute("login_info");
			String teacher = t.getTeacherName(); //오류났던거 적기 : getAssignmentWriter안돼서 session에서 getTeacherName가져옴.
			String teacherId = t.getTeacherId();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sdfDate = sdf.format(date);
			
			//
			assignment= new Assignment(assignment.getAssignmentNo(),
													teacherId,
													assignment.getAssignmentTitle(),
													assignment.getAssignmentContent(),
													sdfDate,
													assignment.getAssignmentHit(),
													assignment.getReplyFamily(),
													assignment.getReplyStep(),
													assignment.getReplyLevel(),
													teacher,
													assignment.getAssignmentDeadline(),
													fileName,
													assignment.getLectureNo()); 
			service.update(assignment);
		
			System.out.println("과제글 수정 ok");
			return new ModelAndView("assignment/assignment_detail.tiles","assignment",assignment); //과제글 수정완료 버튼누르면 내가 수정한내용 detail에서 보여짐
		}

		//학생 : 수정
		@RequestMapping("/assignment_modify_student") //assignment_modify.jsp에서 (강사가)수정완료 눌렀을때																  //파일
		public ModelAndView modifyStudent(@ModelAttribute("lec") @Valid Assignment assignment,BindingResult errors, HttpSession session, @ModelAttribute com.uspaceacademy.vo.FileBoard fileBoard,ModelMap modelMap, HttpServletRequest request) throws IOException{
			
			HashMap<String,Object> map = new HashMap<String,Object>();
			
			String fileName = null; //파일
			
			//validator
			boolean error = errors.hasErrors();
			int errorCount = errors.getErrorCount();
			if(errors.hasErrors()){
			return new ModelAndView("assignment/assignment_modify_student.tiles");
			}
			
			//파일 																			-실제파일은 uploadFile파일에 저장하고  파일이름을 db에 저장해서 불러옴, 테이블에assignment_file추가 vo추가 mapper추가 등등해줌.
			System.out.println("assignment_modify,board"+fileBoard);
			
			ArrayList fileNames = new ArrayList(); //~~
			
			List upfile = fileBoard.getUpfile();//fileBoard(vo)에 upfile이라는 <List>
			if( upfile != null ){ //null인 경우는 upfile이름으로 넘어온 요청파라미터가 없는 경우.
				
				String saveDir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
				//String saveDir = "C:\\java\\temp"; //파일저장디렉토리
				
				for(Object f : upfile){
					MultipartFile file = (MultipartFile)f; //업로드된 파일 정보 하나씩 조회 -> 이동
					
					if( ! file.isEmpty()){//업로드된 파일이 있으면 -> 이동
						fileName = file.getOriginalFilename(); //업로드된 파일명 조회.
						File dest = new File(saveDir, fileName);
						file.transferTo(dest); //이동처리 
						fileNames.add(fileName); //~~
					}
				}
			}
			modelMap.addAttribute("fileNames",fileNames);             	  //~~
			modelMap.addAttribute("title", fileBoard.getTitle());             //~~
			//
			Student s = (Student)session.getAttribute("login_info");
			String student = s.getStudentName(); //오류났던거 적기 : getAssignmentWriter안돼서 session에서 getTeacherName가져옴.
			String studentId = s.getStudentId();
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			String sdfDate = sdf.format(date);
			
		    //
			assignment= new Assignment(assignment.getAssignmentNo(),
													studentId,
													assignment.getAssignmentTitle(),
													assignment.getAssignmentContent(),
													sdfDate,
													assignment.getAssignmentHit(),
													assignment.getReplyFamily(),
													assignment.getReplyStep(),
													assignment.getReplyLevel(),
													student,
													assignment.getAssignmentDeadline(),
													fileName,
													assignment.getLectureNo());
			service.update(assignment);
			
			System.out.println("과제글 수정 ok");
			return new ModelAndView("assignment/assignment_detail.tiles","assignment",assignment); //과제글 수정완료 버튼누르면 내가 수정한내용 detail에서 보여짐
		}

		
		
		
		
		
		
		
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		//아래 학생 답글-----------------------------------------------------------------------------------------------------------------
		
			//답글 작성 폼 (assignment_detail.jsp ->assignment_replyRegister.jsp)글상세페이지에서 답변하기버튼클릭 -> 답변폼으로 온다
			@RequestMapping("/assignment_replyRegister")
			public ModelAndView replyRegister(int assignmentNo,int lectureNo){
				
				Map map = new HashMap();
				Assignment assignment = service.selectNo(assignmentNo,lectureNo);
				map.put("assignment",assignment);
				
				System.out.println("답글 작성폼 ok");	
				return new ModelAndView("assignment/assignment_replyRegister.tiles",map);
			}
			//●오류났던거 적기 : 여기에서도 정보 넘겨줘야함(여기에 아무것도 안적어 줬었음), 새글+수정 합친 개념 - 글번호랑 다른것들 가져와야됨 - 가져와서 뿌려주고 이거 같이 넘겨야됨	
		
		
		
			
			
			
			
			
			
			
		
		
		
			//답변폼에서   ->  답글등록할거 작성하고 답글등록(답글작성완료 눌렀을때) assignment_replyRegister.jsp 에서 assignment_list.jsp로감
			@RequestMapping("/assignment_replyRegisterSuccess")																							      //┌파일																							
			public ModelAndView reply(@ModelAttribute("lec") @Valid Assignment assignment, int lectureNo,BindingResult errors,   HttpSession session, @ModelAttribute com.uspaceacademy.vo.FileBoard fileBoard,ModelMap modelMap, HttpServletRequest request) throws IOException{
				
				HashMap<String, Object> map = new HashMap<String, Object>();
				
				String fileName = null;//파일!
				
				//validator
				boolean error = errors.hasErrors();
				int errorCount = errors.getErrorCount();
				if(errors.hasErrors()){	
					return new ModelAndView("assignment/assignment_replyRegister.tiles");
				}
				
				//파일!
				ArrayList fileNames = new ArrayList();
				List upfile = fileBoard.getUpfile(); //fileBoard(vo)에 upfile이라는 <List>
				if( upfile != null ){//null인 경우는 upfile이름으로 넘어온 요청파라미터가 없는 경우.
					String saveDir = request.getServletContext().getRealPath("/uploadFile"); //파일저장디렉토리
					for(Object f : upfile){
						MultipartFile file = (MultipartFile)f; //업로드된 파일 정보 하나씩 조회 -> 이동
						
						if( ! file.isEmpty()){//업로드된 파일이 있으면 -> 이동
							fileName = file.getOriginalFilename(); //업로드된 파일명 조회.
							File dest = new File(saveDir, fileName);
							file.transferTo(dest); //이동처리 
							fileNames.add(fileName); //~~
						}
					}
				}
				modelMap.addAttribute("fileNames",fileNames);             	  //~~
				modelMap.addAttribute("title", fileBoard.getTitle());             //~~


				//로그인한 학생아이디에 학생이름
				Student s = (Student)session.getAttribute("login_info");//
				String student = s.getStudentName();//
				//Date
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				Date date = new Date();
				String sdfDate = sdf.format(date);
				//글번호
				int num = service.selectNextNo();
				//필요한것만 받아서 넘겨주기
				assignment.setAssignmentNo(num);
				assignment.setAssignmentDate(sdfDate);
				assignment.setAssignmentWriter(student);
				assignment.setReplyLevel(+1); //레벨이 +1되어야 들여쓰기 하는데 안돼서 해줌.
				
				assignment.setAssignmentFile(fileName); //파일!
				
				assignment.setLectureNo(lectureNo);
				
				service.replyReplyReplyAddStep(assignment);

				System.out.println("답글  작성하고 등록ok");
				return new ModelAndView("assignment/assignment_detail.tiles","assignment", assignment); //답글작성완료하면 상세페이지로 돌아가기*
			}
			//=잘못된것 : 아래꺼 지워주기 어짜피 위에서Assignment assignment, 넘겨받기때문에 다시 new생성해서 보낼필요없음
			//=●오류났던거 적기 : 답글로 등록되야 하는데 새글로등록됨 ->해결: no값 그대로 넘겨야 하는데 new생성해서 num값 넣어줘서 새글이 되니까 답글이 안달렸던것.
			//=assignment= new Assignment(num,0,assignment.getAssignmentTitle(),assignment.getAssignmentContent(),sdfDate,assignment.getAssignmentHit(),0,assignment.getReplyFamily(),assignment.getReplyStep(),assignment.getReplyLevel(),student,assignment.getAssignmentDeadline(),5);
			//>●오류났던거 적기 :  답글등록하고 상세페이지뿌려줘야하는데 안나왔었음 -> 해결 : service에 replyReplyReplyAddStep메서드 내가 map에 넣어서 리턴! 해줬었는데 - 이렇게하면 assignment가 넘어가는게 아니라 숫자1이 넘어감 -> 해결 : replyReplyReplyAddStep메서드 void 로 바꿔주고 리턴없앰, 여기 컨트롤러에서도 맵지워주고 "assignment"로 넘겨줌.
			

			
		//위에 학생 답글-----------------------------------------------------------------------------------------------------------------
		
		
			
			//파일-다운로드처리
			@RequestMapping("/download")
			public ModelAndView download(@RequestParam String fileName){
			 /*
			 * 	 View-Name : downloadView
			 * 	 Model(View에 전달할 값) : "downFile" - 파일명 downFile-abc.txt
			 */

			return new ModelAndView("downloadView", "downFile", fileName);
			}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		// 아래 부터 파일-----------------------------------------------------------------------------------------------------------------------------------------------
		
		
		
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














