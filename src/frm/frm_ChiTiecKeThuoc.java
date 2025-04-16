package frm;

import java.awt.BorderLayout;
import java.util.ArrayList;

import dao.dao_ChiTietKeThuoc;
import dao.dao_KeThuoc;
import entity.ent_ChiTietKeThuoc;
import entity.ent_KeThuoc;
import frm_default.frm_default3;
import hieuUng.theGioiThieu;

public class frm_ChiTiecKeThuoc extends frm_default3 {
	private String maKe;
	private dao_ChiTietKeThuoc DAO;
	public static String maKe_Chon;
	public frm_ChiTiecKeThuoc() {
		 DAO = new dao_ChiTietKeThuoc();
	        loadData();
		setTitle("Trang Chi Tiếc Kệ Thuốc");
		maKe = "Mã Kệ";
		jlTieuDeTrang.setText("Chi Tiếc Kệ Thuốc - "+ maKe_Chon);
		
		if (maKe_Chon != null) {
			
			jlTieuDeTrang.setText("Chi Tiếc Kệ Thuốc - "+ maKe_Chon);
		} else {
			maKe = "Mã Kệ";
			jlTieuDeTrang.setText("Chi Tiếc Kệ Thuốc - "+ maKe);
		}
		 tieuDeCot = new String[] {"Mã Thuốc", "Tên Thuốc", "Đơn vị", "Danh Mục", "Hạn sử dụng", "Số lượng"};
	        
	        tableModel.setColumnIdentifiers(tieuDeCot);
	        tableModel.setRowCount(0); 
	        
	        table.setRowHeight(30);
	        taoThongTin();
	        
	        DAO = new dao_ChiTietKeThuoc();
	        loadData();

	}
	private void loadData() {
        tableModel.setRowCount(0);
        ArrayList<ent_ChiTietKeThuoc> danhSachKe = DAO.getChiTietKeThuocByMaKe(maKe_Chon);
        for (ent_ChiTietKeThuoc ke : danhSachKe) {
            tableModel.addRow(new Object[] {
                ke.getMaThuoc(),ke.getTenThuoc(),ke.getDonVi(),ke.getDanhMuc(),ke.getHanSuDung(),ke.getSoLuong()
            });
        }
    }
	public static void main(String[] args) {
		new frm_ChiTiecKeThuoc();
	}
}
