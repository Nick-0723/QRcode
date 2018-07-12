
package com.hejin;

/**
 * @author Nick
 * @date 2018/2/2 14:43
 */

import com.google.zxing.*;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.hejin.BufferedImageLuminanceSource;
import com.hejin.MatrixToImageWriter;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.*;


/**
 * 利用Google的ZXing工具包，生成和解析二维码图片
 */

public class QRCodeEvents {

/**
     * 根据内容，生成指定宽高、指定格式的二维码图片
     *
     * @param text   内容
     * @param width  宽
     * @param height 高
     * @return BufferedImage
     * @throws Exception
     */

    public static BufferedImage generateQRCode(String text, int width, int height) throws Exception {
        Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
        hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(text, BarcodeFormat.QR_CODE, width, height, hints);
        return MatrixToImageWriter.toBufferedImage(bitMatrix);
    }


/**
     * 向剪贴板中添加内容
     *
     * @param contents
     */

    private static void setClipbordContents(String contents) {
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
            // 检查内容是否是文本类型
            if (clipTf.isDataFlavorSupported(DataFlavor.stringFlavor)) {
                try {
                    ret = (String) clipTf
                            .getTransferData(DataFlavor.stringFlavor);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return ret;
    }

}
