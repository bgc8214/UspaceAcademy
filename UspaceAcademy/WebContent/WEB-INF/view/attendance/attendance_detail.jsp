<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<h1>출석부 조회&수정 페이지!!(총 강좌 일수 - ${requestScope.diffDays })</h1><p>
<table border="5" width="1500">
	<tr>
		<th>일차 | 학생</th>
		<c:forEach items="${requestScope.studentInfoList}" var="list">
			<td align="center">${list.studentName}</td>
		</c:forEach>
		<th>등록</th>
		<c:forEach items="${requestScope.attendanceList}" var="stateList" varStatus="no">
		<form action="/UspaceAcademy/attendance/updateAttendance.do?lectureNo=${requestScope.lectureNo}" method="post">
		<input type="hidden" name="day" value="${no.count}">
		<input type="hidden" name="startDate" value="${requestScope.startDate}">
		<input type="hidden" name="endDate" value="${requestScope.endDate}">
		<tr>
			<td>${no.count}일차</td>
			<c:forEach items="${stateList}" var="state">
			<td>
				<select name="attendanceState" id="attendanceState">
						<option value="출석" ${state == '출석'? 'selected=selected':'' }>출석</option>
						<option value="지각" ${state == '지각'? 'selected=selected':'' }>지각</option>
						<option value="조퇴" ${state == '조퇴'? 'selected=selected':'' }>조퇴</option>
						<option value="결석" ${state == '결석'? 'selected=selected':'' }>결석</option>
				</select>
			</td>
			</c:forEach>
			<td><input id="btn" type="submit" value="수정"></td>
		</tr>
		</form>
		</c:forEach>
</table>
<p>
<a href="/UspaceAcademy/attendance/attendanceList.do"><button>강의 목록</button></a>
