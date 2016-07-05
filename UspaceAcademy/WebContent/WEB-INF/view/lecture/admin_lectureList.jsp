<%@page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
tr.dummy, td.dummy{
	border:0px;
}
</style>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){
	$(".lectureList").on("click",function(){
		tmp = $(this);
		$.ajax({
			"url":"/UspaceAcademy/lecture/findLectureByNo.do", //요청 URL
			"type":"POST", //HTTP 요청방식
			"data":"lectureNo="+tmp.children().eq(0).text(), //요청 파라미터 설정 - queryString(n=v&n=v) /  Javascript 객체{n:v,n:v}
			"dataType":"json", //응답데이터 타입 지정. text는 default
			"success":function(list){
				
				$("tbody tr.dummy").remove(); //먼저 dummy class의 tr을 지워주고
				$('<tr class="dummy"><td colspan="8" class="dummy"></td></tr>').insertAfter(tmp); //이벤트소스의 다음 형제로 추가해준다.
				var txt = "강의 제목 : "+list[0].lectureTitle+"<br>세부 내용 : "+list[0].lectureDescription+"<br>강의 가격 : \\"+list[0].lecturePrice+"<br>";
					tmp.next().children().eq(0).append(txt);
/* 					if(list[1]=="student"){
						var txt = tmp.children().eq(0).text();
						var temp="<span id='error'><font color='red'><b>수강 인원이 가득찼습니다</b></font></span>";
						if(list[0].lectureCurrentStudent<list[0].lectureTotalStudent){
							temp = "<a href="+"/UspaceAcademy/lecture/applyLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='lectureApply'>수강신청</button></a>"+
								   	   "<a id='zzim' href="+"/UspaceAcademy/lecture/zzimLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='lectureZzim'>찜하기</button></a>";
						
						}
						tmp.next().children().eq(0).append($(temp)); 
					}*/
					if(list[1]=="administrator"){
						var txt2 = tmp.children().eq(0).text();
						var temp2 = "<a href="+"/UspaceAcademy/lecture/getModifyForm.do?page="+$("#page").val()+"&lectureNo="+txt2+"&codeType=teacherSubject><button class='btn btn-warning'>강의수정</button></a>"+
								    "<a href="+"/UspaceAcademy/lecture/removeLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt2+"><button class='btn btn-danger'>강의삭제</button></a>";
						tmp.next().children().eq(0).append($(temp2));
					}
				
/*	정훈 - 강의일수 확인을 위해서...
				var start_String = list[0].lectureStartDate;
				var end_String = list[0].lectureEndDate;
				var start_Array = start_String.split("/");
				var end_Array = end_String.split("/");
				var start_date = new Date(start_Array[0], Number(start_Array[1])-1, start_Array[2]);
				var end_date = new Date(end_Array[0], Number(end_Array[1])-1, end_Array[2]);
				var between_day = (end_date.getTime() - start_date.getTime())/1000/60/60/24;
				alert("강의일수 - " +(between_day+1));		
 */	 
			},
			"error":function(xhr, status, errorMsg){
				alert("오류가 발생했습니다."+status+", "+errorMsg);
			},
			"beforeSend":function(){
				/*if(!$("#id").val()){	
					alert("조회할 ID를 입력하세요");
					$("#id").focus();
					return false;
				}*/
			}
		})
		
	});//강의목록 클릭했을 때 세부정보 표시
	
	$(".lectureList").on("mouseover", function(){
		this.style.cursor = 'pointer';
        this.style.backgroundColor = '#DAD9FF';
	});
	
	$(".lectureList").on("mouseout", function(){
        this.style.backgroundColor = 'white';
	});
	var ids="";
	var temp=$(".teacherId");
	for(var i=0;i<$(".teacherId").length;i++){
		if(i==$(".teacherId").length-1){
			ids += $(".teacherId").eq(i).text();
		}else{
			ids += $(".teacherId").eq(i).text()+",";
		}
	}
	$.ajax({
		"url":"/UspaceAcademy/lecture/convertTeacherIdToTeacherName.do", //요청 URL
		"type":"POST", //HTTP 요청방식
		"data":"teacherIds="+ids, //요청 파라미터 설정 - queryString(n=v&n=v) /  Javascript 객체{n:v,n:v}
		"dataType":"json", //응답데이터 타입 지정. text는 default
		"success":function(list){
				for(var i=0; i<list.length; i++){
					temp.eq(i).text(list[i]);
				}
		},
		"error":function(xhr, status, errorMsg){
			alert("아이디 이름으로 바꾸는 곳에서 오류");
			alert("오류가 발생했습니다."+status+", "+errorMsg);
		},
		"beforeSend":function(){
		}
	});
	$(document).ready(effect);
	function effect() {
		$("tr:eq(2)").css("background-color", "#FFD9FA");
	}
	
})
</script>

<h3 class="pageTlt">전체 강의 목록</h3>
<hr>

<table class="table table-bordered">
	<thead>
		<tr>	
			<th>강의번호</th>
			<th>강의명 - 과목</th>
			<th>강사</th>
			<th>강의시간</th>
			<th>강의요일</th>
			<th>강의시작일</th>
			<th>강의종료일</th>
			<th>강의인원</th>
			<th>강의가격</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.lectureList}" var="lecture">
			<tr class="lectureList">
				<td>${lecture.lectureNo}</td>
				<td>${lecture.lectureTitle} - [${lecture.lectureSubject}]</td>
				<td class="teacherId">${lecture.teacherId2 }</td>
				<td>${lecture.lectureStartTime} ~ ${lecture.lectureEndTime}</td>
				<td>${lecture.lectureDay}</td>
				<td>${lecture.lectureStartDate}</td>
				<td>${lecture.lectureEndDate}</td>
				<td>${lecture.lectureCurrentStudent} / ${lecture.lectureTotalStudent}</td>
				<td>${lecture.lecturePrice}</td>
			</tr>
	</c:forEach>
	</tbody>
</table>



<br>
<!-- 검색관련 -->
<div class="boardBottom" align="center">
<form action="/UspaceAcademy/lecture/searchLectureByKeyword.do?page=${param.page }" method="post">
<select name="searchType">
	<option value="lectureTitle">강의명</option>
	<option value="teacherSubject">과목</option>
	<option value="teacherName">강사</option>
</select>
<input type="text" name="keyword">
<input type="submit" value="검색" class="btn btn-info">
</form>
</div>
<p>