package Assignmnet1.MainProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import static Assignmnet1.MainProgram.ImageViewingTool.imagePanel;

public class InformationPanel extends JPanel implements MouseMotionListener {

    private JTextField xField = new JTextField(5),
                       yField = new JTextField(5),
                       rField = new JTextField(5),
                       gField = new JTextField(5),
                       bField = new JTextField(5);

    public InformationPanel(Rectangle bounds){
        setLayout(new FlowLayout()); // arrange the text fields horizontally
        setBounds(bounds);
        setOpaque(true);

        add(new JLabel("X"));
        add(xField);
        add(new JLabel("Y"));
        add(yField);
        add(new JLabel("R"));
        add(rField);
        add(new JLabel("G"));
        add(gField);
        add(new JLabel("B"));
        add(bField);
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();
        int rbg = 0;
        if (mouseX >= ImagePanel.imageXOffset
                && mouseX < ImageViewingTool.image.getWidth()+ImagePanel.imageXOffset
                && mouseY >= 0
                && mouseY < ImageViewingTool.image.getHeight()) { // check RGB only if moving in the image

            int x = mouseX-ImagePanel.imageXOffset;
            int y = mouseY;

            if(imagePanel.getScaleX() == -1){
                x = ImageViewingTool.image.getWidth() - x - 1;
            }

            if (imagePanel.getScaleY() == -1) {
                y = ImageViewingTool.image.getHeight() - y - 1;
            }

            rbg = ImageViewingTool.image.getRGB(x,y);

        }

        xField.setText(Integer.toString(mouseX));
        yField.setText(Integer.toString(mouseY));
        rField.setText(Integer.toString((rbg>>16 & 0xff)));
        gField.setText(Integer.toString((rbg>>8 & 0xff)));
        bField.setText(Integer.toString((rbg>>0 & 0xff)));

    }

}
