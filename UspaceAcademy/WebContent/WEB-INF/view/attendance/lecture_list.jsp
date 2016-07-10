<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style>
table#tb {
	width:700px;
	height:100px;
}
</style>

<h3 class="pageTlt">${sessionScope.login_info.teacherName} - 강의목록</h3>
<hr>

<table class="table table-responsive" id="tb">
	<thead>

		<th>강의번호</th><th>강의명</th><th>강의시작일</th><th>강의종료일</th><th>출석</th><!-- <th>현재수강인원/총수강인원</th> -->

	</thead>
<tbody>
<c:forEach items="${requestScope.list}" var="list">
	<tr>
		<td class="bg-success">${list.lectureNo}</td>
		<td class="bg-info">${list.lectureTitle}</td>
		<td class="bg-warning">${list.lectureStartDate}</td>
		<td class="bg-danger">${list.lectureEndDate}</td>
		<td align="center"><a href="/UspaceAcademy/attendance/attendanceSearch.do?lectureNo=${list.lectureNo}&lectureStartDate=${list.lectureStartDate}
		&lectureEndDate=${list.lectureEndDate}&lectureDay=${list.lectureDay}"><button class="btn btn-success">출석보기</button></a></td>
	</tr>
</c:forEach>
</tbody>
</table>