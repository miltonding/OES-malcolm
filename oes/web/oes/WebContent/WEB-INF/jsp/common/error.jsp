<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>error message!!!</title>
</head>
<body>
  <c:if test="${errorMsg != null }">
    ${errorMsg }
  </c:if>
  <c:if test="${ERROR_FIELDS != null }">
      <%
          Map<String, Object> error_field_msg = (HashMap<String, Object>) request.getAttribute("ERROR_FIELDS");
          Set<String> set = error_field_msg.keySet();
          for (String str : set) {
              out.println(error_field_msg.get(str));
          }
      %>
  </c:if>
  <c:if test="${TIP_MESSAGE != null }">
    ${TIP_MESSAGE }
  </c:if>
</body>
</html>