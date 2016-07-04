<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>출결상태</h3><p>
<table border="3" width="300">
<thead>
	<th>일차</th>
	<th>출결</th>
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
