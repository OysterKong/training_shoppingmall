<%@page import="com.dto.MemberDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<!-- top부분 -->
<% 
	MemberDTO dto = (MemberDTO)session.getAttribute("login"); 

	if(dto != null){
		String username = dto.getUsername();
%>
<%= username %> 님 환영합니다.
<a href="LogoutServlet">로그아웃</a>
<a href="">mypage</a>
<a href="">장바구니</a>
<%
	}else{
%>


<a href="LoginUIServlet">로그인</a>
<a href="MemberUIServlet">회원가입</a> <!-- mvc 패턴지키기 -->
<a href="">장바구니</a>

<%
	}
%>
