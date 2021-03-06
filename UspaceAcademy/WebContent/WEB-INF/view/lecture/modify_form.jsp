<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
span.errors {
	color: red
}

.ui-datepicker-calendar>tbody td:first-child a {
	COLOR: #f00;
}

.ui-datepicker-calendar>tbody td:last-child a {
	COLOR: blue;
}
table#tb {
	width: 700px;
	heiht: 100px;
}

</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>

<link href="/UspaceAcademy/jQuery/jquery-ui.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.structure.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.theme.min.css" rel="stylesheet">

<!-- timepicker -->
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css" /> 

<script type="text/javascript">
	$(document).ready(function(){
		// 자바에서 현재날짜 가져오기
		var dt = new Date();
		var yy = dt.getFullYear();
		var mm = dt.getMonth()+1;
		var dd = dt.getDate();
		var h = dt.getHours();
		if(h>=24) dd++;
		mindt=yy+"/"+mm+"/"+dd;
		
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
		
		$(function(){
			$("#bt1").datepicker({
				changeYear: true,
				changeMonth: true,
				showMonthAfterYear:true,
				dateFormat:'yy/mm/dd',
				dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
		        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
				minDate : mindt
		  	});
			$("#bt2").datepicker({
				changeYear: true,
				changeMonth: true,
				showMonthAfterYear:true,
				dateFormat:'yy/mm/dd',
				dayNames: ['일요일', '월요일', '화요일', '수요일', '목요일', '금요일', '토요일'],
		        dayNamesMin: ['일', '월', '화', '수', '목', '금', '토'], 
		        monthNamesShort: ['1','2','3','4','5','6','7','8','9','10','11','12'],
		        monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'],
		        minDate : mindt,
		    	onClose :function(selectedDate) {
		       		var bt1 = $("#bt1").val();
		       		if(bt1>selectedDate) {
		       			alert("강의종료일이 강의시작일보다 이전일 수는 없습니다.");
		       			$('#bt2').val(bt1);
		       		}
		       	}
			});
		});
		
		$(function(){
			var str = $("#lectureDayString").val();
			for(var i=0;i<str.length;i++){
				if((str.charAt(i))=='월'){
					$("#mon").prop("checked", "checked");
				}else if((str.charAt(i))=='화'){
					$("#tue").prop("checked", "checked");
				}else if((str.charAt(i))=='수'){
					$("#wed").prop("checked", "checked");
				}else if((str.charAt(i))=='목'){
					$("#thu").prop("checked", "checked");
				}else if((str.charAt(i))=='금'){
					$("#fri").prop("checked", "checked");
				}else if((str.charAt(i))=='토'){
					$("#sat").prop("checked", "checked");
				}else if((str.charAt(i))=='일'){
					$("#sun").prop("checked", "checked");
				}
				
			}	
		});
		
		// timepicker
		$(function() {
			$('#lectureStartTime')
		    	.timepicker({timeFormat:'H:i', 'minTime':'09:00','maxTime': '23:00','scrollDefaultNow': true })  //lectureStartTime 시작 기본 설정
		   	 	.on('changeTime', function() {                            //lectureStartTime 을 선택한 후 동작
		        
		   	 	var from_time = $("input[name='lectureStartTime']").val();  //lectureStartTime 값을 변수에 저장
		        $('#lectureEndTime').timepicker('option', 'minTime', from_time); //lectureEndTime의 mintime 지정
		        if ($('#lectureEndTime').val() && $('#lectureEndTime').val() < from_time) { 
		            $('#lectureEndTime').timepicker('setTime', from_time);
					//lectureEndTime을 먼저 선택한 경우 그리고 lectureEndTime시간이 lectureStartTime시간보다 작은경우 lectureEndTime시간 변경
		        }	
		    });

			$('#lectureEndTime').timepicker({timeFormat:'H:i','minTime':'09:00','maxTime': '23:00'}); //lectureEndTime 시간 기본 설정
		})
		
	})
</script>


<h3 class="pageTlt">강의 수정폼</h3>
<hr>
<form action="/UspaceAcademy/lecture/modifyLectureByNo.do?page=${param.page}&lectureNo=${requestScope.lecture.lectureNo}" method="post">
	<table class="table" id="tb">
		<tr>
			<th>강의명</th>
			<td><input type="text" name="lectureTitle"
				value="${requestScope.lecture.lectureTitle }"><span
				class="errors"><form:errors path="lectureForm.lectureTitle"
						delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>강의설명</th>
			<td><textarea rows="10" cols="20" name="lectureDescription"
					class="form-control">${requestScope.lecture.lectureDescription }</textarea><span
				class="errors"><form:errors path="lectureForm.lectureDescription" delimiter="//" /></span>
			</td>
		</tr>
		<tr>
			<th>강의 시작시간</th>
			<td><input type="text" id="lectureStartTime"
				name="lectureStartTime"
				value="${requestScope.lecture.lectureStartTime }"><span
				class="errors"><form:errors
						path="lectureForm.lectureStartTime" delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>강의 종료시간</th>
			<td><input type="text" id="lectureEndTime" name="lectureEndTime"
				value="${requestScope.lecture.lectureEndTime }"><span
				class="errors"><form:errors path="lectureForm.lectureEndTime"
						delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>강의 요일</th>
			<td>
				<div id="dummy">
					<label> 월 <input id="mon" type="checkbox"
						name="lectureDay2" value='월'></label> <label> 화 <input
						id="tue" type="checkbox" name="lectureDay2" value='화'></label> <label>
						수 <input id="wed" type="checkbox" name="lectureDay2" value='수'>
					</label> <label> 목 <input id="thu" type="checkbox"
						name="lectureDay2" value='목'></label> <label> 금 <input
						id="fri" type="checkbox" name="lectureDay2" value='금'></label> <label>
						토 <input id="sat" type="checkbox" name="lectureDay2" value='토'>
					</label> <label> 일 <input id="sun" type="checkbox"
						name="lectureDay2" value='일'></label> <font color="red">${param.errorMessage }</font><span
						id="lecDay"></span>
				</div>
			</td>
		</tr>
		<tr>
			<th>수강료</th>
			<td><input type="text" name="lecturePrice"
				value="${requestScope.lecture.lecturePrice }"><span
				class="errors"><form:errors path="lectureForm.lecturePrice"
						delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>수강인원</th>
			<td><input type="text" name="lectureTotalStudent"
				value="${requestScope.lecture.lectureTotalStudent }"><span
				class="errors"><form:errors
						path="lectureForm.lectureTotalStudent" delimiter="//" /></span></td>
		</tr>
		<input type="hidden" name="lectureCurrentStudent" value="0">
		<span class="errors"><form:errors
				path="lectureForm.lectureCurrentStudent" delimiter="//" /></span>
		<input type="hidden" id="lectureNo"
			value="${requestScope.lecture.lectureNo }">
		<input type="hidden" id="lectureDayString"
			value="${requestScope.lecture.lectureDay }">
		<tr>
			<th>강의 시작일</th>
			<td><input readonly="readonly" id="bt1" type="text"
				name="lectureStartDate"
				value="${requestScope.lecture.lectureStartDate }"><br>
			<span class="errors"><form:errors
						path="lectureForm.lectureStartDate" delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>강의 종료일</th>
			<td><input readonly="readonly" id="bt2" type="text"
				name="lectureEndDate"
				value="${requestScope.lecture.lectureEndDate }"><br>
			<span class="errors"><form:errors
						path="lectureForm.lectureEndDate" delimiter="//" /></span></td>
		</tr>
		<tr>
			<th>강의 종류</th>
			<td><select id="subject" name="lectureSubject">
					<c:forEach items="${requestScope.codeList }" var="code">
						<c:choose>
							<c:when
								test="${code.codeName==requestScope.lecture.lectureSubject }">
								<option selected="selected">${code.codeName }</option>
							</c:when>
							<c:otherwise>
								<option>${code.codeName }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select></td>
		</tr>
		<tr>
			<th>강사</th>
			<td>
			<select id="teacher" name="teacherId2">
					<c:forEach items="${requestScope.teacherList }" var="teacher">
						<c:choose>
							<c:when
								test="${teacher.teacherId==requestScope.lecture.teacherId2 }">
								<option selected="selected" value="${teacher.teacherId }">${teacher.teacherName }</option>
							</c:when>
							<c:otherwise>
								<option value="${teacher.teacherId }">${teacher.teacherName }</option>
							</c:otherwise>
						</c:choose>
					</c:forEach>
			</select>
	</table>
	<div align="right">
		<input type="submit" value="강의 수정" id="btn" class="btn btn-warning">	
	</div>
</form>