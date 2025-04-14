package frm_default;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import frm.frm_home;
import hieuUng.GradientPanel;
import hieuUng.ImageResizer;
import hieuUng.boGocTextField;
import hieuUng.chuongThongBao;
import hieuUng.nutBam;
import hieuUng.theGioiThieu;

public class frm_default_timKiem extends JFrame implements ActionListener, MouseListener {
	protected GradientPanel jpThanhCongCu;
	protected GradientPanel jpBangChinh;
	protected JLabel jTenTrang;
	protected JLabel jThoiGian;
	protected JPanel jpTheGTTmK;
	protected JPanel jpBody;
	protected JPanel jpHead;
	protected JLabel timkiem1;
	protected JTextField jtftimkiem1;
	protected JTextField jtftimkiem2;
	protected JLabel timkiem2;
	protected DefaultTableModel tableModel;
	protected JTable table;
	protected String[] tieuDeCot;
	protected JScrollPane scrollPane;
	protected JButton btnThem;
	protected JButton btnXuat;
	protected JTableHeader header;
	protected JButton btnTimKiem;
	protected JButton btnXoarong;
	protected JButton btnXem;
	protected JButton btnSua;
	protected JButton btnXoa;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	public static String maTK_DaDangNhap;
	public frm_default_timKiem() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("Tìm");
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		getContentPane().setBackground(new Color(0x013034));
		setResizable(false);
		getContentPane().setLayout(null);

		jpThanhCongCu = new GradientPanel();
		jpThanhCongCu.setGradientColors(Color.white, Color.white);
		jpThanhCongCu.setCornerRadius(90);
		jpThanhCongCu.setLayout(null);

		// tên trang
		jTenTrang = new JLabel("Tên trang");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/eye.png",40, 40));
		jTenTrang.setForeground(Color.black);
		jTenTrang.setFont(new Font("Arial", Font.BOLD, 36));
		jTenTrang.setBounds(20, 10, 550, 50);
		jpThanhCongCu.add(jTenTrang);

		// Ngày giờ
		jThoiGian = new JLabel();
		jThoiGian.setForeground(Color.black);
		jThoiGian.setFont(new Font("Arial", Font.PLAIN, 13));
		jThoiGian.setBounds(75, 50, 500, 30);
		jpThanhCongCu.add(jThoiGian);

		jpTheGTTmK = new JPanel();
		jpTheGTTmK.setOpaque(false);

		jpTheGTTmK.setBounds(1100, 0, 350, 100);
		jpTheGTTmK.setLayout(new FlowLayout());

		// Tạo avatar
		if (maTK_DaDangNhap != null) {
		    theGioiThieu gioiThieuBtn = new theGioiThieu(maTK_DaDangNhap);
		    jpTheGTTmK.removeAll();
		    jpTheGTTmK.add(gioiThieuBtn);
		    jpThanhCongCu.add(jpTheGTTmK, BorderLayout.CENTER);
		    jpThanhCongCu.revalidate();
		    jpThanhCongCu.repaint();
		} else {
		    System.out.println("Chưa có tài khoản đăng nhập.");
		}
		jpThanhCongCu.add(jpTheGTTmK);

		// Thông báo
		// Load ảnh chuông thông báo
		ImageIcon iconChuong = new ImageIcon(ClassLoader.getSystemResource("image/Chuông.png"));
		Image imgchuong = iconChuong.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		ImageIcon scaledIcon = new ImageIcon(imgchuong);

		// Tạo nút thông báo
		JButton jThongbao = new JButton(scaledIcon);
		jThongbao.setBounds(1000, 7, 70, 70);
		jThongbao.setOpaque(false);
		jThongbao.setBorderPainted(false);
		jThongbao.setFocusPainted(false);
		jThongbao.setContentAreaFilled(false);
		jThongbao.setUI(new chuongThongBao(100));

		jpThanhCongCu.add(jThongbao);

		getContentPane().add(jpThanhCongCu);

		jpBangChinh = new GradientPanel();
		jpBangChinh.setLayout(null);
		jpBangChinh.setGradientColors(Color.white, Color.white);
		jpBangChinh.setBackground(Color.white);
		jpBangChinh.setCornerRadius(55);

		jpHead = new JPanel();
		jpHead.setBackground(Color.white);
		jpHead.setBorder(new LineBorder(new Color(0xBCD365), 2));
		jpHead.setLayout(new BorderLayout(0, 0));
		
		
		JPanel jpTimKiemXoaRong = new JPanel();
		jpTimKiemXoaRong.setBackground(Color.white);
		jpTimKiemXoaRong.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); // Căn trái, khoảng cách 20px

		btnTimKiem = createButton("Tìm Kiếm", "", new Color(180, 220, 85));
		btnTimKiem.setPreferredSize(new Dimension(150, 30));

		btnXoarong = createButton("Xóa Rỗng", "", Color.red);
		btnXoarong.setPreferredSize(new Dimension(150, 30));

		jpTimKiemXoaRong.add(btnTimKiem);
		jpTimKiemXoaRong.add(btnXoarong);



		
		jpHead.add(jpTimKiemXoaRong, BorderLayout.SOUTH);
		

		
		jpBody = new JPanel();
		jpBody.setBackground(new Color(0xb3d787));
		jpBody.setBorder(new LineBorder(new Color(0xBCD365), 2));
		jpBody.setLayout(new BorderLayout());

		
		

	

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(48, 257, 560, 76);

		// Bảng dữ liệu
		  tieuDeCot = new String[] { "Tiêu đề", "Tiêu đề", "Tiêu đề", "Tiêu đề", "Tiêu đề", "Tiêu đề", "Tiêu đề", "Tiêu đề", "Tiêu đề" };
		tableModel = new DefaultTableModel( tieuDeCot, 0);
		table = new JTable(tableModel);
		table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                                                          boolean isSelected, boolean hasFocus,
                                                          int row, int column) {
                Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (!isSelected) { 
                    if (row % 2 == 0) {
                        cell.setBackground(Color.WHITE);
                    } else {
                        cell.setBackground(new Color(220, 245, 200)); 
                    }
                }
                return cell;
            }
        });
		

		 header = table.getTableHeader();
		header.setBackground(new Color(180, 220, 85));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.BOLD, 24));

		table.setGridColor(new Color(180, 220, 85));
		table.setShowGrid(true);
		table.setIntercellSpacing(new Dimension(1, 1));
		table.setRowHeight(25);
		table.setOpaque(true);
		table.setBackground(Color.WHITE);
		table.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		
		  scrollPane = new JScrollPane(table);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());
		scrollPane.getViewport().setOpaque(true);
		scrollPane.getViewport().setBackground(Color.WHITE);

		// Đổi màu thanh cuộn
		JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
		verticalBar.setBackground(new Color(180, 220, 85)); 
		verticalBar.setForeground(new Color(180, 220, 85)); 




		panel_1.setLayout(new BorderLayout());
		panel_1.setOpaque(true);
		panel_1.setBackground(Color.WHITE);
		panel_1.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 20));

		panel_1.add(scrollPane, BorderLayout.CENTER);
	

		jpBody.add(panel_1, BorderLayout.CENTER);
		
		

		// Nút thoát
		JButton jbtThoat = new JButton("Thoát");
		jbtThoat.setFont(new Font("Arial", Font.BOLD, 30));
		jbtThoat.setForeground(Color.white);
		jbtThoat.setBackground(new Color(0x1F4842));
		jbtThoat.setOpaque(false);
		jbtThoat.setBorderPainted(false);
		jbtThoat.setFocusPainted(false);
		jbtThoat.setUI(new nutBam(30));
		jbtThoat.addActionListener(this);

		jbtThoat.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jbtThoat.setBackground(new Color(0xAABB55));
				jbtThoat.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				jbtThoat.setBackground(new Color(0x1F4842));
				jbtThoat.repaint();
			}
		});

		
		
		

		

		jpBangChinh.add(jbtThoat);
		jpBangChinh.add(jpBody);
		jpBangChinh.add(jpHead);
		getContentPane().add(jpBangChinh);

		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				int width = getWidth();
				int height = getHeight();

				jpBangChinh.setBounds(0 + (int) (getWidth() * 0.025), 40 + (int) (getHeight() * 0.1),
						(int) (getWidth() * 0.95), (int) (getHeight() * 0.8));
				jpThanhCongCu.setBounds(0 + (int) (getWidth() * 0.025), 20, (int) (getWidth() * 0.95),
						(int) (getHeight() * 0.1));

				jpHead.setBounds(0 + (int) (jpBangChinh.getWidth() * 0.025), 20, (int) (jpBangChinh.getWidth() * 0.95),
						(int) (jpBangChinh.getHeight() * 0.15));
				jpBody.setBounds(0 + (int) (jpBangChinh.getWidth() * 0.025), 80 + (int) (jpBangChinh.getHeight() * 0.1),
						(int) (jpBangChinh.getWidth() * 0.95), (int) (jpBangChinh.getHeight() * 0.67));

				
				jbtThoat.setBounds(30, jpBangChinh.getHeight() - 60, 170, 46);
			}
		});

		setVisible(true);
		updateTime(); // Gọi hàm cập nhật thời gian

		
	}

	private void updateTime() {
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy -:- HH:mm:ss");
				String dateString = formatter.format(new Date());
				jThoiGian.setText(dateString);
			}
		}, 0, 1000); // Cập nhật mỗi giây
	}

	private static JButton createButton(String text, String iconPath, Color bgColor) {
		ImageIcon icon1 = null;
		try {
			java.net.URL imgURL = Thread.currentThread().getContextClassLoader().getResource(iconPath);
			if (imgURL != null) {
				icon1 = new ImageIcon(imgURL);
			} else {
				System.err.println("Không tìm thấy hình ảnh: " + iconPath);
				icon1 = new ImageIcon();
			}
		} catch (Exception e) {
			System.err.println("Lỗi khi tải hình ảnh: " + e.getMessage());
			icon1 = new ImageIcon();
		}

		// Resize hình ảnh
		Image img1 = icon1.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
		JButton button = new JButton(text, new ImageIcon(img1));

		button.setBackground(bgColor);
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Arial", Font.BOLD, 20));
		button.setFocusPainted(false);
		button.setBorderPainted(false);
		button.setOpaque(true);

		return button;
	}

	public static void main(String[] args) {
		new frm_default_timKiem();

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	    if (e.getActionCommand().equals("Thoát")) {
	        this.dispose(); 
	        new frm_home(); 
	    }
	}

}
