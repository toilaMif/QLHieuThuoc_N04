package frm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

public class fgh extends JFrame {
    public fgh() {
        setTitle("Đặt Cảnh Báo");
        setSize(1400, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Main panel with gradient background
        JPanel mainPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                GradientPaint gp = new GradientPaint(0, 0, new Color(217, 253, 211), getWidth(), getHeight(), new Color(240, 255, 245));
                g2d.setPaint(gp);
                g2d.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        mainPanel.setLayout(new BorderLayout(0, 30));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // Header
        JLabel header = new JLabel("Đặt Cảnh Báo", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 32));
        header.setForeground(Color.WHITE);
        header.setBackground(new Color(134, 203, 146));
        header.setOpaque(true);
        header.setBorder(BorderFactory.createEmptyBorder(25, 0, 25, 0));
        mainPanel.add(header, BorderLayout.NORTH);

        // Center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setOpaque(false);
        centerPanel.setLayout(new BorderLayout(0, 30));

        // Sub-header
        JLabel subHeader = new JLabel("Kệ A - KT0001", SwingConstants.CENTER);
        subHeader.setFont(new Font("Segoe UI", Font.BOLD, 24));
        subHeader.setForeground(new Color(51, 51, 51));
        centerPanel.add(subHeader, BorderLayout.NORTH);

        // Grid panel for cards
        JPanel gridPanel = new JPanel(new GridLayout(1, 3, 30, 30));
        gridPanel.setOpaque(false);

        // Card 1: Hạn sử dụng
        JPanel card1 = createCard("Hạn sử dụng");
        ButtonGroup expiryGroup = new ButtonGroup();
        JRadioButton expiryRadio = new JRadioButton("Thời gian còn lại trước khi hết hạn", true);
        expiryGroup.add(expiryRadio);
        card1.add(expiryRadio);
        JPanel row1 = createRow();
        JTextField expiryField = new JTextField("30", 5);
        JComboBox<String> expiryUnit = new JComboBox<>(new String[]{"ngày", "tuần"});
        row1.add(expiryField);
        row1.add(expiryUnit);
        card1.add(row1);
        JLabel note1 = new JLabel("* Chức năng này sẽ báo trước khi thuốc hết hạn");
        note1.setForeground(new Color(177, 0, 0));
        note1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card1.add(note1);
        gridPanel.add(card1);

        // Card 2: Số lượng
        JPanel card2 = createCard("Số lượng");
        ButtonGroup quantityGroup = new ButtonGroup();
        JRadioButton minQuantityRadio = new JRadioButton("Số lượng tối thiểu", true);
        quantityGroup.add(minQuantityRadio);
        card2.add(minQuantityRadio);
        JPanel row2 = createRow();
        JTextField minQuantityField = new JTextField("105", 5);
        JComboBox<String> minUnit = new JComboBox<>(new String[]{"Viên"});
        row2.add(minQuantityField);
        row2.add(minUnit);
        card2.add(row2);
        JRadioButton maxQuantityRadio = new JRadioButton("Số lượng tối đa");
        quantityGroup.add(maxQuantityRadio);
        card2.add(maxQuantityRadio);
        JPanel row3 = createRow();
        JTextField maxQuantityField = new JTextField("200", 5);
        JComboBox<String> maxUnit = new JComboBox<>(new String[]{"Viên"});
        row3.add(maxQuantityField);
        row3.add(maxUnit);
        card2.add(row3);
        JLabel note2 = new JLabel("* Kiểm soát giới hạn tồn kho");
        note2.setForeground(new Color(177, 0, 0));
        note2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card2.add(note2);
        gridPanel.add(card2);

        // Card 3: Tồn kho
        JPanel card3 = createCard("Tồn kho");
        ButtonGroup storageGroup = new ButtonGroup();
        JRadioButton storageTimeRadio = new JRadioButton("Thời gian lưu trữ");
        storageGroup.add(storageTimeRadio);
        card3.add(storageTimeRadio);
        JPanel row4 = createRow();
        JTextField storageTimeField = new JTextField("30", 5);
        JComboBox<String> storageUnit = new JComboBox<>(new String[]{"ngày", "tuần"});
        row4.add(storageTimeField);
        row4.add(storageUnit);
        card3.add(row4);
        JRadioButton percentSoldRadio = new JRadioButton("Số % bán trong thời hạn", true);
        storageGroup.add(percentSoldRadio);
        card3.add(percentSoldRadio);
        JPanel row5 = createRow();
        JTextField percentField = new JTextField("5", 5);
        JLabel percentLabel = new JLabel("%");
        row5.add(percentField);
        row5.add(percentLabel);
        card3.add(row5);
        JLabel note3 = new JLabel("* Nếu % bán < tồn kho → cảnh báo");
        note3.setForeground(new Color(177, 0, 0));
        note3.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        card3.add(note3);
        gridPanel.add(card3);

        centerPanel.add(gridPanel, BorderLayout.CENTER);

        // Button group
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 0));
        buttonPanel.setOpaque(false);
        JButton clearButton = new JButton("🗑 Xóa rỗng");
        clearButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        clearButton.setBackground(new Color(255, 77, 79));
        clearButton.setForeground(Color.WHITE);
        clearButton.setBorderPainted(false);
        clearButton.setFocusPainted(false);
        clearButton.setPreferredSize(new Dimension(150, 40));
        clearButton.addActionListener(e -> {
            expiryField.setText("");
            minQuantityField.setText("");
            maxQuantityField.setText("");
            storageTimeField.setText("");
            percentField.setText("");
        });

        JButton saveButton = new JButton("💾 Lưu");
        saveButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        saveButton.setBackground(new Color(63, 135, 245));
        saveButton.setForeground(Color.WHITE);
        saveButton.setBorderPainted(false);
        saveButton.setFocusPainted(false);
        saveButton.setPreferredSize(new Dimension(150, 40));
        saveButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Đã lưu!"));

        buttonPanel.add(clearButton);
        buttonPanel.add(saveButton);
        centerPanel.add(buttonPanel, BorderLayout.SOUTH);

        mainPanel.add(centerPanel, BorderLayout.CENTER);
        add(mainPanel);
        setVisible(true);
    }

    private JPanel createCard(String title) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(43, 107, 79), 2),
                title,
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 20),
                new Color(43, 107, 79)
            ),
            BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));
        card.setOpaque(true);
        card.setAlignmentX(Component.LEFT_ALIGNMENT);
        return card;
    }

    private JPanel createRow() {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        row.setOpaque(false);
        row.setAlignmentX(Component.LEFT_ALIGNMENT);
        return row;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(fgh::new);
    }
}