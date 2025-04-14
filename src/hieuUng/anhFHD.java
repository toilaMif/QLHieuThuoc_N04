package hieuUng;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class anhFHD {
    public static BufferedImage loadImage(String path) {
        try {
            URL imgUrl = ClassLoader.getSystemResource(path);
            if (imgUrl == null) {
                throw new IOException("Không tìm thấy file: " + path);
            }
            return ImageIO.read(imgUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static Image getHighQualityThumbnail(String path, int targetWidth, int targetHeight) {
        BufferedImage originalImage = loadImage(path);
        if (originalImage == null) return null;

        BufferedImage resizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImage.createGraphics();

        // Sử dụng thuật toán Bicubic để giữ nét khi thu nhỏ
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

        // Vẽ ảnh với kích thước nhỏ hơn
        g2.drawImage(originalImage, 0, 0, targetWidth, targetHeight, null);
        g2.dispose();

        return resizedImage;
    }

    public static void main(String[] args) {
        // Giảm kích thước xuống 100x100 mà vẫn giữ nét
        ImageIcon imgIcon = new ImageIcon(getHighQualityThumbnail("image/AnhThe.jpg", 200, 200));
        JLabel label = new JLabel(imgIcon);
        JOptionPane.showMessageDialog(null, label, "Ảnh Thu Nhỏ Sắc Nét", JOptionPane.PLAIN_MESSAGE);
    }
}
