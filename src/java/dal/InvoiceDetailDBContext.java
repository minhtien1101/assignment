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

    public ArrayList<DetailInvoice> getInvoiceDetail(int idBuyer, Date dateFrom,
            Date dateTo, int pageIndex, int pageSize) {
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
            if ((dateFrom == null && dateTo != null && idBuyer != -1)
                    || (dateFrom != null && dateTo == null && idBuyer != -1)) {
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
                    || (dateFrom == null && dateTo == null && idBuyer != -1)) {
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
                d.getInvoiceProduct().getProductDetail().getDimension().setId(rs.getInt("idDimension"));
                d.getInvoiceProduct().getProductDetail().getDimension().setName(rs.getString("nameDimension"));
                d.getInvoiceProduct().getProductDetail().getProduct().setId(rs.getInt("idProduct"));
                d.getInvoiceProduct().getProductDetail().getProduct().setName(rs.getString("nameProduct"));
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
        try {
            String sql = "select count(*) as total from \n"
                    + "(select * from Invoice i join InvoiceProduct [ip] on i.id = [ip].idInvoice\n"
                    + "";
            if (dateFrom != null) {
                sql += " and date >= ? ";
            }
            if (dateTo != null) {
                sql += " and date <= ? ";
            }
            if (idBuyer != -1) {
                sql += " and idBuyer = ? ";
            }
            PreparedStatement stm = con.prepareStatement(sql);
            if (dateFrom != null && dateTo == null && idBuyer == -1) {
                stm.setDate(1, dateFrom);
            }
            if (dateFrom == null && dateTo != null && idBuyer == -1) {
                stm.setDate(1, dateTo);
            }
            if (dateFrom != null && dateTo != null && idBuyer == -1) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
            }
            if (dateFrom != null && dateTo != null && idBuyer != -1) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
                stm.setInt(3, idBuyer);
            }
            if (dateFrom == null && dateTo == null && idBuyer != -1) {
                stm.setInt(1, idBuyer);
            }
            if (dateFrom != null && dateTo == null && idBuyer != -1) {
                stm.setDate(1, dateFrom);
                stm.setInt(2, idBuyer);

            }
            if (dateFrom == null && dateTo != null && idBuyer != -1) {
                stm.setDate(1, dateTo);
                stm.setInt(2, idBuyer);
            }
            sql += ") total";
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countOwedInvoice(Date dateFrom, Date dateTo, int idBuyer) {
        try {
            String sql = "select count(*) as total from \n"
                    + "(select * from Invoice i join InvoiceProduct [ip] on i.id = [ip].idInvoice\n"
                    + "and owed > 0 \n";
            if (dateFrom != null) {
                sql += " and date >= ? ";
            }
            if (dateTo != null) {
                sql += " and date <= ? ";
            }
            if (idBuyer != -1) {
                sql += " and idBuyer = ? ";
            }
            PreparedStatement stm = con.prepareStatement(sql);
            if (dateFrom != null && dateTo == null && idBuyer == -1) {
                stm.setDate(1, dateFrom);
            }
            if (dateFrom == null && dateTo != null && idBuyer == -1) {
                stm.setDate(1, dateTo);
            }
            if (dateFrom != null && dateTo != null && idBuyer == -1) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
            }
            if (dateFrom != null && dateTo != null && idBuyer != -1) {
                stm.setDate(1, dateFrom);
                stm.setDate(2, dateTo);
                stm.setInt(3, idBuyer);
            }
            if (dateFrom == null && dateTo == null && idBuyer != -1) {
                stm.setInt(1, idBuyer);
            }
            if (dateFrom != null && dateTo == null && idBuyer != -1) {
                stm.setDate(1, dateFrom);
                stm.setInt(2, idBuyer);

            }
            if (dateFrom == null && dateTo != null && idBuyer != -1) {
                stm.setDate(1, dateTo);
                stm.setInt(2, idBuyer);
            }
            sql += ") total";
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;

    }

    public ArrayList<DetailInvoice> getInvoicesOwed(int idBuyer, Date dateFrom,
            Date dateTo, int pageIndex, int pageSize) {
        ArrayList<DetailInvoice> invoicesOwedDetail = new ArrayList<>();
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
                    + "join Dimension d on d.id = [ip].idDimension\n"
                    + "and owed > 0 \n";
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
            if ((dateFrom == null && dateTo != null && idBuyer != -1)
                    || (dateFrom != null && dateTo == null && idBuyer != -1)) {
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
                    || (dateFrom == null && dateTo == null && idBuyer != -1)) {
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
                d.getInvoiceProduct().getProductDetail().getDimension().setId(rs.getInt("idDimension"));
                d.getInvoiceProduct().getProductDetail().getDimension().setName(rs.getString("nameDimension"));
                d.getInvoiceProduct().getProductDetail().getProduct().setId(rs.getInt("idProduct"));
                d.getInvoiceProduct().getProductDetail().getProduct().setName(rs.getString("nameProduct"));
                d.getInvoiceProduct().setBuyPrice(rs.getLong("buyPrice"));
                d.getInvoiceProduct().setQuantity(rs.getInt("quantity"));
                invoicesOwedDetail.add(d);

            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return invoicesOwedDetail;
    }

    public void insertInvoice(DetailInvoice d, boolean isExist, int totalQuantity) {
        try {
            con.setAutoCommit(false);
            String insert_product_detail = "";
            if (!isExist) {
                insert_product_detail += "INSERT INTO [ProductDetail]\n"
                        + "           ([idProduct]\n"
                        + "           ,[idDimension]\n"
                        + "           ,[totalQuantity])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?)";
            } else {
                insert_product_detail += "UPDATE [ProductDetail]\n"
                        + "   SET [totalQuantity] = ?\n"
                        + " WHERE [idProduct] = ? and [idDimension] = ?";
            }

            String insert_invoice = "INSERT INTO [Invoice]\n"
                    + "           ([date]\n"
                    + "           ,[amount]\n"
                    + "           ,[paid]\n"
                    + "           ,[owed]\n"
                    + "           ,[idAgency]\n"
                    + "           ,[idBuyer]\n"
                    + "           ,[userAccount])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            String insert_invoice_product = "INSERT INTO [InvoiceProduct]\n"
                    + "           ([idInvoice]\n"
                    + "           ,[idProduct]\n"
                    + "           ,[idDimension]\n"
                    + "           ,[quantity]\n"
                    + "           ,[buyPrice]\n"
                    + "           ,[discount])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";

            PreparedStatement stm_insert_product_detail = con.prepareStatement(insert_product_detail);
            if (!isExist) {
                stm_insert_product_detail.setInt(1, d.getInvoiceProduct().getProductDetail().getProduct().getId());
                stm_insert_product_detail.setInt(2, d.getInvoiceProduct().getProductDetail().getDimension().getId());
                stm_insert_product_detail.setInt(3, d.getInvoiceProduct().getQuantity());
            } else {
                int total = totalQuantity + d.getInvoiceProduct().getQuantity();
                stm_insert_product_detail.setInt(1, total);
                stm_insert_product_detail.setInt(2, d.getInvoiceProduct().getProductDetail().getProduct().getId());
                stm_insert_product_detail.setInt(3, d.getInvoiceProduct().getProductDetail().getDimension().getId());
            }
            stm_insert_product_detail.executeUpdate();
            PreparedStatement stm_insert_invoice = con.prepareStatement(insert_invoice);
            stm_insert_invoice.setDate(1, d.getInvoice().getDate());
            stm_insert_invoice.setFloat(2, (d.getInvoice().getAmount()
                    - (d.getInvoice().getAmount() * d.getInvoice().getAmount() / 100)));
            stm_insert_invoice.setFloat(3, d.getInvoice().getPaid());
            stm_insert_invoice.setFloat(4, d.getInvoice().getOwed());
            stm_insert_invoice.setInt(5, d.getInvoice().getAgency().getId());
            stm_insert_invoice.setInt(6, d.getInvoice().getBuyer().getId());
            stm_insert_invoice.setString(7, d.getInvoice().getAccount().getUsername());
            stm_insert_invoice.executeUpdate();

            int idInvoice = 0;
            // lữu trữ tạm bản ghi và lấy id
            String sql_idinvoice = "Select @@IDENTITY as id";
            PreparedStatement stm_getIdInvoice = con.prepareStatement(sql_idinvoice);
            ResultSet rs = stm_getIdInvoice.executeQuery();
            if (rs.next()) {
                idInvoice = rs.getInt(1);
            }
            PreparedStatement stm_insert_invoice_product = con.prepareStatement(insert_invoice_product);
            stm_insert_invoice_product.setInt(1, idInvoice);
            stm_insert_invoice_product.setInt(2, d.getInvoiceProduct().getProductDetail().getProduct().getId());
            stm_insert_invoice_product.setInt(3, d.getInvoiceProduct().getProductDetail().getDimension().getId());
            stm_insert_invoice_product.setInt(4, d.getInvoiceProduct().getQuantity());
            stm_insert_invoice_product.setFloat(5, d.getInvoiceProduct().getBuyPrice());
            stm_insert_invoice_product.setInt(6, d.getInvoiceProduct().getDiscount());
            stm_insert_invoice_product.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } finally {
            try {
                con.setAutoCommit(true);
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                con.close();
            } catch (SQLException ex) {
                Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

}
