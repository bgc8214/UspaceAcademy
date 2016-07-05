<%@ page contentType="text/html;charset=utf-8"%>
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

강의 명 : ${requestScope.lecture.lectureTitle }<br>
강의 강사 : ${requestScope.teacher.teacherName }<br>
강의 기간 : ${requestScope.lecture.lectureStartDate } ~ ${requestScope.lecture.lectureEndDate }<br>
강의 시간 : ${requestScope.lecture.lectureStartTime } ~ ${requestScope.lecture.lectureEndTime }<br>
강의 요일 : ${requestScope.lecture.lectureDay}<br>
강의 비용 : ${requestScope.lecture.lecturePrice }<br>


<div align="right">
<a id="zzim" href="/UspaceAcademy/lecture/zzimLecture.do?page=${requestScope.page }&lectureNo=${requestScope.lecture.lectureNo }"><button id="charge" class="btn btn-warning">찜하기</button></a>
<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.page }"><button id="cancel" class="btn btn-success">강의목록</button></a><br>
<font color="red">${param.errorMessage }</font>
</div>