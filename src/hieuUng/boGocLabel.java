package hieuUng;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class boGocLabel extends JLabel {
    private Image image;

    public boGocLabel(ImageIcon icon) {
        this.image = icon.getImage();
        setPreferredSize(new Dimension(icon.getIconWidth(), icon.getIconHeight()));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (image != null) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int width = getWidth();
            int height = getHeight();

            // Cắt hình ảnh thành hình tròn
            Shape clip = new Ellipse2D.Float(0, 0, width, height);
            g2.setClip(clip);
            g2.drawImage(image, 0, 0, width, height, this);
            g2.dispose();
        } else {
            super.paintComponent(g);
        }
    }
}

