<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.malcolm.oes.model.Question"%>
<%@ page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to question management!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_component_checkbox.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/question_manage.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/question_manage.js"></script>
    <script type="text/javascript">
        var currentOrder = "${currentOrder }";
        var staticUtl = '<%=PropertyUtil.getStaticUrl()%>';
        var pageSizeByEl = "${pageSize }";
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
    <div class="question_content_div">
      <div class="left">
        <ul>
          <li class="quesion_list_active"><a class="active" href="${pageContext.request.contextPath }/page/dashBoard/myoes">Question List</a></li>
          <li><a href="${pageContext.request.contextPath }/page/question/createQuestion">Create Question</a></li>
        </ul>
      </div>
      <div class="right">
        <!-- Search form start -->
        <form id="searchForm" action="${pageContext.request.contextPath }/page/dashBoard/myoes" method="post">
          <div class="search_hidden_div">
            <input id="hiddenCurrentOrder" name="currentOrder" type="text" value="${currentOrder }" />
            <input id="hiddenPage" name="page" type="text" value="${page }"/>
            <input id="hiddenPageSize" name="pageSize" type="text" value="${pageSize }"/>
          </div>
          <div class="search">
            <input value="${keyword }" maxlength="10" id="keyword" name="keyword" type="text" placeholder="Please input the keyword" />
            <a href="#"><img id="searchImg" class="search_icon" alt="search" src="<%=PropertyUtil.getStaticUrl()%>/img/ICN_Search_15x20.png"></a>
          </div>
        </form>
        <!-- Search form over -->
        <div class="question_list_content">
          <div class="question_top">
            <ul>
              <li class="question_id">ID<label id="orderArrow"></label></li>
              <li class="question_descripe">Description</li>
              <li class="question_edit">Edit</li>
              <li class="quesiton_operator">
                <div class="question_select checkbox">
                  <input type="checkBox" id="allCheckedCheckbox" />
                  <label class="checkbox_label" for="allCheckedCheckbox"></label>
                </div>
              </li>
            </ul>
          </div>
          <!-- Delete form start -->
          <form id="deleteForm" action="${pageContext.request.contextPath }/page/question/deleteQuestionByIds" method="post">
            <c:forEach var="question" begin="0" end="${questionList.size() }" varStatus="idx" items="${questionList }">
              <div class="question_list checkbox">
                  <ul>
                    <li class="question_index">${idx.index + 1}</li>
                    <li class="question_id">${question.questionId }</li>
                    <li class="question_descripe" title="${question.questionTitle }"><a href="${pageContext.request.contextPath }/page/question/detailQuestion/${question.id }">${question.questionTitle }</a></li>
                    <li class="question_edit">
                      <a href="${pageContext.request.contextPath }/page/question/editQuestion/${question.id }" ><img alt="edit" src="<%=PropertyUtil.getStaticUrl()%>/img/ICN_Edit_15x15.png"></a>
                    </li>
                    <li class="quesiton_operator">
                      <div class="question_select checkbox">
                        <input name="questionCheckbox" value="${question.id }" type="checkBox" id="custom_checkbox${idx.index }" />
                        <label class="checkbox_label" for="custom_checkbox${idx.index }"></label>
                      </div>
                    </li>
                  </ul>
                </div>
            </c:forEach>
          </form>
          <!-- Delete form over -->

          <!-- pagination start -->
          <div class="question_operator">
            <div class="pagination">
              <a href="#" id="${pagination.nowPage - 1 }"><img alt="left" src="<%=PropertyUtil.getStaticUrl()%>/img/BTN_PageLeft_20x15.png"></a>

              <!-- Judge the page start -->
              <c:if test="${pagination.nowPage - 2 >= 1 }">
                <a href="#" id="${pagination.nowPage - 2 }">${pagination.nowPage - 2 }</a>
              </c:if>
              <c:if test="${pagination.nowPage - 1 >= 1 }">
                <a href="#" id="${pagination.nowPage - 1 }">${pagination.nowPage - 1 }</a>
              </c:if>
              <c:if test="${pagination.nowPage <= pagination.pageCount }">
                <c:choose>
                  <c:when test="${page eq pagination.nowPage }">
                    <a class="active" href="#" id="${pagination.nowPage }">${pagination.nowPage }</a>
                  </c:when>
                  <c:otherwise>
                    <a href="#" id="${pagination.nowPage }">${pagination.nowPage }</a>
                  </c:otherwise>
                </c:choose>
              </c:if>
              <c:if test="${pagination.nowPage + 1 <= pagination.pageCount }">
                <a href="#" id="${pagination.nowPage + 1 }">${pagination.nowPage + 1 }</a>
              </c:if>
              <c:if test="${pagination.nowPage + 2 <= pagination.pageCount }">
                <a href="#" id="${pagination.nowPage + 2 }">${pagination.nowPage + 2 }</a>
              </c:if>
              <c:if test="${pagination.nowPage + 3 <= pagination.pageCount }">
                <a href="#" id="${pagination.nowPage + 3 }">${pagination.nowPage + 3 }</a>
              </c:if>
              <c:if test="${pagination.nowPage + 4 <= pagination.pageCount }">
                <a href="#">...</a>
                <a href="#" id="${pagination.pageCount }">${pagination.pageCount }</a>
              </c:if>

              <!-- Judge the page over -->
              <a href="#" id="${pagination.nowPage + 1 }"><img alt="right" src="<%=PropertyUtil.getStaticUrl()%>/img/BTN_PageRight_20x15.png"></a>
              <select name="pageSize" id="pageSize" >
                <option selected="selected" value="1">1</option>
                <c:forEach begin="5" end="20" varStatus="idx" step="5">
                  <c:choose>
                    <c:when test="${pageSize == idx.index}">
                      <option selected="selected" value="${idx.index }">${idx.index }</option>
                    </c:when>
                    <c:otherwise>
                      <option value="${idx.index }">${idx.index }</option>
                    </c:otherwise>
                  </c:choose>
                </c:forEach>
              </select>
              <label>Per page</label>
              <input id="inputPage" type="text" value="${pagination.nowPage }"/>
              <button id="goPage" type="button">Go</button>
            </div>
            <div class="question_delete">
              <button id="deleteBtn" type="button">Delete</button>
            </div>
          </div>
          <!-- pagination end -->

        </div>
      </div>
    </div>

    <div class="question_footer_div">
      <div class="question_footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
    </div>

    <div id="questionDialogDiv" class="question_dialog">
      <button id="closeBtn" class="close_btn"></button>
      <span>Are you sure delete the selected options?</span>
      <button id="yesBtn" class="yes_btn">Yes</button>
      <button id="noBtn" class="no_btn">No</button>
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