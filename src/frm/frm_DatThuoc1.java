package frm;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;

public class frm_DatThuoc1 extends JFrame {

    public frm_DatThuoc1() {
        setTitle("Đặt Thuốc");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Header
        JLabel header = new JLabel("ĐẶT THUỐC", SwingConstants.CENTER);
        header.setFont(new Font("Segoe UI", Font.BOLD, 30));
        header.setOpaque(true);
        header.setBackground(new Color(76, 175, 80));
        header.setForeground(Color.WHITE);
        header.setPreferredSize(new Dimension(0, 70));
        add(header, BorderLayout.NORTH);

        // Main content
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(new Color(244, 249, 244));
        contentPanel.setBorder(new EmptyBorder(20, 30, 20, 30));

        // Info Section
        JPanel infoPanel = new JPanel(new GridLayout(1, 2, 30, 0));
        infoPanel.setOpaque(false);

        infoPanel.add(createInfoCardWithInput("Thông tin khách hàng",
                "Họ tên", "SĐT", "Giới tính"));

        infoPanel.add(createInfoCardWithInput("Thông tin đơn",
                "Mã đơn", "Ngày đặt", "Trạng thái"));

        contentPanel.add(infoPanel, BorderLayout.NORTH);

        // Table
        String[] columns = {"Tên thuốc", "Đơn vị", "Giá (VNĐ)", "Số lượng"};
        Object[][] data = {
                {"Paracetamol", "Viên", "2000", new Integer(1)},
                {"Amoxicillin", "Viên", "3000", new Integer(0)},
                {"Siro ho Bổ Phế", "Chai", "45000", new Integer(0)}
        };

        JTable table = new JTable(data, columns) {
            public boolean isCellEditable(int row, int column) {
                return column == 3;
            }
        };
        table.setRowHeight(30);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(new EmptyBorder(20, 0, 20, 0));
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        // Footer
        JPanel footerPanel = new JPanel(new BorderLayout());
        footerPanel.setOpaque(false);

        JLabel totalLabel = new JLabel("Tổng tiền: 2.000 VNĐ");
        totalLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        totalLabel.setForeground(new Color(50, 50, 50));

        JButton btnOrder = new JButton("Đặt hàng");
        btnOrder.setBackground(new Color(56, 142, 60));
        btnOrder.setForeground(Color.WHITE);
        btnOrder.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnOrder.setPreferredSize(new Dimension(150, 40));

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.add(btnOrder);

        footerPanel.add(totalLabel, BorderLayout.WEST);
        footerPanel.add(btnPanel, BorderLayout.EAST);
        footerPanel.setBorder(new EmptyBorder(20, 0, 0, 0));

        contentPanel.add(footerPanel, BorderLayout.SOUTH);

        add(contentPanel, BorderLayout.CENTER);
        setVisible(true);
    }

    private JPanel createInfoCardWithInput(String title, String label1, String label2, String label3) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.WHITE);
        card.setBorder(new CompoundBorder(
                new LineBorder(new Color(200, 220, 200), 1),
                new EmptyBorder(15, 20, 15, 20)
        ));

        JLabel lblTitle = new JLabel(title);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 16));
        lblTitle.setForeground(new Color(46, 125, 50));

        card.add(lblTitle);
        card.add(Box.createVerticalStrut(10));

        card.add(createInputRow(label1));
        card.add(Box.createVerticalStrut(10));
        card.add(createInputRow(label2));
        card.add(Box.createVerticalStrut(10));
        card.add(createInputRow(label3));

        return card;
    }

    private JPanel createInputRow(String labelText) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setOpaque(false);

        JLabel label = new JLabel(labelText + ": ");
        label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        JTextField textField = new JTextField();
        textField.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(frm_DatThuoc1::new);
    }
}
