package dal;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Buyer;

public class BuyerDBContext extends DBContext {

    public ArrayList<Buyer> getBuyers() {
        ArrayList<Buyer> buyers = new ArrayList<>();
        try {
            String sql = "Select id, name, gender,dob, phone, address From Buyer";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Buyer b = new Buyer();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setGender(rs.getBoolean("gender"));
                b.setPhone(rs.getString("phone"));
                b.setDob(rs.getDate("dob"));
                b.setAddress(rs.getString("address"));
                buyers.add(b);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

        return buyers;
    }

    public Buyer getBuyer(int idBuyer) {
        try {
            String sql = "select * from Buyer\n"
                    + "where id = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, idBuyer);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Buyer b = new Buyer();
                b.setId(rs.getInt("id"));
                b.setName(rs.getString("name"));
                b.setPhone(rs.getString("phone"));
                b.setGender(rs.getBoolean("gender"));
                b.setDob(rs.getDate("dob"));
                b.setAddress(rs.getString("address"));
                return b;
            }
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertBuyer(Buyer buyer) {
        try {
            String sql = "INSERT INTO [dbo].[Buyer]\n"
                    + "           ([name]\n"
                    + "           ,[phone]\n"
                    + "           ,[gender]\n"
                    + "           ,[dob]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, buyer.getName());
            stm.setString(2, buyer.getPhone());
            stm.setBoolean(3, buyer.isGender());
            stm.setDate(4, buyer.getDob());
            stm.setString(5, buyer.getAddress());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(BuyerDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
