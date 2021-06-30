<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%@ include file="layout/header.jsp"%>

<%-- class="container"는 중앙배치를 위함이다 --%>
<div class="container">

	<c:forEach var="board" items="${boards.content }">
		<%-- content에 실질적인 테이블의 entity들이 담긴다 --%>
		<div class="card m-2">
			<div class="card-body">
				<p>
					<span><a href="/board/detail/${board.id }"><font size="5px" color="black">${board.title }</font></a></span>
					<span style="float:right"><i>작성자 : ${board.user.username }</i></span><br />
					<span style="float:right"><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd" /></span>
				</p>
			</div>
		</div>
	</c:forEach>
	
	<br />

	<ul class="pagination justify-content-center"> <%-- justify-content-center => 가운데 정렬 --%>
	
		<c:choose>
			<c:when test="${boards.first }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number-1 }">이전</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number-1 }">이전</a></li>
			</c:otherwise>
		</c:choose>
		
		<c:choose>
			<c:when test="${boards.last }">
				<li class="page-item disabled"><a class="page-link" href="?page=${boards.number+1 }">다음</a></li>
			</c:when>
			<c:otherwise>
				<li class="page-item"><a class="page-link" href="?page=${boards.number+1 }">다음</a></li>
			</c:otherwise>
		</c:choose>
	</ul>

</div>

<%-- footer를 따로 만들어 관리 --%>
<%@ include file="layout/footer.jsp"%>