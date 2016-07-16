<%@ page contentType ="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3 class="pageTlt">${requestScope.teacherDetail.teacherName } 강사님 월급 정보</h3>
<hr>

<table border='1' class="table table-bordered table-hover" id="tb">
	<thead>
		<tr>
			<!-- <td>글번호</td> -->			
			<td>아이디</td>
			<td>이름</td>
			<td>과목</td>
			<td>월급</td>
		</tr>
	</thead>
			
	<tbody>
			<%-- <input type="hidden" id="secret" value="${list.advancedSecret}"> --%>
			<tr>
				<%-- <td>${list.advancedNo }</td> --%>
				<td>${requestScope.teacherDetail.teacherId }</td>
				<td>${requestScope.teacherDetail.teacherName }</td>
				<td>${requestScope.teacherDetail.teacherSubject }</td>
				<td>
					<fmt:formatNumber value="${requestScope.teacherDetail.teacherSalary}" pattern="#,###원"/>
				</td>
			</tr> 
	</tbody>
	
</table>
<p>
<c:choose>
	<c:when test="${sessionScope.memberType=='administrator'}">

		<div align="right">
			<a href="/UspaceAcademy/member/updateSalaryForm.do?teacherId=${requestScope.teacherDetail.teacherId }"><button class="btn btn-success">월급 등록</button></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<a href="/UspaceAcademy/member/updateSalaryForm.do?teacherId=${requestScope.teacherDetail.teacherId }"><button class="btn btn-warning">월급 수정</button></a>
		</div>
	</c:when>
</c:choose>
	