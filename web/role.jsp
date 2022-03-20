<%-- 
    Document   : role
    Created on : Mar 19, 2022, 10:53:22 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
        <title>Role Account</title>
    </head>
    <body>
        <div class="container">
            <div class="header">
                <div class="header-title">Quản Lý Nhập Vật Liệu</div>

                <ul class="header-menu">
                    <li id="btn-search"><a href="home">Home</a></li>
                    <li id="btn-insert"><a href="insert">Insert Invoice</a></li>
                    <li id="btn-owed"><a href="owed">Invoices Owed</a></li>
                    <li id="btn-warehouse"><a href="warehouse">Warehouse</a></li>
                </ul>

                <div class="user-info">
                    <div class="user-name">
                        <i class="fa-solid fa-user"></i>
                        <span>${sessionScope.account.displayname}</span>
                        <i class="fa-solid fa-caret-down"></i>

                    </div>
                    <ul class="user-menu">
                        <li><a href="role">Role Account</a></li>
                        <li><a href="login">Log Out</a></li>
                    </ul>
                </div>
            </div>

            <div class="content">
                <div class="role">
                    <form action="role" method="POST">
                        <div id="list-acc">Account: 
                            <select name="username" onchange="getRole(this)">
                                <c:forEach items="${requestScope.accounts}" var="a">
                                    <option value="${a.username}" ${(requestScope.username.equals(a.username))?"selected":""}>
                                        ${a.username}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div id="role-acc">
                            <c:forEach items="${requestScope.roles}" var="r">
                                <div>
                                    <input type="radio" value="${r.id}" name="role" ${(requestScope.roleAccount.id==r.id)?"checked":""}/>${r.name}
                                </div>
                            </c:forEach>
                        </div>
                        <input type="submit" value="Save"/>
                    </form>
                </div>
            </div>
        </div>

        <script>
            function getRole(obj) {
                window.location.href = "role?username=" + obj.value;
            }
        </script>
    </body>
</html>
