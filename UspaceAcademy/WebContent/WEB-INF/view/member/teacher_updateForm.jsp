<%@page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<style type="text/css">
table#tb {
	width:700px;
	height:100px;
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
	if(!document.update_form.teacherPassword.value.match(pwExp)) {
		alert("비밀번호는 6~12자, 영문, 숫자, 특수문자의 조합으로 입력해주세요.");
		return false;	
	}

 	// 이름 정규식 체크(한글이름) OK
	var usenameExp = /^[가-힣]{2,4}$/;
	if(!document.update_form.teacherName.value.match(usenameExp)) {
		alert("2~4자, 정확한 한글이름을 입력해 주세요.");
		return false;
 	}
	// 이메일 정규식 체크 OK
	var emailExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	if(!document.update_form.teacherEmail.value.match(emailExp)) {
		alert("이메일 형식에 맞게 입력하세요.");
		return false;
	}  
	
  	// 휴대폰 번호 체크(ex]011-111(1)-1111) 
	var telExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	if(!document.update_form.teacherPhoneNo.value.match(telExp)) {
		alert("올바른 휴대폰번호를 입력하세요");
		return false;
	}  
}
</script>
<h3 class="pageTlt">개인 정보 수정</h3>
<hr>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<form action="/UspaceAcademy/member/updateTeacher.do" name="update_form" method="post" onsubmit="return checkValue();">
<input type="hidden" name="baseAddress" value="${sessionScope.login_info.teacherAddress}">
<table class="table table-striped" id="tb">
	<tr>
		<th>아이디</th>
		<td><input type="text"  name="teacherId" value="${sessionScope.login_info.teacherId}" readonly="readonly"></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text"	name="teacherName" value="${sessionScope.login_info.teacherName}"><span class="error"><form:errors path="updateForm.teacherName" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>비밀번호</th>
		<td><input type="password" name="teacherPassword" value="${sessionScope.login_info.teacherPassword}"><span class="error"><form:errors path="updateForm.teacherPassword" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>이메일</th>
		<td><input type="text" name="teacherEmail" value="${sessionScope.login_info.teacherEmail}"><span class="error"><form:errors path="updateForm.studdent.Email" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>전화번호</th>
		<td><input type="text" name="teacherPhoneNo" value="${sessionScope.login_info.teacherPhoneNo}"><span class="error"><form:errors path="updateForm.studentPhoneNo" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>우편번호</th>
		<td><input name="postnum" type="text" disabled="disabled" placeholder="우편번호"/>&nbsp;
		<input value="검색" type="button" onclick="openAddr();"/></td>
	</tr>
	<tr>
		<th>주소</th>
		<td><input id="addr1" name="addr1" type="text" value="${sessionScope.login_info.teacherAddress}" readonly="readonly" placeholder="주소"/>
	 <input id="addr2" name="addr2" type="text" placeholder="상세주소"/><span class="error"><form:errors path="updateForm.teacherAddress" delimiter=","/></span></td>
	</tr>
	<tr>
		<th>과목</th>
		<td><select name="teacherSubject">
		<option value="${sessionScope.login_info.teacherSubject}">${sessionScope.login_info.teacherSubject}</option>
	</select><span class="error"><form:errors path="updateForm.teacherSubject" delimiter="," /></span>
	</tr>
</table>
	<div align="right">
		<input id="btn" type="submit" value="수정" class="btn btn-warning">
	</div>	
</form>