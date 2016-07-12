<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
table#tb{
	width:1000px;
	height:100px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<h3 class="pageTlt">강의</h3>
<hr>
<table class="table table-bordered" id="tb">

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
	<th>강의질문 게시판으로 이동</th>
	<th>과제게시판으로 이동</th>
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
				<td align="center">
					<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${lectureList.lectureNo}&lectureTitle=${lectureList.lectureTitle }"><button class="btn btn-primary">강의 질문 게시판</button></a>
				</td>
				<td align="center"><a href="/UspaceAcademy/assignment/assignment_list.do?lectureNo=${lectureList.lectureNo}"><button class="btn btn-warning">과제게시판</button></a></td>

			</tr>
	</c:forEach>
</tbody>
</table>