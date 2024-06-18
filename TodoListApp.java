import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TodoListApp {
    private DefaultListModel<String> listModel;
    private JList<String> todoList;

    public TodoListApp() {
        JFrame frame = new JFrame("To-Do List Application");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new BorderLayout());

        // List Model and JList
        listModel = new DefaultListModel<>();
        todoList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(todoList);

        // Panels
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BorderLayout());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        // TextField and Buttons
        JTextField todoTextField = new JTextField();
        JButton addButton = new JButton("Add");
        JButton deleteButton = new JButton("Delete");
        JButton pinButton = new JButton("Pin");
        JButton addDateButton = new JButton("Add Date");

        // Adding components to panels
        inputPanel.add(todoTextField, BorderLayout.CENTER);
        inputPanel.add(addButton, BorderLayout.EAST);

        buttonPanel.add(deleteButton);
        buttonPanel.add(pinButton);
        buttonPanel.add(addDateButton);

        // Adding panels to frame
        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(inputPanel, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Button Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String todoText = todoTextField.getText();
                if (!todoText.isEmpty()) {
                    listModel.addElement(todoText);
                    todoTextField.setText("");
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                }
            }
        });

        pinButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedValue = listModel.getElementAt(selectedIndex);
                    listModel.remove(selectedIndex);
                    listModel.add(0, selectedValue);
                }
            }
        });

        addDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoList.getSelectedIndex();
                if (selectedIndex != -1) {
                    String selectedValue = listModel.getElementAt(selectedIndex);
                    String newValue = selectedValue + " (Due: " + new java.util.Date().toString() + ")";
                    listModel.set(selectedIndex, newValue);
                }
            }
        });

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TodoListApp();
            }
        });
    }
}
