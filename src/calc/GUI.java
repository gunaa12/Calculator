package src.calc;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements Runnable {
    //attributes
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel ansPanel;
    private JPanel operPanel;
    private Logic logic;

    protected JTextField ansField;

    //methods
    public void run() {
        setUpFrameWork();
    }

    private void setUpFrameWork() {
        logic = new Logic();

        frame = new JFrame("Calculator");
        // frame.setTitle("Calculator");

        ansPanel = new JPanel(new FlowLayout());
        ansField = new JTextField("", 30);
        ansPanel.add(ansField);
        frame.add(ansPanel, BorderLayout.NORTH);
        
        mainPanel = new JPanel(new GridLayout(4, 3));
        for (int i = 1; i <= 9; i++) {
            String num = "" + i;
            JButton button = new JButton(num);
            button.addActionListener(logic);
            button.setActionCommand("" + i);
            mainPanel.add(button);
        }
        JButton clear = new JButton("C");
        mainPanel.add(clear);
        clear.addActionListener(logic);
        clear.setActionCommand("C");
        JButton zeroButton = new JButton("0");
        mainPanel.add(zeroButton);
        zeroButton.addActionListener(logic);
        zeroButton.setActionCommand("0");
        JButton solve = new JButton("=");
        mainPanel.add(solve);
        solve.addActionListener(logic);
        solve.setActionCommand("=");
        frame.add(mainPanel, BorderLayout.CENTER);

        operPanel = new JPanel(new GridLayout(1, 4));
        JButton addBtn = new JButton("+");
        JButton subBtn = new JButton("-");
        JButton multBtn = new JButton("*");
        JButton divBtn = new JButton("/");
        operPanel.add(addBtn);
        operPanel.add(subBtn);
        operPanel.add(multBtn);
        operPanel.add(divBtn);
        addBtn.addActionListener(logic);
        subBtn.addActionListener(logic);
        multBtn.addActionListener(logic);
        divBtn.addActionListener(logic);
        addBtn.setActionCommand("+");
        subBtn.setActionCommand("-");
        multBtn.setActionCommand("*");
        divBtn.setActionCommand("/");
        frame.add(operPanel, BorderLayout.SOUTH);

        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public class Logic implements ActionListener{
        //methods
        public float calculate(String str) {
            float num1, num2;
            if (str.indexOf("+") != -1) {
                num1 = Integer.parseInt(str.substring(0, str.indexOf("+")));
                num2 = Integer.parseInt(str.substring(str.indexOf("+") + 1));
                return (num1 + num2);
            }
            else if (str.indexOf("-") != -1) {
                num1 = Integer.parseInt(str.substring(0, str.indexOf("-")));
                num2 = Integer.parseInt(str.substring(str.indexOf("-") + 1));
                return (num1 - num2);
            }
            else if (str.indexOf("*") != -1) {
                num1 = Integer.parseInt(str.substring(0, str.indexOf("*")));
                num2 = Integer.parseInt(str.substring(str.indexOf("*") + 1));
                return (num1 * num2);
            }
            else if (str.indexOf("/") != -1) {
                num1 = Integer.parseInt(str.substring(0, str.indexOf("/")));
                num2 = Integer.parseInt(str.substring(str.indexOf("/") + 1));
                return num1 / num2;
            }
            return 0;
        }
    
        public void actionPerformed(ActionEvent action) {
            if (action.getActionCommand().equals("=")) {
                float ans = calculate(ansField.getText());
                ansField.setText("" + ans);
            }
            else if (action.getActionCommand().equals("C")) {
                ansField.setText("");
            }
            else {
                String updatedAnsField = ansField.getText() + action.getActionCommand();
                ansField.setText(updatedAnsField);
            }
        }
    }
}
