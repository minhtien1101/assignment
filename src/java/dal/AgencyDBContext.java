
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agency;

public class AgencyDBContext extends DBContext{
    public ArrayList<Agency> getAgencies() {
        ArrayList<Agency> agencies = new ArrayList<>();
        try {
            String sql = "Select aid, name, phone, address from Agency";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Agency a = new Agency();
                a.setId(rs.getInt("aid"));
                a.setName(rs.getString("name"));
                a.setPhone(rs.getString("phone"));
                a.setAddress(rs.getString("address"));
                agencies.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AgencyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return agencies;
    }
}
