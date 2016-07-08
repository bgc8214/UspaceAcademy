<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
/* 	$(document).ready(function() {
		$("#ex").on("click", "tr", function() {
			alert("강의 출결정보 페이지로 이동");
		})
	}) */
	
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#6CC0FF");
	
	}

</script>
<h3 class="pageTlt">학생의 강의 정보 page</h3>
<hr>

<table border="3" width="1500" align="center" class="table table-bordered">
	<thead>
		<th>강의번호</th>
		<th>강의</th>
		<th>강의요일</th>
		<th>강의시작일</th>
		<th>강의종료일</th>
		<th>출석</th>
		<th>강의질문 게시판으로 이동</th>
	</thead>
	<tbody id="ex">
		<c:forEach items="${requestScope.studentLectureInfo}" var="studentInfo">
			<tr style="cursor:pointer">
				<td align="center">${studentInfo.LECTURE_NO}</td>
				<td align="center">${studentInfo.LECTURE_TITLE}</td>
				<td align="center">${studentInfo.LECTURE_DAY}</td>
				<td align="center">${studentInfo.LECTURE_START_DATE}</td>
				<td align="center">${studentInfo.LECTURE_END_DATE}</td>
				<td align="center"><a href="/UspaceAcademy/attendance/attendanceInfo.do?lectureNo2=${studentInfo.LECTURE_NO}"><button>출석보기</button></a></td>
				<td align="center"><a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${studentInfo.LECTURE_NO}&lectureTitle=${studentInfo.LECTURE_TITLE}"><button>강의질문하기</button></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>
