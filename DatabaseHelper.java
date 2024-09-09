import java.sql.*;


public class DatabaseHelper {
    private static final String URL = "jdbc:mysql://localhost:3308/electricity_billing"; // db url
    private static final String USER = "root"; // db user id
    private static final String PASSWORD = ""; // db password

    public static String getNextServiceId(){
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);     // verify connection is valid or not 
        Statement smt = con.createStatement()) {                    // even if connection is valid if stmts are not properly created or initialised it wont allow to perform any operations on db
            String query = "Select max(ServiceId) from pendingBills";      // service id of last registered user
            try(PreparedStatement stmt = con.prepareStatement(query);
            ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {    
                    int prevServiceId = rs.getInt(1);
                    return String.valueOf(prevServiceId + 1);           // service id's for users are allocated in sequence
                }
            }

            catch(Exception e){
                e.printStackTrace();
            }
        }

        catch(Exception e){
            e.printStackTrace();
        }
        return "567890";                    // if no user is registered till now , this id will be allocated to them
    }




    public static void saveRegistration(String userName, String mobileNumber,String serviceId, String usageType){
        // System.out.println(userName);
        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD))
        {
            String insertQuery = "INSERT INTO pendingbills (UserName, MobileNumber, ServiceId, UsageType) VALUES ( ?, ?, ?, ?)";
            try(PreparedStatement stmt = con.prepareStatement(insertQuery)) {
                stmt.setString(1, userName);
                stmt.setString(2, mobileNumber);
                stmt.setString(3, serviceId);
                stmt.setString(4, usageType);

                int rowsUpdated = stmt.executeUpdate();             // this boiler code is just for verification , and won't be displayed on interface
                if(rowsUpdated>0)
                    System.out.println("added successfully");       
                
                else
                    System.out.println("failed to add");
            }

            catch(Exception e){
                e.printStackTrace();;
            }

        }

        catch(Exception e){
            e.printStackTrace();
        }
    }
}
