/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.calculator;

/**
 *
 * @author Алина
 */
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

public class Calculator extends JFrame implements ActionListener {

    static JFrame frame; 
    static JTextField result;
    static String a = " ", b = " ", operation = " ";

    public static void main(String[] args) { 
        Main listen = new Main() {
        };

        frame = new JFrame("Калькулятор"); 
        result = new JTextField(16); 
        result.setEditable(false); 

        ArrayList<JButton> btns = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            JButton jTmpButton = new JButton(Integer.toString(i));
            jTmpButton.addActionListener((ActionListener) listen);
            btns.add(jTmpButton); 
        }
        ArrayList<String> operations = new ArrayList<String>(); 
        operations.add("+");
        operations.add("-");
        operations.add("/");
        operations.add("*");
        operations.add("=");

        JPanel buttons = new JPanel();
        btns.forEach(buttons::add);
        operations.forEach(it -> {
            JButton jTmpButton = new JButton(it);
            buttons.add(jTmpButton);
        });

        GridLayout numsAndOpsLayout = new GridLayout(4, 4);
        buttons.setLayout(numsAndOpsLayout);

        JPanel mainPanel = new JPanel();
        mainPanel.add(result);
        mainPanel.add(buttons);

        frame.add(mainPanel);
        frame.setSize(360, 180);
        frame.setVisible(true); 
    }

    public void actionPerfomed(ActionEvent e) {  
        System.out.println(e.getActionCommand());

        String s = e.getActionCommand();
        if (s.charAt(0) >= '0' && s.charAt(0) <= '9') {
            if (operation.equals(" ")) {
                a = a + s;
            } else {
                b = b + s;
            }
            result.setText(a + operation + b);
        } else if (s.charAt(0) == 'c') {
            a = operation = b = "";
            result.setText(a + operation + b);
        } else if (s.charAt(0) == '=') {
            int rslt = switch (operation) {
                case "+" ->
                    Integer.parseInt(a) + Integer.parseInt(b);
                case "-" ->
                    Integer.parseInt(a) - Integer.parseInt(b);
                case "/" ->
                    Integer.parseInt(a) / Integer.parseInt(b);
                default ->
                    Integer.parseInt(a) * Integer.parseInt(b);
            };
            a = String.valueOf(result);
            result.setText(a);
            operation = b = "";
        } else {
            if (operation.equals(" ")) {
                operation = s;
            }
            result.setText(a + operation + b);
        }
    }
}
