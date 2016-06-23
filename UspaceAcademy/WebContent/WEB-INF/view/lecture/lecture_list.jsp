<%@ page contentType ="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style type="text/css">
tr.dummy, td.dummy{
	border:0px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
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
				$('<tr class="dummy"><td colspan="7" class="dummy"></td></tr>').insertAfter(tmp); //이벤트소스의 다음 형제로 추가해준다.
				var txt = "번호 : "+list[0].lectureNo +" 제목 : "+list[0].lectureTitle+"<br>세부내용 : "+list[0].lectureDescription+"<br>";
				//if(tmp.next().children().eq(0).text()==""){
					tmp.next().children().eq(0).append(txt);
					if(list[1]=="student"){
						var txt = tmp.children().eq(0).text();
						var temp = "<a href="+"/UspaceAcademy/lecture/applyLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='lectureApply'>수강신청</button></a>"+
								   "<a id='zzim' href="+"/UspaceAcademy/lecture/zzimLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt+"><button class='lectureZzim'>찜하기</button></a>";
						tmp.next().children().eq(0).append($(temp));
					}
					if(list[1]=="administrator"){
						var txt2 = tmp.children().eq(0).text();
						var temp2 = "<a href="+"/UspaceAcademy/lecture/getModifyForm.do?page="+$("#page").val()+"&lectureNo="+txt2+"&codeType=teacherSubject><button class='lectureModify'>강의수정</button></a>"+
								    "<a href="+"/UspaceAcademy/lecture/removeLectureByNo.do?page="+$("#page").val()+"&lectureNo="+txt2+"><button class='lectureRemove'>강의삭제</button></a>";
						tmp.next().children().eq(0).append($(temp2));
					}
				//}
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
		
	})//강의목록 클릭했을 때 세부정보 표시
	
})
</script>

<table border="1">
<thead>
<tr>
	<th>강의번호</th><th>강의과목</th><th>강의명</th><th>강의기간</th><th>수강요일</th><th>강의시간</th>
	<th>수강인원</th>
</tr>
</thead>
<tbody>
<form><input id="page" type="hidden" value="${param.page }"></form>
<c:forEach items="${requestScope.lectureList }" var="lectureList">
		<tr class="lectureList">
			<td>${lectureList.lectureNo }</td><td>${lectureList.lectureSubject }</td><td>${lectureList.lectureTitle }</td>
			<td>${lectureList.lectureStartDate } ~ ${lectureList.lectureEndDate }</td><td>${lectureList.lectureDay }</td>
			<td>${lectureList.lectureStartTime } ~ ${lectureList.lectureEndTime }</td>
			<td>${lectureList.lectureCurrentStudent } / ${lectureList.lectureTotalStudent }</td>
		</tr>
</c:forEach>
</tbody>
</table>

<p>
	<%--◀이전 페이지 그룹 처리 --%>
	<c:choose>
		<c:when test="${requestScope.paging.previousPageGroup }">
			<a href="/UspaceAcademy/lecture/lectureList.do?page=${requestScope.paging.beginPage - 1}">
			◀
			</a>
		</c:when>
		<c:otherwise>◀</c:otherwise>
	</c:choose>
	<%--페이지 처리 --%>
	<c:forEach begin="${requestScope.paging.beginPage }" end="${requestScope.paging.endPage }" var="page">
		<c:choose>
			<c:when test="${page == requestScope.paging.page }">
			 [${page }]
			</c:when>
			<c:otherwise>
				<a href="/UspaceAcademy/lecture/lectureList.do?page=${page }">
					${page }
				</a>
			</c:otherwise>
		</c:choose>
	&nbsp;&nbsp;
	</c:forEach>
	<%--다음 페이지 그룹 처리 ▶--%>
	<c:choose>
		<c:when test="${requestScope.paging.nextPageGroup }">
			<a href="/UspaceAcademy/lecture/lectureList.do?&page=${requestScope.paging.endPage + 1}">
			▶
			</a>
		</c:when>
		<c:otherwise>▶</c:otherwise>
	</c:choose>
<p>


<!-- 관리자용 강의 등록 버튼 -->
<span class="lectureRegister">
	<c:if test="${sessionScope.memberType=='administrator'}">
		<a href="/UspaceAcademy/lecture/registerForm.do?codeType=teacherSubject"><button>강의 등록</button></a>
	</c:if>
	<c:if test="${sessionScope.memberType=='student'}">
		<a href="/UspaceAcademy/lecture/zzimList.do?page=${param.page }"><button>찜 목록</button></a>
		<a href="/UspaceAcademy/lecture/applyList.do?page=${param.page }"><button>결제 목록</button></a>
	</c:if>
</span>



