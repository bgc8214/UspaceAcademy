<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<style type="text/css">
span.errors{
	color:red
}

.ui-datepicker-calendar > tbody td:first-child a { 
    COLOR: #f00; 
}

.ui-datepicker-calendar > tbody td:last-child a { 

    COLOR: blue; 
}


</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jquery-ui.min.js"></script>

<link href="/UspaceAcademy/jQuery/jquery-ui.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.structure.min.css" rel="stylesheet">
<link href="/UspaceAcademy/jQuery/jquery-ui.theme.min.css" rel="stylesheet">

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
			if(isNaN($("input[name=lectureStartTime]").val())){
				alert("강의 시작시간은 숫자만 입력하세요.");
				return false;
			}
			if($("input[name=lectureStartTime]").val()>24||$("input[name=lectureStartTime]").val()<0){
				alert("강의 시작시간은 0~24사이의 숫자만 입력하세요.");
				return false;
			}
			if(isNaN($("input[name=lectureEndTime]").val())){
				alert("강의 끝시간은 숫자만 입력하세요.");
				
				return false;
			}
			if($("input[name=lectureEndTime]").val()>24||$("input[name=lectureEndTime]").val()<0){
				alert("강의 끝시간은 0~24사이의 숫자만 입력하세요.");
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
				alert("강의 시작일을 입력하세요.");
				return false;
			}
			if(!$("input[name=lectureStartDate]").val()){
				alert("강의 종료일을 입력하세요.");
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


</script>





<h2>강의 등록폼</h2>
<form action="/UspaceAcademy/lecture/registerLecture.do" method="post">
강의명 : <input type="text" name="lectureTitle"><span class="errors"><form:errors path="lecture.lectureTitle" delimiter="//"/></span><br>
강의 설명 : <textarea rows="10" cols="20" name="lectureDescription"></textarea><span class="errors"><form:errors path="lecture.lectureDescription" delimiter="//"/></span><br>
강의 시작시간 : <input type="text" name="lectureStartTime"><span class="errors"><form:errors path="lecture.lectureStartTime" delimiter="//"/></span><br>
강의 끝시간 : <input type="text" name="lectureEndTime"><span class="errors"><form:errors path="lecture.lectureEndTime" delimiter="//"/></span><br>
<%-- 강의 요일 : <input type="text" name="lectureDay"><span class="errors"><form:errors path="lecture.lectureDay" delimiter="//"/></span><br> --%>
강의 요일 :<label> 월 <input type="checkbox" name="lectureDay2" value='월'></label>
		   <label> 화 <input type="checkbox" name="lectureDay2" value='화'></label>
		   <label> 수 <input type="checkbox" name="lectureDay2" value='수'></label>
		   <label> 목 <input type="checkbox" name="lectureDay2" value='목'></label>
		   <label> 금 <input type="checkbox" name="lectureDay2" value='금'></label>
		   <label> 토 <input type="checkbox" name="lectureDay2" value='토'></label>
		   <label> 일 <input type="checkbox" name="lectureDay2" value='일'></label> <font color="red">${param.errorMessage }</font><br>
강의 수강료 : <input type="text" name="lecturePrice"><span class="errors"><form:errors path="lecture.lecturePrice" delimiter="//"/></span><br>
강의 수강인원 : <input type="text" name="lectureTotalStudent"><span class="errors"><form:errors path="lecture.lectureTotalStudent" delimiter="//"/></span><br>
<input type="hidden" name="lectureCurrentStudent" value="0"><span class="errors"><form:errors path="lecture.lectureCurrentStudent" delimiter="//"/></span><br>



강의 시작일 : <input id="bt1" type="text" name="lectureStartDate" readonly="readonly"><span class="errors"><form:errors path="lecture.lectureStartDate" delimiter="//"/></span><br>
강의 종료일 : <input id="bt2" type="text" name="lectureEndDate" readonly="readonly"><span class="errors"><form:errors path="lecture.lectureEndDate" delimiter="//"/></span><br>
강의 종류 : 
<select id="subject" name="lectureSubject">
	<c:forEach items="${requestScope.codeList }" var="code">
		<option>${code.codeName }</option>
	</c:forEach>
</select>

강의 강사 : 
<select id="teacher" name="teacherId2">
	<c:forEach items="${requestScope.teacherList }" var="teacher">
				<option value="${teacher.teacherId }">${teacher.teacherName }</option>
	</c:forEach>
</select>
<p>
<input id="submit1" type="submit" value="강의 등록">
</form>