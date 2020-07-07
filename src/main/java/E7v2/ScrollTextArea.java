package E7v2;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Dimension;

public class ScrollTextArea {
    JFrame frame;
    JTextArea txt;
    JScrollPane scroll;
    int x,y;
    public ScrollTextArea(JFrame frame,int x, int y){
        this.frame = frame;
        
        this.x = x;
        this.y = y;
        GridBagConstraints c = new GridBagConstraints();
        frame.setVisible(true);
        frame.setSize(x,y);
        txt = new JTextArea();
        txt.setLineWrap(true);
        txt.setMinimumSize(new Dimension( 200, 100 ));
        c.gridx = 5;
        c.gridy = 6 + 6;
        
        scroll=new JScrollPane(txt);

        frame.add(scroll,c);


    }
}