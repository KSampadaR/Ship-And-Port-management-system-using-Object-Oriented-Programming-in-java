import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CheckPort extends JFrame {
    private JLabel idLabel;
    private JTextField idField;
    private JButton checkButton;
    private JTextArea resultArea;

    private Map<String, String[]> portData; // Map to store port data by ID or Location

    public CheckPort() {
        initComponents();
        loadPortData(); // Load port data from CSV file
    }

    private void initComponents() {
        setTitle("Port Information");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setLocationRelativeTo(null); // Center the window
        getContentPane().setBackground(new Color(127, 255, 212)); // Aqua background color
        setLayout(new FlowLayout());

        idLabel = new JLabel("Enter Port ID or Location ");
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
                    if (portData.containsKey(input)) {
                        String[] portInfo = portData.get(input);
                        displayPortInfo(input, portInfo);
                    } else {
                        searchByLocation(input);
                    }
                } else {
                    JOptionPane.showMessageDialog(CheckPort.this, "Please enter a Port ID or Location.");
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
                new afterlogin();// Replace this with your code to navigate back
            }
        });
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.add(backButton);
        add(bottomPanel, BorderLayout.SOUTH); // Add back button panel to the bottom

        setVisible(true);
    }


    private void loadPortData() {
        portData = new HashMap<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("port.csv"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length >= 3) {
                    String id = parts[0];
                    String[] info = {parts[1], parts[2]};
                    portData.put(id, info);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void displayPortInfo(String id, String[] portInfo) {
        StringBuilder sb = new StringBuilder();
        sb.append("ID: ").append(id).append("\n");
        sb.append("Facilities: ").append(portInfo[0]).append("\n");
        sb.append("Location: ").append(portInfo[1]).append("\n");
        resultArea.setText(sb.toString());
    }

    private void searchByLocation(String location) {
        StringBuilder sb = new StringBuilder();
        boolean found = false;
        for (Map.Entry<String, String[]> entry : portData.entrySet()) {
            if (entry.getValue()[1].equalsIgnoreCase(location)) {
                found = true;
                sb.append("ID: ").append(entry.getKey()).append("\n");
                sb.append("Facilities: ").append(entry.getValue()[0]).append("\n");
                sb.append("Location: ").append(entry.getValue()[1]).append("\n");
            }
        }
        if (!found) {
            sb.append("No ports found at location: ").append(location);
        }
        resultArea.setText(sb.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CheckPort();
            }
        });
    }
}
