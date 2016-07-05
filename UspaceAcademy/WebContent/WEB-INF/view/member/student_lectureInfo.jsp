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
		<th>출석</th>
		<th>강의질문 게시판으로 이동</th>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.studentLectureInfo}" var="studentInfo">
			<tr>
				<td align="center">${studentInfo.LECTURE_NO}</td>
				<td align="center">${studentInfo.LECTURE_TITLE}</td>
				<td align="center">${studentInfo.LECTURE_DAY}</td>
				<td align="center">${studentInfo.LECTURE_START_DATE}</td>
				<td align="center">${studentInfo.LECTURE_END_DATE}</td>
				<td align="center"><a href="/UspaceAcademy/attendance/attendanceInfo.do?lectureNo2=${studentInfo.LECTURE_NO}"><button>출석보기</button></a></td>
				<td align="center"><a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${studentInfo.LECTURE_NO}"><button>강의질문하기</button></a></td>
			</tr>
		</c:forEach>
	</tbody>
</table>