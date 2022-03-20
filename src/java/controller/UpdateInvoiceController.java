/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import model.Dimension;
import model.InvoiceDetail;
import model.Product;


public class UpdateInvoiceController extends BaseAuthentication {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idInvoice_raw = request.getParameter("idinvoice");
        String idProduct_raw = request.getParameter("idproduct");
        String idDimension_raw = request.getParameter("iddimension");
        String quantity_raw = request.getParameter("quantity");
        
        int idInvoice = Integer.parseInt(idInvoice_raw);
        int idProduct = Integer.parseInt(idProduct_raw);
        int idDimension = Integer.parseInt(idDimension_raw);
        int quantity = Integer.parseInt(quantity_raw);
        
        request.getSession().setAttribute("idProduct", idProduct);
        request.getSession().setAttribute("idDimension", idDimension);
        request.getSession().setAttribute("quantity", quantity);
        
        
        BuyerDBContext buyerDb = new BuyerDBContext();
        ArrayList<Buyer> buyers = buyerDb.getBuyers();
        request.setAttribute("buyers", buyers);
        
        AgencyDBContext agencyDb = new AgencyDBContext();
        ArrayList<Agency> agencies = agencyDb.getAgencies();
        request.setAttribute("agencies", agencies);
        
        ProductDBContext productDb = new ProductDBContext();
        ArrayList<Product> products = productDb.getProducts();
        request.setAttribute("products", products);
        
        DimensionDBContext dimensionDb = new DimensionDBContext();
        ArrayList<Dimension> dimensions = dimensionDb.getDimensions();
        request.setAttribute("dimensions", dimensions);
                
        InvoiceDetailDBContext invoiceDetaildb = new InvoiceDetailDBContext();
        InvoiceDetail invoiceDetail = invoiceDetaildb.getInvoiceDetail(idInvoice, idProduct, idDimension);
        request.setAttribute("invoiceDetail", invoiceDetail);
        request.getRequestDispatcher("updateinvoice.jsp").forward(request, response);
    }

    @Override
    protected void processPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idInvoice_raw = request.getParameter("idInvoice");
        String idBuyer_raw = request.getParameter("idBuyer");
        String idProduct_raw = request.getParameter("idProduct");
        String idDimension_raw = request.getParameter("idDimension");
        String date_raw = request.getParameter("date");
        String buyPrice_raw = request.getParameter("buyPrice");
        String quantity_raw = request.getParameter("quantity");
        String amount_raw = request.getParameter("amount");
        String paid_raw = request.getParameter("paid");
        String owed_raw = request.getParameter("owed");
        String idAgency_raw = request.getParameter("idAgency");
        
        int idInvoice = Integer.parseInt(idInvoice_raw);
        int idBuyer = Integer.parseInt(idBuyer_raw);
        int idProduct = Integer.parseInt(idProduct_raw);
        int idDimension = Integer.parseInt(idDimension_raw);
        Date date = Date.valueOf(date_raw);
        long buyPrice = Long.parseLong(buyPrice_raw);
        int quantity = Integer.parseInt(quantity_raw);
        long amount = Long.parseLong(amount_raw);
        long paid = Long.parseLong(paid_raw);
        long owed = Long.parseLong(owed_raw);
        int idAgency = Integer.parseInt(idAgency_raw);
        Account account = (Account)request.getSession().getAttribute("account");
        
        InvoiceDetail newInvoiceDetail = new InvoiceDetail();
        newInvoiceDetail.getInvoiceProduct().getInvoice().setId(idInvoice);
        newInvoiceDetail.getInvoiceProduct().getInvoice().getBuyer().setId(idBuyer);
        newInvoiceDetail.getInvoiceProduct().getInvoice().setDate(date);
        newInvoiceDetail.getInvoiceProduct().getInvoice().setAmount(amount);
        newInvoiceDetail.getInvoiceProduct().getInvoice().setPaid(paid);
        newInvoiceDetail.getInvoiceProduct().getInvoice().setOwed(owed);
        newInvoiceDetail.getInvoiceProduct().getInvoice().getAgency().setId(idAgency);
        newInvoiceDetail.getInvoiceProduct().getInvoice().getAccount().setUsername(account.getUsername());

        newInvoiceDetail.getInvoiceProduct().getProductDetail().getProduct().setId(idProduct);
        newInvoiceDetail.getInvoiceProduct().getProductDetail().getDimension().setId(idDimension);
        newInvoiceDetail.getInvoiceProduct().setQuantity(quantity);
        newInvoiceDetail.getInvoiceProduct().setBuyPrice(buyPrice);
        
        int oldIdProduct = (Integer)request.getSession().getAttribute("idProduct");
        int oldIdDimension = (Integer)request.getSession().getAttribute("idDimension");
        int oldQuantity = (Integer)request.getSession().getAttribute("quantity");
        
        // Products that have been purchased or not
        ProductDetailDBContext productDetaildb = new ProductDetailDBContext();
        boolean isExistOldProduct = productDetaildb.checkExistProduct(oldIdProduct, oldIdDimension);
        int oldTotalQuantity;
        if(isExistOldProduct) {

            oldTotalQuantity = productDetaildb.getTotalQuantity(oldIdProduct, oldIdDimension) - oldQuantity;
        } else {
            oldTotalQuantity = 0;
        }
        boolean isExistNewProduct = productDetaildb.checkExistProduct(idProduct, idDimension);
        
        int newTotalQuantity;
        if(isExistNewProduct) {

            newTotalQuantity = productDetaildb.getTotalQuantity(idProduct, idDimension);
        } else {
            newTotalQuantity = 0;
        }
        InvoiceDetailDBContext db = new InvoiceDetailDBContext();
        db.updateInvoiceDetail(newInvoiceDetail, newTotalQuantity ,oldTotalQuantity, oldIdProduct, oldIdDimension, isExistNewProduct);
        response.sendRedirect("home");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
