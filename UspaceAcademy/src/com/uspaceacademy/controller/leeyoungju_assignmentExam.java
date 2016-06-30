/*package com.uspaceacademy.controller;

public class leeyoungju_assignmentExam {

	

   //http://hongkwan.blogspot.kr/2013/07/130724-23-webxml-13-spring.html
   

 
 
 컨트롤러 작성 -> 
 <! - BoardWriteController - 삽입 실행 ->
  <콩 
  
 
 
 
 ID = "boardwriteproccontroller"이름 = "/BoardWriteProcController.do" 클래스 = "controller.BoardWriteProcController" > 
 </ 빈> 
 
 <! - BoardList - 선택 * 실행 ->
  <콩 ID = "boardlistcontroller" 
  
 
 
 
 BoardInfo - 선택 실행 ->
  <콩 ID = "boardinfocontroller" 
  
 
 
 
 게시글 수정 - 업데이트 실행 ->
  <콩 ID = "boardupdatecontroller" 
  
 
 
 
 게시글 삭제 - 삭제를 실행 ->
  <콩 ID = "boarddeletecontroller" 
  
 
 
 
 ID = "boardDao" 클래스 = "board.BoardDao"
   P : dataSource에-REF = "은 dataSource"
  > 
 </ 빈> 
 
 <! - 컨트롤러 실행 후 결과를 초래 할 JSP 페이지를 설정 ->
  <콩 ID = "internalResolver " 
    클래스 ="org.springframework.web.servlet.view.InternalResourceViewResolver "> 
    <속성 이름 ="viewClass "> 
     <값> org.springframework.web.servlet.view.JstlView </ 값>     
    </ 속성> 
    <속성 이름 = "접두사"> 
     <값> / JSP / </ 값> 
    </ 속성> 
    <속성 이름 = "접미사"> 
     <값> .jsp로 </ 값> 
    </ 속성> 
 </ 빈> 
 
 <! - 데이터베이스 객체를 사용할 수있게 설정 해주는 데이터 소스 선언 ->
  <콩 ID = "dataSource에" 
    클래스 = "org.springframework.jdbc.datasource.DriverManagerDataSource"> 
    <속성 이름 = "driverClassName"> 
     <값> oracle.jdbc.driver. OracleDriver </ 값> 
    </ 속성> 
    <속성 이름 = "URL"> 
     <값> JDBC : 신탁 : 얇은 : @ 127.0.0.1 : 1521 : XE </ 값> 
    </ 속성> 
    <속성 이름 = "사용자 이름"> 
     <값> 시스템 </ 값> 
    </ 속성> 
    <속성 이름 = "암호"> 
     <값> 123456 </ 값> 
    </ 속성> 
    
 </ 빈>  
</ 콩> 


 - /src/board/BoardBean.java 소스
패키지 기판;

가져 오기 java.util.Date; 

공용 클래스 BoardBean { 
 개인 INT의 납입, 
 개인 문자열 작가, 
 개인 문자열 passwd를, 
 개인 문자열 주제; 
 개인 문자열 내용; 
 개인 문자열 이메일; 
 개인 INT 심판, 
 개인 INT의 re_level, 
 개인 INT의 re_step; 
 개인 날짜 reg_date, 
 개인 INT의 readcount, 
 
 공공 INT getNum () { 
  반환 납입; 
 } 
 공공 무효 setNum (INT의 납입) { 
  this.num = NUM; 
 } 
 공공 문자열로 getWriter () { 
  반환 작가; 
 } 
 공공 무효 setWriter를 (문자열 작가) { 
  this.writer = 작가; 
 } 
 공공 문자열 getPasswd () { 
  반환 passwd를; 
 } 
 공공 무효 setPasswd (문자열 passwd를) { 
  this.passwd = passwd를; 
 } 
 공공 문자열의 getSubject () { 
  반환 대상; 
 } 
 공공 무효의 setSubject (문자열 과목) { 
  this.subject = 제목; 
 } 
 공공 문자열 getContents () { 
  내용을 반환; 
 } 
 공공 무효 setContents (문자열 내용을) { 
  this.contents의 = 내용; 
 } 
 공공 문자열 가령 getMail () { 
  반환 이메일; 
 } 
 공공 무효의 setEmail (문자열 이메일) { 
  this.email = 이메일; 
 } 
 공공 INT getRef () { 
  심판을 반환; 
 } 
 공공 무효 setRef (INT 심판) { 
  this.ref = 심판; 
 } 
 공공 INT getRe_level () { 
  반환 re_level을; 
 } 
 공공 무효 setRe_level (INT의 re_level를) { 
  this.re_level = re_level; 
 } 
 공공 INT getRe_step () { 
  반환 re_step; 
 } 
 공공 무효 setRe_step (INT의 re_step) { 
  this.re_step = re_step; 
 } 
 공공 날짜 getReg_date () { 
  반환 reg_date; 
 } 
 공공 무효 setReg_date (날짜 reg_date) { 
  this.reg_date = reg_date; 
 } 
 공공 INT의 getReadcount () { 
  반환 readcount; 
 } 
 공공 무효 setReadcount (INT readcount) { 
  this.readcount = readcount; 
 } 

}


 - /src/board/BoardDao.java 소스 (중요)
패키지 기판;

가져 오기를 java.util.List;
수입 java.util.Vector에;

가져 오기는 javax.sql.DataSource;

수입 org.springframework.jdbc.core.BeanPropertyRowMapper;
수입 org.springframework.jdbc.core.RowMapper;
수입 org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
수입 org.springframework.jdbc.core.namedparam.SqlParameterSource;
수입 org.springframework.jdbc.core.simple.SimpleJdbcTemplate;

공용 클래스 BoardDao {


 // 문 객체와 유사한 쿼리를 실행시켜주는 객체
 인 SimpleJdbcTemplate 템플릿;

 // 발판-servlet.xml 파일 파일에서 MemberDao가 데이터 소스 객체를 참조하게 선언
 // 자동 매핑을 위해서 위 방식보다는 아래 방식을 추천 (위 방식은 직접받는 방식, 아래 방식은 인터페이스를 사용한 방식)
 데이터 원본은 dataSource;

 공공 무효 setDataSource (데이터 소스 데이터 소스) {
  템플릿 = 새 인 SimpleJdbcTemplate (은 dataSource);
 }

 // 1. 하나의 게시글을 저장하는 메소드
 공공 무효 insertBoard (BoardBean의 bbean) {
  
  INT NUM = bbean.getNum ();
  INT 심판 = bbean.getRef ();
  INT re_level = bbean.getRe_level ();
  INT re_step = bbean.getRe_step ();
  

  // 납입 값이 0이 아니라면 즉, 답글이라면
  // 부모 값으로 넘어온 심판를 기준으로 re_level, re_step의 값을 증가시킨다.
  (NUM! = 0)의 경우 {
   // 스텝을 증가시키는 데 현재 부모의 단계보다 큰 것들만 1 씩 더한다
   // 새로운 댓글을 집어 넣으면 그 댓글을 위해서 나머지 글들의 스텝을 1 씩 증가시켜야 함
   문자열은 SQL은 = "업데이트> re_step = re_step + 1 곳 심판 = 및 re_step을 설정 bboard?";
   
   template.update (SQL, 심판, re_step); // 물음표 부분을 여기서 작성 
   
   re_level + = 1;
   re_step + = 1; // 자신을 증가 시킴
  }

  그밖에{
   // 새 글이라면
   // 현재 글 그룹의 최고 값을 가져옴
   문자열 SQL은 = "bboard에서 최대 (심판)을 선택합니다"; // 심판의 최고 값을 리턴
   경우 (심판 == 0) {
    심판 = template.queryForInt (SQL) +1; // 최대 (REF)를 가져 와서 일을 수행
   }
   
   다른 { // 첫 새글이라면
    REF = 1;
   }
   re_step = 0; // 첫 새글이라면
   re_level = 0;
  }
  
  // 쿼리 준비
  문자열 SQL = "bboard 값 (board_seq.nextval, 삽입 : 작가 : passwd를 : 제목 : 내용 : 이메일, '"+ 심판 + "', '"+ re_level + "', '"+ re_step + "' , SYSDATE 0) ";

  // 준비된 쿼리를 스프링 용 인 SimpleJdbcTemplate 형태로 바꿔 줌
  // 알아서 콩 클래스와 매핑 시킴
  SqlParameterSource의 sqlsource = 새로운 BeanPropertySqlParameterSource (bbean);
  // 쿼리 실행
  template.update (SQL, sqlsource);
 }

 // ★ 총 게시글의 개수를 파악하는 메소드
 공공 INT getAllCount () {
  // 쿼리 준비
  문자열 SQL = "bboard에서 선택 카운트 (*)";

  template.queryForInt (SQL)를 반환;
 }


 // 2. 총 10 개의 데이터를 보여주는 메소드 작성
 공개 목록 <BoardBean> getAllBoard (INT 시작, INT 끝) {
  // 쿼리 준비
  문자열 SQL은 = "* 선택에서 WHERE Rnum>과 Rnum <= (ROWNUM Rnum은 (심판 제품 설명, re_step 오름차순) (A)에 의해 bboard 위해 SELECT * FROM, A.의 * 선택)?" ;

  // SQL의에 맞는 rowmap
  // 멤버 빈 타입 속성과 보드 테이블의 레코드, 컬럼 명을 일치시켜주는 객체
  RowMapper의 <BoardBean> RM은 = 새로운 BeanPropertyRowMapper <BoardBean> (BoardBean.class);

  (시작-1, 끝을, SQL, RM) template.query를 반환; // 물음표 부분을 여기서 작성 (빈 클래스와 관련 없을 때)
 }

 // 3. 하나의 게시글을 리턴하는 메소드 작성
 공공 BoardBean getOneBoard (INT의 납입) {
  // 조회수를 증가 시킴
   setCount (NUM);
  // 쿼리 준비
  문자열 SQL = "여기서 NUM = bboard SELECT * FROM";

  // 멤버 빈 타입 속성과 구성원 테이블의 레코드, 컬럼 명을 일치시켜주는 객체
  RowMapper의 <BoardBean> RM은 = 새로운 BeanPropertyRowMapper <BoardBean> (BoardBean.class);
  template.queryForObject (SQL, RM은, 납입)을 반환;
 }

 // 4. 하나의 게시글을 수정하는 메소드 작성
 공공 무효 updateBoard (문자열 NUM2, 문자열 제목, 문자열의 내용) {
  // 쿼리 준비
  문자열 SQL = "업데이트 bboard 세트 대상 =? 내용 = NUM​​ =?";

  template.update (SQL, 제목, 내용, NUM2);
 }

 // 5. 하나의 게시글을 삭제하는 메소드 작성
 공공 무효의 경우 deleteboard (문자열 NUM) {
  // 쿼리 준비
  =이? "어디 NUM = bboard에서 삭제"문자열은 SQL;

  template.update (SQL, NUM);
 }
 

 // ★ 조회수를 증가시키기위한 메소드
 공공 무효 setCount (INT의 납입) {
  // 현재 선택된 게시글 번호의 조회수를 가져옴
  문자열은 SQL은 = "업데이트는 = readcount + 1 곳 NUM = readcount 설정 bboard";
  template.update (SQL, NUM);
 }
}


 - /WebContent/main.jsp 소스
<% 페이지 언어 @ = "자바"의 contentType = "text / html과; 문자셋 = EUC-KR" 
    pageEncoding = "EUC-KR"%> 
<! DOCTYPE html로의 PUBLIC "- // W3C / / DTD HTML 4.01 과도 // EN ""http://www.w3.org/TR/html4/loose.dtd "> 
<HTML> 
<HEAD> 
<META HTTP-EQUIV ="콘텐츠 형식 "내용 ="텍스트 / html로; 문자셋 = EUC-KR "> 
<제목> 메인 </ 제목> 
</ head> 
<body> 
<중앙> 
<글꼴 얼굴 ="휴먼 아미 체 "> 
 <H2> <A HREF = "/ 스프링 보드 / BoardWriteProcController 수도 있었죠 " > 시작 </A> </ H2> 
</ FONT> 
</ 센터> 
</ BODY> 
</ HTML>



 - /src/controller/BoardWriteProcController.java 소스
패키지 컨트롤러;

수입 org.springframework.web.bind.annotation.RequestMapping;
수입 org.springframework.web.servlet.ModelAndView;
수입 org.springframework.web.servlet.view.RedirectView;

공용 클래스 BoardWriteProcController {

 @RequestMapping ( "/ BoardWriteProcController")
 공공의 ModelAndView writeBoard (문자열 납입, 문자열 심판, 문자열 re_level, 문자열 re_step) {
  // 해당 글이 최신 글인지 답변 글인지를 파악 후 데이터를 저장
  INT가 ref1 = 0 NUM1 = 0 re_step1 = 0 re_level1 = 0;
  
  시험{
   // 납입 값이 널 (null)과 같다면 - 즉, 댓글이라면
   경우 (NUM! = NULL) {
    NUM1 =있는 Integer.parseInt (NUM);
    가 ref1 =있는 Integer.parseInt (심판);
    re_level1 =있는 Integer.parseInt (re_level);
    re_step1 =있는 Integer.parseInt (re_step);
   }

  } 캐치 (예외 전자) { 
  }

  // 모델과 뷰를 동시에 지정 가능한 객체 선언
  의 ModelAndView MAV = 새의 ModelAndView ();
  mav.addObject ( "NUM", NUM1);
  mav.addObject ( "심판", ref1 용);
  mav.addObject ( "re_level", re_level1);
  mav.addObject ( "re_step", re_step1);
  
  // 컨트롤러에서 컨트롤러를 호출
  mav.setViewName ( "BoardWrite");

  MAV를 반환; // 빈 클래스에 선언 한의 ViewResolver 클래스를 호출
 }
}


 - /WebContent/jsp/BoardWrite.jsp 소스
<% 페이지 언어 @ = "자바"의 contentType = "text / html과; 문자셋 = EUC-KR" 
    pageEncoding = "EUC-KR"%> 
<% @ 접두사 = "c"를 태그 라이브러리 URI = "http://java.sun.com/jsp/jstl/core"%> 
<! DOCTYPE html로의 PUBLIC "- // W3C // DTD HTML 4.01 과도 // EN"에 "http : //www.w3. 조직 / TR / HTML4 / loose.dtd "> 
<HTML> 
<HEAD> 
<META HTTP-EQUIV ="콘텐츠 형식 "내용 ="text / html과; 문자셋 = EUC-KR "> 
<제목> 글 쓰 기 </ 제목> 
</ head> 
<body> 
<중앙> 
<글꼴 얼굴 = "휴먼 아미 체"> 
 <H2> 글 쓰 기 </ H2> 

  <양식 액션 = "/ 스프링 보드 / BoardWriteController.do"방법 = "게시물">
     
   <입력 유형 = "숨겨진"이름 = "NUM"값 = "$ {NUM}"> 
    <% - 새글이라면 1, 1, 1이 넘어 가고, 답글이라면 지정한 값이 넘어 간다. - %>
     <입력 유형 = "숨겨진"이름 = "심판"값 = "$ {심판}"> 
    <입력 유형 = "숨겨진"이름 = "re_level"값 = "$ {re_level}"> 
    <입력 유형 = "숨겨진"이름 = "re_step"값 = "$ {re_step}"> 
  
  <표 너비 = "500"국경 = "1"의 cellpadding = "5"cellspacing = "2"> 
  <TR> 
  <TD 정렬 = "오른쪽" 에서 colspan = "2"> <a의 HREF = "/SpringBoard/BoardListController.do"> 전체 글보기 </A> </ TD> 
  </ TR> 
  
     <TR> 
     <폭 번째 = "70"정렬 = "센터"BGCOLOR = "FFFFFFF">이 름 </ 일> 
     <TD 폭 = "330"> <입력 유형 = "텍스트"이름 = "작가"크기 = "25"> </ TD> 
     </ TR> 
    
     <TR> 
     <일 너비 = "70", "FFFFFFF을"= BGCOLOR = "센터"정렬> 제 목 </ 일> 
     <TD 폭 = "330">  
     <C : 시험 = "$ {NUM == 0}">의 경우 
      <입력 유형 = "텍스트"이름 = "대상"크기 = "25"> 
     </ C : 경우> 
     <C : 경우 테스트 = "$ {! NUM = 0}"> 
      <입력 유형 = "텍스트"이름 = "대상"크기 = "25"값 = "┗ [다시]"> 
     </ C : 경우> 
  </ TD> 
     </ TR> 
   
     <TR> 
     <폭 번째 = "70", "FFFFFFF을"= BGCOLOR = "센터"> 정렬 이메일 </ 일> 
     <TD 폭 = "330"> <입력 유형 = "텍스트"이름 = "이메일"크기 = "25"> </ TD> 
     </ TR> 

     <TR> 
     <폭 번째 = "70"= "중앙 정렬 "BGCOLOR ="FFFFFFF "> 내 용 </ 일> 
     <TD 폭 ="330 "> <텍스트 영역의 이름 ="내용 "행 ="13 "COLS ="40 "> </ 텍스트 영역> </ TD> 
     </ TR > 

     <TR> 
     <폭 번째 = "70", "FFFFFFF"> 비밀번호 </ 일> = BGCOLOR = "센터"정렬 
     <TD 폭 = "330"> <입력 유형 = "암호"이름 = "passwd에"크기 = " 25 "> </ TD> 
     </ TR> 
     </ 테이블> 
    
     <표 너비 ="180 "국경 ="0 "의 cellpadding ="5 "> 
     <TR> 
     <TD> <입력 유형 =" "= 값"을 제출 쓰기 "> </ TD> 
     <TD> <입력 유형 ="재설정 "값 ="취소 "> </ TD> 
     <TD> <입력 유형 ="버튼 "값 = "목록 "의 onclick ="스크립팅 window.location = '/ 스프링 보드 / BoardListController.do ' " > </ TD>     
     </ TR> 
    </ 테이블> 
   </ FORM>
</ FONT>  
</ 센터> 
</ BODY> 

</ HTML> 


 - /src/controller/BoardWriteController.java 소스 (문 삽입)
패키지 컨트롤러, 

수입 org.springframework.web.bind.annotation.RequestMapping를, 
수입 org.springframework.web.servlet.ModelAndView를, 
수입 org.springframework.web.servlet.view.RedirectView를, 

수입 board.BoardBean를, 
수입 board.BoardDao; 


공용 클래스 BoardWriteController { 
 
 boardDao boardDao,

  공공 무효 setBoardDao (boardDao boardDao) { 
  this.boardDao = boardDao; 
 } 


 @RequestMapping ( "/ BoardWriteController" ) 
 공공의 ModelAndView writeBoard (BoardBean의 bbean) { 
  // 게시판 등록 
  boardDao.insertBoard ( bbean); 
  
  //를 동시에 지정 가능한 객체 선언 모델과 뷰
   의 ModelAndView MAV = 새의 ModelAndView (); 
    
  // 컨트롤러에서 컨트롤러를 호출 
  mav.setView (새 만약 RedirectView ( "BoardListController.do"));

   반환 MAV; // 빈 클래스에 선언 한의 ViewResolver 클래스를 호출
  } 

}


 - /src/controller/BoardListController.java 소스 (문 * 선택)

패키지 컨트롤러;

가져 오기를 java.util.List;
수입 java.util.Vector에;

수입 org.springframework.web.bind.annotation.RequestMapping;
수입 org.springframework.web.servlet.ModelAndView;

수입 board.BoardBean;
수입 board.BoardDao;

공용 클래스 BoardListController {

 BoardDao boardDao;

 공공 무효 setBoardDao (BoardDao boardDao) {
  this.boardDao = boardDao;
 }

 @RequestMapping ( "/ BoardListController" )
 공공의 ModelAndView listBoard (문자열 pageNum) {

  // 화면에 보여 질 개수를 저장하는 변수 선언
  INT pageSize가 = 10;
  // 전체 글의 개수를 저장할 변수 선언
  INT 카운트 = 0;
  // 현재 페이지에 표시 할 헤 코드 수
  INT 번호 = 0;

  // 새로 쓴글, 삭제, 또는 수정시 호출되었는지 아니면 페이징 처리되었는지를 파악해주어야 함
  경우 (pageNum == NULL) { // 미 페이징 처리라면
   pageNum = "1";
  }
  // pageNum를 숫자로 바꾸어 줌
  INT currentPage =있는 Integer.parseInt (pageNum);
  // 모든 게시글의 수
  () = boardDao.getAllCount를 계산;
  
  목록 <BoardBean> 목록 = NULL;
  // 현재 페이지에 보여줄 시작 번호를 설정
  INT startRow = (currentPage-1) * pageSize가 + 1;
  INT와 endRow = currentPage * pageSize가; // 10 개씩 게시글을 보여줌

  경우 {(> 0 계산)
   // 총 게시글에서 원하는 부분의 데이터를 가져옴
   목록 = boardDao.getAllBoard (startRow, endRow에);
   // 테이 플에 표시해야할 번호를 지정하기 위해서 사용하는 변수
   수 = 계산 - (currentPage-1) * pageSize가를;
  }
  
  // BoardList.jsp쪽으로 데이터를 넘겨 줌
  의 ModelAndView MAV = 새의 ModelAndView ();
  // 데이터를 부착
  mav.addObject ( "currentPage", currentPage);
  mav.addObject ( "startRow"startRow);
  mav.addObject ( "endRow에"와 endRow);
  mav.addObject (계산, "계산");
  mav.addObject ( "pageSize가", pageSize가);
  mav.addObject ( "번호", 수);
  mav.addObject ( "listbean", 목록);
  
  mav.setViewName ( "BoardList");
  MAV를 반환;
 }
}


 - /WebContent/jsp/BoardList.jsp 소스
<% @ 페이지 수입 = "board.BoardBean"%> 
<% @ 페이지 수입 = "java.util.Vector에"%> 
<% @ 페이지 수입 = "board.BoardDao"% > 
<%의 @ 페이지 언어 = "자바"의 contentType = "text / html과; 문자셋 = EUC-KR" 
    pageEncoding = "EUC-KR"%> 
<% @ 접두사 = "c"를 URI = "HTTP 태그 라이브러리 : // 자바. sun.com/jsp/jstl/core "%> 
<! - 국제화 태그 -> 
<% @ 접두사 = 태그 라이브러리"FMT "URI ="http://java.sun.com/jsp/jstl/fmt "% >
<! DOCTYPE html로의 PUBLIC "- // W3C // DTD HTML 4.01 과도 // EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<HTML> 
<HEAD> 
<메타 HTTP -equiv = "콘텐츠 형식"내용 = "text / html과; 문자셋 = EUC-KR"> 
<제목> 자 유 게시 판 </ 제목> 
</ head> 
<body> 
<중앙> 
<글꼴 얼굴 = "휴먼 아미 체 "> 
  <H2> 자 유 게시 판 </ H2> 
   <양식 액션 ="/SpringBoard/BoardWriteProcController.do "방법 ="게시물 ">
   <표 테두리 ="1 "폭 ="740 "의 cellpadding ="4 "cellspacing ="2 "> 
  <TR BGCOLOR ="FFFFFFF "> 
   <번째 폭 ="50 "> 번호 </ 일> 
   <번째 폭 ="330 "> 제목 </> 제 
   <제 폭 ="130 "> 작성자 </ 일> 
   <폭 번째 = "130"> 날짜 </ 일> 
   <제 너비 = "80"> 조회수 </ 일> 
  </ TR> 

   <C : foreach는 VAR = "listbean"항목 = "$ {listbean} ">
     <TR> 
     <폭 번째 ="50 "> <C : 제한 값 ="$ {수} "/> 
      <C : 세트 VAR ="숫자 "값 ="$ {수-1} "> </ c를 : 설정> 
     </> 일 
     <TD 폭 = "330"정렬 = "왼쪽"> 
     <- 답글인지를 파악해서 들여 쓰기를 하기위한 수행 ->!
      <C : 시험 = "$ {경우 listbean.re_level > 0} "> 
      <C : 대해 forEach는 VAR ="내가 1 "= 시작", "끝 ="$ {listbean.re_level} "> 
       & NBSP; & NBSP; 
      </ C : 대해 forEach> 
     </ C : 경우> 
     <A HREF = "/SpringBoard/BoardInfoController.do?num=${listbean.num}">${listbean.subject} </A> </ TD> 
    <TD 폭 ="130 "> $ {listbean.writer} </ TD> 
     <TD 폭 = "130"> $ {listbean.reg_date} </ TD> 
     <TD 폭 = "80"> $ {listbean.readcount} </ TD> 
    </ TR> 
   </ C : 대해 forEach> 
     </ 테이블> 

     <표 테두리 = "0"너비 = "740"의 cellpadding = "4"> 
     <TR> 
  <TD에서 colspan = "5"정렬 = "오른쪽"> <입력 유형 = "제출"값 = "글쓰기"> </ TD > 
  </ TR> 
   </ 테이블> 
   </ FORM>
  
    <C : 시험 = "$ {이 계산> 0}"경우> 
    <- 데이터 개수 및 화면에 표시 할 데이터 의 개수를 설정 ->!
     <C : 세트 VAR = "PAGECOUNT"값 = "$ {계산 / pageSize가 + (%의 pageSize가 == 0을 계산? 0 : 1)} "/> 
    <C : 설정 VAR ="시작 페이지 "값 ="$ {1} "/> 
    <FMT : parseNumber 값 ="$ {currentPage / 10} "integerOnly ="true "를 VAR ="결과 "/> 
    <C : 경우 테스트 ="$ {currentPage의 10 % = 0!} "> 
     <C : 설정 VAR ="시작 페이지 "값 ="$ {결과 * 10 + 1} "/> 
    </ C : 경우> 
    <C : 경우 테스트 = "$ {currentPage의 10 % == 0}"> 
     <C : 설정 VAR = "시작 페이지"값 = "$ {(결과-1) * 10 + 1}"/> 
    </ C : 경우 > 
    
    <- 화면 아래 페이징 처리를 해야하는 소스를 작성 ->!
     <C : 설정 VAR = "pageBlock"값 = "$ {10}"/> 
    <C : 설정 VAR = "ENDPAGE"값 = "$ { 시작 페이지 + pageBlock-1} "/> 
    <C : 경우 테스트 ="$ {ENDPAGE> PAGECOUNT} "> 
     <C : 설정 VAR ="ENDPAGE "값 ="$ {PAGECOUNT} "/> 
    </ C : 경우> 
    < C : 만약 테스트 = "$ {ENDPAGE> 10}"> 
     <a href가 = "BoardList?pageNum=${startPage-10}"> [이전] </a>에 
    </ C : 경우> 
    <C : foreach는 VAR = "m"시작 = "$ {시작 페이지}"끝 = "$ {ENDPAGE}">  
       <a의 HREF = "BoardListController.do?pageNum=${m}"> [$ {m}] </a>에     
      </ C : 대해 forEach> 
    <C : 경우 테스트 = "$ {ENDPAGE> PAGECOUNT}"> 
     <a의 HREF = "BoardListController.do?pageNum=${startPage+10}">는 [다음] </a>에 
    </ C : 경우> 
   </ C : 경우>  
</ FONT> 
</ 센터> 
</ BODY> 
</ HTML> 


 - /src/controller/BoardInfoController.java 소스 (선택 문)
패키지 컨트롤러, 

수입 org.springframework.web.bind.annotation. RequestMapping, 
수입 org.springframework.web.servlet.ModelAndView를, 

수입 board.BoardBean를, 
수입 board.BoardDao을, 

공용 클래스 BoardInfoController { 
 
 boardDao boardDao,


  공공 무효 setBoardDao (boardDao boardDao) { 
  this.boardDao = boardDao; 
 } 


 @RequestMapping을 ( "/ BoardInfoController " ) 
 공공의 ModelAndView infoBoard (INT의 납입) { 

  // 데이터베이스에 접근해서 데이터를 받아 옴 
  BoardBean bbean = boardDao.getOneBoard (NUM),

   새의 ModelAndView (반환"bbean, BoardInfo ","bbean을 "); 
 } 
} 


 - / 하여 WebContent / JSP / BoardInfo.jsp 소스
<% 페이지 언어 @ = "자바"의 contentType = "text / html과; 캐릭터 세트 = EUC-KR " 
    pageEncoding ="EUC-KR "%> 
<DOCTYPE html로의 PUBLIC!"- // W3C // DTD HTML 4.01 과도 // EN ""http://www.w3.org/TR/html4/ loose.dtd "> 
<HTML> 
<HEAD> 
<META HTTP-EQUIV ="콘텐츠 형식 "내용 ="text / html과; 캐릭터 세트 = EUC-KR "> 
<제목> 자 유 게시 판 </ 제목> 
</ head> 
<body> 
<중앙> 
<글꼴 얼굴 ="휴먼 아미 체 "> 
 <H2> 자 유 게시 판 </ H2 > 
 = "/SpringBoard//BoardUpdateController.do"방법 <양식 액션 = "포스트"이름 = "양식">
     <테이블 테두리 = "1"폭 = "500"높이 = "380"의 cellpadding = "5"cellspacing = " 2 "> 
    <TR> 
     <폭 번째 ="100 "BGCOLOR ="FFFFFFF "> 글 번호 </> 일 
     <TD 폭 ="250 "> <입력 유형 ="텍스트 "크기 ="40 "이름 ="NUM " 값 = "$ {bbean.num}" 장애인> </ TD> 
    </ TR> 
    
    <TR> 
    <폭 번째 = "100"BGCOLOR = "FFFFFFF"> 작성자 </ 일> 
    <TD 폭 = "250"> < 입력 유형 = "텍스트"크기 = "40"이름 = "작가"값 = "$ {bbean.writer}"장애인> </ TD> 
    </ TR> 
  
    <TR> 
    <폭 번째 = "100"BGCOLOR = "FFFFFFF "> 글 제목 </ 일> 
    <TD 폭 ="250 "> <입력 유형 ="텍스트 "크기 ="40 "이름 ="대상 "값 ="$ {bbean.subject} "> </ TD> 
    </ TR> 
   
    <TR> 
    <폭 번째 = "100"BGCOLOR = "FFFFFFF"> 내용 </ 일> 
    <TD 폭 = "250"> <텍스트 영역 COLS = "40"행 = "10"이름 = "내용"> $ {bbean.contents} </ 텍스트 영역> </ TD> 
    </ TR> 
    
    <TR> 
    <너비 = "100"BGCOLOR = "FFFFFFF"일> 비밀번호 </ 일> 
    <TD 폭 = "250"> <입력 유형 = "암호"크기 = "40"이름 = "passwd를"> </ TD> 
    </ TR> 
    </ 테이블> 
  
    <표 테두리 = "0"너비 = "250"의 cellpadding = "5"> 
    <TR> 
     <TD> <입력 유형 = "제출"값 = "수정"> </ TD> 
     <TD> <입력 유형 = "버튼" 값 = "삭제"의 onclick = "스크립팅 window.location = '/ 스프링 보드 / BoardDeleteController.do? NUM = $ {bbean.num} ' " > </ TD> 
     <TD> <입력 유형 ="버튼 " 값 ="답글 "의 onclick = </ TD> 
     <TD> <입력 유형 = "버튼" 값 = "목록"의 onclick = "스크립팅 window.location = '/ 스프링 보드 / BoardListController.do'" > </ TD> 
    </ TR> 
    </ 테이블> 
    
    <입력 = "숨겨진"입력 이름 = "NUM2"값 = "$ {bbean.num}" > 
    <입력 유형 = "숨겨진"이름 = "passwd2"값 = "$ {bbean.passwd}"> 
    </ FORM> 
</ 중앙> 
</ BODY> 
</ HTML>


 - /src/controller/BoardUpdateController.java 소스 (업데이트 문)
패키지 컨트롤러, 

수입 org.springframework.web.bind.annotation.RequestMapping, 
수입 org.springframework.web.servlet.ModelAndView, 
수입 org.springframework.web.servlet. view.RedirectView, 

수입 board.BoardDao; 

공용 클래스 BoardUpdateController { 

 boardDao boardDao,


  공공 무효 setBoardDao (boardDao boardDao) { 
  this.boardDao = boardDao; 
 } 

 @RequestMapping ( "/ BoardUpdateController" ) 
 공공의 ModelAndView updateBoard (문자열 NUM2, 문자열 제목, 문자열 내용) { 
  // 데이터베이스에 접근해서 데이터를 받아 옴 
  boardDao.updateBoard (NUM2, 제목, 내용) ; 

  // 모델과 뷰를 동시에 지정 가능한 객체 선언
   의 ModelAndView MAV = 새의 ModelAndView (); 

  // 컨트롤러에서 컨트롤러를 호출
   mav.setView (새 만약 RedirectView ( "BoardListController.do")); 

  반환 MAV; // 빈 클래스에 선언 한의 ViewResolver 클래스를 호출
  } 
}


 - /src/controller/BoardDeleteController.java 소스 (문 삭제)
패키지 컨트롤러;

수입 org.springframework.web.bind.annotation.RequestMapping;
수입 org.springframework.web.servlet.ModelAndView;
수입 org.springframework.web.servlet.view.RedirectView;

수입 board.BoardDao;

공용 클래스 BoardDeleteController {
 BoardDao boardDao;


 공공 무효 setBoardDao (BoardDao boardDao) {
  this.boardDao = boardDao;
 }

 @RequestMapping ( "/ BoardDeleteController" )
 공공의 ModelAndView 경우 deleteboard (문자열 NUM) {
  // 데이터베이스에 접근해서 데이터를 받아 옴
  boardDao.deleteBoard (NUM);

  // 모델과 뷰를 동시에 지정 가능한 객체 선언
  의 ModelAndView MAV = 새의 ModelAndView ();

  // 컨트롤러에서 컨트롤러를 호출
  mav.setView (새 만약 RedirectView ( "BoardListController.do"));

  MAV를 반환; // 빈 클래스에 선언 한의 ViewResolver 클래스를 호출
 }
}


 - 결과




























작성자 : 이홍 관 time-: 오후 5시 ​​4분 
이메일로 전송
블로그에 게시!
트위터에서 공유
페이스 북에서 공유
클립에 공유

라벨 : 엔터프라이즈 자바 (봄) , 엔터프라이즈 자바 5 주차 , 엔터프라이즈 자바 프로젝트
게시물 최근 이전 게시물 홈 로그인
윤곽

내 사진
이홍 관  

전체 프로필보기
꼬리표

엔터프라이즈 자바 (84)
엔터프라이즈 자바 (HTML5) (12)
엔터프라이즈 자바 (자바 스크립트) (9)
엔터프라이즈 자바 (JSP) (39)
엔터프라이즈 자바 (오라클) (6)
엔터프라이즈 자바 (봄) (15)
엔터프라이즈 자바 1 주차 (54)
엔터프라이즈 자바 2 주차 (36)
엔터프라이즈 자바 3 주차 (39)
엔터프라이즈 자바 4 주차 (19)
엔터프라이즈 자바 5 주차 (12)
엔터프라이즈 자바 6 주차 (5)
엔터프라이즈 자바 프로젝트 (16)
OpenCV의 (36)
OpenCV의 # 1 (5)
OpenCV의 # 2 (11)
OpenCV의 # 4 (9)
OpenCV의 # 5 (5)
OpenCV의 # 6 (1)
OpenCV의 # 7 (5)
블로그 메뉴

12 (12)
01 (24)
06 (54)
07 (109)
08 (2)
특히 잘하는

 
OpenCV의 # 2-7 예 (관심 영역 정의)
 - 서로 다른 크기를 갖는 영상을 합칠 때 사용 - 이력서 : 함수는 두 영상이 같아야한다는 전제가 있었음 추가합니다. - 이번 경우에는 관심 영역 ROI (관심의 지역)를 정의 해 이력서 :: 함수에 적용 할 수있다을 추가합니다. - ...
 
OpenCV의 # 7-2 예 (허프 변환으로 영상 내 선 감지)
 - 직선은 객체 인식과 영상 이해에 중요한 역할을하기 때문에 영상 내의 특별한 특징을 감지하기 위해 사용. - 허프 변환 알고리즘은 영상에서 선을 감지하기 위해 개발했고, 다른 간단한 영상 구조 감지로 확장 할 수있다. - 허브 변환은 ...
 
OpenCV의 # 4-1 예 (히스토그램 계산)
OpenCV의 # 4 히스토그램을 이용한 화소 개수 세기 - 영상은 여러 값을 갖는 화소로 구성 (1 채널 그레이 레벨 영상에서 각 화소는 0 (검은 색)과 255 (흰색) 사이의 값을 갖는다.) - 히스토그램은 영상 (또는 영상 집합) 내 해당 값을 갖 ...
합계

111842
인기 게시물

OpenCV의 # 2-7 예 (관심 영역 정의)
OpenCV의 # 7-4 예 (컴포넌트의 외곽선 추출)
OpenCV의 # 7-2 예 (허프 변환으로 영상 내 선 감지)
OpenCV의 # 4-1 예 (히스토그램 계산)
OpenCV의 # 5-5 예 (그랩 컷 알고리즘으로 전경 객체 추출)
OpenCV의 # 7-1 예 (캐니 연산자로 영상 외곽선 감지)
(130712) 15 일차의 login.jsp 외 13 개 (JSP를 이용한 게시판 관리 웹 페이지)
OpenCV의 # 2-6 예 (간단한 연상 산술 연산 실행)
OpenCV의 # 4-6 예 (히스토그램 비교를 이용한 유사 영상 검색)
OpenCV의 # 2-1 예 (화소 값에 접근)
간단한 템플릿. Blogger를 제공.

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//*****************************************************************************************************************************************
	Spring 게시판 구현(답변글 달기)
	:namespace prefix = o ns = "urn:schemas-microsoft-com:office:office" /> 
	1.     시작하기
	 
	-       이번에는 답변글 쓰기 기능을 추가하자.
	-       JSP쪽 /jsp/reply.jsp 파일이 추가된다.
	-       답변글 쓰기 로직을 이해하려면 SpringBoardDAO.java에 설명되어 있는 reply, reply_level, reply_step의 의미에 대해 이해 하자.
	 
	 
	2.     DAO쪽 클래스를 작성하자.
	 
	BoardDAO.java 인터페이스, SpringBoardDAO.java 클래스에 답변 글 등록을 위한 replyBoard() 메소드를 추가하자.
	 
	 
	소스 코드 중 빨강색 부분이 추가된 부분
	 
	 
	[BoardDAO.java]
	 
	package onj.board.dao;
	 
	import java.util.List;
	 
	import onj.board.model.BoardDTO;
	import onj.board.model.CommentDTO;
	 
	import org.springframework.dao.DataAccessException;
	 
//-------------------------------------------------------------------------------------------------------------------------------------------------	 	 	 
	public interface BoardDAO {
	       //게시물 리스트 보기
	       public List<BoardDTO> boardList() throws DataAccessException;
	      
	       //게시물 본문 미리보기
	       public String preView(String seq) throws DataAccessException;
	      
	       //게시물 본문 읽기
	       public BoardDTO readContent(String seq) throws DataAccessException;
	      
	       //읽은 글의 조회수 1증가
	       public int updateReadCount(String seq) throws DataAccessException;
	      
	       //Comment저장
	       public int insertComment(CommentDTO commentDTO) throws DataAccessException ;
	      
	       //Comment조회
	       public List<CommentDTO> commentList(String seq) throws DataAccessException;
	      
	       //게시글 입력
	       public int insertBoard(BoardDTO board) throws DataAccessException;
	      
	       //글 수정
	       public int updateBoard(BoardDTO board) throws DataAccessException;
	      
	       //글 삭제
	       public int deleteBoard(String sid , String password) throws DataAccessException;
	      
	       //답글 달기
	       public int replyBoard(BoardDTO board) throws DataAccessException;
	}
	 
	 
	//-------------------------------------------------------------------------------------------------------------------------------------------------	 
	[SpringBoardDAO.java]
	 
	 
	게시판 리스트보기의 SQL문장이 수정 되었다. 확인 하자.
	 
	 
	 
	package onj.board.dao;
	 
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.util.List;
	 
	import javax.sql.DataSource;
	 
	import onj.board.model.BoardDTO;
	import onj.board.model.CommentDTO;
	 
	import org.springframework.dao.DataAccessException;
	import org.springframework.jdbc.core.JdbcTemplate;
	import org.springframework.jdbc.core.RowMapper;
	 
	 
	public class SpringBoardDAO implements BoardDAO {
	       private JdbcTemplate jdbcTemplate;
	      
	 
	       public void setDataSource(DataSource dataSource) {
	             this.jdbcTemplate = new JdbcTemplate(dataSource);
	       }
	 
	       // /게시판 전체 리스트 보기(list.html)
	       public List<BoardDTO> boardList() throws DataAccessException {
	             List<BoardDTO> boardList = null;
	 
	             //String sql = "select * from board";
	             
	              * reply : 답변인경우 어느글의 답변인지 상위글 번호
	              *         최상위글인 경우 reply는 자신의 글번호(seq)와 동일, 리스트보기에서 정령시 우선  reply로 우선하게 된다.
	              * reply_level : 1차, 2차 답글인지 여부, 하나의 글에  답변이 두개면 그 두답변은 reply_level이 같다.
	              *              list.jsp에서 글을 뿌릴 때 reply_level에 따라 들여쓰기를 한다.
	              * reply_step : 하나의 글 아래에 생기는 모든 답변들에 대해 순차적으로 1씩 증가, reply_level과 관계없이
	              
	 
	             String sql = " select * from  (select seq, name , passwd, "
	                              + "                        title, content, filename, regdate, "
	                              + "                        readcount, reply, reply_step, "
	                              + "                        reply_level , rownum r "
	                              + "                   from board "
	                              + "                  order by reply desc , reply_step asc)";        
	 
	             boardList = jdbcTemplate.query(sql, new RowMapper() {
	                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
	                           BoardDTO board = new BoardDTO();
	 
	                           board.setSeq(rs.getInt("seq"));
	                           board.setName(rs.getString("name"));
	                           board.setPasswd(rs.getString("passwd"));
	                           board.setTitle(rs.getString("title"));
	                           board.setContent(rs.getString("content"));
	                           board.setFileName(rs.getString("filename"));
	                           board.setRegDate(rs.getString("regdate"));
	                           board.setReadCount(rs.getInt("readcount"));
	                           board.setReply(rs.getInt("reply"));
	                           board.setReply_step(rs.getInt("reply_step"));
	                           board.setReply_level(rs.getInt("reply_level"));
	 
	                           return board;
	                    }
	             });
	 
	             return boardList;
	       }
	 
	       // 게시물 본문내용 미리보기(/preView)
	       public String preView(String seq) throws DataAccessException {
	             String sql = "select * from board where seq = ?";
	 
	             String preContent = (String) jdbcTemplate.queryForObject(sql,
	                           new Object[] { seq }, new RowMapper() {
	                                 public Object mapRow(ResultSet rs, int rowNum)
	                                              throws SQLException {
	                                        return rs.getString("content");
	                                 }
	                           });
	             return preContent;
	       }
	 
	       // 게시판 상세보기, 게시글 읽기
	       public BoardDTO readContent(String seq) throws DataAccessException {
	             String sql = "select * from board where seq = ?";
	 
	             BoardDTO boardDTO = (BoardDTO) jdbcTemplate.queryForObject(sql,
	                           new Object[] { seq }, new RowMapper() {
	                                 public Object mapRow(ResultSet rs, int rowNum)
	                                              throws SQLException {
	                                        BoardDTO board = new BoardDTO();
	 
	                                        board.setSeq(rs.getInt("seq"));
	                                        board.setName(rs.getString("name"));
	                                        board.setPasswd(rs.getString("passwd"));
	                                        board.setTitle(rs.getString("title"));
	                                        board.setContent(rs.getString("content"));
	                                        board.setFileName(rs.getString("filename"));
	                                        board.setRegDate(rs.getString("regdate"));
	                                        board.setReadCount(rs.getInt("readcount"));
	                                        board.setReply(rs.getInt("reply"));
	                                        board.setReply_step(rs.getInt("reply_step"));
	                                        board.setReply_level(rs.getInt("reply_level"));
	 
	                                        return board;
	                                 }
	                           });
	             // 글 조회수 1증가
	             this.updateReadCount(new Integer(boardDTO.getSeq()).toString());
	 
	             return boardDTO;
	       }
	 
	       // 읽은 글의 조회수를 1증가
	       public int updateReadCount(String seq) throws DataAccessException {
	             String sql = "update board set readcount = nvl(readcount,0) + 1 where seq = ?";
	             Object[] obj = { seq };
	 
	             return jdbcTemplate.update(sql, obj);
	       }
	 
	       // 커맨트 입력
	       public int insertComment(CommentDTO commentDTO) throws DataAccessException {
	             String sql = "insert into comment_t(seq, name, comm) values (?, ?, ?)";
	 
	             Object[] obj = { commentDTO.getSeq(), // 게시글순번
	                           commentDTO.getName(), // 작성자
	                           commentDTO.getComment() }; // 커맨트
	 
	             return jdbcTemplate.update(sql, obj);
	       }
	 
	       // 커맨트 조회
	       public List<CommentDTO> commentList(String seq) throws DataAccessException {
	             String sql = "select * from comment_t where seq = ?";
	 
	             List<CommentDTO> commentList = jdbcTemplate.query(sql,
	                           new Object[] { seq }, new RowMapper() {
	                                 public Object mapRow(ResultSet rs, int rowNum)
	                                              throws SQLException {
	                                        CommentDTO commentDTO = new CommentDTO();
	                                        commentDTO.setName(rs.getString("name"));
	                                        commentDTO.setComment(rs.getString("comm"));
	 
	                                        return commentDTO;
	                                 }
	                           });
	             return commentList;
	       }
	 
	       // 글쓰기
	       public int insertBoard(BoardDTO board) throws DataAccessException {
	 
	             String sql = "insert into board values(board_seq.nextval , ? , ? , ? , ? , ? , sysdate , 0 , board_seq.currval , 0 , 0)";
	 
	             if (board.getFileName() == null) {
	                    Object[] obj = { board.getName(), board.getPasswd(),
	                                 board.getTitle(), board.getContent(), "" };
	                    return jdbcTemplate.update(sql, obj);
	             } else {
	                    Object[] obj = { board.getName(), board.getPasswd(),
	                                 board.getTitle(), board.getContent(), board.getFileName() };
	                    return jdbcTemplate.update(sql, obj);
	             }
	 
	       }
	 
	       // 게시글 수정
	       public int updateBoard(BoardDTO board) throws DataAccessException {
	 
	             String sql = "update board set  title = ? , content = ? where seq = ?";
	             Object[] obj = { board.getTitle(), board.getContent(), board.getSeq() };
	 
	             return jdbcTemplate.update(sql, obj);
	       }
	 
	       // 게시글 삭제
	       public int deleteBoard(String seq, String passwd)
	                    throws DataAccessException {
	             int result = 0;
	             String sql = "delete from board where seq = ? and passwd = ?";
	             result = jdbcTemplate.update(sql, new Object[] { seq, passwd });
	 
	             return result;
	       }
	 
	       // 답글달기
	       public int replyBoard(BoardDTO boardDTO) throws DataAccessException {
	 
	             int result = 0;
	 
	             String name = boardDTO.getName();
	             String passwd = boardDTO.getPasswd();
	             String title = boardDTO.getTitle();
	             String content = boardDTO.getContent();
	             String fileName = boardDTO.getFileName();
	             int reply = boardDTO.getReply();
	             int reply_step = boardDTO.getReply_step();
	             int reply_level = boardDTO.getReply_level();
	 
	             //현재 답변을 단 게시물 보다 더 높은 스텝의 게시물이 있다면 스텝을 하나씩 상승시킴
	             String sql1 = "update board set reply_step = reply_step + 1 "
	                               + "where reply = " + reply + " and reply_step > " + reply_step;
	 
	             jdbcTemplate.update(sql1);
	 
	             String sql2 = "insert into board values(board_seq.nextval , ? , ? , ? , ? , ? , sysdate , 0 , ? , ? , ?)";
	            
	             // reply_step과 reply_level을 1씩 증가시킨 후 내용을 저장
	             Object[] obj2 = { name, passwd, title, content, fileName, reply, reply_step + 1, reply_level + 1 };
	            
	             result = jdbcTemplate.update(sql2, obj2);
	 
	             return 0;
	       }
	 
	}
	 
	 
	3.  service 쪽 클래스를 만들어 보자.
	 
	 
	소스 코드 중 빨강색 부분이 추가된 부분
	 
	 
	[BoardService.java]
	 
	 
	package onj.board.service;
	 
	import java.util.List;
	import onj.board.model.BoardDTO;
	import onj.board.model.CommentDTO;
	 
	
	 * 게시판에서 구현할 기능을 인터페이스로 정의
	 
	public interface BoardService {
	       //게시판 리스트 보기
	       public List<BoardDTO> boardList();     
	      
	       //게시물 미리보기
	       public String preView(String seq);
	      
	       //게시판 본문 내용보기, 게시글 읽기
	       public BoardDTO readContent(String seq);
	      
	       //커맨트 입력
	       public int insertComment(CommentDTO commentDTO);
	      
	       //커맨트 조회
	       public List<CommentDTO> commentList(String seq);
	      
	       //게시글 입력
	       public int insertBoard(BoardDTO board);
	      
	       //게시글 수정
	       public int updateBoard(BoardDTO board);
	      
	       //게시글 삭제
	       public int deleteBoard(String seq , String passwd);
	      
	       //답글 등록
	       public int replyBoard(BoardDTO board); 
	            
	}
	 
	 
	 
	 
	[BoardServiceImpl.java]
	 
	 
	package onj.board.service;
	 
	import java.util.List;
	import onj.board.dao.BoardDAO;
	import onj.board.model.BoardDTO;
	import onj.board.model.CommentDTO;
	 
	public class BoardServiceImpl implements BoardService {
	    private BoardDAO boardDAO;
	   
	    public void setBoardDAO(BoardDAO boardDAO) {
	        this.boardDAO = boardDAO;
	    }
	   
	    //게시물 리스트 보기
	    public List<BoardDTO> boardList() {
	        return boardDAO.boardList();
	    }
	   
	    //게시물 본문 내용 미리보기
	    public String preView(String seq) {
	        return boardDAO.preView(seq);
	    }
	 
	    //게시글 읽기
	       public BoardDTO readContent(String seq) {
	             return boardDAO.readContent(seq);
	       }
	 
	       //커맨트 입력
	       public int insertComment(CommentDTO commentDTO) {
	             return boardDAO.insertComment(commentDTO);
	       }
	 
	       //커맨트 조회
	       public List<CommentDTO> commentList(String seq) {
	             return boardDAO.commentList(seq);
	       }    
	      
	       //게시글 입력
	       public int insertBoard(BoardDTO board) {
	             return boardDAO.insertBoard(board);
	       }
	      
	       //게시글 수정
	       public int updateBoard(BoardDTO board) {
	             return boardDAO.updateBoard(board);
	       }
	      
	       //게시글 삭제
	       public int deleteBoard(String seq, String passwd) {
	             return boardDAO.deleteBoard(seq, passwd);
	       }
	      
	       //답글 등록
	       public int replyBoard(BoardDTO board){
	             return boardDAO.replyBoard(board);
	       }     
	      
	}
	 
	 
	 
	 
	4.     이번에는 컨트롤러를 수정하자.
	 
	컨트롤러에는 두 개의 메소드가 추가 되는데 reply(), replyok() 이다.
	 
	reply() : 게시글 상세보기(글 읽기) 페이지에서 답변 버튼을 클릭 시 호출되어 답변을 원하는 글(원본글)을 내용을 boardService.readContent() 메소드를 통해 얻은 후 /jsp/reply.jsp로 “reply” 라는 이름으로 addObject후 redirect 시킴
	 
	replyok() : reply.jsp에서 답변 글 저장을 클릭 시 호출되는 컨트롤러의 메소드
	 
	 
	소스 코드 중 빨강색 부분이 추가된 부분
	 
	 
	[BoardMultiController.java]
	 
	 
	package onj.board.controller;
	 
	import java.io.PrintWriter;
	import java.util.Enumeration;
	import java.util.List;
	 
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	 
	import onj.board.model.BoardDTO;
	import onj.board.model.CommentDTO;
	import onj.board.service.BoardService;
	 
	import org.springframework.web.servlet.ModelAndView;
	import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
	 
	import com.oreilly.servlet.MultipartRequest;
	import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
	 
	
	 * MultiActionController는 비슷하거나 관련있는 로직을 수행하는
	 * 다수의 액션을 가지고 있을 때 사용하는 컨트롤러
	 * 연관된 요청(Request)를 묶을 때 용이함
	 
	public class BoardMultiController extends MultiActionController {
	           private BoardService boardService;
	 
	           public void setBoardService(BoardService boardService) {
	                     this.boardService = boardService;
	           }
	 
	           // 게시판 리스트 보기, 페이징 기능은 구현 안함
	           public ModelAndView list(HttpServletRequest req, HttpServletResponse res)
	                                throws Exception {
	 
	                     ModelAndView mv = new ModelAndView("list", "list",
	                                          boardService.boardList());
	 
	                     return mv;
	           }
	 
	           // 게시글 읽기
	           public ModelAndView read(HttpServletRequest req, HttpServletResponse res)
	                                throws Exception {
	                     String seq = req.getParameter("seq");
	 
	                     ModelAndView mav = new ModelAndView("read", "read",
	                                          boardService.readContent(seq));
	                     // 해당 글의 커맨트도 함께 내려 보내자.
	                     mav.addObject("comments", boardService.commentList(seq));
	                     return mav;
	 
	           }
	 
	           // 커맨트쓰기
	           public ModelAndView comment(HttpServletRequest req, HttpServletResponse res) {
	 
	                     String seq = req.getParameter("seq");
	 
	                     CommentDTO commentDTO = new CommentDTO();
	                     commentDTO.setSeq(seq);
	                     commentDTO.setName(req.getParameter("name"));
	                     commentDTO.setComment(req.getParameter("comment"));
	 
	                     boardService.insertComment(commentDTO);
	 
	                     return new ModelAndView("redirect:/read.html?seq=" + seq);
	           }
	 
	           // 새글(게시글) 입력
	           public ModelAndView write(HttpServletRequest req, HttpServletResponse res)
	                                throws Exception {
	 
	                     MultipartRequest multi = new MultipartRequest(req,
	                                          "c:\\java\\project\\onjboard1\\upload", 5 * 1024 * 1024,
	                                          "euc-kr", new DefaultFileRenamePolicy());
	                     Enumeration formNames = multi.getFileNames();
	                     String formName = (String) formNames.nextElement();
	                     String fileName = multi.getFilesystemName(formName);
	 
	                     String name = multi.getParameter("name");
	                     String passwd = multi.getParameter("passwd");
	                     String title = multi.getParameter("title");
	                     String content = multi.getParameter("content");
	 
	                     BoardDTO board = new BoardDTO(name, passwd, title, content, fileName);
	 
	                     boardService.insertBoard(board);
	 
	                     return new ModelAndView("redirect:/list.html");
	           }
	 
	           // 게시글 수정
	           public ModelAndView update(HttpServletRequest req, HttpServletResponse res)
	                                throws Exception {
	 
	                     String seq = req.getParameter("seq");
	                     String name = req.getParameter("name");
	                     String passwd = req.getParameter("passwd");
	                     String title = req.getParameter("title");
	                     String content = req.getParameter("content");
	 
	                     BoardDTO board = new BoardDTO(name, passwd, title, content, "");
	                     board.setSeq(Integer.parseInt(seq));
	 
	                     boardService.updateBoard(board);
	 
	                     return new ModelAndView("redirect:/read.html?seq="
	                                          + req.getParameter("seq"));
	           }
	 
	           // 게시글 삭제
	           public ModelAndView delete(HttpServletRequest req, HttpServletResponse res)
	                                throws Exception {
	                     String seq = req.getParameter("seq");
	                     String passwd = req.getParameter("passwd");
	 
	                     int result = boardService.deleteBoard(seq, passwd);
	 
	                     if (result != 1) {
	                                PrintWriter out = res.getWriter();
	                                out.println("<script>alert('password not correct');</script>");
	                                out.println("<script>history.go(-1);</script>");
	                                return null;
	                     } else {
	                                return new ModelAndView("redirect:/list.html");
	                     }
	           }
	 
	           // 게시물상세보기에서 답변 클릭시 호출되어 답변을 달 reply.jsp로 연결
	           public ModelAndView reply(HttpServletRequest req, HttpServletResponse res) throws Exception {
	 
	                     String seq = req.getParameter("seq");
	 
	                     // 답변달 게시물 내용을 reply.jsp 넘긴다.
	                     ModelAndView mav = new ModelAndView("reply",   //view이름
	                                                                      "reply",   //readContent가 넘기는 boardDTO의 이름, reply.jsp에서 사용
	                                                                      boardService.readContent(seq));
	                    
	                     return mav;
	           }
	 
	           // 답글 저장
	           public ModelAndView replyok(HttpServletRequest req, HttpServletResponse res)
	                                throws Exception {
	 
	                     String seq = req.getParameter("seq");
	                     String name = req.getParameter("name");
	                     String passwd = req.getParameter("passwd");
	                     String title = req.getParameter("title");
	                     String content = req.getParameter("content");
	                     String fileName = "";
	                     String reply = req.getParameter("reply");
	                     String reply_step = req.getParameter("reply_step");
	                     String reply_level = req.getParameter("reply_level");
	 
	                     BoardDTO boardDTO = new BoardDTO(name, passwd, title, content, fileName);
	 
	                     boardDTO.setSeq(Integer.parseInt(seq));
	                     boardDTO.setReply(Integer.parseInt(reply));
	                     boardDTO.setReply_level(Integer.parseInt(reply_level));
	                     boardDTO.setReply_step(Integer.parseInt(reply_step));
	 
	                     boardService.replyBoard(boardDTO);
	 
	                     return new ModelAndView("redirect:/list.html");
	           }
	 
	}
	 
	 
	5.     read.jsp를 수정하자.
	 
	답변 버튼을 클릭 시 이동할 주소 확인!
	 
	[read.jsp]
	 
	<%@ page contentType="text/html; charset=euc-kr" language="java" errorPage="" %>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<title>게시물 읽기</title>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
	<script>
	       function write_ok(){
	             writeform.submit();
	       }
	       function update_ok(){
	             document.viewform.action = '/onjboard1/update.html';
	             document.viewform.submit();
	       }
	</script>
	</head>
	 
	<body>
	<div style="width:600px;">
	<div style="float:enter;">
	 
	<table width="600" height="420" border="1" align="center">
	  <tr height="20"><h2>오라클자바커뮤니티프로그래밍실무교육센터 게시판</h2></tr>
	  <form name="viewform" method="post">
	  <input type="hidden" name="seq" value="${read.seq}">
	  <input type="hidden" name="reply" value="${read.reply}">
	  <input type="hidden" name="reply_step" value="${read.reply_step}">
	  <input type="hidden" name="reply_level" value="${read.reply_level}">
	  <tr>
	    <td width="100">* 이름</td>
	    <td width="500">: <input name="name" type="text" value="${read.name}" size="50" readonly>
	      </td>
	  </tr>
	  <tr>
	    <td>* 제목</td>
	    <td>:
	    <input name="title" type="text" value="${read.title}" size="50" ></td>
	  </tr>
	  <tr align="center">
	    <td colspan="2"><textarea name="content" cols="80" rows="12" >${read.content}</textarea></td>
	  </tr>
	  <tr>
	    <td>* 파일</td>
	    <td>:
	                    <c:choose>
	                           <c:when test="${read.fileName != null}">
	                                 ${read.fileName}                                    
	                           </c:when>
	                           <c:when test="${read.fileName == null}">
	                                 파일 없음
	                           </c:when>
	                    </c:choose>
	       </td>
	  </tr>
	  <tr>
	    <td> </td>
	    <td><input name="button" type="button" onClick="location.href='/onjboard1/reply.html?seq=${read.seq}'" value="답변">
	|
	  <input name="button" type="button" onClick="update_ok()" value="수정">
	<input name="button" type="button" onClick="location.href='/onjboard1/jsp/delete.jsp?seq=${read.seq}'" value="삭제">
	|
	<input name="button" type="button" onClick="location.href='/onjboard1/list.html'" value="목록"></td>
	  </tr>
	  </form>
	  <tr>
	    <td height="99" colspan="2">
	    <!-- BoardMultiController의 comment() 메소드 호출 -->
	    <form method="post" action="comment.html">
	           <table border="1">
	             <tr>          
	               <td>이름 : </td>
	               <td><input type="text" name="name"></td>
	               <td>코멘트:</td>
	               <td><input type="text" name="comment"></td>
	               <td><input type="submit" name="Button" value="쓰기"></td>
	             </tr>
	           </table>
	           <input type="hidden" name="seq" value="${read.seq}">
	    </form>
	            <!--  달려있는 커맨트 보기 -->
	              <table width="789" border="1">
	                  <c:forEach var="comment" items="${comments}">
	                               <tr>
	                              <td width="42" align="center">*</td>
	                              <td width="86">${comment.name}</td>
	                              <td width="639">${comment.comment}</td>
	                            </tr>
	                        </c:forEach>
	             </table>
	      </td>
	    </tr>
	   
	</table>
	<br><br>
	<table><tr><td><tr><b>http://www.ojcedu.com</tr></td></tr></table>
	 
	 
	</div>
	</div>
	</body>
	</html>
	 
	 
	 
	6.     /jsp/reply.jsp 작성.
	 
	[reply.jsp]
	 
	<%@ page contentType="text/html; charset=euc-kr" language="java" %>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
	<html>
	<head>
	<title>답변 달기</title>
	<meta http-equiv="Content-Type" content="text/html; charset=euc-kr">
	<script>
	       function write_ok(){
	             if(document.form.name.value == ""){
	                           alert("이름을 입력하세요.");
	                           document.form.name.focus();
	                           return false;
	             }
	             if(document.form.title.value == ""){
	                           alert("제목을 입력하세요.");
	                           document.form.title.focus();
	                           return false;
	             }
	             if(document.form.content.value == ""){
	                           alert("내용을 입력하세요.");
	                           document.form.content.focus();
	                           return false;
	             }           
	             if(document.form.passwd.value == ""){
	                           alert("비밀번호를  입력하세요.");
	                           document.form.passwd.focus();
	                           return false;
	             }     
	       form.submit();
	       }
	</script>
	</head>
	<body>
	<div style="width:600px;">
	<form method="post" name="form" action="/onjboard1/replyok.html">
	<table><tr><td><h2>오라클자바커뮤니티프로그래밍실무교육센터 게시판(답글달기)</h2><tr><td></table>
	<table width="600" height="277" border="1" align="center">
	  <tr>
	    <td width="120">* 이름</td>
	    <td width="480">: <input name="name" type="text" size="50"></td>
	  </tr>
	  <tr>
	    <td>* 제목</td>
	    <td>:
	    <input name="title" type="text" value="Re: ${reply.title}" size="50"></td>
	  </tr>
	  <tr align="center">
	    <td colspan="2"><textarea name="content" cols="80" rows="10">${reply.content}</textarea></td>
	  </tr>
	  <tr>
	    <td>* 비밀번호</td>
	    <td>:
	      <input type="password" name="passwd"></td>
	  </tr>
	  <tr>
	    <td> </td>
	    <td><input type="button" value="답변" onclick="write_ok();"> |
	        <input type="button" value="취소" onclick="history.back();"></td>
	  </tr>
	</table>
	<input type="hidden" name="seq" value="${reply.seq}">
	<input type="hidden" name="reply" value="${reply.reply}">
	<input type="hidden" name="reply_step" value="${reply.reply_step}">
	<input type="hidden" name="reply_level" value="${reply.reply_level}">
	</form>
	</div>
	<br><br>
	<table><tr><td><tr><b>http://www.ojcedu.com</tr></td></tr></table>
	</body>
	</html>
	 
	 
	 
	7.     action-servlet.xml을 수정하자.
	 
	소스 코드 중 빨강색 부분이  추가된 부분
	 
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE beans PUBLIC "-//SPRING//DTD BEAN//EN"
	    "http://www.springframework.org/dtd/spring-beans.dtd">
	<beans>
	 
	       <bean id="dataSource" class="org.apache.commons.dbcp.BasicDataSource"
	             destroy-method="close">
	             <property name="driverClassName">
	                    <value>oracle.jdbc.driver.OracleDriver</value>
	             </property>
	             <property name="url">
	                    <value>jdbc:oracle:thin:@127.0.0.1:1521:onj</value>
	             </property>
	             <property name="username">
	                    <value>scott</value>
	             </property>
	             <property name="password">
	                    <value>tiger</value>
	             </property>
	       </bean>
	 
	       <!-- 넘어오는 URL에 따라 컨트롤러에서 실행될 메소드 매핑 -->
	       <!-- PropertiesMethodNameResolver는 prop key로 넘어오는 url에 대해 실행할 컨트롤러의 메소드
	             정의 -->
	       <bean id="userControllerMethodNameResolver"
	              class="org.springframework.web.servlet.mvc.multiaction.PropertiesMethodNameResolver">
	             <property name="mappings">
	                    <props>
	                           <!-- list.html 요청이 오면 컨트롤러의 list 메소드 실행 -->
	                           <prop key="/list.html">list</prop>
	                          
	                           <!-- read.html 요청이 오면 컨트롤러의 read 메소드 실행 -->
	                           <prop key="/read.html">read</prop>
	                          
	                           <!-- comment.html 요청이 오면 컨트롤러의 comment 메소드 실행 -->
	                           <prop key="/comment.html">comment</prop>
	                          
	                           <!-- write.html 요청이 오면 컨트롤러의 write 메소드 실행 -->
	                           <prop key="/write.html">write</prop>
	                          
	                           <!-- update.html 요청이 오면 컨트롤러의 update 메소드 실행 -->
	                           <prop key="/update.html">update</prop>                                  
	                          
	                           <!-- delete.html 요청이 오면 컨트롤러의 delete 메소드 실행 -->
	                           <prop key="/delete.html">delete</prop>
	                          
	                           <!-- reply.html 요청이 오면 컨트롤러의 reply 메소드 실행 -->
	                           <prop key="/reply.html">reply</prop>
	                          
	                           <!-- replyok.html 요청이 오면 컨트롤러의 replyok 메소드 실행 -->
	                           <prop key="/replyok.html">replyok</prop>
	                    </props>
	             </property>
	       </bean>
	 
	       <!-- 뷰 리졸버 -->
	       <bean id="viewResolver"
	             class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	             <property name="prefix">
	                    <value>/jsp/</value>
	             </property>
	             <property name="suffix">
	                    <value>.jsp</value>
	             </property>
	       </bean>
	 
	       <!-- 컨트롤러 매핑 -->
	       <bean name="/list.html /read.html /comment.html /write.html
	                   /update.html /delete.html /reply.html /replyok.html"
	            class="onj.board.controller.BoardMultiController">
	             <property name="methodNameResolver">
	                    <ref local="userControllerMethodNameResolver" />
	             </property>
	             <property name="boardService">
	                    <ref bean="boardService" />
	             </property>
	       </bean>
	      
	</beans>
	 
	 
	실행결과
	 
	(게시판 리스트보기 + 게시물 본문내용 미리 보기 + 게시글 상세보기 + 커멘트기능 + 글쓰기 + 글 수정하기 + 글 삭제하기 + 답글달기)
	 
	 
	 
	 
	:namespace prefix = v ns = "urn:schemas-microsoft-com:vml" />
	
}
*/