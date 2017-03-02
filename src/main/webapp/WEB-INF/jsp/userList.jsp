<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>User List Form</title>
</head>
<body>
    <div align="center">
        <h1>User already exist. Select your user...</h1>        
        <table border="1"> 
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Surname</th>
            <th>Action</th>
 
            <c:forEach var="user" items="${userList}">
                <tr>
 
                    <td>${user.name}</td>
                    <td>${user.email}</td>
                    <td>${user.phone}</td>
                    <td>${user.surname}</td>
                    <td>
                    	<a href="selectEmployee?id=${user.email}">Select</a>
                    	<a href="deleteEmployee?id=${user.email}">Delete</a>
                   	</td>
 
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>