<%@ page contentType ="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
/* 				var txt = "강의 제목 : "+list[0].lectureTitle+"<br>세부 내용 : "+list[0].lectureDescription+"<br>강의 가격 : \\"+list[0].lecturePrice+"<br>";
				
				$('<tr class="dummy"><td colspan=8" class="dummy"><textarea rows="10" cols="50" readonly="readonly" class="form-control">강의제목 '+list[0].lectureTitle+'<br>세부 내용 : '+list[0].lectureDescription+"<br>강의 가격 : "+list[0].lecturePrice+'</textarea></td></tr>').insertAfter(tmp);

				$('<tr class="dummy"><td colspan=8" class="dummy"><textarea rows="10" cols="50" readonly="readonly" class="form-control">'+txt+'</textarea></td></tr>').insertAfter(tmp);

				 */
 				$('<tr class="dummy"><td colspan="9" class="dummy"></td></tr>').insertAfter(tmp); //이벤트소스의 다음 형제로 추가해준다.
				var txt = "강의 제목 : "+list[0].lectureTitle+"<br>세부 내용 : "+list[0].lectureDescription+"<br>강의 가격 : "+list[0].lecturePrice+"<br>";
					tmp.next().children().eq(0).append(txt);
					if(list[1]=="student"){
						var txt = tmp.children().eq(0).text();
						var temp="<span id='error'><font color='red'><b>수강 인원이 가득찼습니다</b></font></span>";
						if(list[0].lectureCurrentStudent<list[0].lectureTotalStudent){
							temp = "<a href="+"/UspaceAcademy/lecture/applyLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='lectureApply'>수강신청</button></a>"+
								   	   "<a id='zzim' href="+"/UspaceAcademy/lecture/zzimLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='lectureZzim'>찜하기</button></a>";
						
						}
						tmp.next().children().eq(0).append($(temp));
					}
					if(list[1]=="administrator"){
						var txt2 = tmp.children().eq(0).text();
						var temp2 = "<a href="+"/UspaceAcademy/lecture/getModifyForm.do?page="+$("#page").val()+"&lectureNo="+txt2+"&codeType=teacherSubject><button class='btn btn-warning'>강의수정</button></a>"+
								    "<a href="+"/UspaceAcademy/lecture/removeLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt2+">&nbsp;&nbsp;<button id='removeBtn' class='btn btn-danger'>강의삭제</button></a>";
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
        this.style.backgroundColor = '#dcfac9';
	});
	
	$(".lectureList").on("mouseout", function(){
        this.style.backgroundColor = 'white';
	});
	$("#removeBtn").on("click", function() {
		return confirm("강의를 삭제하시겠습니까?")
	})
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
		$("tr:eq(2)").css("background-color", "#FFE08C");
	}
	$("#searchBtn").on("click", function() {
		if(!$("input[name=keyword]").val()){
			alert("키워드를 입력하세요.");
			return false;
		}
	})
	
})
</script>
<h3 class="pageTlt">강좌 목록</h3>
<hr>
<form>
<input id="page" type="hidden" value="${param.page }">
</form>
<font color="red" size="5">${requestScope.errorMessage}</font>
<table border="1" class="table table-bordered">

<thead>
<tr>
	<th>강의번호</th><th>과목</th><th>강의명</th><th>강사</th><th>강의시작일</th><th>강의종료일</th><th>강의요일</th><th>강의시간</th><th>수강인원</th>
</tr>
</thead>
<tbody>

<c:forEach items="${requestScope.lectureList }" var="lectureList">
		<tr class="lectureList">
			<td>${lectureList.lectureNo }</td><td>${lectureList.lectureSubject }</td><td>${lectureList.lectureTitle }</td>
			<td class="teacherId">${lectureList.teacherId2 }</td><td>${lectureList.lectureStartDate }</td><td>${lectureList.lectureEndDate }</td>
			<td>${lectureList.lectureDay }</td><td>${lectureList.lectureStartTime } ~ ${lectureList.lectureEndTime }</td>
			<td><span class="current">${lectureList.lectureCurrentStudent }</span> / <span class="total">${lectureList.lectureTotalStudent }</span></td>
		</tr>
</c:forEach>
</tbody>
</table>

<p>
<div class="pageNav" align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/lecture/searchLectureByKeyword.do?page=${requestScope.paging.beginPage - 1}&searchType=${requestScope.searchType}&keyword=${requestScope.keyword}" class="prevPage">
			<span><strong>이전</strong></span>
			</a>
		</c:when>
		<c:otherwise><span><strong>이전</strong></span></c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 <span>${page }
			 
			 </span>
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/lecture/searchLectureByKeyword.do?page=${page }&searchType=${requestScope.searchType}&keyword=${requestScope.keyword}">
					<span><strong>${page }</strong></span>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/lecture/searchLectureByKeyword.do?page=${requestScope.paging.endPage + 1}&searchType=${requestScope.searchType}&keyword=${requestScope.keyword}" class="nextPage">
			<span><strong>다음</strong></span>
			</a>
		</c:when>
		<c:otherwise><span><strong>다음</strong></span></c:otherwise>
	</c:choose>
	</div>
<br>

<!-- 관리자용 강의 등록 버튼 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/lecture/registerForm.do?codeType=teacherSubject"><button class="btn btn-success">강의 등록</button></a>
	</c:if>
	<c:if test="${sessionScope.memberType=='student'}">
		<a href="/UspaceAcademy/lecture/zzimList.do?page=${param.page }"><button class="btn btn-success">찜 목록</button></a>
		<a href="/UspaceAcademy/lecture/applyList.do?page=${param.page }"><button class="btn btn-warning">결제 목록</button></a>
	</c:if>
</span>


<c:choose>
	<c:when test="${sessionScope.memberType=='administrator'}">
		<!-- 검색관련 -->
	<form action="/UspaceAcademy/lecture/searchLectureByKeywordA.do?page=${param.page }" method="post">
		<div align="right">
			<select name="searchType">
				<option value="lectureTitle">강의명</option>
				<option value="teacherSubject">과목</option>
				<option value="teacherName">강사</option>
			</select>
		<input type="text" name="keyword">
		<input type="submit" value="검색" class="btn btn-info">
		</div>	
	</form>
	</c:when>
	<c:otherwise>
		<!-- 검색관련 -->
	<form action="/UspaceAcademy/lecture/searchLectureByKeyword.do?page=${param.page }" method="post">
		<div align="right">
			<select name="searchType">
				<option value="lectureTitle">강의명</option>
				<option value="teacherSubject">과목</option>
				<option value="teacherName">강사</option>
			</select>
		<input type="text" name="keyword" placeholder="입력하세요.">
		<input id="searchBtn" type="submit" value="검색" class="btn btn-info">
		</div>	
	</form>
	</c:otherwise>
</c:choose>






