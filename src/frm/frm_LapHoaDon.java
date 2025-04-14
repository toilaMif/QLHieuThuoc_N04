package frm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import dao.dao_thuoc;
import entity.ent_thuoc;
import frm_default.frm_default;
import hieuUng.GradientPanel;
import hieuUng.HoaDon;
import hieuUng.ImageResizer;

public class frm_LapHoaDon extends frm_default implements ActionListener, MouseListener {
	private HoaDon hoaDon;
	private JButton jbtnInHoaDon;
	private JButton jbtnXoaRong;
	private GradientPanel painKhungDieuKhien;
	private GradientPanel bangSP;
	private JPanel panelContainer;
	private NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));

	private dao_thuoc thuocDAO;
	private ArrayList<ent_thuoc> danhSachThuoc;
	private ImageIcon anh;

	public frm_LapHoaDon() {
		super();
		setTitle("Lập Hóa Đơn");
		jTenTrang.setText("Lập Hóa Đơn");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuHoaDon.png", 50, 50));

		thuocDAO = new dao_thuoc();
		danhSachThuoc = thuocDAO.getAllThuoc();

		// Hoa Don Panel
		hoaDon = new HoaDon();
		hoaDon.setCornerRadius(50);
		hoaDon.setBounds(20, 125, 310, 550);
		hoaDon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		jpWest.add(hoaDon, BorderLayout.CENTER);

		

		jbtnInHoaDon = new JButton("In hóa đơn");
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
		painKhungDieuKhien
				.setBorder(BorderFactory.createCompoundBorder(
						BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
								"Bảng Thông Tin", TitledBorder.LEADING, TitledBorder.TOP,
								new Font("Arial", Font.BOLD, 14), new Color(180, 220, 85)),
						BorderFactory.createEmptyBorder(10, 10, 10, 10)));
		painKhungDieuKhien.setPreferredSize(new Dimension(jpBangChinh.getPreferredSize().width - 20, 100));
		painKhungDieuKhien.setLayout(new GridLayout(1, 4, 15, 0));

		JPanel panelMaThuoc = new JPanel(new BorderLayout(5, 5));
		panelMaThuoc.setBackground(Color.white);
		JLabel lblMaThuoc = new JLabel("Mã Thuốc");
		JTextField txtMaThuoc = new JTextField();
		panelMaThuoc.add(lblMaThuoc, BorderLayout.NORTH);
		panelMaThuoc.add(txtMaThuoc, BorderLayout.CENTER);

		JPanel panelTenThuoc = new JPanel(new BorderLayout(5, 5));
		panelTenThuoc.setBackground(Color.white);
		JLabel lblTenThuoc = new JLabel("Tên Thuốc");
		JTextField txtTenThuoc = new JTextField();
		panelTenThuoc.add(lblTenThuoc, BorderLayout.NORTH);
		panelTenThuoc.add(txtTenThuoc, BorderLayout.CENTER);

		JPanel panelDanhMuc = new JPanel(new BorderLayout(5, 5));
		panelDanhMuc.setBackground(Color.white);
		JLabel lblDanhMuc = new JLabel("Danh Mục Thuốc:");
		String[] danhMuc = { "Tất cả" };
		JComboBox<String> cbDanhMuc = new JComboBox<>(danhMuc);
		panelDanhMuc.add(lblDanhMuc, BorderLayout.NORTH);
		panelDanhMuc.add(cbDanhMuc, BorderLayout.CENTER);

		JPanel panelButton = new JPanel(new BorderLayout(5, 5));
		panelButton.setBackground(Color.white);
		JLabel lblDummy = new JLabel("Nút chức năng:");
		JButton btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setBackground(new Color(101, 86, 255));
		btnTimKiem.setForeground(Color.WHITE);

		JButton btnXoaRongTim = new JButton("Xóa Rỗng");
		btnXoaRongTim.setBackground(Color.RED);
		btnXoaRongTim.setForeground(Color.WHITE);
		btnXoaRongTim.addActionListener(e -> {
			txtMaThuoc.setText("");
			txtTenThuoc.setText("");
			cbDanhMuc.setSelectedIndex(0);
			HienThiSPBAN();
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

		HienThiSPBAN();

		jpBangChinh.setLayout(new BorderLayout());
		jpBangChinh.add(painKhungDieuKhien, BorderLayout.NORTH);
		jpBangChinh.add(bangSP, BorderLayout.CENTER);

		setVisible(true);
		jbtThoat.addActionListener(this);
	}

	private void HienThiSPBAN() {
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
        if(o.equals(jbtThoat)) {
        	this.dispose();
        	new frm_home();
        }
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
		new frm_LapHoaDon();
	}
	
}