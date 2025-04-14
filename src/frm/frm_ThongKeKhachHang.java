package frm;

import java.awt.Font;
import java.util.ArrayList;

import dao.dao_khachHang;
import entity.ent_khachHang;
import frm_default.frm_default_ThongKe;

public class frm_ThongKeKhachHang extends frm_default_ThongKe{
	private dao_khachHang khachHangDAO;
	public frm_ThongKeKhachHang() {
		tieuDeCot = new String[] { "Mã khách hàng", "Họ và tên", "Số điện thoại", "Giới tính" };

		tableModel.setColumnIdentifiers(tieuDeCot);
		tableModel.setRowCount(0);

		table.setRowHeight(30);

		// Kết nối DAO
		khachHangDAO = new dao_khachHang();
		loadData();
	}
private void loadData() {

		tableModel.setRowCount(0);

		ArrayList<ent_khachHang> danhSachKhachHang = khachHangDAO.getAllKhachHang();
		for (ent_khachHang kh : danhSachKhachHang) {
			tableModel.addRow(new Object[] {

					kh.getMaKh(), kh.getHoTen(), kh.getsDT(), kh.getGioiTinh() });
		}
	}
public static void main(String[] args) {
	new frm_ThongKeKhachHang();
}		
}
