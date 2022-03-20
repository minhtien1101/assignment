/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller.authentication;

import dal.AccountDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Role;

/**
 *
 * @author DELL
 */
public class RoleAccountController extends BaseAuthentication {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String username = request.getParameter("username");
        if(username == null || username.trim().length() == 0) {
            Account account = (Account) request.getSession().getAttribute("account");
            username = account.getUsername();
        }
        request.setAttribute("username", username);
        
        AccountDBContext db = new AccountDBContext();
        
        ArrayList<Role> roles = db.getRoles();
        request.setAttribute("roles", roles);
        ArrayList<Account> accounts = db.getAccounts();
        request.setAttribute("accounts", accounts);
        
        Role roleAccount = db.getRoleAccountByUsername(username);
        request.setAttribute("roleAccount", roleAccount);
        request.setAttribute("username", username);
        request.getRequestDispatcher("role.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String role_raw = request.getParameter("role");
        int role = Integer.parseInt(role_raw);
        AccountDBContext db = new AccountDBContext();
        db.updateRoleAccount(username, role);
        response.sendRedirect("role");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
