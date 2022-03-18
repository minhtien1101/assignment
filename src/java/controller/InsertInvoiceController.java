
package controller;

import controller.authentication.BaseAuthentication;
import dal.AgencyDBContext;
import dal.BuyerDBContext;
import dal.DimensionDBContext;
import dal.InvoiceDetailDBContext;
import dal.ProductDBContext;
import dal.ProductDetailDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;
import model.Agency;
import model.Buyer;
import model.InvoiceDetail;
import model.Dimension;
import model.Product;


public class InsertInvoiceController extends BaseAuthentication {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BuyerDBContext buyerDB = new BuyerDBContext();
        ArrayList<Buyer> buyers = buyerDB.getBuyers();
        request.setAttribute("buyers", buyers);
        AgencyDBContext agencyDB = new AgencyDBContext();
        ArrayList<Agency> agencies = agencyDB.getAgencies();
        request.setAttribute("agencies", agencies);
        ProductDBContext productDB = new ProductDBContext();
        ArrayList<Product> products = productDB.getProducts();
        request.setAttribute("products", products);
        DimensionDBContext dimensionDB = new DimensionDBContext();
        ArrayList<Dimension> dimensions = dimensionDB.getDimensions();
        request.setAttribute("dimensions", dimensions);
        request.getRequestDispatcher("insertinvoice.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idBuyer_raw = request.getParameter("idBuyer");
        String idProduct_raw = request.getParameter("idProduct");
        String idDimension_raw = request.getParameter("idDimension");
        String date_raw = request.getParameter("date");
        String buyPrice_raw = request.getParameter("buyPrice");
        String quantity_raw = request.getParameter("quantity");
        String discount_raw = request.getParameter("discount");
        String amount_raw = request.getParameter("amount");
        String paid_raw = request.getParameter("paid");
        String owed_raw = request.getParameter("owed");
        String idAgency_raw = request.getParameter("idAgency");
        
        // handling type data
        int idBuyer = Integer.parseInt(idBuyer_raw);
        int idProduct = Integer.parseInt(idProduct_raw);
        int idDimension = Integer.parseInt(idDimension_raw);
        Date date = Date.valueOf(date_raw);
        long buyPrice = Long.parseLong(buyPrice_raw);
        int quantity = Integer.parseInt(quantity_raw);
        int discount = Integer.parseInt(discount_raw);
        long amount = Long.parseLong(amount_raw);
        long paid = Long.parseLong(paid_raw);
        long owed = Long.parseLong(owed_raw);
        int idAgency = Integer.parseInt(idAgency_raw);
        Account account = (Account)request.getSession().getAttribute("account");


        ProductDetailDBContext productDdb = new ProductDetailDBContext();
        boolean isExist = productDdb.checkExistProduct(idProduct, idDimension);
        int totalQuantity;
        if(isExist) {
             totalQuantity = productDdb.getTotalQuantity(idProduct, idDimension);
        } else {
            totalQuantity = 0;
        }
        InvoiceDetail invoiceDetail = new InvoiceDetail();
        invoiceDetail.getInvoiceProduct().getInvoice().getBuyer().setId(idBuyer);
        invoiceDetail.getInvoiceProduct().getInvoice().setDate(date);
        invoiceDetail.getInvoiceProduct().getInvoice().setAmount(amount);
        invoiceDetail.getInvoiceProduct().getInvoice().setPaid(paid);
        invoiceDetail.getInvoiceProduct().getInvoice().setOwed(owed);
        invoiceDetail.getInvoiceProduct().getInvoice().getAgency().setId(idAgency);
        invoiceDetail.getInvoiceProduct().getInvoice().getAccount().setUsername(account.getUsername());
//        invoiceDetail.getInvoice().getBuyer().setId(idBuyer);
//        invoiceDetail.getInvoice().setDate(date);
//        invoiceDetail.getInvoice().setAmount(amount);
//        invoiceDetail.getInvoice().setPaid(paid);
//        invoiceDetail.getInvoice().setOwed(owed);
//        invoiceDetail.getInvoice().getAgency().setId(idAgency);
//        invoiceDetail.getInvoice().getAccount().setUsername(account.getUsername());
        invoiceDetail.getInvoiceProduct().getProductDetail().getProduct().setId(idProduct);
        invoiceDetail.getInvoiceProduct().getProductDetail().getDimension().setId(idDimension);
        invoiceDetail.getInvoiceProduct().setQuantity(quantity);
        invoiceDetail.getInvoiceProduct().setDiscount(discount);
        invoiceDetail.getInvoiceProduct().setBuyPrice(buyPrice);
        InvoiceDetailDBContext db = new InvoiceDetailDBContext();
        db.insertInvoice(invoiceDetail, isExist, totalQuantity);
//        db.insertInvoice(idBuyer, idProduct, idDimension,date, buyPrice, 
//                quantity, discount, amount, paid, owed, idAgency, "admin", isExist, totalQuantity);
        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
