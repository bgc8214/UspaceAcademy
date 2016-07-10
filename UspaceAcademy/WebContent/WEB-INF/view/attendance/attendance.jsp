<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
table#tb {
	width:700px;
	height:100px;
}
</style>
<script type="text/javascript">
	// select 폼 체크
	function selectCheck() {
		var stateSel = $("select[name=attendanceState]");
		var flag = true;
		for(var i=0; i<stateSel.length; i++) {
			if(stateSel[i].selectedIndex == 0){
				flag = false;
				break;
			}
		}
		if(!flag){
			alert("모두 선택");
			return false;
		}
	}
</script> 
<h3 class="pageTlt">출결</h3>
<hr>
<table class="table table-bordered" id="tb">
	<tr>
		<th>일차 | 학생</th>
		<c:forEach items="${requestScope.studentInfoList}" var="list">
			<th align="center">${list.studentName}</th>
		</c:forEach>
		<th>등록</th>
	<c:set value="1" var="cnt"/>
		<c:forEach items="${requestScope.attendanceList}" var="stateList" varStatus="no">
		<input type="hidden" name="day" value="${no.count}">
		<tr>
			<td align="center">${no.count}일차</td>
			<c:forEach items="${stateList}" var="state">
					<td align="center">${state}</td>
<!-- 					<td align="center"> </td> -->
			</c:forEach>
			<td></td>
		</tr>
		<c:set var="cnt" value="${cnt+1}"/>
		</c:forEach>

		<c:if test="${requestScope.diffDays >= cnt}">
		<tr>
			<form action="/UspaceAcademy/attendance/attendanceRegister.do" id="insertForm" method="post" onsubmit="return selectCheck();">
				<input type="hidden" name="day" value="${cnt}">
				<input type="hidden" name="lectureNo" value="${requestScope.lectureNo}">
				<input type="hidden" name="lectureStartDate" value="${requestScope.lecture.lectureStartDate}">
				<input type="hidden" name="lectureEndDate" value="${requestScope.lecture.lectureEndDate}">
				<input type="hidden" name="lectureDay" value="${requestScope.lecture.lectureDay}">
					<td align="center">${cnt}일차</td>
					<c:forEach begin="1" end="${fn:length(studentInfoList)}">
						<td align="center">
							<select id="attendanceState" name="attendanceState">
								<option value="">선택</option>
								<option value="출석">출석</option>
								<option value="지각">지각</option>
								<option value="조퇴">조퇴</option>
								<option value="결석">결석</option>
							</select>
						</td>	
					</c:forEach>

					<c:choose>
						<c:when test="${requestScope.diff<=0 && requestScope.dummy=='0'&& requestScope.dummy1 =='1'}">
							<td align="center"><input id="btn" type="submit" value="등록" class="btn btn-success"></td>
						</c:when>
						<c:otherwise><td></td></c:otherwise>
					</c:choose>
			</form>
		</tr>
		</c:if>
</table>

<p>

<c:choose>
	<c:when test="${sessionScope.memberType=='teacher'}">
		<a href="/UspaceAcademy/member/selectAllByTeacherId.do"><button class="btn btn-info">강의목록</button></a>
		<a href="/UspaceAcademy/attendance/attendanceRedirect.do?startDate=${requestScope.lecture.lectureStartDate}&endDate=${requestScope.lecture.lectureEndDate}&lectureNo=${requestScope.lecture.lectureNo}&lectureDay=${requestScope.lecture.lectureDay}">
		<button class="btn btn-warning">출석수정</button></a>
	</c:when>
</c:choose>


<!-- <a href="/UspaceAcademy/member/teacherAll.do"><button class="btn btn-info">모든 강사 정보</button></a> -->



