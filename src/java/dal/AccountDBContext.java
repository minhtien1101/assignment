package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;

public class AccountDBContext extends DBContext {

    public Account getAccount(String username, String password) {
        try {
            String sql = "select username, password, displayname from account\n"
                    + "where username = ? and password = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setDisplayname(rs.getString("displayname"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccountByUsername(String username) {
        try {
            String sql = "select username, password, displayname from account\n"
                    + "where username = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setDisplayname(rs.getString("displayname"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccount(String username, String password, String fullname) {
        try {
            con.setAutoCommit(false);
            String insert_account = "INSERT INTO [dbo].[Account]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[displayname])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_account = con.prepareStatement(insert_account);
            stm_insert_account.setString(1, username);
            stm_insert_account.setString(2, password);
            stm_insert_account.setString(3, fullname);
            stm_insert_account.executeUpdate();
            
            String insert_role_account = "INSERT INTO [AccountGroup]\n"
                    + "           ([gid]\n"
                    + "           ,[username])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_role_account = con.prepareStatement(insert_role_account);
            stm_insert_role_account.setInt(1, 2); // 1: admin  2: user
            stm_insert_role_account.setString(2, username);
            stm_insert_role_account.executeUpdate();
            con.commit();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
            try {
                con.rollback();
            } catch (SQLException ex1) {
                Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex1);
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

    public int getNumberOfRoles(String username, String url) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM \n"
                    + "Account a INNER JOIN [AccountGroup] ag ON a.username = ag.username\n"
                    + "		  INNER JOIN [Group] g ON g.gid = ag.gid\n"
                    + "		  INNER JOIN [GroupFeature] gf ON gf.gid = g.gid\n"
                    + "		  INNER JOIN [Feature] f ON gf.fid = f.fid\n"
                    + "WHERE a.username = ? AND f.url = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, url);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                return rs.getInt("Total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
