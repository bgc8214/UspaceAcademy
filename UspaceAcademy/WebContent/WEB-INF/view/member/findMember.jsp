<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">

$(document).ready(function(){
		$("#findIdBtn").on("click",function(){ //아이디 찾는 버튼
			$.ajax({
				"url" : "/UspaceAcademy/member/findId.do",
				"type" : "POST",
				"data" : "name="+$("#name").val()+"&phoneNo="+$("#phoneNo").val(),
				"dataType" : "text",
				"success" : function(txt){
					window.open("/UspaceAcademy/findIdResult.do?findId="+txt,"아이디 확인","width=400,height=200,resizable=no");
				},
				"error" : function(xhr,status,errorMsg){
					alert("오류가 발생했습니다.");
				},
				"beforeSend" : function(){
					if(!$("#name").val()){
						alert("이름을 입력하세요");
						$("#name").focus();
						return false;
					}
					if(!$("#phoneNo").val()){
						alert("핸드폰 번호를 입력하세요");
						$("#phoneNo").focus();
						return false;
					}
					
				}
			});
		})
		
			$("#findPasswordBtn").on("click",function(){ // 비밀번호 찾는 버튼
			$.ajax({
				"url" : "/UspaceAcademy/member/findPassword.do",
				"type" : "POST",
				"data" : "id="+$("#id").val()+"&email="+$("#email").val(),
				"dataType" : "text",
				"success" : function(txt){
					alert(txt);
					//window.open("/UspaceAcademy/findIdResult.do?findId="+txt,"아이디 확인","width=400,height=200,resizable=no");
				},
				"error" : function(xhr,status,errorMsg){
					alert("오류가 발생했습니다.");
				},
				"beforeSend" : function(){
					if(!$("#id").val()){
						alert("아이디를 입력하세요");
						$("#id").focus();
						
						return false;
					}
					if(!$("#email").val()){
						alert("이메일을 입력하세요");
						$("#email").focus();
						
						return false;
					}
					
				}
			});
		})
		
});


	
</script>
<div align="center">
<h3 class="pageTlt">아이디 찾기</h3>
<hr>
<form class="form-inline" role="form">
	<table>
		<tr>
			<th>이름</th>
			<td>&nbsp;&nbsp;<input type="text" id="name">&nbsp;&nbsp;</td>
			<th>핸드폰 번호</th>
			<td>&nbsp;&nbsp;<input type="text" id="phoneNo"></td>
			<td>&nbsp;&nbsp;<button id="findIdBtn" class="btn btn-primary">아이디 찾기</button> </td>	
		</tr>	
	</table>
</form>
<hr>
<p>
<p>
<h3 class="pageTlt">비밀번호 찾기</h3>
<hr>
<form class="form-inline" role="form">
	<table>
		<tr>
			<th>아이디</th>
			<td>&nbsp;&nbsp;<input type="text" id="id">&nbsp;&nbsp;</td>
			<th>이메일 주소</th>
			<td>&nbsp;&nbsp;<input type="text" id="email"></td>
			<td>&nbsp;&nbsp;<button id="findPasswordBtn" class="btn btn-primary">비밀번호 찾기</button></td>
		</tr>
	</table>
</form>
</div>


