package com.inzapp.barcodegenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeGenerator {

    private String basePath = "";
    private int barcodeWidth = 512;
    private int barcodeHeight = 160;

    public static void main(String[] args) {
    }

    public String getBasePath() {
        return this.basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = this.convertPath(basePath);
    }

    public String generate(String content) {
        return this.generate(content, this.basePath);
    }

    public String generate(String content, String basePath) {
        return this.generate(content, this.convertPath(basePath), this.barcodeWidth, this.barcodeHeight);
    }

    public String generate(String content, String basePath, int barcodeWidth, int barcodeHeight) {
        try {
            File barcodeImgFile = new File(this.convertPath(basePath) + content + ".png");
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

    private String convertPath(String path) {
        path = path.replaceAll("/", "\\\\");
        if (!path.endsWith("\\"))
            path += "\\";
        return path;
    }
}
