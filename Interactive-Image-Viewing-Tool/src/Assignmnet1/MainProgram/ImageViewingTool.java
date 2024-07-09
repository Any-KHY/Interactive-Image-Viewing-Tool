package Assignmnet1.MainProgram;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImageViewingTool extends JFrame implements ActionListener {

    public static final int WINDOW_WIDTH = 1280;
    public static final int WINDOW_HEIGHT = 720;
    public static BufferedImage image = null;


    // Panels
    public static InformationPanel informationBar;
    public static ImagePanel imagePanel;
    private ToolPanel toolPanel;

    private JMenuItem openItem, quitItem; // Menu items in the File tab

    // Constructor
    public ImageViewingTool() {
        JFrame frame = new JFrame("Assignment 1 - Interactive ImagePanel Viewing Tool"); // Outer frame for the Tool

        // Window setting
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // allow close with close button on the right
        frame.setResizable(false); // not allow to resize window
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);

        // add menu bar to JFrame
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // add file menu with menu items
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);

        openItem = new JMenuItem("Open");
        openItem.addActionListener(this);
        fileMenu.add(openItem);

        quitItem = new JMenuItem("Quit");
        quitItem.addActionListener(this);
        fileMenu.add(quitItem);


        // display area
        Container content = frame.getContentPane();
        content.setLayout(null);

        // load default image
        try {
            image = ImageIO.read(new File("Res/default.jpg"));
        } catch (IOException e){
            JOptionPane.showMessageDialog(ImageViewingTool.this,
                    "Default File Not FOUND.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // add pamel to the display area
        toolPanel = new ToolPanel(new Rectangle(0,0,WINDOW_WIDTH,50 ));
        content.add(toolPanel);

        informationBar = new InformationPanel(new Rectangle(0,50,WINDOW_WIDTH,50 ));
        content.add(informationBar);

        imagePanel = new ImagePanel(new Rectangle(0,100, WINDOW_WIDTH, WINDOW_HEIGHT));
        content.add(imagePanel);

        frame.setLocationRelativeTo(null); // set location to the center
        frame.setVisible(true); // set visible

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        // respond to the menu
        JComponent source = (JComponent) e.getSource();
         if (source == openItem) {
             // select a file
             JFileChooser chooser = new JFileChooser("./");
             int retVal = chooser.showOpenDialog(this);
             if(retVal == JFileChooser.APPROVE_OPTION){
                 File chosenFile = chooser.getSelectedFile();
                 try {
                     image = ImageIO.read(chosenFile);
                     this.imagePanel.repaint();
                 } catch (IOException ex) {
                     JOptionPane.showMessageDialog(ImageViewingTool.this,
                             "Incorrect format of file",
                             "Error", JOptionPane.ERROR_MESSAGE);
                 }
             }
         } else if (source == quitItem) {
             System.exit(0);
         }
    }

    public static void main(String[] args) {
        new ImageViewingTool();
    }

}
