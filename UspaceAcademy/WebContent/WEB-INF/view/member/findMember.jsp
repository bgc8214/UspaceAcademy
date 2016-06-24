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
<h2>아이디 찾기</h2>
이름 : <input type="text" id="name">
핸드폰 번호 : <input type="text" id="phoneNo">
<button id="findIdBtn" >아이디 찾기</button> <br>
<h2>비밀번호 찾기</h2>
아이디 : <input type="text" id="id">
이메일 주소 : <input type="text" id="email">
<button id="findPasswordBtn" >비밀번호 찾기</button> <br>


