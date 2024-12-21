package FilesProject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class CloseAction {
    public void closeAction(JFrame frame) {

        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                frame.setEnabled(false);
                ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                client.writeLine("Пользователь пытается завершить работу с приложением.");
                JFrame window = new JFrame("Выход из программы");
                window.setSize(300, 150); window.setLocationRelativeTo(null);

                JPanel panel = new JPanel(); panel.setLayout(new GridLayout(2, 1));
                JPanel panel1 = new JPanel(); panel1.setSize(150, 75);
                panel1.setLayout(new GridBagLayout()); GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.CENTER; gbc.insets = new Insets(41, 75, 33, 75);
                JPanel panel2 = new JPanel(); panel2.setSize(150, 75);
                JPanel panel3 = new JPanel(); JPanel panel4 = new JPanel();
                panel3.setSize(75, 75); panel4.setSize(75, 75);

                JLabel label = new JLabel("Точно хотите выйти?"); label.setSize(150, 75);
                JButton exit = new JButton("Да"); JButton no = new JButton("Нет");
                no.setSize(75, 75); exit.setSize(75, 75);

                exit.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {
                       ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                       client.writeLine("Пользователь вышел из приложения");
                       System.exit(0);
                   }
                });

                no.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                        client.writeLine("Пользователь не вышел из приложения");
                        frame.setEnabled(true); window.setVisible(false);
                    }
                });

                window.add(panel); panel.add(panel1); panel.add(panel2); panel2.add(panel3); panel2.add(panel4);
                panel1.add(label, gbc); panel3.add(exit); panel4.add(no);
                window.setResizable(true); window.revalidate(); window.setVisible(true);
            }
        });
    }
}
