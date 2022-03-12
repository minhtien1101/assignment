
package controller;

import dal.AgencyDBContext;
import dal.BuyerDBContext;
import dal.DimensionDBContext;
import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Agency;
import model.Buyer;
import model.Dimension;
import model.Product;


public class InsertController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
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
        request.getRequestDispatcher("insert.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String idBuyer = request.getParameter("idBuyer");
        String idProduct = request.getParameter("idProduct");
        String idDimension = request.getParameter("idDimension");
        String date = request.getParameter("date");
        String buyPrice = request.getParameter("buyPrice");
        String quantity = request.getParameter("quantity");
        String amount = request.getParameter("amount");
        String paid = request.getParameter("paid");
        String owed = request.getParameter("owed");
        String idAgency = request.getParameter("idAgency");
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
