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
	<%--<script src="https://twitter.github.io/typeahead.js/releases/latest/typeahead.bundle.js"></script>--%>
	<link href="<c:url value="/plugins/typeahead/Typeahead.css" />" rel="stylesheet">
	<script src="<c:url value="/plugins/typeahead/typeahead-min.js" />"></script>

	<link href="<c:url value="/plugins/bootstrap-select/css/bootstrap-select.css" />" rel="stylesheet">
	<script src="<c:url value="/plugins/bootstrap-select/js/bootstrap-select.js" />"></script>
	<title><tiles:insertAttribute name="title" /></title>
</head>
<body>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">Take A Trip</a>
		</div>
		<div id="navbar" class="collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="#about">About</a></li>
				<li><a href="#contact">Contact</a></li>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>

<div class="container">

	<div class="content">
		<tiles:insertAttribute name="content" />
	</div>

</div><!-- /.container -->

</body>
</html>