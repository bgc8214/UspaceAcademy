<%@ page contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){
	$("#charge").on("click", function(){
		return confirm("정말 결제하시겠습니까?");
	})
		
	
})
</script>


<h2>결제하기 폼</h2>

강의 명 : ${requestScope.lecture.lectureTitle }<br>
강의 강사 : ${requestScope.teacher.teacherName }<br>
강의 기간 : ${requestScope.lecture.lectureStartDate } ~ ${requestScope.lecture.lectureEndDate }<br>
강의 시간 : ${requestScope.lecture.lectureStartTime } ~ ${requestScope.lecture.lectureEndTime }<br>
강의 요일 : ${requestScope.lecture.lectureDay}<br>
강의 비용 : ${requestScope.lecture.lecturePrice }<br>
<a id="charge" href="/UspaceAcademy/lecture/chargeLecture.do?page=${param.page }&lectureNo=${requestScope.lecture.lectureNo }"><button id="charge">결제하기</button></a>&emsp;&emsp;&emsp;
<a href="/UspaceAcademy/lecture/lectureList.do?page=${param.page }"><button id="cancel">강의목록</button></a><br>
<font color="red">${param.errorMessage }</font>
