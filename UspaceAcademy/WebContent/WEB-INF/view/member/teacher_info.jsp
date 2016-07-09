<%@page contentType="text/html;charset=utf-8"%>
<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
var tmp;//이벤트소스를 저장하기 위한 변수
$(document).ready(function(){
	$("#remove").on("click", function(){
		return confirm("정말 탈퇴 하시겠습니까?");
	})
})
</script>
<h3 class="pageTlt">개인정보</h3>
<table class="table table-bordered form-table">
	<tbody>
		<tr>
			<th>아이디</th>
			<td>${sessionScope.login_info.teacherId}</td>
		</tr>
		<tr>
			<th>이름</th>
			<td>${sessionScope.login_info.teacherName}</td>
		</tr>
		<tr>
			<th>이메일</th>
			<td>${sessionScope.login_info.teacherEmail}</td>
		</tr>
		<tr>
			<th>전화번호</th>
			<td>${sessionScope.login_info.teacherPhoneNo}</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>${sessionScope.login_info.teacherAddress}</td>
		</tr>
		<tr>
			<th>과목</th>
			<td>${sessionScope.login_info.teacherSubject}</td>	
		</tr>
		<tr>
			<th>월급 정보</th>
			<td>
				<a href="/UspaceAcademy/member/selectSalaryByTeacherId.do?teacherId=${sessionScope.login_info.teacherId}">${teacher.teacherName} 강사님</a>
			</td>	
		</tr>
	</tbody>
</table>
<p>
<div align="right">
<a href="/UspaceAcademy/member/updateTeacherForm.do"><button class="btn btn-warning">수정</button></a>
<a href="/UspaceAcademy/member/deleteTeacher.do?"><button id="remove" class="btn btn-danger">탈퇴</button></a>
</div>