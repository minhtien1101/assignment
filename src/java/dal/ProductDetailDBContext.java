/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.ProductDetail;

public class ProductDetailDBContext extends DBContext {

    public ArrayList<ProductDetail> getProductsDetail() {
        ArrayList<ProductDetail> productsDetail = new ArrayList<>();
        try {
            String sql = "select p.id as idProduct, p.name as idName, d.id as idDimension, d.name as nameDimension, pd.totalQuantity\n"
                    + "from Product p join ProductDetail pd\n"
                    + "on p.id = pd.idProduct\n"
                    + "join Dimension d on d.id = pd.idDimension\n"
                    + "order by p.name";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                ProductDetail d = new ProductDetail();
                d.getProduct().setId(rs.getInt("idProduct"));
                d.getProduct().setName(rs.getString("nameProduct"));
                d.getDimension().setId(rs.getInt("idDimension"));
                d.getDimension().setName(rs.getString("nameDimension"));
                d.setTotalQuantity(rs.getInt("totalQuantity"));
                productsDetail.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return productsDetail;
    }
}
