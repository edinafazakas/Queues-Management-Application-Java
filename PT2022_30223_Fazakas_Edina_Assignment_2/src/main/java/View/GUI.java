package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Collections;

public class GUI extends JFrame{

        JFrame f;
        JTextField t1, t2, t3;
        JButton b1;
        JLabel l1, l2, l3, l;
        String text1, text2; int k;
        public GUI() throws Exception {
            f = new JFrame("QUEUE MANAGEMENT");
            f.setLayout(null);
            f.setVisible(true);
            f.setSize(2000, 1000);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setResizable(false);
            f.setLayout(new FlowLayout(FlowLayout.CENTER, 2000, 200));
            //t1 = new JTextField(100);

            l = new JLabel("* Random generated tasks *");
            l.setBounds(20, 10, 300, 25);


            l.setFont(new Font("cooper", Font.PLAIN, 20));

            f.add(l);


            /*t1.addActionListener(this);
            t2.addActionListener(this);
            t3.addActionListener(this);*/

        }


            public void actionPerformed (ActionEvent e)
            {

            }

}


