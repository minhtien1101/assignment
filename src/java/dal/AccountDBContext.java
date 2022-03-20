package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;
import model.Role;

public class AccountDBContext extends DBContext {

    public Account getAccount(String username, String password) {
        try {
            String sql = "Select username, password, displayname, gid From Account\n"
                    + "Where username = ? and password = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            stm.setString(2, password);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setDisplayname(rs.getString("displayname"));
                acc.getRole().setId(rs.getInt("gid"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public Account getAccountByUsername(String username) {
        try {
            String sql = "Select username, password, displayname, gid From Account\n"
                    + "Where username = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Account acc = new Account();
                acc.setUsername(rs.getString("username"));
                acc.setPassword(rs.getString("password"));
                acc.setDisplayname(rs.getString("displayname"));
                acc.getRole().setId(rs.getInt("gid"));
                return acc;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void insertAccount(String username, String password, String fullname) {
        try {
            String insert_account = "INSERT INTO [dbo].[Account]\n"
                    + "           ([username]\n"
                    + "           ,[password]\n"
                    + "           ,[displayname]\n"
                    + "           ,[gid])\n"
                    + "     VALUES\n"
                    + "           (?\n"
                    + "           ,?\n"
                    + "           ,?\n"
                    + "           ,?)";
            PreparedStatement stm_insert_account = con.prepareStatement(insert_account);
            stm_insert_account.setString(1, username);
            stm_insert_account.setString(2, password);
            stm_insert_account.setString(3, fullname);
            stm_insert_account.setInt(4, 2);
            stm_insert_account.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumberOfRoles(String username, String url) {
        try {
            String sql = "SELECT COUNT(*) as Total FROM \n"
                    + "Account a INNER JOIN [Group] g ON g.gid = a.gid\n"
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

    public ArrayList<Role> getRoles() {
        ArrayList<Role> roles = new ArrayList<>();
        try {
            String sql = "Select * From [Group]";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("gid"));
                r.setName(rs.getString("gname"));
                roles.add(r);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return roles;
    }

    public ArrayList<Account> getAccounts() {
        ArrayList<Account> accounts = new ArrayList<>();
        try {
            String sql = "Select * from Account order by username asc";
            PreparedStatement stm = con.prepareStatement(sql);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Account a = new Account();
                a.setUsername(rs.getString("username"));
                a.setPassword(rs.getString("password"));
                a.setDisplayname(rs.getString("displayname"));
                a.getRole().setId(rs.getInt("gid"));
                accounts.add(a);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return accounts;
    }

    public Role getRoleAccountByUsername(String username) {
        try {
            String sql = "Select a.gid, p.gname from account a \n"
                    + "join [Group] p on p.gid = a.gid\n"
                    + "where a.username = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setString(1, username);
            ResultSet rs = stm.executeQuery();
            if (rs.next()) {
                Role r = new Role();
                r.setId(rs.getInt("gid"));
                r.setName(rs.getString("gname"));
                return r;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public void updateRoleAccount(String username, int role) {
        try {
            String sql = "UPDATE [Account]\n"
                    + "   SET [gid] = ?\n"
                    + " WHERE [username] = ?";
            PreparedStatement stm = con.prepareStatement(sql);
            stm.setInt(1, role);
            stm.setString(2, username);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDBContext.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
