<%@page import="com.malcolm.oes.util.StringUtil"%>
<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to create question!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_manage.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_create.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/question_create.js"></script>
    <script type="text/javascript">
        var page = "${pageContext.request.contextPath }";
    </script>
  </head>
  <body>

    <block:display name="topBlock"/>

    <div class="question_tab_div">
      <ul>
        <li class="question_management quesion_manage_active"><a href="${pageContext.request.contextPath }/page/dashBoard/myoes">Question Management</a></li>
        <li class="exam_management"><a href="${pageContext.request.contextPath }/page/exam/manageExam">Exam Management</a></li>
      </ul>
    </div>
    <div class="breadcrumb">
      <ul>
        <li><a href="${pageContext.request.contextPath }/page/dashBoard/myoes">Question Management</a> >&nbsp;</li>
        <li><a href="#">Question List </a> >&nbsp;</li>
        <li>${question.questionId }</li>
      </ul>
    </div>

    <!-- Save form start -->
    <form id="saveForm" action="${pageContext.request.contextPath }/page/question/saveQuestion" method="post">
      <div class="question_content_div">
        <ul>
          <li class="item_name">Question ID:</li>
          <li><input id="questionIdInput" class="question_id" name="questionId" type="text" value="${questionId }" readonly="readonly" disabled="disabled"/></li>
          <li id="questionIdMsg" class="error_msg">Question id's length should in 1~20.</li>
        </ul>
        <ul>
          <li class="item_name">Question:</li>
          <li><textarea id="questionTextarea" name="questionTitle"></textarea></li>
          <li id="questionMsg" class="error_msg">Question name's length should in 1~200.</li>
        </ul>
        <ul>
          <li class="item_name">Answer:</li>
          <li>
            <ul class="inner_ul">
              <li>
                <div class="radio">
                  <input id="customRadioA" name="answer" type="radio" value="A"/>
                  <label class="radio_label" for="customRadioA"></label>
                </div>
                <label>A</label>
                <input id="answerA" autocomplete="off" name="answerA" type="text" placeholder="Please input the answer." />
                <label id="answerAMsg" class="error_msg">Answer A's length should in 1~30.</label>
              </li>

              <li>
                <div class="radio">
                  <input id="customRadioB" name="answer" type="radio" value="B"/>
                  <label class="radio_label" for="customRadioB"></label>
                </div>
                <label>B</label>
                <input id="answerB" name="answerB" type="text" autocomplete="off" placeholder="Please input the answer." />
                <label id="answerBMsg" class="error_msg">Answer B's length should in 1~30.</label>
              </li>

              <li>
                <div class="radio">
                  <input id="customRadioC" name="answer" type="radio" value="C"/>
                  <label class="radio_label" for="customRadioC"></label>
                </div>
                <label>C</label>
                <input id="answerC" name="answerC" type="text" autocomplete="off" placeholder="Please input the answer." />
                <label id="answerCMsg" class="error_msg">Answer C's length should in 1~30.</label>
              </li>

              <li>
                <div class="radio">
                  <input id="customRadioD" name="answer" type="radio" value="D"/>
                  <label class="radio_label" for="customRadioD"></label>
                </div>
                <label>D</label>
                <input autocomplete="off" id="answerD" name="answerD" type="text" placeholder="Please input the answer." />
                <label id="answerDMsg" class="error_msg">Answer D's length should in 1~30.</label>
              </li>
            </ul>
          </li>
        </ul>
        <div class="btn_operate">
          <button id="saveBtn" type="button">Save</button>
          <button id="cancelBtn" type="button">Cancel</button>
        </div>
      </div>
    </form>
    <!-- Save form over -->

    <div class="question_footer_div">
      <div class="question_footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
    </div>
    <!-- Flash message start -->
    <c:if test="${QUESTION_SUCCESS_FLASH_MSG != null}">
      <div id="successFlashMsg" class="success_flash_msg">
        ${QUESTION_SUCCESS_FLASH_MSG }
        <script type="text/javascript">
            setTimeout(function() {
                document.getElementById("successFlashMsg").style.display = "none";
            }, 3000);
        </script>
        <%session.removeAttribute("QUESTION_SUCCESS_FLASH_MSG"); %>
      </div>
    </c:if>

    <c:if test="${QUESTION_FAILURE_FLASH_MSG != null}">
      <div id="errorFlashMsg" class="error_flash_msg">
        ${QUESTION_FAILURE_FLASH_MSG }
        <script type="text/javascript">
            setTimeout(function() {
                document.getElementById("errorFlashMsg").style.display = "none";
            }, 3000);
        </script>
        <%session.removeAttribute("QUESTION_FAILURE_FLASH_MSG"); %>
      </div>
    </c:if>

    <div id="tipFlashMsg" class="tip_flash_msg"></div>
    <!-- Flash message over -->
  </body>
</html>