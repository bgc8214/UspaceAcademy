<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->


<h3>내정보 | 과제게시판 | 등록하기</h3>

<hr/>


<form method="POST" action="/UspaceAcademy/assignment/assignment_registerSuccess.do"> <!--  폼으로 묶기* -->

<table class="table_list" summary="영주" cellpacing="0">
	<caption></caption>
	<tbody>
	
	
<tr><!--  1   -->
<th scope="col">강의명</th>
<td class="title"><select name="lectureName">
		<option value="국어고등1">국어고등 코드테이블?</option>
		<option value="국어고등2">국어고등 코드테이블?</option>
		<option value="국어고등3">국어고등 코드테이블?</option>
		<option value="영어고등1">영어고등 코드테이블?</option>
		<option value="영어고등2">영어고등 코드테이블?</option>
		<option value="영어고등3">영어고등 코드테이블?</option>
		<option value="수학고등1">수학고등 코드테이블?</option>
		<option value="수학고등2">수학고등 코드테이블?</option>
		<option value="수학고등3">수학고등 코드테이블?</option>		
		</select>
</td>
</tr>


<tr><!--   2   -->
<th scope="col">마감일</th>
<td class="title"><input type="text" name="assignmentDeadline" size="70" placeholder="ex)2016/07/30" required="required"><span class="error"><form:errors path="lec.assignmentDeadline" delimiter="//"/></span>
</td>
</tr>



<tr><!--   3   -->
<th scope="col">제목</th>
<td class="title"><input type="text" name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lec.assignmentTitle" delimiter="//"/></span>
</td>
</tr>


<tr><!--   3   -->
<th scope="col">내용</th>
<td class="title"><textarea rows="15" cols="72" name="assignmentContent" placeholder="내용을 입력하세요"></textarea><span class="error"><form:errors path="lec.assignmentContent" delimiter="//"/></span>
</td>
</tr>





<tr>
<!--  ●오류났던거적기 : assingmentWriterId 없다고 오류남 - 해결 :  name="assignmentWriterId"네임으로 받고 , 밸류(실제받을값으로 바꿔받으면)받으면됨               -->
<%-- <td><input type="text" name="assignmentWriterId" value="${sessionScope.login_info.teacherId}"  readonly="readonly"></td>
<td><input type="text" name="assignmentWriter" value="${sessionScope.login_info.teacherName}"  readonly="readonly"></td> --%>
<input type="hidden" name="assignmentWriterId" value="${sessionScope.login_info.teacherId}">
<input type="hidden" name="assignmentWriter" value="${sessionScope.login_info.teacherName}">









</tbody>
</table>
	<input type="submit" value="과제등록완료">
	<input type="reset" value="초기화"/> 
</form>







