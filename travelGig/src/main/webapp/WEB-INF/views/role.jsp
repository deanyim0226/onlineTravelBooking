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
    <title>Role Form</title>

</head>
<body>
<header>

    <div align="center">

            <f:form action="saveRole" method="POST" modelAttribute="role">
                <div class="col">
                    ROLE ID <f:input path="roleId" class="form-control" type="text" id="modal_roleId"/>
                    ROLE <f:input path="roleName" class="form-control" type="text" id="modal_roleName"/>
                    <input style="margin-top:25px" class="btn form-control btn-primary" type="submit" id="" value="submit"/>
                </div>
            </f:form>

    </div>



</body>
</html>