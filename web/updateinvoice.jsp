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
                        <input type="hidden" value="${requestScope.invoiceDetail.invoiceProduct.invoice.id}" name="idInvoice"/>
                        ${requestScope.invoiceDetail.invoiceProduct.invoice.id}
                    </td>
                </tr>
                <tr>
                    <td>Buyer</td>
                    <td>
                        <select name="idBuyer">
                            <c:forEach items="${requestScope.buyers}" var="b">
                                <option ${(requestScope.invoiceDetail.invoiceProduct.invoice.buyer.id==b.id)?"selected":""} 
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
                               value="${requestScope.invoiceDetail.invoiceProduct.invoice.date}" required/></td>
                </tr>
                <tr>
                    <td>Price</td>
                    <td><input id="price" type="number" name="buyPrice" min="0" 
                               value="${requestScope.invoiceDetail.invoiceProduct.buyPrice}" required/></td>
                </tr>
                <tr>
                    <td>Quantity</td>
                    <td><input id="quantity" type="number" name="quantity" min="0" 
                               value="${requestScope.invoiceDetail.invoiceProduct.quantity}" required/>
                        <div style="color: red;" id="msg-quantity"></div>
                    </td>
                </tr>

                <tr>
                    <td>Amount</td>
                    <td><input id="amount" type="number" name="amount" 
                               value="${requestScope.invoiceDetail.invoiceProduct.invoice.amount}" onclick="getAmount()" required/></td>
                </tr>
                <tr>
                    <td>Paid</td>
                    <td><input id="paid" type="number" name="paid" min="0" 
                               value="${requestScope.invoiceDetail.invoiceProduct.invoice.paid}" required/>
                        <div style="color: red;" id="msg-paid"></div>
                    </td>
                </tr>
                <tr>
                    <td>Owed</td>
                    <td><input id="owed" type="number" name="owed" min="0" 
                               value="${requestScope.invoiceDetail.invoiceProduct.invoice.owed}" onclick="getOwed()" required/>
                        <div style="color: red;" id="msg-owed"></div>
                    </td>
                </tr>
                <tr>
                    <td>Name Agency</td>
                    <td>
                        <select name="idAgency">
                            <c:forEach items="${requestScope.agencies}" var="a">
                                <option ${(requestScope.invoiceDetail.invoiceProduct.invoice.agency.id==a.id)?"selected":""} 
                                    value="${a.id}">${a.name}</option>
                            </c:forEach>
                        </select>
                    </td>
                </tr>
            </table>
            <input type="submit" value="Save" onclick="return updateInvoice()"/>
        </form>
        <script>
            function getAmount() {
                var price = document.getElementById("price");
                var quantity = document.getElementById("quantity");
                var amount = document.getElementById("amount");
                amount.value = Math.round(price.value * quantity.value);
            }

            function getOwed() {
                var amount = document.getElementById("amount");
                var paid = document.getElementById("paid");
                var owed = document.getElementById("owed");
                owed.value = amount.value - paid.value;
                if(owed.value <= 0) {
                    owed.value = 0;
                }
            }
            function updateInvoice() {
                var price = document.getElementById("price").value;
                var quantity = document.getElementById("quantity").value;
                var amount = document.getElementById("amount").value;
                var paid = document.getElementById("paid").value;
                var owed = document.getElementById("owed").value;
                if ((paid <= (Math.round(price * quantity)))
                        && (owed <= (amount - paid))
                        && quantity > 0) {
                    return true;
                } else {
                    if(paid > amount) {
                        document.getElementById("msg-paid").innerHTML = "Paid must <= "+ amount.value;
                    } else {
                        document.getElementById("msg-paid").innerHTML = "";
                    }
                    if(owed > (amount - paid) && (amount - paid) >= 0) {
                        document.getElementById("msg-owed").innerHTML = "Owed must <= "+(amount - paid);
                    } else {
                        document.getElementById("msg-owed").innerHTML = "";
                    }
                    if(quantity <= 0) {
                        document.getElementById("msg-quantity").innerHTML = "Quantity must > 0";
                    } else {
                        document.getElementById("msg-quantity").innerHTML = "";
                    }
                    return false;
                }
            }
        </script>
    </body>
</html>
