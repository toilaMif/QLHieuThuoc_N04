package hieuUng;

import javax.swing.*;
import java.awt.*;

public class boGocPopupMenu extends JPopupMenu {
    private int cornerRadius = 15; // Độ cong góc

    public boGocPopupMenu() {
        setOpaque(true); // Đảm bảo nền trong suốt để vẽ bo góc
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền bo góc
        g2.setColor(Color.WHITE);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius);

        // Vẽ viền (nếu cần)
        g2.setColor(Color.LIGHT_GRAY);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, cornerRadius, cornerRadius);
    }
}
