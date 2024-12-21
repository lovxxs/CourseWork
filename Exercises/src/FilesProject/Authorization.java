package FilesProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Authorization {

    public void Login(JFrame frame) {
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowOpened(WindowEvent e) {
                frame.setEnabled(false);
                ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                client.writeLine("Происходит авторизация пользователя");
                JFrame window = new JFrame("Вход в программу");
                window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                window.setSize(300, 150);
                window.setLocationRelativeTo(null);
                window.setResizable(false);

                JPanel panel = new JPanel();
                JLabel userLabel = new JLabel("Логин:");
                JTextField userText = new JTextField(20);
                JLabel passwordLabel = new JLabel("Пароль:");
                JPasswordField passwordText = new JPasswordField(20);
                JButton loginButton = new JButton("Войти");

                panel.add(userLabel);
                panel.add(userText);
                panel.add(passwordLabel);
                panel.add(passwordText);
                panel.add(loginButton);

                window.add(panel);

                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String username = userText.getText();
                        String password = new String(passwordText.getPassword());
                        if ("admin".equals(username) && "password".equals(password)) {
                            ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                            client.writeLine("Пользователь авторизовался");
                            frame.setEnabled(true); window.dispose();
                        } else {
                            ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                            client.writeLine("Пользователь не авторизовался");
                            JOptionPane.showMessageDialog(window, "Неверный логин или пароль.");
                        }
                    }
                });
                window.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosing(WindowEvent e) {
                        ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                        client.writeLine("Пользователь закрыл приложение при авториазации");
                    }
                });
                window.setVisible(true);
                window.revalidate();
            }
        });
    }

    public void Login(JFrame frame, JComboBox<String> box) {
        JFrame window = new JFrame("Авторизация"); frame.setEnabled(false);
        window.setSize(300, 150); window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

        JPanel panel = new JPanel(); panel.setLayout(new GridLayout(3, 1));
        JPanel panel1 = new JPanel(); panel1.setSize(300, 50);
        JPanel panel2 = new JPanel(); panel2.setSize(300, 50);
        JPanel panel3 = new JPanel(); panel3.setSize(300, 50);
        panel.add(panel1); panel.add(panel2); panel.add(panel3);

        JLabel label1 = new JLabel("Логин:"); JTextField login = new JTextField(20);
        JLabel label2 = new JLabel("Пароль:"); JPasswordField password = new JPasswordField(20);
        panel1.add(label1); panel1.add(login); panel2.add(label2); panel2.add(password);

        JButton ok = new JButton("Войти");
        panel3.add(ok);

        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = login.getText();
                String rpassword = new String(password.getPassword());
                if ("manager".equals(username) && "manager".equals(rpassword)) {
                    ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                    client.writeLine("Пользователь авторизовался как 'Тренер'");
                    frame.setEnabled(true); window.setVisible(false);
                }
                else {
                    ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                    client.writeLine("Пользователь не авторизовался как 'Тренер'");
                    JOptionPane.showMessageDialog(window, "Неправильный логин или пароль!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    box.setSelectedIndex(0); window.setVisible(false); frame.setEnabled(true);
                }
            }
        });

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                box.setSelectedIndex(0); frame.setEnabled(true);
            }
        });
        window.add(panel); window.revalidate(); window.setResizable(true); window.setVisible(true);
    }
}