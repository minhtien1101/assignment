package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Agency;

public class AgencyDBContext extends DBContext {

    public ArrayList<Agency> getAgencies() {
        ArrayList<Agency> agencies = new ArrayList<>();
        try {
            String sql = "Select id, name, phone, address from Agency";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Agency a = new Agency();
                a.setId(rs.getInt("id"));
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

    public void insertAgency(Agency agency) {
        try {
            String sql = "INSERT INTO [Agency]\n"
                    + "           ([name]\n"
                    + "           ,[phone]\n"
                    + "           ,[address])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, agency.getName());
            stm.setString(2, agency.getPhone());
            stm.setString(3, agency.getAddress());
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AgencyDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
