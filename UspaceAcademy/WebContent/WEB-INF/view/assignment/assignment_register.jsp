<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->


<h3>내정보 | 과제게시판 | 등록하기</h3>
<hr/>
<form method="POST"       <%-- file --%>enctype="multipart/form-data"     action="/UspaceAcademy/assignment/assignment_registerSuccess.do"> <!--  폼으로 묶기* -->


<table class="table_list" summary="영주" cellpacing="0">
	<caption></caption>
	<tbody>
	
<%-- <tr><!--  1   -->
<th scope="col">강의명</th>
<td class="title">
<select id="lectureTitle" name="lectureTitle"> 
<c:forEach items="${requestScope.getLectureList}"  var="getLectureList"><!--  컨트롤러에서 보낸값 -->
<option>${getLectureList.lectureTitle}</option>
</c:forEach>
</select>
</td>
</tr>	 --%>



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


<tr><!--   4   -->
<th scope="col">내용</th>
<td class="title"><textarea rows="15" cols="72" name="assignmentContent" placeholder="내용을 입력하세요"></textarea><span class="error"><form:errors path="lec.assignmentContent" delimiter="//"/></span>
</td>
</tr>


<tr><!--   file, 5   -->
<th scope="col">파일첨부</th>
<td><input type="file" name="upfile"></td>
</tr>



<!--  ●오류났던거적기 : assingmentWriterId 없다고 오류남 - 해결 :  name="assignmentWriterId"네임으로 받고 , 밸류(실제받을값으로 바꿔받으면)받으면됨               -->
<input type="hidden" name="assignmentWriterId" value="${sessionScope.login_info.teacherId}">
<input type="hidden" name="assignmentWriter" value="${sessionScope.login_info.teacherName}">





</tbody>
</table>
	<input type="submit" value="과제등록완료">
	<input type="reset" value="초기화"/> 
</form>







