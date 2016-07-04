<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h3 class="pageTlt">${sessionScope.login_info.teacherName} - 강의목록</h3>
<hr>
<script type="text/javascript">
$(document).ready(function() {
	$("#ex").on("click", "tr", function() {
		alert("출결페이지로 이동합니다");
	})
	
});
$(document).ready(effect);
function effect() {
	$("tr:eq(2)").css("background-color", "#DAD9FF");

}
</script>


<table class="table table-responsive">
	<thead>
		<tr>
			<th>강의번호</th>
			<th>강의명</th>
			<th>강의시작일</th>
			<th>강의종료일</th><!-- <th>현재수강인원/총수강인원</th> -->
		</tr>
	</thead>
<tbody id="ex">
<c:forEach items="${requestScope.list}" var="list">
	<tr onclick="location.href='/UspaceAcademy/attendance/attendanceSearch.do?lectureNo=${list.lectureNo}&lectureStartDate=${list.lectureStartDate}&lectureEndDate=${list.lectureEndDate}'" style="cursor:pointer;">
		<td class="bg-success">${list.lectureNo}</td>
		<td class="bg-info">${list.lectureTitle}</td>
		<td class="bg-warning">${list.lectureStartDate}</td>
		<td class="bg-danger">${list.lectureEndDate}</td>
	</tr>
</c:forEach>
</tbody>
</table>