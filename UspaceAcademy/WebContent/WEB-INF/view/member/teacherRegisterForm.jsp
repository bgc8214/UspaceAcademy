<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
//아이디 입력않하고 가입 버튼 눌렀을 때 alert띄우기
function checkValue() {
	// 비밀번호 정규식 체크 OK
	var pwExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{5,11}$/;
	if(!document.join_form.teacherPassword.value.match(pwExp)) {
		alert("비밀번호는 6~12자, 영문, 숫자, 특수문자의 조합으로 입력해주세요.");
		return false;	
	}

 	// 이름 정규식 체크(한글이름) OK
	var usenameExp = /^[가-힣]{2,4}$/;
	if(!document.join_form.teacherName.value.match(usenameExp)) {
		alert("3~5자, 정확한 한글이름을 입력해 주세요.");
		return false;
 	}
	// 이메일 정규식 체크 OK
	var emailExp = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	if(!document.join_form.teacherEmail.value.match(emailExp)) {
		alert("이메일 형식에 맞게 입력하세요.");
		return false;
	}  
	
  	// 휴대폰 번호 체크(ex]011-111(1)-1111) 
	var telExp = /^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/;
	if(!document.join_form.teacherPhoneNo.value.match(telExp)) {
		alert("올바른 휴대폰번호를 입력하세요");
		return false;
	}
	// 주소가 입력되지 않은 경우
 	if($("#addr1").val()=='' || $("#addr2").val()=='') {
		alert("주소를 입력해주세요");
		return false;
	} 
	return true;
	
	
}

function idCheck()
{
	window.open("/UspaceAcademy/teacherIdCheck.do","아이디 중복 확인","width=400,height=200,resizable=no");
}
</script>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<h3 class="pageTlt">강사 가입폼</h3>
<hr>
<form action="/UspaceAcademy/member/teacherRegister.do" method="post" name="join_form" onsubmit="return checkValue();">
<table class="table table-bordered form-table">
	<tr>
		<th>ID</th>
		<td><input type="text" name="teacherId" onclick="idCheck();" value="${requestScope.teacher.teacherId }" readonly="readonly"><span class="error">
		<form:errors path="teacher.teacherId" delimiter="//"/> <!-- BindingResult의 에러메세지 출력 -->
		
		</span></td>
	</tr>
	<tr>
		<th>패스워드</th>
		<td><input type="password" name="teacherPassword"><span class="error"><form:errors path="teacherPassword" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이름</th>
		<td><input type="text" name="teacherName" value="${requestScope.teacher.teacherName }"><span class="error"><form:errors path="teacher.teacherName" delimiter="//"/></span></td>
	</tr>
	<tr>
		<th>이메일주소</th>
		<td><input type="text" name="teacherEmail"  value="${requestScope.teacher.teacherEmail }"><span class="error"></span><form:errors path="teacher.teacherEmail" delimiter="//"/></td>
	</tr>
	<tr>
		<th>핸드폰 번호</th>
		<td><input type="text" name="teacherPhoneNo" value="${requestScope.teacher.teacherPhoneNo }" placeholder="ex> 010-111(1)-1111"><span class="error"><form:errors path="teacher.teacherPhoneNo" delimiter="//"/></span></td>
	</tr>
	<tr>
			<th>우편번호</th>
			<td>
				<input name="postnum" type="text" disabled="disabled" placeholder="우편번호"/>&nbsp;
				<input value="검색" type="button" onclick="openAddr();"/> 
			</td>
		</tr>
		<tr>
			<th>주소</th>
			<td>
				<input id="addr1" name="addr1" type="text" readonly="readonly" placeholder="주소"/>
				<input id="addr2" name="addr2" type="text" placeholder="상세주소"/><span class="error"><form:errors path="teacher.teacherAddress" delimiter="//"/></span>
			</td>
		</tr>
	
	
	<tr>
		<th>과목</th>
		<td>
			<select name="teacherSubject">
			<c:forEach items="${requestScope.codeType }" var="code">
				<option value="${code.codeName }">${code.codeName }</option>
			
			</c:forEach>
			</select>
		</td>
	</tr>
</table>
<p class="text-right"><input id="btn" type="submit" value="가입" class="btn btn-primary"></p>
</form>
