package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.dao_khachHang;
import entity.ent_khachHang;
import frm_default.frm_default_timKiem;
import hieuUng.ImageResizer;
import hieuUng.boGocComboBoxUI;
import hieuUng.boGocTextField;

public class frm_TimKiemKhachHang extends frm_default_timKiem {
    private dao_khachHang khachHangDAO;
    private JPanel jpaneNhap;
    private boGocTextField textFieldMaKH;
    private boGocTextField textFieldHoTen;
    private boGocTextField textFieldSDT;
    private JComboBox<String> comboBoxGioiTinh;

    public frm_TimKiemKhachHang() {
        setTitle("Tìm kiếm khách hàng");
        jTenTrang.setText("Tìm kiếm khách hàng");
        jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuKhachHang.png", 50, 50));
        tieuDeCot = new String[] { "Mã khách hàng", "Họ và tên", "Số điện thoại", "Giới tính" };
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0);
        table.setRowHeight(30);
        table.addMouseListener(this);

        khachHangDAO = new dao_khachHang();
        loadData();

        JPanel jpNhapThongTin = new JPanel();
        jpNhapThongTin.setLayout(new GridLayout(1, 4, 20, 0)); 
        jpNhapThongTin.setBackground(Color.white);

        JPanel panelMaKH = new JPanel(new BorderLayout(5, 5));
        panelMaKH.setBackground(Color.white);
        JLabel lblMaKH = new JLabel("Mã Khách Hàng:");
        textFieldMaKH = new boGocTextField(5);
        textFieldMaKH.setColumns(15);
        panelMaKH.add(lblMaKH, BorderLayout.NORTH);
        panelMaKH.add(textFieldMaKH, BorderLayout.CENTER);

        JPanel panelHoTen = new JPanel(new BorderLayout(5, 5));
        panelHoTen.setBackground(Color.white);
        JLabel lblHoTen = new JLabel("Họ Tên:");
        textFieldHoTen = new boGocTextField(5);
        textFieldHoTen.setColumns(15);
        panelHoTen.add(lblHoTen, BorderLayout.NORTH);
        panelHoTen.add(textFieldHoTen, BorderLayout.CENTER);

        JPanel panelSDT = new JPanel(new BorderLayout(5, 5));
        panelSDT.setBackground(Color.white);
        JLabel lblSDT = new JLabel("Số Điện Thoại:");
        textFieldSDT = new boGocTextField(5);
        textFieldSDT.setColumns(15);
        panelSDT.add(lblSDT, BorderLayout.NORTH);
        panelSDT.add(textFieldSDT, BorderLayout.CENTER);

        JPanel panelGioiTinh = new JPanel(new BorderLayout(5, 5));
        panelGioiTinh.setBackground(Color.white);
        JLabel lblGioiTinh = new JLabel("Giới Tính:");
        comboBoxGioiTinh = new JComboBox<>();
        comboBoxGioiTinh.addItem("Tất cả"); // Thêm lựa chọn "Tất cả"
        comboBoxGioiTinh.addItem("Nam");
        comboBoxGioiTinh.addItem("Nữ");
        comboBoxGioiTinh.setUI(new boGocComboBoxUI());
        comboBoxGioiTinh.setBackground(Color.WHITE);
        comboBoxGioiTinh.setForeground(Color.BLACK);
        comboBoxGioiTinh.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBoxGioiTinh.setPreferredSize(new Dimension(200, 30));
        comboBoxGioiTinh.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panelGioiTinh.add(lblGioiTinh, BorderLayout.NORTH);
        panelGioiTinh.add(comboBoxGioiTinh, BorderLayout.CENTER);

        jpNhapThongTin.add(panelMaKH);
        jpNhapThongTin.add(panelHoTen);
        jpNhapThongTin.add(panelSDT);
        jpNhapThongTin.add(panelGioiTinh);
        btnTimKiem.addActionListener(this);
        btnXoarong.addActionListener(this);
        jpHead.add(jpNhapThongTin, BorderLayout.NORTH);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        super.actionPerformed(e);
        Object o = e.getSource();
        if(o.equals(btnTimKiem)) {
            timKiemKhachHang();
        } else if(o.equals(btnXoarong)) {
            xoaRong();
        }
    }
    
    private void xoaRong() {
        textFieldMaKH.setText("");
        textFieldHoTen.setText("");
        textFieldSDT.setText("");
        comboBoxGioiTinh.setSelectedIndex(0); // Chọn "Tất cả"
        loadData(); // Tải lại toàn bộ dữ liệu
    }
    
    private void timKiemKhachHang() {
        String maKH = textFieldMaKH.getText().trim();
        String hoTen = textFieldHoTen.getText().trim();
        String sdt = textFieldSDT.getText().trim();
        String gioiTinh = comboBoxGioiTinh.getSelectedItem().toString();

        tableModel.setRowCount(0); // Xóa dữ liệu cũ trong bảng
        ArrayList<ent_khachHang> danhSachKhachHang = khachHangDAO.getAllKhachHang();

        for (ent_khachHang kh : danhSachKhachHang) {
            boolean match = true;

            // Kiểm tra mã khách hàng
            if (!maKH.isEmpty() && !kh.getMaKh().toLowerCase().contains(maKH.toLowerCase())) {
                match = false;
            }

            // Kiểm tra họ tên
            if (!hoTen.isEmpty() && !kh.getHoTen().toLowerCase().contains(hoTen.toLowerCase())) {
                match = false;
            }

            // Kiểm tra số điện thoại
            if (!sdt.isEmpty() && !kh.getsDT().contains(sdt)) {
                match = false;
            }

            // Kiểm tra giới tính
            if (!gioiTinh.equals("Tất cả") && !kh.getGioiTinh().equals(gioiTinh)) {
                match = false;
            }

            // Nếu thỏa mãn tất cả điều kiện, thêm vào bảng
            if (match) {
                tableModel.addRow(new Object[] { 
                    kh.getMaKh(), 
                    kh.getHoTen(), 
                    kh.getsDT(), 
                    kh.getGioiTinh() 
                });
            }
        }
    }

    public static void main(String[] args) {
        new frm_TimKiemKhachHang();
    }

    private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_khachHang> danhSachKhachHang = khachHangDAO.getAllKhachHang();
        for (ent_khachHang kh : danhSachKhachHang) {
            tableModel.addRow(new Object[] { kh.getMaKh(), kh.getHoTen(), kh.getsDT(), kh.getGioiTinh() });
        }
    }
}