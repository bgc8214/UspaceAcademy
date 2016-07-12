<%@page contentType="text/html;charset=utf-8"%>
<style>
table#tb {
	width:700px;
	height:100px;
}
th {
	width:100px;
}
</style>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){
	$("#remove").on("click", function(){
		return confirm("정말 탈퇴 하시겠습니까?");
	})
})
</script>
<h3 class="pageTlt">개인 정보</h3>
<hr>
<table class="table table-bordered form-table" id="tb">
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
<div align="right">
	<a href="/UspaceAcademy/member/updateStudentForm.do"><button class="btn btn-warning">수정</button></a>
	<a href="/UspaceAcademy/member/deleteStudent.do"><button id="remove" class="btn btn-danger">탈퇴</button></a>
</div>