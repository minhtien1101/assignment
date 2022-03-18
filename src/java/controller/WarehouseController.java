/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import controller.authentication.BaseAuthentication;
import dal.DimensionDBContext;
import dal.ProductDBContext;
import dal.ProductDetailDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Dimension;
import model.Product;
import model.ProductDetail;


public class WarehouseController extends BaseAuthentication {

    @Override
    protected void processGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {       
        String idProduct_raw = request.getParameter("idProduct");
        String idDimension_raw = request.getParameter("idDimension");
        
        if(idProduct_raw == null || idProduct_raw.trim().length() == 0) {
            idProduct_raw = "-1";
        }
        if(idDimension_raw == null || idDimension_raw.trim().length() == 0) {
            idDimension_raw = "-1";
        }
        int idProduct = Integer.parseInt(idProduct_raw);
        int idDimension = Integer.parseInt(idDimension_raw);
        request.setAttribute("idProduct", idProduct);
        request.setAttribute("idDimension", idDimension);
        
        ProductDBContext productdb = new ProductDBContext();
        ArrayList<Product> products = productdb.getProducts();
        request.setAttribute("products", products);
        
        DimensionDBContext dimensiondb = new DimensionDBContext();
        ArrayList<Dimension> dimensions = dimensiondb.getDimensions();
        request.setAttribute("dimensions", dimensions);
        
        ProductDetailDBContext db = new ProductDetailDBContext();
        ArrayList<ProductDetail> productsDetail = db.getProductsDetail(idProduct, idDimension);
        request.setAttribute("productsDetail", productsDetail);
        
        request.getRequestDispatcher("warehouse.jsp").forward(request, response);
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
