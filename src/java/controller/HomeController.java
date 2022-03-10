
package controller;

import dal.HomeDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DetailInvoice;

/**
 *
 * @author DELL
 */
public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateFrom_raw = request.getParameter("dateFrom");
        String dateTo_raw = request.getParameter("dateTo");
        boolean check = false;
        Date dateFrom = null;
        Date dateTo = null;
        if(dateFrom_raw == null || dateFrom_raw.trim().length() == 0) {
            dateFrom = null;
        } else {
            dateFrom = Date.valueOf(dateFrom_raw);
        }
        if(dateTo_raw == null || dateTo_raw.trim().length() == 0 ) {
            dateTo = null;
        } else {
            dateTo = Date.valueOf(dateTo_raw);
        }
        HomeDBContext db = new HomeDBContext();
        ArrayList<DetailInvoice> detailInvoices = db.getInvoiceDetail();
        request.setAttribute("detailInvoices", detailInvoices);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
