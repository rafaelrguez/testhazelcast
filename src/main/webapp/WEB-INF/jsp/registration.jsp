<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">



<title>Registration Form</title>
</head>
<body>
    <div align="center">
        <h1>Registration Form</h1>
        <form:form action="saveUser" method="post" modelAttribute="userBean">
        <table>
            <form:hidden path="email"/>
            <tr>
                <td>Name:</td>
                <td><form:input path="name" /></td>
            </tr>
            <tr>
                <td>Email:</td>
                <td><form:input path="email" /></td>
            </tr>
            <tr>
                <td>Phone:</td>
                <td><form:input path="phone" /></td>
            </tr>
            <tr>
                <td>Surname:</td>
                <td><form:input path="surname" /></td>
            </tr>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="Send"></td>
            </tr>
        </table>
        </form:form>
    </div>
</body>
</html>