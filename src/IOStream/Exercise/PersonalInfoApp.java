package IOStream.Exercise;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class PersonalInfoApp extends JFrame implements ActionListener {

    JTextField txtName, txtDOB, txtAddress, txtEmail, txtPhone;
    JButton btnSave, btnCancel;

    public PersonalInfoApp() {
        // Set up the frame
        setTitle("Personal Information");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame
        setLayout(new GridLayout(6, 2));

        // Create text fields
        txtName = new JTextField();
        txtDOB = new JTextField();
        txtAddress = new JTextField();
        txtEmail = new JTextField();
        txtPhone = new JTextField();

        // Create buttons
        btnSave = new JButton("Save");
        btnSave.addActionListener(this);
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(this);

        // Add components to the frame
        add(new JLabel("Name:"));
        add(txtName);
        add(new JLabel("Date of Birth:"));
        add(txtDOB);
        add(new JLabel("Address:"));
        add(txtAddress);
        add(new JLabel("Email:"));
        add(txtEmail);
        add(new JLabel("Phone:"));
        add(txtPhone);
        add(btnSave);
        add(btnCancel);

        // Load data from file if it exists
        loadDataFromFile();
    }

    // Load data from file if it exists
    private void loadDataFromFile() {
        try {
            File file = new File("personal_info.txt");
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                txtName.setText(reader.readLine());
                txtDOB.setText(reader.readLine());
                txtAddress.setText(reader.readLine());
                txtEmail.setText(reader.readLine());
                txtPhone.setText(reader.readLine());
                reader.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Save data to file
    private void saveDataToFile() {
        try {
            PrintWriter writer = new PrintWriter(new FileWriter("personal_info.txt"));
            writer.println(txtName.getText());
            writer.println(txtDOB.getText());
            writer.println(txtAddress.getText());
            writer.println(txtEmail.getText());
            writer.println(txtPhone.getText());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSave) {
            saveDataToFile();
        } else if (e.getSource() == btnCancel) {
            dispose(); // Close the frame
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PersonalInfoApp().setVisible(true);
            }
        });
    }
}
