package com.strongliu.blog.utility;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by liuyuzhe on 2017/5/4.
 */
public class FileUtil {

    public static boolean isImage(File file) {
        if (!file.exists()) {
            return false;
        }

        try {
            Image image = ImageIO.read(file);

            return !(image == null || image.getWidth(null) <= 0 || image.getHeight(null) <= 0);
        } catch (Exception e) {
            return false;
        }
    }

    public static String getFileSavePath(String fileName, String fileUuid, Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
        String prefix = format.format(date);

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        return prefix + File.separator + fileUuid + "." + suffix;
    }

}
