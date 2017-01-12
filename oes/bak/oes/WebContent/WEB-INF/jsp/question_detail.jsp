<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to login!</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/reset.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/question_component_checkbox.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/style.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/question_management.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/create_question.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath }/static/style/css/question_detail.css">
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/common.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript">
        var page = "${pageContext.request.contextPath }";
        var id = ${question.id};
        function DeleteThisQuestion() {
            location.href= page + '/questionDetailDelete.action?id='+id;
        }
        function editQuestion() {
            location.href= page + '/editQuestion.action?id='+id;
        }
    </script>
  </head>
  <body style="background-color: #D2DAE3">
    <nav class="question_nav">
      <img class="web_logo" src="${pageContext.request.contextPath }/static/style/img/LOGO_Web.png" />
      <span class="web_title">Online Exam Web System</span>
      <a href="#" class="language">中文</a>
      <a id="logout" href="#" class="logout">Logout</a>
      <a href="#" class="username">${USER.userName}</a>
      <img class="user_icon" src="${pageContext.request.contextPath }/static/style/img/ICN_Web_PersonalInformation.png" />
    </nav>
    <div class="question_tab_div">
      <ul>
        <li class="question_management quesion_manage_active"><a href="myoes.action">Question Management</a></li>
        <li class="exam_management"><a href="#">Exam Management</a></li>
      </ul>
    </div>
    <div class="breadcrumb">
      <ul>
        <li class="first"><a href="myoes.action">Question Management</a> >&nbsp;</li>
        <li class="second"><a href="#">Question List </a> >&nbsp;</li>
        <li class="question_id">${question.questionId }</li>
      </ul>
    </div>
    <div class="question_content_div">
      <ul>
        <li class="item_name">Question ID:</li>
        <li>
          <input class="question_id" type="text" value="${question.questionId }" />
        </li>
      </ul>
      <ul>
        <li class="item_name">Question:</li>
        <li>
          <textarea>${question.questionTitle }</textarea>
        </li>
      </ul>
      <ul>
        <li class="item_name">Answer:</li>
          <ul class="inner_ul">
            <li class="first">
              <label>A</label>
              <c:choose>
                <c:when test="${question.answer eq 'A' }">
                  <input readonly="readonly" class="active" type="text" value="${question.answerA }" />
                </c:when>
                <c:otherwise>
                  <input readonly="readonly" type="text" value="${question.answerA }" />
                </c:otherwise>
              </c:choose>
            </li>
            <li>
              <label>B</label>
              <c:choose>
                <c:when test="${question.answer eq 'B'}">
                  <input readonly="readonly" class="active" type="text" value="${question.answerB }" />
                </c:when>
                <c:otherwise>
                  <input readonly="readonly" type="text" value="${question.answerB }" />
                </c:otherwise>
              </c:choose>
            </li>
            <li>
              <label>C</label>
              <c:choose>
                <c:when test="${question.answer eq 'C'}">
                  <input readonly="readonly" class="active" type="text" value="${question.answerC }" />
                </c:when>
                <c:otherwise>
                  <input readonly="readonly" type="text" value="${question.answerC }" />
                </c:otherwise>
              </c:choose>
            </li>
            <li>
              <label>D</label>
              <c:choose>
                <c:when test="${question.answer eq 'D'}">
                  <input class="question_option active" readonly="readonly" type="text" value="${question.answerD }" />
                </c:when>
                <c:otherwise>
                  <input readonly="readonly" type="text" value="${question.answerD }" />
                </c:otherwise>
              </c:choose>
            </li>
          </ul>
      </ul>
      <div class="btn_operate">
        <button onclick="editQuestion()" type="button">Edit</button>
        <button onclick="DeleteThisQuestion()" type="button">Delete</button>
      </div>
    </div>
    
    <div class="question_footer_div">
      <div class="question_footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
    </div>
    
    <!-- flash message开始 -->
    <%
        String flash_msg = (String)session.getAttribute("update_question_flash_msg");
        flash_msg = flash_msg == null ? "" : flash_msg;
        String isSuccessMsg = "";
        if(flash_msg.equals("")) {
            isSuccessMsg = "style='display:none'";
        }
    %>
    <div id="update_question_flash_msg" class="flash_msg" <%=isSuccessMsg %> >
    <%
        out.write(flash_msg);
        session.removeAttribute("update_question_flash_msg");
        if(!flash_msg.equals("")) {
            %>
            <script type="text/javascript">
                setTimeout(function() {
                    document.getElementById("update_question_flash_msg").style.display = "none";
                }, 3000);
            </script>
            <%
        }
    %>
    </div>
  </body>
</html>