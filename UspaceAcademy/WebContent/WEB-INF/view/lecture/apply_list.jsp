<%@ page contentType="text/html;charset=utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>${sessionScope.login_info.studentName }님의 강의 결제목록</h2>
<table border="1">
<thead>
<tr>
	<th>강의번호</th><th>강의과목</th><th>강의명</th><th>강의기간</th><th>수강요일</th><th>강의시간</th>
	<th>수강인원</th>
</tr>
</thead>
<tbody>

<c:forEach items="${requestScope.applyList }" var="apply">
		<tr class="lectureList">
			<td>${apply.lectureNo }</td><td>${apply.lectureSubject }</td><td>${apply.lectureTitle }</td>
			<td>${apply.lectureStartDate } ~ ${apply.lectureEndDate }</td><td>${apply.lectureDay }</td>
			<td>${apply.lectureStartTime } ~ ${apply.lectureEndTime }</td>
			<td>${apply.lectureCurrentStudent } / ${apply.lectureTotalStudent }</td>
		</tr>
</c:forEach>

</tbody>
</table>
<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.page }"><button>강의목록</button></a>