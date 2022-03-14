
package controller;

import dal.BuyerDBContext;
import dal.InvoiceDetailDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Buyer;
import model.DetailInvoice;


public class HomeController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateFrom_raw = request.getParameter("dateFrom");
        String dateTo_raw = request.getParameter("dateTo");
        String index = request.getParameter("page");   
        int idBuyer;
        try {
            idBuyer = Integer.parseInt(request.getParameter("idBuyer"));
        } catch (NumberFormatException e) {
            idBuyer = -1;
        }
        
        if(index == null || index.trim().length() == 0) {
            index = "1";
        }
        Date dateFrom = null;
        Date dateTo = null;
        if(dateFrom_raw == null || dateFrom_raw.trim().length() == 0) {
            dateFrom = null;
        } else {
            dateFrom = Date.valueOf(dateFrom_raw);
            request.setAttribute("dateFrom", dateFrom);
        }
        if(dateTo_raw == null || dateTo_raw.trim().length() == 0 ) {
            dateTo = null;
        } else {
            dateTo = Date.valueOf(dateTo_raw);
            request.setAttribute("dateTo", dateTo);
        }
        int pageIndex = Integer.parseInt(index);
        int pageSize = 5;
        BuyerDBContext buyerDB = new BuyerDBContext();
        ArrayList<Buyer> buyers = buyerDB.getBuyers();
        InvoiceDetailDBContext invoiceDetailDB = new InvoiceDetailDBContext();
        ArrayList<DetailInvoice> detailInvoices = invoiceDetailDB.getInvoiceDetail(idBuyer,dateFrom, dateTo, pageIndex, pageSize);
        int count = invoiceDetailDB.count(dateFrom, dateTo, idBuyer);
        int totalPage = (count % pageSize == 0)?(count/pageSize):((count/pageSize)+1);
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("buyers", buyers);
        request.setAttribute("idBuyer", idBuyer);
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
