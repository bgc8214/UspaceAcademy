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
<a href="/UspaceAcademy/member/deleteTeacher.do"><button class="remove">탈퇴</button></a>
