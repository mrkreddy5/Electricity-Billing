import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomePanel extends JPanel {
    public WelcomePanel(CardLayout cardLayout, JPanel mainPanel) {

        // gridbag layout provides a way to user to specify the object( label / any fields) locations

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        // external padding to each element
        gbc.insets = new Insets(10, 10, 10, 10);


        // welcome label

        JLabel welcomeLabel = new JLabel("WELCOME TO STATE ELECTRICITY BILLING DESK");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(welcomeLabel, gbc);


        // sign up button

        JButton signUpButton = new JButton("Sign Up");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.ipadx = 70;
        gbc.ipady = 10;

        signUpButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "SignUp");
            }
        });
        add(signUpButton, gbc);

        JButton loginButton = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.ipadx = 80;         // explicit padding for each button to get buttons in same size
        gbc.ipady = 10;
        
        loginButton.addActionListener(e -> cardLayout.show(mainPanel,"Login"));
        add(loginButton, gbc);


        JButton serviceProviderButton = new JButton("Service Provider");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.ipadx = 20;
        gbc.ipady = 10;
        gbc.gridwidth =2;
        gbc.anchor = GridBagConstraints.CENTER;
        serviceProviderButton.addActionListener(e -> cardLayout.show(mainPanel, "Service Provider"));
        add(serviceProviderButton,gbc);


        /*  we can even create a buttonPanel and add all these buttons to it and specify location constraints 
            to only buttonPanel    */
    }
}