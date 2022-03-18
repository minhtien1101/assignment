<%-- 
    Document   : insertbuyer
    Created on : Mar 13, 2022, 9:24:02 PM
    Author     : DELL
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insert New Buyer</title>
    </head>
    <body>
        <h2>Insert New Buyer</h2>
        <form action="../buyer/insert" method="POST">
            <table>
                <tr>
                    <td>Name: </td>
                    <td><input type="text" name="name" required /></td>
                </tr>
                <tr>
                    <td>Phone Number: </td>
                    <td>
                        <input id="phone" type="text" name="phone" required/>
                        <br/>
                        <span id="msg-phone" style="color: red;"></span>
                    </td>
                </tr>
                <tr>
                    <td>Gender</td>
                    <td>
                        <input type="radio" name="gender" value="male" required/> Male
                        <input type="radio" name="gender" value="female" required/> Female
                    </td>
                </tr>
                <tr>
                    <td>Date of Birth: </td>
                    <td><input type="date" name="dob" required/></td>
                </tr>
                <tr>
                    <td>Address: </td>
                    <td><input type="text" name="address" required/></td>
                </tr>
                <tr>
                    <td></td>
                    <td><input type="submit" value="Save" onclick="return addBuyer()"/>   </td>
                </tr>
            </table>
        </form>
        <script>
            function addBuyer() {
                var phone = document.getElementById("phone");
                var regex_phone = /[0-9]+/;
                if(regex_phone.test(phone.value)) {
                    document.getElementById("msg-phone").innerHTML = "";
                    return true;
                } else {
                    document.getElementById("msg-phone").innerHTML = "Phone number only contain number!";
                    return false;
                }
            }
        </script>
    </body>
</html>
