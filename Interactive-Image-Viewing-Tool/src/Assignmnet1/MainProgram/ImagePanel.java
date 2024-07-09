package Assignmnet1.MainProgram;

import javax.swing.*;
import java.awt.*;

import static Assignmnet1.MainProgram.ImageViewingTool.*;

public class ImagePanel extends JPanel {
    private boolean ori, flipX, flipY, flipXY, neg;
    private float scaleX = 1.0f, scaleY = 1.0f;
    private float translateX = 0, translateY = 0;
    private boolean isNeg = false;
    public static int imageXOffset = (WINDOW_WIDTH/2)-(ImageViewingTool.image.getWidth()/2);

    // constructor
    public ImagePanel(Rectangle bounds) {
        // Panel setting
        setLayout(null);
        setBounds(bounds);
        setOpaque(false);

        resetBool();
        addMouseMotionListener(ImageViewingTool.informationBar);
    }

    // Getters
    public float getScaleX() {
        return scaleX;
    }

    public float getScaleY() {
        return scaleY;
    }


    // setters
    public void setOri(boolean ori) {
        this.ori = ori;
    }

    public void setFlipX(boolean flipX) {
        this.flipX = flipX;
    }

    public void setFlipY(boolean flipY) {
        this.flipY = flipY;
    }

    public void setFlipXY(boolean flipXY) {
        this.flipXY = flipXY;
    }

    public void setNeg(boolean neg) {
        this.neg = neg;
    }

    private void setTranslateX(){
        if (scaleX == -1) {
            translateX -= (ImageViewingTool.image.getWidth() + imageXOffset * 2);
        } else {
            translateX = 0;
        }
    }

    private void setTranslateY(){
        if (scaleY == -1) {
            translateY -= (ImageViewingTool.image.getHeight());
        } else {
            translateY = 0;
        }
    }

    public void resetBool(){
        ori = true;
        flipX = false;
        flipY = false;
        flipXY = false;
        neg = false;

    }

    private void negRGB(Graphics2D g2){
        isNeg = !isNeg;
        int width = ImageViewingTool.image.getWidth();
        int height = ImageViewingTool.image.getHeight();

        for (int y=0; y<height; y++) {
            for (int x = 0; x < width; x++) {
                int rgb = ImageViewingTool.image.getRGB(x, y);
                int negRGB = rgb ^ 0x00ffffff; // neg image
                ImageViewingTool.image.setRGB(x, y, negRGB);
            }
        }
    }

    private void imageRender( Graphics2D g2){

        g2.scale(scaleX, scaleY);
        g2.translate(translateX,translateY);
        g2.drawImage(ImageViewingTool.image,imageXOffset,0,null);
    }

    public void paintComponent (Graphics g){
        Graphics2D g2 = (Graphics2D) g;  // case to Graphics2D

        if(ImageViewingTool.image != null){
            if (ori == true){

                scaleX = 1;
                scaleY = 1;
                translateX = 0;
                translateY = 0;

                if (isNeg == true) {
                    negRGB(g2);
                }
                imageRender(g2);

            } else if (flipX == true) {
                scaleX *= -1;
                setTranslateX();
                imageRender(g2);
            } else if (flipY == true) {
                scaleY *= -1;
                setTranslateY();
                imageRender(g2);
            } else if (flipXY == true) {
                scaleX *= -1;
                scaleY *= -1;
                setTranslateX();
                setTranslateY();
                imageRender(g2);

            } else if (neg == true) {
                negRGB(g2);
                imageRender(g2);
            }

        } else {
            JOptionPane.showMessageDialog(ImagePanel.this,
                    "Incorrect format of file",
                    "Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
