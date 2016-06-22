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

<a href="/UspaceAcademy/introduceSelct.do"><button>학원소개</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/lecture/lectureList.do"><button>개설강좌</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button>수강후기</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><button>공지사항</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/lecture/lectureList.do"><button>고객센터</button></a>
&emsp;&emsp;


<a href="/UspaceAcademy/registerSelect.do"><button>회원가입</button></a>
&emsp;&emsp;

</c:when>
<c:otherwise>
<br>
<a href="/UspaceAcademy/introduceSelct.do"><button>학원소개</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/lecture/lectureList.do"><button>개설강좌</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button>수강후기</button></a>
&emsp;&emsp;

<a href="/UspaceAcademy/notice/codeList.do?codeNames=공지사항"><button>공지사항</button></a>
&emsp;&emsp;


<a href="/UspaceAcademy/lecture/lectureList.do"><button>고객센터</button></a>
&emsp;&emsp;


<a href="/UspaceAcademy/lecture/lectureList.do"><button>마이페이지</button></a>
&emsp;&emsp;

</c:otherwise>
</c:choose>
<br>



