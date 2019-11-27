import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BarcodeGenerator {
    private int barcodeWidth = 1080;
    private int barcodeHeight = 640;

    public static void main(String[] args) {
        new BarcodeGenerator().generate("1234asd5");
    }

    public BufferedImage generate(String content) {
        try {
            BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.CODE_128, this.barcodeWidth, 1);
            BufferedImage bufferedImage = new BufferedImage(this.barcodeWidth, this.barcodeHeight, BufferedImage.TYPE_BYTE_GRAY);
            for (int x = 0; x < 1080; x++) {
                for (int y = 0; y < this.barcodeHeight; ++y)
                    bufferedImage.setRGB(x, y, bitMatrix.get(x, 0) ? Byte.MIN_VALUE : Byte.MAX_VALUE);
            }
            ImageIO.write(bufferedImage, "jpg", new File("barcode.jpg"));
            return bufferedImage;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
