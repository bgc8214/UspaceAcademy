<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3>학생의 강의 정보 page</h3><p>

<table border="3" width="1500" align="center">
	<thead>
		<th>강의번호</th>
		<th>강의</th>
		<th>강의요일</th>
		<th>강의시작일</th>
		<th>강의종료일</th>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.studentLectureInfo}" var="studentInfo">
			<tr>
				<td align="center">${studentInfo.LECTURE_NO}</td>
				<td align="center"><a href="/UspaceAcademy/attendance/attendanceInfo.do?lectureNo2=${studentInfo.LECTURE_NO}">${studentInfo.LECTURE_TITLE}</a></td>
				<td align="center">${studentInfo.LECTURE_DAY}</td>
				<td align="center">${studentInfo.LECTURE_START_DATE}</td>
				<td align="center">${studentInfo.LECTURE_END_DATE}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>