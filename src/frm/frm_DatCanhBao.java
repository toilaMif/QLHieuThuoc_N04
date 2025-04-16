package frm;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import dao.dao_KeThuoc;
import entity.ent_KeThuoc;
import frm_default.frm_default;
import hieuUng.GradientPanel;
import hieuUng.ImageResizer;
import hieuUng.menu;
import hieuUng.menuKeThuoc;

public class frm_DatCanhBao extends frm_default {
	private GradientPanel painKhung;
	private GradientPanel painKhungHXD;
	public static JLabel tenke_make;
	private menuKeThuoc menuPanel;
	private dao_KeThuoc dao;
	private JPanel jpanelHXD;
	private JPanel jpanelSl;
	private JPanel jpanelTonKho;

	public frm_DatCanhBao() {
		setTitle("Đặt Cảnh Báo");
		jTenTrang.setText("Đặt Cảnh Báo");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/CanhBao.png", 50, 50));

		tenke_make = new JLabel("Kệ A - KT0001", JLabel.CENTER);
		tenke_make.setIcon(ImageResizer.resizeImage("/image/menuKeThuoc.png", 40, 40));
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

		painKhungHXD.setLayout(new GridLayout(1, 3, 5, 5));

		jpanelHXD = new JPanel();
        jpanelHXD.setBackground(Color.white);
        jpanelHXD.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
                        "Hạn sử dụng", TitledBorder.LEADING, TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14), new Color(180, 220, 85)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jpanelHXD.setLayout(new BoxLayout(jpanelHXD, BoxLayout.Y_AXIS));

        ButtonGroup nhomHanSuDung = new ButtonGroup();
        JRadioButton radThoiGianConLai = new JRadioButton("Thời gian còn lại trước khi hết hạn", true);
        nhomHanSuDung.add(radThoiGianConLai);
        radThoiGianConLai.setFont(new Font("Arial", Font.PLAIN, 14));
        radThoiGianConLai.setBackground(Color.white);
        jpanelHXD.add(radThoiGianConLai);

        JPanel hangHanSuDung = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        hangHanSuDung.setBackground(Color.white);
        JTextField txtThoiGian = new JTextField("30", 20);
        txtThoiGian.setFont(new Font("Arial", Font.PLAIN, 14));
        JComboBox<String> cboDonViThoiGian = new JComboBox<>(new String[]{"ngày", "tuần"});
        cboDonViThoiGian.setFont(new Font("Arial", Font.PLAIN, 14));
        hangHanSuDung.add(txtThoiGian);
        hangHanSuDung.add(cboDonViThoiGian);
        jpanelHXD.add(hangHanSuDung);

        JLabel lblGhiChuHanSuDung = new JLabel("* Chức năng này sẽ báo trước khi thuốc hết hạn");
        lblGhiChuHanSuDung.setForeground(new Color(177, 0, 0));
        lblGhiChuHanSuDung.setFont(new Font("Arial", Font.PLAIN, 12));
        jpanelHXD.add(lblGhiChuHanSuDung);

        painKhungHXD.add(jpanelHXD);

     // Panel Số lượng
        jpanelSl = new JPanel();
        jpanelSl.setBackground(Color.white);
        jpanelSl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
                        "Số lượng", TitledBorder.LEADING, TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14), new Color(180, 220, 85)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jpanelSl.setLayout(new BoxLayout(jpanelSl, BoxLayout.Y_AXIS));

        ButtonGroup nhomSoLuong = new ButtonGroup();
        JRadioButton radSoLuongToiThieu = new JRadioButton("Số lượng tối thiểu", true);
        nhomSoLuong.add(radSoLuongToiThieu);
        radSoLuongToiThieu.setFont(new Font("Arial", Font.PLAIN, 14));
        radSoLuongToiThieu.setBackground(Color.white);
        jpanelSl.add(radSoLuongToiThieu);

        JPanel hangSoLuongToiThieu = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        hangSoLuongToiThieu.setBackground(Color.white);
        JTextField txtSoLuongToiThieu = new JTextField("105", 20);
        txtSoLuongToiThieu.setFont(new Font("Arial", Font.PLAIN, 14));
        JComboBox<String> cboDonViToiThieu = new JComboBox<>(new String[]{"Viên"});
        cboDonViToiThieu.setFont(new Font("Arial", Font.PLAIN, 14));
        hangSoLuongToiThieu.add(txtSoLuongToiThieu);
        hangSoLuongToiThieu.add(cboDonViToiThieu);
        jpanelSl.add(hangSoLuongToiThieu);

        JRadioButton radSoLuongToiDa = new JRadioButton("Số lượng tối đa");
        nhomSoLuong.add(radSoLuongToiDa);
        radSoLuongToiDa.setFont(new Font("Arial", Font.PLAIN, 14));
        radSoLuongToiDa.setBackground(Color.white);
        jpanelSl.add(radSoLuongToiDa);

        JPanel hangSoLuongToiDa = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        hangSoLuongToiDa.setBackground(Color.white);
        JTextField txtSoLuongToiDa = new JTextField("200", 20);
        txtSoLuongToiDa.setFont(new Font("Arial", Font.PLAIN, 14));
        JComboBox<String> cboDonViToiDa = new JComboBox<>(new String[]{"Viên"});
        cboDonViToiDa.setFont(new Font("Arial", Font.PLAIN, 14));
        hangSoLuongToiDa.add(txtSoLuongToiDa);
        hangSoLuongToiDa.add(cboDonViToiDa);
        jpanelSl.add(hangSoLuongToiDa);

        JLabel lblGhiChuSoLuong = new JLabel("* Kiểm soát giới hạn tồn kho");
        lblGhiChuSoLuong.setForeground(new Color(177, 0, 0));
        lblGhiChuSoLuong.setFont(new Font("Arial", Font.PLAIN, 12));
        jpanelSl.add(lblGhiChuSoLuong);

        painKhungHXD.add(jpanelSl);

        // Panel Tồn kho
        jpanelTonKho = new JPanel();
        jpanelTonKho.setBackground(Color.white);
        jpanelTonKho.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
                        "Tồn kho", TitledBorder.LEADING, TitledBorder.TOP,
                        new Font("Arial", Font.BOLD, 14), new Color(180, 220, 85)),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jpanelTonKho.setLayout(new BoxLayout(jpanelTonKho, BoxLayout.Y_AXIS));

        ButtonGroup nhomTonKho = new ButtonGroup();
        JRadioButton radThoiGianLuuTru = new JRadioButton("Thời gian lưu trữ");
        nhomTonKho.add(radThoiGianLuuTru);
        radThoiGianLuuTru.setFont(new Font("Arial", Font.PLAIN, 14));
        radThoiGianLuuTru.setBackground(Color.white);
        jpanelTonKho.add(radThoiGianLuuTru);

        JPanel hangThoiGianLuuTru = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        hangThoiGianLuuTru.setBackground(Color.white);
        JTextField txtThoiGianLuuTru = new JTextField("30", 20);
        txtThoiGianLuuTru.setFont(new Font("Arial", Font.PLAIN, 14));
        JComboBox<String> cboDonViLuuTru = new JComboBox<>(new String[]{"ngày", "tuần"});
        cboDonViLuuTru.setFont(new Font("Arial", Font.PLAIN, 14));
        hangThoiGianLuuTru.add(txtThoiGianLuuTru);
        hangThoiGianLuuTru.add(cboDonViLuuTru);
        jpanelTonKho.add(hangThoiGianLuuTru);

        JRadioButton radPhanTramBan = new JRadioButton("Số % bán trong thời hạn", true);
        nhomTonKho.add(radPhanTramBan);
        radPhanTramBan.setFont(new Font("Arial", Font.PLAIN, 14));
        radPhanTramBan.setBackground(Color.white);
        jpanelTonKho.add(radPhanTramBan);

        JPanel hangPhanTramBan = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        hangPhanTramBan.setBackground(Color.white);
        JTextField txtPhanTramBan = new JTextField("5", 20);
        txtPhanTramBan.setFont(new Font("Arial", Font.PLAIN, 14));
        JLabel lblPhanTram = new JLabel("%");
        lblPhanTram.setFont(new Font("Arial", Font.PLAIN, 14));
        hangPhanTramBan.add(txtPhanTramBan);
        hangPhanTramBan.add(lblPhanTram);
        jpanelTonKho.add(hangPhanTramBan);

        JLabel lblGhiChuTonKho = new JLabel("*Chú thích sản phẩm bán ít hơn số % trên tổng số sản phẩm báo tồn kho",JLabel.LEFT);
        lblGhiChuTonKho.setForeground(new Color(177, 0, 0));
        lblGhiChuTonKho.setFont(new Font("Arial", Font.PLAIN, 12));
        jpanelTonKho.add(lblGhiChuTonKho);

        painKhungHXD.add(jpanelTonKho);

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

		dao = new dao_KeThuoc();
		ArrayList<ent_KeThuoc> danhSachKe = dao.getAllKeThuoc();
		
		

		ArrayList<Object[]> menuItemsList = new ArrayList<>();

		for (ent_KeThuoc ke : danhSachKe) {
			String tenMenu = ke.getTenKe() + " - " + ke.getMaKe();
			String iconPath = "/image/menuKeThuoc.png";
			menuItemsList.add(new Object[] { tenMenu, iconPath });
		}

		Object[][] menuItems = new Object[menuItemsList.size()][2];
		for (int i = 0; i < menuItemsList.size(); i++) {
			menuItems[i] = menuItemsList.get(i);
		}

		menuPanel = new menuKeThuoc(menuItems);
		JScrollPane scrollPane = new JScrollPane(menuPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.setBounds(10, 120, 330, 550);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(0, 0));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		jpWest.add(scrollPane, BorderLayout.CENTER);

	}

	public static void main(String[] args) {
		new frm_DatCanhBao();
	}
}
