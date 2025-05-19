package hieuUng;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import dao.dao_NhanVien;
import entity.ent_NhanVien;
import entity.ent_thuoc;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class doiThuoc extends GradientPanel {

	private JLabel lblTongTien;
	private JTable bangThuocCu;
	public static  JTable bangThuocMoi;
	public JLabel lblTienVAT;
	private DefaultTableModel modelThuocCu;
	public static DefaultTableModel modelThuocMoi;
	public static String[][] duLieuThuocMoi = {};
	public static String[][] duLieuThuocCu = {};
	public static JLabel lblThongTin;

	public static String maTaiKhoanDangNhap;
	public static String tenKhachHang;
	public static String soDienThoaiKhachHang;
	public static JLabel lblLyDo;
	public static JLabel lblCamOn;

	public doiThuoc() {
		// Kiểm tra nhân viên
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
		setGradientColors(Color.white, Color.white);
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		setLayout(new BorderLayout());

		// Panel tiêu đề
		JPanel panelTieuDe = new JPanel();
		panelTieuDe.setBackground(Color.WHITE);
		panelTieuDe.setLayout(new BorderLayout());
		add(panelTieuDe, BorderLayout.NORTH);

		JLabel lblTieuDe = new JLabel("Nhà thuốc TMT", JLabel.CENTER);
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 32));
		panelTieuDe.add(lblTieuDe, BorderLayout.NORTH);

		JLabel lblPhuDe = new JLabel(
				"<html>Trường IUH - Đại học Công nghiệp TPHCM<br><b><center>Phần mềm quản lý hiệu thuốc tây</b></center></html>",
				JLabel.CENTER);
		lblPhuDe.setFont(new Font("Arial", Font.PLAIN, 10));
		panelTieuDe.add(lblPhuDe, BorderLayout.CENTER);

		JLabel lblDuongKe = new JLabel("<html><hr style='width:150px; border: 2px solid black;'></html>",
				JLabel.CENTER);
		panelTieuDe.add(lblDuongKe, BorderLayout.SOUTH);

		// Panel nội dung
		JPanel panelNoiDung = new JPanel(new BorderLayout());
		panelNoiDung.setBackground(Color.WHITE);
		add(panelNoiDung, BorderLayout.CENTER);

		// Panel thông tin
		JPanel panelThongTin = new JPanel();
		panelThongTin.setBackground(Color.WHITE);
		panelThongTin.setLayout(new BorderLayout(0, 0));
		panelNoiDung.add(panelThongTin, BorderLayout.NORTH);

		JLabel lblPhieuDoiThuoc = new JLabel("Phiếu đổi thuốc", JLabel.CENTER);
		lblPhieuDoiThuoc.setBackground(Color.WHITE);
		panelThongTin.add(lblPhieuDoiThuoc, BorderLayout.NORTH);
		lblPhieuDoiThuoc.setFont(new Font("Arial", Font.BOLD, 20));

		// Lấy ngày giờ hiện tại
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
		String ngayGioHienTai = dateFormat.format(new Date());

		lblThongTin = new JLabel("<html>Mã NV: " + nhanVien.getMaNV() + "<br>Ngày lập: " + ngayGioHienTai
				+ "<br>Dược sĩ: " + nhanVien.getHoTen() + "<br>Bệnh nhân: " + tenKhachHang + " - "
				+ soDienThoaiKhachHang + "</html>");
		lblThongTin.setBackground(Color.WHITE);
		panelThongTin.add(lblThongTin, BorderLayout.WEST);
		lblThongTin.setFont(new Font("Arial", Font.ITALIC, 9));

		JLabel lblMaHoaDon = new JLabel("PD001      ", JLabel.LEFT);
		panelThongTin.add(lblMaHoaDon, BorderLayout.EAST);

		// Panel bảng
		JPanel panelBang = new JPanel();
		panelBang.setBackground(Color.WHITE);
		panelBang.setLayout(new BoxLayout(panelBang, BoxLayout.Y_AXIS));
		panelNoiDung.add(panelBang, BorderLayout.CENTER);

		// Bảng thuốc cũ
		JLabel lblThuocCu = new JLabel("Thuốc cũ", JLabel.CENTER);
		lblThuocCu.setFont(new Font("Arial", Font.BOLD, 14));
		lblThuocCu.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBang.add(Box.createVerticalStrut(10));
		panelBang.add(lblThuocCu);

		String[] tenCot = { "T.Thuốc", "-", "SL", "+", "Đơn Vị", "Đơn Giá", "T.Tiền" };
		

		 modelThuocCu = new DefaultTableModel(duLieuThuocCu, tenCot);
		bangThuocCu = new JTable(modelThuocCu) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					((JComponent) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
				}
				return c;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 3; // Chỉ các cột nút là chỉnh sửa được
			}
		};

		bangThuocCu.setShowGrid(false);
		bangThuocCu.setIntercellSpacing(new Dimension(0, 0));
		bangThuocCu.setRowHeight(20);
		bangThuocCu.setBackground(Color.WHITE);
		bangThuocCu.getTableHeader().setBackground(Color.WHITE);
		bangThuocCu.getTableHeader().setForeground(Color.BLACK);
		bangThuocCu.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
		bangThuocCu.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
		bangThuocCu.setFont(new Font("Arial", Font.ITALIC, 9));

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < bangThuocCu.getColumnCount(); i++) {
			if (i != 1 && i != 3) { // Bỏ qua cột nút
				bangThuocCu.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		}

		// Thêm nút vào cột thứ hai và thứ tư
		bangThuocCu.getColumnModel().getColumn(1).setCellRenderer(new NutGiamRenderer());
		bangThuocCu.getColumnModel().getColumn(1).setCellEditor(new TrinhChinhSuaNutGiam(new JCheckBox(), bangThuocCu));
		bangThuocCu.getColumnModel().getColumn(3).setCellRenderer(new NutTangRenderer());
		bangThuocCu.getColumnModel().getColumn(3).setCellEditor(new TrinhChinhSuaNutTang(new JCheckBox(), bangThuocCu));

		// Đặt chiều rộng cột nút
		bangThuocCu.getColumnModel().getColumn(1).setPreferredWidth(30);
		bangThuocCu.getColumnModel().getColumn(3).setPreferredWidth(30);

		JScrollPane scrollPaneThuocCu = new JScrollPane(bangThuocCu);
		scrollPaneThuocCu.setBorder(BorderFactory.createEmptyBorder());
		scrollPaneThuocCu.getViewport().setBackground(Color.WHITE);
		scrollPaneThuocCu.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneThuocCu.setPreferredSize(new Dimension(340, 120));
		panelBang.add(scrollPaneThuocCu);
		panelBang.add(Box.createVerticalStrut(20));

		// Bảng thuốc mới
		JLabel lblThuocMoi = new JLabel("Thuốc mới", JLabel.CENTER);
		lblThuocMoi.setFont(new Font("Arial", Font.BOLD, 14));
		lblThuocMoi.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelBang.add(lblThuocMoi);

		
//		doiThuoc.duLieuThuocMoi = new String[][] {
//		    {"Amoxicillin", "", "8", "", "Viên", "5000", "40000"},
//		    {"Vitamin C", "", "12", "", "Viên", "1500", "18000"}
//		};
		 modelThuocMoi = new DefaultTableModel(duLieuThuocMoi, tenCot);
		bangThuocMoi = new JTable(modelThuocMoi) {
			@Override
			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component c = super.prepareRenderer(renderer, row, column);
				if (c instanceof JComponent) {
					((JComponent) c).setBorder(BorderFactory.createEmptyBorder(0, 0, 1, 0));
				}
				return c;
			}

			@Override
			public boolean isCellEditable(int row, int column) {
				return column == 1 || column == 3; // Chỉ các cột nút là chỉnh sửa được
			}
		};

		bangThuocMoi.setShowGrid(false);
		bangThuocMoi.setIntercellSpacing(new Dimension(0, 0));
		bangThuocMoi.setRowHeight(20);
		bangThuocMoi.setBackground(Color.WHITE);
		bangThuocMoi.getTableHeader().setBackground(Color.WHITE);
		bangThuocMoi.getTableHeader().setForeground(Color.BLACK);
		bangThuocMoi.getTableHeader().setBorder(BorderFactory.createMatteBorder(2, 0, 2, 0, Color.BLACK));
		bangThuocMoi.getTableHeader().setFont(new Font("Arial", Font.BOLD, 10));
		bangThuocMoi.setFont(new Font("Arial", Font.ITALIC, 9));

		for (int i = 0; i < bangThuocMoi.getColumnCount(); i++) {
			if (i != 1 && i != 3) { // Bỏ qua cột nút
				bangThuocMoi.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			}
		}

		// Thêm nút vào cột thứ hai và thứ tư
		bangThuocMoi.getColumnModel().getColumn(1).setCellRenderer(new NutGiamRenderer());
		bangThuocMoi.getColumnModel().getColumn(1)
				.setCellEditor(new TrinhChinhSuaNutGiam(new JCheckBox(), bangThuocMoi));
		bangThuocMoi.getColumnModel().getColumn(3).setCellRenderer(new NutTangRenderer());
		bangThuocMoi.getColumnModel().getColumn(3)
				.setCellEditor(new TrinhChinhSuaNutTang(new JCheckBox(), bangThuocMoi));

		// Đặt chiều rộng cột nút
		bangThuocMoi.getColumnModel().getColumn(1).setPreferredWidth(30);
		bangThuocMoi.getColumnModel().getColumn(3).setPreferredWidth(30);

		JScrollPane scrollPaneThuocMoi = new JScrollPane(bangThuocMoi);
		scrollPaneThuocMoi.setBorder(BorderFactory.createEmptyBorder());
		scrollPaneThuocMoi.getViewport().setBackground(Color.WHITE);
		scrollPaneThuocMoi.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPaneThuocMoi.setPreferredSize(new Dimension(340, 120));
		panelBang.add(scrollPaneThuocMoi);

		// Panel chân trang
		JPanel panelChanTrang = new JPanel();
		panelChanTrang.setBackground(Color.WHITE);
		panelChanTrang.setLayout(new BorderLayout(0, 0));
		add(panelChanTrang, BorderLayout.SOUTH);

		// VAT và đường kẻ
		JPanel panelVAT = new JPanel();
		panelVAT.setBackground(Color.WHITE);
		panelVAT.setLayout(new BorderLayout(0, 0));
		panelChanTrang.add(panelVAT, BorderLayout.NORTH);

		JPanel panelNoiDungVAT = new JPanel();
		panelNoiDungVAT.setBackground(Color.WHITE);
		panelNoiDungVAT.setLayout(new BorderLayout(0, 0));
		panelVAT.add(panelNoiDungVAT, BorderLayout.NORTH);

		JLabel lblDuongKeNgang = new JLabel("<html><hr style='width:1000px;border:2px dashed black;'></html>");
		lblDuongKeNgang.setFont(new Font("Arial", Font.ITALIC, 9));
		panelNoiDungVAT.add(lblDuongKeNgang, BorderLayout.NORTH);

		JLabel lblVAT = new JLabel("VAT: 10%");
		lblVAT.setFont(new Font("Arial", Font.ITALIC, 9));
		panelNoiDungVAT.add(lblVAT, BorderLayout.WEST);

		lblTienVAT = new JLabel("10,000      ");
		lblTienVAT.setFont(new Font("Arial", Font.ITALIC, 9));
		panelNoiDungVAT.add(lblTienVAT, BorderLayout.EAST);

		// Tổng tiền và thanh toán
		JPanel panelThanhToan = new JPanel();
		panelThanhToan.setBackground(Color.WHITE);
		panelThanhToan.setLayout(new GridLayout(3, 2, 0, 0));
		panelChanTrang.add(panelThanhToan, BorderLayout.CENTER);

		JLabel lblTongCong = new JLabel("Tổng Cộng");
		lblTongCong.setFont(new Font("Arial", Font.BOLD, 20));
		panelThanhToan.add(lblTongCong);

		lblTongTien = new JLabel("", JLabel.RIGHT);
		lblTongTien.setFont(new Font("Arial", Font.ITALIC, 15));
		panelThanhToan.add(lblTongTien);

		JLabel lblHinhThucThanhToan = new JLabel("Hình thức thanh toán");
		lblHinhThucThanhToan.setFont(new Font("Arial", Font.BOLD, 13));
		panelThanhToan.add(lblHinhThucThanhToan);

		String[] phuongThucThanhToan = { "Tiền mặt", "Ngân hàng", "Momo" };
		JComboBox<String> comboBoxThanhToan = new JComboBox<>(phuongThucThanhToan);
		panelThanhToan.add(comboBoxThanhToan);

		JLabel lblLyDoDoi = new JLabel("Lý do đổi thuốc: ");
		lblLyDoDoi.setFont(new Font("Arial", Font.BOLD, 14));
		panelThanhToan.add(lblLyDoDoi);

		lblLyDo = new JLabel("Hết hạn sử dụng      ", JLabel.RIGHT);
		lblLyDo.setFont(new Font("Arial", Font.ITALIC, 10));
		panelThanhToan.add(lblLyDo);

		// Panel cảm ơn
		JPanel panelCamOn = new JPanel();
		panelCamOn.setBackground(Color.WHITE);
		panelCamOn.setLayout(new GridLayout(0, 1, -10, -10));
		panelChanTrang.add(panelCamOn, BorderLayout.SOUTH);

		JLabel lblDuongKeChan = new JLabel("<html><hr style='width:1000px;border:2px dashed black;'></html>");
		panelCamOn.add(lblDuongKeChan);

		lblCamOn = new JLabel("<html><center>Cảm ơn quý khách!<br>" + tenKhachHang + "</center></html>", JLabel.CENTER);
		lblCamOn.setFont(new Font("Arial", Font.BOLD, 20));
		panelCamOn.add(lblCamOn);

		calculateTotal();
	}

	private void calculateTotal() {
		double totalThuocCu = 0;
		double totalThuocMoi = 0;
		double vat = 0.1;

		for (int i = 0; i < bangThuocCu.getRowCount(); i++) {
			String thanhTienStr = bangThuocCu.getValueAt(i, 6).toString().replace(",", "");
			totalThuocCu += Double.parseDouble(thanhTienStr);
		}

		for (int i = 0; i < bangThuocMoi.getRowCount(); i++) {
			String thanhTienStr = bangThuocMoi.getValueAt(i, 6).toString().replace(",", "");
			totalThuocMoi += Double.parseDouble(thanhTienStr);
		}
		lblTienVAT.setText(String.format("%,.0f   ", totalThuocMoi * vat) + "    ");
		totalThuocMoi += totalThuocMoi * vat;
		double finalTotal = totalThuocMoi - totalThuocCu;

		if (finalTotal < 0) {
			lblTongTien.setText(String.format("Hoàn tiền %,.0f   ", Math.abs(finalTotal)));
		} else {
			lblTongTien.setText(String.format("%,.0f   ", finalTotal));
		}
	}

	private void tongtien() {
		double totalThuocCu = 0;
		double totalThuocMoi = 0;
		double vat = 0.1;

		for (int i = 0; i < bangThuocCu.getRowCount(); i++) {
			String thanhTienStr = bangThuocCu.getValueAt(i, 6).toString().replace(",", "");
			Double tien = Double.parseDouble(thanhTienStr) * 1000.0;
			if (tien > 10000000) {
				tien = tien / 1000;
			}
			totalThuocCu += tien;
		}

		for (int i = 0; i < bangThuocMoi.getRowCount(); i++) {
			String thanhTienStr = bangThuocMoi.getValueAt(i, 6).toString().replace(",", "");
			Double tien = Double.parseDouble(thanhTienStr) * 1000.0;
			if (tien > 10000000) {
				tien = tien / 1000;
			}
			totalThuocMoi += tien;
		}
		lblTienVAT.setText(String.format("%,.0f   ", totalThuocMoi * vat) + "    ");
		totalThuocMoi += totalThuocMoi * vat;
		double finalTotal = totalThuocMoi - totalThuocCu;

		if (finalTotal < 0) {
			lblTongTien.setText(String.format("Hoàn tiền %,.0f   ", Math.abs(finalTotal)));
		} else {
			lblTongTien.setText(String.format("%,.0f   ", finalTotal));
		}

	}

	class NutGiamRenderer extends JButton implements TableCellRenderer {
		public NutGiamRenderer() {
			setText("-");
			setFont(new Font("Arial", Font.BOLD, 10));
			setPreferredSize(new Dimension(20, 20));
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
	}

	class NutTangRenderer extends JButton implements TableCellRenderer {
		public NutTangRenderer() {
			setText("+");
			setFont(new Font("Arial", Font.BOLD, 10));
			setPreferredSize(new Dimension(20, 20));
			setContentAreaFilled(false);
			setBorderPainted(false);
			setFocusPainted(false);
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			return this;
		}
	}

	class TrinhChinhSuaNutGiam extends DefaultCellEditor {
		private JButton nutGiam;
		private JTable bang;
		private int dong;

		public TrinhChinhSuaNutGiam(JCheckBox checkBox, JTable bang) {
			super(checkBox);
			this.bang = bang;

			nutGiam = new JButton("-");
			nutGiam.setFont(new Font("Arial", Font.BOLD, 10));
			nutGiam.setPreferredSize(new Dimension(20, 20));
			nutGiam.setContentAreaFilled(false);
			nutGiam.setBorderPainted(false);
			nutGiam.setFocusPainted(false);

			nutGiam.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int soLuong = Integer.parseInt(bang.getValueAt(dong, 2).toString());
					if (soLuong > 1) {
						soLuong--;
						bang.setValueAt(String.valueOf(soLuong), dong, 2);
						double donGia = Double.parseDouble(bang.getValueAt(dong, 5).toString().replace(",", ""));
						double thanhTien = soLuong * donGia;
						bang.setValueAt(String.format("%,.0f", thanhTien), dong, 6);
						tongtien();
					}
					fireEditingStopped();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.dong = row;
			return nutGiam;
		}

		@Override
		public Object getCellEditorValue() {
			return "";
		}
	}

	class TrinhChinhSuaNutTang extends DefaultCellEditor {
		private JButton nutTang;
		private JTable bang;
		private int dong;

		public TrinhChinhSuaNutTang(JCheckBox checkBox, JTable bang) {
			super(checkBox);
			this.bang = bang;

			nutTang = new JButton("+");
			nutTang.setFont(new Font("Arial", Font.BOLD, 10));
			nutTang.setPreferredSize(new Dimension(20, 20));
			nutTang.setContentAreaFilled(false);
			nutTang.setBorderPainted(false);
			nutTang.setFocusPainted(false);

			nutTang.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int soLuong = Integer.parseInt(bang.getValueAt(dong, 2).toString());
					soLuong++;
					bang.setValueAt(String.valueOf(soLuong), dong, 2);
					double donGia = Double.parseDouble(bang.getValueAt(dong, 5).toString().replace(",", ""));
					double thanhTien = soLuong * donGia;
					bang.setValueAt(String.format("%,.0f", thanhTien), dong, 6);
					tongtien();
				}
			});
		}

		@Override
		public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row,
				int column) {
			this.dong = row;
			return nutTang;
		}

		@Override
		public Object getCellEditorValue() {
			return "";
		}
	}
	public void themThuocMoi(ent_thuoc thuoc, int soLuong) {
		if(thuoc!= null) {
			String tenThuoc = thuoc.getTenThuoc();
		    String donVi = thuoc.getTenDonVi();
		    double donGia = thuoc.getGiaBan();
		    double thanhTien = donGia * soLuong;

		    

		    modelThuocMoi.addRow(new Object[]{
		        tenThuoc,
		        "",
		        String.valueOf(soLuong),
		        "",
		        donVi,
		        donGia,
		        thanhTien
		    });

		    tongtien();
		}else {
			System.out.println("Không có thuốc");
		}
	}
	public void themThuocCu(ent_thuoc thuoc, int soLuong) {
		if(thuoc!= null) {
			String tenThuoc = thuoc.getTenThuoc();
		    String donVi = thuoc.getTenDonVi();
		    double donGia = thuoc.getGiaBan();
		    double thanhTien = donGia * soLuong;

		    

		    modelThuocCu.addRow(new Object[]{
		        tenThuoc,
		        "",
		        String.valueOf(soLuong),
		        "",
		        donVi,
		        donGia,
		        thanhTien
		    });

		    tongtien();
		}else {
			System.out.println("Không có thuốc");
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame("Phiếu Đổi Thuốc");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(350, 600);
		frame.setLocationRelativeTo(null);
		frame.getContentPane().add(new doiThuoc());
		frame.setVisible(true);
	}
}