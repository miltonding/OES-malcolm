<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to edit question!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_manage.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_create.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/question_edit.js"></script>
    <script type="text/javascript">
        var questionAnswer = "${question.answer }";
        var page = "${pageContext.request.contextPath}";
        var oldQuestionId = "${question.questionId }";
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
        <li class="first"><a href="${pageContext.request.contextPath }/page/dashBoard/myoes">Question Management</a> >&nbsp;</li>
        <li class="second"><a href="#">Question List </a> >&nbsp;</li>
        <li class="question_id">${question.questionId }</li>
      </ul>
    </div>
    <!-- Save form start -->
    <form id="saveForm" action="${pageContext.request.contextPath }/page/question/editQuestionToSave" method="post">
      <input name="oldId" value="${question.id }" hidden="hidden">
      <div class="question_content_div">
        <ul>
          <li class="item_name">Question ID:</li>
          <li><input id="questionIdInput" name="questionId" class="question_id" type="text" value="${question.questionId }" disabled="disabled"/></li>
          <li id="questionIdMsg" class="error_msg">Question id's length should in 1~10.</li>
        </ul>
        <ul>
          <li class="item_name">Question:</li>
          <li><textarea id="questionTextarea" name="questionTitle">${question.questionTitle }</textarea></li>
          <li id="questionMsg" class="error_msg">Question name's length should in 1~100.</li>
        </ul>
        <ul>
          <li class="item_name">Answer:</li>
            <li>
              <ul class="inner_ul">
                <li>
                  <div class="radio">
                    <c:choose>
                      <c:when test="${question.answer eq 'A' }">
                        <input id="customRadioA" name="answer" type="radio" value="A" checked="checked"/>
                      </c:when>
                      <c:otherwise>
                        <input id="customRadioA" name="answer" type="radio" value="A"/>
                      </c:otherwise>
                    </c:choose>
                    <label class="radio_label" for="customRadioA"></label>
                  </div>
                  <label>A</label>
                  <c:choose>
                    <c:when test="${question.answer eq 'A' }">
                      <input id="answerA" class="active" name="answerA" type="text" value="${question.answerA }" autocomplete="off"/>
                    </c:when>
                    <c:otherwise>
                      <input id="answerA" name="answerA" type="text" value="${question.answerA }" autocomplete="off"/>
                    </c:otherwise>
                  </c:choose>
                  <label id="answerAMsg" class="error_msg">Answer A's length should in 1~30</label>
                </li>
                
                <li>
                  <div class="radio">
                    <c:choose>
                      <c:when test="${question.answer eq 'B' }">
                        <input id="customRadioB" name="answer" type="radio" value="B" checked="checked"/>
                      </c:when>
                      <c:otherwise>
                        <input id="customRadioB" name="answer" type="radio" value="B"/>
                      </c:otherwise>
                    </c:choose>
                    <label class="radio_label" for="customRadioB"></label>
                  </div>
                  <label>B</label>
                    <c:choose>
                      <c:when test="${question.answer eq 'B'}">
                        <input id="answerB" name="answerB" class="active" type="text" value="${question.answerB }" autocomplete="off"/>
                      </c:when>
                      <c:otherwise>
                        <input id="answerB" name="answerB" type="text" value="${question.answerB }"  autocomplete="off"/>
                      </c:otherwise>
                    </c:choose>
                    <label id="answerBMsg" class="error_msg">Answer B's length should in 1~30</label>
                </li>
                
                <li>
                  <div class="radio">
                    <c:choose>
                      <c:when test="${question.answer eq 'C'}">
                        <input id="customRadioC" checked="checked" name="answer" type="radio" value="C"/>
                      </c:when>
                      <c:otherwise>
                        <input id="customRadioC" name="answer" type="radio" value="C"/>
                      </c:otherwise>
                    </c:choose>
                    <label class="radio_label" for="customRadioC"></label>
                  </div>
                  <label>C</label>
                  <c:choose>
                    <c:when test="${question.answer eq 'C'}">
                      <input id="answerC" name="answerC" class="active" type="text" value="${question.answerC }" autocomplete="off"/>
                    </c:when>
                    <c:otherwise>
                      <input id="answerC" name="answerC" type="text" value="${question.answerC }" autocomplete="off"/>
                    </c:otherwise>
                  </c:choose>
                  <label id="answerCMsg" class="error_msg">Answer C's length should in 1~30</label>
                </li>
                
                <li>
                  <div class="radio">
                    <c:choose>
                      <c:when test="${question.answer eq 'D'}">
                        <input id="customRadioD" name="answer" type="radio" value="D" checked="checked"/>
                      </c:when>
                      <c:otherwise>
                        <input id="customRadioD" name="answer" type="radio" value="D"/>
                      </c:otherwise>
                    </c:choose>
                    <label class="radio_label" for="customRadioD"></label>
                  </div>
                  <label>D</label>
                  <c:choose>
                    <c:when test="${question.answer eq 'D'}">
                      <input id="answerD" name="answerD" class="active" type="text" value="${question.answerD }" autocomplete="off"/>
                    </c:when>
                    <c:otherwise>
                      <input id="answerD" name="answerD" type="text" value="${question.answerD }" autocomplete="off"/>
                    </c:otherwise>
                  </c:choose>
                  <label id="answerDMsg" class="error_msg">Answer D's length should in 1~30</label>
                </li>
              </ul>
            </li>
        </ul>
        <div class="btn_operate">
          <button id="saveQuestionBtn" type="button">Save</button>
          <button id="cancleBtn" type="button">Cancel</button>
        </div>
      </div>
    </form>
    <!-- Save form over -->

    <div id="tipFlashMsg" class="tip_flash_msg"></div>
  </body>
</html>