<%@page contentType="text/html;charset=utf-8"%>
<h1>개인정보</h1>

아이디 : <input type="text"  value="${sessionScope.login_info.teacherId}" readonly="readonly"><br>
이름 : <input type="text"	value="${sessionScope.login_info.teacherName}" readonly="readonly"><br>
이메일 : <input type="text"	value="${sessionScope.login_info.teacherEmail}" readonly="readonly"><br>
전화번호 : <input type="text"	value="${sessionScope.login_info.teacherPhoneNo}" readonly="readonly"><br>
주소 : <input type="text"	value="${sessionScope.login_info.teacherAddress}" readonly="readonly"><br>
과목 : <select>
		<option>${sessionScope.login_info.teacherSubject}</option>
	</select>
<p>
<a href="/UspaceAcademy/member/updateTeacherForm.do"><button>수정</button></a>
<a href="/UspaceAcademy/member/deleteTeacher.do"><button>탈퇴</button></a>
<%-- <a href="/UspaceAcademy/member/deleteTeacher.do?teacherId=${sessionScope.login_info.teacherId}"><button>탈퇴</button></a> --%>
