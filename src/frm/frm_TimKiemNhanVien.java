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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import dao.dao_NhanVien;
import dao.dao_khachHang;
import entity.ent_NhanVien;
import entity.ent_khachHang;
import frm_default.frm_default_timKiem;
import hieuUng.ImageResizer;
import hieuUng.boGocComboBoxUI;
import hieuUng.boGocTextField;

public class frm_TimKiemNhanVien extends frm_default_timKiem implements ActionListener {
	private dao_NhanVien nhanVienDAO;

	private boGocTextField textFieldMaNV;
	private boGocTextField textFieldHoTen;
	private boGocTextField textFieldSDT;
	private boGocTextField textFieldEmail;
	private boGocTextField textFieldNgaySinh;
	private boGocTextField textFieldNgayVao;
	private JComboBox<String> comboBoxChucVu;
	private JComboBox<String> comboBoxGioiTinh;

	public frm_TimKiemNhanVien() {
		setTitle("Tìm kiếm nhân viên");
		jTenTrang.setText("Tìm kiếm nhân viên");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuNhanVien.png", 50, 50));
		tieuDeCot = new String[] { "Mã nhân viên", "Họ tên", "Số điện thoại", "Email", "Ngày sinh", "Ngày vào làm", "Chức vụ", "Giới tính" };
		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		table.setRowHeight(30);
		table.addMouseListener(this);

		nhanVienDAO = new dao_NhanVien();

		JPanel jpNhapThongTin = new JPanel();
		jpNhapThongTin.setLayout(new GridLayout(1, 8, 15, 0));
		jpNhapThongTin.setBackground(Color.white);

		// Mã nhân viên
		JPanel panelMaNV = new JPanel(new BorderLayout(5, 5));
		panelMaNV.setBackground(Color.white);
		JLabel lblMaNV = new JLabel("Mã nhân viên:");
		textFieldMaNV = new boGocTextField(5);
		panelMaNV.add(lblMaNV, BorderLayout.NORTH);
		panelMaNV.add(textFieldMaNV, BorderLayout.CENTER);

		// Họ tên
		JPanel panelHoTen = new JPanel(new BorderLayout(5, 5));
		panelHoTen.setBackground(Color.white);
		JLabel lblHoTen = new JLabel("Họ tên:");
		textFieldHoTen = new boGocTextField(5);
		panelHoTen.add(lblHoTen, BorderLayout.NORTH);
		panelHoTen.add(textFieldHoTen, BorderLayout.CENTER);

		// Số điện thoại
		JPanel panelSDT = new JPanel(new BorderLayout(5, 5));
		panelSDT.setBackground(Color.white);
		JLabel lblSDT = new JLabel("Số điện thoại:");
		textFieldSDT = new boGocTextField(5);
		panelSDT.add(lblSDT, BorderLayout.NORTH);
		panelSDT.add(textFieldSDT, BorderLayout.CENTER);

		// Email
		JPanel panelEmail = new JPanel(new BorderLayout(5, 5));
		panelEmail.setBackground(Color.white);
		JLabel lblEmail = new JLabel("Email:");
		textFieldEmail = new boGocTextField(5);
		panelEmail.add(lblEmail, BorderLayout.NORTH);
		panelEmail.add(textFieldEmail, BorderLayout.CENTER);

		// Ngày sinh
		JPanel panelNgaySinh = new JPanel(new BorderLayout(5, 5));
		panelNgaySinh.setBackground(Color.white);
		JLabel lblNgaySinh = new JLabel("Ngày sinh:");
		textFieldNgaySinh = new boGocTextField(5);
		panelNgaySinh.add(lblNgaySinh, BorderLayout.NORTH);
		panelNgaySinh.add(textFieldNgaySinh, BorderLayout.CENTER);

		// Ngày vào làm
		JPanel panelNgayVao = new JPanel(new BorderLayout(5, 5));
		panelNgayVao.setBackground(Color.white);
		JLabel lblNgayVao = new JLabel("Ngày vào làm:");
		textFieldNgayVao = new boGocTextField(5);
		panelNgayVao.add(lblNgayVao, BorderLayout.NORTH);
		panelNgayVao.add(textFieldNgayVao, BorderLayout.CENTER);

		// Chức vụ
		JPanel panelChucVu = new JPanel(new BorderLayout(5, 5));
		panelChucVu.setBackground(Color.white);
		JLabel lblChucVu = new JLabel("Chức vụ:");
		comboBoxChucVu = new JComboBox<>();
		comboBoxChucVu.addItem("Tất cả");
		comboBoxChucVu.addItem("Quản lý");
		comboBoxChucVu.addItem("Nhân viên");
		comboBoxChucVu.setUI(new boGocComboBoxUI());
		panelChucVu.add(lblChucVu, BorderLayout.NORTH);
		panelChucVu.add(comboBoxChucVu, BorderLayout.CENTER);

		// Giới tính
		JPanel panelGioiTinh = new JPanel(new BorderLayout(5, 5));
		panelGioiTinh.setBackground(Color.white);
		JLabel lblGioiTinh = new JLabel("Giới tính:");
		comboBoxGioiTinh = new JComboBox<>();
		comboBoxGioiTinh.addItem("Tất cả");
		comboBoxGioiTinh.addItem("Nam");
		comboBoxGioiTinh.addItem("Nữ");
		comboBoxGioiTinh.setUI(new boGocComboBoxUI());
		panelGioiTinh.add(lblGioiTinh, BorderLayout.NORTH);
		panelGioiTinh.add(comboBoxGioiTinh, BorderLayout.CENTER);

		// Thêm các panel vào jpNhapThongTin
		jpNhapThongTin.add(panelMaNV);
		jpNhapThongTin.add(panelHoTen);
		jpNhapThongTin.add(panelSDT);
		jpNhapThongTin.add(panelEmail);
		jpNhapThongTin.add(panelNgaySinh);
		jpNhapThongTin.add(panelNgayVao);
		jpNhapThongTin.add(panelChucVu);
		jpNhapThongTin.add(panelGioiTinh);

		btnTimKiem.addActionListener(this);
		btnXoarong.addActionListener(this);
		jpHead.add(jpNhapThongTin, BorderLayout.NORTH);

		loadData();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			timKiemNhanVien();
		} else if (o.equals(btnXoarong)) {
			xoaRong();
		}
	}

	private void xoaRong() {
		textFieldMaNV.setText("");
		textFieldHoTen.setText("");
		textFieldSDT.setText("");
		textFieldEmail.setText("");
		textFieldNgaySinh.setText("");
		textFieldNgayVao.setText("");
		comboBoxChucVu.setSelectedIndex(0);
		comboBoxGioiTinh.setSelectedIndex(0);
		loadData(); // Tải lại toàn bộ dữ liệu
	}

	private void timKiemNhanVien() {
		String maNV = textFieldMaNV.getText().trim();
		String hoTen = textFieldHoTen.getText().trim();
		String sdt = textFieldSDT.getText().trim();
		String email = textFieldEmail.getText().trim();
		String ngaySinh = textFieldNgaySinh.getText().trim();
		String ngayVao = textFieldNgayVao.getText().trim();
		String chucVu = comboBoxChucVu.getSelectedItem().toString();
		String gioiTinh = comboBoxGioiTinh.getSelectedItem().toString();

		tableModel.setRowCount(0);
		ArrayList<ent_NhanVien> danhSachNhanVien = nhanVienDAO.getAllNhanVien();

		for (ent_NhanVien nv : danhSachNhanVien) {
			boolean match = true;

			if (!maNV.isEmpty() && !nv.getMaNV().toLowerCase().contains(maNV.toLowerCase())) match = false;
			if (!hoTen.isEmpty() && !nv.getHoTen().toLowerCase().contains(hoTen.toLowerCase())) match = false;
			if (!sdt.isEmpty() && !nv.getsDT().contains(sdt)) match = false;
			if (!email.isEmpty() && !nv.getEmail().toLowerCase().contains(email.toLowerCase())) match = false;
			if (!ngaySinh.isEmpty() && !nv.getNgaySinh().toString().contains(ngaySinh)) match = false;
			if (!ngayVao.isEmpty() && !nv.getNgayVaoLam().toString().contains(ngayVao)) match = false;
			if (!chucVu.equals("Tất cả") && !nv.getChucVu().equalsIgnoreCase(chucVu)) match = false;
			if (!gioiTinh.equals("Tất cả") && !nv.getGioiTinh().equalsIgnoreCase(gioiTinh)) match = false;

			if (match) {
				tableModel.addRow(new Object[] {
					nv.getMaNV(), nv.getHoTen(), nv.getsDT(), nv.getEmail(),
					nv.getNgaySinh(), nv.getNgayVaoLam(), nv.getChucVu(), nv.getGioiTinh()
				});
			}
		}
	}

	public static void main(String[] args) {
		new frm_TimKiemNhanVien();
	}

	private void loadData() {
		tableModel.setRowCount(0);
		ArrayList<ent_NhanVien> danhSachNhanVien = nhanVienDAO.getAllNhanVien();
		for (ent_NhanVien nv : danhSachNhanVien) {
			tableModel.addRow(new Object[] {
				nv.getMaNV(), nv.getHoTen(), nv.getsDT(), nv.getEmail(),
				nv.getNgaySinh(), nv.getNgayVaoLam(), nv.getChucVu(), nv.getGioiTinh()
			});
		}
	}
}

