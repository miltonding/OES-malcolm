<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.malcolm.oes.model.Question"%>
<%@ page import="java.util.List"%>
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
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/jquery-1.10.2.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath }/static/style/js/common.js"></script>
    <script type="text/javascript">
        function closeDialog() {
            var closeObj = document.getElementById("question_dialog_div");
            closeObj.style.cssText = "display:none;"
        }
        function openCloseDialog() {
            var isChecked = verifyChecked();
            if (!isChecked) {
                alert("You must check one question!");
                return;
            }
            var closeObj = document.getElementById("question_dialog_div");
            closeObj.style.cssText = "display:block;"
            return;
        }
        function verifyChecked() {
            var checkBoxsObj = document.getElementsByName("questionCheckbox");
            var isChecked = false;;
            for (var i = 0; i < checkBoxsObj.length; i++) {
                if(checkBoxsObj[i].checked){
                    isChecked = true;
                }
            }
            return isChecked;
        }
        function search() {
            var searchObj = document.getElementById("searchForm");
            var action = searchObj.getAttribute("action");
            /* var keyword = document.getElementById("keyword").value;
            if (!keyword) {
                return;
            } */
            //searchObj.setAttribute("action", action + "?keyword=" + keyword);
            //console.log(searchObj.getAttribute("action"));
            searchObj.submit();
        }
        function deleteQuestions() {
            var deleteFormObj = document.getElementById("deleteForm");
            deleteFormObj.submit();
        }
        function allChecked() {
            var checkBoxsObj = document.getElementsByName("questionCheckbox");
            var count = getCheckedCount();
            if (count == checkBoxsObj.length) {
                for (var i = 0; i < checkBoxsObj.length; i++) {
                    checkBoxsObj[i].checked = false;
                }
            }else{
                for (var i = 0; i < checkBoxsObj.length; i++) {
                    checkBoxsObj[i].checked = true;
                }
            }
            verifyIsAllChecked();
        }
        function getCheckedCount() {
            var checkBoxsObj = document.getElementsByName("questionCheckbox");
            var count = 0;
            for (var i = 0; i < checkBoxsObj.length; i++) {
                if(checkBoxsObj[i].checked){
                    count ++;
                }
            }
            return count;
        }
        function verifyIsAllChecked() {
            var checkBoxsObj = document.getElementsByName("questionCheckbox");
            var allCheckedObj = document.getElementById("custom_checkbox-0");
            var count = 0;
            for (var i = 0; i < checkBoxsObj.length; i++) {
                if(checkBoxsObj[i].checked){
                    count ++;
                }
            }
            if (count == checkBoxsObj.length) {
                allCheckedObj.checked = true;
            }else{
                allCheckedObj.checked = false;
            }
        }
        $(function() {
            $("#pageSize").change(function() {
                var pageSize = $(this).val();
                var projectName = "${pageContext.request.contextPath}";
                console.log(projectName);
                location.href = "myoes.action?pageSize="+pageSize;
            });
            $("#goPage").click(function() {
                var pageSize = $("#pageSize").val();
                var page = $("#inputPage").val();
                if (!page) {
                    page = "1";
                }
                console.log("myoes.action?pageSize="+pageSize+"&page="+page);
                location.href = "myoes.action?pageSize="+pageSize+"&page="+page;
            });
        });
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
    <div class="question_content_div">
      <div class="left">
        <ul>
          <li class="quesion_list_active"><a class="active" href="${pageContext.request.contextPath }/myoes.action">Question List</a></li>
          <li><a href="createQuestion.action">Create Question</a></li>
        </ul>
      </div>
      <div class="right">
        <form id="searchForm" action="${pageContext.request.contextPath }/searchQuestion.action" method="post">
          <div class="search">
            <input value="${keyword }" required="required" id="keyword" name="keyword" type="text" placeholder="Please input the keyword" />
            <a type="submit" href="#"><img onclick="search()" class="search_icon" alt="search" src="static/img/ICN_Search_15x20.png"></a>
          </div>
        </form>
        <div class="question_list_content">
          <div class="question_top">
            <ul>
              <li class="question_id">ID</li>
              <li class="question_descripe">Description</li>
              <li class="question_edit">Edit</li>
              <li class="quesiton_operator">
                <div class="question_select checkbox">
                  <input onclick="allChecked()" type="checkBox" id="custom_checkbox-0" />
                  <label class="checkbox_label" for="custom_checkbox-0"></label>
                </div>
              </li>
            </ul>
          </div>
          <form id="deleteForm" action="${pageContext.request.contextPath }/deleteQuestionByIds.action" method="post">
            <c:forEach var="question" begin="0" end="${questionList.size() }" varStatus="idx" items="${questionList }">
              <div class="question_list checkbox">
                  <ul>
                    <li class="question_index">${idx.index + 1}</li>
                    <li class="question_id">${question.questionId }</li>
                    <c:choose>
                      <c:when test="${question.questionTitle.length() > 30  }">
                        <li class="question_descripe" title="${question.questionTitle }"><a href="questionDetail.action?id=${question.id }">${question.questionTitle.substring(0,30) }...</a></li>
                      </c:when>
                      <c:otherwise>
                        <li class="question_descripe" title="${question.questionTitle }"><a href="questionDetail.action?id=${question.id }">${question.questionTitle }</a></li>
                      </c:otherwise>
                    </c:choose>
                    <li class="question_edit">
                      <a href="editQuestion.action?id=${question.id }" ><img alt="edit" src="static/img/ICN_Edit_15x15.png"></a>
                    </li>
                    <li class="quesiton_operator">
                      <div class="question_select checkbox">
                        <input onclick="verifyIsAllChecked()" name="questionCheckbox" value="${question.id }" type="checkBox" id="custom_checkbox${idx.index }" />
                        <label class="checkbox_label" for="custom_checkbox${idx.index }"></label>
                      </div>
                    </li>
                  </ul>
                </div>
            </c:forEach>
            </form>
            <!-- pagation start -->
            <div class="question_operator">
              <div class="pagation">
                <a href="myoes.action?page=${pageBean.nowPage - 1 }&pageSize=${pageSize }"><img alt="left" src="static/img/BTN_PageLeft_20x15.png"></a>
                <c:forEach begin="1" end="${pageBean.pageCount }" varStatus="idx">
                    <c:choose>
                      <c:when test="${idx.index eq pageBean.nowPage }">
                        <a class="active" href="myoes.action?page=${idx.index }&pageSize=${pageSize }">${idx.index }</a>
                      </c:when>
                      <c:otherwise>
                        <a href="myoes.action?page=${idx.index }&pageSize=${pageSize }">${idx.index }</a>
                      </c:otherwise>
                    </c:choose>
                </c:forEach>
                <a href="myoes.action?page=${pageBean.nowPage + 1 }&pageSize=${pageSize }"><img alt="right" src="static/img/BTN_PageRight_20x15.png"></a>
                <select name="pageSize" id="pageSize" >
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
                <input id="inputPage" type="text" value="${pageBean.nowPage }"/>
                <button id="goPage" type="button">Go</button>
              </div>
              <div class="question_delete">
                <button type="button" onclick="openCloseDialog()">Delete</button>
              </div>
            </div>
            <!-- pagation end -->
          
        </div>
      </div>
    </div>
    
    <div class="question_footer_div">
      <div class="question_footer_content">Copyright &copyright; 2014 Augmentum, Inc. All Rights Reserved.</div>
    </div>
    
    <div id="question_dialog_div" class="question_dialog">
      <button id="delete_dialog" class="close_btn" onclick="closeDialog()"></button>
      <span>Are you sure delete the selected options?</span>
      <button onclick="deleteQuestions()" id="yes_btn" class="yes_btn">Yes</button>
      <button onclick="closeDialog()" id="no_btn" class="no_btn">No</button>
    </div>
    
    <!-- flash message开始 -->
    <%
        String flash_msg = (String)session.getAttribute("question_flash_msg");
        flash_msg = flash_msg == null ? "" : flash_msg;
        String isSuccessMsg = "";
        if(flash_msg.equals("")) {
            isSuccessMsg = "style='display:none'";
        }
    %>
    <div id="success_flash_msg" class="flash_msg" <%=isSuccessMsg %> />
    <%
        out.write(flash_msg);
        session.removeAttribute("question_flash_msg");
        if(!flash_msg.equals("")) {
            %>
            <script type="text/javascript">
                setTimeout(function() {
                    document.getElementById("success_flash_msg").style.display = "none";
                }, 3000);
            </script>
            <%
        }
    %>
    <!-- flash message结束 -->
    
  </body>
</html>