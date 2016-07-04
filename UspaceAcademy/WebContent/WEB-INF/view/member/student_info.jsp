<%@page contentType="text/html;charset=utf-8"%>
아이디 : <input type="text"  value="${sessionScope.login_info.studentId}" readonly="readonly"><br>
이름 : <input type="text"	value="${sessionScope.login_info.studentName}" readonly="readonly"><br>
이메일 : <input type="text"	value="${sessionScope.login_info.studentEmail}" readonly="readonly"><br>
전화번호 : <input type="text"	value="${sessionScope.login_info.studentPhoneNo}" readonly="readonly"><br>
주소 : <input type="text"	value="${sessionScope.login_info.studentAddress}" readonly="readonly"><br>
<p>
<a href="/UspaceAcademy/member/updateStudentForm.do"><button>수정</button></a>
<a href="/UspaceAcademy/member/deleteStudent.do"><button>탈퇴</button></a>