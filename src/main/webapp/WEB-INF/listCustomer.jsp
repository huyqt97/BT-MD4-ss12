<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: shmily
  Date: 03/08/2023
  Time: 14:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>List Customer</h1>
<form action="<%=request.getContextPath()%>/CustomerServlet">
    <input type="text" name="search" value="${searchName}">
    <label>Sort</label>
    <select name="sort">
        <option value="name" <%= (request.getAttribute("sort") != null && request.getAttribute("sort").equals("name")) ? "selected" : "" %>>Name</option>
        <option value="email" <%= (request.getAttribute("sort") != null && request.getAttribute("sort").equals("email")) ? "selected" : "" %>>Email</option>
        <option value="address" <%= (request.getAttribute("sort") != null && request.getAttribute("sort").equals("address")) ? "selected" : "" %>>Address</option>
    </select>
    <label>By</label>
    <select name="By">
        <option value="ASC" <%= (request.getAttribute("By") != null && request.getAttribute("By").equals("ASC")) ? "selected" : "" %>>ASC</option>
        <option value="DESC" <%= (request.getAttribute("By") != null && request.getAttribute("By").equals("DESC")) ? "selected" : "" %>>DESC</option>
    </select>
    <input type="submit" name="action" value="SEARCH">
</form>

<br>
<a href="<%=request.getContextPath()%>/CustomerServlet?action=ADD">ADD</a>

<table border="10" cellpadding="20">
    <thead>
    <tr>
        <th>STT</th>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th colspan="2">Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${customers}" var="c" varStatus="item">
        <tr>
            <td>${item.count}</td>
            <td>${c.name}</td>
            <td>${c.email}</td>
            <td>${c.address}</td>
            <td><a href="<%=request.getContextPath()%>/CustomerServlet?action=EDIT&id=${c.id}">Edit</a></td>
            <td><a onclick="return confirm('Are you sure?')" href="<%=request.getContextPath()%>/CustomerServlet?action=DELETE&id=${c.id}">Delete</a></td>
        </tr>


    </c:forEach>
    </tbody>
</table>
</body>
</html>
