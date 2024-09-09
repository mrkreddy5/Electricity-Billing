import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3308/electricity_billing"; // db url
    private static final String USER = "root"; // db user id
    private static final String PASSWORD = ""; // db password

    public static void main(String[] args) {
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
            Statement smt = con.createStatement()) {

            // String query = "INSERT INTO pending_bills VALUES('ram', 123456, 9878901234, 25, 250)";
            // int rowsAffected = smt.executeUpdate(query);

            // if (rowsAffected > 0) {
            //     System.out.println("Data inserted successfully.");
            // } else {
            //     System.out.println("No data was inserted.");
            // }

            String query = "Select * from pending_bills";
            ResultSet rs = smt.executeQuery(query);
            while(rs.next()){
                System.out.println(rs.getString(1) + " " +
                                   rs.getString(2) + " " + 
                                   rs.getString(3) + " " +
                                   rs.getString(4) + " " + 
                                   rs.getString(5));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
