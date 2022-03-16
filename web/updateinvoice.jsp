<%-- 
    Document   : update
    Created on : Mar 8, 2022, 9:01:30 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Invoice</title>
    </head>
    <body>
        <form action="update" method="POST">
            <table>
                <tr>
                    <td>Id Invoice</td>
                    <td>
                        <input type="hidden" value="${requestScope.invoiceDetail.invoice.id}" name="idInvoice"/>
                        ${requestScope.invoiceDetail.invoice.id}
                    </td>
                </tr>
                <tr>
                    <td>Order by</td>
                    <td>
                        <select name="idBuyer">
                            <c:forEach items="${requestScope.buyers}" var="b">
                                <option ${(requestScope.invoiceDetail.invoice.buyer.id==b.id)?"selected":""} 
                                    value="${b.id}">${b.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Product</td>
                    <td>
                        <select name="idProduct">
                            <c:forEach items="${requestScope.products}" var="p">
                                <option ${(requestScope.invoiceDetail.invoiceProduct.productDetail.product.id==p.id)?"selected":""} 
                                    value="${p.id}">${p.name}</option>       
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Dimension</td>
                    <td>
                        <select name="idDimension">
                            <c:forEach items="${requestScope.dimensions}" var="d">
                                <option ${(requestScope.invoiceDetail.invoiceProduct.productDetail.dimension.id==d.id)?"selected":""}
                                    value="${d.id}">${d.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td>Date</td>
                    <td><input type="date" name="date" 
                               value="${requestScope.invoiceDetail.invoice.date}" required/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input id="price" type="number" name="buyPrice" min="0" 
                               value="${requestScope.invoiceDetail.invoiceProduct.buyPrice}" required/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input id="quantity" type="number" name="quantity" min="0" 
                               value="${requestScope.invoiceDetail.invoiceProduct.quantity}" required/></td>
                </tr>
                <tr>
                    <td>Discount</td>
                    <td><input id="discount" type="number" name="discount" min="0" max="100" 
                               value="${requestScope.invoiceDetail.invoiceProduct.discount}" required/></td>
                </tr>
                <tr>
                    <td>Amount</td>
                    <td><input id="amount" type="number" name="amount" 
                               value="${requestScope.invoiceDetail.invoice.amount}" onclick="getAmount()" required/></td>
                </tr>
                <tr>
                    <td>Paid</td>
                    <td><input id="paid" type="number" name="paid" min="0" 
                               value="${requestScope.invoiceDetail.invoice.paid}" required/></td>
                </tr>
                <tr>
                    <td>Owed</td>
                    <td><input id="owed" type="number" name="owed" min="0" 
                               value="${requestScope.invoiceDetail.invoice.owed}" onclick="getOwed()" required/></td>
                </tr>
                <tr>
                    <td>Name Agency</td>
                    <td>
                        <select name="idAgency">
                            <c:forEach items="${requestScope.agencies}" var="a">
                                <option ${(requestScope.invoiceDetail.invoice.agency.id==a.id)?"selected":""} 
                                    value="${a.id}">${a.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save" onclick="return submitInvoice()"/>
        </form>
        <script>
            function getAmount() {
                var price = document.getElementById("price");
                var quantity = document.getElementById("quantity");
                var discount = document.getElementById("discount");
                var amount = document.getElementById("amount");
                amount.value = Math.round((price.value * quantity.value) - (price.value * quantity.value * discount.value / 100));
            }

            function getOwed() {
                var amount = document.getElementById("amount");
                var paid = document.getElementById("paid");
                var owed = document.getElementById("owed");
                owed.value = amount.value - paid.value;
            }
            function submitInvoice() {
                var submit = false;
                var price = document.getElementById("price");
                var quantity = document.getElementById("quantity");
                var discount = document.getElementById("discount");
                var amount = document.getElementById("amount");
                var paid = document.getElementById("paid");
                var owed = document.getElementById("owed");
                if (paid.value <= amount.value
                        && owed <= (amount.value - paid.value)) {
                    submit = true;
                } else {
                    submit = false;
                }
                return submit;
            }
        </script>
    </body>
</html>
