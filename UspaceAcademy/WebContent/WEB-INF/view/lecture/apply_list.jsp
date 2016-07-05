<%@ page contentType="text/html;charset=utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<script type="text/javascript">
$(document).ready(function(){
	$("#cancel").on("click", function(){
		return confirm("정말 취소하시겠습니까?");
	})

})
</script>

<h3 class="pageTlt">${sessionScope.login_info.studentName }님의 강의 결제목록</h3>
<hr>
<table border="1" class="table">
<thead>
<tr>
	<th>강의번호</th><th>강의과목</th><th>강의명</th><th>강의기간</th><th>수강요일</th><th>강의시간</th>
	<th>수강인원</th><th>결제취소하기</th>
</tr>
</thead>
<tbody>

<c:forEach items="${requestScope.applyList }" var="apply">
		<tr class="lectureList">
			<td>${apply.lectureNo }</td><td>${apply.lectureSubject }</td><td>${apply.lectureTitle }</td>
			<td>${apply.lectureStartDate } ~ ${apply.lectureEndDate }</td><td>${apply.lectureDay }</td>
			<td>${apply.lectureStartTime } ~ ${apply.lectureEndTime }</td>
			<td>${apply.lectureCurrentStudent } / ${apply.lectureTotalStudent }</td>
			<td align="center"><a href="/UspaceAcademy/lecture/removeLectureFromApplyList.do?lectureNo=${apply.lectureNo }"><button id="cancel" class="btn btn-danger">결제 취소</button></a></td>
		</tr>
</c:forEach>

</tbody>
</table>
<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.page }"><button class="btn btn-info">강의목록</button></a>