<%@ page contentType ="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<form action="/UspaceAcademy/member/teacherIdCheck.do">
<fieldset>
	<dt>아이디 입력</dt>
	<dd><input type="text" name="id"></dd>
	<dd><input type="submit" value="중복확인" class="btn btn-info"></dd>
</fieldset>
</form>
<c:choose>
	<c:when test="${requestScope.idCheck==true}">
		<c:if test="${requestScope.id!=null}">
			<script type="text/javascript">
			if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?"))
			{
				
				opener.document.join_form.teacherId.value="${requestScope.id}";
				window.close();
			}
			</script>
		</c:if>	
	</c:when>
	<c:when test="${requestScope.idCheck==false }">
		<script type="text/javascript">
			alert('중복되는 아이디가 존재합니다');
			</script>
	</c:when>
	<c:when test="${requestScope.idError!=null }">
		<script type="text/javascript">
			alert('${requestScope.idError}');
		</script>
	</c:when>
</c:choose>
