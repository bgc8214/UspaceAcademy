<%@ page contentType ="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
tr.dummy, td.dummy {
	border: 0px;
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
				
				if(list[1]=="student"){
						var txt = tmp.children().eq(0).text();
						var temp="<span id='error'><font color='red'><b>수강 인원이 가득찼습니다</b></font></span>";
						if(list[0].lectureCurrentStudent<list[0].lectureTotalStudent){
							temp = "<a href="+"/UspaceAcademy/lecture/applyLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='btn btn-success'>수강신청</button></a>"+
								   	   "<a id='zzim' href="+"/UspaceAcademy/lecture/zzimLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='btn btn-warning'>찜하기</button></a>";
						
						}
						tmp.next().next().children().eq(0).append($(temp));
					}
			},
			"error":function(xhr, status, errorMsg){
				alert("오류가 발생했습니다."+status+", "+errorMsg);
			},
			"beforeSend":function(){
				
			}
		})
		
	});//강의목록 클릭했을 때 세부정보 표시
	
	$(".lectureList").on("mouseover", function(){
		this.style.cursor = 'pointer';
        this.style.backgroundColor = '#D8EFF1';
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
			<td>${lectureList.lectureCurrentStudent } / ${lectureList.lectureTotalStudent }</td>
		</tr>
</c:forEach>
</tbody>
</table>

<p>
<div class="pageNav" align="center">
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.paging.beginPage - 1}" class="prevPage">
			<strong>이전</strong>
			</a>
		</c:when>
		<c:otherwise><strong>이전</strong></c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 <strong>${page }</strong> 
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/lecture/lectureList.do?page=${page }">
					<strong>${page }</strong>
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.paging.endPage + 1}" class="nextPage">
			<strong>다음</strong>
			</a>
		</c:when>
		<c:otherwise><strong>다음</strong></c:otherwise>
	</c:choose>
	</div>
<br>

<!-- 관리자용 강의 등록 버튼 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/lecture/registerForm.do?codeType=teacherSubject"><button class="btn btn-success">강의 등록</button></a>
	</c:if>
	<c:if test="${sessionScope.memberType=='student'}">
		<div align="left">
			<a href="/UspaceAcademy/lecture/zzimList.do?page=${param.page }"><button class="btn btn-warning">찜 목록</button></a>
			<a href="/UspaceAcademy/lecture/applyList.do?page=${param.page }"><button class="btn btn-danger">결제 목록</button></a>
		</div>	
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






