<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to question's detail!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_component_checkbox.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_manage.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_create.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_detail.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/common.js"></script>
    <script type="text/javascript">
        var page = "${pageContext.request.contextPath}";
        var id = ${question.id};
        $(function() {
            $("#editQuestionBtn").click(function() {
                location.href = page + '/page/question/editQuestion/' + id;
            });
            $("#deleteQuestionBtn").click(function() {
                location.href = page + '/page/question/deleteDetailQuestion/' + id;
            });
        });
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
        <li class="active">${question.questionId }</li>
      </ul>
    </div>
    <div class="question_content_div">
      <ul>
        <li class="item_name">Question ID:</li>
        <li><input class="question_id" type="text" value="${question.questionId }"/></li>
      </ul>
      <ul>
        <li class="item_name">Question:</li>
        <li><textarea>${question.questionTitle }</textarea></li>
      </ul>
      <ul>
        <li class="item_name">Answer:</li>
        <li>
          <ul class="inner_ul">
            <li class="first">
              <label>A</label>
              <c:choose>
                <c:when test="${question.answer eq 'A' }">
                  <input class="active" type="text" value="${question.answerA }" readonly="readonly"/>
                </c:when>
                <c:otherwise>
                  <input type="text" value="${question.answerA }" readonly="readonly"/>
                </c:otherwise>
              </c:choose>
            </li>
            <li>
              <label>B</label>
              <c:choose>
                <c:when test="${question.answer eq 'B' }">
                  <input class="active" type="text" value="${question.answerB }" readonly="readonly"/>
                </c:when>
                <c:otherwise>
                  <input type="text" value="${question.answerB }" readonly="readonly"/>
                </c:otherwise>
              </c:choose>
            </li>
            <li>
              <label>C</label>
              <c:choose>
                <c:when test="${question.answer eq 'C'}">
                  <input class="active" type="text" value="${question.answerC }" readonly="readonly"/>
                </c:when>
                <c:otherwise>
                  <input type="text" value="${question.answerC }" readonly="readonly"/>
                </c:otherwise>
              </c:choose>
            </li>
            <li>
              <label>D</label>
              <c:choose>
                <c:when test="${question.answer eq 'D'}">
                  <input class="question_option active" type="text" value="${question.answerD }" readonly="readonly"/>
                </c:when>
                <c:otherwise>
                  <input type="text" value="${question.answerD }" readonly="readonly"/>
                </c:otherwise>
              </c:choose>
            </li>
          </ul>
        </li>
      </ul>
      <div class="btn_operate">
        <button id="editQuestionBtn" type="button">Edit</button>
        <button id="deleteQuestionBtn" type="button">Delete</button>
      </div>
    </div>

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
    <!-- Flash message over -->
  </body>
</html>