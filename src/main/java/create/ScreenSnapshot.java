package create;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class ScreenSnapshot {

    private String fileName = null;
    private String path = "L:\\Nick\\Desktop";

    public static void main(String[] args) {

    }

    public void images(){
        while (true){
            BufferedImage image = screenShot(1000, 1000);
            String value = QRCodeEvents.parseQRCode(image);
            if (value == null ) continue;
            if (value.startsWith("FileName:")) fileName = value.split(":")[1];
            if (value.equals("end")) break;
            writeToFile(value);
        }
    }

    public void writeToFile(String str){
        File file = new File(path,fileName);
        try(FileOutputStream out = new FileOutputStream(file)) {
            out.write(strToByte(str));
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] strToByte(String str){
        if (str == null) return null;
        String[] strs = str.split(",");
        byte[] b = new byte[strs.length];
        for (int i = 0; i < b.length; i++){
            b[i] = Byte.parseByte(strs[i].trim());
        }
        return b;
    }


    public BufferedImage screenShot(int width, int height){
        try {
            return new Robot().createScreenCapture(new Rectangle(width,height));
        } catch (AWTException e) {
            e.printStackTrace();
        }
        return null;
    }

}