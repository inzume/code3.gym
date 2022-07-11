<%@ page import="java.util.List" %>
<%@ page import="model.NhanVien" %><%--
  Created by IntelliJ IDEA.
  User: johntoan98gmail.com
  Date: 01/07/2022
  Time: 10:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>

</head>
<body id="page-top">
<!-- Navigation-->

<!-- Masthead-->
<header class="masthead">
    <div class="container px-4 px-lg-5 d-flex h-100 align-items-center justify-content-center">
        <div class="d-flex justify-content-center">
            <div class="text-center">
                <h1 class="mx-auto my-0 text-uppercase">Grayscale</h1>
                <h2 class="text-white-50 mx-auto mt-2 mb-5">A free, responsive, one page Bootstrap theme created by Start Bootstrap.</h2>
                <a class="btn btn-primary" href="#about">Get Started</a>
            </div>
        </div>
    </div>
</header>
<h2>Table Product</h2>  <button type="button" class="btn btn-success"><a href="/nhanvien?action=create">Create</a></button>


<form action="/nhanvien">
    <input name="action" value="search" hidden>
    <input name="search"><button type="submit">Search</button>
</form>


<table class="container table table-striped">
    <thead>
    <tr>
        <th>id</th>
        <th>name</th>
        <th>DateOfBirth</th>
        <th>address</th>
        <th>email</th>
        <th>phoneNumber</th>
        <th>Class Name</th>
        <th>Delete</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="p" items="${nhanvien}">
        <tr>
            <td>${p.getId()}</td>
            <td>${p.getName()}</td>
            <td>${p.getBirthDay()}</td>
            <td>${p.getLocation()}</td>
            <td>${p.getEmail()}</td>
            <td>${p.getNumber()}</td>
            <td>${p.getPhongBan().getNamePhongBan()}</td>
            <td><button type="button" class="btn btn-warning"><a href="/nhanvien?id=${p.getId()}&action=edit">Edit</a></button></td>
            <td><button type="button" class="btn btn-danger"><a href="/nhanvien?id=${p.getId()}&action=delete">Delete</a></button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>


</body>
</html>