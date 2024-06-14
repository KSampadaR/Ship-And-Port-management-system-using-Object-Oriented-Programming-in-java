import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class newShip extends JFrame {

    public newShip() {
        initComponents();
    }

    private void initComponents() {
        setTitle("New Ship");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        
        // Load background image
        JLabel background = new JLabel(new ImageIcon("image.jpeg"));
        background.setLayout(new BorderLayout());
        setContentPane(background); // Set background label as content pane
        
        // Set layout for content
        setLayout(new GridLayout(11, 2, 10, 1)); // 9 rows, 2 columns, with horizontal and vertical gap

        // Define components
        Color darkWhite = new Color(22, 20, 50);

        // setLayout(new GridLayout(11, 2, 10, 1)); // 9 rows, 2 columns, with horizontal and vertical gap
    
        JLabel idLabel = new JLabel("ID:");
        idLabel.setForeground(darkWhite); // Set text color
        JTextField idField = new JTextField();
    
        JLabel sizeLabel = new JLabel("Size:");
        sizeLabel.setForeground(darkWhite); // Set text color
        JTextField sizeField = new JTextField();
    
        JLabel capacityLabel = new JLabel("Capacity:");
        capacityLabel.setForeground(darkWhite); // Set text color
        JTextField capacityField = new JTextField();
    
        JLabel fuelLabel = new JLabel("Fuel:");
        fuelLabel.setForeground(darkWhite); // Set text color
        JTextField fuelField = new JTextField();
    
        JLabel loadedLabel = new JLabel("Loaded Containers:");
        loadedLabel.setForeground(darkWhite); // Set text color
        JTextField loadedField = new JTextField();
    
        JLabel unloadedLabel = new JLabel("Unloaded Containers:");
        unloadedLabel.setForeground(darkWhite); // Set text color
        JTextField unloadedField = new JTextField();
    
        JLabel sailingToPortLabel = new JLabel("Sailing to Port:");
        sailingToPortLabel.setForeground(darkWhite); // Set text color
        JTextField sailingToPortField = new JTextField();
    
        JLabel containerTypeLabel = new JLabel("Container Type:");
        containerTypeLabel.setForeground(darkWhite); // Set text color
        JTextField containerTypeField = new JTextField();
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retrieve ship information
                String id = idField.getText();
                String size = sizeField.getText();
                String capacity = capacityField.getText();
                String fuel = fuelField.getText();
                String loaded = loadedField.getText();
                String unloaded = unloadedField.getText();
                String sailingToPort = sailingToPortField.getText();
                String containerType = containerTypeField.getText();

                // Check if the ship ID is already present
                if (isShipIdPresent(id)) {
                    JOptionPane.showMessageDialog(newShip.this, "Ship ID already exists. Please use a different ID.");
                } else {
                    // Write ship information to CSV file
                    try (BufferedWriter writer = new BufferedWriter(new FileWriter("ship.csv", true))) {
                        writer.write(id + "," + size + "," + capacity + "," + fuel + "," + loaded + "," + unloaded + "," + sailingToPort + "," + containerType);
                        writer.newLine();
                        writer.close();
                        JOptionPane.showMessageDialog(newShip.this, "Ship information saved successfully.");
                    } catch (IOException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(newShip.this, "Failed to save ship information.");
                    }
                }
            }
        });

        // Add components to the frame
        add(idLabel);
        add(idField);
        add(sizeLabel);
        add(sizeField);
        add(capacityLabel);
        add(capacityField);
        add(fuelLabel);
        add(fuelField);
        add(loadedLabel);
        add(loadedField);
        add(unloadedLabel);
        add(unloadedField);
        add(sailingToPortLabel);
        add(sailingToPortField);
        add(containerTypeLabel);
        add(containerTypeField);
        add(new JLabel()); // Placeholder for alignment
        add(saveButton);

        // Back button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the current frame
                new ships(); // Open the login GUI
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH); // Add back button panel to the bottom

        setVisible(true);
    }


    private boolean isShipIdPresent(String id) {
        try {
            List<String> lines = Files.readAllLines(Paths.get("ship.csv"));
            for (String line : lines) {
                String[] parts = line.split(",");
                if (parts.length > 0 && parts[0].equals(id)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new newShip();
            }
        });
    }
}
