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
				$("tbody tr.dummy1").remove();
				$('<tr class="dummy"><td colspan="5"><label>강의제목&nbsp;&nbsp;</label>'+list[0].lectureTitle+'</td><td colspan="5"><label>수강료&nbsp;&nbsp;</label>'+list[0].lecturePrice+'</tr><tr class="dummy1"><td colspan="10" class="dummy"><textarea rows="10" cols="50" readonly="readonly" class="form-control">'+list[0].lectureDescription+'</textarea></td></tr>').insertAfter(tmp);
				tmp.next().children().eq(0).append();

					if(list[1]=="administrator"){
						var txt2 = tmp.children().eq(0).text();
						var temp2 = "<a href="+"/UspaceAcademy/lecture/getModifyForm.do?lectureNo="+txt2+"&codeType=teacherSubject><button class='btn btn-warning'>강의수정</button></a>"+
								    "<a href="+"/UspaceAcademy/lecture/removeLectureByNo.do?lectureNo="+txt2+"><button id='removeBtn' class='btn btn-danger'>강의삭제</button></a>";
						tmp.next().next().children().eq(0).append($(temp2));
					}

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
})
</script>

<h3 class="pageTlt">강의 목록</h3>
<hr>

<table class="table table-bordered">
	<thead>
		<tr>	
			<th>강의번호</th>
			<th>과목</th>
			<th>강의명</th>
			<th>강사</th>
			<th>강의시작일</th>
			<th>강의종료일</th>
			<th>강의요일</th>
			<th>강의시간</th>
			<th>수강인원</th>
			<th>강의가격</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${requestScope.lectureList}" var="lecture">
			<tr class="lectureList">
				<td>${lecture.lectureNo}</td>
				<td>${lecture.lectureSubject}</td>
				<td>${lecture.lectureTitle}</td>
				<td class="teacherId">${lecture.teacherId2}</td>
				<td>${lecture.lectureStartDate}</td>
				<td>${lecture.lectureEndDate}</td>
				<td>${lecture.lectureDay}</td>
				<td>${lecture.lectureStartTime} ~ ${lecture.lectureEndTime}</td>
				<td>${lecture.lectureCurrentStudent} / ${lecture.lectureTotalStudent}</td>
				<td>${lecture.lecturePrice}</td>
			</tr>
	</c:forEach>
	</tbody>
</table>
<p>

<div class="pageNav" align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/lecture/searchLectureByKeywordA.do?page=${requestScope.paging.beginPage - 1}">
			이전
			</a>
		</c:when>
		<c:otherwise>이전</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 <strong>${page }</strong>
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/lecture/searchLectureByKeywordA.do?page=${page }">
				<strong>${page }</strong>		
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/lecture/searchLectureByKeywordA.do?page=${requestScope.paging.endPage + 1}">
			다음
			</a>
		</c:when>
		<c:otherwise>다음</c:otherwise>
	</c:choose>
	</div>
<br>
<div align="right">
<a href="/UspaceAcademy/lecture/registerForm.do?codeType=teacherSubject"><button class="btn btn-success">강의 등록</button></a>
</div>
<br>

<br>
<!-- 검색관련 -->
<div class="boardBottom" align="center">
<form action="/UspaceAcademy/lecture/searchLectureByKeywordA.do?page=${param.page }" method="post">
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