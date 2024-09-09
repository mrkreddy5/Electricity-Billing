import java.sql.*;

public class ServiceProviderLogin {
    private static final String URL = "jdbc:mysql://localhost:3308/electricity_billing"; // db url
    private static final String USER = "root"; // db user id
    private static final String PASSWORD = ""; // db password

    public static boolean verifyUser(String userId, String userName, String password){
        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String query = "Select UserName, Password from serviceprovider where UserId = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, userId);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                String retrievedUserName = rs.getString(1);
                String retrievedPassword = rs.getString(2);
                if(userName.equals(retrievedUserName) && password.equals(retrievedPassword)){
                    return true;
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return false;
    }

}
