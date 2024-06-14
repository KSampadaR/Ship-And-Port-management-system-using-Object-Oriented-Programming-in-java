import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class afterlogin extends JFrame {

    public afterlogin() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Search");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLayout(new BorderLayout()); // Use BorderLayout for easier positioning
        setLocationRelativeTo(null);
        // Set background image
        JLabel background = new JLabel(new ImageIcon("image.png"));
        background.setLayout(new BorderLayout());
        setContentPane(background); // Set background label as content pane
        
        // Ship panel
        JPanel shipPanel = new JPanel(new BorderLayout());
        shipPanel.setPreferredSize(new Dimension(200, 300));
        shipPanel.setOpaque(false); // Make panel transparent

        JLabel shipLabel = new JLabel();
        shipLabel.setHorizontalAlignment(JLabel.CENTER);
        shipLabel.setIcon(createResizedImageIcon("ship.png", 150, 150)); // Adjust size as needed

        JPanel shipButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        shipButtonPanel.setOpaque(false); // Make panel transparent
        JButton shipsButton = new JButton("Ships");
        shipsButton.setForeground(Color.WHITE); // Set text color
        shipsButton.setBackground(Color.PINK); // Set button color
        shipsButton.setFont(shipsButton.getFont().deriveFont(Font.BOLD)); // Set bold font
        shipsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Ships GUI
                dispose(); // Close the current frame
                new ships(); // Open the Ships GUI
            }
        });
        shipButtonPanel.add(shipsButton);

        shipPanel.add(shipLabel, BorderLayout.CENTER);
        shipPanel.add(shipButtonPanel, BorderLayout.SOUTH);

        // Port panel
        JPanel portPanel = new JPanel(new BorderLayout());
        portPanel.setPreferredSize(new Dimension(200, 300));
        portPanel.setOpaque(false); // Make panel transparent

        JLabel portLabel = new JLabel();
        portLabel.setHorizontalAlignment(JLabel.CENTER);
        portLabel.setIcon(createResizedImageIcon("port.png", 150, 150)); // Adjust size as needed

        JPanel portButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        portButtonPanel.setOpaque(false); // Make panel transparent
        JButton portsButton = new JButton("Ports");
        portsButton.setForeground(Color.WHITE); // Set text color
        portsButton.setBackground(Color.PINK); // Set button color
        portsButton.setFont(portsButton.getFont().deriveFont(Font.BOLD)); // Set bold font
        portsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the Ships GUI
                dispose(); // Close the current frame
                new CheckPort(); // Open the Ships GUI
            }
        });
        portButtonPanel.add(portsButton);

        portPanel.add(portLabel, BorderLayout.CENTER);
        portPanel.add(portButtonPanel, BorderLayout.SOUTH);

        // Add ship panel and port panel to main frame
        background.add(shipPanel, BorderLayout.WEST); // Add ship panel to the left
        background.add(portPanel, BorderLayout.EAST); // Add port panel to the right

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new login(); // Open the login GUI
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false); // Make panel transparent
        bottomPanel.add(backButton);
        background.add(bottomPanel, BorderLayout.SOUTH); // Add back button panel to the bottom

        setVisible(true);
    }

    // Method to create ImageIcon from file and resize it
    private ImageIcon createResizedImageIcon(String path, int width, int height) {
        URL imgUrl = getClass().getResource(path);
        if (imgUrl != null) {
            ImageIcon icon = new ImageIcon(imgUrl);
            Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
            return new ImageIcon(image);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new afterlogin();
            }
        });
    }
}
