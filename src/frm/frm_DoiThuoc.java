package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

import com.toedter.calendar.JDateChooser;

import dao.dao_DanhMuc;
import dao.dao_HoaDon;
import dao.dao_NhanVien;
import dao.dao_thuoc;
import entity.ent_DanhMucThuoc;
import entity.ent_HoaDon;
import entity.ent_NhanVien;
import entity.ent_thuoc;
import frm_default.frm_default;
import hieuUng.GradientPanel;
import hieuUng.HoaDon;
import hieuUng.ImageResizer;
import hieuUng.datThuoc;
import hieuUng.doiThuoc;
import hieuUng.setHoaDon;

public class frm_DoiThuoc extends frm_default implements ActionListener, MouseListener {

	private JButton jbtnInHoaDon;
	private JButton jbtnXoaRong;
	private GradientPanel painKhungDieuKhien;
	private GradientPanel bangSP;
	private JPanel panelContainer;

	private dao_thuoc thuocDAO;
	private ArrayList<ent_thuoc> danhSachThuoc;
	private ImageIcon anh;
	private GradientPanel painKhungThongTin;

	private JTextField jtfSDT;
	private datThuoc donDat;
	private JButton xacnhanDondat;
	private JButton xoarongDondat;
	private JButton jbtnPhieudoi;
	private JButton jbtnHoaDon;
	private doiThuoc doiThuoc;
	private JButton xacnhanDoiThuoc;
	private JButton xoarongDoiThuoc;
	private JTextField jtfMaHD;
	private JTextField jtfHoTen;
	private setHoaDon setHoaDon;
	private JLabel jlbLyDo;
	private JTextField jtfLyDo;
	private hieuUng.setHoaDon setHoaDon1;
	private GradientPanel painChuaHoaDon;
	private JTextField jtfMaThuocmoi;
	private JTextField jtfThuocmoi;
	private JButton btnTimKiem;
	private JButton btnXoaRongTim;
	private JTextField txtMaThuoc;
	private JTextField txtTenThuoc;
	private JComboBox cbDanhMuc;
	public static JTextField jtfThuoccu;
	public static JTextField jtfMaThuoccu;

	public static String tenThuocCuMuonDoi;
	public static Double giaThuocCuMuonDoi;
	public static String maTaiKhoanDangNhap;

	public frm_DoiThuoc() {
		super();
		setTitle("Đổi Thuốc");
		jTenTrang.setText("Đổi Thuốc");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/DoiThuoc.png", 50, 50));

		thuocDAO = new dao_thuoc();
		danhSachThuoc = thuocDAO.getAllThuoc();

		// Hoa Don Panel
		painChuaHoaDon = new GradientPanel();
		painChuaHoaDon.setGradientColors(Color.white, Color.white);
		painChuaHoaDon.setBounds(20, 145, 310, 530);
		painChuaHoaDon.setLayout(new BorderLayout());
		doiThuoc = new doiThuoc();
		painChuaHoaDon.removeAll();
		painChuaHoaDon.add(doiThuoc, BorderLayout.CENTER);
		painChuaHoaDon.revalidate();
		painChuaHoaDon.repaint();

		jpWest.add(painChuaHoaDon, BorderLayout.CENTER);

		jbtnHoaDon = new JButton("Hóa Đơn   /");
		jbtnHoaDon.setForeground(Color.WHITE);
		jbtnHoaDon.setContentAreaFilled(false);
		jbtnHoaDon.setBorderPainted(false);
		jbtnHoaDon.setFocusPainted(false);
		jbtnHoaDon.setOpaque(false);

		jbtnHoaDon.setBounds(80, 120, 120, 25);
		jpWest.add(jbtnHoaDon, BorderLayout.EAST);

		jbtnPhieudoi = new JButton("Phiếu đổi");
		jbtnPhieudoi.setForeground(Color.WHITE);
		jbtnPhieudoi.setContentAreaFilled(false);
		jbtnPhieudoi.setBorderPainted(false);
		jbtnPhieudoi.setFocusPainted(false);
		jbtnPhieudoi.setOpaque(false);
		jbtnPhieudoi.setBounds(150, 120, 120, 25);
		jpWest.add(jbtnPhieudoi, BorderLayout.EAST);

		jbtnInHoaDon = new JButton("In phiếu đổi");
		jbtnInHoaDon.setBackground(new Color(173, 230, 106));
		jbtnInHoaDon.setBounds(50, 690, 120, 45);
		jbtnInHoaDon.setForeground(Color.WHITE);
		jbtnInHoaDon.setFont(new Font("Arial", Font.BOLD, 14));
		jbtnInHoaDon.setFocusPainted(false);
		jbtnInHoaDon.setContentAreaFilled(false);
		jbtnInHoaDon.setOpaque(true);
		jbtnInHoaDon.addActionListener(this);

		jpWest.add(jbtnInHoaDon, BorderLayout.EAST);

		jbtnXoaRong = new JButton("❌ Xóa Rỗng");
		jbtnXoaRong.setBackground(Color.RED);
		jbtnXoaRong.setForeground(Color.WHITE);
		jbtnXoaRong.setFont(new Font("", Font.BOLD, 14));
		jbtnXoaRong.setBounds(180, 690, 120, 45);
		jbtnXoaRong.setFocusPainted(false);
		jbtnXoaRong.setContentAreaFilled(false);
		jbtnXoaRong.setOpaque(true);
		jbtnXoaRong.addActionListener(this);
		jpWest.add(jbtnXoaRong, BorderLayout.SOUTH);

		jpWest.revalidate();
		jpWest.repaint();

		// Control Panel
		painKhungDieuKhien = new GradientPanel();
		painKhungDieuKhien.setCornerRadius(30);
		painKhungDieuKhien.setGradientColors(Color.white, Color.white);
		painKhungDieuKhien.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
						"Tìm Thuốc", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
						new Color(180, 220, 85)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		painKhungDieuKhien.setPreferredSize(new Dimension(jpBangChinh.getPreferredSize().width - 20, 100));
		painKhungDieuKhien.setLayout(new GridLayout(1, 4, 15, 0));

		JPanel panelMaThuoc = new JPanel(new BorderLayout(5, 5));
		panelMaThuoc.setBackground(Color.white);
		JLabel lblMaThuoc = new JLabel("Mã Thuốc");
		txtMaThuoc = new JTextField();
		panelMaThuoc.add(lblMaThuoc, BorderLayout.NORTH);
		panelMaThuoc.add(txtMaThuoc, BorderLayout.CENTER);

		JPanel panelTenThuoc = new JPanel(new BorderLayout(5, 5));
		panelTenThuoc.setBackground(Color.white);
		JLabel lblTenThuoc = new JLabel("Tên Thuốc");
		txtTenThuoc = new JTextField();
		panelTenThuoc.add(lblTenThuoc, BorderLayout.NORTH);
		panelTenThuoc.add(txtTenThuoc, BorderLayout.CENTER);

		JPanel panelDanhMuc = new JPanel(new BorderLayout(5, 5));
		panelDanhMuc.setBackground(Color.white);
		JLabel lblDanhMuc = new JLabel("Danh Mục Thuốc:");
		cbDanhMuc = new JComboBox<>();
		panelDanhMuc.add(lblDanhMuc, BorderLayout.NORTH);
		panelDanhMuc.add(cbDanhMuc, BorderLayout.CENTER);

		dao_DanhMuc dao = new dao_DanhMuc();
		ArrayList<ent_DanhMucThuoc> danhMucList = dao.getAllDanhMucThuoc();

		cbDanhMuc.addItem("Tất cả");

		for (ent_DanhMucThuoc dm : danhMucList) {
			cbDanhMuc.addItem(dm.getTenDM());
		}
		panelDanhMuc.add(lblDanhMuc, BorderLayout.NORTH);
		panelDanhMuc.add(cbDanhMuc, BorderLayout.CENTER);

		JPanel panelButton = new JPanel(new BorderLayout(5, 5));
		panelButton.setBackground(Color.white);
		JLabel lblDummy = new JLabel("Nút chức năng:");
		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(new Color(101, 86, 255));
		btnTimKiem.setForeground(Color.WHITE);
		btnTimKiem.addActionListener(this);

		btnXoaRongTim = new JButton("Xóa Rỗng");
		btnXoaRongTim.setBackground(Color.RED);
		btnXoaRongTim.setForeground(Color.WHITE);
		btnXoaRongTim.addActionListener(e -> {
			txtMaThuoc.setText("");
			txtTenThuoc.setText("");
			cbDanhMuc.setSelectedIndex(0);
			HienThiSPBAN(danhSachThuoc);
		});

		panelButton.add(btnXoaRongTim, BorderLayout.EAST);
		panelButton.add(lblDummy, BorderLayout.NORTH);
		panelButton.add(btnTimKiem, BorderLayout.CENTER);

		painKhungDieuKhien.add(panelMaThuoc);
		painKhungDieuKhien.add(panelTenThuoc);
		painKhungDieuKhien.add(panelDanhMuc);
		painKhungDieuKhien.add(panelButton);

		// Product Display Panel
		bangSP = new GradientPanel();
		bangSP.setCornerRadius(30);
		bangSP.setGradientColors(Color.white, Color.white);
		bangSP.setBorder(BorderFactory.createCompoundBorder(
				BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2), "Thuốc",
						TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
						new Color(180, 220, 85)),
				BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		bangSP.setPreferredSize(new Dimension(jpBangChinh.getPreferredSize().width - 20, 400));
		bangSP.setLayout(new BorderLayout());

		panelContainer = new JPanel();
		panelContainer.setBackground(Color.white);
		panelContainer.setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));
		panelContainer.setPreferredSize(new Dimension(150, 200 * (danhSachThuoc.size() / 5 + 1)));

		JScrollPane jscSP = new JScrollPane(panelContainer);
		jscSP.getVerticalScrollBar().setUnitIncrement(20);
		bangSP.add(jscSP, BorderLayout.CENTER);

		HienThiSPBAN(danhSachThuoc);

		// Control Panel
		painKhungThongTin = new GradientPanel();
		painKhungThongTin.setCornerRadius(30);
		painKhungThongTin.setGradientColors(Color.white, Color.white);
		painKhungThongTin
				.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
								"Bảng Thông Tin", TitledBorder.LEADING, TitledBorder.TOP,
								new Font("Arial", Font.BOLD, 14), new Color(180, 220, 85)),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		painKhungThongTin.setPreferredSize(new Dimension(jpBangChinh.getPreferredSize().width - 20, 100));
		painKhungThongTin.setLayout(new BorderLayout());

		JPanel infoPanel = new JPanel(new GridLayout(1, 2, 30, 50));
		infoPanel.setBackground(new Color(245, 250, 245));

		// Panel khách hàng
		JPanel khachHang = new JPanel();
		khachHang.setLayout(null);
		khachHang.setBackground(Color.WHITE);
		khachHang.setBorder(
				new CompoundBorder(new LineBorder(new Color(200, 220, 200), 1), new EmptyBorder(15, 20, 15, 20)));

		JLabel lblKhachHang = new JLabel("Xác Nhận Hóa Đơn");
		lblKhachHang.setBounds(10, 10, 300, 20);
		lblKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblKhachHang.setForeground(new Color(46, 125, 50));
		khachHang.add(lblKhachHang, BorderLayout.NORTH);

		xacnhanDondat = new JButton("Xác Nhận");
		xacnhanDondat.setBounds(415, 120, 90, 30);
		xacnhanDondat.setBackground(new Color(101, 86, 255));
		xacnhanDondat.setForeground(Color.WHITE);

		xoarongDondat = new JButton("Xóa Rỗng");
		xoarongDondat.setBounds(415, 80, 90, 30);
		xoarongDondat.setBackground(Color.red);
		xoarongDondat.setForeground(Color.WHITE);

		khachHang.add(xacnhanDondat);

		khachHang.add(xoarongDondat);

		JLabel jlbMaHD = new JLabel("Mã hóa đơn:");
		jlbMaHD.setBounds(10, 40, 100, 20);
		jtfMaHD = new JTextField();
		jtfMaHD.setBounds(110, 40, 300, 30);

		JLabel jlbHoTen = new JLabel("Họ tên KH:");
		jlbHoTen.setBounds(10, 80, 100, 20);
		jtfHoTen = new JTextField();
		jtfHoTen.setBounds(110, 80, 300, 30);

		JLabel jlbSDT = new JLabel("SĐT:");
		jlbSDT.setBounds(10, 120, 100, 20);
		jtfSDT = new JTextField();
		jtfSDT.setBounds(110, 120, 300, 30);

//		xacnhanKhachhang = new JButton("Xác Nhận");
//		xacnhanKhachhang.setBounds(415, 120, 90, 30);
//		xacnhanKhachhang.setBackground(new Color(101, 86, 255));
//		xacnhanKhachhang.setForeground(Color.WHITE);

		// Thêm vào panel
		khachHang.add(jlbHoTen);
		khachHang.add(jtfHoTen);
		khachHang.add(jlbSDT);
		khachHang.add(jtfSDT);
		khachHang.add(jlbMaHD);
		khachHang.add(jtfMaHD);

//		khachHang.add(xacnhanKhachhang);

		// Panel đơn đặt
		JPanel DonDat = new JPanel();
		DonDat.setLayout(null);
		DonDat.setBackground(Color.WHITE);
		DonDat.setBorder(
				new CompoundBorder(new LineBorder(new Color(200, 220, 200), 1), new EmptyBorder(15, 20, 15, 20)));

		// Tiêu đề
		JLabel lblDonDat = new JLabel("Thông tin đổi thuốc");
		lblDonDat.setBounds(10, 10, 300, 20);
		lblDonDat.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblDonDat.setForeground(new Color(46, 125, 50));
		DonDat.add(lblDonDat);

		JLabel jlbMThuoccu = new JLabel("Thuốc cũ:");
		jlbMThuoccu.setBounds(10, 40, 100, 20);
		jtfThuoccu = new JTextField();
		jtfThuoccu.setBounds(110, 40, 115, 30);

		JLabel jlbThuocmoi = new JLabel("Thuốc mới:");
		jlbThuocmoi.setBounds(225, 40, 70, 20);
		jtfThuocmoi = new JTextField();
		jtfThuocmoi.setBounds(295, 40, 115, 30);

		JLabel jlbMaThuoccu = new JLabel("Giá Thuốc Cũ:");
		jlbMaThuoccu.setBounds(10, 80, 100, 20);
		jtfMaThuoccu = new JTextField();
		jtfMaThuoccu.setBounds(110, 80, 115, 30);

		jlbLyDo = new JLabel("Lý do đổi thuốc:");
		jlbLyDo.setBounds(10, 120, 100, 20);
		jtfLyDo = new JTextField();
		jtfLyDo.setBounds(110, 120, 300, 30);

		JLabel jlbMaThuocmoi = new JLabel("--> Giá Mới:");
		jlbMaThuocmoi.setBounds(225, 80, 70, 20);
		jtfMaThuocmoi = new JTextField();
		jtfMaThuocmoi.setBounds(295, 80, 115, 30);

		xacnhanDoiThuoc = new JButton("Đổi Thuốc");
		xacnhanDoiThuoc.setBounds(412, 120, 95, 30);
		xacnhanDoiThuoc.setBackground(new Color(101, 86, 255));
		xacnhanDoiThuoc.setForeground(Color.WHITE);

		xoarongDoiThuoc = new JButton("Xóa Rỗng");
		xoarongDoiThuoc.setBounds(412, 80, 95, 30);
		xoarongDoiThuoc.setBackground(Color.red);
		xoarongDoiThuoc.setForeground(Color.WHITE);

		DonDat.add(xacnhanDoiThuoc);

		DonDat.add(xoarongDoiThuoc);

		DonDat.add(jlbMThuoccu);
		DonDat.add(jtfThuoccu);
		DonDat.add(jlbThuocmoi);
		DonDat.add(jtfThuocmoi);
		DonDat.add(jlbMaThuoccu);
		DonDat.add(jtfMaThuoccu);
		DonDat.add(jlbMaThuocmoi);
		DonDat.add(jtfMaThuocmoi);
		DonDat.add(jlbLyDo);
		DonDat.add(jtfLyDo);

		infoPanel.add(khachHang);
		infoPanel.add(DonDat);

		painKhungThongTin.add(infoPanel, BorderLayout.CENTER);

		jpBangChinh.setLayout(new BorderLayout());
		jpBangChinh.add(painKhungThongTin, BorderLayout.CENTER);
		jpBangChinh.add(painKhungDieuKhien, BorderLayout.NORTH);
		jpBangChinh.add(bangSP, BorderLayout.SOUTH);

		jbtnPhieudoi.addActionListener(this);
		jbtnHoaDon.addActionListener(this);

		setVisible(true);
		jbtThoat.addActionListener(this);
		xoarongDondat.addActionListener(this);
		xacnhanDondat.addActionListener(this);
		xacnhanDoiThuoc.addActionListener(this);
		xoarongDoiThuoc.addActionListener(this);
	}

	private void HienThiSPBAN(ArrayList<ent_thuoc> danhSachThuoc) {
		panelContainer.removeAll();
		for (ent_thuoc thuoc : danhSachThuoc) {
			JPanel panelThuoc = new JPanel();
			panelThuoc.setPreferredSize(new Dimension(150, 200));
			panelThuoc.setLayout(new BorderLayout());
			panelThuoc.setName(thuoc.getMaThuoc());

			JLabel lblMaThuoc = new JLabel(thuoc.getMaThuoc(), JLabel.CENTER);
			panelThuoc.add(lblMaThuoc, BorderLayout.NORTH);

			JLabel lblHinhAnh = new JLabel();
			anh = ImageResizer.resizeImage("/img_thuoc/" + thuoc.getMaThuoc() + ".png", 150, 150);

			lblHinhAnh.setIcon(anh);
			panelThuoc.add(lblHinhAnh, BorderLayout.CENTER);

			JLabel lblTenThuoc = new JLabel(thuoc.getTenThuoc());
			panelThuoc.add(lblTenThuoc, BorderLayout.SOUTH);

			panelThuoc.setBorder(BorderFactory.createLineBorder(new Color(180, 220, 85)));
			panelThuoc.addMouseListener(this);
			panelContainer.add(panelThuoc);
		}
		panelContainer.revalidate();
		panelContainer.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		Object o = e.getSource();
		if (o.equals(jbtThoat)) {
			this.dispose();
			new frm_home();
		} else if (o.equals(jbtnHoaDon)) {

			String maHoaDonXacNhan = jtfMaHD.getText().trim();

			dao_HoaDon dao = new dao_HoaDon();
			ent_HoaDon hoaDon = dao.getHoaDonByMaHoaDon(maHoaDonXacNhan);

			if (hoaDon != null) {
				String tenKhachHangInput = jtfHoTen.getText().trim();
				String sdtKhachHangInput = jtfSDT.getText().trim();

				if (hoaDon.getKhachHang().equalsIgnoreCase(tenKhachHangInput)
						&& hoaDon.getSdtKhachHang().equals(sdtKhachHangInput)) {

					setHoaDon1 = new setHoaDon(maHoaDonXacNhan);

					setHoaDon1.setBounds(20, 145, 310, 530);
					painChuaHoaDon.removeAll();
					painChuaHoaDon.add(setHoaDon1, BorderLayout.CENTER);
					painChuaHoaDon.revalidate();
					painChuaHoaDon.repaint();

				} else {
					JOptionPane.showMessageDialog(null, "Thông tin khách hàng không khớp với hóa đơn!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy mã hóa đơn!");
			}
		} else if (o.equals(jbtnPhieudoi)) {
			System.out.println("jbtnPhieudoi");
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
			String ngayGioHienTai = dateFormat.format(new Date());
			dao_NhanVien dao = new dao_NhanVien();
			ent_NhanVien nhanVien = null;
			if (maTaiKhoanDangNhap != null && !maTaiKhoanDangNhap.isEmpty()) {
				nhanVien = dao.getNhanVienByMa(maTaiKhoanDangNhap);
			}
			if (nhanVien == null) {
				nhanVien = new ent_NhanVien();
				nhanVien.setMaNV("N/A");
				nhanVien.setHoTen("Chưa đăng nhập");
			}

			doiThuoc.lblThongTin.setText("<html>Mã NV: " + nhanVien.getMaNV() + "<br>Ngày lập: " + ngayGioHienTai
					+ "<br>Dược sĩ: " + nhanVien.getHoTen() + "<br>Bệnh nhân: " + jtfHoTen.getText() + " - "
					+ jtfSDT.getText() + "</html>");
			doiThuoc.lblCamOn.setText("<html><center>Cảm ơn quý khách!<br>" + jtfHoTen.getText() + "</center></html>");

			painChuaHoaDon.removeAll();
			painChuaHoaDon.add(doiThuoc, BorderLayout.CENTER);
			painChuaHoaDon.revalidate();
			painChuaHoaDon.repaint();

		} else if (o.equals(xacnhanDondat)) {
			System.out.println("xacnhanDondat");

			String maHoaDonXacNhan = jtfMaHD.getText().trim();

			dao_HoaDon dao = new dao_HoaDon();
			ent_HoaDon hoaDon = dao.getHoaDonByMaHoaDon(maHoaDonXacNhan);

			if (hoaDon != null) {
				String tenKhachHangInput = jtfHoTen.getText().trim();
				String sdtKhachHangInput = jtfSDT.getText().trim();

				if (hoaDon.getKhachHang().equalsIgnoreCase(tenKhachHangInput)
						&& hoaDon.getSdtKhachHang().equals(sdtKhachHangInput)) {

					doiThuoc.soDienThoaiKhachHang = sdtKhachHangInput;
					doiThuoc.tenKhachHang = tenKhachHangInput;

					JOptionPane.showMessageDialog(null, "Xác nhận hóa đơn thành công!");
					setHoaDon1 = new setHoaDon(maHoaDonXacNhan);

					setHoaDon1.setBounds(20, 145, 310, 530);
					painChuaHoaDon.removeAll();
					painChuaHoaDon.add(setHoaDon1, BorderLayout.CENTER);
					painChuaHoaDon.revalidate();
					painChuaHoaDon.repaint();

				} else {
					JOptionPane.showMessageDialog(null, "Thông tin khách hàng không khớp với hóa đơn!");
				}
			} else {
				JOptionPane.showMessageDialog(null, "Không tìm thấy mã hóa đơn!");
			}

		} else if (o.equals(xoarongDondat)) {
			System.out.println("xoarongDondat");
			jtfMaHD.setText("");
			jtfHoTen.setText("");
			jtfSDT.setText("");

		} else if (o.equals(xoarongDoiThuoc)) {
			System.out.println("xoarongDoiThuoc");
			jtfLyDo.setText("");
			jtfMaThuocmoi.setText("");
			jtfMaThuoccu.setText("");
			jtfThuocmoi.setText("");
			jtfThuoccu.setText("");
		} else if (o.equals(xacnhanDoiThuoc)) {
			System.out.println("xacnhanDoiThuoc");
			doiThuoc.lblLyDo.setText(jtfLyDo.getText().trim());

			ent_thuoc thuoccu = danhSachThuoc.stream().filter(t -> t.getTenThuoc().equals(jtfThuoccu.getText()))
					.findFirst().orElse(null);

			ent_thuoc thuocmoi = danhSachThuoc.stream().filter(t -> t.getTenThuoc().equals(jtfThuocmoi.getText()))
					.findFirst().orElse(null);

			doiThuoc.themThuocMoi(thuocmoi, 1);
			doiThuoc.themThuocCu(thuoccu, 1);

		} else if (o.equals(btnTimKiem)) {
			System.out.println("btnTimKiem");
			String maThuocSearch = txtMaThuoc.getText().trim().toLowerCase();
			String tenThuocSearch = txtTenThuoc.getText().trim().toLowerCase();
			String danhMucSelected = cbDanhMuc.getSelectedItem().toString();

			if (maThuocSearch.equals("nhập dữ liệu")) {
				maThuocSearch = "";
			}
			if (tenThuocSearch.equals("nhập dữ liệu")) {
				tenThuocSearch = "";
			}

			ArrayList<ent_thuoc> filteredList = new ArrayList<>();

			for (ent_thuoc thuoc : thuocDAO.getAllThuoc()) {
				boolean matchesMaThuoc = maThuocSearch.isEmpty()
						|| thuoc.getMaThuoc().toLowerCase().contains(maThuocSearch);
				boolean matchesTenThuoc = tenThuocSearch.isEmpty()
						|| thuoc.getTenThuoc().toLowerCase().contains(tenThuocSearch);
				boolean matchesDanhMuc = danhMucSelected.equals("Tất cả")
						|| thuoc.getTenDanhMuc().equalsIgnoreCase(danhMucSelected);

				if (matchesMaThuoc && matchesTenThuoc && matchesDanhMuc) {
					filteredList.add(thuoc);
				}
			}
			HienThiSPBAN(filteredList);

		} else if (o.equals(jbtnInHoaDon)) {
			System.out.println("jbtnInHoaDon");
			try {
				PrinterJob printerJob = PrinterJob.getPrinterJob();

				if (printerJob.printDialog()) {
					printerJob.setPrintable(new Printable() {
						@Override
						public int print(Graphics graphics, PageFormat pageFormat, int pageIndex)
								throws PrinterException {
							if (pageIndex > 0) {
								return Printable.NO_SUCH_PAGE;
							}

							Graphics2D g2d = (Graphics2D) graphics;
							g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());

							double panelWidth = painChuaHoaDon.getWidth();
							double panelHeight = painChuaHoaDon.getHeight();
							double pageWidth = pageFormat.getImageableWidth();
							double pageHeight = pageFormat.getImageableHeight();
							double scaleX = pageWidth / panelWidth;
							double scaleY = pageHeight / panelHeight;
							double scale = Math.min(scaleX, scaleY);

							g2d.scale(scale, scale);
							g2d.translate((pageWidth / scale - panelWidth) / 2, (pageHeight / scale - panelHeight) / 2);

							painChuaHoaDon.paint(g2d);

							return Printable.PAGE_EXISTS;
						}
					});

					printerJob.print();
					JOptionPane.showMessageDialog(null, "In phiếu đổi thành công!");
				}
			} catch (PrinterException ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null, "Lỗi khi in phiếu đổi: " + ex.getMessage());
			}
		}

	}

	private void removeOldActionListenersAndAdd(javax.swing.JButton button) {
		for (ActionListener al : button.getActionListeners()) {
			button.removeActionListener(al);
		}
		button.addActionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		JPanel clickedPanel = (JPanel) e.getSource();
		String maThuoc = clickedPanel.getName();

		ent_thuoc thuoc = danhSachThuoc.stream().filter(t -> t.getMaThuoc().equals(maThuoc)).findFirst().orElse(null);

		if (thuoc != null) {
			jtfThuocmoi.setText(thuoc.getTenThuoc());
			jtfMaThuocmoi.setText(thuoc.getGiaBan() + "");
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	public static void main(String[] args) {
		new frm_DoiThuoc();
	}

}