<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<%-- delete를 위한 hidden 값 --%>
	<input type="hidden" id="boardId" value="${board.id }" />

	<button class="btn btn-secondary" onclick="history.back()">뒤로가기</button>

	<div class="float-right">
		<c:if test="${board.user.id == principal.user.id }">
			<a href="/board/updateForm/${board.id }" class="btn btn-warning">수정</a>
			<button id="btn-board-delete" class="btn btn-danger">삭제</button>
		</c:if>
	</div>

	<br />
	<br />

	<div>
		<p>
			<span><i>작성자 : ${board.user.username }</i></span> <span style="float: right"><fmt:formatDate value="${board.createDate}" pattern="yyyy-MM-dd" /></span>
		</p>
	</div>

	<hr />

	<div class="card">
		<div class="card-header">
			<h3>${board.title }</h3>
		</div>


		<div class="card-body">
			<div>${board.content }</div>
		</div>

	</div>

	<hr />

	<textarea id="replyContent" class="form-control" rows="2"></textarea>
	<br />
	<button id="btn-reply-save" class="btn btn-primary float-right">댓글등록</button>

	<br /> <br />
	<hr />

	<div class="card">
		<div class="card-header">댓글</div>
		<ul class="list-group">
			<%-- List<Reply> 객체는 Board.java의 mappedBy = "board"에 의해 자동으로 끌어온다. 즉, 글상세보기() 메서드로 충분하다 --%>
			<c:forEach var="reply" items="${board.replies }">

				<input type="hidden" id="replyId" value="${reply.id }" />

				<li class="list-group-item d-flex justify-content-between">
					<div>${reply.content }</div>
					<div class="d-flex">
						<c:if test="${reply.user.id == principal.user.id }">
							<button onclick="replyIndex.replyDelete(${board.id}, ${reply.id })" class="badge">삭제</button>
						</c:if>
						<div>
							<i>&nbsp;&nbsp;&nbsp;작성자 : ${reply.user.username }&nbsp;</i>
						</div>
					</div>
				</li>

			</c:forEach>
		</ul>
	</div>

</div>

<%-- Summer Note의 Javascript 부분이다 --%>
<%-- class를 지칭하는 .으로 summernote를 찾는다 --%>
<script>
	$('.summernote').summernote({
		placeholder : 'Enter Content',
		tabsize : 2,
		height : 300
	});
</script>

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/board.js로 해도 문제없다 --%>
<script src="/js/board.js"></script>
<script src="/js/reply.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>