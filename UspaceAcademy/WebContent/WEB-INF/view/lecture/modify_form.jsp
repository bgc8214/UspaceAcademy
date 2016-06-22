<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#subject").on("change",function(){
			var tmp = $(this);
			$.ajax({
				"url":"/UspaceAcademy/lecture/getTeacherBySubject.do", //요청 URL
				"type":"POST", //HTTP 요청방식
				"data":"teacherSubject="+tmp.val(), //요청 파라미터 설정 - queryString(n=v&n=v) /  Javascript 객체{n:v,n:v}
				"dataType":"json", //응답데이터 타입 지정. text는 default
				"success":function(list){
					for(var i=0;i<list.length;i++){
						var teacherId = list[i].teacherId;
						var teacherName = list[i].teacherName;
							$("#teacher").append("<option value="+teacherId+">"+teacherName+"</option>");
					}
				},
				"error":function(xhr, status, errorMsg){
					alert("오류가 발생했습니다."+status+", "+errorMsg);
				},
				"beforeSend":function(){
					$("#teacher").empty();
				}
			})
			
		})
		
	})
</script>


<h2>강의 수정폼</h2>
<form action="/UspaceAcademy/lecture/modifyLectureByNo.do?lectureNo=${requestScope.lecture.lectureNo}" method="post">
강의명 : <input type="text" name="lectureTitle" value="${requestScope.lecture.lectureTitle }"><br>
강의 설명 : <textarea rows="10" cols="20" name="lectureDescription">${requestScope.lecture.lectureDescription }</textarea><br>
강의 시작시간 : <input type="text" name="lectureStartTime" value="${requestScope.lecture.lectureStartTime }"><br>
강의 끝시간 : <input type="text" name="lectureEndTime" value="${requestScope.lecture.lectureEndTime }"><br>
강의 요일 : <input type="text" name="lectureDay" value="${requestScope.lecture.lectureDay }"><br>
강의 수강료 : <input type="text" name="lecturePrice" value="${requestScope.lecture.lecturePrice }"><br>
강의 수강인원 : <input type="text" name="lectureTotalStudent" value="${requestScope.lecture.lectureTotalStudent }"><br> 
<input type="hidden" name="lectureCurrentStudent" value="0">

<input type="hidden" id="lectureNo" value="${requestScope.lecture.lectureNo }">

<div id="datepicker1"></div><br>
강의 시작일 : <input id="bt1" type="text" name="lectureStartDate" value="${requestScope.lecture.lectureStartDate }"><br>
<div id="datepicker2"></div><br>
강의 종료일 : <input id="bt2" type="text" name="lectureEndDate" value="${requestScope.lecture.lectureEndDate }"><br>
강의 종류 : 
<select id="subject" name="lectureSubject">
	<c:forEach items="${requestScope.codeList }" var="code">
		<c:choose>
			<c:when test="${code.codeName==requestScope.lecture.lectureSubject }">
				<option selected="selected">${code.codeName }</option>
			</c:when>
			<c:otherwise>
				<option >${code.codeName }</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>

강의 강사 : 
<select id="teacher" name="teacherId2">
	<c:forEach items="${requestScope.teacherList }" var="teacher">
		<c:choose>
			<c:when test="${teacher.teacherId==requestScope.lecture.teacherId2 }">
				<option selected="selected" value="${teacher.teacherId }">${teacher.teacherName }</option>
			</c:when>
			<c:otherwise>
				<option value="${teacher.teacherId }">${teacher.teacherName }</option>
			</c:otherwise>
		</c:choose>
	</c:forEach>
</select>
<input type="submit" value="강의 수정">
</form>