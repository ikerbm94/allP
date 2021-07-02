<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%-- header를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/header.jsp"%>

<div class="container">
	<form action="/auth/loginProc" method="POST">

		<p>
			<b>아이디</b>
		</p>
		<div class="row">
			<div class="col">
				<input type="text" class="form-control" name="username">
			</div>
			<div class="col"></div>
		</div>
		<br />

		<p>
			<b>비밀번호</b>
		</p>
		<div class="row">
			<div class="col">
				<input type="password" class="form-control" name="password">
			</div>
			<div class="col"></div>
		</div>
		<br/><br/>
		
		<button id="btn-login" class="btn btn-primary">로그인</button>

	</form>
</div>

<%-- footer를 따로 만들어 관리 --%>
<%-- 위치가 views인 index.jsp에서와는 달리 joinForm.jsp의 위치는 views/user이기 때문에 앞에 ../를 붙여줘야 한다 --%>
<%@ include file="../layout/footer.jsp"%>