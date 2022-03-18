
package dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DBContext {
    protected Connection con;

    public DBContext() {
        try {
            String user = "tienpm";
            String pass = "1";
            String url = "jdbc:sqlserver://localhost\\TIEN:1433;databaseName=Assignm";
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url, user, pass);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
