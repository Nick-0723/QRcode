package create;

/**
 * @author Nick
 * @date 2018/2/2 14:43
 */
import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;


/**
 * 利用Google的ZXing工具包，生成和解析二维码图片
 */
public class QRCodeEvents {

    public static void main(String[] args) {
//        从剪贴板获取string字符串
        String text = getSysClipboardText();
        int len = text.length();
        String[] texts = new String[len/500+1];
        for (int i = 0; i < len/500+1; i++) {
            System.out.println(len+"---"+i+"===="+i*500+"====="+(((i+1)*500)>len?len:(i+1)*500));
            texts[i] = text.substring(i*500,((i+1)*500)>len?len:(i+1)*500);
        }
        int width = 500;    //二维码图片的宽
        int height = 500;   //二维码图片的高
        String format = "png";  //二维码图片的格式

        try {
            //生成二维码图片，并返回图片路径
            for (int i = 0; i < len; i++) {
                System.out.println("-----------");
                String pathName = generateQRCode(texts[i], width, height, format,i+".png");
                System.out.println("生成二维码的图片路径： " + pathName);
                String content = parseQRCode(pathName);
                System.out.println("解析出二维码的图片的内容为： " + content);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




    /**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @param width  宽
     * @param height 高
     * @param format 图片格式
     * @return 生成的二维码图片路径
     * @throws Exception
     */
    public static String generateQRCode(String text, int width, int height, String format, String imgName) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        File outputFile = new File(imgName);
        MatrixToImageWriter.writeToFile(bitMatrix, format, outputFile);
        return imgName;
    }

    /**
     * 解析指定路径下的二维码图片
     *
     * @param filePath 二维码图片路径
     * @return
     */
    public static String parseQRCode(String filePath) {
        String content = "";
        try {
            File file = new File(filePath);
            BufferedImage image = ImageIO.read(file);
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);

//            System.out.println("result 为：" + result.toString());
//            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
//            System.out.println("resultText 为：" + result.getText());
            //设置返回值
            content = result.getText();
            byte[] rawBytes = result.getRawBytes();
//            System.out.println(Arrays.toString(rawBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }



    public static String parseQRCode(BufferedImage image) {
        String content = "";
        try {
            LuminanceSource source = new BufferedImageLuminanceSource(image);
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            Map<DecodeHintType, Object> hints = new HashMap<>();
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            MultiFormatReader formatReader = new MultiFormatReader();
            Result result = formatReader.decode(binaryBitmap, hints);

            System.out.println("result 为：" + result.toString());
            System.out.println("resultFormat 为：" + result.getBarcodeFormat());
            System.out.println("resultText 为：" + result.getText());
            //设置返回值
            content = result.getText();
            byte[] rawBytes = result.getRawBytes();
//            System.out.println(Arrays.toString(rawBytes));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    /**
     * 随机生成指定长度的验证码
     *
     * @param length 验证码长度
     * @return 生成的验证码
     */
    private static String generateNumCode(int length) {
        String val = "";
        String charStr = "char";
        String numStr = "num";
        Random random = new Random();

        //参数length，表示生成几位随机数
        for (int i = 0; i < length; i++) {

            String charOrNum = random.nextInt(2) % 2 == 0 ? charStr : numStr;
            //输出字母还是数字
            if (charStr.equalsIgnoreCase(charOrNum)) {
                //输出是大写字母还是小写字母
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;
                val += (char) (random.nextInt(26) + temp);
            } else if (numStr.equalsIgnoreCase(charOrNum)) {
                val += String.valueOf(random.nextInt(10));
            }
        }
        return val;
    }

    @Test
    public void test(){
        String sysClipboardText = getSysClipboardText();
        System.out.println(sysClipboardText.length());
        System.out.println(sysClipboardText);
    }

    /**
     * 向剪贴板中添加内容
     *
     * @param contents
     */
    public static void setClipbordContents(String contents) {
        StringSelection stringSelection = new StringSelection( contents );
        // 系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
    }

    /**
     *1. 从剪切板获得文字。
     */
    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        Transferable clipTf = sysClip.getContents(null);

        if (clipTf != null) {
            try {
                if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                    ret = (String) clipTf.getTransferData(DataFlavor.stringFlavor);
                } else if (clipTf.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
                    Object data = clipTf.getTransferData(DataFlavor.javaFileListFlavor);
                    ret = data.toString().replace("[","").replace("]","");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

    }

        return ret;
    }


//    读取文件的字节码到粘贴板

    public static void getFileBytes(File file){
        setClipbordContents("scl-fileName-"+file.getName());
        try(InputStream is = new FileInputStream(file)) {
            Thread.sleep(10000);
            System.out.println(getSysClipboardText());
            byte[] b = new byte[1024];
            int len = -1;
            String contents = "";
            while ((len = is.read(b))!=-1){
                Thread.sleep(5000);
                contents = len<1024 ? "scl-end-"+new String(b) : "scl-"+new String(b);
                setClipbordContents(contents);
                System.out.println(getSysClipboardText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}