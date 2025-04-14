package frm;

import java.awt.BorderLayout;

import frm_default.frm_default3;
import hieuUng.theGioiThieu;

public class frm_ChiTiecKeThuoc extends frm_default3 {
	private String maKe;
	public static String maKe_Chon;
	public frm_ChiTiecKeThuoc() {
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

	}

	public static void main(String[] args) {
		new frm_ChiTiecKeThuoc();
	}
}
