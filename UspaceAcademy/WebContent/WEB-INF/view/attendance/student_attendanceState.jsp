<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="pageTlt">출결 상태</h3>
<hr>
<table class="table table-bordered" id="tb">
<thead>
	<tr>
		<th>일차</th>
		<th>출결</th>
	</tr>
</thead>
<tbody>
	<c:forEach items="${requestScope.attendanceStateList}" var="attendance">	
		<tr>
			<td align="center" width="150">${attendance.lectureDay}</td>
			<td align="center" width="150">${attendance.attendanceState}</td>
		</tr>
	</c:forEach>
	<c:if test="${requestScope.attendanceStateList.size()==0}">
		<td colspan="2" align="center">입력된 출결정보가 없습니다.</td>
	</c:if>
	</tbody>
</table>

<p>
<a href="/UspaceAcademy/attendance/studentLectureInfo.do"><button class="btn btn-info">내 강의 목록</button></a>
