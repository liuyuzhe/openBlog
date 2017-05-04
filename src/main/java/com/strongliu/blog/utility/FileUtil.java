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
            if (image == null || image.getWidth(null) <= 0 || image.getHeight(null) <= 0) {
                return false;
            }

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getFileSlug(String fileName) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM");
        String prefix = format.format(new Date());

        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);

        return prefix + File.separator + StringUtil.getUUID() + "." + suffix;
    }
}
