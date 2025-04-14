package frm;

import java.awt.Font;

import frm_default.frm_default_ThongKe;

public class frm_ThongKeHoaDon extends frm_default_ThongKe{
	public frm_ThongKeHoaDon() {
		tieuDeCot = new String[] {"Mã hóa đơn", "Ngày lập", "Người lập", "Khách hàng","Thuế","Hình thức thanh toán","Tổng tiền"};
        header.setFont(new Font("Arial", Font.BOLD, 18));
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0); 
	}
public static void main(String[] args) {
	new frm_ThongKeHoaDon();
}		
}
