package FilesProject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class SetPanels {

    private static final String url = "jdbc:mysql://localhost:3306/exercises";
    private static final String user = "root";
    private static final String password = "A200775271252a";

    final String[] value = new String[1];

    public static DefaultTableModel tableM;
    public static DefaultTableModel tableTu;
    public static DefaultTableModel tableWed;
    public static DefaultTableModel tableTh;
    public static DefaultTableModel tableFr;
    public static DefaultTableModel tableSt;
    public static DefaultTableModel tableSn;
    public static DefaultTableModel tableRec;

    static final Object[] temp = new String[1];

    public JComboBox<String> box = new JComboBox<>();

    public void setPanels(JFrame frame) throws Exception {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(250, 332));
        panel.setBackground(new Color(169, 169, 169));
        JPanel panel2 = new JPanel();
        panel2.setPreferredSize(new Dimension(500, 332));
        panel2.setBackground(new Color(169, 169, 169));
        JPanel panel3 = new JPanel();
        panel3.setPreferredSize(new Dimension(250, 332));
        panel3.setBackground(new Color(169, 169, 169));
        JPanel panel4 = new JPanel();
        panel4.setPreferredSize(new Dimension(259, 332));
        panel4.setBackground((new Color(169, 169, 169)));
        JPanel panel5 = new JPanel();
        panel5.setPreferredSize(new Dimension(1000, 332));
        panel5.setBackground((new Color(169, 169, 169)));
        JPanel panel6 = new JPanel();
        panel6.setPreferredSize(new Dimension(250, 332));
        panel6.setBackground((new Color(169, 169, 169)));
        JPanel panel7 = new JPanel();
        panel7.setPreferredSize(new Dimension(500, 332));
        panel7.setBackground(new Color(169, 169, 169));
        JPanel panel8 = new JPanel();
        panel8.setPreferredSize(new Dimension(250, 332));
        panel8.setBackground(new Color(169, 169, 169));
        JPanel panel9 = new JPanel();
        panel9.setPreferredSize(new Dimension(250, 332));
        panel9.setBackground(new Color(169, 169, 169));
        JPanel panel10 = new JPanel();
        panel10.setPreferredSize(new Dimension(259, 332));
        panel10.setBackground(new Color(169, 169, 169));
        JPanel panel11 = new JPanel();
        panel11.setPreferredSize(new Dimension(250, 332));
        panel11.setBackground(new Color(169, 169, 169));

        String[] monday = {"Понедельник"};
        String[] tuesday = {"Вторник"};
        String[] wednesday = {"Среда"};
        String[] thursday = {"Четверг"};
        String[] friday = {"Пятница"};
        String[] saturday = {"Суббота"};
        String[] sunday = {"Воскресенье"};
        String[] records = {"Рекорды"};
        Object[][] data = {};

        tableM = new DefaultTableModel(data, monday);
        tableTu = new DefaultTableModel(data, tuesday);
        tableWed = new DefaultTableModel(data, wednesday);
        tableTh = new DefaultTableModel(data, thursday);
        tableFr = new DefaultTableModel(data, friday);
        tableSt = new DefaultTableModel(data, saturday);
        tableSn = new DefaultTableModel(data, sunday);
        tableRec = new DefaultTableModel(data, records);

        ChangeEditable(panel, tableM, 250, 332);
        ChangeEditable(panel9, tableTu, 250, 332);
        ChangeEditable(panel4, tableWed, 259, 332);
        ChangeEditable(panel3, tableTh, 250, 332);
        ChangeEditable(panel6, tableFr, 250, 300);
        ChangeEditable(panel11, tableSt, 250, 300);
        ChangeEditable(panel10, tableSn, 259, 300);
        ChangeEditable(panel8, tableRec, 250, 300);

        ConnectionServer base = new ConnectionServer("127.0.0.1", 8000);
        base.writeLine("Произошло подключение к базе данных");
        setExercises(tableM, "exercisemonday"); setExercises(tableTu, "exercisetuesday"); setExercises(tableWed, "exercisewednesday");
        setExercises(tableTh, "exercisethursday"); setExercises(tableFr,"exercisefriday"); setExercises(tableSt, "exercisesaturday");
        setExercises(tableSn, "exercisesunday"); setExercises(tableRec, "exerciserecords");
        ConnectionServer datum = new ConnectionServer("127.0.0.1", 8000);
        datum.writeLine("Все данные из базы данных выгружены в приложение");

        JPanel options = new JPanel() {};
        options.setPreferredSize(new Dimension(1000, 35));
        options.setBackground(new Color(169, 169, 169));
        box.insertItemAt("Ученик", 0); box.addItem("Тренер"); box.setSelectedIndex(0);
        JButton addExercise = new JButton("Добавить упражнение");

        addExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionServer client = new ConnectionServer("127.0.0.1", 8000); String tableName = new String();
                client.writeLine("Добавление упражнения");
                value[0] = JOptionPane.showInputDialog(frame, "Какой день недели?", "День недели", JOptionPane.QUESTION_MESSAGE);
                if (value[0] == null || value[0].equals("")) {
                    ConnectionServer message = new ConnectionServer("127.0.0.1", 8000);
                    message.writeLine("Сообщение невалидное");
                    return;
                }
                temp[0] = JOptionPane.showInputDialog(frame, "Какое упражнение Вы хотите добавить?", "Добавить упражнение", JOptionPane.QUESTION_MESSAGE);
                if (temp[0] == null || temp[0].equals("")) {
                    ConnectionServer message = new ConnectionServer("127.0.0.1", 8000);
                    message.writeLine("Название упражнения невалидное");
                    return;
                }
                if (value[0].equals("Понедельник")) { tableName = "exercisemonday"; } if (value[0].equals("Вторник")) { tableName = "exercisetuesday"; }
                if (value[0].equals("Среда")) { tableName = "exercisewednesday"; } if (value[0].equals("Четверг")) { tableName = "exercisethursday"; }
                if (value[0].equals("Пятница")) { tableName = "exercisefriday"; } if (value[0].equals("Суббота")) { tableName = "exercisesaturday"; }
                if (value[0].equals("Воскресенье")) { tableName = "exercisesunday"; }

                ConnectionServer addInfo = new ConnectionServer("127.0.0.1", 8000);
                addInfo.writeLine("Отправка данных в базу данных");
                String insertBase = "INSERT INTO " + tableName + " (" + value[0] +  ") " + "VALUES (?)";
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement prestate = connection.prepareStatement(insertBase)) {
                     prestate.setString(1, temp[0].toString());
                     prestate.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                ConnectionServer clientDay = new ConnectionServer("127.0.0.1", 8000);
                clientDay.writeLine("День недели: " + value[0]);
                if (tableM.getColumnName(0).equals(value[0])) {
                    tableM.addRow(temp);
                }
                if (tableTu.getColumnName(0).equals(value[0])) {
                    tableTu.addRow(temp);
                }
                if (tableWed.getColumnName(0).equals(value[0])) {
                    tableWed.addRow(temp);
                }
                if (tableTh.getColumnName(0).equals(value[0])) {
                    tableTh.addRow(temp);
                }
                if (tableFr.getColumnName(0).equals(value[0])) {
                    tableFr.addRow(temp);
                }
                if (tableSt.getColumnName(0).equals(value[0])) {
                    tableSt.addRow(temp);
                }
                if (tableSn.getColumnName(0).equals(value[0])) {
                    tableSn.addRow(temp);
                }

                ConnectionServer clientExc = new ConnectionServer("127.0.0.1", 8000);
                clientExc.writeLine("Упражнение: " + temp[0]);
            }
        });

        JButton deleteExercise = new JButton("Удалить упражнение");
        deleteExercise.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionServer client = new ConnectionServer("127.0.0.1", 8000); String tableName = new String();
                client.writeLine("Удаление упражнения: ");
                value[0] = JOptionPane.showInputDialog(frame, "Какой день недели?", "День недели", JOptionPane.QUESTION_MESSAGE);
                if (value[0] == null || value[0].equals("")) {
                    ConnectionServer message = new ConnectionServer("127.0.0.1", 8000);
                    message.writeLine("Сообщение невалидное");
                    return;
                }
                if (value[0].equals("Понедельник")) { tableName = "exercisemonday"; } if (value[0].equals("Вторник")) { tableName = "exercisetuesday"; }
                if (value[0].equals("Среда")) { tableName = "exercisewednesday"; } if (value[0].equals("Четверг")) { tableName = "exercisethursday"; }
                if (value[0].equals("Пятница")) { tableName = "exercisefriday"; } if (value[0].equals("Суббота")) { tableName = "exercisesaturday"; }
                if (value[0].equals("Воскресенье")) { tableName = "exercisesunday"; }

                ConnectionServer clientDay = new ConnectionServer("127.0.0.1", 8000);
                clientDay.writeLine("День недели: " + value[0]);
                temp[0] = JOptionPane.showInputDialog(frame, "Какое упражнение Вы хотите удалить?", "Удалить упражнение", JOptionPane.QUESTION_MESSAGE);
                if (temp[0] == null || temp[0].equals("")) {
                    ConnectionServer message = new ConnectionServer("127.0.0.1", 8000);
                    message.writeLine("Название упражнения невалидное");
                    return;
                }

                ConnectionServer addInfo = new ConnectionServer("127.0.0.1", 8000);
                addInfo.writeLine("Удаление данных из базы данных");
                String deleteInsert = "DELETE FROM " + tableName + " WHERE " + value[0] + " = ?";
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement prestate = connection.prepareStatement(deleteInsert))
                {
                    prestate.setString(1, temp[0].toString());
                    prestate.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                if (tableM.getColumnName(0).equals(value[0])) {
                    deleteRow(tableM, temp);
                }
                if (tableTu.getColumnName(0).equals(value[0])) {
                    deleteRow(tableTu, temp);
                }
                if (tableWed.getColumnName(0).equals(value[0])) {
                    deleteRow(tableWed, temp);
                }
                if (tableTh.getColumnName(0).equals(value[0])) {
                    deleteRow(tableTh, temp);
                }
                if (tableFr.getColumnName(0).equals(value[0])) {
                    deleteRow(tableFr, temp);
                }
                if (tableSt.getColumnName(0).equals(value[0])) {
                    deleteRow(tableSt, temp);
                }
                if (tableSn.getColumnName(0).equals(value[0])) {
                    deleteRow(tableSn, temp);
                }

                ConnectionServer clientExc = new ConnectionServer("127.0.0.1", 8000);
                clientExc.writeLine("Упражнение: " + temp[0]);
            }
        });

        JButton addRecord = new JButton("Добавить рекорд");
        addRecord.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ConnectionServer client = new ConnectionServer("127.0.0.1", 8000);
                client.writeLine("Добавление рекорда");
                Object[] record = new Object[1];
                record[0] = JOptionPane.showInputDialog(frame, "Добавить рекорд", "Рекорд", JOptionPane.QUESTION_MESSAGE);
                if (record[0] == null || record[0].equals("")) {
                    ConnectionServer message = new ConnectionServer("127.0.0.1", 8000);
                    message.writeLine("Сообщение невалидное");
                    return;
                }

                ConnectionServer addInfo = new ConnectionServer("127.0.0.1", 8000);
                addInfo.writeLine("Отправка данных в базу данных");
                String insertRecord = "INSERT INTO exerciserecords (Рекорды) VALUES(?)";
                try (Connection connection = DriverManager.getConnection(url, user, password);
                     PreparedStatement prestate = connection.prepareStatement(insertRecord)) {
                     prestate.setString(1, record[0].toString());
                     prestate.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                tableRec.addRow(record);
                ConnectionServer clientRec = new ConnectionServer("127.0.0.1", 8000);
                clientRec.writeLine("Рекорд: " + record[0]);
            }
        });
        box.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (box.getSelectedIndex() == 1) {
                    Authorization auto = new Authorization(); auto.Login(frame, box);
                    addExercise.setEnabled(true); deleteExercise.setEnabled(true); addRecord.setEnabled(true);
                }
                if (box.getSelectedIndex() == 0) {
                    ConnectionServer choose = new ConnectionServer("127.0.0.1", 8000);
                    choose.writeLine("Тип пользователя 'Ученик' выбран");
                    addExercise.setEnabled(false); deleteExercise.setEnabled(false); addRecord.setEnabled(false);
                }
            }
        });

        frame.add(options, BorderLayout.NORTH);

        options.setLayout(new FlowLayout(FlowLayout.LEFT));
        addExercise.setEnabled(false); deleteExercise.setEnabled(false); addRecord.setEnabled(false);
        options.add(addExercise);
        options.add(deleteExercise);
        options.add(addRecord);
        options.add(box);

        frame.add(panel, BorderLayout.WEST);
        frame.add(panel2, BorderLayout.CENTER);
        frame.add(panel3, BorderLayout.EAST);

        panel2.setLayout(new BorderLayout());
        panel2.add(panel4, BorderLayout.EAST);

        frame.add(panel5, BorderLayout.SOUTH);

        panel5.setLayout(new BorderLayout());
        panel5.add(panel6, BorderLayout.WEST);
        panel5.add(panel7, BorderLayout.CENTER);
        panel5.add(panel8, BorderLayout.EAST);

        panel2.add(panel9, BorderLayout.WEST);

        panel7.setLayout(new BorderLayout());
        panel7.add(panel11, BorderLayout.WEST);
        panel7.add(panel10, BorderLayout.EAST);
    }

    public static void deleteRow(DefaultTableModel model, Object[] exercise) {
        for (int i = 0; i < model.getRowCount(); i++)
        {
            if (model.getValueAt(i, 0).equals(exercise[0])) {
                model.removeRow(i);
            }
        }
    }

    public static void ChangeEditable(JPanel panel, DefaultTableModel tableDef, int width, int height) {
        JTable table = new JTable(tableDef) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JScrollPane scroll = new JScrollPane(table); scroll.setPreferredSize(new Dimension(width, height));
        panel.add(scroll);
    }

    public static void setExercises(DefaultTableModel curtabel, String name) throws SQLException {
        String query = "SELECT * FROM " + name;

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement statement = connection.createStatement();
             ResultSet result = statement.executeQuery(query)) {

            while (result.next()) {
                temp[0] = result.getString(1);
                curtabel.addRow(temp);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
