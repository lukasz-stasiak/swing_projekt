import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyPanel extends JPanel implements ActionListener {

    JTextField textField;
    JButton button;

    public MyPanel() {
        setLayout(null); // wybor menagera tutaj zaden
        textField = new JTextField();
        textField.setBounds(10,10, 100,100);

        button = new JButton();
        button.setBounds(20,150,100,30);
        button.setText("Kliknij");
        button.addActionListener(this);

        add(button);
        add(textField);

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(1200, 600);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        textField.setText("Bum");

    }
}
