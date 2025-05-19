package hieuUng;

import javax.swing.JButton;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;

public class boGocComboBoxUI extends BasicComboBoxUI {
    @Override
    protected JButton createArrowButton() {
        JButton button = new JButton();
        button.setBorder(null);
        button.setContentAreaFilled(false);
        return button;
    }

    @Override
    public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
        g.setColor(new Color(255, 255, 255));
        g.fillRoundRect(bounds.x, bounds.y, bounds.width, bounds.height, 20, 20);
    }
}

