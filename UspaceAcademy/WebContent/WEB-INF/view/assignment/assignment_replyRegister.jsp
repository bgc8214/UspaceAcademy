<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%><!--  밸리 해주려면 이거선언* -->
<style type="text/css">


</style>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
 //폼체크
 $(document).ready(function(){
	 $("#ok").on("click",function(){
/* 	  	if(!$("input[name=assignmentWriterId]").val()){
			alert("입력하세요");
			return false;
		}
		if(!$("input[name=assignmentWriter]").val()){
			alert("입력하세요");
			return false;
		}  
	 	if(!$("input[name=assignmentTitle]").val()){
			alert("제목을 입력하세요");
			return false;
		} */
		if($("textarea[name=assignmentContent]").val()==""){
			alert("내용을 입력하세요");
			return false;
		}
	});
});
</script>

<h3>내정보 | 과제게시판 | 답글등록</h3>
<hr/>
<form method="POST"      <%-- file --%>enctype="multipart/form-data"       action="/UspaceAcademy/assignment/assignment_replyRegisterSuccess.do?assignmentNo=${assignment.assignmentNo}&lectureNo=${assignment.lectureNo}"> <!--  폼으로 묶기* -->


<table class="table_list" summary="영주" cellpacing="0">
	<caption></caption>
	<tbody>
	
<tr><!-- 1 -->
<th scope="col">아이디</th>
<td><input type="text" name="assignmentWriterId" value="${sessionScope.login_info.studentId}"  readonly="readonly">
</td>
</tr>



<tr><!-- 2 -->
<th>이름</th>
<td><input type="text" name="assignmentWriter" value="${sessionScope.login_info.studentName}"  readonly="readonly">
</td>
</tr>

<tr><!-- 3 -->
<th>제목</th>
<td><input type="text" readonly="readonly" value="RE:${requestScope.assignment.assignmentTitle }"  name="assignmentTitle" size="70" placeholder="제목을 입력하세요" required="required"><span class="error"><form:errors path="lec.assignmentTitle" delimiter="//"/></span>
</td>
</tr>

<tr>
<th></th><!-- 4 -->
<td><textarea rows="15" cols="72"  name="assignmentContent"   placeholder="내용을 입력하세요">${requestScope.assignment.assignmentContent}</textarea><span class="error"><form:errors path="lec.assignmentContent" delimiter="//"/></span>
</td>
</tr>

<tr><!--   file,5   -->
<th scope="col">파일첨부</th>
<td><input type="file" name="upfile"></td>
</tr>



<!--  히든값 -->
<input type="hidden" name="replyStep"  value="${assignment.replyStep}">
<input type="hidden" name="replyLevel"  value="${assignment.replyLevel}">
<input type="hidden" name="replyFamily"  value="${assignment.replyFamily}">
<input type="hidden" name="assignmentDeadline"  value="${assignment.assignmentDeadline}">

</tbody>
</table>

	<input id="ok" type="submit" value="답글작성완료">
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










