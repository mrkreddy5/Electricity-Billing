import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SignUpPanel extends JPanel {
    public SignUpPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Padding

        // Username Label

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Username:"), gbc);

        // Username Field

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField usernameField = new JTextField(20);
        add(usernameField, gbc);

        // Mobile Number Label

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Mobile Number:"), gbc);

        // Mobile Number Field

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JTextField mobileNumberField = new JTextField(20);
        add(mobileNumberField, gbc);


        // Usage type label

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Usage Type:"),gbc);


        // usage type dropdown 

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] userTypes = {"Household", "Industry"};
        JComboBox<String> usageTypeField = new JComboBox<>(userTypes);
        add(usageTypeField,gbc);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20,20));

        JButton backToHomeButton = new JButton("Home");

        // used buttonPanel , so no need of specific location constraints to each button
        // gbc.gridx =0;
        // gbc.gridy = 3;
        // gbc.gridwidth = 1;

        backToHomeButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        buttonPanel.add(backToHomeButton,gbc);

        // Register Button

        // gbc.gridx = 1;
        // gbc.gridy = 3;
        // gbc.gridwidth = 1;
        // gbc.fill = GridBagConstraints.NONE;
        // gbc.anchor = GridBagConstraints.CENTER;

        JButton registerButton = new JButton("Register");

        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String userName = usernameField.getText();
                String mobileNumber = mobileNumberField.getText();
                String usageType = (String) usageTypeField.getSelectedItem();

                // verifies whether user entered any name or not
                if(userName.length()==0){
                    JOptionPane.showMessageDialog(mainPanel, "Enter your name ");
                }

                // verifies whether user entered mobile number is already registered or not

                else if (isValidMobileNumber(mainPanel,mobileNumber)) {
                    String serviceId = DatabaseHelper.getNextServiceId();           // databaseHelper program contains the method to get service number if number is not registered
                    DatabaseHelper.saveRegistration(userName , mobileNumber, serviceId, usageType);             // update the details in database
                    JOptionPane.showMessageDialog(mainPanel, "Registered Successfully! Your service ID: "+ serviceId);
                    cardLayout.show(mainPanel, "Welcome");
                } 
                
                else {
                    JOptionPane.showMessageDialog(mainPanel, "Enter a valid Mobile Number");
                }
            }
        });
        
        buttonPanel.add(registerButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across both columns to center it
        gbc.anchor = GridBagConstraints.CENTER; // Center the button panel
        add(buttonPanel, gbc);

    }

    private static boolean isValidMobileNumber(JPanel mainPanel,String mobileNumber) {
        final String URL = "jdbc:mysql://localhost:3308/electricity_billing"; // db url
        final String USER = "root"; // db user id
        final String PASSWORD = ""; // db password

        try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
            String query = "SELECT * FROM pendingbills WHERE MobileNumber = ?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, mobileNumber);
            ResultSet rs = pstmt.executeQuery();
            if(rs.next()){
                JOptionPane.showMessageDialog(mainPanel,"Mobile number is already registered");
                return false;
            }
        }
        catch(Exception e){
            e.printStackTrace();
            return false;
        }

        return mobileNumber != null && !mobileNumber.isEmpty() && mobileNumber.length()==10 && mobileNumber.matches("\\d+");
    }

}