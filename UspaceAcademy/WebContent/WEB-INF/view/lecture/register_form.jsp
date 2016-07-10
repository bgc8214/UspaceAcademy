
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
<script type="text/javascript"
	src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>

<link href="/UspaceAcademy/jQuery/jquery-ui.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.structure.min.css"
	rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.theme.min.css"
	rel="stylesheet">

<!-- 추가적 timepicker -->
<!--  <script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<!--  datepicker가 안되는 원인 -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jquery-timepicker/1.10.0/jquery.timepicker.css" />
<script type="text/javascript">


		// 자바에서 현재날짜 가져오기
		var dt = new Date();
		var yy = dt.getFullYear();
		var mm = dt.getMonth()+1;
		var dd = dt.getDate();
		var h = dt.getHours();
		if(h>=24) dd++;
		mindt=yy+"/"+mm+"/"+dd;


		$(document).ready(function(){
			//폼체크
			$("#submit1").on("click", function(){
				if(!$("input[name=lectureTitle]").val()){
					alert("강의명을 입력하세요.");
					return false;
				}
				if($("textarea[name=lectureDescription]").val()==""){
					alert("강의세부내용을 입력하세요.");
					return false;
				}
				if(!($("input[name=lectureStartTime]").val())){
					alert("강의 시작시간을 선택하세요.");
					return false;
				}

				if(!($("input[name=lectureEndTime]").val())){
					alert("강의 종료시간을 선택하세요.");
					
					return false;
				}
				//요일 검증 시작
				var flag = false;
				$("input[type=checkbox]").each(function(index, item){
					
					if(item.checked==true){
						flag = true;
					}
				})
				if(!flag){
					alert("한 개 이상의 요일을 선택하세요!!");
					return false;
				}else{
					return true;
				}
				//요일 검증 끝
				if(isNaN($("input[name=lecturePrice]").val())){
					alert("수강료는 숫자만 입력하세요.");
					return false;
				}
				if(isNaN($("input[name=lectureTotalStudent]").val())){
					alert("수강인원은 숫자만 입력하세요.");
					return false;
				}
				if(!$("input[name=lectureStartDate]").val()){
					alert("강의 시작일을 선택하세요.");
					return false;
				}
				if(!$("input[name=lectureStartDate]").val()){
					alert("강의 종료일을 선택하세요.");
					return false;
				}
			})
			
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
				
			})//등록할 때 강사 이름, 과목 관련 스크립트
			
			
		});
		
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
				monthNames: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월'] ,
				minDate : mindt,
				onClose :function(selectedDate) {
					var bt1 = $("#bt1").val();
					if(bt1>selectedDate) {
						alert("강의종료일이 강의시작일보다 이전일 수는 없습니다.");
						$('#bt2').val(bt1);
					}
				}
			}) 	
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
	</script>

<h3 class="pageTlt">강의 등록폼</h3>
<hr>
<div align="center">
<form action="/UspaceAcademy/lecture/registerLecture.do" method="post" class="form-horizontal">
<table class="table" id="tb">
	<tr>
		<th>강의명  </th>
		<td><input type="text" name="lectureTitle"><span class="errors"><form:errors path="lecture.lectureTitle" delimiter="//" /></span></td>
	</tr>
	<tr>
		<th>강의설명  </th>
		<td><textarea name="lectureDescription" class="form-control"></textarea><span class="errors"><form:errors path="lecture.lectureDescription" delimiter="//" /></span></td>
	</tr>
	<tr> 
		<th>강의 시작시간</th>
		<td><input type="text" id="lectureStartTime" name="lectureStartTime"><span class="errors"><form:errors path="lecture.lectureStartTime" delimiter="//" /></span></td>	
	</tr>
	<tr>
		<th>강의 종료시간</th>
		<td><input type="text" id="lectureEndTime" name="lectureEndTime"><span class="errors"><form:errors path="lecture.lectureEndTime" delimiter="//" /></span></td>
	</tr>
	<tr>
		<th>강의 요일</th>
		<td>
		<div id="dummy">
		 	일<input type="checkbox" name="lectureDay2" value='일'>

		 	월 <input type="checkbox" name="lectureDay2" value='월'>
		
			 화 <input type="checkbox" name="lectureDay2" value='화'>
		
			수 <input type="checkbox" name="lectureDay2" value='수'>	
		
			 목 <input type="checkbox" name="lectureDay2" value='목'>
		
			금 <input type="checkbox" name="lectureDay2" value='금'>	
		
			토 <input type="checkbox" name="lectureDay2" value='토'>
				<font color="red">${param.errorMessage }</font></div>
		</td>
		
	</tr>
	<tr>
		<th>수강료</th>
		<td><input type="text" name="lecturePrice"><span class="errors"><form:errors path="lecture.lecturePrice" delimiter="//" /></span></td>
 	</tr>
 	<tr>
 		<th>수강인원</th>
 		<td><input type="text" name="lectureTotalStudent"><span class="errors"><form:errors path="lecture.lectureTotalStudent" delimiter="//" /></span></td>
 	</tr>	
 	<input type="hidden" name="lectureCurrentStudent" value="0"><span class="errors"><form:errors path="lecture.lectureCurrentStudent" delimiter="//" /></span>
 	<tr>
 		<th>강의시작일</th>
 		<td><input id="bt1" type="text" name="lectureStartDate" readonly="readonly"><span class="errors"><form:errors path="lecture.lectureStartDate" delimiter="//" /></span>	
 	</tr>
 	<tr>
 		<th>강의종료일</th>
 		<td><input id="bt2" type="text" name="lectureEndDate" readonly="readonly"><span class="errors"><form:errors path="lecture.lectureEndDate" delimiter="//" /></span>
 	</tr>
 	<tr>
 		<th>강의 종류</th>
 		<td>
 		<select id="subject" name="lectureSubject">
		<c:forEach items="${requestScope.codeList }" var="code">
			<option>${code.codeName }</option>
		</c:forEach>
		</select>
		</td>
 	</tr>
 	<tr>
		<th>강사</th>
		<td><select id="teacher" name="teacherId2">
		<c:forEach items="${requestScope.teacherList }" var="teacher">
			<option value="${teacher.teacherId }">${teacher.teacherName }</option>
		</c:forEach>
		</select>
 	</tr>
</table>
<div align="right">
		<input type="submit" value="강의 등록" class="btn btn-success">
</div>
</form>
</div>