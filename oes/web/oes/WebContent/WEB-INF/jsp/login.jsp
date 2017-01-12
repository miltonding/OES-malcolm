<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page import="com.malcolm.oes.Constants"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to login!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/login.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/component.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/login.js"></script>
    <script type="text/javascript">
        var tipMessage = "${TIP_MESSAGE}";
    </script>
  </head>
  <body>
    <%
        String tipMessage = (String) request.getAttribute(Constants.TIP_MESSAGE);
        String visibility = "hidden"; 
        if (tipMessage != null && !tipMessage.equals("")) {
            visibility = "visible";
        }
    %>
    <div class="content_div">
      <div class="top_div">
        <div class="left_login_content">
          <div class="oes_pic"><img src="<%=PropertyUtil.getStaticUrl()%>/style/img/LOGO_Web_Login.png" /></div>
          <div class="oes_title">Online Exam Web System</div>
        </div>
        <div class="right_login_content">
          <div class="welcome_content">Welcome to login!</div>
          <!-- Login form start -->
          <form id="loginForm" action="login" method="POST">
            <input id="queryString" name="queryString" type="hidden"/>
            <input id="go" name="go" type="hidden" value="${go }"/>
            <div class="login_content">
              <div id="errorMsg" class="login_error_msg" style="visibility: <%=visibility%>"><%=tipMessage %></div>
              <div class="user_input">
                <label class="user_icon_bg" ></label>
                <input id="userName" class="icon_user" name="userName" type="text" value="${userName }" placeholder="Username" />
                <span id="userNameErrorIcon"><img src="<%=PropertyUtil.getStaticUrl()%>/style/img/login_wrong.png" /></span>
              </div>
              <div class="pwd_input">
                <label class="password_icon_bg"></label>
                <input id="password" class="icon_pwd" name="password" type="password" value="${password }" placeholder="Password" />
                <span id="passwordErrorIcon"><img src="<%=PropertyUtil.getStaticUrl()%>/style/img/login_wrong.png" /></span>
              </div>
              <div class="login_button"><button id="loginBtn" type="button" value="Login" >Login</button></div>
              <div>
                <div class="rember_me checkbox">
                  <input id="customCheckbox" type="checkBox" />
                  <label class="checkbox_label" for="customCheckbox">Remember me</label>
                </div>
                <div class="forget_pwd"><a href="#">Forget password</a></div>
              </div>
            </div>
          </form>
          <!-- Login form over -->
        </div>
      </div>
      <div class="footer_div">
        <div class="footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
      </div>
    </div>
  </body>
</html>