package com.inzapp.barcodegenerator;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeGenerator {

    public static void main(String[] args) {
        new BarcodeGenerator().generate("1234asd5");
    }

    public BufferedImage generate(String content) {
        try {
            int barcodeWidth = 1080;
            int barcodeHeight = 640;
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, barcodeWidth, 1);
            BufferedImage bufferedImage = new BufferedImage(barcodeWidth, barcodeHeight, BufferedImage.TYPE_4BYTE_ABGR);
            for (int x = 0; x < 1080; x++) {
                for (int y = 0; y < barcodeHeight; ++y)
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, 0) ? Byte.MIN_VALUE : Byte.MAX_VALUE);
            }
            ImageIO.write(bufferedImage, "png", new File("barcode.png"));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
