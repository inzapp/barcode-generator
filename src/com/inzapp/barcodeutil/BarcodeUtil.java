package com.inzapp.barcodeutil;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeUtil {

    private static String basePath = "";
    private static int barcodeWidth = 512;
    private static int barcodeHeight = 160;

    public static void main(String[] args) {
        BarcodeUtil.setBasePath("barcode/");
        String filePath = BarcodeUtil.getBarcodePath("1234567890");
        System.out.println(filePath);
    }

    public static String getBasePath() {
        return BarcodeUtil.basePath;
    }

    public static void setBasePath(String basePath) {
        BarcodeUtil.basePath = convertPath(basePath);
    }

    private static String convertPath(String path) {
        path = path.replaceAll("/", "\\.");
        if (!path.endsWith("\\"))
            path += "\\";
        return path;
    }

    public static int getBarcodeWidth() {
        return BarcodeUtil.barcodeWidth;
    }

    public static void setBarcodeWidth(int barcodeWidth) {
        BarcodeUtil.barcodeWidth = barcodeWidth;
    }

    public static int getBarcodeHeight() {
        return BarcodeUtil.barcodeHeight;
    }

    public static void setBarcodeHeight(int barcodeHeight) {
        BarcodeUtil.barcodeHeight = barcodeHeight;
    }

    public static String getBarcodePath(String content) {
        return BarcodeUtil.getBarcodePath(content, BarcodeUtil.basePath);
    }

    public static String getBarcodePath(String content, String basePath) {
        return BarcodeUtil.getBarcodePath(content, basePath, BarcodeUtil.barcodeWidth, BarcodeUtil.barcodeHeight);
    }

    public static String getBarcodePath(String content, String basePath, int barcodeWidth, int barcodeHeight) {
        try {
            File barcodeImgFile = new File(basePath + content + ".png");
            if (barcodeImgFile.exists())
                return barcodeImgFile.getAbsolutePath();

            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, barcodeWidth, 1);
            BufferedImage bufferedImage = new BufferedImage(barcodeWidth, barcodeHeight, BufferedImage.TYPE_BYTE_BINARY);
            for (int x = 0; x < barcodeWidth; x++) {
                for (int y = 0; y < barcodeHeight; ++y)
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, 0) ? Byte.MIN_VALUE : Byte.MAX_VALUE);
            }
            ImageIO.write(bufferedImage, "png", barcodeImgFile);
            return barcodeImgFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
