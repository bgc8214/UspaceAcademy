<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<style>
	#tb {
		width:500px;
	}
	#th {
		width:100px;
	}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//폼체크
		$("#insert").on("click", function(){
			if(!$("input[name=teacherSalary]").val()){
				alert("숫자를 입력하세요!");
				
				return false;
			}			
		});
	});
</script>
<h3 class="pageTlt">월급</h3>
<hr>
<div align="center">
<form action="/UspaceAcademy/member/updateSalary.do" method="post">
<input type="hidden" name="teacherId" value="${requestScope.teacherDetail.teacherId }">

<table border="1" class="table table-bordered form-table" id="tb">
	<tr>
		<th id="th">ID</th><td>${requestScope.teacherDetail.teacherId }</td>
	</tr>
	<tr>	
		<th>이름</th><td>${requestScope.teacherDetail.teacherName }</td> 
	</tr>
	<tr>
		<th>과목</th>
		<td>${requestScope.teacherDetail.teacherSubject }</td>
	</tr>
	<tr>
		<th>월급</th>
		<td><input type="text" name="teacherSalary" size="70" value="${requestScope.teacherDetail.teacherSalary }">
			<span class="error"> <form:errors path="teacher.teacherSalary" delimiter="//" /></span></td>
	</tr>
</table>

	<input id="insert" type="submit" value="등록" class="btn btn-success">&nbsp;&nbsp;<input type="reset" value="초기화" class="btn btn-info">

</form>
</div>