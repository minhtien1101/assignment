/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.authentication.BaseAuthentication;
import dal.InvoiceDBContext;
import dal.InvoiceDetailDBContext;
import dal.ProductDetailDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteInvoiceController extends BaseAuthentication {

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

        ProductDetailDBContext productDetailDb = new ProductDetailDBContext();
        int totalQuantity = productDetailDb.getTotalQuantity(idProduct, idDimension);
        int newTotalQuantity = totalQuantity - quantity;
        InvoiceDetailDBContext db = new InvoiceDetailDBContext();
        db.deleteInvoiceDetail(idInvoice, idProduct, idDimension, newTotalQuantity);
        response.sendRedirect("home");
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
