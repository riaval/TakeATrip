<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<form action="http://localhost:8080/TakeATrip/addtransfer" method="post">
    <input type="text" name="city1" placeholder="City 1 ID"> <br>
    <input type="text" name="city2" placeholder="City 2 ID"> <br>
    <input type="number" name="price" placeholder="price"> <br>
    <input type="number" name="duration" placeholder="duration"> <br>
    <select name="type">
        <option value="PLANE">Plane</option>
        <option value="BUS">Bus</option>
        <option value="TRAIN">Train</option>
    </select>
    <br>
    <input type="submit">
</form>