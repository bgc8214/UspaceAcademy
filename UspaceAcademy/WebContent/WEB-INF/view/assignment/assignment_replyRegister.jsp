<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h3>내정보 | 과제게시판 | 답글등록하기</h3>
강사가작성한 글내용이랑 제목그대로 가져오고 - 지우고 학생이 쓸수있게끔
비밀글 여부 체크기능 만들어야됨
첨부파일기능 넣어야함
<hr/>


<form method="POST" action="/UspaceAcademy/assignment/assignment_replyRegisterSuccess.do"> <!--  폼으로 묶기* -->

<div class="boardList">
<table border="1" summary"">
<tr>
<th>강의명(디비)</th>
<th>작성자</th>
<th>마감일</th>
<th>제목</th>
<th>내용</th>
</tr>

<tr>
<td><%-- ${assignment.lectureNo} --%></td><!--  강의명 (db에서) -->
<td>${sessionScope.login_info.studentName}</td>
<%-- <td>${requestScope.assignmentWriter}</td><!-- ??????????? --> --%>
<td><input type="text" name="assignmentDeadline" size="70" placeholder="제목을 입력하세요" required="required"></td>
<td><input type="text" name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"></td>
<td><textarea rows="15" cols="80" name="assignmentContent" placeholder="입력하세요"></textarea></td>
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










