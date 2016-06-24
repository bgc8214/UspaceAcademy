<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
button{
border:1x solid #ff0080;    /*---테두리 정의---*/
background-Color:#ffe6f2;   /*--백그라운드 정의---*/
font:12px 굴림;      /*--폰트 정의---*/
font-weight:bold;   /*--폰트 굵기---*/
color:#ff0080;    /*--폰트 색깔---*/
width:130;height:30;  /*--버튼 크기---*/

}
</style>




<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
$(document).ready(function(){ // 마우스를 올렸을 때 하위 메뉴 나온다.
	$("#introduce").on("mouseover",function(){ // 학원 소개
		var td = $(this).text();
		$("#td1").html('<a href="/UspaceAcademy/academyIntroduce.do"><button>학원소개</button></a><a href="/UspaceAcademy/roadIntroduce.do"><button>오시는길</button></a><a href="/UspaceAcademy/teacherIntroduce.do"><button>강사소개</button></a>');
		$("#td5").html("");
		$("#td6").html("");
	});
	$("#customerCenter").on("mouseover",function(){ //고객센터
		var td = $(this).text();
		$("#td5").html("<button>FAQ</button><button>1대1문의</button>");
		$("#td1").html("");
		$("#td6").html("");
	});
	
	$("#myPage").on("mouseover",function(){
		if($("#memberType").val()=="teacher"){ // 마이페이지 강사 로그인
		$("#td6").html("<button>내 정보</button><button>내 강좌</button>");
		$("#td1").html("");
		$("#td5").html("");
		}
		else if($("#memberType").val()=="student") // 마이페이지 학생 로그인
		{
			$("#td6").html("<button>내 정보</button><button>내 강좌</button>");
			$("#td1").html("");
			$("#td5").html("");
		}else //마이페이지 관리자 로그인
		{
			$("#td6").html("<button>학생관리</button><button>강사관리</button><button>재정관리</button>");
			$("#td1").html("");
			$("#td5").html("");
		}
	});
});

</script>


<c:choose>
	<c:when test="${sessionScope.memberType==null }">
		<!-- 로그인 안했을 때 -->

		<form action="/UspaceAcademy/member/login.do">
			ID : <input type="text" name="id"><font color="red">${requestScope.idError }</font>
			PASSWORD : <input type="password" name="password"><font
				color="red"> ${requestScope.passwordError }</font> <input
				type="submit" value="login">
		</form>
	</c:when>
	<c:otherwise> <!-- 로그인시 각각 회원 종류에 맞게 환영인사. -->
		<c:choose>
			<c:when test="${sessionScope.memberType=='student' }">
			${sessionScope.login_info.studentName } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do">로그아웃</a>
			</c:when>
			<c:when test="${sessionScope.memberType=='teacher' }">
			${sessionScope.login_info.teacherName } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do">로그아웃</a>
			</c:when>
			<c:when test="${sessionScope.memberType=='administrator' }">
			${sessionScope.login_info } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do">로그아웃</a>
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>

<c:choose>
<c:when test="${sessionScope.memberType==null }">
<table>
<tr>
<td width="300" id="dummy">
</td>
<td>
<a href="/UspaceAcademy/academyIntroduce.do"><button id="introduce">학원소개</button></a>
</td>
<td>
<a href="/UspaceAcademy/lecture/searchLectureByKeyword.do?page="+${param.page }><button id="lecture">개설강좌</button></a>
</td>
<td>
<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button id="lectureReview">수강후기</button></a>
</td>
<td>
<a href="/UspaceAcademy/notice/list.do?type=공지사항"><button id="notice">공지사항</button></a>
</td>
<td>
<a href="/UspaceAcademy/serviceCenter.do"><button id="customerCenter">고객센터</button></a>
</td>
<td>
<a href="/UspaceAcademy/registerSelect.do"><button id="join">회원가입</button></a>
</td>
</tr>

</table>
<table >
<tr>
<td width="299"></td>
<td id="td1"></td>
<td id="td2"></td>
<td width="180" id="td3"></td>
<td width="118" id="td4"></td>
<td id="td5"></td>
<td id="td6"></td>
</tr>
</table>

</c:when>
<c:otherwise>
<br>
<table>
<tr>
<td width="300">
</td>
<td>
<a href="/UspaceAcademy/academyIntroduce.do"><button id="introduce">학원소개</button></a>
</td><td>
<a href="/UspaceAcademy/lecture/lectureList.do"><button id="lecture">개설강좌</button></a>
</td><td>
<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button id="lectureReview">수강후기</button></a>
</td><td>
<a href="/UspaceAcademy/notice/list.do?type=공지사항"><button id="notice">공지사항</button></a>
</td><td>
<a href="/UspaceAcademy/serviceCenter.do"><button id="customerCenter">고객센터</button></a>
</td><td>
<a href="/UspaceAcademy/lecture/lectureList.do"><button id="myPage">마이페이지</button></a>
</td>
</tr>
</table>
<table>
<tr>
<td width="299"></td>
<td id="td1"></td>
<td id="td2"></td>
<td width="180" id="td3"></td>
<td width="118" id="td4"></td>
<td id="td5"></td>
<td width="65"></td>
<td id="td6"></td>
</tr>
</table>
<form>
<input type="hidden" id="memberType" value="${sessionScope.memberType }">
</form>


</c:otherwise>
</c:choose>
<br>



