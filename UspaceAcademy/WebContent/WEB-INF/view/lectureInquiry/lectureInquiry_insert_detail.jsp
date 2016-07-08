<%@ page contentType="text/html;charset=utf-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript" src="/UspaceAcademy/jQuery/jQuery.js"></script>
<script type="text/javascript">
	
$(document).ready(effect);
function effect(){
	$("tr:eq(2)").css("background-color", "palegreen");
	$("tr:eq(3)").css("background-color", "sky-blue");

}

</script>

<table border="" class="table table-bordered">
	<tr>
		<td>
			no: ${requestScope.lectureInquiryDetail.advancedNo}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글쓴이: ${requestScope.lectureInquiryDetail.advancedId}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			글 등록일:${requestScope.lectureInquiryDetail.advancedDate}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			조회수: ${requestScope.lectureInquiryDetail.advancedHit}
		</td>
	</tr>
	<tr>
		<td>
			제목: ${requestScope.lectureInquiryDetail.advancedTitle}
		</td>
	</tr>
	<tr>
		<td>
			내용: ${requestScope.lectureInquiryDetail.advancedContent}
		</td>
	</tr>
</table>

<p>

<a href="/UspaceAcademy/lectureInquiry/lectureInquiryList.do?lectureNo2=${requestScope.lectureInquiryDetail.lectureNo2 }">1:1문의 목록</a>