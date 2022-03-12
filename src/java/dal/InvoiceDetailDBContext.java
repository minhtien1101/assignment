package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DetailInvoice;

public class InvoiceDetailDBContext extends DBContext {

    public ArrayList<DetailInvoice> getInvoiceDetail(int idBuyer, Date dateFrom, Date dateTo, int pageIndex, int pageSize) {
        ArrayList<DetailInvoice> invoicesDetail = new ArrayList<>();
        try {
            String sql = "select * from\n"
                    + "(select ROW_NUMBER() over(order by i.date DESC) as row_index, b.id as idBuyer, b.name as nameBuyer, \n"
                    + "i.id as idInvoice, [ip].idProduct, d.id as idDimension, \n"
                    + "d.name as nameDimension, p.name as nameProduct, i.date, [ip].buyPrice, \n"
                    + "[ip].quantity, i.amount, i.paid, i.owed, ag.id as idAgency, ag.name as nameAgency, \n"
                    + "ag.phone as phoneAgency, ag.address as addressAgency\n"
                    + "from Buyer b join Invoice i on b.id = i.idBuyer\n"
                    + "join Account a on a.username = i.userAccount\n"
                    + "join Agency ag on ag.id = i.idAgency\n"
                    + "join InvoiceProduct [ip] on [ip].idInvoice = i.id\n"
                    + "join ProductDetail pd on pd.idProduct = [ip].idProduct\n"
                    + "join Product p on p.id = pd.idProduct\n"
                    + "join Dimension d on d.id = [ip].idDimension\n";
            if (dateFrom != null) {
                sql += " and date >= ? ";
            }
            if (dateTo != null) {
                sql += " and date <= ? ";
            }
            if (idBuyer != -1) {
                sql += " and idBuyer = ? \n";
            }
            sql += " ) invoiceDetail\n"
                        + "where row_index >= (?-1)*?+1 and row_index <= ? * ?";
            PreparedStatement stm = con.prepareStatement(sql);
            if (dateFrom != null) {
                stm.setDate(1, dateFrom);
            }
            if (dateTo != null && dateFrom == null) {
                stm.setDate(1, dateTo);
            }
            if (dateTo != null && dateFrom != null) {
                stm.setDate(2, dateTo);
            }
            if (dateFrom == null && dateTo == null && idBuyer != -1) {
                stm.setInt(1, idBuyer);
            }
            if ((dateFrom == null && dateTo != null && idBuyer != -1) ||
                   (dateFrom != null && dateTo == null && idBuyer != -1)) {
                stm.setInt(2, idBuyer);
            }
            if (dateFrom != null && dateTo != null && idBuyer != -1) {
                stm.setInt(3, idBuyer);
            }
            if (dateFrom == null && dateTo == null && idBuyer == -1) {
                stm.setInt(1, pageIndex);
                stm.setInt(2, pageSize);
                stm.setInt(3, pageIndex);
                stm.setInt(4, pageSize);
            }
            if ((dateFrom != null && dateTo == null && idBuyer == -1)
                    || (dateFrom == null && dateTo != null && idBuyer == -1)
                    ||(dateFrom == null && dateTo == null && idBuyer != -1)) {
                stm.setInt(2, pageIndex);
                stm.setInt(3, pageSize);
                stm.setInt(4, pageIndex);
                stm.setInt(5, pageSize);
            }
            if (dateFrom != null && dateTo != null && idBuyer == -1) {
                stm.setInt(3, pageIndex);
                stm.setInt(4, pageSize);
                stm.setInt(5, pageIndex);
                stm.setInt(6, pageSize);
            }
            if (dateFrom != null && dateTo != null && idBuyer != -1) {
                stm.setInt(4, pageIndex);
                stm.setInt(5, pageSize);
                stm.setInt(6, pageIndex);
                stm.setInt(7, pageSize);
            }
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                DetailInvoice d = new DetailInvoice();
                d.getInvoice().getBuyer().setId(rs.getInt("idBuyer"));
                d.getInvoice().getBuyer().setName(rs.getString("nameBuyer"));
                d.getInvoice().getAgency().setId(rs.getInt("idAgency"));
                d.getInvoice().getAgency().setName(rs.getString("nameAgency"));
                d.getInvoice().getAgency().setPhone(rs.getString("phoneAgency"));
                d.getInvoice().getAgency().setAddress(rs.getString("addressAgency"));
                d.getInvoice().setId(rs.getInt("idInvoice"));
                d.getInvoice().setDate(rs.getDate("date"));
                d.getInvoice().setAmount(rs.getLong("amount"));
                d.getInvoice().setPaid(rs.getLong("paid"));
                d.getInvoice().setOwed(rs.getLong("owed"));
                d.getProductDetail().getDimension().setId(rs.getInt("idDimension"));
                d.getProductDetail().getDimension().setName(rs.getString("nameDimension"));
                d.getProductDetail().getProduct().setId(rs.getInt("idProduct"));
                d.getProductDetail().getProduct().setName(rs.getString("nameProduct"));
                d.getInvoiceProduct().setBuyPrice(rs.getLong("buyPrice"));
                d.getInvoiceProduct().setQuantity(rs.getInt("quantity"));
                invoicesDetail.add(d);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invoicesDetail;
    }

    public int count(Date dateFrom, Date dateTo, int idBuyer) {
        return 10;
    }

    public ArrayList<DetailInvoice> getAllInvoiceOwed(Date dateFrom, Date dateTo) {
        return null;
    }
}
