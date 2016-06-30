<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<h3>내정보 | 과제게시판 | 등록하기</h3>
비밀글여부(강사는 비밀글x, 학생은 무조건 비밀글로 답글달기),
첨부파일기능
답글기능 
<hr/>


<form method="POST" action="/UspaceAcademy/assignment/assignment_registerSuccess.do"> <!--  폼으로 묶기* -->
<input type="hidden" name="teacherName" value="${sessionScope.login_info.teacherName}">

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
<td>${sessionScope.login_info.teacherName}</td>
<td><input type="text" name="assignmentDeadline" size="70" placeholder="ex)2016/07/30" required="required"></td>
<td><input type="text" name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"></td>
<td><textarea rows="15" cols="80" name="assignmentContent" placeholder="내용을 입력하세요"></textarea></td>
</tr>

</table>
</div>
	<input type="submit" value="과제등록완료">
	<input type="reset" value="초기화"/> 
</form>







