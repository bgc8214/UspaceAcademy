<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
button {
/* 	border: 1x solid #ff0080; ---테두리 정의--- */
/* 	background-Color: #ffe6f2; --백그라운드 정의--- */
	font: 12px 굴림; /*--폰트 정의---*/
	font-weight: bold; /*--폰트 굵기---*/
	color: #ff0080; /*--폰트 색깔---*/
	width: 130;
	height: 30; /*--버튼 크기---*/
}

table {
	margin-left: auto;
	margin-right: auto;
}

form {
	margin-right: auto;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() { // 마우스를 올렸을 때 하위 메뉴 나온다.
						$("#introduce")
								.on(
										"mouseover",
										function() { // 학원 소개
											var td = $(this).text();
											$("#td1")
													.html(
															'<a href="/UspaceAcademy/academyIntroduce.do"><button>학원소개</button></a><a href="/UspaceAcademy/roadIntroduce.do"><button>오시는길</button></a><a href="/UspaceAcademy/teacherIntroduce.do"><button>강사소개</button></a>');
											$("#td5").html("");
											$("#td6").html("");
										});
						$("#customerCenter")
								.on(
										"mouseover",
										function() { //고객센터
											var td = $(this).text();
											$("#td5")
													.html(
															'<a href="/UspaceAcademy/FAQ/list.do?type=FAQ"><button style="width:76px">FAQ</button></a><a href="/UspaceAcademy/inquiry/inquiryList.do?advancedType=1:1문의"><button>1대1문의</button></a>');
											$("#td1").html("");
											$("#td6").html("");
										});


						$("#myPage")
								.on(
										"mouseover",
										function() {
											if ($("#memberType").val() == "teacher") { // 마이페이지 강사 로그인
												$("#td6")
														.html(/* attendanceList.do */
																'<a href="/UspaceAcademy/teacherInfo.do"><button>내 정보</button></a><a href="/UspaceAcademy/member/selectAllByTeacherId.do"><button>내 강좌</button></a><a href="/UspaceAcademy/assignment/assignment_list.do"><button id="assignment">과제게시판</button></a><a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=1"><button>강의 질문 게시판</button></a>');
												$("#td1").html("");
												$("#td5").html("");
											} else if ($("#memberType").val() == "student") // 마이페이지 학생 로그인
											{
												$("#td6")
														.html(
																'<a href="/UspaceAcademy/studentInfo.do"><button>내 정보</button></a><a href="/UspaceAcademy/attendance/studentLectureInfo.do"><button>내 강좌</button></a><a href="/UspaceAcademy/assignment/assignment_list.do"><button id="assignment">과제게시판</button></a><a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=1"><button>강의 질문 게시판</button></a>');
												$("#td1").html("");
												$("#td5").html("");
											} else //마이페이지 관리자 로그인
											{
												$("#td6")
														.html(
																'<a href="/UspaceAcademy/member/studentAll.do"><button style="width:88px">학생관리</button></a><a href="/UspaceAcademy/member/teacherAll.do"><button style="width:88px" >강사관리</button></a><button style="width:88px">재정관리</button>');
												$("#td1").html("");
												$("#td5").html("");
											}
										});
					});
</script>


<c:choose>
	<c:when test="${sessionScope.memberType==null }">
		<table>
			<tr>
				<td><a href="/UspaceAcademy/academyIntroduce.do"><button
							id="introduce">학원소개</button></a></td>
				<td><a href="/UspaceAcademy/lecture/lectureList.do"><button
							id="lecture">개설강좌</button></a></td>
				<td><a
					href="/UspaceAcademy/lectureReview/lecture_review_list.do"><button
							id="lectureReview">수강후기</button></a></td>
				<td><a href="/UspaceAcademy/notice/list.do?type=공지사항"><button
							id="notice">공지사항</button></a></td>
				<td><button	id="customerCenter">고객센터</button></td>
				<td><a href="/UspaceAcademy/registerSelect.do"><button
							id="join">회원가입</button></a></td>

			</tr>

			<tr>
				<td width="79" id="td1"></td>
				<td width="79" id="td2"></td>
				<td width="79" id="td3"></td>
				<td width="79" id="td4"></td>
				<td width="79" id="td5"></td>
				<td width="88" id="td6"></td>
			</tr>
		</table>

	</c:when>
	<c:otherwise>
		<br>
		<table>
			<tr>
				<td><a href="/UspaceAcademy/academyIntroduce.do"><button
							id="introduce">학원소개</button></a></td>
				<td><a href="/UspaceAcademy/lecture/lectureList.do"><button
							id="lecture">개설강좌</button></a></td>
				<td><a
					href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button
							id="lectureReview">수강후기</button></a></td>
				<td><a href="/UspaceAcademy/notice/list.do?type=공지사항"><button
							id="notice">공지사항</button></a></td>
				<td><a href="/UspaceAcademy/FAQ/list.do?type=FAQ"><button
							id="customerCenter">고객센터</button></a></td>
				<td><a href="/UspaceAcademy/lecture/lectureList.do"><button
							id="myPage">마이페이지</button></a></td>
							
							
			</tr>

			<tr>
				<td width="74" id="td1"></td>
				<td width="74" id="td2"></td>
				<td width="74" id="td3"></td>
				<td width="74" id="td4"></td>
				<td width="74" id="td5"></td>
				<td width="100" id="td6"></td>
			</tr>
		</table>




		<form>
			<input type="hidden" id="memberType"
				value="${sessionScope.memberType }">
		</form>


	</c:otherwise>
</c:choose>

<br>

<c:choose>
	<c:when test="${sessionScope.memberType==null }">
		<!-- 로그인 안했을 때 -->

		<form action="/UspaceAcademy/member/login.do" method="post">
			ID : <input type="text" name="id"> PASSWORD : <input
				type="password" name="password"> <input type="submit"
				value="login" class="btn btn-success">
		</form>
	</c:when>
	<c:otherwise>
		<!-- 로그인시 각각 회원 종류에 맞게 환영인사. -->
		<c:choose>
			<c:when test="${sessionScope.memberType=='student' }">
			${sessionScope.login_info.studentName } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do"><button class="btn btn-info">로그아웃</button></a>
			</c:when>
			<c:when test="${sessionScope.memberType=='teacher' }">
			${sessionScope.login_info.teacherName } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do"><button class="btn btn-info">로그아웃</button></a>
			</c:when>
			<c:when test="${sessionScope.memberType=='administrator' }">
			${sessionScope.login_info } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do"><button class="btn btn-info">로그아웃</button></a>
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>



