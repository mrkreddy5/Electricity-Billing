import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    public LoginPanel(CardLayout cardLayout, JPanel mainPanel) {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Service ID label

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1; // Span label within a single column
        // gbc.anchor = GridBagConstraints.CENTER; // Center the label
        add(new JLabel("Service ID:"), gbc);

        // Service ID field

        gbc.gridx = 1;
        gbc.gridwidth = 1; // Span text field adjacent to service id label 
        JTextField serviceIdField = new JTextField(20);
        add(serviceIdField, gbc);

        // Verify button

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2; // Span across both columns to center it
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        JButton verifyButton = new JButton("Verify");

        verifyButton.addActionListener(e -> {
            String serviceId = serviceIdField.getText();
            ForgotServiceID.checkServiceId(serviceId);
        });

        add(verifyButton, gbc);
        
        // Panel for Home and Forgot Service ID buttons

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0)); // Centered with spacing
        JButton backToHomeButton = new JButton("Home");

        backToHomeButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        buttonPanel.add(backToHomeButton);

        JButton forgotButton = new JButton("Forgot Service ID");

        forgotButton.addActionListener(e -> {
            String mobileNumber = JOptionPane.showInputDialog("Enter mobile number:");
            if (isValidMobileNumber(mobileNumber)) {
                ForgotServiceID.retrieveServiceId(mobileNumber);
            } else {
                JOptionPane.showMessageDialog(mainPanel, "Enter a valid mobile number");
            }
        });

        buttonPanel.add(forgotButton);

        // Home and Forgot Service ID buttons are added to button panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Span across both columns to center it
        gbc.anchor = GridBagConstraints.CENTER; // Center the button panel
        add(buttonPanel, gbc);
    }

    private boolean isValidMobileNumber(String mobileNumber) {
        // Validate that the number is not null, is not empty, and consists only of digits and eaxctly 10 digits
        return mobileNumber != null && !mobileNumber.isEmpty() && mobileNumber.length()==10 && mobileNumber.matches("\\d{10}");
    }
}
