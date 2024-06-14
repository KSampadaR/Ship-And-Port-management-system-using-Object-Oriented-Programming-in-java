import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class OldShip extends JFrame {
    private JLabel idLabel;
    private JTextField idField;
    private JButton checkButton;
    private JTextArea resultArea;

    private Map<String, String[]> shipData; // Map to store ship data by ID

    public OldShip() {
        initComponents();
        loadShipData(); // Load ship data from CSV file
    }

    private void initComponents() {
        setTitle("Ship Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(new Color(127, 255, 212)); // Aqua background color
        setLayout(new FlowLayout());

        idLabel = new JLabel("Enter Ship ID or Container Type:");
        idField = new JTextField(15);
        checkButton = new JButton("Check");
        resultArea = new JTextArea(10, 30);
        resultArea.setEditable(false); // Make result area read-only
        JScrollPane scrollPane = new JScrollPane(resultArea);

        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = idField.getText().trim();
                if (!input.isEmpty()) {
                    if (shipData.containsKey(input)) {
                        String[] shipInfo = shipData.get(input);
                        displayShipInfo(input, shipInfo);
                    } else {
                        searchByContainerType(input);
                    }
                } else {
                    JOptionPane.showMessageDialog(OldShip.this, "Please enter a Ship ID or Container Type.");
                }
            }
        });

        add(idLabel);
        add(idField);
        add(checkButton);
        add(scrollPane);

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


    private void loadShipData() {
        shipData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("ship.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 8) {
                    String id = parts[0];
                    String[] info = {parts[1], parts[2], parts[3], parts[4], parts[5], parts[6], parts[7]};
                    shipData.put(id, info);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayShipInfo(String id, String[] shipInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("Ship ID: ").append(id).append("\n");
        sb.append("Size: ").append(shipInfo[0]).append("\n");
        sb.append("Capacity: ").append(shipInfo[1]).append("\n");
        sb.append("Fuel: ").append(shipInfo[2]).append("\n");
        sb.append("Loaded Containers: ").append(shipInfo[3]).append("\n");
        sb.append("Unloaded Containers: ").append(shipInfo[4]).append("\n");
        sb.append("Sailing to Port: ").append(shipInfo[5]).append("\n");
        sb.append("Container Type: ").append(shipInfo[6]);
        resultArea.setText(sb.toString());
    }

    private void searchByContainerType(String containerType) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Map.Entry<String, String[]> entry : shipData.entrySet()) {
            if (entry.getValue()[6].equals(containerType)) {
                found = true;
                sb.append("Ship ID: ").append(entry.getKey()).append("\n");
                sb.append("Size: ").append(entry.getValue()[0]).append("\n");
                sb.append("Capacity: ").append(entry.getValue()[1]).append("\n");
                sb.append("Fuel: ").append(entry.getValue()[2]).append("\n");
                sb.append("Loaded Containers: ").append(entry.getValue()[3]).append("\n");
                sb.append("Unloaded Containers: ").append(entry.getValue()[4]).append("\n");
                sb.append("Sailing to Port: ").append(entry.getValue()[5]).append("\n");
                sb.append("Container Type: ").append(entry.getValue()[6]).append("\n\n");
            }
        }
        if (!found) {
            sb.append("No ships found with container type: ").append(containerType);
        }
        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new OldShip();
            }
        });
    }
}
