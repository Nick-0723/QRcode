package com.hejin;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.io.*;


public class GetFile {
    private static boolean flag = true;
    private static String fileName = null;
    private static String tempFile = "E:\\Users\\c_limingjun-006\\Desktop\\temp.txt";
    private static String path = "E:\\Users\\c_limingjun-006\\Desktop\\test";



    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        runEach(1);
        long end = System.currentTimeMillis();
        System.out.println("耗时："+(end-start)/1000+"s");
    }

    private static void runEach(int times){

        for (int i = 0; i < times; i++){
            run();
        }

    }


    private static void run(){
        StringSelection stringSelection = new StringSelection( "clear" );
        // 系统剪贴板
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(stringSelection, null);
        clipbordToFile();
        makeFile();
    }




    private static void clipbordToFile(){
        String clipboardText;
        String contents;
        String t = "";
        int times = -1;

        File file = new File(tempFile);
        if (file.exists()){
            if(file.delete()) System.out.println("临时文件已删除！");
        }
        flag = true;
        fileName = null;

        try (OutputStream out = new FileOutputStream(file,true)) {
            while (flag) {
                clipboardText = getSysClipboardText();
                if (clipboardText.startsWith("scl=")) {
                    contents = clipboardText.split("scl=")[1];
                    String nt = contents.split("=")[0];
                    System.out.println(t+":"+nt);
                    if (contents.startsWith("end=")) {
                        flag = false;
                        contents = contents.split("end=")[1];
                        fileName = contents.split("=")[0];
                        contents = contents.replace(fileName+"=","");
                    }
                    if (!t.equals(nt)){
                        out.write(contents.getBytes());
                        out.write(13);
                        out.write(10);
                        out.flush();
                    }
                    t = nt;
                }
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("文件名字："+fileName);
    }



    public static String getSysClipboardText() {
        String ret = "";
        Clipboard sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
        // 获取剪切板中的内容
        if (sysClip == null) {
            while(true){
                sysClip = Toolkit.getDefaultToolkit().getSystemClipboard();
                if (sysClip != null) break;
            }
        }
        Transferable clipTf = null;
        try {

            clipTf = sysClip.getContents(null);
        } catch (IllegalStateException e){
            try {
                Thread.sleep(20);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            clipTf = sysClip.getContents(null);
        }

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


    public static void makeFile(){
        int times = -1;
        if (fileName == null) return;
        File f = new File(path,fileName);
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(tempFile)));
            FileOutputStream out = new FileOutputStream(f)
        ){
            String line = null;
            while ((line = reader.readLine()) != null){
                if (Integer.parseInt(line.split("=")[0]) != times) {
                    int next_times = Integer.parseInt(line.split("=")[0]);
                    if (next_times - 1 != times) {
                        System.out.println("文件不完整"+times+":"+next_times);
                        return;
                    }
                    times = next_times;
                    String[] bytes = line.split("\\[")[1].split(",");
                    byte[] b = new byte[bytes.length];
                    for (int i = 0; i < b.length; i++){
                        b[i] = Byte.parseByte(bytes[i].trim());
                    }
                    out.write(b);
                    out.flush();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("文件已经复制进，路径是："+path+"\\"+fileName);
    }
}

