
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Dimension;


public class DimensionDBContext extends DBContext{
    public ArrayList<Dimension> getDimensions() {
        ArrayList<Dimension> dimensions = new ArrayList<>();
        try {
            String sql = "Select id, name From Dimension";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Dimension d = new Dimension();
                d.setId(rs.getInt("id"));
                d.setName(rs.getString("name"));
                dimensions.add(d);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DimensionDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dimensions;
    }
}
