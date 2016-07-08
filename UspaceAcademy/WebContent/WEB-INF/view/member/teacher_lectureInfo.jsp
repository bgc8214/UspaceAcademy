<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">

$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "violet");

}
</script>

<table border="4" class="table table-bordered">

<thead>
<tr>
	<th>강의번호</th>
	<th>강의과목</th>
	<th>강의명</th>
	<th>강사</th>
	<th>강의기간</th>
	<th>수강요일</th>
	<th>강의시간</th>
	<th>수강인원</th>
	<th>출석</th>
	<th>과제게시판</th>
</tr>
</thead>
<tbody>
	<c:forEach items="${requestScope.lectureList }" var="lectureList">
			<tr class="lectureList">
				<td align="center">${lectureList.lectureNo }</td>
				<td>${lectureList.lectureSubject }</td>
				<td>${lectureList.lectureTitle }</td>
				<td align="center" class="teacherId">${lectureList.teacherId2 }</td>
				<td>${lectureList.lectureStartDate } ~ ${lectureList.lectureEndDate }</td>
				<td align="center">${lectureList.lectureDay }</td>
				<td>${lectureList.lectureStartTime }시 ~ ${lectureList.lectureEndTime }시</td>
				
				<td align="center"><span class="current">${lectureList.lectureCurrentStudent }</span> / <span class="total">${lectureList.lectureTotalStudent }</span></td>
				<td align="center"><a href="/UspaceAcademy/attendance/attendanceSearch.do?lectureNo=${lectureList.lectureNo}&lectureStartDate=${lectureList.lectureStartDate}
				&lectureEndDate=${lectureList.lectureEndDate}&lectureDay=${lectureList.lectureDay}"><button class="btn btn-success">출석보기</button></a></td>
				<td align="center"><a href="/UspaceAcademy/assignment/assignment_list.do?lectureNo=${lectureList.lectureNo}"><button>과제게시판</button></a></td>

			</tr>
	</c:forEach>
</tbody>
</table>