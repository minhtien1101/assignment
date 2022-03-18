<%-- 
    Document   : home
    Created on : Mar 5, 2022, 12:28:18 AM
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
        <title>Home</title>
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
                        <li><a href="login">Log Out</a></li>
                    </ul>
                </div>
            </div>
                        
            <div class="content">
                <div id="search" class="search">
                    <form action="home" method="GET">
                        <div>From <input type="date" value="${requestScope.dateFrom}" name="dateFrom"></div>
                        <div>To <input type="date" value="${requestScope.dateTo}" name="dateTo"></div>
                        Name Buyer
                        <select name="idBuyer">
                            <option value="-1">All</option>
                            <c:forEach items="${requestScope.buyers}" var="b">
                                <option ${(idBuyer == b.id)?"selected":""} value="${b.id}">${b.name}</option>
                            </c:forEach>
                        </select>
                        Name Product
                        <select name="idProduct">
                            <option value="-1">All</option>
                            <c:forEach items="${requestScope.products}" var="p">
                                <option ${(idProduct == p.id)?"selected":""} value="${p.id}">${p.name}</option>
                            </c:forEach>
                        </select>
                        Dimension
                        <select name="idDimension">
                            <option value="-1">All</option>
                            <c:forEach items="${requestScope.dimensions}" var="d">
                                <option ${(idDimension == d.id)?"selected":""} value="${d.id}">${d.name}</option>
                            </c:forEach>
                        </select>
                        
                        <input type="submit" value="Search">
                        
                    </form>
                    <table>
                        <h2>List Of All Invoices</h2>
                        <tr>
                            <td>Name Buyer</td>
                            <td>Phone</td>
                            <td>Name Product</td>
                            <td>Dimension</td>
                            <td>Date</td>
                            <td>Price</td>
                            <td>Quantity</td>
                            <td>Discount</td>
                            <td>Amount</td>
                            <td>Paid</td>
                            <td>Owed</td>
                            <td>Name Agent</td>
                            <td>Phone</td>
                            <td>Address</td>
                            <td></td>
                        </tr>

                        <c:forEach items="${requestScope.invoicesDetail}" var="d">
                            <tr>
                                <td>${d.invoiceProduct.invoice.buyer.name}</td>
                                <td>${d.invoiceProduct.invoice.buyer.phone}</td>
                                <td>${d.invoiceProduct.productDetail.product.name}</td>
                                <td>${d.invoiceProduct.productDetail.dimension.name}</td>
                                <td>${d.invoiceProduct.invoice.date}</td>
                                <td>${d.invoiceProduct.buyPrice}</td>
                                <td>${d.invoiceProduct.quantity}</td>
                                <td>${d.invoiceProduct.discount}%</td>
                                <td>${d.invoiceProduct.invoice.amount}</td>
                                <td>${d.invoiceProduct.invoice.paid}</td>
                                <td>${d.invoiceProduct.invoice.owed}</td>
                                <td>${d.invoiceProduct.invoice.agency.name}</td>
                                <td>${d.invoiceProduct.invoice.agency.phone}</td>
                                <td>${d.invoiceProduct.invoice.agency.address}</td>
                                <td>
                                    <a href="update?idinvoice=${d.invoiceProduct.invoice.id}&idproduct=${d.invoiceProduct.productDetail.product.id}&iddimension=${d.invoiceProduct.productDetail.dimension.id}&quantity=${d.invoiceProduct.quantity}">
                                        Update
                                    </a> | 
                                    <a href="delete?idinvoice=${d.invoiceProduct.invoice.id}&idproduct=${d.invoiceProduct.productDetail.product.id}&iddimension=${d.invoiceProduct.productDetail.dimension.id}&quantity=${d.invoiceProduct.quantity}" onclick="return deleteInvoice()">
                                        Delete
                                    </a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                    <div ${(requestScope.totalPage <= 1)?"style=\"display:none;\"":""} class="page">
                        <input id="pageIndex" value="${requestScope.pageIndex}" onkeyup="keyUp(event)">/${requestScope.totalPage}
                    </div>
                </div>
            </div>
        </div>
        <script>
            function keyUp(event) {
                if (event.keyCode === 13) {
                    window.location.href = "home?page=" + document.getElementById("pageIndex").value;
                }
            }
            function deleteInvoice() {
                var rs = confirm("Do you want to delete?");
                if (rs) {
                    return true;
                } else {
                    return false;
                }
            }
        </script>
    </body>
</html>
