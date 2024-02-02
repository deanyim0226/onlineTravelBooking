<%@page language="java" contentType="text/html; ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="f"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="../js/jquery-3.7.1.min.js"></script>
    <title>Role Form</title>
    <style>


    </style>
    <script>
        $(document).ready(function(){
            $("#add").css("color","black")
            $("#view").css("color","black")

            $("#role-form").hide()
            $("#role-result").hide()

            $("#add").click(function(){

                $(this).parent().css("background-color","orange")
                $("#view").parent().css("background-color", "white")

                $("#role-form").show()
                $("#role-result").hide()
            })

            $("#view").click(function(){
                $(this).parent().css("background-color","orange")
                $("#add").parent().css("background-color", "white")

                $("#role-result").show()
                $("#role-form").hide()
            })
        })
    </script>
</head>
<body>
<header>
    <nav class="navbar bg-dark border-bottom border-body" data-bs-theme="dark">
        <a class="btn btn-dark dropdown"   href="home"  >HOME</a>
        <ul class="nav justify-content-end">
            <s:authorize access="isAuthenticated()">

                <s:authorize access="hasAuthority('Admin')">
                    <li class = "nav-item"><a class="btn btn-dark dropdown"  href="user" >USER</a></li>
                    <li class = "nav-item"><a class="btn btn-dark dropdown"  href="role" >ROLE</a></li>
                </s:authorize>
                <li class = "nav-item"><a class="btn btn-dark dropdown"   href="reservation">RESERVATION </a></li>

                <li class = "nav-item"><a class="btn btn-dark dropdown"  href="/logout">LOGOUT</a></li>
            </s:authorize>

            <c:if test="${isLoggin}">
                <li class = "nav-item"><a class="btn btn-dark dropdown"  href="/login">LOGIN</a></li>
            </c:if>

        </ul>
    </nav>
</header>
<div align="center">
    <div class="row">
        <div class="col">
            <a id="add" href="#" style="font-size:25px ">ROLE FORM</a>
        </div>
        <div class="col">
            <a id="view" href="#" style="font-size:25px ">VIEW ROLES</a>
        </div>
    </div>

</div>
    <div id="role-form">

            <f:form action="saveRole" method="POST" modelAttribute="role">
                <b>ROLE ID</b>
                <f:input path="roleId" class="form-control" type="text" id="modal_roleId"/>
                <b>ROLE NAME</b>
                <f:input path="roleName" class="form-control" type="text" id="modal_roleName"/>
                <input style="margin-top:25px" class="btn form-control btn-primary" type="submit" id="" value="Submit"/>
            </f:form>
    </div>

    <div id="role-result" align="center">

        <table class="table table-light table-striped-column">
            <tr>
                <th>ROLE-ID</th>
                <th>ROLENAME</th>
            </tr>

            <c:forEach items="${roles}" var="role">
                <tr>
                    <td>${role.getRoleId()}</td>
                    <td>${role.getRoleName()}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>