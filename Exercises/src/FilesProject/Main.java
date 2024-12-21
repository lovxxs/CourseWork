package FilesProject;

import javax.swing.*;
import java.awt.*;

public class Main {

    private final static JFrame frame = getframe();

    public static void main(String[] args) throws Exception {
        Authorization auto = new Authorization(); auto.Login(frame);
        ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
        client.writeLine("Пользователь запустил приложение");
        frame.setLayout(new BorderLayout()); SetPanels panels = new SetPanels(); panels.setPanels(frame);
        frame.revalidate(); CloseAction close = new CloseAction(); close.closeAction(frame);
    }

    public static JFrame getframe() {
        JFrame window = new JFrame("Расписание тренировок");
        window.setSize(1025, 700); window.setVisible(true);
        window.setLocationRelativeTo(null); window.setResizable(false);
        return window;
    }
}