<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->

<style type="text/css">


</style>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>
<script type="text/javascript">

$(document).ready(function(){
	//폼체크
	$("#ok").on("click", function(){
		if(!$("input[name=assignmentDeadline]").val()){
			alert("마감일을 입력하세요!");
			
			return false;
		}
		if(!$("input[name=assignmentTitle]").val()){
			alert("제목을 입력하세요!");
			
			return false;
		}
		if($("textarea[name=assignmentContent]").val()==""){
			alert("내용을 입력하세요!");
			
			return false;
		}
	
	});
});
</script>



<h3>내정보 | 과제게시판 | 수정</h3>
<hr/>

<form method="POST"       <%-- file --%>enctype="multipart/form-data"      action="/UspaceAcademy/assignment/assignment_modify.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"> <!--  폼으로 묶기* -->

<table class="table_list" summary="영주" cellpacing="0">
	<caption></caption>
	<tbody>


<tr><!--   1   -->
<th scope="col">아이디</th>
<td><input type="text" name="assignmentWriterId" value="${sessionScope.login_info.teacherId}"  readonly="readonly"></td>
</tr>

<tr><!--   2   -->
<th scope="col">이름</th>
<td><input type="text" name="assignmentWriter" value="${sessionScope.login_info.teacherName}"  readonly="readonly"></td>
</tr>


<tr><!--   3   -->
<th scope="col">마감일</th>
<td><input type="text" value="${requestScope.assignment.assignmentDeadline}" name="assignmentDeadline" size="70" placeholder="ex)2016/07/30"required="required"><span class="error"><form:errors path="lec.assignmentDeadline" delimiter="//"/></span></td>
</tr>

<tr><!--   4   -->
<th scope="col">제목</th>
<td><input type="text" value="${requestScope.assignment.assignmentTitle }"  name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lec.assignmentTitle" delimiter="//"/></span></td>
</tr>

<tr><!--   5   -->
<th scope="col">내용</th>
<td><textarea rows="15" cols="80"  name="assignmentContent"   placeholder="입력하세요">${requestScope.assignment.assignmentContent}</textarea><span class="error"><form:errors path="lec.assignmentContent" delimiter="//"/></span></td>
</tr>




<tr><!--   6   -->
<th scope="col">파일첨부</th> <!--  assignment.assignmentFile -> 넘겨준이름.vo이름 으로 받아야됨! -->
<td><input type="file" name="upfile" >
</td>
</tr> 





<!-- 히든값 -->
<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}"> <!--  오류났던거 적기 : 수정폼(여기 assignment_modify.jsp에서도 replyStep, replyLevel, replyFamily 히든값으로 넘겨받아야함, 안넘겨받으면 새로운 값으로 등록됨 -->


</tbody>
</table>
	<input id="ok" type="submit" value="수정완료">
	<input type="reset" value="초기화"/> 
</form>



