<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->

<h3>내정보 | 과제게시판 | 수정</h3>
<hr/>

<form method="POST" action="/UspaceAcademy/assignment/assignment_modify.do?assignmentNo=${assignment.assignmentNo}"> <!--  폼으로 묶기* -->

<!-- 히든값 -->
<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}"> <!--  오류났던거 적기 : 수정폼(여기 assignment_modify.jsp에서도 replyStep, replyLevel, replyFamily 히든값으로 넘겨받아야함, 안넘겨받으면 새로운 값으로 등록됨 -->


<div class="boardList">
<table border="1">
<tr>
<th>아이디</th>
<th>이름</th>
<th>마감일</th>
<th>제목</th>
<th>내용</th>
</tr>

<tr>
<td><input type="text" name="assignmentWriterId" value="${sessionScope.login_info.teacherId}"  readonly="readonly"></td>
<td><input type="text" name="assignmentWriter" value="${sessionScope.login_info.teacherName}"  readonly="readonly"></td>
<td><input type="text" value="${requestScope.assignment.assignmentDeadline}" name="assignmentDeadline" size="70" placeholder="ex)2016/07/30"required="required"><span class="error"><form:errors path="lec.assignmentDeadline" delimiter="//"/></span></td>
<td><input type="text" value="${requestScope.assignment.assignmentTitle }"  name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lec.assignmentTitle" delimiter="//"/></span></td>
<td><textarea rows="15" cols="80"  name="assignmentContent"   placeholder="입력하세요">${requestScope.assignment.assignmentContent}</textarea><span class="error"><form:errors path="lec.assignmentContent" delimiter="//"/></span></td>
</tr>

</table>
</div>
	<input type="submit" value="답글작성완료">
	<input type="reset" value="초기화"/> 
</form>







