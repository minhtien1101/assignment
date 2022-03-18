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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author DELL
 */
//@WebServlet(name = "BaseAuthentication", urlPatterns = {"/BaseAuthentication"})
public abstract class BaseAuthentication extends HttpServlet {

    private boolean isAuth(HttpServletRequest request) {
        Account account = (Account) request.getSession().getAttribute("account");
        if (account == null) {
            return false;
        } else {
            String url = request.getServletPath();
            AccountDBContext db = new AccountDBContext();
            int num = db.getNumberOfRoles(account.getUsername(), url);
            return num >= 1;
        }
    }

    protected abstract void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected abstract void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (isAuth(request)) {
            //business
            processGet(request, response);
        } else {
            if (account == null) {
                response.sendRedirect("login");
            } else {
                response.getWriter().println("access denied!");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Account account = (Account) request.getSession().getAttribute("account");
        if (isAuth(request)) {
            //business
            processPost(request, response);
        } else {
            if (account == null) {
                response.sendRedirect("login");
            } else {
                response.getWriter().println("access denied!");
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
