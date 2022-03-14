
package controller;

import dal.ProductDBContext;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Product;


public class InsertProductController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("../insertproduct.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        Product product = new Product();
        product.setName(name);
        ProductDBContext db = new ProductDBContext();
        db.insertProduct(product);
        response.sendRedirect("../insert");
    }


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
