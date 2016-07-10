<%@ page contentType="text/html;charset=utf-8"%>
<style>
table#tb {
	width: 700px;
	heiht: 100px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){
	$("#zzim").on("click", function(){
		return confirm("정말 찜하시겠습니까?");
	})
		
})
</script>

<h3 class="pageTlt">찜하기 폼</h3>
<hr>
<table class="table table-bordered" id="tb">
<tr>
	<th>강의명</th>
	<td> ${requestScope.lecture.lectureTitle }</td>
</tr>
<tr>
	<th>강의 강사</th>
	<td> ${requestScope.teacher.teacherName }</td>
</tr>
<tr>
	<th>강의 기간</th>
	<td>${requestScope.lecture.lectureStartDate } ~ ${requestScope.lecture.lectureEndDate }</td>
<tr>
	<th>강의 시간 </th>
	<td>${requestScope.lecture.lectureStartTime } ~ ${requestScope.lecture.lectureEndTime }</td>
</tr>
<tr>
	<th>강의 요일</th>
	<td>${requestScope.lecture.lectureDay}</td>
</tr>
<tr>
	<th>수강료</th>
	<td>${requestScope.lecture.lecturePrice }</td>
</table>

<div align="right">
<a id="zzim" href="/UspaceAcademy/lecture/zzimLecture.do?page=${requestScope.page }&lectureNo=${requestScope.lecture.lectureNo }"><button id="charge" class="btn btn-warning">찜하기</button></a>
<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.page }"><button id="cancel" class="btn btn-success">강의목록</button></a><br>
<font color="red">${param.errorMessage }</font>
</div>