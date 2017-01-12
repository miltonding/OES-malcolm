<%@page import="com.malcolm.oes.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to login!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/component.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
      function login() {
         var loginFormObj = document.getElementById("loginForm");
         var errorMsgObj = document.getElementById("error_msg");
         var userNameErrorIcon = document.getElementById("username_error_icon");
         var passwordErrorIcon = document.getElementById("password_error_icon");
         
         var userNameValue = document.getElementById("userName").value;
         var isSubmitForm = true;
         var errorMsg = "";
         if (!userNameValue) {
             errorMsg="user name is required";
             userNameErrorIcon.style.visibility = "visible";
             isSubmitForm = false;
         } else {
             userNameErrorIcon.style.visibility = "hidden";
             document.getElementById("error_msg").innerHTML="";
         }
         var passwordValue = document.getElementById("password").value;
         if (!passwordValue) {
             errorMsg="password is required";
             passwordErrorIcon.style.visibility = "visible";
             isSubmitForm = false;
         } else {
             passwordErrorIcon.style.visibility = "hidden";
             document.getElementById("error_msg").innerHTML="";
         }
         if (!passwordValue && !userNameValue) {
             errorMsg="username and password is required";
             userNameErrorIcon.style.visibility = "visible";
             passwordErrorIcon.style.visibility = "visible";
             isSubmitForm = false;
         } else {
             document.getElementById("error_msg").innerHTML="";
         }
         if (!isSubmitForm) {
             errorMsgObj.innerHTML = errorMsg;
             errorMsgObj.style.visibility = "visible";
         }
         var checkObj = $("#custom_checkbox");
         var isChecked = checkObj.is(":checked");
         if (isChecked) {
             storageData();
         }else{
             clearStorageData();
         }
         if (isSubmitForm) {
             loginFormObj.submit();
         }
      }
      function storageData() {
          var storage = window.localStorage;
          storage.setItem("username", $("#userName").val());
          storage.setItem("password", $("#password").val());
      }
      $(function(){
          var storage = window.localStorage;
          var username = storage.getItem("username");
          var password = storage.getItem("password");
          if (username && password) {
              $("#userName").val(username);
              $("#password").val(password);
              $("#custom_checkbox").attr("checked","checked");
              login();
          }
      });
      function clearStorageData() {
          var storage = window.localStorage;
          storage.removeItem("username");
          storage.removeItem("password");
      }
    </script>
  </head>
  <body>
    <%
        String tipMessage = (String)request.getAttribute(Constants.TIP_MESSAGE);
        String visibility = "hidden"; 
        if(tipMessage != null && !tipMessage.equals("")) {
              visibility = "visible";
        }
    %>
    <div class="content_div">
      <div class="top_div">
        <div class="left_login_content">
          <div class="oes_pic"><img src="static/style/img/LOGO_Web_Login.png" /></div>
          <div class="oes_title">Online Exam Web System</div>
        </div>
        <div class="right_login_content">
          <div class="welcome_content">Welcome to login!</div>
          <form action="login.action" method="POST" id="loginForm">
            <div class="login_content">
              <div class="login_errormsg" id="error_msg" style="visibility: <%=visibility%>"><%=tipMessage %></div>
              <div class="user_input">
                <label class="user_icon_bg" ></label>
                <input class="icon_user" type="text" name="userName" id="userName" value="${userName }" placeholder="Username" />
                <span id="username_error_icon"><img src="static/style/img/login_wrong.png" /></span>
              </div>
              <div class="pwd_input">
                <label class="password_icon_bg"></label>
                <input class="icon_pwd" type="password" name="password" id="password" value="${password }" placeholder="Password" />
                <span id="password_error_icon"><img src="static/style/img/login_wrong.png" /></span>
              </div>
              <div class="login_button"><button type="button" value="Login" onclick="login()" >Login</button></div>
              <div>
                <div class="rember_me checkbox">
                  <input type="checkBox" id="custom_checkbox" />
                  <label class="checkbox_label" for="custom_checkbox">Rember me</label>
                </div>
                <div class="forget_pwd"><a href="#">Forget password</a></div>
              </div>
            </div>
          </form>
        </div>
      </div>
      <div class="footer_div">
        <div class="footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
      </div>
    </div>
  </body>
</html>