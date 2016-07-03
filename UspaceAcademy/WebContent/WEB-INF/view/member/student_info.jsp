<%@page contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){
	$(".remove").on("click", function(){
		return confirm("정말 탈퇴 하시겠습니까?");
	})
})
</script>

<table border="3" width="500">
<tr>
	<th>아이디</th><td>${sessionScope.login_info.studentId}</td>
</tr>
<tr>
	<th>이름</th><td>${sessionScope.login_info.studentName}</td>
</tr>
<tr>
	<th>이메일</th><td>${sessionScope.login_info.studentEmail}</td>
</tr>
<tr>
	<th>전화번호</th><td>${sessionScope.login_info.studentPhoneNo}</td>
</tr>
<tr>	
	<th>주소</th><td>${sessionScope.login_info.studentAddress}</td>
</tr>
</table>
<%-- 아이디 : <input type="text"  value="${sessionScope.login_info.studentId}" readonly="readonly"><br>
이름 : <input type="text"	value="${sessionScope.login_info.studentName}" readonly="readonly"><br>
이메일 : <input type="text"	value="${sessionScope.login_info.studentEmail}" readonly="readonly"><br>
전화번호 : <input type="text"	value="${sessionScope.login_info.studentPhoneNo}" readonly="readonly"><br>
주소 : <input type="text"	value="${sessionScope.login_info.studentAddress}" readonly="readonly"><br> --%>
<p>
<a href="/UspaceAcademy/member/updateStudentForm.do"><button>수정</button></a>
<a href="/UspaceAcademy/member/deleteStudent.do"><button class="remove">탈퇴</button></a>