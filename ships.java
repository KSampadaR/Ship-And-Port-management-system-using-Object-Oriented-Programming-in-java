import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ships extends JFrame {
    private JButton checkShipsButton;
    private JButton createShipButton;

    public ships() {
        initComponents();
    }

    private void initComponents() {
        setTitle("Ship Management");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window

        // Aqua background color
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(127, 255, 212));
        GridBagConstraints gbc = new GridBagConstraints();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel("What would you like to do?");
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20)); // Set bold font

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);

        JPanel buttonsPanel = new JPanel(new GridLayout(1, 2, 2, 0));
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(2, 0, 0, 0));

        checkShipsButton = new JButton("Check Existing Ships");
        checkShipsButton.setPreferredSize(new Dimension(190, 40)); // Increase button size
        checkShipsButton.setBackground(Color.PINK); // Pink button color
        checkShipsButton.setForeground(Color.WHITE); // White text color
        checkShipsButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set bold font
        checkShipsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new OldShip(); // Open the newShip GUI
            }
        });

        createShipButton = new JButton("Create New Ship");
        createShipButton.setPreferredSize(new Dimension(140, 40)); // Increase button size
        createShipButton.setBackground(Color.PINK); // Pink button color
        createShipButton.setForeground(Color.WHITE); // White text color
        createShipButton.setFont(new Font("Arial", Font.BOLD, 14)); // Set bold font
        createShipButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new newShip(); // Open the newShip GUI
            }
        });

        buttonsPanel.add(checkShipsButton);
        buttonsPanel.add(createShipButton);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        mainPanel.add(buttonsPanel, gbc);

        // Add some padding around the main panel
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        contentPane.add(mainPanel, BorderLayout.CENTER);

        setContentPane(contentPane);
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new afterlogin(); // Open the login GUI
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH); // Add back button panel to the bottom

        setVisible(true);
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ships();
            }
        });
    }
}
