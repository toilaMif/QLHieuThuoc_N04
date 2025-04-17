package hieuUng;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import dao.dao_ChiTietHoaDon;
import dao.dao_HoaDon;
import entity.ent_ChiTietHoaDon;
import entity.ent_HoaDon;
import entity.ent_thuoc;
import frm.frm_DoiThuoc;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class setHoaDon extends GradientPanel {
    // Constants
    private static final Color WHITE_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("Arial", Font.BOLD, 32);
    private static final Font SUBTITLE_FONT = new Font("Arial", Font.PLAIN, 10);
    private static final Font BOLD_FONT_20 = new Font("Arial", Font.BOLD, 20);
    private static final Font BOLD_FONT_14 = new Font("Arial", Font.BOLD, 14);
    private static final Font ITALIC_FONT_9 = new Font("Arial", Font.ITALIC, 9);
    private static final Font BOLD_FONT_10 = new Font("Arial", Font.BOLD, 10);
    
    // Instance variables
    private JLabel lblTotal;
    private JLabel lblVAT;
    private JTable table;
    private DefaultTableModel model;
    private ent_HoaDon hoaDon;
    private ArrayList<ent_ChiTietHoaDon> danhSachChiTiet;
    private DecimalFormat formatter;
    private JLabel lblInfo;
    private JComboBox<String> comboBoxPayment;
    private JLabel lblCustomerName;
    
    /**
     * Constructor mặc định với giao diện trống
     */
    public setHoaDon() {
        initializeComponents();
        setupEmptyTable();
    }
    
    /**
     * Constructor với mã hóa đơn đã có
     * @param maHoaDon Mã hóa đơn cần hiển thị
     */
    public setHoaDon(String maHoaDon) {
        initializeComponents();
        
        try {
            loadHoaDonData(maHoaDon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Không thể tải thông tin hóa đơn: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            setupEmptyTable();
        }
    }
    
    /**
     * Thiết lập các thành phần giao diện chung
     */
    private void initializeComponents() {
        // Setup basic panel properties
        formatter = new DecimalFormat("#,###");
        setGradientColors(WHITE_COLOR, WHITE_COLOR);
        setCornerRadius(30);
        setLayout(new BorderLayout());
        
        // Add header panel
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Add content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setBackground(WHITE_COLOR);
        
        // Add title and info panel
        JPanel infoPanel = createInfoPanel();
        contentPanel.add(infoPanel, BorderLayout.NORTH);
        
        // Add table panel
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(WHITE_COLOR);
        
        // Setup table
        String[] columnNames = {"Tên Thuốc", "SL", "Đơn vị", "Đơn giá", "T.Tiền"};
        model = new DefaultTableModel(columnNames, 0);
        table = createTable(model);
        
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        scrollPane.getViewport().setBackground(WHITE_COLOR);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        tablePanel.add(scrollPane, BorderLayout.CENTER);
        contentPanel.add(tablePanel, BorderLayout.CENTER);
        
        add(contentPanel, BorderLayout.CENTER);
        
        // Add footer panel
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    /**
     * Tạo panel header
     */
    private JPanel createHeaderPanel() {
        GradientPanel panel = new GradientPanel();
        panel.setCornerRadius(30);
        panel.setGradientColors(WHITE_COLOR, WHITE_COLOR);
        panel.setLayout(new BorderLayout());
        
        JLabel lblTitle = new JLabel("Nhà thuốc TMT", JLabel.CENTER);
        lblTitle.setFont(TITLE_FONT);
        panel.add(lblTitle, BorderLayout.NORTH);
        
        JLabel lblSubTitle = new JLabel(
            "<html>Trường IUH - đại học công nghiệp TPHCM<br>" +
            "<b><center>Phần mềm quản lý hiệu thuốc tây</b></center></html>", 
            JLabel.CENTER);
        lblSubTitle.setFont(SUBTITLE_FONT);
        panel.add(lblSubTitle, BorderLayout.CENTER);
        
        JLabel lblSeparator = new JLabel(
            "<html><hr style='width:150px; border: 2px solid black;'></html>", 
            JLabel.CENTER);
        panel.add(lblSeparator, BorderLayout.SOUTH);
        
        return panel;
    }
    
    /**
     * Tạo panel thông tin hóa đơn
     */
    private JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(WHITE_COLOR);
        
        JLabel lblDonThuoc = new JLabel("Đơn Thuốc", JLabel.CENTER);
        lblDonThuoc.setBackground(WHITE_COLOR);
        lblDonThuoc.setFont(BOLD_FONT_20);
        panel.add(lblDonThuoc, BorderLayout.NORTH);
        
        // Create info label with current date and time
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);
        
        lblInfo = new JLabel(
            "<html>Mã NV: <br>Ngày lập: " + currentDate + 
            " - Giờ lập: " + currentTime +
            "<br>Dược sĩ: <br>Bệnh nhân: - </html>"
        );
        lblInfo.setBackground(WHITE_COLOR);
        lblInfo.setFont(ITALIC_FONT_9);
        panel.add(lblInfo, BorderLayout.WEST);
        
        JLabel lblHoaDonId = new JLabel("      ", JLabel.LEFT);
        panel.add(lblHoaDonId, BorderLayout.EAST);
        
        return panel;
    }
    
    /**
     * Tạo cấu trúc bảng
     */
    private JTable createTable(DefaultTableModel model) {
        JTable newTable = new JTable(model) {
            @Override
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (c instanceof JComponent) {
                    ((JComponent) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
                }
                return c;
            }
            
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Không cho phép chỉnh sửa bảng
            }
        };
        
        newTable.setShowGrid(false);
        newTable.setIntercellSpacing(new Dimension(0, 0));
        newTable.setRowHeight(30);
        newTable.setBackground(WHITE_COLOR);
        newTable.setOpaque(true);
        newTable.getTableHeader().setBackground(WHITE_COLOR);
        newTable.getTableHeader().setForeground(Color.BLACK);
        newTable.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
        newTable.getTableHeader().setFont(BOLD_FONT_10);
        newTable.setFont(ITALIC_FONT_9);
        
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < newTable.getColumnCount(); i++) {
            newTable.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
        
        newTable.addMouseListener(new MouseAdapter() {
           

			@Override
            public void mouseClicked(MouseEvent e) {
                int row = newTable.getSelectedRow();
                if (row >= 0) { 
                   frm_DoiThuoc.tenThuocCuMuonDoi = (String) newTable.getValueAt(row, 0);
                    
                    String giaBanStr = (String) newTable.getValueAt(row, 3);
                    try {
                    	frm_DoiThuoc.giaThuocCuMuonDoi = Double.parseDouble(giaBanStr.replaceAll("[,]", ""))*1000;
                    } catch (NumberFormatException ex) {
                    	frm_DoiThuoc.giaThuocCuMuonDoi = 0.0; 
                        System.err.println("Error parsing price: " + ex.getMessage());
                    }
                    
                    System.out.println("Selected: tenThuocCuMuonDoi = " + frm_DoiThuoc.tenThuocCuMuonDoi + 
                                       ", giaThuocCuMuonDoi = " + frm_DoiThuoc.giaThuocCuMuonDoi);
                    
                    frm_DoiThuoc.jtfThuoccu.setText(frm_DoiThuoc.tenThuocCuMuonDoi);
                    frm_DoiThuoc.jtfMaThuoccu.setText(frm_DoiThuoc.giaThuocCuMuonDoi+"");
                }
            }
        });
        
        return newTable;
    }
    
    /**
     * Tạo panel footer
     */
    private JPanel createFooterPanel() {
        GradientPanel panelFooter = new GradientPanel();
        panelFooter.setCornerRadius(30);
        panelFooter.setGradientColors(WHITE_COLOR, WHITE_COLOR);
        panelFooter.setLayout(new BorderLayout());
        
        // Panel chứa dòng kẻ và VAT
        JPanel panel_2 = new JPanel(new BorderLayout());
        panel_2.setBackground(WHITE_COLOR);
        
        JPanel panel_4 = new JPanel(new BorderLayout());
        panel_4.setBackground(WHITE_COLOR);
        
        JLabel lblLine = new JLabel(
            "<html><hr style='width:1000px;border:2px dashed black;'></html>");
        lblLine.setFont(ITALIC_FONT_9);
        panel_4.add(lblLine, BorderLayout.NORTH);
        
        JLabel lblVATLabel = new JLabel("VAT: %");
        lblVATLabel.setFont(ITALIC_FONT_9);
        panel_4.add(lblVATLabel, BorderLayout.WEST);
        
        lblVAT = new JLabel("0", JLabel.RIGHT);
        lblVAT.setFont(ITALIC_FONT_9);
        panel_4.add(lblVAT, BorderLayout.EAST);
        
        panel_2.add(panel_4, BorderLayout.NORTH);
        panelFooter.add(panel_2, BorderLayout.NORTH);
        
        // Panel chứa tổng cộng và phương thức thanh toán
        JPanel panel_3 = new JPanel(new GridLayout(2, 2));
        panel_3.setBackground(WHITE_COLOR);
        
        JLabel lblTotalLabel = new JLabel("Tổng Cộng");
        lblTotalLabel.setFont(BOLD_FONT_20);
        panel_3.add(lblTotalLabel);
        
        lblTotal = new JLabel("0đ", JLabel.RIGHT);
        lblTotal.setFont(BOLD_FONT_20);
        panel_3.add(lblTotal);
        
        JLabel lblPaymentLabel = new JLabel("Hình thức thanh toán");
        lblPaymentLabel.setFont(BOLD_FONT_14);
        panel_3.add(lblPaymentLabel);
        
        String[] paymentMethods = {"Tiền mặt", "Chuyển khoản", "Quẹt thẻ"};
        comboBoxPayment = new JComboBox<>(paymentMethods);
        comboBoxPayment.setEnabled(false); // Chỉ hiển thị, không cho phép thay đổi
        panel_3.add(comboBoxPayment);
        
        panelFooter.add(panel_3, BorderLayout.CENTER);
        
        // Panel chứa lời cảm ơn
        GradientPanel panel_5 = new GradientPanel();
        panel_5.setCornerRadius(30);
        panel_5.setGradientColors(WHITE_COLOR, WHITE_COLOR);
        panel_5.setLayout(new GridLayout(0, 1, -10, -10));
        
        JLabel lblBottomLine = new JLabel(
            "<html><hr style='width:1000px;border:2px dashed black;'></html>");
        panel_5.add(lblBottomLine);
        
        lblCustomerName = new JLabel(
            "<html><center>Cảm ơn quý khách!</center></html>", 
            JLabel.CENTER);
        lblCustomerName.setFont(BOLD_FONT_20);
        panel_5.add(lblCustomerName);
        
        panelFooter.add(panel_5, BorderLayout.SOUTH);
        
        return panelFooter;
    }
    
    /**
     * Thiết lập bảng trống
     */
    private void setupEmptyTable() {
        model.setRowCount(0);
        lblTotal.setText("0đ");
        lblVAT.setText("0đ     ");
        
        // Reset thông tin khách hàng
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);
        
        lblInfo.setText(
            "<html>Mã NV: <br>Ngày lập: " + currentDate + 
            " - Giờ lập: " + currentTime +
            "<br>Dược sĩ: <br>Bệnh nhân: - </html>"
        );
        
        lblCustomerName.setText("<html><center>Cảm ơn quý khách!</center></html>");
    }
    
    /**
     * Tải dữ liệu hóa đơn từ database
     * @param maHoaDon Mã hóa đơn cần tải
     * @throws Exception nếu không tìm thấy hoặc lỗi xảy ra
     */
    private void loadHoaDonData(String maHoaDon) throws Exception {
        dao_HoaDon daoHoaDon = new dao_HoaDon();
        hoaDon = daoHoaDon.getHoaDonByMaHoaDon(maHoaDon);
        
        if (hoaDon == null) {
            throw new Exception("Không tìm thấy hóa đơn với mã " + maHoaDon);
        }
        
        // Load chi tiết hóa đơn
        dao_ChiTietHoaDon daoChiTiet = new dao_ChiTietHoaDon();
        danhSachChiTiet = daoChiTiet.getAllByMaHoaDon(maHoaDon);
        
        // Cập nhật bảng
        updateTableData();
        
        // Cập nhật thông tin và tổng tiền
        updateHoaDonInfo();
    }
    
    /**
     * Cập nhật dữ liệu trong bảng từ danh sách chi tiết hóa đơn
     */
    private void updateTableData() {
        model.setRowCount(0);
        
        if (danhSachChiTiet == null || danhSachChiTiet.isEmpty()) {
            return;
        }
        
        for (ent_ChiTietHoaDon chiTiet : danhSachChiTiet) {
            model.addRow(new Object[] {
                chiTiet.getTenThuoc(),
                chiTiet.getSoLuong(),
                chiTiet.getDonVi(),
                formatter.format(chiTiet.getGiaBan()),
                formatter.format(chiTiet.getGiaBan() * chiTiet.getSoLuong())
            });
        }
    }
    
    /**
     * Cập nhật thông tin hóa đơn và tổng tiền
     */
    private void updateHoaDonInfo() {
        if (hoaDon == null) {
            return;
        }
        
        // Cập nhật thông tin hóa đơn
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String currentDate = now.format(dateFormatter);
        String currentTime = now.format(timeFormatter);
        
        lblInfo.setText(
            "<html>Mã HĐ: " + hoaDon.getMaHoaDon() + 
            "<br>Ngày lập: " + currentDate + " - Giờ lập: " + currentTime +
            "<br>Dược sĩ: " + hoaDon.getNguoiLap() +
            "<br>Bệnh nhân: " + hoaDon.getKhachHang() + " - " + hoaDon.getSdtKhachHang() + "</html>"
        );
        
        // Cập nhật thông tin thanh toán
        updatePaymentInfo();
        
        // Cập nhật tên khách hàng ở phần cảm ơn
        lblCustomerName.setText("<html><center>Cảm ơn quý khách!<br>" + 
            hoaDon.getKhachHang() + "</center></html>");
    }
    
    /**
     * Cập nhật thông tin thanh toán (VAT, tổng tiền)
     */
    private void updatePaymentInfo() {
        if (hoaDon == null) {
            return;
        }
        
        // Tính tổng tiền và VAT
        double tongTien = hoaDon.getTongTien();
        double thueVAT = tongTien * hoaDon.getThue() / 100;
        
        // Cập nhật labels
        lblTotal.setText(formatter.format(tongTien) + "đ");
        lblVAT.setText(formatter.format(thueVAT) + "đ     ");
        
        // Cập nhật hình thức thanh toán
        String hinhThucThanhToan = hoaDon.getHinhThucThanhToan();
        if (hinhThucThanhToan != null && !hinhThucThanhToan.isEmpty()) {
            comboBoxPayment.removeAllItems();
            comboBoxPayment.addItem(hinhThucThanhToan);
        }
    }
    
    /**
     * Cập nhật hóa đơn với dữ liệu mới
     * @param maHoaDon Mã hóa đơn mới cần hiển thị
     */
    public void updateHoaDon(String maHoaDon) {
        try {
            loadHoaDonData(maHoaDon);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Không thể tải thông tin hóa đơn: " + e.getMessage(), 
                "Lỗi", JOptionPane.ERROR_MESSAGE);
            setupEmptyTable();
        }
    }
    
    /**
     * Main method để kiểm thử component
     */
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        JFrame frame = new JFrame("Hóa Đơn");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 600);
        frame.setLocationRelativeTo(null);
        
        // Test với mã hóa đơn thực
        frame.getContentPane().add(new setHoaDon("HD001"));
        frame.setVisible(true);
    }
}