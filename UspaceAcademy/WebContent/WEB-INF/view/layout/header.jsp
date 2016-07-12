<%@page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
	nav{padding-left: 200px;}
	body{padding-top:20px;}
	form#loginForm {
		padding-left : 200px;
		position :relative;
		position : absolute;
		left:750px;
		color : black;
		top : 12px;
		bold;
	}

	#aa{
		color:black;
	}
	
	div#bb {
		background: blue;
		color : green;
	}
	span#bb {
		padding-left: 200px;
		position :relative;
		position : absolute;
		left:750px;
		color : black;
		top : 10px;
		bold;
	}
	table#tb {
		width:900px;
		height:100px;
	}
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("div").on("mouseover", function() {
			this.style.cursor = 'pointer';
		})/
	})

</script>
<c:choose>
	<c:when test="${sessionScope.memberType==null }">
		<nav class="navbar navbar-light navbar-fixed-top" style='background-color: #FFD9EC;'>
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/UspaceAcademy/main.do">UspaceAcademy</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li class="down"><a class="dropdown-toggle"
							data-toggle="dropdown">학원소개</a>
							<ul class="dropdown-menu">
								<li><a href="/UspaceAcademy/academyIntroduce.do">학원소개</a></li>
								<li><a href="/UspaceAcademy/roadIntroduce.do">오시는길</a></li>
								<li><a href="/UspaceAcademy/teacherIntroduce.do">강사소개</a></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav">
						<li><a href="/UspaceAcademy/lecture/lectureList.do">개설강좌</a></li>
						<li><a
							href="/UspaceAcademy/lectureReview/lecture_review_list.do">수강후기</a></li>
						<li><a href="/UspaceAcademy/notice/list.do?type=공지사항">공지사항</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li class="down"><a class="dropdown-toggle"
							data-toggle="dropdown">고객센터</a>
							<ul class="dropdown-menu">
								<li><a href="/UspaceAcademy/FAQ/list.do?type=FAQ">FAQ</a></li>
								<li><a
									href="/UspaceAcademy/inquiry/inquiryList.do?advancedType=1:1문의">1:1문의</a></li>
							</ul>
					</ul>
					<ul class="nav navbar-nav">
						<li class="down"><a class="dropdown-toggle"
							data-toggle="dropdown">회원가입</a>
							<ul class="dropdown-menu">
								<li><a href="/UspaceAcademy/member/teacherRegisterForm.do?codeType=teacherSubject">강사가입</a></li>
								<li><a
									href="/UspaceAcademy/member/studentRegisterForm.do">학생가입</a></li>
							</ul>
					</ul>
					<ul class="nav navbar-nav">
						
						<c:choose>
	<c:when test="${sessionScope.memberType==null }">
		<!-- 로그인 안했을 때 -->

		<form action="/UspaceAcademy/member/login.do" method="post" id="loginForm">
			ID : <input type="text" name="id" placeholder="id를 입력하세요" id="aa"> PASSWORD :<input
				type="password" name="password" placeholder="비밀번호를 입력하세요" id="aa"> <input type="submit"
				value="login" class="btn btn-success">
		</form>
	</c:when>
	<c:otherwise>
		<!-- 로그인시 각각 회원 종류에 맞게 환영인사. -->
		<c:choose>
			<c:when test="${sessionScope.memberType=='student' }">
		<span id="bb">${sessionScope.login_info.studentName } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do"><button class="btn btn-success">로그아웃</button></a>
		</span>
			</c:when>
			<c:when test="${sessionScope.memberType=='teacher' }">
		<span id="bb">	
			${sessionScope.login_info.teacherName } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do"><button class="btn btn-success">로그아웃</button></a>
		</span>
			</c:when>
			<c:when test="${sessionScope.memberType=='administrator' }">
		<span id="bb">	
			${sessionScope.login_info } 님 환영합니다.
			<a href="/UspaceAcademy/member/logout.do"><button class="btn btn-success">로그아웃</button></a>
		</span>
			</c:when>
		</c:choose>
	</c:otherwise>
</c:choose>
					</ul>
				</div>
			</div>
		</nav>
	</c:when>
	<c:otherwise>
		<nav class="navbar navbar-light navbar-fixed-top" style='background-color: #FFD9EC;'>
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/UspaceAcademy/main.do">UspaceAcademy</a>
				</div>
				<div>
					<ul class="nav navbar-nav">
						<li class="down"><a class="dropdown-toggle"
							data-toggle="dropdown">학원소개</a>
							<ul class="dropdown-menu">
								<li><a href="/UspaceAcademy/academyIntroduce.do">학원소개</a></li>
								<li><a href="/UspaceAcademy/roadIntroduce.do">오시는길</a></li>
								<li><a href="/UspaceAcademy/teacherIntroduce.do">강사소개</a></li>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav">
						<c:choose>
							<c:when test="${sessionScope.memberType=='administrator'}">
								<li><a href="/UspaceAcademy/lecture/lectureAll.do">개설강좌</a></li>
								<!-- 관리자 -->
							</c:when>
							<c:otherwise>
								<li><a href="/UspaceAcademy/lecture/lectureList.do">개설강좌</a></li>
							</c:otherwise>
						</c:choose>
						<li><a
							href="/UspaceAcademy/lectureReview/lecture_review_list.do">수강후기</a></li>
						<li><a href="/UspaceAcademy/notice/list.do?type=공지사항">공지사항</a></li>
					</ul>
					<ul class="nav navbar-nav">
						<li class="down"><a class="dropdown-toggle"
							data-toggle="dropdown">고객센터</a>
							<ul class="dropdown-menu">
								<li><a href="/UspaceAcademy/FAQ/list.do?type=FAQ">FAQ</a></li>
								<li><a
									href="/UspaceAcademy/inquiry/inquiryList.do?advancedType=1:1문의">1:1문의</a></li>
							</ul>
					</ul>
					<ul class="nav navbar-nav">
						<li class="down"><a class="dropdown-toggle"
							data-toggle="dropdown">마이페이지</a>
							<ul class="dropdown-menu">
								<c:choose>
									<c:when test="${sessionScope.memberType=='teacher' }">
										<!-- 강사 로그인 -->
										<li><a href="/UspaceAcademy/teacherInfo.do">내정보</a></li>
										<li><a
											href="/UspaceAcademy/member/selectAllByTeacherId.do">내강좌</a></li>
									</c:when>
									<c:when test="${sessionScope.memberType=='student'}">
										<!-- 학생 로그인 -->
										<li><a href="/UspaceAcademy/studentInfo.do">내정보</a></li>
										<li><a
											href="/UspaceAcademy/attendance/studentLectureInfo.do">내강좌</a></li>
										<li><a href="/UspaceAcademy/lecture/zzimList.do">장바구니</a></li>
										<li><a href="/UspaceAcademy/lecture/applyList.do">결제목록</a></li>
									</c:when>
									<c:otherwise>
										<li><a href="/UspaceAcademy/member/studentAll.do">학생관리</a></li>
										<li><a href="/UspaceAcademy/member/teacherAll.do">강사관리</a></li>
										<li><a href="/UspaceAcademy/lecture/lectureAll.do">강의관리</a></li>
										<!-- <li><a href="/UspaceAcademy/member/selectSalaryList.do">재정관리</a></li> -->
									</c:otherwise>
								</c:choose>
							</ul></li>
					</ul>
					<ul class="nav navbar-nav">

						<c:choose>
							<c:when test="${sessionScope.memberType==null }">
								<!-- 로그인 안했을 때 -->

								<form action="/UspaceAcademy/member/login.do" method="post"
									id="loginForm">
									ID : <input type="text" name="id" placeholder="id를 입력하세요"
										id="aa"> PASSWORD :<input type="password"
										name="password" placeholder="비밀번호를 입력하세요" id="aa"> <input
										type="submit" value="login" class="btn btn-success">
								</form>
							</c:when>
							<c:otherwise>
								<!-- 로그인시 각각 회원 종류에 맞게 환영인사. -->
								<c:choose>
									<c:when test="${sessionScope.memberType=='student' }">
										<span id="bb">${sessionScope.login_info.studentName } 님
											환영합니다. <a href="/UspaceAcademy/member/logout.do"><button
													class="btn btn-success">로그아웃</button></a>
										</span>
									</c:when>
									<c:when test="${sessionScope.memberType=='teacher' }">
										<span id="bb"> ${sessionScope.login_info.teacherName }
											님 환영합니다. <a href="/UspaceAcademy/member/logout.do"><button
													class="btn btn-success">로그아웃</button></a>
										</span>
									</c:when>
									<c:when test="${sessionScope.memberType=='administrator' }">
										<span id="bb"> ${sessionScope.login_info } 님 환영합니다. <a
											href="/UspaceAcademy/member/logout.do"><button
													class="btn btn-success">로그아웃</button></a>
										</span>
									</c:when>
								</c:choose>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
		</nav>
		<form>
			<input type="hidden" id="memberType"
				value="${sessionScope.memberType }">
		</form>
	</c:otherwise>
</c:choose>


<!-- 바꿔본게 위 -->





<%-- <style>
button {
	border: /* 1x */ /* solid */ #FFFFFF; /*---테두리 정의---*/
	background-Color: #FFFFFF; /*--백그라운드 정의---*/
	font: 17px 맑은고딕; /*--폰트 정의---*/
/* 	border: 1x solid #ff0080; ---테두리 정의--- */
/* 	background-Color: #ffe6f2; --백그라운드 정의--- */
	font: 12px 굴림; /*--폰트 정의---*/
	font-weight: bold; /*--폰트 굵기---*/
	color: #DB0000; /*--폰트 색깔---*/
	width: 400;
	height: 100; /*--버튼 크기---*/
	margin-left: 15px;
	margin-right: 15px;
}

table {
	margin-left: auto;
	margin-right: auto;
}

form {
	margin-right: auto;
}
</style>
<!-- <script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	$(document)
			.ready(
					function() { // 마우스를 올렸을 때 하위 메뉴 나온다.
						$("#join").on("mouseover", function(){
							var td = $(this).text();
							$("#td6")
									.html(
											'<a href="/UspaceAcademy/member/studentRegisterForm.do"><button style="width:76px">학생 회원가입</button></a>'
											+ '<a href="/UspaceAcademy/member/teacherRegisterForm.do?codeType=teacherSubject"><button>강사 회원가입</button></a>');
							$("#td1").html("");
							$("#td5").html("");
						}),
						
						$("#introduce")
								.on(
										"mouseover",
										function() { // 학원 소개
											var td = $(this).text();
											$("#td1")
													.html(
															'<a href="/UspaceAcademy/academyIntroduce.do"><button>학원소개</button>' 
															+ '</a><a href="/UspaceAcademy/roadIntroduce.do"><button>오시는길</button></a>'
															+'<a href="/UspaceAcademy/teacherIntroduce.do"><button>강사소개</button></a>');
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
															'<a href="/UspaceAcademy/FAQ/list.do?type=FAQ"><button style="width:76px">FAQ</button></a>'
															+ '<a href="/UspaceAcademy/inquiry/inquiryList.do?advancedType=1:1문의"><button>1대1문의</button></a>');
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
																'<a href="/UspaceAcademy/teacherInfo.do"><button>내 정보</button></a>'
																+ '<a href="/UspaceAcademy/member/selectAllByTeacherId.do"><button>내 강좌</button></a>');

												$("#td1").html("");
												$("#td5").html("");
											} else if ($("#memberType").val() == "student") // 마이페이지 학생 로그인
											{
												$("#td6")
														.html(
																'<a href="/UspaceAcademy/studentInfo.do"><button>내 정보</button></a>'
																+ '<a href="/UspaceAcademy/attendance/studentLectureInfo.do"><button>내 강좌</button></a>'
																+ '<a href="/UspaceAcademy/lecture/zzimList.do"><button>장바구니</button></a>'
																+ '<a href="/UspaceAcademy/lecture/applyList.do"><button>결제 목록</button></a>');

												$("#td1").html("");
												$("#td5").html("");
											} else //마이페이지 관리자 로그인
											{
												$("#td6")
														.html(
																'<a href="/UspaceAcademy/member/studentAll.do"><button style="width:88px">학생관리</button></a>'
																+ '<a href="/UspaceAcademy/member/teacherAll.do"><button style="width:88px" >강사 관리</button>'
																+ '<a href="/UspaceAcademy/lecture/lectureAll.do"><button style="width:88px">강의 관리</button></a>'
																/* + '<a href="/UspaceAcademy/member/selectSalaryList.do"><button style="width:88px">월급 관리</button></a>' */);
																
												$("#td1").html("");
												$("#td5").html("");
											}
										});
					});
</script> -->


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
				<td><button id="join">회원가입</button></a></td>

			</tr>

			<tr>
				<td width="150" id="td1"></td>
				<td width="150" id="td2"></td>
				<td width="150" id="td3"></td>
				<td width="150" id="td4"></td>
				<td width="150" id="td5"></td>
				<td width="500" id="td6"></td>
			</tr>
		</table>

	</c:when>
	<c:otherwise>
		<br>
		<table>
			<tr>
				<td><a href="/UspaceAcademy/academyIntroduce.do"><button
							id="introduce">학원소개</button></a></td>
							
				<c:choose>
					<c:when test="${sessionScope.memberType=='administrator'}">
					
				<td><a href="/UspaceAcademy/lecture/lectureAll.do"><button
							id="lecture">개설강좌</button></a></td> <!-- 관리자용 강의 리스트 -->
					</c:when>	
					<c:otherwise>
						<td><a href="/UspaceAcademy/lecture/lectureList.do"><button
							id="lecture">개설강좌</button></a></td>
					</c:otherwise>
				</c:choose>						
		
				<td><a
					href="/UspaceAcademy/lectureReview/lecture_review_list.do?reviewNo"><button
							id="lectureReview">수강후기</button></a></td>
				<td><a href="/UspaceAcademy/notice/list.do?type=공지사항"><button
							id="notice">공지사항</button></a></td>
				<td><a href="/UspaceAcademy/FAQ/list.do?type=FAQ"><button
							id="customerCenter">고객센터</button></a></td>
				<td><button id="myPage">마이페이지</button></td>
							
							
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

<br> --%>
