<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.malcolm.oes.util.DateUtil"%>
<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to create question!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/exam_create.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/exam_manage.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/exam_create.js"></script>
    <script type="text/javascript">
        var page = "${pageContext.request.contextPath }";
    </script>
  </head>
  <body>

    <block:display name="topBlock"/>

    <div class="exam_tab_div question_tab_div">
      <ul>
        <li class="question_management"><a href="${pageContext.request.contextPath }/page/dashBoard/myoes">Question Management</a></li>
        <li class="exam_management manage_active"><a href="${pageContext.request.contextPath }/page/exam/manageExam">Exam Management</a></li>
      </ul>
    </div>
    <div class="breadcrumb">
      <ul>
        <li><a href="${pageContext.request.contextPath }/page/dashBoard/myoes">Exam Management</a> >&nbsp;</li>
        <li><a href="#">Exam List </a> >&nbsp;</li>
      </ul>
    </div>

    <!-- Save form start -->
    <form id="saveForm" action="${pageContext.request.contextPath }/page/exam/createExam" method="post">
      <input id="isDraft" class="is_draft" name="isDraft" value="0" type="text" />
      <div class="exam_content_div">
        <ul>
          <li class="item_name">Exam Name:<i>*</i></li>
          <li><input name="examName" id="examName" class="exam_id" type="text" maxlength="20"/></li>
          <li id="examNameMsg" class="error_msg">Exam name's length should in 1~20.</li>
        </ul>
        <ul>
          <li class="item_name">Description:</li>
          <li><textarea name="examDescription" id="examDescription" maxlength="200"></textarea></li>
          <li id="examDescriptionMsg" class="error_msg">Description's length should in 0~200.</li>
        </ul>
        <ul>
          <li class="item_name">Effective Time:<i>*</i></li>
          <li><input class="exam_date" min="<%=DateUtil.getRecentDay() %>" max="2066-12-31" name="examDate" id="examDate" type="date"></input></li>
          <li><input class="exam_time" name="examTime" id="examTime" type="time"></input></li>
          <li id="examDateMsg" class="error_msg">Effective time is required.</li>
        </ul>
        <ul>
          <li class="item_name">Duration:<i>*</i></li>
          <li>
            <select name="examDuration" id="examDuration" class="duration">
              <option value="120">120</option>
              <option value="60">60</option>
              <option value="30">30</option>
            </select>
            min
          </li>
          <li id="examDurationMsg" class="error_msg">Duration is required.</li>
        </ul>
        <ul class="question_setting">
          <li class="item_name">Question Setting:<i>*</i></li>
          <li>
            <span>Quetion Quantity:</span><input id="questionQuantity" name="questionQuantity" type="text" maxlength="3"/>
            <span class="right_span">Quetion Point:</span><input id="questionPoint" name="questionPoint" type="text" maxlength="2"/>
          </li>
          <li class="behind_setting">
            <span>Total Score:</span><input id="totalScore" name="totalScore" type="text" disabled="disabled" maxlength="4"/>
            <span class="right_span">Pass Criteria:</span>
            <input id="passCriteria" name="passCriteria" type="text" maxlength="5"/>
          </li>
          <li id="questionQuantityMsg" class="question_quantity_error_msg">Question quantity's length should in 1~3.</li>
          <li id="questionPointMsg" class="question_point_error_msg">Question point's length should in 1~2.</li>
          <li id="passCriteriaMsg" class="pass_criteria_error_msg">Question criteria's length should in 1~5.</li>
        </ul>
        <div class="btn_operate">
          <button id="saveExam" type="button">Save</button>
          <button id="cancel" type="button">Cancel</button>
        </div>
      </div>
    </form>
    <!-- Save form over -->

    <div class="exam_footer_div">
      <div class="exam_footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
    </div>

    <div id="examWarnDialog" class="exam_warn_dialog">
      <button id="closeBtn" class="close_btn"></button>
      <span>The question quantity is less than the input.<br/>
            The present question quantity is <label id="presentQuestionQuantity" class="present_question_quantity">123</label>.<br/>
            Do you want to save this exam for draft?</span>
      <button id="yesBtn" class="yes_btn">Yes</button>
      <button id="noBtn" class="no_btn">No</button>
    </div>

    <!-- Flash message start -->
    <c:if test="${EXAM_SUCCESS_FLASH_MSG != null}">
      <div id="successFlashMsg" class="success_flash_msg">
        ${EXAM_SUCCESS_FLASH_MSG }
        <script type="text/javascript">
            setTimeout(function() {
                document.getElementById("successFlashMsg").style.display = "none";
            }, 3000);
        </script>
        <%session.removeAttribute("EXAM_SUCCESS_FLASH_MSG"); %>
      </div>
    </c:if>

    <c:if test="${EXAM_FAILURE_FLASH_MSG != null}">
      <div id="errorFlashMsg" class="error_flash_msg">
        ${EXAM_FAILURE_FLASH_MSG }
        <script type="text/javascript">
            setTimeout(function() {
                document.getElementById("errorFlashMsg").style.display = "none";
            }, 3000);
        </script>
        <%session.removeAttribute("EXAM_FAILURE_FLASH_MSG"); %>
      </div>
    </c:if>

    <div id="tipFlashMsg" class="tip_flash_msg"></div>
    <!-- Flash message over -->
  </body>
</html>