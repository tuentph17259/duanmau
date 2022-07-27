/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.edusys.utils;

import java.awt.Image;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javax.swing.ImageIcon;

/**
 *
 * @author admin
 */
public class XImage {
     public static Image getAppIcon() {
        URL url = XImage.class.getResource("/com/edusys/Hinh/fpt.png");
        return new ImageIcon(url).getImage();
    }
     
     public static void save(File src){
        File dst = new File("logos",src.getName());
        if (!dst.getParentFile().exists()) {
            dst.getParentFile().mkdirs();
        }
        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(dst.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING); // Copy file vào thư mục logos
            //StandardCopyOption.REPLACE_EXISTING :  Tiêu chuẩn thay thế cái đã tồn tại
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static ImageIcon read(String filename){
        File path = new File("logos", filename);
        ImageIcon icon = new ImageIcon(path.getAbsolutePath());
        return icon ;
    }
}
