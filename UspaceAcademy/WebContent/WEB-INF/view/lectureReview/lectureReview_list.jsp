
<!--  notice -->
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!-- ??????? -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!-- ??????? -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>lectureReview_list.jsp</title>
</head>
<body>
<h5>수강후기</h5>


<%-- <%-- <c:if test="${fn:length(request.Scope.noticeList) != 0 }"><!-- 게시물이 있으면 --> --%>
	<table width="500" border='1'>
		<thead>
			<tr>
				<td>글번호</td>
				<td>강의과목</td>
				<td>강의명</td>
				<td>제목</td>
				<td>작성자</td>
				<td>날짜</td>
				<td>조회수</td>
				<!-- <td>내용</td> -->
			</tr>
		</thead>

		
		<tbody>
			<c:forEach items="${requestScope.list}" var="noticeList" ><!-- listPaging에 list,     forEach는 컬렉션(배열)이다 var는 반복할때마다 나오는 저장할 변수임 --> 
				<tr>
					<td>${noticeList.no }</td>
																					<!--   제목눌렀을때(링크) 상세페이지로 이동해야해서 a href함 , ${noticeList.no} -> list안에 no값을 넘겨줌 ,  page=${requestScope.pageBean.page} -> pageBean에 page(현재페이지)를 넘겨준다(다시 목록보기를 눌러도 1페이지에 안가게하려고)  -->
					<td><a href ="${initParam.rootPath }/controller?command=noticeDetail&no=${noticeList.no}&page=${requestScope.pageBean.page}"> [${noticeList.codeName}]${noticeList.title}</a></td>
					<td>${noticeList.day}</td>
					<td>${noticeList.hits}</td>
				</tr> 
	 		</c:forEach> 
		</tbody>
	</table>






<p>
<!-- 이전 페이지 그룹 처리
		-만약에 이전페이지 그룹이 있으면 링크처리하고 없으면 화살표만 나오도록 처리 -->
		<!--  when-if  ,  otherwise-else  ,  choose반복문 -->
	<c:choose>
	<c:when test='${requestScope.pageBean.previousPageGroup }'><!--  pagingBean.java에 previousPageGroup(이전 페이지 그룹이 있는지 체크) 부름-->
	<a href = "${initParam.rootPath}/controller?command=noticeList&page=${requestScope.pageBean.beginPage-1}"> ◀ </a><!--  pagingBean.java에 BeginPage부름,  이전페이지로가기위해 -1 -->
	<!--  href -->
	</c:when>
	<c:otherwise>
	◀
	</c:otherwise>
		</c:choose>


	
	
	
	
	
<!-- 현재 page가 속한 page 그룹내의 페이지들 링크
		-현재 pageGroup의 시작 page~끝page			 
		
		만약에 p가 현재페이지면 링크처리를 하지 않고 p가 현재페이지가 아니라면 링크처리
		-->
<c:forEach begin='${requestScope.pageBean.beginPage }' end='${requestScope.pageBean.endPage }' var="p">
<c:choose>
<c:when test="${p != requestScope.Bean.Page }">
<a href="${initParam.rootPath}/controller?command=noticeList&page=${p }"> ${p } </a>
</c:when>
<c:otherwise>
[${p }] &nbsp;
</c:otherwise>
</c:choose>
</c:forEach>




	
	
	
<!--  다음 페이지 그룹으로 이동
		-만약에 다음페이지 그룹이 있으면 링크처리 없으면 화살표만 나오도록 처리		 -->	
	<c:choose>
	<c:when test = "${requestScope.pageBean.nextPageGroup }" >
	<a href = "${initParam.rootPath}/controller?command=noticeList&page=${requestScope.pageBean.endPage+1 }"> ▶ </a><!--  다음페이지로 가기위해 +1   -->
	</c:when>
	<c:otherwise>
	▶
	</c:otherwise>
		</c:choose>





 --%>



</body>
</html>