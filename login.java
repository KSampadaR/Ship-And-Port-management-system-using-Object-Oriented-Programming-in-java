import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class login {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel background = new JLabel(new ImageIcon("image.jpeg")); // Set background image

        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Register"); // Add Register button

        // Set bounds for components
        usernameLabel.setBounds(50, 50, 80, 30);
        passwordLabel.setBounds(50, 100, 80, 30);
        usernameField.setBounds(140, 50, 150, 30);
        passwordField.setBounds(140, 100, 150, 30);
        loginButton.setBounds(140, 150, 80, 30);
        registerButton.setBounds(230, 150, 100, 30); // Set bounds for Register button

        // Add components to the background label
        background.setLayout(null);
        background.add(usernameLabel);
        background.add(passwordLabel);
        background.add(usernameField);
        background.add(passwordField);
        background.add(loginButton);
        background.add(registerButton); // Add Register button to the background

        // Set frame properties
        f.setContentPane(background); // Set background label as content pane
        f.setSize(500, 400); // Set frame size
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setLayout(null);
        f.setLocationRelativeTo(null); // Center the frame on the screen
        f.setVisible(true);

        // Add action listener to the login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    JOptionPane.showMessageDialog(f, "Login Successful!");
                    openAppUI(); // Call method to open App.java UI
                } else {
                    JOptionPane.showMessageDialog(f, "Invalid Username or Password");
                }
            }
        });

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                f.dispose(); // Close the current frame 
                new registration(); // Open the registration GUI
            }
        });
    }

    private static boolean authenticate(String username, String password) {
        String csvFile = "users.csv";
        String line;
        String cvsSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] user = line.split(cvsSplitBy);
                if (user.length >= 2 && user[0].equals(username) && user[1].equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private static void openAppUI() {
        // Call App class to open the UI
        afterlogin.main(new String[]{});
    }
}
