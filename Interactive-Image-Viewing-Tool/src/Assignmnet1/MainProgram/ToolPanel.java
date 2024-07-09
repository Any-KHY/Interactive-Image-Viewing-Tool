package Assignmnet1.MainProgram;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Assignmnet1.MainProgram.ImageViewingTool.imagePanel;

public class ToolPanel extends JPanel implements ActionListener {

    private JButton oriButton, flipXButton, flipYButton , flipXYButton, negButton;

    public ToolPanel(Rectangle bounds){

        // Panel setting
        setLayout(new FlowLayout()); // to arrange the buttons horizontally
        setBounds(bounds);
        setOpaque(true);

        // add Buttons
        oriButton = new JButton("Original Image");
        add(oriButton);
        oriButton.addActionListener(this);

        flipXButton = new JButton("Flip X Image");
        add(flipXButton);
        flipXButton.addActionListener(this);

        flipYButton = new JButton("Flip Y Image");
        add(flipYButton);
        flipYButton.addActionListener(this);

        flipXYButton = new JButton("Flip XY Image");
        add(flipXYButton);
        flipXYButton.addActionListener(this);

        negButton = new JButton("Negate Image");
        add(negButton);
        negButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JComponent source = (JComponent) e.getSource();
        if (source == oriButton) {

            imagePanel.resetBool();
            imagePanel.repaint();

        } else if (source == flipXButton) {

            imagePanel.resetBool();
            imagePanel.setOri(false);
            imagePanel.setFlipX(true);
            imagePanel.repaint();

        } else if (source == flipYButton) {

            imagePanel.resetBool();
            imagePanel.setOri(false);
            imagePanel.setFlipY(true);
            imagePanel.repaint();

        } else if (source == flipXYButton) {

            imagePanel.resetBool();
            imagePanel.setOri(false);
            imagePanel.setFlipXY(true);
            imagePanel.repaint();

        } else if (source == negButton) {

            imagePanel.resetBool();
            imagePanel.setOri(false);
            imagePanel.setNeg(true);
            imagePanel.repaint();

        }
    }
}
