<%@ page contentType="text/html;charset=utf-8"%>
<form action="/UspaceAcademy/member/login.do" method="post">
	<fieldset>
		<dl>
			<dt><label>ID</label>&nbsp;&nbsp;&nbsp;<input type="text" name="id"  style="width:200px" placeholder="아이디를 입력하세요"><font color="red"> ${requestScope.idError }</font></dt>
		</dl>
		<dl>
			<dt><label>PASSWORD</label>&nbsp;&nbsp;&nbsp;<input type="password" name="password"  style="width:200px" placeholder="비밀번호를 입력하세요"><font
				color="red">  ${requestScope.passwordError }</font></dt>
		</dl>
	</fieldset>
		<p><input type="submit" value="login" class="btn btn-success"></p>
</form>





<a href="/UspaceAcademy/findMember.do"><button class="btn btn-default">아이디 패스워드 찾기</button></a>





