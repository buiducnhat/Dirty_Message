import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class DirtyMessage extends JFrame implements ActionListener {
    private String inputSting;
    private boolean isUpperState;
    private JLabel inputLabel, outputLabel;
    private JTextField inputTextField, outputTextField;
    private JButton submitButton;

    public DirtyMessage() {
        initComponents();
    }

    private void initComponents() {
        this.setTitle("Dirty Message");
        this.setIconImage(new ImageIcon("src\\app_icon.png").getImage());
        this.setBounds(500, 220, 550, 250);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLayout(null);

        inputLabel = new JLabel("Input");
        inputLabel.setBounds(20, 20, 50, 30);
        this.add(inputLabel);

        outputLabel = new JLabel("Output");
        outputLabel.setBounds(20, 70, 50, 30);
        this.add(outputLabel);

        inputTextField = new JTextField();
        inputTextField.setBounds(120, 20, 400, 30);
        this.add(inputTextField);

        outputTextField = new JTextField();
        outputTextField.setBounds(120, 70, 400, 30);
        outputTextField.setEditable(false);
        this.add(outputTextField);

        submitButton = new JButton("CONVERT");
        submitButton.setBounds(200, 120, 120, 60);
        submitButton.addActionListener(this::actionPerformed);
        this.add(submitButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        inputSting = inputTextField.getText();
        inputSting = convertString(inputSting);
        outputTextField.setText(inputSting);

        //Copy text after converted to clipboard.
        StringSelection stringSelection = new StringSelection(outputTextField.getText());
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);
        JOptionPane.showMessageDialog(this, "Copied!!!");
    }

    private String convertWord(String input) {
        char[] a = input.toCharArray();
        if (isUpperState) {
            for (int i = 0; i < a.length; i++) {
                if (i % 2 == 1) {
                    a[i] = String.valueOf(a[i]).toLowerCase().charAt(0);
                } else {
                    a[i] = String.valueOf(a[i]).toUpperCase().charAt(0);
                }
            }
        } else {
            for (int i = 0; i < a.length; i++) {
                if (i % 2 == 0) {
                    a[i] = String.valueOf(a[i]).toLowerCase().charAt(0);
                } else {
                    a[i] = String.valueOf(a[i]).toUpperCase().charAt(0);
                }
            }
        }
        isUpperState = !Character.isUpperCase(a[a.length - 1]);
        return String.valueOf(a);
    }

    private String convertString(String input) {
        String s = input;
        s = s.trim();
        isUpperState = false;

        String[] words = s.split(" ");

        for (int i = 0; i < words.length; i++) {
            words[i] = convertWord(words[i]);
        }

        s = String.join(" ", words);
        return s;
    }

    public static void main(String[] args) {
        new DirtyMessage();
    }
}
