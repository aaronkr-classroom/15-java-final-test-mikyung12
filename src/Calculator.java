import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private StringBuilder currentInput;

    public Calculator() {
        setTitle("계산기");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        currentInput = new StringBuilder();

        display = new JTextField();
        display.setFont(new Font("Arial", Font.PLAIN, 24));
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);

        add(display, BorderLayout.NORTH);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4));

        String[] buttons = { 
            "7", "8", "9", "+",
            "4", "5", "6", "-",
            "1", "2", "3", "*",
            "0", "AC", "=", "/"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            button.setFont(new Font("Arial", Font.PLAIN, 24));
            button.addActionListener(this);
            panel.add(button);
        }

        add(panel, BorderLayout.CENTER);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("=")) {
            try {
                String result = evaluateExpression(currentInput.toString());
                display.setText(result);
                currentInput = new StringBuilder(result);    
            } catch (Exception ex) {
                display.setText("Error");
                currentInput = new StringBuilder();
            }
        } else if (command.equals("AC")) {
            currentInput.setLength(0);
            display.setText("");
        } else {
            currentInput.append(command);
            display.setText(currentInput.toString());
        }
    }

    private String evaluateExpression(String expression) {
        try {
            ScriptEngine engine = new ScriptEngineManager().getEngineByName("JavaScript");
            return String.valueOf(engine.eval(expression));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Calculator calculator = new Calculator();
                calculator.setVisible(true);
            }
        });
    }

 
    public static class ExMenu01 extends JFrame {
        public ExMenu01() {
            setTitle("메뉴 만들기");
            setSize(400, 200);

            JMenuBar menuBar = new JMenuBar();

            JMenu menu01 = new JMenu("File");
            JMenu menu02 = new JMenu("Edit");
            JMenu menu03 = new JMenu("Help");
            menuBar.add(menu01);
            menuBar.add(menu02);
            menuBar.add(menu03);

            JMenuItem item01 = new JMenuItem("New");
            JMenuItem item02 = new JMenuItem("Open");
            menu01.add(item01);
            menu01.add(item02);

            setJMenuBar(menuBar);

            setLayout(null);
            setVisible(true);
        }

        public static void main(String[] args) {
            new ExMenu01();
        }
    }
}