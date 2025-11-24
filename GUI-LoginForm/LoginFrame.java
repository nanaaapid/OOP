import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame {
    private JTextField tUser;
    private JPasswordField tPass;
    private JButton bLogin;
    private JButton bBatal;

    private LoginController controller;

    public LoginFrame() {
        controller = new LoginController();

        setTitle("Form Login");
        setSize(350, 170);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initUI();
    }

    private void initUI() {
        JPanel p = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        c.gridx = 0; c.gridy = 0;
        p.add(new JLabel("Username:"), c);
        tUser = new JTextField();
        c.gridx = 1; c.gridy = 0;
        p.add(tUser, c);

        c.gridx = 0; c.gridy = 1;
        p.add(new JLabel("Password:"), c);
        tPass = new JPasswordField();
        c.gridx = 1; c.gridy = 1;
        p.add(tPass, c);

        JPanel pBtn = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bBatal = new JButton("Batal");
        bLogin = new JButton("Login");
        pBtn.add(bBatal);
        pBtn.add(bLogin);
        c.gridx = 0; c.gridy = 2; c.gridwidth = 2;
        p.add(pBtn, c);

        add(p);

        ActionListener al = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                prosesLogin();
            }
        };

        bLogin.addActionListener(al);
        tPass.addActionListener(al); // tekan Enter pada password = login

        bBatal.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tUser.setText("");
                tPass.setText("");
                tUser.requestFocus();
            }
        });
    }

    private void prosesLogin() {
        String u = tUser.getText();
        String p = new String(tPass.getPassword());

        if (controller.cekLogin(u, p)) {
            JOptionPane.showMessageDialog(this, "Login Berhasil!\nSelamat datang, " + u + "!");
            // Pilihan 1: hanya tutup frame
            dispose();

            // Pilihan 2 (opsional): keluar dari aplikasi sepenuhnya
            // System.exit(0);

        } else {
            JOptionPane.showMessageDialog(this, "Username atau Password salah!", "Gagal", JOptionPane.ERROR_MESSAGE);
            tPass.setText("");
            tUser.requestFocus();
        }
    }
}

