<%@page import="java.util.Date"%>
<%@ page import="com.malcolm.oes.util.PropertyUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="block" uri="/WEB-INF/block.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
  <html>
  <head>
    <meta charset="UTF-8">
    <title>Welcome to question management!</title>
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/reset.css">
    <link rel="stylesheet" href="<%=PropertyUtil.getStaticUrl()%>/style/css/exam_manage.css">
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/jquery/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/common.js"></script>
    <script type="text/javascript" src="<%=PropertyUtil.getStaticUrl()%>/style/js/exam_manage.js"></script>
    <script type="text/javascript">
        var pageSizeByEl = "${pageSize }";
        var currentOrder = "${currentOrder}";
        var page = "${pageContext.request.contextPath }";
        var orderParam = "${orderParam}";
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
    <div class="exam_content_div">
      <div class="left">
        <ul>
          <li class="exam_list_active"><a class="active" href="${pageContext.request.contextPath }/page/exam/manageExam">Exam List</a></li>
          <li><a href="${pageContext.request.contextPath }/page/exam/createExam">Create Exam</a></li>
        </ul>
      </div>
      <div class="right">
        <!-- Search form start -->
        <form id="searchForm" action="${pageContext.request.contextPath }/page/exam/manageExam" method="post">
          <div class="search_hidden_div">
            <input id="hiddenCurrentOrder" name="currentOrder" type="text" value="${currentOrder }" />
            <input id="hiddenPage" name="page" type="text" value="${page }"/>
            <input id="hiddenPageSize" name="pageSize" type="text" value="${pageSize }"/>
            <input id="hiddenOrderParam" name="orderParam" type="text" value="${orderParam }"/>
          </div>
          <div class="search">
            <input maxlength="10" id="keyword" name="keyword" value="${keyword }" type="text" placeholder="Please input the keyword"/>
            <a href="#"><img id="search" class="search_icon" alt="search" src="<%=PropertyUtil.getStaticUrl()%>/img/ICN_Search_15x20.png"></a>
          </div>
          <div class="searchByDate">
            <button class="button_date" id="dateButton" type="button">date</button>
            <input maxlength="20" max="2066-12-31" id="endDate" name="endDate" type="date" value="${endDate }"/>
            <span class="separate_line">——</span>
            <input maxlength="20" max="2066-12-31" id="startDate" name="startDate" type="date" value="${startDate }"/>
          </div>
        </form>
        <!-- Search form over -->
        <div class="question_list_content">
          <div class="exam_content">
            <ul>
              <li class="exam_index"></li>
              <li class="exam_id">ID<label id="id" class="orderArrow"></label></li>
              <li class="exam_name">Name<label id="exam_name" class="orderArrow"></label></li>
              <li class="exam_effective_time">Effective Time<label id="effective_time" class="orderArrow"></label></li>
              <li class="exam_duration">Duration(Mins)</li>
              <li class="exam_creator">Creator</li>
              <li class="exam_edit">Edit</li>
              <li class="exam_operator">
                <div class="exam_select checkbox">
                  <input id="allCheckedCheckbox" type="checkbox"/>
                  <label class="checkbox_label" for="allCheckedCheckbox"></label>
                </div>
              </li>
            </ul>
          </div>
          <!-- Delete form start -->
          <form id="deleteForm" action="${pageContext.request.contextPath }/page/exam/deleteExamByIds" method="post">
            <div class="exam_content checkbox">
              <c:forEach items="${examList }" var="examVo" varStatus="idx" begin="0" end="${examList.size() }">
                <ul>
                  <li class="exam_index">${idx.index + 1 }</li>
                  <li class="exam_id">${examVo.examId }</li>
                  <li class="exam_name" title="${examVo.examName }">${examVo.examName }</li>
                  <li class="exam_effective_time"><fmt:formatDate value="${examVo.effectiveTime }" pattern="yyyy-MM-dd HH:mm"/></li>
                  <li class="exam_duration">${examVo.examDuration }</li>
                  <li class="exam_creator">${examVo.userName }</li>
                  <li class="exam_edit"><a href="#" ><img alt="edit" src="<%=PropertyUtil.getStaticUrl()%>/img/ICN_Edit_15x15.png"></a></li>
                  <li class="exam_operator">
                    <div class="exam_select checkbox">
                      <input id="CheckedCheckbox${idx.index + 1 }" name="examIdCheckbox" type="checkbox" value="${examVo.id }"/>
                      <label class="checkbox_label" for="CheckedCheckbox${idx.index + 1 }" ></label>
                    </div>
                  </li>
                </ul> 
              </c:forEach>
            </div>
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
            <div class="delete_exam">
              <button id="deleteBtn" type="button">Delete</button>
            </div>
          </div>
          <!-- pagination end -->

        </div>
      </div>
    </div>

    <div class="exam_footer_div">
      <div class="exam_footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
    </div>

    <div id="examDeleteDialog" class="exam_dialog">
      <button id="closeBtn" class="close_btn"></button>
      <span>Are you sure delete the selected options?</span>
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