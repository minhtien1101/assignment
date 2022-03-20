package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.InvoiceDetail;

public class InvoiceDetailDBContext extends DBContext {

    public ArrayList<InvoiceDetail> getInvoicesDetail(int idBuyer, int idProduct,int idDimension, Date dateFrom, Date dateTo, 
            int pageIndex, int pageSize, boolean isOwedInvoice) {
        ArrayList<InvoiceDetail> invoicesDetail = new ArrayList<>();
        try {
            String sql = "select * from\n"
                    + "(select ROW_NUMBER() over(order by i.id DESC, i.date DESC) as row_index, b.id as idBuyer, b.name as nameBuyer, \n"
                    + "b.phone as phoneBuyer, b.gender, b.dob, b.address as addressBuyer, \n"
                    + "i.id as idInvoice, [ip].idProduct, d.id as idDimension, \n"
                    + "d.name as nameDimension, p.name as nameProduct, i.date, [ip].buyPrice, \n"
                    + "[ip].quantity, i.amount, i.paid, i.owed, ag.id as idAgency, ag.name as nameAgency, \n"
                    + "ag.phone as phoneAgency, ag.address as addressAgency, a.username\n"
                    + "from Buyer b join Invoice i on b.id = i.idBuyer\n"
                    + "join Account a on a.username = i.userAccount\n"
                    + "join Agency ag on ag.id = i.idAgency\n"
                    + "join InvoiceProduct [ip] on [ip].idInvoice = i.id\n"
                    + "join ProductDetail pd on pd.idProduct = [ip].idProduct \n"
                    + "and pd.idDimension = [ip].idDimension\n"
                    + "join Product p on p.id = pd.idProduct\n"
                    + "join Dimension d on d.id = [ip].idDimension\n";
            if(isOwedInvoice) {
                sql += " and i.owed > 0 ";
            }
            if (dateFrom != null) {
                sql += " and date >= ? ";
            }
            if (dateTo != null) {
                sql += " and date <= ? ";
            }
            if (idBuyer != -1) {
                sql += " and idBuyer = ? \n";
            }
            
            if (idProduct != -1) {
                sql += " and [ip].idProduct = ? \n";
            }
            if (idDimension != -1) {
                sql += " and d.id = ? \n";
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
            
            if(dateFrom == null && dateTo == null && idBuyer == -1 && idProduct != -1) {
                stm.setInt(1, idProduct);
            }
            if((dateFrom != null && dateTo == null && idBuyer == -1 && idProduct != -1)
                    ||(dateFrom == null && dateTo != null && idBuyer == -1 && idProduct != -1)
                    || (dateFrom == null && dateTo == null && idBuyer != -1 && idProduct != -1)) {
                stm.setInt(2, idProduct);
            }
            if((dateFrom != null && dateTo != null && idBuyer == -1 && idProduct != -1)
                    ||(dateFrom != null && dateTo == null && idBuyer != -1 && idProduct != -1)
                    || (dateFrom == null && dateTo != null && idBuyer != -1 && idProduct != -1)) {
                stm.setInt(3, idProduct);
            }
            if(dateFrom != null && dateTo != null && idBuyer != -1 && idProduct != -1) {
                stm.setInt(4, idProduct);
            }
            
            if(dateFrom == null && dateTo == null && idBuyer == -1 && idProduct == -1
                    && idDimension != -1) {
                stm.setInt(1, idDimension);
            }
            
            if((dateFrom != null && dateTo == null && idBuyer == -1 && idProduct == -1
                    && idDimension != -1)
                    ||(dateFrom == null && dateTo != null && idBuyer == -1 && idProduct == -1
                    && idDimension != -1)
                    ||(dateFrom == null && dateTo == null && idBuyer != -1 && idProduct == -1
                    && idDimension != -1)
                    ||(dateFrom == null && dateTo == null && idBuyer == -1 && idProduct != -1
                    && idDimension != -1) ) {
                stm.setInt(2, idDimension);
            }
            if((dateFrom != null && dateTo != null && idBuyer == -1 && idProduct == -1
                    && idDimension != -1)
                    ||(dateFrom != null && dateTo == null && idBuyer != -1 && idProduct == -1
                    && idDimension != -1)
                    ||(dateFrom != null && dateTo == null && idBuyer == -1 && idProduct != -1
                    && idDimension != -1)
                    ||(dateFrom == null && dateTo != null && idBuyer != -1 && idProduct == -1
                    && idDimension != -1) 
                    ||(dateFrom == null && dateTo != null && idBuyer == -1 && idProduct != -1
                    && idDimension != -1) 
                    ||(dateFrom == null && dateTo == null && idBuyer != -1 && idProduct != -1
                    && idDimension != -1) 
                    ) {
                stm.setInt(3, idDimension);
            }
            
            if((dateFrom != null && dateTo != null && idBuyer != -1 && idProduct == -1
                    && idDimension != -1)
                    ||(dateFrom != null && dateTo != null && idBuyer == -1 && idProduct != -1
                    && idDimension != -1)
                    || (dateFrom != null && dateTo == null && idBuyer != -1 && idProduct != -1
                    && idDimension != -1)
                    || (dateFrom == null && dateTo != null && idBuyer != -1 && idProduct != -1
                    && idDimension != -1)
                    ) {
                stm.setInt(4, idDimension);
            }
            
            if(dateFrom != null && dateTo != null && idBuyer != -1 && idProduct != -1
                    && idDimension != -1) {
                stm.setInt(5, idDimension);
            }
            
            if (dateFrom == null && dateTo == null && idBuyer == -1 &&
                    idProduct == -1 && idDimension == -1) {
                stm.setInt(1, pageIndex);
                stm.setInt(2, pageSize);
                stm.setInt(3, pageIndex);
                stm.setInt(4, pageSize);
            }
            if ((dateFrom != null && dateTo == null && idBuyer == -1 &&
                    idProduct == -1 && idDimension == -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer == -1 &&
                    idProduct == -1 && idDimension == -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 &&
                    idProduct == -1 && idDimension == -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer == -1 &&
                    idProduct != -1 && idDimension == -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer == -1 &&
                    idProduct == -1 && idDimension != -1)
                    ) {
                stm.setInt(2, pageIndex);
                stm.setInt(3, pageSize);
                stm.setInt(4, pageIndex);
                stm.setInt(5, pageSize);
            }
            if ((dateFrom != null && dateTo != null && idBuyer == -1 &&
                    idProduct == -1 && idDimension == -1)   
                    ||
                    (dateFrom != null && dateTo == null && idBuyer != -1 &&
                    idProduct == -1 && idDimension == -1)  
                    ||
                    (dateFrom != null && dateTo == null && idBuyer == -1 &&
                    idProduct != -1 && idDimension == -1) 
                    ||
                    (dateFrom != null && dateTo == null && idBuyer == -1 &&
                    idProduct == -1 && idDimension != -1)  
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 &&
                    idProduct == -1 && idDimension == -1)  
                    ||
                    (dateFrom == null && dateTo != null && idBuyer == -1 &&
                    idProduct != -1 && idDimension == -1) 
                    || 
                    (dateFrom == null && dateTo != null && idBuyer == -1 &&
                    idProduct == -1 && idDimension != -1)  
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 &&
                    idProduct != -1 && idDimension == -1)  
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 &&
                    idProduct == -1 && idDimension != -1)  
                    ||
                    (dateFrom == null && dateTo == null && idBuyer == -1 &&
                    idProduct != -1 && idDimension != -1)
                    ) {
                stm.setInt(3, pageIndex);
                stm.setInt(4, pageSize);
                stm.setInt(5, pageIndex);
                stm.setInt(6, pageSize);
            }
            if ((dateFrom != null && dateTo != null && idBuyer != -1 &&
                    idProduct == -1 && idDimension == -1)   
                    ||
                    (dateFrom != null && dateTo != null && idBuyer == -1 &&
                    idProduct != -1 && idDimension == -1)
                    ||
                    (dateFrom != null && dateTo != null && idBuyer == -1 &&
                    idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer != -1 &&
                    idProduct != -1 && idDimension == -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer != -1 &&
                    idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer == -1 &&
                    idProduct != -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 &&
                    idProduct != -1 && idDimension == -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 &&
                    idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 &&
                    idProduct != -1 && idDimension != -1)
                    ) {
                stm.setInt(4, pageIndex);
                stm.setInt(5, pageSize);
                stm.setInt(6, pageIndex);
                stm.setInt(7, pageSize);
            }
            
            if ((dateFrom != null && dateTo != null && idBuyer != -1 &&
                    idProduct != -1 && idDimension == -1)   
                    ||
                    (dateFrom != null && dateTo != null && idBuyer != -1 &&
                    idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo != null && idBuyer == -1 &&
                    idProduct != -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer != -1 &&
                    idProduct != -1 && idDimension != -1)   
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 &&
                    idProduct != -1 && idDimension != -1)   
                    ) {
                stm.setInt(5, pageIndex);
                stm.setInt(6, pageSize);
                stm.setInt(7, pageIndex);
                stm.setInt(8, pageSize);
            }
            if ((dateFrom != null && dateTo != null && idBuyer != -1 &&
                    idProduct != -1 && idDimension != -1)                  
                    ) {
                stm.setInt(6, pageIndex);
                stm.setInt(7, pageSize);
                stm.setInt(8, pageIndex);
                stm.setInt(9, pageSize);
            }
            
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                InvoiceDetail d = new InvoiceDetail();
                d.getInvoiceProduct().getInvoice().getBuyer().setId(rs.getInt("idBuyer"));
                d.getInvoiceProduct().getInvoice().getBuyer().setName(rs.getString("nameBuyer"));
                d.getInvoiceProduct().getInvoice().getBuyer().setPhone(rs.getString("phoneBuyer"));
                d.getInvoiceProduct().getInvoice().getBuyer().setDob(rs.getDate("dob"));
                d.getInvoiceProduct().getInvoice().getBuyer().setGender(rs.getBoolean("gender"));
                d.getInvoiceProduct().getInvoice().getBuyer().setAddress(rs.getString("addressBuyer"));
                d.getInvoiceProduct().getInvoice().getAgency().setId(rs.getInt("idAgency"));
                d.getInvoiceProduct().getInvoice().getAgency().setName(rs.getString("nameAgency"));
                d.getInvoiceProduct().getInvoice().getAgency().setPhone(rs.getString("phoneAgency"));
                d.getInvoiceProduct().getInvoice().getAgency().setAddress(rs.getString("addressAgency"));
                d.getInvoiceProduct().getInvoice().getAccount().setUsername(rs.getString("username"));
                d.getInvoiceProduct().getInvoice().setId(rs.getInt("idInvoice"));
                d.getInvoiceProduct().getInvoice().setDate(rs.getDate("date"));
                d.getInvoiceProduct().getInvoice().setAmount(rs.getLong("amount"));
                d.getInvoiceProduct().getInvoice().setPaid(rs.getLong("paid"));
                d.getInvoiceProduct().getInvoice().setOwed(rs.getLong("owed"));

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

    public int count(Date dateFrom, Date dateTo, int idBuyer, int idProduct, int idDimension, boolean isOwedInvoice) {

        
        try {
            String sql = "select count(*) as total from \n"
                    + "(select * from Invoice i join InvoiceProduct [ip] on i.id = [ip].idInvoice\n"
                    + "";
            if(isOwedInvoice) {
                sql += " and i.owed > 0 ";
            }
            if (dateFrom != null) {
                sql += " and date >= ? ";
            }
            if (dateTo != null) {
                sql += " and date <= ? ";
            }
            if (idBuyer != -1) {
                sql += " and idBuyer = ? ";
            }
            if (idProduct != -1) {
                sql += " and [ip].idProduct = ? ";
            }
            if (idDimension != -1) {
                sql += " and [ip].idDimension = ? ";
            }
            sql += ") totalInvoice";
            PreparedStatement stm = con.prepareStatement(sql);
            if(dateFrom != null) {
                stm.setDate(1, dateFrom);
            }
            if(dateFrom == null && dateTo != null) {
                stm.setDate(1, dateTo);
            }
            if(dateFrom != null && dateTo != null) {
                stm.setDate(2, dateTo);
            }
            if(dateFrom == null && dateTo == null && idBuyer != -1) {
                stm.setInt(1, idBuyer);
            }
            if((dateFrom != null && dateTo == null && idBuyer != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1)) {
                stm.setInt(2, idBuyer);
            }
            if((dateFrom != null && dateTo != null && idBuyer != -1)) {
                stm.setInt(3, idBuyer);
            }
            if(dateFrom == null && dateTo == null && idBuyer == -1 && idProduct != -1) {
                stm.setInt(1, idProduct);
            }
            if((dateFrom != null && dateTo == null && idBuyer == -1 && idProduct != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer == -1 && idProduct != -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 && idProduct != -1)
                    ) {
                stm.setInt(2, idProduct);
            }
            if((dateFrom != null && dateTo != null && idBuyer == -1 && idProduct != -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer != -1 && idProduct != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 && idProduct != -1)
                    ) {
                stm.setInt(3, idProduct);
            }
            if((dateFrom != null && dateTo != null && idBuyer != -1 && idProduct != -1)) {
                stm.setInt(4, idProduct);
            }
            
            if(dateFrom == null && dateTo == null && idBuyer == -1 && idProduct == -1 && idDimension != -1) {
                stm.setInt(1, idDimension);
            }
            if((dateFrom != null && dateTo == null && idBuyer == -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer == -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer == -1 && idProduct != -1 && idDimension != -1)
                    ) {
                stm.setInt(2, idDimension);
            }
            
            if((dateFrom != null && dateTo != null && idBuyer == -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer != -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo == null && idBuyer == -1 && idProduct != -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer == -1 && idProduct != -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo == null && idBuyer != -1 && idProduct != -1 && idDimension != -1)
                    ) {
                stm.setInt(3, idDimension);
            }
            if((dateFrom != null && dateTo != null && idBuyer != -1 && idProduct == -1 && idDimension != -1)
                    ||
                    (dateFrom != null && dateTo != null && idBuyer == -1 && idProduct != -1 && idDimension != -1)
                    ||
                    (dateFrom == null && dateTo != null && idBuyer != -1 && idProduct != -1 && idDimension != -1)
                    ) {
                stm.setInt(4, idDimension);
            }
            
            if(dateFrom != null && dateTo != null && idBuyer != -1 && idProduct != -1 && idDimension != -1) {
                stm.setInt(5, idDimension);
            }
            
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public void insertInvoice(InvoiceDetail invoiceDetail, boolean isExist, int totalQuantity) {
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
            PreparedStatement stm_insert_product_detail = con.prepareStatement(insert_product_detail);
            if (!isExist) {
                stm_insert_product_detail.setInt(1, invoiceDetail.getInvoiceProduct().getProductDetail().getProduct().getId());
                stm_insert_product_detail.setInt(2, invoiceDetail.getInvoiceProduct().getProductDetail().getDimension().getId());
                stm_insert_product_detail.setInt(3, invoiceDetail.getInvoiceProduct().getQuantity());
            } else {
                int total = totalQuantity + invoiceDetail.getInvoiceProduct().getQuantity();
                stm_insert_product_detail.setInt(1, total);
                stm_insert_product_detail.setInt(2, invoiceDetail.getInvoiceProduct().getProductDetail().getProduct().getId());
                stm_insert_product_detail.setInt(3, invoiceDetail.getInvoiceProduct().getProductDetail().getDimension().getId());
            }
            stm_insert_product_detail.executeUpdate();
            
            
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
  
            PreparedStatement stm_insert_invoice = con.prepareStatement(insert_invoice);
            stm_insert_invoice.setDate(1, invoiceDetail.getInvoiceProduct().getInvoice().getDate());
            stm_insert_invoice.setFloat(2, invoiceDetail.getInvoiceProduct().getInvoice().getAmount());
            stm_insert_invoice.setFloat(3, invoiceDetail.getInvoiceProduct().getInvoice().getPaid());
            stm_insert_invoice.setFloat(4, invoiceDetail.getInvoiceProduct().getInvoice().getOwed());
            stm_insert_invoice.setInt(5, invoiceDetail.getInvoiceProduct().getInvoice().getAgency().getId());
            stm_insert_invoice.setInt(6, invoiceDetail.getInvoiceProduct().getInvoice().getBuyer().getId());
            stm_insert_invoice.setString(7, invoiceDetail.getInvoiceProduct().getInvoice().getAccount().getUsername());

            stm_insert_invoice.executeUpdate();

            int idInvoice = 0;
            
            String sql_idinvoice = "Select @@IDENTITY as id";
            PreparedStatement stm_getIdInvoice = con.prepareStatement(sql_idinvoice);
            ResultSet rs = stm_getIdInvoice.executeQuery();
            if (rs.next()) {
                idInvoice = rs.getInt(1);
            }
            
            String insert_invoice_product = "INSERT INTO [InvoiceProduct]\n"
                    + "           ([idInvoice]\n"
                    + "           ,[idProduct]\n"
                    + "           ,[idDimension]\n"
                    + "           ,[quantity]\n"
                    + "           ,[buyPrice])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            
            PreparedStatement stm_insert_invoice_product = con.prepareStatement(insert_invoice_product);
            stm_insert_invoice_product.setInt(1, idInvoice);
            stm_insert_invoice_product.setInt(2, invoiceDetail.getInvoiceProduct().getProductDetail().getProduct().getId());
            stm_insert_invoice_product.setInt(3, invoiceDetail.getInvoiceProduct().getProductDetail().getDimension().getId());
            stm_insert_invoice_product.setInt(4, invoiceDetail.getInvoiceProduct().getQuantity());
            stm_insert_invoice_product.setFloat(5, invoiceDetail.getInvoiceProduct().getBuyPrice());
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

    public void deleteInvoiceDetail(int idInvoice, int idProduct, int idDimension, int newTotalQuantity) {
        try {
            con.setAutoCommit(false);
            String delete_invoice_product = "DELETE FROM [InvoiceProduct]\n"
                    + "      WHERE [idInvoice] = ?";
            PreparedStatement stm_delete_invoice_product = con.prepareStatement(delete_invoice_product);
            stm_delete_invoice_product.setInt(1, idInvoice);
            stm_delete_invoice_product.executeUpdate();

            String delete_invoice = "DELETE FROM [Invoice]\n"
                    + "      WHERE [id] = ?";
            PreparedStatement stm_delete_invoice = con.prepareStatement(delete_invoice);
            stm_delete_invoice.setInt(1, idInvoice);
            stm_delete_invoice.executeUpdate();

            String update_total_quantity_product = "UPDATE [ProductDetail]\n"
                    + "   SET \n"
                    + "      [totalQuantity] = ?\n"
                    + " WHERE [idProduct] = ? and [idDimension] = ?";
            PreparedStatement stm_update_total_quantity_product = con.prepareStatement(update_total_quantity_product);
            stm_update_total_quantity_product.setInt(1, newTotalQuantity);
            stm_update_total_quantity_product.setInt(2, idProduct);
            stm_update_total_quantity_product.setInt(3, idDimension);
            stm_update_total_quantity_product.executeUpdate();

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

    public InvoiceDetail getInvoiceDetail(int idInvoice, int idProduct, int idDimension) {
        try {
            String sql = "select  b.id as idBuyer, b.name as nameBuyer, \n"
                    + "i.id as idInvoice, [ip].idProduct, d.id as idDimension,\n"
                    + "d.name as nameDimension, p.name as nameProduct, i.date, [ip].buyPrice,\n"
                    + "[ip].quantity, i.amount, i.paid, i.owed, ag.id as idAgency, ag.name as nameAgency, \n"
                    + "ag.phone as phoneAgency, ag.address as addressAgency\n"
                    + "from Buyer b join Invoice i on b.id = i.idBuyer\n"
                    + "join Account a on a.username = i.userAccount\n"
                    + "join Agency ag on ag.id = i.idAgency\n"
                    + "join InvoiceProduct [ip] on [ip].idInvoice = i.id\n"
                    + "join ProductDetail pd on pd.idProduct = [ip].idProduct\n"
                    + "join Product p on p.id = pd.idProduct\n"
                    + "join Dimension d on d.id = [ip].idDimension\n"
                    + "and [ip].idProduct = ? and d.id = ? and idInvoice = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, idProduct);
            stm.setInt(2, idDimension);
            stm.setInt(3, idInvoice);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                InvoiceDetail d = new InvoiceDetail();
                d.getInvoiceProduct().getInvoice().getBuyer().setId(rs.getInt("idBuyer"));
                d.getInvoiceProduct().getInvoice().getBuyer().setName(rs.getString("nameBuyer"));
                d.getInvoiceProduct().getInvoice().getAgency().setId(rs.getInt("idAgency"));
                d.getInvoiceProduct().getInvoice().getAgency().setName(rs.getString("nameAgency"));
                d.getInvoiceProduct().getInvoice().getAgency().setPhone(rs.getString("phoneAgency"));
                d.getInvoiceProduct().getInvoice().getAgency().setAddress(rs.getString("addressAgency"));
                d.getInvoiceProduct().getInvoice().setId(rs.getInt("idInvoice"));
                d.getInvoiceProduct().getInvoice().setDate(rs.getDate("date"));
                d.getInvoiceProduct().getInvoice().setAmount(rs.getLong("amount"));
                d.getInvoiceProduct().getInvoice().setPaid(rs.getLong("paid"));
                d.getInvoiceProduct().getInvoice().setOwed(rs.getLong("owed"));
                d.getInvoiceProduct().getProductDetail().getDimension().setId(rs.getInt("idDimension"));
                d.getInvoiceProduct().getProductDetail().getDimension().setName(rs.getString("nameDimension"));
                d.getInvoiceProduct().getProductDetail().getProduct().setId(rs.getInt("idProduct"));
                d.getInvoiceProduct().getProductDetail().getProduct().setName(rs.getString("nameProduct"));
                d.getInvoiceProduct().setBuyPrice(rs.getLong("buyPrice"));
                d.getInvoiceProduct().setQuantity(rs.getInt("quantity"));
                return d;
            }
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDetailDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateInvoiceDetail(InvoiceDetail newInvoiceDetail, int newTotalQuantity,
            int oldTotalQuantity, int oldIdProduct, int oldIdDimension, boolean isExistNewProduct) {
        try {
            con.setAutoCommit(false);
            String update_invoice = "UPDATE [dbo].[Invoice]\n"
                    + "   SET [date] = ?\n"
                    + "      ,[amount] = ?\n"
                    + "      ,[paid] = ?\n"
                    + "      ,[owed] = ?\n"
                    + "      ,[idAgency] = ?\n"
                    + "      ,[idBuyer] = ?\n"
                    + "      ,[userAccount] = ?\n"
                    + " WHERE [id] = ?";
            PreparedStatement stm_update_invoice = con.prepareStatement(update_invoice);
            stm_update_invoice.setDate(1, newInvoiceDetail.getInvoiceProduct().getInvoice().getDate());
            stm_update_invoice.setFloat(2, newInvoiceDetail.getInvoiceProduct().getInvoice().getAmount());
            stm_update_invoice.setFloat(3, newInvoiceDetail.getInvoiceProduct().getInvoice().getPaid());
            stm_update_invoice.setFloat(4, newInvoiceDetail.getInvoiceProduct().getInvoice().getOwed());
            stm_update_invoice.setInt(5, newInvoiceDetail.getInvoiceProduct().getInvoice().getAgency().getId());
            stm_update_invoice.setInt(6, newInvoiceDetail.getInvoiceProduct().getInvoice().getBuyer().getId());
            stm_update_invoice.setString(7, newInvoiceDetail.getInvoiceProduct().getInvoice().getAccount().getUsername());
            stm_update_invoice.setInt(8, newInvoiceDetail.getInvoiceProduct().getInvoice().getId());
            stm_update_invoice.executeUpdate();
            System.out.println("b1");

            String update_product_detail = "";
            if (isExistNewProduct) {
                int newIdProduct = newInvoiceDetail.getInvoiceProduct().getProductDetail().getProduct().getId();
                int newIdDimension = newInvoiceDetail.getInvoiceProduct().getProductDetail().getDimension().getId();
                if ((oldIdProduct == newIdProduct) && (oldIdDimension == newIdDimension)) {
                    update_product_detail = "UPDATE [ProductDetail]\n"
                            + "   SET \n"
                            + "      [totalQuantity] = ?\n"
                            + " WHERE [idProduct] = ?\n"
                            + "      and [idDimension] = ?";
                } else {
                    update_product_detail = "UPDATE [ProductDetail]\n"
                            + "   SET \n"
                            + "      [totalQuantity] = ?\n"
                            + " WHERE [idProduct] = ?\n"
                            + "      and [idDimension] = ?";
                }
            } else {
                update_product_detail = "INSERT INTO [ProductDetail]\n"
                        + "           ([idProduct]\n"
                        + "           ,[idDimension]\n"
                        + "           ,[totalQuantity])\n"
                        + "     VALUES\n"
                        + "           (?\n"
                        + "           ,?\n"
                        + "           ,?)";
            }
            PreparedStatement stm_update_product_detail = con.prepareStatement(update_product_detail);
            int quantityProduct = newInvoiceDetail.getInvoiceProduct().getQuantity();
            int newIdProduct = newInvoiceDetail.getInvoiceProduct().getProductDetail().getProduct().getId();
            int newIdDimension = newInvoiceDetail.getInvoiceProduct().getProductDetail().getDimension().getId();
            // update new product
            // new product has exist
            if (isExistNewProduct) {
                // new product is still old product
                if ((oldIdProduct == newIdProduct) && (oldIdDimension == newIdDimension)) {
                    stm_update_product_detail.setInt(1, oldTotalQuantity + quantityProduct);
                    stm_update_product_detail.setInt(2, oldIdProduct);
                    stm_update_product_detail.setInt(3, oldIdDimension);

                } 
                // new product is not old product
                else {
                    stm_update_product_detail.setInt(1, newTotalQuantity + quantityProduct);
                    stm_update_product_detail.setInt(2, newIdProduct);
                    stm_update_product_detail.setInt(3, newIdDimension);
                }
            } 
            // new product not exist
            else {
                stm_update_product_detail.setInt(1, newIdProduct);
                stm_update_product_detail.setInt(2, newIdDimension);
                stm_update_product_detail.setInt(3, quantityProduct);
            }
            stm_update_product_detail.executeUpdate();
            System.out.println("b2");
            String update_quantity_old_product_detail = "UPDATE [ProductDetail]\n"
                            + "   SET \n"
                            + "      [totalQuantity] = ?\n"
                            + " WHERE [idProduct] = ?\n"
                            + "      and [idDimension] = ?";
            if(!isExistNewProduct || !((oldIdProduct == newIdProduct) && (oldIdDimension == newIdDimension))) {
                PreparedStatement stm_update_quantity_old_product_detail = con.prepareStatement(update_quantity_old_product_detail);
                stm_update_quantity_old_product_detail.setInt(1, oldTotalQuantity);
                stm_update_quantity_old_product_detail.setInt(2, oldIdProduct);
                stm_update_quantity_old_product_detail.setInt(3, oldIdDimension);
                stm_update_quantity_old_product_detail.executeUpdate();
            }

            String update_invoice_detail = "UPDATE [dbo].[InvoiceProduct]\n"
                    + "   SET \n"
                    + "      [idProduct] = ?\n"
                    + "      ,[idDimension] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[buyPrice] = ?\n"
                    + " WHERE [idInvoice] = ?";
            PreparedStatement stm_update_invoice_detail = con.prepareStatement(update_invoice_detail);
            stm_update_invoice_detail.setInt(1, newIdProduct);
            stm_update_invoice_detail.setInt(2, newIdDimension);
            stm_update_invoice_detail.setInt(3, quantityProduct);
            stm_update_invoice_detail.setFloat(4, newInvoiceDetail.getInvoiceProduct().getBuyPrice());
            stm_update_invoice_detail.setFloat(5, newInvoiceDetail.getInvoiceProduct().getInvoice().getId());
            stm_update_invoice_detail.executeUpdate();
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
