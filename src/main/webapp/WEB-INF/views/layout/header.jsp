<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%-- jsp의 taglibs --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%-- security의 taglibs --%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<%-- PrincipalDetailService에서 받아온 로그인 정보인 UserDetails 객체가 세션에 저장된다 --%>
<%-- 따라서 세션 정보인 principal을 받아와서 다시 principal이라는 var 값에 담는다 --%>
<%-- 즉, 로그인 정보인 session을 principal로 관리한다 --%>
<sec:authorize access="isAuthenticated()">
	<sec:authentication property="principal" var="principal" />
</sec:authorize>

<!DOCTYPE html>
<html lang="en">
<head>
<title>allP</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<%-- 순서 중요 --%>

<%-- Popper --%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<%-- JQuery --%>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<%-- Bootstrap --%>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<%-- Summer Note --%>
<link href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>

</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">

		<a class="navbar-brand" href="/">allP</a>

		<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="collapsibleNavbar">

			<c:choose>

				<%-- security의 taglibs를 통해 principal을 받아옴 --%>
				<%-- principal이 비어있을때 --%>
				<c:when test="${empty principal }">
					<ul class="navbar-nav">
						<%-- UserController의 loginForm.jsp로 이동 --%>
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
						<%-- UserController의 joinForm.jsp로 이동 --%>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
					</ul>
				</c:when>

				<%-- principal이 존재할때 --%>
				<c:otherwise>
					<ul class="navbar-nav mr-auto">
						<li class="nav-item"><a class="nav-link" href="/board/saveForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link" href="/user/updateForm">회원정보</a></li>
						<li class="nav-item"><a class="nav-link" href="/logout">로그아웃</a></li>
					</ul>
					<ul class="navbar-nav ml-auto">
						<li class="nav-item"><a class="nav-link">${principal.user.username }님 안녕하세요!</a></li>
					</ul>
				</c:otherwise>

			</c:choose>

		</div>

	</nav>
	<br>