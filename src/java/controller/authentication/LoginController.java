/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

public class LoginController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        AccountDBContext db = new AccountDBContext();
        Account acc = db.getAccount(username, password);
        if(acc != null) {
            request.getSession().setAttribute("account", acc);
            response.sendRedirect("home");
        } else {
            request.setAttribute("msgErr", "Username or password wrong!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
