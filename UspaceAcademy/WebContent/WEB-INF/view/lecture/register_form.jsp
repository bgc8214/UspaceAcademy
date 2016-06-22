<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!--  script type="text/javascript" src="/jQuery/jQuery.js"></script>
<script type="text/javascript" src="/jQuery/jquery-ui.min.js"></script>
<link href="/jQuery/jquery-ui.min.css" rel="stylesheet">
<link href="/jQuery/jquery-ui.structure.min.css" rel="stylesheet">
<link href="/jQuery/jquery-ui.theme.min.css" rel="stylesheet">

<script type="text/javascript">
$(document).ready(function(){
	$("#datepicker1").datepicker({
		showMonthAfterYear:true,
		minDate:'-0d',
		yearSuffix:'년',
		monthNames:['1월','2월','3월','4월','5월','6월','7월','8월'
		            ,'9월','10월', '11월','12월'],
		dayNamesMin:['일','월','화','수','목','금','토'],
			xxonSelect:function(dateText, datePicker){
				var mm = (datePicker.selectedMonth+1);
				var dd = datePicker.selectDay;
				
				if(mm<10) mm="0"+mm;
				if(dd<10) dd="0"+dd;
					$("#bt1").val(datePicker.selectedYear +"년"+mm+"월"+dd+"일");    
			}
	});
})
</script-->

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

<h2>강의 등록폼</h2>
<form action="" method="post">
강의명 : <input type="text" name="lectureTitle"><br>
강의 설명 : <textarea rows="10" cols="20"></textarea><br>
강의 시작시간 : <input type="text" name="lectureStartTime"><br>
강의 끝시간 : <input type="text" name="lectureEndTime"><br>
강의 요일 : <input type="text" name="lectureDay"><br>
강의 수강료 : <input type="text" name="lecturePrice"><br>
강의 수강인원 : <input type="text" name="lectureTotalStudent"><br> 
<input type="hidden" name="lectureCurrentStudent" value="0">

<div id="datepicker1"></div><br>
강의 시작일 : <input id="bt1" type="text" name="lectureStartDate"><br>
<div id="datepicker2"></div><br>
강의 종료일 : <input id="bt2" type="text" name="lectureEndDate"><br>
강의 종류 : 
<select id="subject" name="lectureSubject">
	<c:forEach items="${requestScope.codeList }" var="code">
		<option>${code.codeName }</option>
	</c:forEach>
</select>

강의 강사 : 
<select id="teacher" name="teacherId2">

</select>
<input type="submit" value="강의 등록">
</form>