package dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InvoiceDBContext extends DBContext {

    public void updateOwedInvoice(int idInvoice, long amount) {
        try {
            String sql = "UPDATE [Invoice]\n"
                    + "   SET \n"
                    + "      [paid] = ?\n"
                    + "      ,[owed] = ?     \n"
                    + " WHERE [id] = ?;";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setFloat(1, amount);
            stm.setFloat(2, 0);
            stm.setInt(3, idInvoice);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InvoiceDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
