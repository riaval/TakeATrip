<%@ page contentType="text/html; charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8" />
	<link href="<c:url value="/css/main.css" />" rel="stylesheet">
	<!-- jQuery -->
	<script type="text/javascript" charset="UTF-8" src="http://code.jquery.com/jquery-2.0.3.min.js"></script>
	<%-- jQuery Growl --%>
	<script src="<c:url value="/plugins/jquery.growl/javascripts/jquery.growl.js" />" type="text/javascript"></script>
	<link href="<c:url value="/plugins/jquery.growl/stylesheets/jquery.growl.css" />" rel="stylesheet" type="text/css" />
	<!-- Bootstrap -->
	<link href="<c:url value="/plugins/bootstrap/css/bootstrap.min.css" />" rel="stylesheet">
	<script type="text/javascript" src="<c:url value="/plugins/bootstrap/js/bootstrap.min.js" />"></script>
	<title><tiles:insertAttribute name="title" /></title>
</head>
<body>

<!-- Begin page content -->
<div class="container">
	<tiles:insertAttribute name="content" />
</div>

</body>
</html>