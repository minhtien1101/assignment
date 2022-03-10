
package controller;

import dal.AgencyDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agency;
import model.Product;


public class InsertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AgencyDBContext agencyDB = new AgencyDBContext();
        ArrayList<Agency> agencies = agencyDB.getAgencies();
        request.setAttribute("agencies", agencies);
        ProductDBContext productDB = new ProductDBContext();
        ArrayList<Product> products = productDB.getProducts();
        request.setAttribute("products", products);
        request.getRequestDispatcher("insert.jsp").forward(request, response);
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
