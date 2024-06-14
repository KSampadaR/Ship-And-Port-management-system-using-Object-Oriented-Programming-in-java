import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class registration {

    public static void main(String[] args) {
        JFrame f = new JFrame();
        JLabel background = new JLabel(new ImageIcon("image.jpeg"));
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton registerButton = new JButton("Register");

        // Set bounds for components
        usernameLabel.setBounds(100, 100, 80, 30);
        passwordLabel.setBounds(100, 150, 80, 30);
        usernameField.setBounds(200, 100, 150, 30);
        passwordField.setBounds(200, 150, 150, 30);
        registerButton.setBounds(200, 200, 80, 30);

        // Add components to the background label
        background.setLayout(null);
        background.add(usernameLabel);
        background.add(passwordLabel);
        background.add(usernameField);
        background.add(passwordField);
        background.add(registerButton);

        // Set foreground color to white
        usernameLabel.setForeground(Color.cyan);
        passwordLabel.setForeground(Color.cyan);
        usernameField.setForeground(Color.magenta);
        passwordField.setForeground(Color.magenta);
        registerButton.setForeground(Color.magenta);

        // Set frame properties
        f.setContentPane(background); // Set background label as content pane
        f.setSize(500, 300);
        f.setLayout(null);
        f.setVisible(true);

        // Add action listener to the register button
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                // Check if any of the fields are empty
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(f, "Please fill all details!");
                } else {
                    if (register(username, password)) {
                        JOptionPane.showMessageDialog(f, "Registration Successful!");
                    } else {
                        JOptionPane.showMessageDialog(f, "Registration Failed!");
                    }
                }
            }
        });
    }

    private static boolean register(String username, String password) {
        String csvFile = "users.csv";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(csvFile, true))) {
            bw.write(username + "," + password);
            bw.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
