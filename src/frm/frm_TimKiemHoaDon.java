package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
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
import entity.ent_khachHang;
import frm_default.frm_default_timKiem;
import hieuUng.ImageResizer;
import hieuUng.boGocComboBoxUI;
import hieuUng.boGocTextField;

public class frm_TimKiemHoaDon extends frm_default_timKiem {
	private dao_khachHang khachHangDAO;
	private JPanel jpaneNhap;
	private boGocTextField textField;
	private boGocTextField textField_1;
	private boGocTextField textField_2;

	public frm_TimKiemHoaDon() {

		setTitle("Tìm kiếm");
		jTenTrang.setText("Tìm kiếm");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuHoaDon.png", 50, 50));
		tieuDeCot = new String[] {"Mã hóa đơn", "Ngày lập", "Người lập", "Khách hàng","Thuế","Hình thức thanh toán","Tổng tiền"};
        header.setFont(new Font("Arial", Font.BOLD, 18));
        tableModel.setColumnIdentifiers(tieuDeCot);
        tableModel.setRowCount(0); 

		

        JPanel jpNhapThongTin = new JPanel();
        jpNhapThongTin.setLayout(new GridLayout(1, 7, 15, 0));
        jpNhapThongTin.setBackground(Color.white);

        // Mã hóa đơn
        JPanel panelMaHD = new JPanel(new BorderLayout(5, 5));
        panelMaHD.setBackground(Color.white);
        JLabel lblMaHD = new JLabel("Mã hóa đơn:");
        boGocTextField textFieldMaHD = new boGocTextField(5);
        textFieldMaHD.setColumns(10);
        panelMaHD.add(lblMaHD, BorderLayout.NORTH);
        panelMaHD.add(textFieldMaHD, BorderLayout.CENTER);

        // Ngày lập
        JPanel panelNgayLap = new JPanel(new BorderLayout(5, 5));
        panelNgayLap.setBackground(Color.white);
        JLabel lblNgayLap = new JLabel("Ngày lập:");
        boGocTextField textFieldNgayLap = new boGocTextField(5);
        textFieldNgayLap.setColumns(10);
        textFieldNgayLap.setForeground(Color.GRAY);
        panelNgayLap.add(lblNgayLap, BorderLayout.NORTH);
        panelNgayLap.add(textFieldNgayLap, BorderLayout.CENTER);

        // Người lập
        JPanel panelNguoiLap = new JPanel(new BorderLayout(5, 5));
        panelNguoiLap.setBackground(Color.white);
        JLabel lblNguoiLap = new JLabel("Người lập:");
        boGocTextField textFieldNguoiLap = new boGocTextField(5);
        textFieldNguoiLap.setColumns(10);
        panelNguoiLap.add(lblNguoiLap, BorderLayout.NORTH);
        panelNguoiLap.add(textFieldNguoiLap, BorderLayout.CENTER);

        // Khách hàng
        JPanel panelKhachHang = new JPanel(new BorderLayout(5, 5));
        panelKhachHang.setBackground(Color.white);
        JLabel lblKhachHang = new JLabel("Khách hàng:");
        boGocTextField textFieldKhachHang = new boGocTextField(5);
        textFieldKhachHang.setColumns(10);
        panelKhachHang.add(lblKhachHang, BorderLayout.NORTH);
        panelKhachHang.add(textFieldKhachHang, BorderLayout.CENTER);

        // Thuế
        JPanel panelThue = new JPanel(new BorderLayout(5, 5));
        panelThue.setBackground(Color.white);
        JLabel lblThue = new JLabel("Thuế (%):");
        boGocTextField textFieldThue = new boGocTextField(5);
        textFieldThue.setColumns(10);
        panelThue.add(lblThue, BorderLayout.NORTH);
        panelThue.add(textFieldThue, BorderLayout.CENTER);

        // Hình thức thanh toán - ComboBox
        JPanel panelHTTT = new JPanel(new BorderLayout(5, 5));
        panelHTTT.setBackground(Color.white);
        JLabel lblHTTT = new JLabel("Hình thức thanh toán:");
        JComboBox<String> comboBoxHTTT = new JComboBox<>();
        comboBoxHTTT.addItem("Tiền mặt");
        comboBoxHTTT.addItem("Chuyển khoản");
        comboBoxHTTT.addItem("Thẻ tín dụng");
        comboBoxHTTT.setUI(new boGocComboBoxUI());
        comboBoxHTTT.setPreferredSize(new Dimension(200, 30));
        comboBoxHTTT.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        panelHTTT.add(lblHTTT, BorderLayout.NORTH);
        panelHTTT.add(comboBoxHTTT, BorderLayout.CENTER);

        // Tổng tiền
        JPanel panelTongTien = new JPanel(new BorderLayout(5, 5));
        panelTongTien.setBackground(Color.white);
        JLabel lblTongTien = new JLabel("Tổng tiền:");
        boGocTextField textFieldTongTien = new boGocTextField(5);
        textFieldTongTien.setColumns(10);
        panelTongTien.add(lblTongTien, BorderLayout.NORTH);
        panelTongTien.add(textFieldTongTien, BorderLayout.CENTER);

        // Thêm tất cả vào jpNhapThongTin
        jpNhapThongTin.add(panelMaHD);
        jpNhapThongTin.add(panelNgayLap);
        jpNhapThongTin.add(panelNguoiLap);
        jpNhapThongTin.add(panelKhachHang);
        jpNhapThongTin.add(panelThue);
        jpNhapThongTin.add(panelHTTT);
        jpNhapThongTin.add(panelTongTien);

        jpHead.add(jpNhapThongTin, BorderLayout.NORTH);



	}

	public static void main(String[] args) {
		new frm_TimKiemHoaDon();
	}

	
}
