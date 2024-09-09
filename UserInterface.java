import javax.swing.*;
import java.awt.*;

public class UserInterface {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;

    public UserInterface(){
        createAndShowGUI();
    }


    private void createAndShowGUI() {
        frame = new JFrame("User Interface");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // cardlayout is used to switch between multiple panels at a single container

        cardLayout = new CardLayout();  
        mainPanel = new JPanel(cardLayout);

        // passing cardlayout and main panel as parameters to each sub panel and constarints are nothing but panel name
        mainPanel.add(new WelcomePanel(cardLayout, mainPanel),"Welcome");
        mainPanel.add(new SignUpPanel(cardLayout, mainPanel),"SignUp");
        mainPanel.add(new LoginPanel(cardLayout,mainPanel),"Login");
        mainPanel.add(new ServiceProviderPanel(cardLayout, mainPanel),"Service Provider");
        mainPanel.add(new AddingBillToUser(cardLayout, mainPanel),"Adding Bill To User");

        frame.getContentPane().add(mainPanel);
        frame.setSize(400,600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // only "welcome" subpanel is added to main panel initially 
        cardLayout.show(mainPanel,"Welcome");  
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run(){
                new UserInterface();
            }
        });
    }
}



