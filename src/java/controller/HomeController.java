
package controller;

import controller.authentication.BaseAuthentication;
import dal.BuyerDBContext;
import dal.DimensionDBContext;
import dal.InvoiceDetailDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Buyer;
import model.Dimension;
import model.InvoiceDetail;
import model.Product;


public class HomeController extends BaseAuthentication {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String dateFrom_raw = request.getParameter("dateFrom");
        String dateTo_raw = request.getParameter("dateTo");
        String idBuyer_raw = request.getParameter("idBuyer");
        String idProduct_raw = request.getParameter("idProduct");
        String idDimension_raw = request.getParameter("idDimension");
        
        if(idBuyer_raw == null || idBuyer_raw.trim().length() == 0) {
            idBuyer_raw = "-1";
        }
        if(idProduct_raw == null || idProduct_raw.trim().length() == 0) {
            idProduct_raw = "-1";
        }
        if(idDimension_raw == null || idDimension_raw.trim().length() == 0) {
            idDimension_raw = "-1";
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
        int idBuyer = Integer.parseInt(idBuyer_raw);
        int idProduct = Integer.parseInt(idProduct_raw);
        int idDimension = Integer.parseInt(idDimension_raw);
        
        int pageIndex;
        try {
            pageIndex = Integer.parseInt(request.getParameter("page"));
        } catch (NumberFormatException e) {
            pageIndex = 1;
        }
        int pageSize = 5;
        
        BuyerDBContext buyerDB = new BuyerDBContext();
        ArrayList<Buyer> buyers = buyerDB.getBuyers();
        
        InvoiceDetailDBContext invoiceDetailDB = new InvoiceDetailDBContext();

        ArrayList<InvoiceDetail> invoicesDetail = 
                invoiceDetailDB.getInvoicesDetail(idBuyer,idProduct, idDimension, 
                        dateFrom, dateTo, pageIndex, pageSize, false);
        
        int count = invoiceDetailDB.count(dateFrom, dateTo, idBuyer, idProduct, idDimension, false);
        int totalPage = (count % pageSize == 0)?(count/pageSize):((count/pageSize)+1);
        
        ProductDBContext productDb = new ProductDBContext();
        ArrayList<Product> products = productDb.getProducts();
        
        DimensionDBContext dimensionDb = new DimensionDBContext();
        ArrayList<Dimension> dimensions = dimensionDb.getDimensions();
        request.setAttribute("pageIndex", pageIndex);
        request.setAttribute("totalPage", totalPage);
        request.setAttribute("buyers", buyers);
        request.setAttribute("products", products);
        request.setAttribute("dimensions", dimensions);
        request.setAttribute("idBuyer", idBuyer);
        request.setAttribute("idProduct", idProduct);
        request.setAttribute("idDimension", idDimension);
        request.setAttribute("invoicesDetail", invoicesDetail);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
