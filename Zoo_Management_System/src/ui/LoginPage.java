package ui;
import dao.SessionManager;
import dao.UserDAO;
import model.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame {

    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;

    public LoginPage() {
        setTitle("Zoo Management System - Login");
        setSize(400, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // ----------- Header -----------
        JLabel lblTitle = new JLabel("Login to Zoo Management System", SwingConstants.CENTER);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        add(lblTitle, BorderLayout.NORTH);

        // ----------- Form Panel -----------
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 20, 40));

        JLabel lblUsername = new JLabel("Username:");
        txtUsername = new JTextField();
        JLabel lblPassword = new JLabel("Password:");
        txtPassword = new JPasswordField();

        formPanel.add(lblUsername);
        formPanel.add(txtUsername);
        formPanel.add(lblPassword);
        formPanel.add(txtPassword);

        add(formPanel, BorderLayout.CENTER);

        // ----------- Button Panel -----------
        JPanel btnPanel = new JPanel();
        btnLogin = new JButton("Login");
        btnLogin.addActionListener(e -> loginAction());
        btnPanel.add(btnLogin);

        add(btnPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

 // Replace the loginAction() method:
    private void loginAction() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter both fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        model.User user = dao.UserDAO.authenticate(username, password);
        if (user != null) {
            SessionManager.setCurrentUser(user);   // save role for access control
            JOptionPane.showMessageDialog(this, "Welcome, " + user.getUsername() + " (" + user.getRole() + ")", "Login Successful", JOptionPane.INFORMATION_MESSAGE);
            dispose();
            new ZooManagementUI();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginPage::new);
    }
}

