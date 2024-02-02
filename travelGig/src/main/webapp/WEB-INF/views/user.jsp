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
    <title>USER Form</title>
    <script>
        $(document).ready(function(){

            $("#add").css("color","black")
            $("#view").css("color","black")

            $("#user-form").hide()
            $("#user-result").hide()

            $("#add").click(function(){

                $(this).parent().css("background-color","orange")
                $("#view").parent().css("background-color", "white")

                $("#user-form").show()
                $("#user-result").hide()
            })

            $("#view").click(function(){
                $(this).parent().css("background-color","orange")
                $("#add").parent().css("background-color", "white")

                $("#user-result").show()
                $("#user-form").hide()
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
                <a id="add" href="#" style="font-size:25px ">USER FORM</a>
            </div>
            <div class="col">
                <a id="view" href="#" style="font-size:25px ">VIEW USERS</a>
            </div>
        </div>
</div>
<div id="user-form">

    <f:form action="saveUser" method="POST" modelAttribute="user">

        <b>USER-ID</b>
        <f:input path="userId" readonly="false" class="form-control" type="text" id="modal_userId"/>
        <b>USERNAME</b>
        <f:input path="userName" class="form-control" type="text" id="modal_userName"/>
        <b>PASSWORD</b>
        <f:input path="userPassword" class="form-control" type="text" id="modal_userPassword"/>
        <b>EMAIL</b>
        <f:input path="email" class="form-control" type="text" id="modal_userEmail"/>
        <c:forEach  items="${roles}" var="role" >

            <c:if test="${retrievedRole.contains(role)}">
                <f:checkbox class="checkbox1" path="roles" label="${role.getRoleName()}" value="${role.getRoleId()}" checked="ture"/>
            </c:if>

            <c:if test="${!retrievedRole.contains(role)}">
                <f:checkbox class="checkbox2" path="roles" label="${role.getRoleName()}" value="${role.getRoleId()}"/>
            </c:if>

        </c:forEach>
        <input style="margin-top:25px" class="btn form-control btn-primary" type="submit" id="" value="Submit"/>

    </f:form>
</div>

<div id="user-result" align="center">
    <table class="table table-light table-striped-column">
        <tr>
            <th>USER-ID</th>
            <th>USERNAME</th>
            <th>PASSWORD</th>
            <th>EMAIL</th>
            <th>ROLE</th>
        </tr>

        <c:forEach items="${users}" var="user">
            <tr>
                <td>${user.getUserId()}</td>
                <td>${user.getUserName()}</td>
                <td>${user.getUserPassword()}</td>
                <td>${user.getEmail()}</td>
                <td>
                    <c:forEach items="${user.getRoles()}" var="role">
                        ${role.getRoleName()}
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>