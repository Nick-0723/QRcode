package create;

import java.awt.BorderLayout;
import java.io.File;
import javax.swing.*;

public class ShowImage extends JFrame{

    public ShowImage(){}


    public ShowImage(String url) {
        JPanel panel=new JPanel(new BorderLayout());

//        String urlString="L:\\Nick\\Desktop\\微信截图_20180308214156.png";
        JLabel label=new JLabel(new ImageIcon(url));


        panel.add(label,BorderLayout.CENTER);

        this.getContentPane().setLayout(new BorderLayout());
        this.getContentPane().add(panel,BorderLayout.CENTER);

        this.setSize(1200, 1200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("显示图像");
        this.setVisible(true);
        this.setAlwaysOnTop(true);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public static void main(String[] args) {
        CreateQR createQR = new CreateQR();
        File file = new File("L:\\Nick\\Desktop\\微信截图_20180308170912.png");
        long start = System.currentTimeMillis();
        createQR.create(file);
        long end = System.currentTimeMillis();
        System.out.println("当前文件大小为："+file.length()/1024+"M"+"  耗时："+(end-start)+"秒");
        ShowImage showImage=new ShowImage();
        showImage.carousel("L:\\Nick\\Desktop\\QR");
    }

    public void carousel(String path){
        File der = new File(path);
        File[] files = der.listFiles();
        for (File file : files){
            new ShowImage(path+"\\"+file.getName());
        }
    }

}