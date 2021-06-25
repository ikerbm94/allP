<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">

	<form>
	
		<div class="form-group">
			<label for="username">아이디</label>
			<input type="text" class="form-control" placeholder="Enter username" id="username">
			<div id="username_check"></div>
		</div>
		
		<div class="form-group">
			<label for="password">비밀번호</label>
			<input type="password" class="form-control" placeholder="Enter password" id="password">
		</div>
		
		<div class="form-group">
			<label for="email">이메일</label>
			<input type="email" class="form-control" placeholder="Enter email" id="email">
		</div>
		
	</form>
	
	<button id="btn-save" class="btn btn-primary">회원가입</button>
	
</div>

<%-- static 폴더의 경로까지도 기본세팅이 되어있기 때문에 /js/user.js로 해도 문제없다 --%>
<script src="/js/joinForm.js"></script>
<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>