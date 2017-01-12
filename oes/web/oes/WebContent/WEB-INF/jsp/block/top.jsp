<%@page import="com.malcolm.oes.Constants"%>
<%@page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<nav class="top_nav">
  <img class="web_logo" src="<%=PropertyUtil.getStaticUrl()%>/style/img/LOGO_Web.png" />
  <span class="web_title">Online Exam Web System</span>
  <a class="language" href="#">中文</a>
  <a id="logout" class="logout" href="${pageContext.request.contextPath}/page/user/logout">Logout</a>
  <a class="username" href="#"><%=request.getAttribute(Constants.USER_NAME) %></a>
  <img class="user_icon" src="<%=PropertyUtil.getStaticUrl()%>/style/img/ICN_Web_PersonalInformation.png" />
</nav>
