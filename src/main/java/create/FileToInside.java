package create;

import java.io.*;
import java.util.Arrays;

public class FileToInside {

    public static void main(String[] args) {
        FileToInside t = new FileToInside();
//        String fileName = "G:\\BD1702\\Jxust\\maven\\repository\\com\\google\\zxing\\core\\3.3.0\\core-3.3.0.jar";
        String fileName = "L:\\Nick\\Documents\\WeChat Files\\Charles-song-323\\Files\\防锁屏.vbs";
//        String fileName = "L:\\Nick\\Downloads\\qrcode_java0.50beta10\\qrcode_java0.50beta10\\lib\\Qrcode.jar";

//        从那个文件开始读取
        int start = 1;

        File file = new File(fileName);
        if (file.isFile()){
            t.fileToClipbord(file);
        } else if (file.isDirectory()){
            File[] files = file.listFiles();
            for (int i = start; i <= files.length; i ++){
                t.fileToClipbord(files[i-1]);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }


    }


    private void fileToClipbord(File file){
        long fileLen = file.length();
        System.out.println("文件大小："+fileLen);
        try ( InputStream in = new FileInputStream(file)) {
            Thread.sleep(2000);
            byte[] b = new byte[1024*100];
            int len = -1;
            int times = 0;
            String start = "scl=";
            while((len = in.read(b)) != -1){
                if (times == fileLen/(1024*100)){
                    start = "scl=end="+file.getName()+"=";
                }
                String con = start+times+"="+(Arrays.toString(Arrays.copyOf(b,len)))
                        .replace("]","")
                        .replaceAll(" ","");
                System.out.println(len+start+times);
//                System.out.println(con.length());
                QRCodeEvents.setClipbordContents(con);
//                QRCodeEvents.setClipbordContents((Arrays.toString(b)).replace("[","{").replace("]","}"));
                times++;
                Thread.sleep(300);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
