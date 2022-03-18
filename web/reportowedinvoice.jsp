<%-- 
    Document   : reportowed
    Created on : Mar 10, 2022, 4:34:54 PM
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
        <title>Invoices Owed</title>
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
                <div id="owed" class="owed">
                    <form action="owed" method="get">
                        <div>From <input type="date" value="${requestScope.dateFrom}" name="dateFrom"></div>
                        <div>To <input type="date" value="${requestScope.dateTo}" name="dateTo"></div>
                        <div>Buyer 
                            <select name="idBuyer">
                                <option value="-1">All</option>
                                <c:forEach items="${requestScope.buyers}" var="b">
                                    <option ${(requestScope.idBuyer == b.id)?"selected":""} value="${b.id}">${b.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                        Name Product
                        <select name="idProduct">
                            <option value="-1">All</option>
                            <c:forEach items="${requestScope.products}" var="p">
                                <option ${(idProduct == p.id)?"selected":""} value="${p.id}">${p.name}</option>
                            </c:forEach>
                        </select>
                        Name Dimension
                        <select name="idDimension">
                            <option value="-1">All</option>
                            <c:forEach items="${requestScope.dimensions}" var="d">
                                <option ${(idDimension == d.id)?"selected":""} value="${d.id}">${d.name}</option>
                            </c:forEach>
                        </select>
                        <input type="submit" value="Search">
                    </form>
                    <h2>List Of All Invoice Owed</h2>
                    <table>
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
                            <td>Name Agency</td>
                            <td>Phone</td>
                            <td>Address</td>
                        </tr>
                        <c:forEach items="${requestScope.invoicesDetailOwed}" var="iv">
                            <tr>
                                <td>${iv.invoiceProduct.invoice.buyer.name}</td>
                                <td>${iv.invoiceProduct.invoice.buyer.phone}</td>
                                <td>${iv.invoiceProduct.productDetail.product.name}</td>
                                <td>${iv.invoiceProduct.productDetail.dimension.name}</td>
                                <td>${iv.invoiceProduct.invoice.date}</td>
                                <td>${iv.invoiceProduct.buyPrice}</td>
                                <td>${iv.invoiceProduct.quantity}</td>
                                <td>${iv.invoiceProduct.discount}%</td>
                                <td>${iv.invoiceProduct.invoice.amount}</td>
                                <td>${iv.invoiceProduct.invoice.paid}</td>
                                <td>${iv.invoiceProduct.invoice.owed}</td>
                                <td>${iv.invoiceProduct.invoice.agency.name}</td>
                                <td>${iv.invoiceProduct.invoice.agency.phone}</td>
                                <td>${iv.invoiceProduct.invoice.agency.address}</td>
                                <td>
                                    <button>
                                        <a href="updateowedinvoice?idinvoice=${iv.invoiceProduct.invoice.id}&amount=${iv.invoiceProduct.invoice.amount}">
                                            Paid full
                                        </a>
                                    </button>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                        <div ${(requestScope.totalPage <= 1)?"style=\"display:none;\"":""} class="page">
                        <input ${(requestScope.totalPage <= 1)?"type=\"hidden\"":"type=\"text\""} id="pageIndex"  value="${requestScope.pageIndex}" 
                                                                                                  onkeyup="keyUp(event)">/${requestScope.totalPage}
                    </div>
                </div>
            </div>
        </div>
        <script>
            function keyUp(event) {
                if (event.keyCode === 13) {
                    window.location.href = "owed?page=" + document.getElementById("pageIndex").value;
                }
            }
        </script>
    </body>
</html>

