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

    public ArrayList<ProductDetail> getProductsDetail(int idProduct, int idDimension) {
        ArrayList<ProductDetail> productsDetail = new ArrayList<>();
        try {
            String sql = "select p.id as idProduct, p.name as nameProduct, "
                    + "d.id as idDimension, d.name as nameDimension, prd.totalQuantity \n"
                    + "from Product p join ProductDetail prd on p.id = prd.idProduct\n"
                    + "join Dimension d on d.id = prd.idDimension\n";
            if(idProduct != -1) {
                sql += " and p.id = ? ";
            }
            if(idDimension != -1) {
                sql += " and d.id = ? ";
            }
            sql += " order by p.id ";
            PreparedStatement stm = con.prepareStatement(sql);
            if(idProduct != -1 && idDimension == -1) {
                stm.setInt(1, idProduct);
            }
            if(idProduct == -1 && idDimension != -1) {
                stm.setInt(1, idDimension);
            }
            if(idProduct != -1 && idDimension != -1) {
                stm.setInt(1, idProduct);
                stm.setInt(2, idDimension);
            }
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

    public boolean checkExistProduct(int idProduct, int idDimension) {
        try {
            String sql = "Select * from ProductDetail\n"
                    + "where idProduct = ? and idDimension = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, idProduct);
            stm.setInt(2, idDimension);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public int getTotalQuantity(int idProduct, int idDimension) {
        try {
            String sql = "select totalQuantity from ProductDetail\n"
                    + "where idProduct = ? and idDimension = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, idProduct);
            stm.setInt(2, idDimension);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("totalQuantity");
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

}
