package hieuUng;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import controller.ConnectDB;
import frm.frm_DangNhap;
import frm.frm_home;
import frm_default.frm_default;
import frm_default.frm_default2;
import frm_default.frm_default_ThongKe;
import frm_default.frm_default_timKiem;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.NumberFormat;
import java.util.Locale;

public class theGioiThieu extends JButton implements ActionListener {
	protected String avatarImage;
	protected String name;
	protected String position;
	protected String email;
	protected String phone;
	protected String birthDate;
	protected String startDate;
	protected String status = "Đang hoạt động"; // Mặc định trạng thái

	protected JPopupMenu popupMenu;
	protected JPanel jp1, jp2, jp3, jp11, jp12;
	private JButton logoutButton;

	public theGioiThieu(String maNV) {
		if (maNV != null && !maNV.trim().isEmpty()) {
			try {
				Connection conn = ConnectDB.getConnection();
				String sql = "SELECT nv.hoTen, nv.sDT, nv.email, nv.ngaySinh, nv.ngayVaoLam, cv.tenChucVu "
						+ "FROM NhanVien nv " + "JOIN ChucVu cv ON nv.maChucVu = cv.maChucVu " + "WHERE nv.maNV = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, maNV);
				ResultSet rs = ps.executeQuery();

				if (rs.next()) {
					name = rs.getString("hoTen");
					phone = rs.getString("sDT");
					email = rs.getString("email");
					birthDate = rs.getString("ngaySinh");
					startDate = rs.getString("ngayVaoLam");
					position = rs.getString("tenChucVu");
					avatarImage = "/IMG_NhanVien/" + maNV + ".png";
				} else {
					System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
				}

				rs.close();
				ps.close();
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		setPreferredSize(new Dimension(350, 75));
		setOpaque(false);
		setBorderPainted(false);
		setContentAreaFilled(false);
		setFocusPainted(false);

		popupMenu = new boGocPopupMenu();
		popupMenu.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		popupMenu.setLayout(new BorderLayout());

		// ===== Thông tin cá nhân =====
		jp1 = new JPanel(new BorderLayout());
		jp1.setBackground(Color.WHITE);
		jp1.setPreferredSize(new Dimension(380, 200));
		jp1.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
				"Thông tin cá nhân", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
				new Color(180, 220, 85)));

		JPanel infoPanel = new JPanel(new BorderLayout(10, 0));
		infoPanel.setOpaque(false);

		jp11 = new JPanel(new BorderLayout());
		jp11.setPreferredSize(new Dimension(120, 120));
		jp11.setBackground(Color.WHITE);

		JLabel avatarLabel = new JLabel();
		avatarLabel.setIcon(ImageResizer.resizeImage(avatarImage, 130, 200));
		avatarLabel.setHorizontalAlignment(JLabel.CENTER);
		jp11.add(avatarLabel, BorderLayout.CENTER);

		jp12 = new JPanel(new GridBagLayout());
		jp12.setBackground(Color.WHITE);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridx = 0;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(2, 2, 2, 2);

		// Name
		JLabel nameLabel = new JLabel("<html><div style='width:150px;'>" + name + "</div></html>");
		nameLabel.setFont(new Font("", Font.BOLD, 20));
		gbc.gridy = 0;
		gbc.gridheight = 2;
		jp12.add(nameLabel, gbc);
		gbc.gridheight = 1;

		// Position
		gbc.gridy = 2;
		JLabel positionLabel = new JLabel(
				"<html><div style='width:150px;'><b>Chức vụ: </b>" + position + "</div></html>");
		positionLabel.setFont(new Font("", Font.PLAIN, 10));
		jp12.add(positionLabel, gbc);

		// Phone
		gbc.gridy = 3;
		JLabel phoneLabel = new JLabel("<html><div style='width:150px;'><b>SDT: </b>" + phone + "</div></html>");
		phoneLabel.setFont(new Font("", Font.PLAIN, 10));
		jp12.add(phoneLabel, gbc);

		// Email
		gbc.gridy = 4;
		JLabel emailLabel = new JLabel("<html><div style='width:150px;'><b>Email: </b>" + email + "</div></html>");
		emailLabel.setFont(new Font("", Font.PLAIN, 10));
		jp12.add(emailLabel, gbc);

		// Birth Date
		gbc.gridy = 5;
		JLabel birthDateLabel = new JLabel(
				"<html><div style='width:150px;'><b>Ngày sinh: </b>" + birthDate + "</div></html>");
		birthDateLabel.setFont(new Font("", Font.PLAIN, 10));
		jp12.add(birthDateLabel, gbc);

		// Start Date
		gbc.gridy = 6;
		JLabel startDateLabel = new JLabel(
				"<html><div style='width:150px;'><b>Ngày vào làm: </b>" + startDate + "</div></html>");
		startDateLabel.setFont(new Font("", Font.PLAIN, 10));
		jp12.add(startDateLabel, gbc);

		// Status
		gbc.gridy = 7;
		JLabel statusLabel = new JLabel("<html><div style='width:150px;'><b>Trạng thái: </b><span style='color:green;'>"
				+ status + "</span></div></html>");
		statusLabel.setFont(new Font("", Font.PLAIN, 10));
		jp12.add(statusLabel, gbc);

		JScrollPane scrollPane = new JScrollPane(jp12);
		scrollPane.setPreferredSize(new Dimension(200, 150));
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(null);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
		scrollPane.getVerticalScrollBar().setPreferredSize(new Dimension(8, 0));
		scrollPane.getVerticalScrollBar().setOpaque(false);
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		infoPanel.add(jp11, BorderLayout.WEST);
		infoPanel.add(scrollPane, BorderLayout.CENTER);
		jp1.add(infoPanel, BorderLayout.CENTER);

		// ===== KPI Hôm nay =====
		jp2 = new JPanel(new GridBagLayout());
		jp2.setBackground(Color.WHITE);
		jp2.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(180, 220, 85), 2),
				"KPI hôm nay", TitledBorder.LEADING, TitledBorder.TOP, new Font("Arial", Font.BOLD, 14),
				new Color(180, 220, 85)));

		GridBagConstraints kpiGbc = new GridBagConstraints();
		kpiGbc.gridx = 0;
		kpiGbc.fill = GridBagConstraints.HORIZONTAL;
		kpiGbc.anchor = GridBagConstraints.WEST;
		kpiGbc.insets = new Insets(5, 10, 5, 10);

		JLabel kpiTitle = new JLabel("<html><b>Tổng Doanh Thu:</b></html>", JLabel.CENTER);
		kpiTitle.setFont(new Font("", Font.BOLD, 28));
		kpiTitle.setForeground(new Color(0x22FF00));
		kpiGbc.gridy = 0;
		jp2.add(kpiTitle, kpiGbc);

		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
		String revenue = currencyFormat.format(20_000_000).replace("₫", "VND");
		JLabel revenueLabel = new JLabel("<html><div style='color:#22FF00;'>" + revenue + "</div></html>",
				JLabel.CENTER);
		revenueLabel.setFont(new Font("", Font.BOLD, 20));
		kpiGbc.gridy = 1;
		jp2.add(revenueLabel, kpiGbc);

		JPanel financialDetailsPanel = new JPanel(new GridLayout(4, 2, 10, 5));
		financialDetailsPanel.setBackground(Color.white);

		financialDetailsPanel
				.add(new JLabel("Số hóa đơn:", ImageResizer.resizeImage("/image/menuHoaDon.png", 20, 20), JLabel.LEFT));
		financialDetailsPanel.add(new JLabel("120 hóa đơn", JLabel.RIGHT));

		financialDetailsPanel.add(
				new JLabel("Tổng số thuốc:", ImageResizer.resizeImage("/image/menuThuoc.png", 20, 20), JLabel.LEFT));
		financialDetailsPanel.add(new JLabel("350 hộp", JLabel.RIGHT));

		financialDetailsPanel
				.add(new JLabel("Tiền mặt:", ImageResizer.resizeImage("/image/TienMac.png", 20, 20), JLabel.LEFT));
		financialDetailsPanel.add(new JLabel(currencyFormat.format(15_000_000).replace("₫", "VND"), JLabel.RIGHT));

		financialDetailsPanel.add(
				new JLabel("Chuyển khoản:", ImageResizer.resizeImage("/image/ChuyenKhoan.png", 20, 20), JLabel.LEFT));
		financialDetailsPanel.add(new JLabel(currencyFormat.format(500_000).replace("₫", "VND"), JLabel.RIGHT));

		kpiGbc.gridy = 2;
		jp2.add(financialDetailsPanel, kpiGbc);

		// ===== Nút đăng xuất =====
		jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		jp3.setBackground(Color.WHITE);

		logoutButton = new JButton("Đăng xuất");
		logoutButton.setPreferredSize(new Dimension(420, 40));
		logoutButton.setBackground(new Color(231, 76, 60));
		logoutButton.setForeground(Color.WHITE);
		logoutButton.setFocusPainted(false);
		logoutButton.setContentAreaFilled(true);
		logoutButton.setBorderPainted(false);
		logoutButton.addActionListener(this);
		jp3.add(logoutButton);

		// ===== Thêm vào popup menu =====
		popupMenu.add(jp1, BorderLayout.NORTH);
		popupMenu.add(jp2, BorderLayout.CENTER);
		popupMenu.add(jp3, BorderLayout.SOUTH);
		popupMenu.setPopupSize(350, 500);

		this.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (popupMenu.isShowing()) {
					popupMenu.setVisible(false);
				} else {
					popupMenu.show(theGioiThieu.this, 0, getHeight());
				}
			}
		});

		// Panel hiển thị avatar, tên ngắn gọn
		JPanel innerPanel = new ProfilePanel(name, position, avatarImage);
		innerPanel.setOpaque(false);
		innerPanel.setLayout(null);
		innerPanel.setBounds(0, 0, 300, 100);
		setLayout(null);
		add(innerPanel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g.create();
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		int width = getWidth();
		int height = getHeight();
		int cornerRadius = 80;

		g2.setColor(Color.WHITE);
		g2.fillRoundRect(1, 1, width - 2, height - 2, cornerRadius, cornerRadius);

		g2.setColor(Color.GRAY);
		g2.setStroke(new BasicStroke(1));
		g2.drawRoundRect(1, 1, width - 2, height - 2, cornerRadius, cornerRadius);

		g2.dispose();
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			JFrame frame = new JFrame("Giới Thiệu Nhân Viên");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setSize(400, 200);
			frame.setLocationRelativeTo(null);

			theGioiThieu gioiThieuBtn = new theGioiThieu("NV001");

			JPanel panel = new JPanel();
			panel.setBackground(Color.LIGHT_GRAY);
			panel.add(gioiThieuBtn);

			frame.setContentPane(panel);
			frame.setVisible(true);
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(logoutButton)) {
			System.out.println("dang xuat");
			frm_default.maTK_DaDangNhap = null;
			frm_default2.maTK_DaDangNhap = null;
			frm_default_timKiem.maTK_DaDangNhap = null;
			frm_default_ThongKe.maTK_DaDangNhap = null;
			frm_home.maTK_DaDangNhap = null;
			this.dispose();
			new frm_DangNhap();
		}
	}

	private void dispose() {
		Window window = SwingUtilities.getWindowAncestor(this);
		if (window != null) {
		    window.dispose();
		}


	}

}
