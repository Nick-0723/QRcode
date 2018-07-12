package com.scl;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.swing.*;

public class ShowImage extends JFrame{

    public ShowImage(){}


    public ShowImage(BufferedImage image) {
        JPanel panel=new JPanel(new BorderLayout());
        JLabel label=new JLabel(new ImageIcon(image));


        panel.add(label,BorderLayout.CENTER);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel,BorderLayout.CENTER);

        this.setSize(520, 550);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TextToQRcode");
        this.setVisible(true);
        this.setAlwaysOnTop(true);
    }

    public static void main(String[] args) {
        String text = QRCodeEvents.getSysClipboardText();
        if (text.length()>1024){
            JOptionPane.showMessageDialog(null,"您复制的字符超过1024个，可能会生成不出二维码！");
            return;
        }
        BufferedImage image = null;
        try {
            image = QRCodeEvents.generateQRCode(text,500,500);
        } catch (Exception e) {
            e.printStackTrace();
        }
        new ShowImage(image);
    }
}
