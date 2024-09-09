import java.awt.*;
import javax.swing.*;


public class ServiceProviderPanel extends JPanel{
    public ServiceProviderPanel(CardLayout cardLayout, JPanel mainPanel){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10,10,10,10);

        // user id label
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth =1;
        add(new JLabel("User Id: "),gbc);

        // username textfield
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        JTextField userIdTextField = new JTextField(20);
        add(userIdTextField, gbc);

        // username label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth =1;
        add(new JLabel("User Name: "),gbc);

        // username textfield
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        JTextField userNameTextField = new JTextField(20);
        add(userNameTextField, gbc);

        // password label 
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth =1;
        add(new JLabel("Password: "),gbc);

        //password field
        gbc.gridx = 1;
        gbc.gridwidth = 1;
        JPasswordField passwordTextField = new JPasswordField(20);
        add(passwordTextField, gbc);

        
        // Login button
        JButton LoginButton = new JButton("Login");
        gbc.gridwidth = 1;

        // gbc.anchor = GridBagConstraints.CENTER;
        LoginButton.addActionListener(e -> {
            String userId = userIdTextField.getText();
            String username = userNameTextField.getText();
            char[] passwordCharacters = passwordTextField.getPassword();
            String password = new String(passwordCharacters);
            boolean verificationResult = ServiceProviderLogin.verifyUser(userId,username, password);
            if(verificationResult){
                cardLayout.show(mainPanel, "Adding Bill To User");
            }
            else{
                JOptionPane.showMessageDialog(null, "Invalid User Id or Password");
            }
        });
        
        // home button
        JButton backToHomeButton = new JButton("Home");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        backToHomeButton.addActionListener(e -> cardLayout.show(mainPanel, "Welcome"));
        
        
        // button panel 
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER,20,20));
        gbc.gridx = 1;
        // gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        buttonPanel.add(backToHomeButton);
        buttonPanel.add(LoginButton);
        add(buttonPanel, gbc);
    }
}
