package com.uspaceacademy.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.uspaceacademy.service.LectureService;
import com.uspaceacademy.service.MemberService;
import com.uspaceacademy.validaotor.LectureValidator;
import com.uspaceacademy.vo.Lecture;
import com.uspaceacademy.vo.Student;
import com.uspaceacademy.vo.StudentLectureJoin;
import com.uspaceacademy.vo.Teacher;

@Controller
@RequestMapping("/lecture")
public class LectureController {
	
	@Autowired
	private LectureService lectureService;
	
	@Autowired
	private MemberService memberService;
	
	//강의 목록 전체조회(페이징)
	@RequestMapping("/lectureList.do")
	public ModelAndView getLectureList(@RequestParam(defaultValue="1") int page){
		//원래 방식
		//List lectureList = lectureService.getLectureList();
		Map map = lectureService.getLectureList(page);
		List codeList = lectureService.searchCode("teacherSubject");
		map.put("page", page);
		map.put("codeList", codeList); 
		return new ModelAndView("lecture/lecture_list.tiles", map);
	}
	
	//수강번호로 강의 조회(상세보기)
	@RequestMapping("/findLectureByNo.do")
	@ResponseBody
	public List getLectureByNo(int lectureNo, HttpSession session){
		Lecture lecture = lectureService.getLectureByNo(lectureNo);
		String memberType = (String) session.getAttribute("memberType");
		ArrayList list = new ArrayList();
		list.add(lecture);
		list.add(memberType);
		return list;
	}
	//관리자가 강의를 등록하기 폼을 조회하기 위한 컨트롤러
	@RequestMapping("/registerForm.do")
	public ModelAndView registerLecture(String codeType){
		List codeList = lectureService.searchCode(codeType);
		List teacherList = memberService.getTeacherBySubject("국어");//default값인 국어선생님들을 옵션으로 뿌려줌
		Map map = new HashMap();
		map.put("codeList", codeList);
		map.put("teacherList", teacherList);
		return new ModelAndView("lecture/register_form.tiles", map);
	}
	
	//과목명으로 선생들을 조회해옴
	@RequestMapping("/getTeacherBySubject.do")
	@ResponseBody
	public List getTeacherBySubject(String teacherSubject){
		System.out.println("대영"+teacherSubject);
		List teacherList = memberService.getTeacherBySubject(teacherSubject);
		//System.out.println(teacherList);
		//return new ModelAndView("lecture/register_form.tiles","teacherList", teacherList);
		return teacherList;
	}
	
	
	//강의등록폼에서 입력한 정보를 토대로 강의를 등록해줌
		@RequestMapping("/registerLecture.do")
		public String registerLecture(@ModelAttribute @Valid Lecture lecture, BindingResult errors, @RequestParam(defaultValue="") String[] lectureDay2){
			if(lectureDay2.length==0){
				return "/lecture/registerForm.do?codeType=teacherSubject&errorMessage=choose_Lecture_day";
			}
			
			String lectureDay="";
			for(String s : lectureDay2){
				System.out.println(s);
				lectureDay +=s;
			}
			System.out.println(lecture.getLectureStartDate()+ " " + lecture.getLectureEndDate());
			
			LectureValidator validator = new LectureValidator();
			validator.validate(lecture, errors);
			boolean error = errors.hasErrors();
			int errorCount = errors.getErrorCount();
			
			if(errors.hasErrors()){
				return "/lecture/registerForm.do?codeType=teacherSubject";
			}
			int lectureNo = -1;//어차피 sequence를 사용할 것이므로 -1 세팅
			int lectureCurrentStudent = 0; // 초기 학생은 0명
			
			
			/*Lecture lecture = new Lecture(lectureNo, lectureTitle, lectureDescription, lectureStartTime, 
					lectureEndTime, lectureDay, lectureStartDate, lectureEndDate, lecturePrice, 
					lectureTotalStudent, lectureCurrentStudent, lectureSubject, teacherId2);*/
	
			lecture.setLectureDay(lectureDay);
			lecture.setLectureNo(lectureNo);
			lecture.setLectureCurrentStudent(lectureCurrentStudent);
			int flag = lectureService.registerLecture(lecture);
			System.out.println(flag+"개의 강의가 등록되었습니다 - 대영-");
			return "redirect:/lecture/lectureRegisterRedirect.do";
		}
	
	//강의등록폼에서 리다이렉트로 모델을 부르는 핸들러를 redirect방식으로 요청하는 중간 연결자
	@RequestMapping("/lectureRegisterRedirect")
	public ModelAndView	registerRedirect(@RequestParam(defaultValue="1") int page){
		Map map = lectureService.getLectureList(page);
		return new ModelAndView("lecture/lecture_list.tiles", map);
		
	}
	
	
	//수정 폼을 불러오기 위한 핸들러 
	@RequestMapping("/getModifyForm.do")
	public ModelAndView getLectureModifyForm(int lectureNo, String codeType){
		Lecture lecture = lectureService.getLectureByNo(lectureNo);
		List codeList = lectureService.searchCode(codeType);
		List teacherList = memberService.getTeacherBySubject(lecture.getLectureSubject());
		String lectureDay = lecture.getLectureDay();
		List lectureDayList = new ArrayList();
		for(int i=0;i<lectureDay.length();i++){
			lectureDayList.add((lectureDay.charAt(i)+""));
		}
		
		Map map = new HashMap();
		map.put("lecture", lecture);
		map.put("codeList", codeList);
		map.put("teacherList", teacherList);
		map.put("lectureDayList", lectureDayList);
		return new ModelAndView("lecture/modify_form.tiles",map);
	}
	
	//강의를 수정하기 위한 컨트롤러
	@RequestMapping("/modifyLectureByNo")
	public ModelAndView modifyLectureByNo(@RequestParam(defaultValue="1") int page, @ModelAttribute("lectureForm") @Valid Lecture lecture, BindingResult errors, @RequestParam(defaultValue="") String[] lectureDay2){
		if(lectureDay2.length==0){
			return new ModelAndView("/lecture/getModifyForm.do?codeType=teacherSubject&errorMessage=choose_Lecture_day&lectureNo="+lecture.getLectureNo());
		}
		
		String lectureDay = "";
		for(String s : lectureDay2){
			lectureDay +=s;
		}
		
		if(errors.hasErrors()){
			//오류가 발생할 경우
			System.out.println("에러개수 : "+errors.getErrorCount());
			return new ModelAndView("/lecture/getModifyForm.do?codeType=teacherSubject&lectureNo="+lecture.getLectureNo());
		}
		
		int lectureCurrentStudent = 0;
		//업데이트할 새로운 강의 정보 등록
		lecture.setLectureDay(lectureDay);
		lecture.setLectureCurrentStudent(lectureCurrentStudent);
		
		int flag = lectureService.modifyLectureByNo(lecture);
		System.out.println(flag+"개의 강의가 수정되었습니다 - 대영쓰 - ");
		//새로운 강의 리스트 조회
		Map map = lectureService.getLectureList(page);
		return new ModelAndView("lecture/lecture_list.tiles", map);
	}
	
	//강의를 삭제하기 위한 컨트롤러
	@RequestMapping("/removeLectureByNo.do")
	public ModelAndView removeLectureByNo(int lectureNo, @RequestParam(defaultValue="1") int page){
		//삭제하는 서비스 부름
		int flag = lectureService.removeLectureByNo(lectureNo);
		//새로운 강의 리스트 조회
		Map map = lectureService.getLectureList(page);
		return new ModelAndView("lecture/lecture_list.tiles", map);
	}
	
	//결제하기 폼을 조회하기 위한 컨트롤러
	@RequestMapping("/applyLectureByNo.do")
	public ModelAndView getChargeForm(@RequestParam(defaultValue="1") int page, int lectureNo){
		
		Lecture lecture = lectureService.getLectureByNo(lectureNo);
		if(lecture.getLectureCurrentStudent()>=lecture.getLectureTotalStudent()){
			//현재수강인원이 가득 찬 경우
			String errorMessage = "수강 인원이 가득 찼습니다!!";
			return new ModelAndView("/lecture/lectureList.do?page="+page,"errorMessage", errorMessage);
		}
		Teacher teacher = memberService.findTeacherById(lecture.getTeacherId2());
		Map map = new HashMap();
		map.put("lecture", lecture);
		map.put("teacher", teacher);
		map.put("page", page);
		return new ModelAndView("lecture/charge_form.tiles", map);
	}
	
	//결제를 하기 위한 컨트롤러
	@RequestMapping("/chargeLecture.do")
	public String chargeLecture(@RequestParam(defaultValue="1") int page, int lectureNo, HttpSession session){
		//결제처리 - 조인 테이블에 넣어주기
		Student student = (Student) session.getAttribute("login_info");
		
		//이미 결제목록이나 찜 목록에 있는 것인가
		
		StudentLectureJoin studentLectureJoin = lectureService.getOneStudentLectureJoin(student.getStudentId(), lectureNo, "0");
		StudentLectureJoin studentLectureJoin2 = lectureService.getOneStudentLectureJoin(student.getStudentId(), lectureNo, "1");
		
		if(studentLectureJoin!=null){
			//1. 결제목록에 있는 것인가
			System.out.println("이미 결제목록에 있는 것을 결제하려고함 -대영-");
			return "/lecture/applyLectureByNo.do?page="+page+"&errorMessage=이미 결제목록에 있는 대상입니다&lectureNo="+lectureNo;
		}else if(studentLectureJoin2!=null){
			//2. 찜목록에 있는 것인가
			System.out.println("이미 찜목록에 있는 것을 결제하려고함 -대영-");
			String zzimOption = "0";
			try {
				lectureService.chargeFromZzimList(student.getStudentId(), lectureNo, zzimOption);
			} catch (Exception e) {
				return ":/lecture/applyList.do?errorMessage="+e.getMessage()+"&page="+page;
			}
			return "redirect:/lecture/applyList.do?page="+page;
		
		}else{
			//3. 찜목록에도 없고 결제목록에도 없는 새로운 강의를 결제하는 경우
			//찜 여부는 결제를 직접하므로 false("0")로들어감.
			int flag = lectureService.chargeLecture(student.getStudentId(), lectureNo, "0");
			System.out.println(flag+"개의 강의를 결제하였습니다 - 대영 -");
			return "redirect:/lecture/applyList.do?page="+page;
		}
	}
	
	//리다이렉트로 받아서 결제리스트를 실제로 보여주는 컨트롤러
	@RequestMapping("/applyList.do")
	public ModelAndView applyList(@RequestParam(defaultValue="1") int page, HttpSession session){
		Student student = (Student) session.getAttribute("login_info");
		String zzimOption = "0";//진짜 수강신청한 목록
		List studentLectureJoinList = lectureService.getStudentLectureJoinList(student.getStudentId(),zzimOption);
		List applyList = new ArrayList();
		for(Object o : studentLectureJoinList){
			applyList.add(lectureService.getLectureByNo(((StudentLectureJoin)o).getLectureNo3()));
		}
		Map map = new HashMap();
		map.put("applyList", applyList);
		map.put("page", page);
		return new ModelAndView("lecture/apply_list.tiles", map);
	}
	
	
	
	//찜하기 폼을 조회하기 위한 컨트롤러
		@RequestMapping("/zzimLectureByNo.do")
		public ModelAndView getZzimForm(@RequestParam(defaultValue="1") int page, int lectureNo){
			Lecture lecture = lectureService.getLectureByNo(lectureNo);
			Teacher teacher = memberService.findTeacherById(lecture.getTeacherId2());
			Map map = new HashMap();
			map.put("lecture", lecture);
			map.put("teacher", teacher);
			map.put("page", page);
			return new ModelAndView("lecture/zzim_form.tiles", map);
		}
	
	
	//찜을 하기 위한 컨트롤러
	@RequestMapping("/zzimLecture.do")
	@Transactional(rollbackFor=Exception.class)
	public String zzimLecture(@RequestParam(defaultValue="1") int page, int lectureNo, HttpSession session){
		Student student = (Student) session.getAttribute("login_info");
		
	
		StudentLectureJoin studentLectureJoin = lectureService.getOneStudentLectureJoin(student.getStudentId(), lectureNo, "0");
		StudentLectureJoin studentLectureJoin2 = lectureService.getOneStudentLectureJoin(student.getStudentId(), lectureNo, "1");
		if(studentLectureJoin!=null){
			//1. 결제목록에 있는 것인가
			System.out.println("이미 결제목록에 있는 것을 찜하려고함 -대영-");
			System.out.println(studentLectureJoin);
			return "/lecture/zzimLectureByNo.do?page="+page+"&errorMessage=이미 결제목록에 있는 대상입니다&lectureNo="+lectureNo;
		}else if(studentLectureJoin2!=null){
			//2. 찜목록에 있는 것인가
			System.out.println("이미 찜목록에 있는 것을 찜하려고함 -대영-");
			System.out.println(studentLectureJoin2);
			return "/lecture/zzimLectureByNo.do?page="+page+"&errorMessage=이미 찜 목록에 있는 대상입니다&lectureNo="+lectureNo;
		
		}else{
			
			//찜 여부는 결제를 직접하므로 true("1")로들어감.
			int flag = lectureService.chargeLecture(student.getStudentId(), lectureNo, "1");
			System.out.println(flag+"개의 강의를 찜하였습니다 - 대영 -");
			//List lectureList = lectureService.getLectureList();
			
			return "redirect:/lecture/zzimList.do?page="+page;
		}
	}
	
	//리다이렉트로 받아서 찜 리스트를 실제로 보여주는 컨트롤러
	@RequestMapping("/zzimList.do")
	public ModelAndView zzimList(@RequestParam(defaultValue="1") int page, HttpSession session, String errorMessage){
		Student student = (Student) session.getAttribute("login_info");
		String zzimOption = "1";//찜한 목록조회
		List studentLectureJoinList = lectureService.getStudentLectureJoinList(student.getStudentId(),zzimOption);
		List zzimList = new ArrayList();
		for(Object o : studentLectureJoinList){
			zzimList.add(lectureService.getLectureByNo(((StudentLectureJoin)o).getLectureNo3()));
		}
		Map map = new HashMap();
		map.put("zzimList", zzimList);
		map.put("page", page);
		map.put("errorMessage", errorMessage);
		return new ModelAndView("lecture/zzim_list.tiles", map);
	
	}
	
	//찜 목록에서 체크박스 한 리스트 결제하기
	@RequestMapping("/applyFromZzimList.do")
	public String applyFromZzimList(@RequestParam(defaultValue="1") int page, @RequestParam List applyList, HttpSession session){
		Student student = (Student)session.getAttribute("login_info");
		if(applyList==null||applyList.isEmpty()){
			//체크한 목록이 없는 경우
			return "/lecture/zzimList.do?page="+page;
		}else{
			//체크한 목록이 있는 경우
			List tmpList = new ArrayList();
			for(Object o : applyList){
				int lectureNo = Integer.parseInt(o+"");
				try {
					lectureService.chargeFromZzimList(student.getStudentId(), lectureNo, "0");
				} catch (Exception e) {
					return "/lecture/zzimList.do?errorMessage="+e.getMessage()+"&page="+page;
				}
			}
			return "redirect:/lecture/zzimList.do?page="+page;
		}
	}
	
	//키워드로 강의리스트 가져오기(강의가 시작 되면 강의전체목록을 볼 수 없음...(학생, 강사))
	@RequestMapping("/searchLectureByKeyword.do")
	public ModelAndView searchLectureBy(@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="") String searchType, @RequestParam(defaultValue="") String keyword){
		Map map = new HashMap();
		if(searchType.equals("lectureTitle")){
		   map = lectureService.getLectureByTitle(keyword, page);
		   map.put("searchType", searchType);
		   map.put("keyword", keyword);
		   System.out.println("-----------------------제목일경우 ㄷㅇ컨트롤러1 searchType:" + searchType);
			System.out.println("-----------------------제목일경우 ㄷㅇ컨트롤러1 keyword:" + keyword);
		   //if(((List)map.get("lectureList")).size()==0){
		   //map = lectureService.getLectureList(page);
			 //  map.put("searchType", searchType);
		  // map.put("keyword", keyword);
		   //}
		}else if(searchType.equals("teacherSubject")){
			map = lectureService.getLectureByLectureSubject(keyword, page);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
			System.out.println("-----------------------과목일경우 ㄷㅇ컨트롤러2 searchType:" + searchType);
			System.out.println("-----------------------과목일경우 ㄷㅇ컨트롤러2 keyword:" + keyword);
		}else if(searchType.equals("teacherName")){
			map = lectureService.getLectureByTeacherName(keyword, page);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
		}else{
			map = lectureService.getLectureList(page);
			List codeList = lectureService.searchCode("teacherSubject");
			map.put("page", page);
			map.put("codeList", codeList); 
			map.put("searchType", searchType);
			map.put("keyword", keyword);
			System.out.println("------------------------ㄷㅇ컨트롤러4 page:" + page);
			System.out.println("------------------------ㄷㅇ컨트롤러4 codeList:" + codeList);
			System.out.println("------------------------ㄷㅇ컨트롤러4 searchType:" + searchType);
			System.out.println("------------------------ㄷㅇ컨트롤러4 keyword:" + keyword);
			
		}
		return new ModelAndView("lecture/lecture_list.tiles", map);
	}
	//강사ID를 강사이름으로 변경해서 보여주는 ajax요청을 받음
	@RequestMapping("/convertTeacherIdToTeacherName.do")
	@ResponseBody
	public List	converTeacherIdToTeacherName(@RequestParam(required=false) String teacherIds){
		List teacherNameList = new ArrayList();
		System.out.println(teacherIds);
		if(teacherIds!=null&& (!teacherIds.trim().isEmpty())){
		String[] teacherIdArray = teacherIds.split(",");
			for(int i=0;i<teacherIdArray.length;i++){
				String teacherName = memberService.findTeacherById(teacherIdArray[i]).getTeacherName();
				teacherNameList.add(teacherName);
			}
		}
		return teacherNameList;
	}
	
	//학생 회원이 자신이 결제한 강의를 결제취소하기 위한 핸들러(결제목록에서 삭제), 해당 강의의 현재 학생수 -1
	@RequestMapping("/removeLectureFromApplyList.do")
	public String removeLectureFromApplyList(@RequestParam(defaultValue="1") int page, int lectureNo, HttpSession session){
		Student student = (Student) session.getAttribute("login_info");
		System.out.println(lectureService.removeLectureFromApplyListByLectureNo(student.getStudentId(),lectureNo)+"개의 강의 삭제");
		return "/lecture/applyList.do?page="+page;
	}
	
	// 관리자가 강의가 시작되고 종료된 강의에 대해서 관리하기 위해서....
	@RequestMapping("lectureAll.do")
	public ModelAndView adminLectureCheck(@RequestParam(defaultValue="1") int page) {
		Map map = new HashMap<>();
		map = lectureService.selectAdminLectureService();
//		System.out.println(map);
		return new ModelAndView("lecture/admin_lectureList.tiles", map);
	}
	
	
	//키워드로 강의리스트 가져오기(강의가 시작 되면 강의전체목록을 볼 수 없음...(관리자))
	@RequestMapping("/searchLectureByKeywordA.do")
	public ModelAndView searchLectureByA(@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="") String searchType, @RequestParam(defaultValue="") String keyword){
		Map map = new HashMap();
		if(searchType.equals("lectureTitle")){
		   map = lectureService.getLectureByTitleA(keyword, page);
		   map.put("searchType", searchType);
		   map.put("keyword", keyword);
		   System.out.println("-----------------------제목일경우 ㄷㅇ컨트롤러1 searchType:" + searchType);
			System.out.println("-----------------------제목일경우 ㄷㅇ컨트롤러1 keyword:" + keyword);
		   //if(((List)map.get("lectureList")).size()==0){
		   //map = lectureService.getLectureList(page);
			 //  map.put("searchType", searchType);
		  // map.put("keyword", keyword);
		   //}
		}else if(searchType.equals("teacherSubject")){
			map = lectureService.getLectureByLectureSubjectA(keyword, page);
			System.out.println("컨트롤러 - "+ map);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
			System.out.println("-----------------------과목일경우 ㄷㅇ컨트롤러2 searchType:" + searchType);
			System.out.println("-----------------------과목일경우 ㄷㅇ컨트롤러2 keyword:" + keyword);
		}else if(searchType.equals("teacherName")){
			map = lectureService.getLectureByTeacherNameA(keyword, page);
			map.put("searchType", searchType);
			map.put("keyword", keyword);
		}else{
			map = lectureService.getLectureList(page);
			List codeList = lectureService.searchCode("teacherSubject");
			map.put("page", page);
			map.put("codeList", codeList); 
			map.put("searchType", searchType);
			map.put("keyword", keyword);
			System.out.println("------------------------ㄷㅇ컨트롤러4 page:" + page);
			System.out.println("------------------------ㄷㅇ컨트롤러4 codeList:" + codeList);
			System.out.println("------------------------ㄷㅇ컨트롤러4 searchType:" + searchType);
			System.out.println("------------------------ㄷㅇ컨트롤러4 keyword:" + keyword);
			
		}
		return new ModelAndView("lecture/lecture_list.tiles", map);
	}	
}