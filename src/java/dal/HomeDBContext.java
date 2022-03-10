package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DetailInvoice;

public class HomeDBContext extends DBContext {

    public ArrayList<DetailInvoice> getInvoiceDetail() {
        ArrayList<DetailInvoice> detailInvoices = new ArrayList<>();
        try {
            String sql = "select i.buyer, a.displayname, i.id, i.date, id.quantity, id.buyPrice, i.amount, i.paid, i.owed , ag.name, ag.address\n"
                    + "from invoice i join InvoiceProduct id on i.id = id.invoiceId\n"
                    + "join ProductDetail pd on pd.idProduct = id.idProduct\n"
                    + "join product p on p.id = pd.idProduct\n"
                    + "join Dimension d on d.id = pd.idDimension\n"
                    + "join Account a on a.username = i.buyer\n"
                    + "join Agency ag on ag.aid = i.agencyId\n"
                    + "order by i.date desc";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                DetailInvoice d = new DetailInvoice();
                d.getAccount().setUsername(rs.getString("buyer"));
                d.getAccount().setDisplayName(rs.getString("displayname"));
                d.getInvoice().setId(rs.getInt("id"));
                d.getInvoice().setDate(rs.getDate("date"));
                d.getInvoice().setAmount(rs.getFloat("amount"));
                d.getInvoice().setPaid(rs.getFloat("paid"));
                d.getInvoice().setOwed(rs.getFloat("owed"));
                d.getInvoiceProduct().setQuantity(rs.getInt("quantity"));
                d.getInvoiceProduct().setBuyPrice(rs.getFloat("buyPrice"));
                d.getAgency().setName(rs.getString("name"));
                d.getAgency().setAddress(rs.getString("address"));
                detailInvoices.add(d);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(HomeDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return detailInvoices;
    }
}
