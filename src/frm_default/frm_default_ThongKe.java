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
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
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

import org.jfree.chart.ChartPanel;

import com.toedter.calendar.JDateChooser;

import frm.frm_home;
import hieuUng.BieuDoUtil;
import hieuUng.GradientPanel;
import hieuUng.ImageResizer;
import hieuUng.boGocTextField;
import hieuUng.chuongThongBao;
import hieuUng.nutBam;
import hieuUng.theGioiThieu;

public class frm_default_ThongKe extends JFrame implements ActionListener, MouseListener {
	protected GradientPanel jpThanhCongCu;
	protected GradientPanel jpBangChinh;
	protected JLabel jTenTrang;
	protected JLabel jThoiGian;
	protected JPanel jpTheGTTK;
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
	private JPanel jpTongKet;
	private JPanel jpBieuDo;
	public static String maTK_DaDangNhap;
	public frm_default_ThongKe() {
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setTitle("frm_ThongKeHoaDon");
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
		jTenTrang = new JLabel("Thống kê");
		jTenTrang.setIcon(ImageResizer.resizeImage("/image/menuHoaDon.png",40, 40));
		jTenTrang.setForeground(Color.black);
		jTenTrang.setFont(new Font("Arial", Font.BOLD, 36));
		jTenTrang.setBounds(20, 10, 350, 50);
		jpThanhCongCu.add(jTenTrang);

		// Ngày giờ
		jThoiGian = new JLabel();
		jThoiGian.setForeground(Color.black);
		jThoiGian.setFont(new Font("Arial", Font.PLAIN, 13));
		jThoiGian.setBounds(75, 50, 500, 30);
		jpThanhCongCu.add(jThoiGian);

		jpTheGTTK = new JPanel();
		jpTheGTTK.setOpaque(false);

		jpTheGTTK.setBounds(1100, 0, 350, 100);
		jpTheGTTK.setLayout(new FlowLayout());

		if (maTK_DaDangNhap != null) {
		    theGioiThieu gioiThieuBtn = new theGioiThieu(maTK_DaDangNhap);
		    jpTheGTTK.removeAll();
		    jpTheGTTK.add(gioiThieuBtn);
		    jpThanhCongCu.add(jpTheGTTK, BorderLayout.CENTER);
		    jpThanhCongCu.revalidate();
		    jpThanhCongCu.repaint();
		} else {
		    System.out.println("Chưa có tài khoản đăng nhập.");
		}
		
		jpThanhCongCu.add(jpTheGTTK);

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

		JPanel jpHead = new JPanel();
		jpHead.setBackground(Color.white);
		jpHead.setLayout(new BorderLayout(10, 10)); // Gán layout ở đây

		jpHead.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0xBCD365)),
		    "Lọc",
		    TitledBorder.LEADING,
		    TitledBorder.TOP,
		    new Font("Arial", Font.BOLD, 14),
		    new Color(0xBCD365)
		));

		JPanel jpLocThongTin = new JPanel();
		jpLocThongTin.setLayout(new GridLayout(1, 4, 15, 0));
		jpLocThongTin.setBackground(Color.white);


	

		JPanel panelTuNgay = new JPanel(new BorderLayout(5, 5));
		panelTuNgay.setBackground(Color.white);
		JLabel lblTuNgay = new JLabel("Từ ngày:");
		JDateChooser dateChooserTuNgay = new JDateChooser();
		dateChooserTuNgay.setDateFormatString("dd/MM/yyyy");
		dateChooserTuNgay.setBackground(Color.white);  // Nền trắng cho đồng bộ
		panelTuNgay.add(lblTuNgay, BorderLayout.NORTH);
		panelTuNgay.add(dateChooserTuNgay, BorderLayout.CENTER);


		// Panel Đến ngày
		JPanel panelDenNgay = new JPanel(new BorderLayout(5, 5));
		panelDenNgay.setBackground(Color.white);
		JLabel lbDenNgay = new JLabel("Đến ngày:");
		JDateChooser dateChooserDenNgay = new JDateChooser();
		dateChooserDenNgay.setDateFormatString("dd/MM/yyyy");
		dateChooserDenNgay.setBackground(Color.white);  // Nền trắng cho đồng bộ
		panelDenNgay.add(lbDenNgay, BorderLayout.NORTH);
		panelDenNgay.add(dateChooserDenNgay, BorderLayout.CENTER);

		// Panel Hình thức thanh toán
		JPanel panelHinhThuc = new JPanel(new BorderLayout(5, 5));
		panelHinhThuc.setBackground(Color.white);
		JLabel lblHinhThuc = new JLabel("Hình thức thanh toán:");
		String[] hinhThuc = {"Tất cả", "Tiền mặt", "Chuyển khoản", "Thẻ"};
		JComboBox<String> cbHinhThuc = new JComboBox<>(hinhThuc);
		panelHinhThuc.add(lblHinhThuc, BorderLayout.NORTH);
		panelHinhThuc.add(cbHinhThuc, BorderLayout.CENTER);

		// Panel nút Lọc
		JPanel panelButton = new JPanel(new BorderLayout(5, 5));
		panelButton.setBackground(Color.white);
		JLabel lblDummy = new JLabel(" ");
		JButton btnLoc = new JButton("Lọc");
		btnLoc.setBackground(new Color(101, 86, 255));
		btnLoc.setForeground(Color.WHITE);
		JButton btnXoaRong = new JButton("Xóa Rỗng");
		btnXoaRong.setBackground(Color.RED);
		btnXoaRong.setForeground(Color.WHITE);

		panelButton.add(btnXoaRong, BorderLayout.WEST);
		panelButton.add(lblDummy, BorderLayout.NORTH);
		panelButton.add(btnLoc, BorderLayout.CENTER);

		
		
		// Thêm các panel con vào jpLocThongTin
		jpLocThongTin.add(panelTuNgay);
		jpLocThongTin.add(panelDenNgay);
		jpLocThongTin.add(panelHinhThuc);
		jpLocThongTin.add(panelButton);

		// Gắn vào phần đầu của giao diện
		jpHead.add(jpLocThongTin, BorderLayout.CENTER);


	        
	        

		jpBody = new JPanel();
		jpBody.setBackground(Color.white);
		jpBody.setBorder(new LineBorder(new Color(0xBCD365), 2));
		jpBody.setLayout(new BorderLayout());

		jpBody.setBorder(BorderFactory.createTitledBorder(
			    BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0xBCD365)), // Chỉ có viền trên và dưới
			    "Danh sách", 
			    TitledBorder.LEADING, 
			    TitledBorder.TOP, 
			    new Font("Arial", Font.BOLD, 14), 
			    new Color(0xBCD365)
			));
		
	

		
		jpBody.setOpaque(false);

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
                if (!isSelected) { // Chỉ thay đổi nếu không được chọn
                    if (row % 2 == 0) {
                        cell.setBackground(Color.WHITE);
                    } else {
                        cell.setBackground(new Color(220, 245, 200)); // Màu xanh nhạt
                    }
                }
                return cell;
            }
        });
		

		 header = table.getTableHeader();
		header.setBackground(new Color(180, 220, 85));
		header.setForeground(Color.WHITE);
		header.setFont(new Font("Arial", Font.BOLD, 24));

		table.setRowHeight(8);
		table.setIntercellSpacing(new Dimension(0, 0)); 
		table.setShowGrid(true);
		table.setGridColor(new Color(180, 220, 85));
		table.setOpaque(true);
		table.setBackground(Color.WHITE);
		table.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


		
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
		
		//Nút Xuất file
		JButton jbtXuatFile = new JButton("Xuất File");
		jbtXuatFile.setFont(new Font("Arial", Font.BOLD, 30));
		jbtXuatFile.setForeground(Color.white);
		jbtXuatFile.setBackground(new Color(0x1F4842));
		jbtXuatFile.setOpaque(false);
		jbtXuatFile.setBorderPainted(false);
		jbtXuatFile.setFocusPainted(false);
		jbtXuatFile.setUI(new nutBam(30));
		jbtXuatFile.addActionListener(this);

		jbtXuatFile.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				jbtXuatFile.setBackground(new Color(0xAABB55));
				jbtXuatFile.repaint();
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				jbtXuatFile.setBackground(new Color(0x1F4842));
				jbtXuatFile.repaint();
			}
		});
		
		jpTongKet = new JPanel();
		jpTongKet.setBackground(Color.white);
		jpTongKet.setBorder(new LineBorder(new Color(0xBCD365), 2));

		jpTongKet.setBorder(BorderFactory.createTitledBorder(
			    BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0xBCD365)), 
			    "Tổng kết", 
			    TitledBorder.LEADING, 
			    TitledBorder.TOP, 
			    new Font("Arial", Font.BOLD, 14), 
			    new Color(0xBCD365)
			));

		
		JPanel jpBieuDo = new JPanel();
		jpBieuDo.setBackground(Color.white);
		jpBieuDo.setBorder(BorderFactory.createTitledBorder(
		    BorderFactory.createMatteBorder(1, 1, 1, 1, new Color(0xBCD365)), 
		    "Biểu Đồ", 
		    TitledBorder.LEADING, 
		    TitledBorder.TOP, 
		    new Font("Arial", Font.BOLD, 14), 
		    new Color(0xBCD365)
		));
		jpBieuDo.setLayout(new BorderLayout());

		// Panel chứa RadioButton
		JPanel panelButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JRadioButton rbCot = new JRadioButton("Cột");
		JRadioButton rbTron = new JRadioButton("Tròn");
		JRadioButton rbDuong = new JRadioButton("Đường");
		rbCot.setBackground(Color.white);
		rbTron.setBackground(Color.white);
		rbDuong.setBackground(Color.white);
		
		
		ButtonGroup group = new ButtonGroup();
		group.add(rbCot);
		group.add(rbTron);
		group.add(rbDuong);

		panelButtons.add(rbCot);
		panelButtons.add(rbTron);
		panelButtons.add(rbDuong);
		panelButtons.setBackground(Color.white);
		jpBieuDo.add(panelButtons, BorderLayout.NORTH);

		JPanel chartPanel = new JPanel(new BorderLayout());
		chartPanel.setBackground(Color.white);
		jpBieuDo.add(chartPanel, BorderLayout.CENTER);

		ActionListener actionListener = e -> {
		    chartPanel.removeAll();
		    ChartPanel chart = switch (e.getActionCommand()) {
		        case "Tròn" -> BieuDoUtil.createPieChartPanel();
		        case "Đường" -> BieuDoUtil.createLineChartPanel();
		        default -> BieuDoUtil.createBarChartPanel();
		    };
		    chart.setPreferredSize(new Dimension(650, 400));
		    chartPanel.add(chart, BorderLayout.CENTER);
		    chartPanel.revalidate();
		    chartPanel.repaint();
		};

		rbCot.setActionCommand("Cột");
		rbTron.setActionCommand("Tròn");
		rbDuong.setActionCommand("Đường");

		rbCot.addActionListener(actionListener);
		rbTron.addActionListener(actionListener);
		rbDuong.addActionListener(actionListener);

		rbCot.setSelected(true); 
		rbCot.doClick(); 

		
		
		
		

		jpBangChinh.add(jbtXuatFile);
		jpBangChinh.add(jbtThoat);
		jpBangChinh.add(jpBody);
		jpBangChinh.add(jpHead);
		jpBangChinh.add(jpTongKet);
		jpBangChinh.add(jpBieuDo);
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
						(int) (jpBangChinh.getHeight() * 0.1));
				jpBody.setBounds(0 + (int) (jpBangChinh.getWidth() * 0.025), 40 + (int) (jpBangChinh.getHeight() * 0.1),
						(int) (jpBangChinh.getWidth() * 0.95), (int) (jpBangChinh.getHeight() * 0.25));
				jpTongKet.setBounds(0 + (int) (jpBangChinh.getWidth() * 0.025), 220 + (int) (jpBangChinh.getHeight() * 0.1),
						(int) (jpBangChinh.getWidth() * 0.40), (int) (jpBangChinh.getHeight() * 0.50));
				jpBieuDo.setBounds(600 + (int) (jpBangChinh.getWidth() * 0.025), 220 + (int) (jpBangChinh.getHeight() * 0.1),
						(int) (jpBangChinh.getWidth() * 0.55-10), (int) (jpBangChinh.getHeight() * 0.50));
				jbtThoat.setBounds(30, jpBangChinh.getHeight() - 55, 170, 46);
				jbtXuatFile.setBounds(230, jpBangChinh.getHeight() - 55, 170, 46);
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
		new frm_default_ThongKe();

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
