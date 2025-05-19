package hieuUng;

import java.awt.*;
import javax.swing.*;

public class GradientPanel extends JPanel {
    private Color color1 = Color.BLUE;  // Màu bắt đầu
    private Color color2 = Color.WHITE; // Màu kết thúc
    private int cornerRadius = 30;      // Độ bo góc

    public GradientPanel() {
        setOpaque(false); // Giúp hiển thị đẹp hơn
    }

    public void setGradientColors(Color c1, Color c2) {
        this.color1 = c1;
        this.color2 = c2;
        repaint(); // Cập nhật lại giao diện
    }

    public void setCornerRadius(int radius) {
        this.cornerRadius = radius;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();

        // Bật chế độ khử răng cưa
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tạo gradient màu từ trên xuống dưới
        GradientPaint gradient = new GradientPaint(0, 0, color1, 0, height, color2);
        g2d.setPaint(gradient);

        // Vẽ hình bo góc với gradient
        g2d.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);
    }
}
