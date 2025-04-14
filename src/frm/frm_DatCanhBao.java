package frm;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.TitledBorder;

import frm_default.frm_default;
import hieuUng.GradientPanel;
import hieuUng.ImageResizer;

public class frm_DatCanhBao extends frm_default {
    private GradientPanel painKhung;
	private GradientPanel painKhungHXD;
	private JLabel 		tenke_make;



	public frm_DatCanhBao() {
        setTitle("Đặt Cảnh Báo");
        jTenTrang.setText("Đặt Cảnh Báo");
        jTenTrang.setIcon(ImageResizer.resizeImage("/image/CanhBao.png", 50, 50));

      
		tenke_make = new JLabel("Kệ A - KT0001",JLabel.CENTER);
		tenke_make.setIcon(ImageResizer.resizeImage("/image/menuKeThuoc.png",40, 40));
		tenke_make.setForeground(new Color(180, 220, 85));
		tenke_make.setFont(new Font("Arial", Font.BOLD, 36));
		tenke_make.setBounds(20, 10, 550, 50);
		
        painKhungHXD = new GradientPanel();
        painKhungHXD.setCornerRadius(30);
        painKhungHXD.setGradientColors(Color.white, Color.white);
        painKhungHXD
				.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
								"Bảng cảnh báo", TitledBorder.LEADING, TitledBorder.TOP,
								new Font("Arial", Font.BOLD, 14), new Color(180, 220, 85)),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));

        
        
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        footerPanel.setBackground(Color.white);

        JButton btnClear = new JButton("Xóa rỗng");
        btnClear.setBackground(Color.RED);
        btnClear.setForeground(Color.WHITE);
        btnClear.setFont(new Font("", Font.BOLD, 18));
        btnClear.setPreferredSize(new Dimension(150, 60));
        btnClear.setFocusPainted(false);
        btnClear.setContentAreaFilled(false);
        btnClear.setOpaque(true);
        btnClear.addActionListener(this);
        JButton btnSave = new JButton("Lưu");
        btnSave.setBackground(new Color(173, 230, 106));
        btnSave.setForeground(Color.WHITE);
        btnSave.setFont(new Font("", Font.BOLD, 18));
        btnSave.setPreferredSize(new Dimension(150, 60));
        btnSave.setFocusPainted(false);
        btnSave.setContentAreaFilled(false);
        btnSave.setOpaque(true);
        btnSave.addActionListener(this);

     

        footerPanel.add(btnClear);
        footerPanel.add(btnSave);
        jpBangChinh.add(tenke_make, BorderLayout.NORTH);
        jpBangChinh.add(footerPanel, BorderLayout.SOUTH);
        jpBangChinh.add(painKhungHXD, BorderLayout.CENTER);
    }



    public static void main(String[] args) {
        new frm_DatCanhBao();
    }
}
