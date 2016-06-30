<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h3>내정보 | 과제게시판 | 답글등록하기</h3>
강사가작성한 글내용이랑 제목그대로 가져오고 - 지우고 학생이 쓸수있게끔
비밀글 여부 체크기능 만들어야됨
첨부파일기능 넣어야함
<hr/>

<!--  	private int assignmentNo;	o				//1.글번호-
		private int assignmentSecret;				//4.비밀여부 
		private String assignmentTitle;		o		//3.글제목-
		private String assignmentContent;	o	//    글내용 - 					리스트세부
		private String assignmentDate;			//7.글 등록일-
		private int assignmentHit;					//9.글 조회수-
		private int assignmentPassword;			//    글 비밀번호				리스트세부
		private int replyFamily;			o			//    답글 묶음
		private int replyStep;			o				//    답글 순서
		private int replyLevel;			o			//    답글 단계
		private String assignmentWriter;			//6.글쓴이-
		private String assignmentDeadline;	o	//8.글 마감일-
		private int lectureNo;
-->
<form method="POST" action="/UspaceAcademy/assignment/assignment_replyRegisterSuccess.do?assignmentNo=${assignment.assignmentNo}"> <!--  폼으로 묶기* -->

<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}">

<input type="hidden" name="assignmentDeadline"  value="${assignment.assignmentDeadline}">

<div class="boardList">
<table border="1">
<tr>
<th>강의명(디비)</th>
<th>작성자</th>
<!-- <th>마감일</th> --><!--  마감일 히든 -->
<th>제목</th>
<th>내용</th>
</tr>

<tr>
<td><%-- ${assignment.lectureNo} --%></td><!--  강의명 (db에서) -->
<td>${sessionScope.login_info.studentName}</td>
<%-- <td>${requestScope.assignmentWriter}</td><!-- ??????????? --> --%>
<!-- 마감일 히든 --><%-- <td><input type="text" value="${requestScope.assignment.assignmentDeadline}" name="assignmentDeadline" size="70" placeholder="ex)2016/07/30" required="required"></td> --%>
<td><input type="text" value="RE:${requestScope.assignment.assignmentTitle }"  name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"></td>
<td><textarea rows="15" cols="80"  name="assignmentContent"   placeholder="입력하세요">${requestScope.assignment.assignmentContent}</textarea></td>
</tr>

</table>
</div>
	<input type="submit" value="답글작성완료">
	<input type="reset" value="초기화"/> 
</form>






<!-- ............................................................................................................................................................................................................................ -->
<!-- 
   upload_files.jsp****************************************
<form action="/UspaceAcademy/writeBoard.do" method="post" enctype="multipart/form-data">
제목<input type="text" name="title"><br>
첨부파일
<input type="file" name="upfile">
<textarea name="content" rows="15" cols="70"></textarea>
<input type="submit" value="저장">
</form>
  ****************************************
 <form method="post" enctype="multipart/form-data" action="/UspaceAcademy/imageUpload.do">
 전송할사진<input type="file" name="upImage">
 사진설명<br>
 <textarea rows="10" cols="50" name="comment"></textarea>
 <input type="submit" value="전송">
 </form>
  ****************************************
 -->
<!-- ............................................................................................................................................................................................................................ -->










