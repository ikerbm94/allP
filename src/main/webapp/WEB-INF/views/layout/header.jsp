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

<style>

<%-- header --%>
 
.navbar-nav > li > .dropdown-menu {
	background-color: #343a40;
}

.navbar-nav > li > .dropdown-menu > .dropdown-item {
	color: white;
}

.navbar-nav > li > .dropdown-menu > .dropdown-item:hover {
	color: white;
	background-color: #343a40;
}

<%-- footer --%>

.full {
    width: 100%;    
}
.gap {
	height: 30px;
	width: 100%;
	clear: both;
	display: block;
}
.footer {
	background: #002147;
	height: auto;
	padding-bottom: 30px;
	position: relative;
	width: 100%;
	border-bottom: 1px solid #CCCCCC;
	border-top: 1px solid #DDDDDD;
}
.footer p {
	margin: 0;
}
.footer img {
	max-width: 100%;
}
.footer h3 {
	color: white;
	font-size: 18px;
	font-weight: 600;
	line-height: 27px;
	padding: 40px 0 0px;
	text-transform: uppercase;
  margin-bottom: 15px;
}

.footer h4 {
	color: white;
	font-size: 2em;
	font-weight: 600;
	line-height: 38px;
	padding: 40px 0 10px;
	font-family: cursive;
  font-weight: lighter
}

.footer ul {
	font-size: 13px;
	list-style-type: none;
	margin-left: 0;
	padding-left: 0;
	margin-top: 0px;
	color: #7F8C8D;
  padding: 0 0 8px 0;
}

.email{
  border-bottom: 3px solid #fff;
}
.footer ul li a {
	padding: 0 0 12px 0;
	display: block;
}
.footer a {
	color: white;
  font-weight: lighter;
}

.footer p {
	color: white;
  font-weight: lighter;
  font-size: 
}

.footer a:hover {
	text-decoration:none;
  font-weight: bold;
}
.supportLi h4 {
	font-size: 20px;
	font-weight: lighter;
	line-height: normal;
	margin-bottom: 0 !important;
	padding-bottom: 0;
}

.bg-gray {
	background-image: -moz-linear-gradient(center bottom, #BBBBBB 0%, #F0F0F0 100%);
	box-shadow: 0 1px 0 #B4B3B3;
}

}
.footer a {
	color: #78828D
}

.footer-bottom {
  margin-top: 2em;
	border-top: 1px solid #DDDDDD;
	padding-top: 20px;
	padding-bottom: 10px;
}
.footer-bottom p.pull-left {
	padding-top: 6px;
  font-size: 0.75em
}


</style>

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
					<ul class="navbar-nav ml-auto">
						<%-- UserController의 loginForm.jsp로 이동 --%>
						<li class="nav-item"><a class="nav-link" href="/auth/loginForm">로그인</a></li>
						<%-- UserController의 joinForm.jsp로 이동 --%>
						<li class="nav-item"><a class="nav-link" href="/auth/joinForm">회원가입</a></li>
					</ul>
					
				</c:when>

				<%-- principal이 존재할때 --%>
				<c:otherwise>
					<ul class="navbar-nav ml-auto">
						<li class="nav-item dropdown">
							<a class="nav-link dropdown-toggle" id="navbardrop" href="#" data-toggle="dropdown">${principal.user.username }님 안녕하세요!</a>
							<div class="dropdown-menu">
								<a class="dropdown-item" href="/user/updateForm">회원수정</a>
								<a class="dropdown-item" href="/board/list">QnA</a>
								<hr class="dropdown-divider">
								<a class="dropdown-item" href="/logout">로그아웃</a>
							</div>
						</li>
					</ul>
				</c:otherwise>

			</c:choose>

		</div>

	</nav>
	<br>