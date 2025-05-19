package hieuUng;

import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JButton;
import javax.swing.JComponent;

public class nutBam extends javax.swing.plaf.basic.BasicButtonUI {
    private int cornerRadius; // Độ bo góc

    // Constructor mặc định với góc bo là 30px
    public nutBam() {
        this(30);
    }

    // Constructor cho phép truyền độ bo góc
    public nutBam(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        JButton b = (JButton) c;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Vẽ nền với góc bo tùy chỉnh
        g2.setColor(b.getBackground());
        g2.fillRoundRect(0, 0, b.getWidth(), b.getHeight(), cornerRadius, cornerRadius);

        // Vẽ chữ căn giữa
        g2.setColor(b.getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int x = (b.getWidth() - fm.stringWidth(b.getText())) / 2;
        int y = (b.getHeight() + fm.getAscent()) / 2 - 2;
        g2.drawString(b.getText(), x, y);

        g2.dispose();
    }
}
