package create;

import org.junit.Test;

import java.io.*;
import java.util.Arrays;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class CreateQR {
    private String path = "L:\\Nick\\Desktop\\QR\\";
    private int size = 1000;

    public void create(File file){
        long fileLen = file.length();
        System.out.println("文件大小："+fileLen);
        try ( InputStream in = new FileInputStream(file)) {
            byte[] b = new byte[600];
            int len = -1;
            int times = 1;
            QRCodeEvents.generateQRCode("FileName:"+file.getName(),size,size,"png",path+0+".png");
            while((len = in.read(b)) != -1){
                QRCodeEvents.generateQRCode(Arrays.toString(Arrays.copyOf(b,len)).replace("[","").replace("]",""),size,size,"png",path+times+".png");
                times++;
            }
            QRCodeEvents.generateQRCode("end",size,size,"png",path+times+".png");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public String byteToString(byte[] b, int len){
        String[] c = new String[len];

        for (int i = 0; i < len; i++){
            if (b[i] > 0){
                c[i] = Integer.toHexString(b[i]);
            } else{
                c[i] = ""+b[i];
            }
        }

        return Arrays.toString(c).replace(" ","").replace("[","").replace("]","");
    }

    public static void main(String[] args) {
        CreateQR createQR = new CreateQR();
        byte[] b = {-92, -33, -113, 40, -104, 78, 70, 84, 23, 59, -115, 77, -122, 117, -114, -98, -118, -78, -74, -28, 54, -57, -122, -63, 78, -124, -19, 93, -127, 49, 1, 104, 67, -28, 107, 7, 69, 57, -88, -63, -35, -37, -45, 49, 57, 79, 120, 95, 91, 77, -27, -22, -110, -68, -16, -49, -108, 9, 72, 120, 71, 72, 33, -99, -85, 17, 94, 0, -87, 50, -104, 11, -26, -76, 121, -115, 14, -108, -69, -27, 124, 109, -51, -3, 47, -104, 87, -50, -94, 2, -119, -51, 52, 13, 20, 59, 20, -49, 12, -73, 63, -95, -80, -14, 95, -118, 86, 116, -37, 49, -10, -42, -24, 108, 57, 100, 87, 49, -122, 17, 22, 4, -105, -89, -101, -19, 29, -123, -78, -30, -113, -18, 82, -58, 100, 99, -3, 118, -12, -107, 8, -5, -72, -101, -105, 88, 17, -57, -74, 13, 7, -128, -102, 44, 38, -33, -118, -79, 123, -37, -108, 30, -6, -6, 19, 45, 14, 100, 27, 47, -11, -38, -52, -81, 82, 41, -50, -74, 3, 99, 111, 27, 23, -127, 22, 25, -125, 11, 108, -10, -113, 8, -85, 61, -120, -28, -89, -50, -75, 21, -93, -117, -117, 85, 108, -89, 70, -37, 9, 116, 27, -127, -75, -66, -94, 64, -93, -121};

        String compress = createQR.compress(Arrays.toString(b).getBytes());

        System.out.println(compress);
        System.out.println(compress.length());

//        String s = createQR.byteToString(b,b.length);
//        System.out.println(s.length()+":"+s);
//        String[] split = s.split(",");
//        byte[] bs = new byte[split.length];
//        for (int i = 0; i < bs.length; i++){
//            if (split[i].contains("-")){
//                bs[i] = Byte.parseByte(split[i]);
//            }else {
//                bs[i] = Byte.parseByte(split[i],16);
//            }
//        }
//        System.out.println(Arrays.toString(bs).length());

    }

    public String compress(byte[] input){
        try {
            // Encode a String into bytes
//            String inputString = "blahblahblah";
//            byte[] input = inputString.getBytes("UTF-8");

            // Compress the bytes
            byte[] output = new byte[input.length];
            Deflater compresser = new Deflater();
            compresser.setInput(input);
            compresser.finish();
            int compressedDataLength = compresser.deflate(output);
            compresser.end();

            System.out.println(compressedDataLength);

            // Decompress the bytes
            Inflater decompresser = new Inflater();
            decompresser.setInput(output, 0, compressedDataLength);
            byte[] result = new byte[input.length];
            int resultLength = decompresser.inflate(result);
            decompresser.end();

            // Decode the bytes into a String
            return new String(result, 0, resultLength, "UTF-8");
        } catch(java.io.UnsupportedEncodingException ex) {
            // handle
        } catch (java.util.zip.DataFormatException ex) {
            // handle
        }


//        return Arrays.toString(Arrays.copyOf(output,compressedDataLength));

        return null;
    }


}
