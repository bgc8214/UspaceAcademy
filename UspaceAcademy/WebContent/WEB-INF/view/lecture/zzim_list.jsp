<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
.css{
	border:1x solid #ff0080;    /*---테두리 정의---*/
	background-Color:#ffe6f2;   /*--백그라운드 정의---*/
	font:12px 굴림;      /*--폰트 정의---*/
	font-weight:bold;   /*--폰트 굵기---*/
	color:#ff0080;    /*--폰트 색깔---*/
	width:130;height:30;  /*--버튼 크기---*/

}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#submit").on("click", function(){
		return confirm("선택된 항목을 정말 결제하시겠습니까?");
	})

})

</script>

<h2>${sessionScope.login_info.studentName }님의 찜 목록</h2>

<form class="form" method="post" action="/UspaceAcademy/lecture/applyFromZzimList.do?page=${requestScope.page }">
<table border="1">
<thead>
<tr>
	<th>강의선택</th><th>강의번호</th><th>강의과목</th><th>강의명</th><th>강의기간</th><th>수강요일</th><th>강의시간</th>
	<th>수강인원</th>
</tr>
</thead>
<input type="hidden" name="page" value="${requestScope.page }">
<tbody>
	<c:forEach items="${requestScope.zzimList }" var="zzim">
			<tr class="lectureList">
				<td><input class="checkbox" type="checkbox" name="applyList" value="${zzim.lectureNo }"></td><td>${zzim.lectureNo }</td><td>${zzim.lectureSubject }</td><td>${zzim.lectureTitle }</td>
				<td>${zzim.lectureStartDate } ~ ${zzim.lectureEndDate }</td><td>${zzim.lectureDay }</td>
				<td>${zzim.lectureStartTime } ~ ${zzim.lectureEndTime }</td>
				<td>${zzim.lectureCurrentStudent } / ${zzim.lectureTotalStudent }</td>
			</tr>
	</c:forEach>
</tbody>
</table>
<input class="css" id="submit" type="submit" value="일괄신청하기"><a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.page }"><input class="css" type="button" value="강의목록"></a>
</form>

