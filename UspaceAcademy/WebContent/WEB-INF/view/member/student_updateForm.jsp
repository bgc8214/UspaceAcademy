<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
table {
	
}

span, td, th {
	padding: 5px;
}

span {
	font-size: .9em;
	color: red;
}
</style>
<script>
	function openAddr() {
		new daum.Postcode({
			oncomplete:function(data) {
				document.getElementsByName("postnum")[0].value = data.zonecode;
					document.getElementsByName("addr1")[0].value = data.roadAddress;
			}
		}).open();
	}
</script>
<script type="text/javascript">
function checkValue() {
	// 비밀번호 정규식 체크 OK
	var pwExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{6,12}$/;
	if(!document.update_form.studentPassword.value.match(pwExp)) {
		alert("비밀번호는 6~12자, 영문, 숫자, 특수문자의 조합으로 입력해주세요.");
		return false;	
	}

 	// 이름 정규식 체크(한글이름) OK
	var usenameExp = /^[가-힣]{2,4}$/;
	if(!document.update_form.studentName.value.match(usenameExp)) {
		alert("2~4자, 정확한 한글이름을 입력해 주세요.");
		return false;
 	}
	// 이메일 정규식 체크 OK
	var emailExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	if(!document.update_form.studentEmail.value.match(emailExp)) {
		alert("이메일 형식에 맞게 입력하세요.");
		return false;
	}  
	
  	// 휴대폰 번호 체크(ex]011-111(1)-1111) 
	var telExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	if(!document.update_form.studentPhoneNo.value.match(telExp)) {
		alert("올바른 휴대폰번호를 입력하세요");
		return false;
	}  
}
</script>
<h3 class="pageTlt">개인 정보 수정</h3>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<form action="/UspaceAcademy/member/updateStudent.do" method="post" name="update_form" onsubmit="return checkValue();">
<input type="hidden" name="baseAddress" value="${sessionScope.login_info.studentAddress}">
<table border="2" class="table table-bordered">
	<tr>
		<th>아이디</th>
		<td><input type="text" name="studentId" value="${sessionScope.login_info.studentId}" readonly="readonly"> <span class="error"><form:errors path="updateForm.studentId" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="studentName" value="${sessionScope.login_info.studentName}"> <span class="error"><form:errors path="updateForm.studentName" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="studentPassword" value="${sessionScope.login_info.studentPassword}"> <span class="error"><form:errors path="updateForm.studentPassword" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name="studentEmail" value="${sessionScope.login_info.studentEmail}"> <span class="error"><form:errors path="updateForm.studentEmail" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="text" name="studentPhoneNo" value="${sessionScope.login_info.studentPhoneNo}"> <span class="error"><form:errors path="updateForm.studentPhoneNo" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td><input name="postnum" type="text" disabled="disabled" placeholder="우편번호"/>&nbsp;<input value="검색" type="button" onclick="openAddr();"/></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input id="addr1" name="addr1" type="text" value="${sessionScope.login_info.studentAddress}" readonly="readonly" placeholder="주소"/>
	 		<input id="addr2" name="addr2" type="text" placeholder="상세주소"/><span class="error"><form:errors path="updateForm.studentAddress" delimiter=","/></span></td>
</table>
	<div align="right">
		<input id="btn" type="submit" value="수정" class="btn btn-warning">
	</div>	
</form>
