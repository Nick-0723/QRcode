package com.escape.file;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Escape {


    private String getFileBytes(File file){
        long fileLen = file.length();
        System.out.println("文件大小："+fileLen);
        try ( InputStream in = new FileInputStream(file)) {
            byte[] b = new byte[1024];
            int len = -1;
            int times = 0;
            while((len = in.read(b)) != -1){

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
