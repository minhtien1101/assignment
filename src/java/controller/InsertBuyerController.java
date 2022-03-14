/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.BuyerDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Buyer;

/**
 *
 * @author DELL
 */
public class InsertBuyerController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../insertbuyer.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String name_raw = request.getParameter("name");
        String phone_raw = request.getParameter("phone");
        String gender_raw = request.getParameter("gender");
        String dob_raw = request.getParameter("dob");
        String address_raw = request.getParameter("address");

        String name = name_raw;
        String phone = phone_raw;
        boolean gender = (gender_raw.equalsIgnoreCase("male"));
        Date dob = Date.valueOf(dob_raw);
        String address = address_raw;

        Buyer b = new Buyer();
        b.setName(name);
        b.setPhone(phone);
        b.setGender(gender);
        b.setDob(dob);
        b.setAddress(address);
        BuyerDBContext db = new BuyerDBContext();
        db.insertBuyer(b);
        response.sendRedirect("../insert");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
