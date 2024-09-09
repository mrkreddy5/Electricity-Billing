import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class AddingBillToUser extends JPanel{

    private static final String URL = "jdbc:mysql://localhost:3308/electricity_billing"; // db url
    private static final String USER = "root"; // db user id
    private static final String PASSWORD = ""; // db password


    public AddingBillToUser(CardLayout cardLayout, JPanel mainPanel){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        // service id label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        add(new JLabel("Service Id: "),gbc);

        // service id textfield
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JTextField serviceIdTextField = new JTextField(20);
        add(serviceIdTextField, gbc);

        // Usage type label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        add(new JLabel("Usage Type: "),gbc);

        // usagetype selection dropdown
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        String[] typesAvailable = {"Household", "Industry"};
        JComboBox<String> usageTypeField = new JComboBox<>(typesAvailable);
        usageTypeField.setPreferredSize(new Dimension(180,20));
        add(usageTypeField, gbc);

        // units used label
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        add(new JLabel("Units used: "),gbc);

        // units used textfield
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        JTextField unitsUsedTextField = new JTextField(20);
        add(unitsUsedTextField, gbc);


        // submit button 
        JButton submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> {
            String serviceId = serviceIdTextField.getText();
            String usageType = (String) usageTypeField.getSelectedItem();
            String unitsUsed = unitsUsedTextField.getText();

            try(Connection con = DriverManager.getConnection(URL, USER, PASSWORD)){
                String query = "Select UnitsUsed, TotalCost from pendingbills where ServiceId = ? and UsageType = ?";  // if usagetype entered by user , it will show an indication that user is not registered
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setString(1, serviceId);
                pstmt.setString(2, usageType);

                ResultSet rs = pstmt.executeQuery();
                
                if(rs.next()){
                    // extracting existing information

                    int existingUnits = rs.getInt("UnitsUsed");
                    double existingTotalCost = rs.getDouble("TotalCost");

                    // update existing inforamtion

                    int newUnitsUsed = existingUnits + Integer.parseInt(unitsUsed);
                    double newBill = UnitsCharges.toatlBill(usageType, unitsUsed);
                    double newTotalCost = existingTotalCost + newBill;

                    String updateQuery = "update pendingbills set UnitsUsed = ?, TotalCost = ? where ServiceId = ?";
                    PreparedStatement pstmt2 = con.prepareStatement(updateQuery);
                    pstmt2.setInt(1, newUnitsUsed);
                    pstmt2.setDouble(2, newTotalCost);
                    pstmt2.setString(3, serviceId);
                   
                    int updatedRows = pstmt2.executeUpdate();
                    if(updatedRows > 0){
                        JOptionPane.showMessageDialog(submitButton, "Bill is added successfully");
                    }
                }
                else{
                    JOptionPane.showMessageDialog(submitButton, "Service ID not found");
                }
            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        });

        JButton backToHomeButton = new JButton("Home");
        backToHomeButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
    
        // button panel
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        buttonPanel.add(backToHomeButton,gbc);
        buttonPanel.add(submitButton,gbc);

        add(buttonPanel,gbc);
    }
}
