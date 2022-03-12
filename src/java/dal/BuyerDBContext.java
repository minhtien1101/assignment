
package dal;

import dal.DBContext;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Buyer;

public class BuyerDBContext extends DBContext{
    public ArrayList<Buyer> getBuyers() {
        ArrayList<Buyer> buyers = new ArrayList<>();
        try {
            String sql = "Select id, name, gender,dob, phone, address From Buyer";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
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
}
