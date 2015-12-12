<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form action="http://localhost:8080/TakeATrip/cities" method="post">
    <input type="text" name="name" placeholder="Name"> <br>
    <input type="text" name="country" placeholder="Country"> <br>
    <input type="text" name="region" placeholder="Region"> <br>
    <input type="number" name="priceLive" placeholder="Price life"> <br>
    <input type="number" name="priceFood" placeholder="Price food"> <br>
    <input type="submit">
</form>