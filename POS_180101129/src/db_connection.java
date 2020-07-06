import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author user
 */
public class db_connection {
    private static Connection connection;
    public static Connection getconnection() {
        String  host = "jdbc:mysql://localhost/electronic_store",
                user = "root",
                pass = "";
        try {
            connection = (Connection) DriverManager.getConnection(host, user, pass);
        } catch (SQLException err) {
            JOptionPane.showMessageDialog(null, err.getMessage());
        }
        return connection;
    }
}
