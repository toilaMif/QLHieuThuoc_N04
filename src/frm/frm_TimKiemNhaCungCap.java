package frm;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;

import dao.dao_NhaCungCap;
import dao.dao_khachHang;
import entity.ent_NhaCungCap;
import entity.ent_khachHang;
import frm_default.frm_default_timKiem;
import hieuUng.ImageResizer;
import hieuUng.boGocComboBoxUI;
import hieuUng.boGocTextField;

public class frm_TimKiemNhaCungCap extends frm_default_timKiem implements ActionListener {
	private dao_NhaCungCap NCCDAO;

	private boGocTextField textFieldMaNCC;
	private boGocTextField textFieldTenNCC;
	private boGocTextField textFieldDiaChi;
	private boGocTextField textFieldSDT;

	public frm_TimKiemNhaCungCap() {
		setTitle("Tìm kiếm nhà cung cấp");
		jTenTrang.setText("Tìm kiếm nhà cung cấp");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuNCC.png", 50, 50));

		tieuDeCot = new String[] { "Mã nhà cung cấp", "Tên nhà cung cấp", "Địa chỉ", "Số điện thoại" };
		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		table.setRowHeight(30);

		NCCDAO = new dao_NhaCungCap();

		JPanel jpNhapThongTin = new JPanel(new GridLayout(1, 4, 15, 0));
		jpNhapThongTin.setBackground(Color.white);

		// Mã nhà cung cấp
		JPanel panelMaNCC = new JPanel(new BorderLayout(5, 5));
		panelMaNCC.setBackground(Color.white);
		JLabel lblMaNCC = new JLabel("Mã nhà cung cấp:");
		textFieldMaNCC = new boGocTextField(5);
		panelMaNCC.add(lblMaNCC, BorderLayout.NORTH);
		panelMaNCC.add(textFieldMaNCC, BorderLayout.CENTER);

		// Tên nhà cung cấp
		JPanel panelTenNCC = new JPanel(new BorderLayout(5, 5));
		panelTenNCC.setBackground(Color.white);
		JLabel lblTenNCC = new JLabel("Tên nhà cung cấp:");
		textFieldTenNCC = new boGocTextField(5);
		panelTenNCC.add(lblTenNCC, BorderLayout.NORTH);
		panelTenNCC.add(textFieldTenNCC, BorderLayout.CENTER);

		// Địa chỉ
		JPanel panelDiaChi = new JPanel(new BorderLayout(5, 5));
		panelDiaChi.setBackground(Color.white);
		JLabel lblDiaChi = new JLabel("Địa chỉ:");
		textFieldDiaChi = new boGocTextField(5);
		panelDiaChi.add(lblDiaChi, BorderLayout.NORTH);
		panelDiaChi.add(textFieldDiaChi, BorderLayout.CENTER);

		// Số điện thoại
		JPanel panelSDT = new JPanel(new BorderLayout(5, 5));
		panelSDT.setBackground(Color.white);
		JLabel lblSDT = new JLabel("Số điện thoại:");
		textFieldSDT = new boGocTextField(5);
		panelSDT.add(lblSDT, BorderLayout.NORTH);
		panelSDT.add(textFieldSDT, BorderLayout.CENTER);

		jpNhapThongTin.add(panelMaNCC);
		jpNhapThongTin.add(panelTenNCC);
		jpNhapThongTin.add(panelDiaChi);
		jpNhapThongTin.add(panelSDT);

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
			timKiemNCC();
		} else if (o.equals(btnXoarong)) {
			xoaRong();
		}
	}

	private void xoaRong() {
		textFieldMaNCC.setText("");
		textFieldTenNCC.setText("");
		textFieldDiaChi.setText("");
		textFieldSDT.setText("");
		loadData(); // Tải lại toàn bộ dữ liệu
	}

	private void timKiemNCC() {
		String ma = textFieldMaNCC.getText().trim();
		String ten = textFieldTenNCC.getText().trim();
		String diaChi = textFieldDiaChi.getText().trim();
		String sdt = textFieldSDT.getText().trim();

		tableModel.setRowCount(0);
		ArrayList<ent_NhaCungCap> list = NCCDAO.getAllNhaCungCap();

		for (ent_NhaCungCap ncc : list) {
			boolean match = true;
			if (!ma.isEmpty() && !ncc.getMaNCC().toLowerCase().contains(ma.toLowerCase())) match = false;
			if (!ten.isEmpty() && !ncc.getTenNCC().toLowerCase().contains(ten.toLowerCase())) match = false;
			if (!diaChi.isEmpty() && !ncc.getDiaChi().toLowerCase().contains(diaChi.toLowerCase())) match = false;
			if (!sdt.isEmpty() && !ncc.getsDT().contains(sdt)) match = false;

			if (match) {
				tableModel.addRow(new Object[] {
					ncc.getMaNCC(), ncc.getTenNCC(),
					ncc.getDiaChi(), ncc.getsDT()
				});
			}
		}
	}

	private void loadData() {
		tableModel.setRowCount(0);
		ArrayList<ent_NhaCungCap> list = NCCDAO.getAllNhaCungCap();
		for (ent_NhaCungCap ncc : list) {
			tableModel.addRow(new Object[] {
				ncc.getMaNCC(), ncc.getTenNCC(),
				ncc.getDiaChi(), ncc.getsDT()
			});
		}
	}

	public static void main(String[] args) {
		new frm_TimKiemNhaCungCap();
	}
}
