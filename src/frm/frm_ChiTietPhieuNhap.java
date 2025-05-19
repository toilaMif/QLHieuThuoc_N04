package frm;

import java.awt.Font;

import frm_default.frm_default3;

public class frm_ChiTietPhieuNhap extends frm_default3 {
	public frm_ChiTietPhieuNhap() {
		setTitle("Chi Tiết Phiếu Nhập Hàng");
		jlTieuDeTrang.setText("Chi Tiết Phiếu Nhập Hàng");
		
		tieuDeCot = new String[] {"Mã phiếu nhập hàng", "Tên thuốc", "Số lượng", "Đơn vị", "Giá Bán"};
		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);
		header.setFont(new Font("Arial", Font.BOLD, 20));
		table.setRowHeight(30);
		taoThongTin();
		}

	public static void main(String[] args) {
		new frm_ChiTietPhieuNhap();
	}
}