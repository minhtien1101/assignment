/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

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
import model.Agency;
import model.Buyer;
import model.Dimension;
import model.InvoiceDetail;
import model.Product;


public class UpdateInvoiceController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idInvoice_raw = request.getParameter("idInvoice");
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
        int idInvoice = Integer.parseInt(idInvoice_raw);
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
//        Account acc = (Account)request.getSession().getAttribute("account");
        
        InvoiceDetail newInvoiceDetail = new InvoiceDetail();
        newInvoiceDetail.getInvoice().setId(idInvoice);
        newInvoiceDetail.getInvoice().getBuyer().setId(idBuyer);
        newInvoiceDetail.getInvoice().setDate(date);
        newInvoiceDetail.getInvoice().setAmount(amount);
        newInvoiceDetail.getInvoice().setPaid(paid);
        newInvoiceDetail.getInvoice().setOwed(owed);
        newInvoiceDetail.getInvoice().getAgency().setId(idAgency);
        newInvoiceDetail.getInvoice().getAccount().setUsername("admin");
        newInvoiceDetail.getInvoiceProduct().getProductDetail().getProduct().setId(idProduct);
        newInvoiceDetail.getInvoiceProduct().getProductDetail().getDimension().setId(idDimension);
        newInvoiceDetail.getInvoiceProduct().setQuantity(quantity);
        newInvoiceDetail.getInvoiceProduct().setDiscount(discount);
        newInvoiceDetail.getInvoiceProduct().setBuyPrice(buyPrice);
        
        Integer oldIdProduct_raw = (Integer)request.getSession().getAttribute("idProduct");
        Integer oldIdDimension_raw = (Integer)request.getSession().getAttribute("idDimension");
        Integer oldQuantity_raw = (Integer)request.getSession().getAttribute("quantity");
        int oldIdProduct = oldIdProduct_raw;
        int oldIdDimension = oldIdDimension_raw;
        int oldQuantity = oldQuantity_raw;
        
        // Products that have been purchased or not
        ProductDetailDBContext productDdb = new ProductDetailDBContext();
        boolean isExistOldProduct = productDdb.checkExistProduct(oldIdProduct, oldIdDimension);
        int oldTotalQuantity;
        if(isExistOldProduct) {

            oldTotalQuantity = productDdb.getTotalQuantity(oldIdProduct, oldIdDimension) - oldQuantity;
        } else {
            oldTotalQuantity = 0;
        }
        boolean isExistNewProduct = productDdb.checkExistProduct(idProduct, idDimension);
        
        int newTotalQuantity;
        if(isExistNewProduct) {

            newTotalQuantity = productDdb.getTotalQuantity(idProduct, idDimension);
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
