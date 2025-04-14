package frm;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.table.DefaultTableModel;
import frm_default.frm_default2;
import hieuUng.ImageResizer;

public class frm_PhieuNhapHang extends frm_default2 {
    
    public frm_PhieuNhapHang() {
        setTitle("Phiếu nhập hàng");
        jTenTrang.setText("Phiếu nhập hàng");
        jTenTrang.setIcon(ImageResizer.resizeImage("/image/munuPhieuNH.png", 50, 50));
        
        
        timkiem1.setText("Mã phiếu nhập");
        timkiem2.setText("Ngày nhập hàng");
        jpHead.setLayout(new FlowLayout(FlowLayout.CENTER, 30,10));
        tieuDeCot = new String[] {"Mã phiếu nhập hàng", "Mã nhà cung cấp","ngày nhập", "Tổng tiền"};
        
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0); 
        
        table.setRowHeight(30); 
    }

    public static void main(String[] args) {
        new frm_PhieuNhapHang();
    }
}