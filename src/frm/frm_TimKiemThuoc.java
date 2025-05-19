package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.dao_khachHang;
import dao.dao_thuoc;
import entity.ent_khachHang;
import entity.ent_thuoc;
import frm_default.frm_default_timKiem;
import hieuUng.ImageResizer;
import hieuUng.boGocComboBoxUI;
import hieuUng.boGocTextField;

public class frm_TimKiemThuoc extends frm_default_timKiem {
	private dao_khachHang khachHangDAO;
	private JPanel jpaneNhap;
	private boGocTextField textField;
	private boGocTextField textField_1;
	private boGocTextField textField_2;
	private dao_thuoc thuocDAO;

	public frm_TimKiemThuoc() {

		setTitle("Tìm kiếm Thuốc");
		jTenTrang.setText("Tìm kiếm Thuốc");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuThuoc.png", 50, 50));
        tieuDeCot = new String[] {"Mã thuốc", "Tên thuốc", "Đơn vị", "Danh mục","Giá bán","Hạn sử dụng","Nhà cung cấp"};
        
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0); 
        
        table.setRowHeight(30);

		

        JPanel jpNhapThongTin = new JPanel();
        jpNhapThongTin.setLayout(new GridLayout(1, 8, 20, 10)); // 2 hàng, 4 cột
        jpNhapThongTin.setBackground(Color.white);

        // Mã thuốc
        JPanel panelMaThuoc = new JPanel(new BorderLayout(5, 5));
        panelMaThuoc.setBackground(Color.white);
        JLabel lblMaThuoc = new JLabel("Mã thuốc:");
        boGocTextField textFieldMaThuoc = new boGocTextField(5);
        textFieldMaThuoc.setColumns(15);
        panelMaThuoc.add(lblMaThuoc, BorderLayout.NORTH);
        panelMaThuoc.add(textFieldMaThuoc, BorderLayout.CENTER);

        // Tên thuốc
        JPanel panelTenThuoc = new JPanel(new BorderLayout(5, 5));
        panelTenThuoc.setBackground(Color.white);
        JLabel lblTenThuoc = new JLabel("Tên thuốc:");
        boGocTextField textFieldTenThuoc = new boGocTextField(5);
        textFieldTenThuoc.setColumns(15);
        panelTenThuoc.add(lblTenThuoc, BorderLayout.NORTH);
        panelTenThuoc.add(textFieldTenThuoc, BorderLayout.CENTER);

        // Đơn vị (ComboBox)
        JPanel panelDonVi = new JPanel(new BorderLayout(5, 5));
        panelDonVi.setBackground(Color.white);
        JLabel lblDonVi = new JLabel("Đơn vị:");
        JComboBox<String> comboBoxDonVi = new JComboBox<>();
        comboBoxDonVi.addItem("Viên");
        comboBoxDonVi.addItem("Hộp");
        comboBoxDonVi.addItem("Chai");
        comboBoxDonVi.setUI(new boGocComboBoxUI());
        comboBoxDonVi.setBackground(Color.WHITE);
        comboBoxDonVi.setForeground(Color.BLACK);
        comboBoxDonVi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBoxDonVi.setPreferredSize(new Dimension(200, 30));
        comboBoxDonVi.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panelDonVi.add(lblDonVi, BorderLayout.NORTH);
        panelDonVi.add(comboBoxDonVi, BorderLayout.CENTER);

        // Danh mục (ComboBox)
        JPanel panelDanhMuc = new JPanel(new BorderLayout(5, 5));
        panelDanhMuc.setBackground(Color.white);
        JLabel lblDanhMuc = new JLabel("Danh mục:");
        JComboBox<String> comboBoxDanhMuc = new JComboBox<>();
        comboBoxDanhMuc.addItem("Thuốc Xoang Dị Ứng");
        comboBoxDanhMuc.addItem("Kháng sinh");
        comboBoxDanhMuc.addItem("Vitamin");
        comboBoxDanhMuc.addItem("Giảm đau");
        comboBoxDanhMuc.setUI(new boGocComboBoxUI());
        comboBoxDanhMuc.setBackground(Color.WHITE);
        comboBoxDanhMuc.setForeground(Color.BLACK);
        comboBoxDanhMuc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        comboBoxDanhMuc.setPreferredSize(new Dimension(200, 30));
        comboBoxDanhMuc.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panelDanhMuc.add(lblDanhMuc, BorderLayout.NORTH);
        panelDanhMuc.add(comboBoxDanhMuc, BorderLayout.CENTER);

        // Giá bán
        JPanel panelGiaBan = new JPanel(new BorderLayout(5, 5));
        panelGiaBan.setBackground(Color.white);
        JLabel lblGiaBan = new JLabel("Giá bán:");
        boGocTextField textFieldGiaBan = new boGocTextField(5);
        textFieldGiaBan.setColumns(15);
        panelGiaBan.add(lblGiaBan, BorderLayout.NORTH);
        panelGiaBan.add(textFieldGiaBan, BorderLayout.CENTER);

        // Hạn sử dụng
        JPanel panelHanSD = new JPanel(new BorderLayout(5, 5));
        panelHanSD.setBackground(Color.white);
        JLabel lblHanSD = new JLabel("Hạn sử dụng:");
        boGocTextField txtHanSD = new boGocTextField(5);
        txtHanSD.setColumns(15);
        txtHanSD.setForeground(Color.GRAY);
        panelHanSD.add(lblHanSD, BorderLayout.NORTH);
        panelHanSD.add(txtHanSD, BorderLayout.CENTER);

        // Nhà cung cấp
        JPanel panelNCC = new JPanel(new BorderLayout(5, 5));
        panelNCC.setBackground(Color.white);
        JLabel lblNCC = new JLabel("Nhà cung cấp:");
        boGocTextField textFieldNCC = new boGocTextField(5);
        textFieldNCC.setColumns(15);
        panelNCC.add(lblNCC, BorderLayout.NORTH);
        panelNCC.add(textFieldNCC, BorderLayout.CENTER);

        // Add vào panel chính
        jpNhapThongTin.add(panelMaThuoc);
        jpNhapThongTin.add(panelTenThuoc);
        jpNhapThongTin.add(panelDonVi);
        jpNhapThongTin.add(panelDanhMuc);
        jpNhapThongTin.add(panelGiaBan);
        jpNhapThongTin.add(panelHanSD);
        jpNhapThongTin.add(panelNCC);

        // Thêm vào vùng chính
        jpHead.add(jpNhapThongTin, BorderLayout.NORTH);
        thuocDAO = new dao_thuoc();
        loadData();


	}

	public static void main(String[] args) {
		new frm_TimKiemThuoc();
	}

	 private void loadData() {
	        tableModel.setRowCount(0);

	        ArrayList<ent_thuoc> danhSachThuoc = thuocDAO.getAllThuoc();
	        for (ent_thuoc thuoc : danhSachThuoc) {
	            tableModel.addRow(new Object[] {
	                thuoc.getMaThuoc(),
	                thuoc.getTenThuoc(),
	                thuoc.getTenDonVi(),
	                thuoc.getTenDanhMuc(),
	                thuoc.getGiaBan(),
	                thuoc.getHanSuDung(),
	                thuoc.getTenNCC()
	            });
	        }
	    }
}
