package hieuUng;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class ProfilePanel extends JPanel {
    private JLabel avatarLabel, nameLabel, positionLabel;
    private JButton actionButton;
    
    public ProfilePanel(String name, String position, String avatarPath) {
        setLayout(null);
        setBackground(Color.WHITE);

        // Avatar (JLabel)
        avatarLabel = new JLabel();
        ImageIcon avatar = ImageResizer.resizeImage(avatarPath, 60, 60);
        if (avatar != null) {
            avatarLabel.setIcon(getRoundedImage(avatar, 60));
        }
        avatarLabel.setBounds(10, 7, 60, 60);
        add(avatarLabel);

        // Tên (JLabel)
        nameLabel = new JLabel(name);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
        nameLabel.setBounds(80, 15, 250, 25);
        add(nameLabel);

        // Chức vụ (JLabel)
        positionLabel = new JLabel("Chức Vụ: " + position);
        positionLabel.setFont(new Font("Arial", Font.ITALIC, 15));
        positionLabel.setForeground(Color.GRAY);
        positionLabel.setBounds(80, 40, 200, 20);
        add(positionLabel);

      

        setPreferredSize(new Dimension(300, 120));
    }

    private ImageIcon getRoundedImage(ImageIcon icon, int size) {
        BufferedImage img = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = img.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Tạo mặt nạ hình tròn
        BufferedImage circleBuffer = new BufferedImage(size, size, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = circleBuffer.createGraphics();
        g.setClip(new Ellipse2D.Float(0, 0, size, size));
        g.drawImage(icon.getImage(), 0, 0, size, size, null);
        g.dispose();

        g2.drawImage(circleBuffer, 0, 0, null);
        g2.dispose();

        return new ImageIcon(img);
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Hồ Sơ");
//        ProfilePanel panel = new ProfilePanel("Nguyễn Văn A", "Quản lý", "/image/AnhThe.jpg");
//
//        frame.add(panel);
//        frame.pack();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setVisible(true);
//    }
}
