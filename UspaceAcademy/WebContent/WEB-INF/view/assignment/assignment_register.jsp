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

<h3>내정보 | 과제게시판 | 등록하기</h3>
<hr/>																																												<!--  ★여기assignment_registerSuccess까지만있었움,,,,,  -->
<form method="POST"       <%-- file --%>enctype="multipart/form-data"     action="/UspaceAcademy/assignment/assignment_registerSuccess.do"> <!--  폼으로 묶기* -->


<table class="table_list" summary="영주" cellpacing="0">
	<caption></caption>
	<tbody>
	



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
<input type="hidden"  name="lectureNo" value="${requestScope.lectureNo}">




</tbody>
</table>
	<input id="ok" type="submit" value="과제등록완료">
	<input type="reset" value="초기화"/> 
</form>







