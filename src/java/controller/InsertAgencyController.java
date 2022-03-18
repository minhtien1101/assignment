/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.authentication.BaseAuthentication;
import dal.AgencyDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agency;

/**
 *
 * @author DELL
 */
public class InsertAgencyController extends BaseAuthentication {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../insertagency.jsp").forward(request, response);
    }


    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
        Agency agency = new Agency();
        agency.setName(name);
        agency.setPhone(phone);
        agency.setAddress(address);
        AgencyDBContext db = new AgencyDBContext();
        db.insertAgency(agency);
        response.sendRedirect("../insert");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
