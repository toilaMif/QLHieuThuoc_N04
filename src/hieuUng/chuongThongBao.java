package hieuUng;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;

public class chuongThongBao extends BasicButtonUI {
    private int cornerRadius;
    private JPopupMenu popupMenu;
    private boolean isMouseInsidePopup = false;
    private List<String> danhSachThongBao = new ArrayList<>();

    public chuongThongBao() {
        this(30);
    }

    public chuongThongBao(int cornerRadius) {
        this.cornerRadius = cornerRadius;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        JButton button = (JButton) c;

        popupMenu = new boGocPopupMenu();
        popupMenu.setPopupSize(200, 150);
        popupMenu.setBackground(Color.white);
        popupMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Sự kiện click: Cập nhật lại popup trước khi hiển thị
        button.addActionListener(e -> {
            capNhatPopup();
            popupMenu.show(button, -150, button.getHeight());
        });

        button.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (popupMenu.isShowing()) {
					popupMenu.setVisible(false);
				} 
			}
		});

        popupMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                isMouseInsidePopup = true;
            }

           
        });
    }

    // Hàm cập nhật lại danh sách thông báo trong popup
    private void capNhatPopup() {
        popupMenu.removeAll();

        if (danhSachThongBao.isEmpty()) {
            JMenuItem emptyItem = new JMenuItem("✅ Không có thông báo mới.");
            emptyItem.setEnabled(false);
            popupMenu.add(emptyItem);
        } else {
            for (int i = 0; i < danhSachThongBao.size(); i++) {
                String tb = danhSachThongBao.get(i);
                JMenuItem item = new JMenuItem("🔔 " + tb);
                int index = i;

                // Khi click thì xóa thông báo và cập nhật lại popup
                item.addActionListener(e -> {
                    danhSachThongBao.remove(index);
                    capNhatPopup();  // refresh lại menu
                });

                popupMenu.add(item);
            }
        }

        popupMenu.revalidate();
        popupMenu.repaint();
    }


    // Hàm thêm thông báo mới
    public void themThongBao(String thongBao) {
        danhSachThongBao.add(thongBao);
    }

    @Override
    public void paint(Graphics g, JComponent c) {
        JButton b = (JButton) c;
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = b.getWidth();
        int height = b.getHeight();

        g2.setColor(Color.white);
        g2.fillRoundRect(0, 0, width, height, cornerRadius, cornerRadius);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(1));
        g2.drawRoundRect(1, 1, width - 2, height - 2, cornerRadius, cornerRadius);

        g2.setColor(b.getForeground());
        FontMetrics fm = g2.getFontMetrics();
        int textX = (width - fm.stringWidth(b.getText())) / 2;
        int textY = (height + fm.getAscent()) / 2 - 2;
        g2.drawString(b.getText(), textX, textY);

        g2.dispose();
        super.paint(g, c);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Test chuongThongBao");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(400, 300);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new FlowLayout(FlowLayout.RIGHT, 20, 20));

            JButton btnThongBao = new JButton("🔔");
            chuongThongBao thongBaoUI = new chuongThongBao(50);
            btnThongBao.setUI(thongBaoUI);
            btnThongBao.setPreferredSize(new Dimension(50, 50));
            btnThongBao.setBackground(Color.WHITE);
            btnThongBao.setForeground(Color.BLACK);
            btnThongBao.setFocusPainted(false);
            btnThongBao.setBorderPainted(false);

            // Giả lập thêm thông báo
            thongBaoUI.themThongBao("Bạn có đơn hàng mới.");
            thongBaoUI.themThongBao("Thuốc A sắp hết hạn.");
            thongBaoUI.themThongBao("Khách hàng X vừa đăng ký.");

            frame.add(btnThongBao);
            frame.setVisible(true);
        });
    }
}
