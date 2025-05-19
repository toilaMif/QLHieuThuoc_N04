package hieuUng;

import javax.swing.*;
import java.awt.*;

public class loadImg extends JPanel {
    private JLabel lblImage;
    private ImageIcon originalIcon;
    private int imageSize;

    public loadImg(String imagePath, int initialSize) {
        this.imageSize = initialSize;
        setLayout(new BorderLayout());

        // Load ảnh từ đường dẫn trực tiếp
        originalIcon = new ImageIcon(imagePath);
        lblImage = new JLabel();
        lblImage.setHorizontalAlignment(JLabel.CENTER);
        lblImage.setVerticalAlignment(JLabel.CENTER);
        lblImage.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Hiển thị ảnh với kích thước mong muốn
        resizeImage(imageSize);
        add(lblImage, BorderLayout.CENTER);
    }

    private void resizeImage(int size) {
        if (originalIcon != null) {
            Image image = originalIcon.getImage().getScaledInstance(size, size, Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(image));
            lblImage.setPreferredSize(new Dimension(size, size));
            revalidate();
            repaint();
        }
    }

    public static void main(String[] args) {
        String imagePath = "src/image/AnhThe.jpg"; // Đường dẫn ảnh
        int initialSize = 80;

        JFrame frame = new JFrame("Resizable Image Viewer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 600);
        frame.setLayout(new BorderLayout());
        frame.add(new loadImg(imagePath, initialSize), BorderLayout.CENTER);
        frame.setVisible(true);
    }
}
