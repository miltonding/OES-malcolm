<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/common.js"></script>
    <script type="text/javascript">
        $(function(){
            $("input[name='answer']").each(function() {
                $(this).click(function() {
                    clean();
                    $(this).parent().siblings("input").css({"background-color":"#D2DAE3"});
                });
            });
        });
        function clean() {
            $("#answerA").css({"background-color":"#FFFFFF"});
            $("#answerB").css({"background-color":"#FFFFFF"});
            $("#answerC").css({"background-color":"#FFFFFF"});
            $("#answerD").css({"background-color":"#FFFFFF"});
        }
        function saveQuestion() {
            var saveFormObj = document.getElementById("saveForm");
            var isSubmit = true;
            // Verify the question id.
            var questionIdValue = $("#questionIdInput");
            if(questionIdValue.val().trim().length < 1 || questionIdValue.val().trim().length > 20) {
                $("#questionIdMsg").css({"display" : "inline-block" });
                $("#questionIdInput").css({"border" : "dashed 1px red"});
                isSubmit = false;
            }else{
                $("#questionIdInput").css({"border" : "solid 1px #2E4358"});
                $("#questionIdMsg").css({"display" : "none" });
                questionIdValue.val(questionIdValue.val().trim());
            }
            // Verify the question.
            var questionTextareaValue = $("#questionTextarea");
            if(questionTextareaValue.val().trim().length < 1 || questionTextareaValue.val().trim().length > 200) {
                $("#questionMsg").css({"display" : "inline-block" });
                $("#questionTextarea").css({"border" : "dashed 1px red"});
                isSubmit = false;
            }else{
                $("#questionTextarea").css({"border" : "solid 1px #2E4358"});
                $("#questionMsg").css({"display" : "none"});
                questionTextareaValue.val(questionTextareaValue.val().trim());
            }
            // Verify the answerA
            var answerAValue = $("#answerA");
            if(answerAValue.val().trim().length < 1 || answerAValue.val().trim().length > 30) {
                $("#answerAMsg").css({"display" : "inline-block" });
                $("#answerA").css({"border" : "dashed 1px red"});
                isSubmit = false;
            }else{
                $("#answerA").css({"border" : "solid 1px #2E4358"});
                $("#answerAMsg").css({"display" : "none"});
                answerAValue.val(answerAValue.val().trim());
            }
            // Verify the answerB.
            var answerBValue = $("#answerB");
            if(answerBValue.val().trim().length < 1 || answerBValue.val().trim().length > 30) {
                $("#answerBMsg").css({"display" : "inline-block" });
                $("#answerB").css({"border" : "dashed 1px red"});
                isSubmit = false;
            }else{
                $("#answerB").css({"border" : "solid 1px #2E4358"});
                $("#answerBMsg").css({"display" : "none"});
                answerBValue.val(answerBValue.val().trim());
            }
            // Verify the answerC.
            var answerCValue = $("#answerC");
            if(answerCValue.val().trim().length < 1 || answerCValue.val().trim().length > 30) {
                $("#answerCMsg").css({"display" : "inline-block" });
                $("#answerC").css({"border" : "dashed 1px red"});
                isSubmit = false;
            }else{
                $("#answerC").css({"border" : "solid 1px #2E4358"});
                $("#answerCMsg").css({"display" : "none"});
                answerCValue.val(answerCValue.val().trim());
            }
            // Verify the answerD.
            var answerDValue = $("#answerD");
            if(answerDValue.val().trim().length < 1 || answerDValue.val().trim().length > 30) {
                $("#answerDMsg").css({"display" : "inline-block" });
                $("#answerD").css({"border" : "dashed 1px red"});
                isSubmit = false;
            }else{
                $("#answerD").css({"border" : "solid 1px #2E4358"});
                $("#answerDMsg").css({"display" : "none"});
                answerDValue.val(answerDValue.val().trim());
            }
            if (isSubmit) {
                saveFormObj.submit();
            }
        }
        function cancel() {
            var page = "${pageContext.request.contextPath }";
            var id = ${question.id};
            location.href= page + '/myoes.action';
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
    <!-- Form start -->
    <form id="saveForm" action="${pageContext.request.contextPath }/editQuestionToSave.action" method="post">
      <input name="oldId" hidden="hidden" value="${question.id }" >
      <div class="question_content_div">
        <ul>
          <li class="item_name">Question ID:</li>
          <li>
            <input name="questionId" id="questionIdInput" class="question_id" type="text" value="${question.questionId }" />
          </li>
          <li id="questionIdMsg" class="error_msg">Question id's length should in 1~10.</li>
        </ul>
        <ul>
          <li class="item_name">Question:</li>
          <li>
            <textarea name="questionTitle" id="questionTextarea" >${question.questionTitle }</textarea>
          </li>
          <li id="questionMsg" class="error_msg">Question name's length should in 1~100.</li>
        </ul>
        <ul>
          <li class="item_name">Answer:</li>
            <ul class="inner_ul">
              <li>
                <div class="radio">
                  <c:choose>
                    <c:when test="${question.answer eq 'A' }">
                      <input checked="checked" name="answer" type="radio" value="A" id="custom_radio1">
                    </c:when>
                    <c:otherwise>
                      <input name="answer" type="radio" value="A" id="custom_radio1">
                    </c:otherwise>
                  </c:choose>
                  <label class="radio_label" for="custom_radio1"></label>
                </div>
                <label>A</label>
                <c:choose>
                  <c:when test="${question.answer eq 'A' }">
                    <input id="answerA" name="answerA" class="active" type="text" value="${question.answerA }" />
                  </c:when>
                  <c:otherwise>
                    <input id="answerA" name="answerA" type="text" value="${question.answerA }" />
                  </c:otherwise>
                </c:choose>
                <label id="answerAMsg" class="error_msg">Answer A's length should in 1~20</label>
              </li>
              
              <li>
                <div class="radio">
                  <c:choose>
                    <c:when test="${question.answer eq 'B' }">
                      <input checked="checked" name="answer" type="radio" value="B" id="custom_radio2">
                    </c:when>
                    <c:otherwise>
                      <input name="answer" type="radio" value="B" id="custom_radio2">
                    </c:otherwise>
                  </c:choose>
                  <label class="radio_label" for="custom_radio2"></label>
                </div>
                <label>B</label>
                  <c:choose>
                    <c:when test="${question.answer eq 'B'}">
                      <input id="answerB" name="answerB" class="active" type="text" value="${question.answerB }" />
                    </c:when>
                    <c:otherwise>
                      <input id="answerB" name="answerB" type="text" value="${question.answerB }" />
                    </c:otherwise>
                  </c:choose>
                  <label id="answerBMsg" class="error_msg">Answer B's length should in 1~20</label>
              </li>
              
              <li>
                <div class="radio">
                  <c:choose>
                    <c:when test="${question.answer eq 'C'}">
                      <input checked="checked" name="answer" type="radio" value="C" id="custom_radio3">
                    </c:when>
                    <c:otherwise>
                      <input name="answer" type="radio" value="C" id="custom_radio3">
                    </c:otherwise>
                  </c:choose>
                  <label class="radio_label" for="custom_radio3"></label>
                </div>
                <label>C</label>
                <c:choose>
                  <c:when test="${question.answer eq 'C'}">
                    <input id="answerC" name="answerC" class="active" type="text" value="${question.answerC }" />
                  </c:when>
                  <c:otherwise>
                    <input id="answerC" name="answerC" type="text" value="${question.answerC }" />
                  </c:otherwise>
                </c:choose>
                <label id="answerCMsg" class="error_msg">Answer C's length should in 1~20</label>
              </li>
              
              <li>
                <div class="radio">
                  <c:choose>
                    <c:when test="${question.answer eq 'D'}">
                      <input checked="checked" name="answer" type="radio" value="D" id="custom_radio4">
                    </c:when>
                    <c:otherwise>
                      <input name="answer" type="radio" value="D" id="custom_radio4">
                    </c:otherwise>
                  </c:choose>
                  <label class="radio_label" for="custom_radio4"></label>
                </div>
                <label>D</label>
                <c:choose>
                  <c:when test="${question.answer eq 'D'}">
                    <input id="answerD" name="answerD" class="active" type="text" value="${question.answerD }" />
                  </c:when>
                  <c:otherwise>
                    <input id="answerD" name="answerD" type="text" value="${question.answerD }" />
                  </c:otherwise>
                </c:choose>
                <label id="answerDMsg" class="error_msg">Answer D's length should in 1~20</label>
              </li>
            </ul>
        </ul>
        <div class="btn_operate">
          <button type="button" onclick="saveQuestion()">Save</button>
          <button type="button" onclick="cancel()">Cancel</button>
        </div>
      </div>
    </form>
    
  </body>
</html>