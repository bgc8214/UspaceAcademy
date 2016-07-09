<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	
$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "palegreen");

}

</script>

<h2 class="pageTlt">월급 관리</h2><br>

<table border='1' class="table table-bordered table-hover">
	<thead>
		<tr>
			<!-- <td>글번호</td> -->	
			<td>아이디</td>		
			<td>이름</td>			
			<td>강의명</td>
			<td>월급</td>
		</tr>
	</thead>
			
	<tbody>
		<c:forEach items="${requestScope.teacherSalaryList}" var="list">
			<tr>
				<td>${list.teacherId }</td>
				<td>${list.teacherName }</td>
				<%-- <c:forEach items="${requestScope.teacherSalaryList.lectureList}" var="lectureList"> --%>
				<td>${list.teacherSubject }</td>
				<%-- </c:forEach> --%>
				<td>${list.teacherSalary }</td>
			</tr>	
 		</c:forEach>
	</tbody>
	
</table>

<br>


<a href="/UspaceAcademy/member/insertSalaryForm.do">월급 등록</a>




	