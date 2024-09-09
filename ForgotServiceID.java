import javax.swing.*;
import java.sql.*;

public class ForgotServiceID {
    private static final String URL = "jdbc:mysql://localhost:3308/electricity_billing"; // db url
    private static final String USER = "root"; // db user id
    private static final String PASSWORD = ""; // db password

    public static void checkServiceId(String serviceId){
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
        Statement smt = con.createStatement()) {
            String query = "Select TotalCost from pendingbills where ServiceId = ?";        // retrieve pending amount to be paid
            try(PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, serviceId);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    double totalCost = rs.getDouble("TotalCost");
                    JOptionPane.showMessageDialog(null, "Pending Amount: RS." + totalCost);
                } else {
                    JOptionPane.showMessageDialog(null, "Service id not found.");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void retrieveServiceId(String mobileNumber){
        // This method should only be called with valid mobile numbers, as validated in LoginPanel
        
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String query = "Select ServiceId from pendingbills where MobileNumber = ?";
            try(PreparedStatement stmt = con.prepareStatement(query)) {
                stmt.setString(1, mobileNumber);
                ResultSet rs = stmt.executeQuery();
                if(rs.next()){
                    String serviceId = rs.getString("ServiceId");
                    JOptionPane.showMessageDialog(null, "Your Service ID: " + serviceId);
                } else {
                    JOptionPane.showMessageDialog(null, "User not found.");
                }
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
