package com.hejin;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class ShowImage extends JFrame{

    public ShowImage(){}


    public ShowImage(BufferedImage image) {
        JPanel panel=new JPanel(new BorderLayout());

//        String urlString="L:\\Nick\\Desktop\\微信截图_20180308214156.png";
        JLabel label=new JLabel(new ImageIcon(image));


        panel.add(label,BorderLayout.CENTER);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel,BorderLayout.CENTER);

        this.setSize(520, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("显示图像");
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        String text = QRCodeEvents.getSysClipboardText();
        BufferedImage image = null;
        try {
            image = QRCodeEvents.generateQRCode(text,500,500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ShowImage showImage=new ShowImage(image);
    }
}
