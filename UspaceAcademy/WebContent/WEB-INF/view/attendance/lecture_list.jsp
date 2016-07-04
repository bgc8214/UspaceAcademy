<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h4>${sessionScope.login_info.teacherName} - 강의목록</h4>
<table border="4">
	<thead>
		<th>강의번호</th><th>강의명</th><th>강의시작일</th><th>강의종료일</th><!-- <th>현재수강인원/총수강인원</th> -->
	</thead>
<tbody>
<c:forEach items="${requestScope.list}" var="list">
	<tr>
		<td align="center">${list.lectureNo}</td>
		<td><a href="/UspaceAcademy/attendance/attendanceSearch.do?lectureNo=${list.lectureNo}&lectureStartDate=${list.lectureStartDate}&lectureEndDate=${list.lectureEndDate}">${list.lectureTitle}</a></td>
		<td>${list.lectureStartDate}</td>
		<td>${list.lectureEndDate}</td>
	</tr>
</c:forEach>
</tbody>
</table>