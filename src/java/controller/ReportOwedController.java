/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dal.InvoiceDetailDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailInvoice;

public class ReportOwedController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateFrom_raw = request.getParameter("dateFrom");
        String dateTo_raw = request.getParameter("dateTo");
        int pageIndex;
        int pageSize = 5;
        try {
            pageIndex = Integer.parseInt(request.getParameter("pageIndex"));
        } catch (NumberFormatException e) {
            pageIndex = 1;
        }
        Date dateFrom = null;
        Date dateTo = null;
        if (dateFrom_raw == null || dateFrom_raw.trim().length() == 0) {
            dateFrom = null;
        } else {
            dateFrom = Date.valueOf(dateFrom_raw);
        }
        if (dateTo_raw == null || dateTo_raw.trim().length() == 0) {
            dateTo = null;
        } else {
            dateTo = Date.valueOf(dateTo_raw);
        }

        InvoiceDetailDBContext db = new InvoiceDetailDBContext();
        ArrayList<DetailInvoice> invoicesDetailOwed = db.getAllInvoiceOwed(dateFrom, dateTo);
        request.setAttribute("invoicesDetailOwed", invoicesDetailOwed);
        request.setAttribute("dateFrom", dateFrom);
        request.setAttribute("dateTo", dateTo);
        request.setAttribute("pageIndex", pageIndex);
        request.getRequestDispatcher("reportowed.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
