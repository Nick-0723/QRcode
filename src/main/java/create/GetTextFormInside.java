package create;

import java.awt.image.BufferedImage;

public class GetTextFormInside {

    public static void main(String[] args) {
        String s1 = QRCodeEvents.parseQRCode("L:\\Nick\\Desktop\\1.png");
        System.out.println(s1);
        ScreenSnapshot s = new ScreenSnapshot();
        BufferedImage bufferedImage = s.screenShot(800, 800);
        String value = QRCodeEvents.parseQRCode(bufferedImage);
        QRCodeEvents.setClipbordContents(value);
    }

}
